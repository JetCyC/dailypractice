package xianglin.com.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xianglin.com.retrofit.bean.Student;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create()) //使用工厂模式创建Gason的解析器
                .baseUrl("https://api.github.com/").build();

        GitHubService gitHubService = retrofit.create(GitHubService.class);

        Call<List<Student>> call = gitHubService.listRepos("octocat");

        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if (response == null) return;
                if (response.isSuccessful()) {
                    List<Student> list = response.body();
                    Log.e("TAG", list.toString());
                } else {


                }

            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.e("TAG", "fail");

            }
        });


    }
}
