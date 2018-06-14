package solution.twinflag.com.facesolution.food.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import solution.twinflag.com.facesolution.R;
import solution.twinflag.com.facesolution.domain.PersonInfo;
import solution.twinflag.com.facesolution.domain.WeatherInfo;
import solution.twinflag.com.facesolution.food.presenter.FootPresenter;
import solution.twinflag.com.facesolution.food.presenter.FootPresenterImpl;
import solution.twinflag.com.facesolution.util.Constant;


public class FaceFoodActivity extends AppCompatActivity implements FootView{


    @BindView(R.id.pay_money_ly)
    RelativeLayout payMoneyLayout;

    @BindView(R.id.left_money_ly)
    RelativeLayout leftMoneyLayout;

    @BindView(R.id.menu_price)
    TextView consumeTv;

    @BindView(R.id.scan_face_tip)
    TextView scanFaceTip;

    @BindView(R.id.enter_price_tip)
    TextView EnterPriceTipTv;

    @BindView(R.id.customer_name)
    TextView customerNameTv;

    @BindView(R.id.pay_money)
    TextView payMoneyTv;

    @BindView(R.id.left_money)
    TextView leftMoneyTv;

    @BindView(R.id.pay_tip)
    TextView paySuccessTipTv;

    @BindView(R.id.weather_temperature)
    TextView currentTempatureTv;

    @BindView(R.id.weather_info)
    TextView weatherInfo;

    private FootPresenter footPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_food);
        ButterKnife.bind(this);
        footPresenter = new FootPresenterImpl(this);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                PersonInfo personInfo = new PersonInfo();
                personInfo.setName("周义然(03912457)");
                footPresenter.payMoney(personInfo);
                handler.postDelayed(this, 3000);
            }
        }, 3000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        footPresenter.initView();
        registerBroadCastReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterBroadCastReceiver();
    }

    @Override
    public void showEnterPriceView() {
        consumeTv.setVisibility(View.VISIBLE);
        EnterPriceTipTv.setVisibility(View.VISIBLE);
    }

    @Override
    public void editPrice(String moneyStr) {
        consumeTv.setText(moneyStr);
    }

    @Override
    public void confirmPayMoneyView(String money) {
        String confirmStr = money + getResources().getString(R.string.money_unit);
        SpannableString spannableString = new SpannableString(confirmStr);
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(35, false);
        spannableString.setSpan(absoluteSizeSpan, confirmStr.length() - 1, confirmStr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        consumeTv.setText(spannableString);
        scanFaceTip.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideConfirmPayMoneyView() {
        scanFaceTip.setVisibility(View.GONE);
    }

    @Override
    public void hideEnterPriceView() {
        consumeTv.setVisibility(View.GONE);
        EnterPriceTipTv.setVisibility(View.GONE);
        scanFaceTip.setVisibility(View.GONE);
    }

    @Override
    public void showPaySuccessView(String name, String consumeMoney, String leftMoney, int tipId) {
        customerNameTv.setVisibility(View.VISIBLE);
        payMoneyLayout.setVisibility(View.VISIBLE);
        leftMoneyLayout.setVisibility(View.VISIBLE);
        paySuccessTipTv.setVisibility(View.VISIBLE);

        paySuccessTipTv.setTextColor(getResources().getColor(R.color.money_color));
        paySuccessTipTv.setText(tipId);
        customerNameTv.setText(name);
        payMoneyTv.setText(consumeMoney);
        leftMoneyTv.setText(leftMoney);
    }

    @Override
    public void showPayFailedView(String name, String consumeMoney, String leftMoney, int tipId) {
        customerNameTv.setVisibility(View.VISIBLE);
        payMoneyLayout.setVisibility(View.VISIBLE);
        leftMoneyLayout.setVisibility(View.VISIBLE);
        paySuccessTipTv.setVisibility(View.VISIBLE);

        paySuccessTipTv.setTextColor(getResources().getColor(R.color.money_fail_color));
        paySuccessTipTv.setText(tipId);
        customerNameTv.setText(name);
        payMoneyTv.setText(consumeMoney);
        leftMoneyTv.setText(leftMoney);
    }

    @Override
    public void hidePaySuccessView() {
        customerNameTv.setVisibility(View.GONE);
        paySuccessTipTv.setVisibility(View.GONE);
        payMoneyLayout.setVisibility(View.GONE);
        leftMoneyLayout.setVisibility(View.GONE);
    }

    @Override
    public String getConsumeMoney() {
        return this.consumeTv.getText().toString();
    }

    @Override
    public void showWeather(WeatherInfo weatherInfo) {

    }

    @Override
    public void showNetInfo(String message) {

    }

    private void unregisterBroadCastReceiver() {
        unregisterReceiver(faceDataReceive);
    }

    private void registerBroadCastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.COMMAND_RECEIVER);
        registerReceiver(faceDataReceive, intentFilter);
    }

    private BroadcastReceiver faceDataReceive = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            PersonInfo personInfo = intent.getParcelableExtra("personInfo");
            footPresenter.payMoney(personInfo);
        }
    };

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            footPresenter.processKey(event.getKeyCode(), event);
        }
        return super.dispatchKeyEvent(event);
    }
}
