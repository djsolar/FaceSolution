package solution.twinflag.com.facesolution.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URLEncoder;

import solution.twinflag.com.facesolution.domain.PersonInfo;
import solution.twinflag.com.facesolution.domain.RecognizeData;
import solution.twinflag.com.facesolution.util.Constant;

/**
 * Created by zhouyiran on 2018/4/3.
 */

public class WebSocketService extends IntentService {

    private static final String TAG = "WebSocketService";

    private static final String RTSP_URL = "rtsp://192.168.1.10/user=admin&password=&channel=1&stream=0.sdp";

    private String uri = "ws://192.168.1.50:9000/video?url=";

    private LocalBroadcastManager localBroadcastManager;

    private Gson gson;

    private WebSocketClient mClient;

    public WebSocketService() {
        super("WebSocketService");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public WebSocketService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        gson = new GsonBuilder().create();
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        mClient = new WebSocketClient(URI.create(uri + URLEncoder.encode(RTSP_URL))) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.i(TAG, "打开websocket");
            }

            @Override
            public void onMessage(String message) {
                Log.i(TAG, "message = " + message);
                RecognizeData recognizeData = gson.fromJson(message, RecognizeData.class);
                Log.i(TAG, "data = " + recognizeData);

                if (recognizeData.getType().equals(Constant.TYPE_RECOGNIZED)) {
                    PersonInfo personInfo = recognizeData.getPerson();
                    personInfo.setAvatar(Constant.SERVER_URL + personInfo.getAvatar());
                    Log.i(TAG, "person = " + personInfo.toString());
                    Intent commandIntent = new Intent(Constant.COMMAND_RECEIVER);
                    commandIntent.putExtra("personInfo", personInfo);
                    localBroadcastManager.sendBroadcast(commandIntent);
                }
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.i(TAG, "关闭websocket");
                mClient.reconnect();
                Log.i(TAG, "重连");
            }

            @Override
            public void onError(Exception ex) {
                Log.i(TAG, ex.getMessage());
                mClient.reconnect();
                Log.i(TAG, "重连");
            }
        };
        mClient.connect();
    }

}
