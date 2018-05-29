package yc.com.app.biz.downloadservice;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import yc.com.app.R;
import yc.com.app.utils.ToastUtils;

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
        //权限
        if (ContextCompat.checkSelfPermission(DownloadActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(DownloadActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
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
        if (downloadBinder == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.bt_start:
                String url = "http://shouji.baidu.com/software/23018246.html";
                downloadBinder.startDownload(url);
                break;
            case R.id.bt_pause:
                downloadBinder.pauseDownload();
                break;
            case R.id.bt_cancel:
                downloadBinder.cancelDownload();
                break;
        }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    ToastUtils.showMsg("拒绝权限将无法使用");
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
