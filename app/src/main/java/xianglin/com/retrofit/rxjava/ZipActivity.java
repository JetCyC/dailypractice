package xianglin.com.retrofit.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import xianglin.com.retrofit.R;

public class ZipActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zip);
        init();
    }

    private void init() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.e("TAG", "1");
                e.onNext(1);
                Thread.sleep(1000);
                Log.e("TAG", "2");
                e.onNext(2);
                Thread.sleep(1000);
                Log.e("TAG", "3");
                e.onNext(3);
                Thread.sleep(1000);
                Log.e("TAG", "4");
                e.onNext(4);
                Thread.sleep(1000);
                Log.e("TAG", "integer+oncomplete");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());


        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.e("TAG", "A");
                e.onNext("A");
                Thread.sleep(1000);
                Log.e("TAG", "B");
                e.onNext("B");
                Thread.sleep(1000);
                Log.e("TAG", "C");
                e.onNext("C");
                Thread.sleep(1000);
                Log.e("TAG", "String+oncomplete");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {


            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.e("TAG", value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.e("TAG", "onComplete");
            }
        });

    }
}
