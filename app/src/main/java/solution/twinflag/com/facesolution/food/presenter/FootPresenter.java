package solution.twinflag.com.facesolution.food.presenter;

import android.view.KeyEvent;

import solution.twinflag.com.facesolution.base.BasePresenter;
import solution.twinflag.com.facesolution.domain.PersonInfo;

public interface FootPresenter extends BasePresenter{

    void initView();

    // 付款成功
    void paySuccess(String name, String consumeMoney, String leftMoney);

    // 付款失败
    void payFail(String name, String consumeMoney, String leftMoney);

    // 确认消费金额
    void confirmConsumeMoney();

    // 修改消费金额
    void editConsumeMoney();

    void payMoney(PersonInfo personInfo);

    /**
     * 处理输入的消费金额
     *
     * @param keyCode
     * @param event
     */
    void processKey(int keyCode, KeyEvent event);
}
