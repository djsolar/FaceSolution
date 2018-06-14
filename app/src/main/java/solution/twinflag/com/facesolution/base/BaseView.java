package solution.twinflag.com.facesolution.base;

import solution.twinflag.com.facesolution.domain.WeatherInfo;

public interface BaseView {

    void showWeather(WeatherInfo weatherInfo);

    void showNetInfo(String message);
}
