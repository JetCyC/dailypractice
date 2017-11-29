package xianglin.com.retrofit.rxjava;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import xianglin.com.retrofit.R;

public class FlowableActivity extends AppCompatActivity {

    private Subscription mSubscription;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowable);
        initView();
        init();
    }

    private void initView() {
        button1 = (Button) findViewById(R.id.bt_start);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSubscription.request(1);
            }
        });
    }

    private void init() {
         Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                Log.e("TAG", "emit 1");
                emitter.onNext(1);
                Log.e("TAG", "emit 2");
                emitter.onNext(2);
                Log.e("TAG", "emit 3");
                emitter.onNext(3);
                Log.e("TAG", "emit onComplete");
                emitter.onComplete();

            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.e("TAG", "onSubscribe");
                        mSubscription = s;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e("TAG", "onNext");
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e("TAG", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TAG", "onComplete");
                    }
                });
    }
}
