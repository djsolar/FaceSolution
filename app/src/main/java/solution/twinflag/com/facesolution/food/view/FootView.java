package solution.twinflag.com.facesolution.food.view;

import solution.twinflag.com.facesolution.base.BaseView;

public interface FootView extends BaseView {

    // 显示输入金额金额界面
    void showEnterPriceView();

    void editPrice(String moneyStr);

    // 确认开始付款
    void confirmPayMoneyView(String money);

    void hideConfirmPayMoneyView();

    // 隐藏输入金额界面
    void hideEnterPriceView();

    // 显示付款成功界面
    void showPaySuccessView(String name, String consumeMoney, String leftMoney, int tipId);

    void showPayFailedView(String name, String consumeMoney, String leftMoney, int tipId);

    // 隐藏付款成功界面
    void hidePaySuccessView();

    String getConsumeMoney();
}
