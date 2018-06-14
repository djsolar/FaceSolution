package solution.twinflag.com.facesolution.net;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import solution.twinflag.com.facesolution.domain.WeatherInfo;
import solution.twinflag.com.facesolution.util.OnWeatherSuccessListener;
import solution.twinflag.com.facesolution.util.ParserUtils;

public class RetrofitManage {

    Retrofit retrofit;

    private RetrofitManage() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://wthrcdn.etouch.cn")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }

    public static RetrofitManage getInstance() {
        return RetrofitManager.retrofitManager;
    }

    private static class RetrofitManager {
        private static final RetrofitManage retrofitManager = new RetrofitManage();
    }

    public void getWeatherInfo(String cityKey, final OnWeatherSuccessListener listener) {
        WeatherService weatherService = retrofit.create(WeatherService.class);
        Call<ResponseBody> call = weatherService.getWeatherData(cityKey);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    WeatherInfo weatherInfo = ParserUtils.parserWeather(response.body().string());
                    if (listener != null) {
                        listener.onSuccess(weatherInfo);
                    }
                    System.out.println(weatherInfo.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.onSuccess(null);
            }
        });
    }
}
