package xianglin.com.retrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xianglin.com.retrofit.bean.Student;
import xianglin.com.retrofit.bean.User;
import xianglin.com.retrofit.rxjava.FirstActivity;


public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get();
        // post();
        initView();
    }

    private void initView() {
        button = (Button) findViewById(R.id.bt_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(intent);

            }
        });
    }

    private void post() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.test.com/")
                .build();
        GitHubService gitHubService = retrofit.create(GitHubService.class);
        Call<ResponseBody> call = gitHubService.login(new User("18611990521", "abc123456"));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response == null) return;
                if (response.isSuccessful()) {
                    Log.e("TAG", response.body().toString());
                } else {
                    Log.e("TAG", "失败");
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void get() {
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
