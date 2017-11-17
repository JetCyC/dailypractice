
package xianglin.com.retrofit.rxjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import xianglin.com.retrofit.R;

public class FirstActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        initRxjava();

    }



    private void initRxjava() {
        //上游  下游  订阅
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        });
        //下游
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("TAG", "subscribe");
            }

            @Override
            public void onNext(Object value) {
                Log.e("TAG", value.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "error");
            }

            @Override
            public void onComplete() {
                Log.e("TAG", "complete");
            }
        };

        observable.subscribe();

    }
}
