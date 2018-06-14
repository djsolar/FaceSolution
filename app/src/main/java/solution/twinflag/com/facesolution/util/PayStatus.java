package solution.twinflag.com.facesolution.util;

public enum PayStatus {

    ENTER_PRICE(0), LOCK_PRICE(1), PAYING(2);

    private int code;
    PayStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
