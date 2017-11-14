package xianglin.com.retrofit;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import xianglin.com.retrofit.bean.Student;
import xianglin.com.retrofit.bean.User;

/**
 * []
 *
 * @author ex-caoyanchang
 * @version v 3.0.3 2017/10/18 16:16
 * @email ex-caoyanchang@xianglin.cn
 */

public interface GitHubService {


    @GET("users/{user}/repos")
    Call<List<Student>> listRepos(@Path("user") String user);

    @POST("mobile/login")
    Call<ResponseBody> login(@Body User user);


}
