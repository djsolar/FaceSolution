package solution.twinflag.com.facesolution.food.presenter;

import android.text.TextUtils;
import android.view.KeyEvent;

import java.text.DecimalFormat;

import solution.twinflag.com.facesolution.R;
import solution.twinflag.com.facesolution.domain.PersonInfo;
import solution.twinflag.com.facesolution.domain.WeatherInfo;
import solution.twinflag.com.facesolution.food.view.FootView;
import solution.twinflag.com.facesolution.net.RetrofitManage;
import solution.twinflag.com.facesolution.util.OnWeatherSuccessListener;
import solution.twinflag.com.facesolution.util.PayStatus;

public class FootPresenterImpl implements FootPresenter {

    private PayStatus payStatus = PayStatus.ENTER_PRICE;

    private FootView footView;

    private String consumeMoney;

    public FootPresenterImpl(FootView footView) {
        this.footView = footView;
    }


    @Override
    public void initView() {
        footView.hidePaySuccessView();
        footView.showEnterPriceView();
        footView.hideConfirmPayMoneyView();
        payStatus = PayStatus.ENTER_PRICE;
        RetrofitManage.getInstance().getWeatherInfo("101010100", new OnWeatherSuccessListener() {
            @Override
            public void onSuccess(WeatherInfo weatherInfo) {
                if (weatherInfo != null) {
                    footView.showWeather(weatherInfo);
                }
            }
        });
    }

    @Override
    public void paySuccess(String name, String consumeMoney, String leftMoney) {
        payStatus = PayStatus.PAYING;
        footView.hideEnterPriceView();
        footView.showPaySuccessView(name, consumeMoney, leftMoney, R.string.pay_success);
    }

    @Override
    public void payFail(String name, String consumeMoney, String leftMoney) {
        payStatus = PayStatus.PAYING;
        footView.hideEnterPriceView();
        footView.showPayFailedView(name, consumeMoney, leftMoney, R.string.pay_failed);
    }

    @Override
    public void confirmConsumeMoney() {

    }

    @Override
    public void editConsumeMoney() {

    }

    private float totalMoney = 200.0f;


    @Override
    public void payMoney(PersonInfo personInfo) {
        if (payStatus == PayStatus.LOCK_PRICE) {
            float payMoney = Float.parseFloat(consumeMoney);
            if (totalMoney >= payMoney) {
                totalMoney -= payMoney;
                paySuccess(personInfo.getName(), String.valueOf(payMoney), String.valueOf(totalMoney));
            } else {
                payFail(personInfo.getName(), String.valueOf(payMoney), String.valueOf(totalMoney));
            }
        }
    }

    @Override
    public void processKey(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_NUMPAD_0 <= keyCode && keyCode <= KeyEvent.KEYCODE_NUMPAD_9 ||
                keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER || keyCode == KeyEvent.KEYCODE_NUMPAD_DOT ||
                keyCode == KeyEvent.KEYCODE_DEL) {
            if (payStatus.equals(PayStatus.ENTER_PRICE)) {

                if (keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER) {
                    String moneyStr = footView.getConsumeMoney();
                    if (!TextUtils.isEmpty(moneyStr) && Float.parseFloat(moneyStr) >= 0.0f) {
                        payStatus = PayStatus.LOCK_PRICE;
                        String nowMoney = footView.getConsumeMoney();
                        this.consumeMoney = nowMoney;
                        DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
                        String formatMoney = decimalFormat.format(Float.parseFloat(nowMoney));
                        this.consumeMoney = formatMoney;
                        // 确认付款
                        footView.confirmPayMoneyView(formatMoney);
                    }
                } else if (keyCode == KeyEvent.KEYCODE_DEL) {
                    String moneyStr = footView.getConsumeMoney();
                    if (TextUtils.isEmpty(moneyStr)) {
                        return;
                    }
                    String delStr = moneyStr.substring(0, moneyStr.length() - 1);
                    footView.editPrice(delStr);
                } else {
                    String moneyStr = footView.getConsumeMoney();
                    try {
                        String newMoneyStr = moneyStr + getKeyCodeValue(keyCode);


                        // 小数点后面最多保留两位
                        if (newMoneyStr.contains(".") && newMoneyStr.substring(newMoneyStr.indexOf('.')).length() > 3) {
                            return;
                        }

                        // 整数对齐
                        if (!newMoneyStr.contains(".")) {
                            int money = Integer.parseInt(newMoneyStr);
                            if (money > 1000) {
                                return;
                            }
                            newMoneyStr = String.valueOf(money);
                        }

                        float money = Float.parseFloat(newMoneyStr);
                        if (money > 1000.0) {
                            return;
                        }
                        footView.editPrice(newMoneyStr);
                    } catch (NumberFormatException e) {

                    }
                }

            } else if (payStatus.equals(PayStatus.LOCK_PRICE)) {
                if (keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER) {
                    payStatus = PayStatus.ENTER_PRICE;
                    footView.editPrice(consumeMoney);
                    footView.hideConfirmPayMoneyView();
                    // 关闭付款
                }
            } else if (payStatus.equals(PayStatus.PAYING)) {
                if (keyCode == KeyEvent.KEYCODE_NUMPAD_ENTER) {
                    // 开始下一个付款
                    payStatus = PayStatus.ENTER_PRICE;
                    footView.editPrice("");
                    footView.hidePaySuccessView();
                    footView.showEnterPriceView();
                }
            }

        }
    }

    private String getKeyCodeValue(int keyCode) {
        String value = null;
        switch (keyCode) {
            case KeyEvent.KEYCODE_NUMPAD_0:
                value = "0";
                break;
            case KeyEvent.KEYCODE_NUMPAD_1:
                value = "1";
                break;
            case KeyEvent.KEYCODE_NUMPAD_2:
                value = "2";
                break;
            case KeyEvent.KEYCODE_NUMPAD_3:
                value = "3";
                break;
            case KeyEvent.KEYCODE_NUMPAD_4:
                value = "4";
                break;
            case KeyEvent.KEYCODE_NUMPAD_5:
                value = "5";
                break;
            case KeyEvent.KEYCODE_NUMPAD_6:
                value = "6";
                break;
            case KeyEvent.KEYCODE_NUMPAD_7:
                value = "7";
                break;
            case KeyEvent.KEYCODE_NUMPAD_8:
                value = "8";
                break;
            case KeyEvent.KEYCODE_NUMPAD_9:
                value = "9";
                break;
            case KeyEvent.KEYCODE_NUMPAD_DOT:
                value = ".";
                break;
        }
        return value;
    }

    @Override
    public void getWeather(String url) {

    }
}
