package solution.twinflag.com.facesolution.domain;

public class WeatherInfo {

    // 当前温度
    private String currentTempature;

    // 最高温度
    private String high;

    // 最低温度
    private String low;

    // 当前天气
    private String type;

    public String getCurrentTempature() {
        return currentTempature;
    }

    public void setCurrentTempature(String currentTempature) {
        this.currentTempature = currentTempature;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "currentTempature='" + currentTempature + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
