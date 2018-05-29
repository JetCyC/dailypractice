package yc.com.app.http;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import yc.com.app.bean.LoginRequest;
import yc.com.app.bean.LoginResponse;
import yc.com.app.bean.RegisterRequest;
import yc.com.app.bean.RegisterResponse;
import yc.com.app.bean.Student;
import yc.com.app.bean.User;

/**
 * []
 *
 * @author ex-caoyanchang
 * @version v 3.0.3 2017/10/18 16:16
 * @email ex-caoyanchang@xianglin.cn
 */

public interface ApiService {


    @GET("users/{user}/repos")
    Call<List<Student>> listRepos(@Path("user") String user);

    @POST("mobile/login")
    Observable<ResponseBody> login(@Body User user);

    @GET
    Observable<LoginResponse>  lologin(@Body LoginRequest loginRequest);

    @GET
    Observable<RegisterResponse> register(@Body RegisterRequest registerRequest);


}
