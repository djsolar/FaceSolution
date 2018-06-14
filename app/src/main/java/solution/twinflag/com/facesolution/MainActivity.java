package solution.twinflag.com.facesolution;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import solution.twinflag.com.facesolution.adapter.ServiceMenuAdapter;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.service_rv) RecyclerView recyclerView;

    void fillServiceMenu() {
        ServiceItem serviceItem1 = new ServiceItem(0, "考勤", R.mipmap.icon);
        ServiceItem serviceItem2 = new ServiceItem(1, "保险柜", R.mipmap.icon);
        ServiceItem serviceItem3 = new ServiceItem(2, "课堂", R.mipmap.icon);
        ServiceItem serviceItem4 = new ServiceItem(3, "借书", R.mipmap.icon);
        ServiceItem serviceItem5 = new ServiceItem(3, "食堂", R.mipmap.icon);

        List<ServiceItem> serviceItemList = new ArrayList<>();
        serviceItemList.add(serviceItem1);
        serviceItemList.add(serviceItem2);
        serviceItemList.add(serviceItem3);
        serviceItemList.add(serviceItem4);
        serviceItemList.add(serviceItem5);

        GridLayoutManager gm = new GridLayoutManager(this, 2);
        ServiceMenuAdapter serviceMenuAdapter = new ServiceMenuAdapter(serviceItemList);
        recyclerView.setLayoutManager(gm);
        recyclerView.setAdapter(serviceMenuAdapter);
    }

    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            recyclerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fillServiceMenu();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        delayedHide(0);
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
