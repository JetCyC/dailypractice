package yc.com.app.biz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import yc.com.app.R;
import yc.com.app.base.BaseRnActivity;
import yc.com.app.bean.Student;
import yc.com.app.bean.User;
import yc.com.app.biz.downloadservice.DownloadActivity;
import yc.com.app.biz.myview.MyViewActivity;
import yc.com.app.http.ApiService;
import yc.com.app.biz.rxjava.FirstActivity;
import yc.com.app.biz.rxjava.FlowableActivity;
import yc.com.app.biz.rxjava.IntervalActivity;
import yc.com.app.biz.rxjava.MapActivity;
import yc.com.app.biz.rxjava.ZipActivity;
import yc.com.app.biz.sqlite.SqActivity;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.bt_sqlite)
    Button btSqlite;
    private Unbinder unbinder;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button button11;
    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        mCompositeDisposable = new CompositeDisposable();
        //get();

        initView();
    }


    private void initView() {
        button1 = (Button) findViewById(R.id.bt_next);
        button2 = (Button) findViewById(R.id.bt_login);
        button3 = (Button) findViewById(R.id.bt_map);
        button4 = (Button) findViewById(R.id.bt_zip);
        button5 = (Button) findViewById(R.id.bt_flowable);
        button6 = (Button) findViewById(R.id.bt_interval);
        button7 = (Button) findViewById(R.id.bt_download);
        button8 = (Button) findViewById(R.id.bt_myview);
        button9 = (Button) findViewById(R.id.bt_rn);
        button10 = (Button) findViewById(R.id.bt_ndk);
        button11 = (Button) findViewById(R.id.bt_view_sun);

        button11.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });



        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, NdkActivity.class);
//                startActivity(intent);
            }
        });


        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BaseRnActivity.class);
                startActivity(intent);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyViewActivity.class);
                startActivity(intent);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DownloadActivity.class);
                startActivity(intent);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, IntervalActivity.class);
                startActivity(intent);

            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FlowableActivity.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ZipActivity.class);
                startActivity(intent);
            }
        });
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

        ApiService apiService = retrofit.create(ApiService.class);
        apiService.login(new User("18611990521", "abc123456"))
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

        ApiService apiService = retrofit.create(ApiService.class);

        Call<List<Student>> call = apiService.listRepos("octocat");

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
        if (unbinder == null) {
            unbinder.unbind();
        }
    }

    @OnClick(R.id.bt_sqlite)
    public void onViewClicked() {
        startActivity(new Intent(this, SqActivity.class));
    }
}
