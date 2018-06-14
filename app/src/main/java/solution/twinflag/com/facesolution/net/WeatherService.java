package solution.twinflag.com.facesolution.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("/WeatherApi?")
    Call<ResponseBody> getWeatherData(@Query(value = "citykey") String citykey);
}
