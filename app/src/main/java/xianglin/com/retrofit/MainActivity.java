package xianglin.com.retrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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
import xianglin.com.retrofit.rxjava.MapActivity;


public class MainActivity extends AppCompatActivity {
    private Button button1;
    private Button button2;
    private Button button3;
    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCompositeDisposable = new CompositeDisposable();
        //get();

        initView();
    }


    private void initView() {
        button1 = (Button) findViewById(R.id.bt_next);
        button2 = (Button) findViewById(R.id.bt_login);
        button3 = (Button) findViewById(R.id.bt_map);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);


            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(intent);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post();
            }
        });
    }

    private void post() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://www.test.com/")
                .build();

        GitHubService gitHubService = retrofit.create(GitHubService.class);
        gitHubService.login(new User("18611990521", "abc123456"))
                .subscribeOn(Schedulers.io())//上游在io线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())//下游主线程处理结果
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(ResponseBody value) {
                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "登录错误", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}
