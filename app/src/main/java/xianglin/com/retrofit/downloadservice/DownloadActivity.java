package xianglin.com.retrofit.downloadservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import xianglin.com.retrofit.R;

public class DownloadActivity extends AppCompatActivity implements View.OnClickListener {


    private DownloadService.DownloadBinder downloadBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        initView();
        initService();
    }

    private void initService() {
        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    private void initView() {
        Button startDownLoad = (Button) findViewById(R.id.bt_start);
        startDownLoad.setOnClickListener(this);
        Button pauseDownLoad = (Button) findViewById(R.id.bt_pause);
        pauseDownLoad.setOnClickListener(this);
        Button cancelDownLoad = (Button) findViewById(R.id.bt_cancel);
        cancelDownLoad.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;//
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


}
