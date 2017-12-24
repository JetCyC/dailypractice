package xianglin.com.retrofit.downloadservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;

import java.io.File;

import xianglin.com.retrofit.MainActivity;
import xianglin.com.retrofit.R;
import xianglin.com.retrofit.utils.ToastUtils;

public class DownloadService extends Service {

    private DownloadTask downloadTask;
    private String downlaodUrl;


    public DownloadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return mBinder;
    }

    private DownloadBinder mBinder = new DownloadBinder();

     class DownloadBinder extends Binder {

        public void startDownload(String url) {
            if (downloadTask == null) {
                downlaodUrl = url;
                downloadTask = new DownloadTask(listener);
                downloadTask.execute(downlaodUrl);
                startForeground(1, getNotifycation("Downloading...", 0));
                ToastUtils.showMsg("下载中");
            }
        }

        public void pauseDownload() {
            if (downloadTask != null) {
                downloadTask.pauseDownload();
            }
        }

        public void cancelDownload() {
            if (downloadTask != null) {
                downloadTask.cancleDownload();
            } else {//删除文件，清除通知栏
                if (downlaodUrl != null) {
                    String fileName = downlaodUrl.substring(downlaodUrl.lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                            .getPath();
                    File file = new File(directory + fileName);
                    if (file.exists()) {
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                    ToastUtils.showMsg("取消");
                }
            }


        }


    }

    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotifycation("Download...", progress));
        }

        @Override
        public void onSuccess() {
            downloadTask = null;
            stopForeground(true);
            ToastUtils.showMsg("下载成功");
        }

        @Override
        public void onFailed() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1, getNotifycation("Download...", -1));
            ToastUtils.showMsg("下载失败");
        }

        @Override
        public void onPaused() {
            downloadTask = null;
            ToastUtils.showMsg("停止下载");
        }

        @Override
        public void onCanceled() {
            downloadTask = null;
            stopForeground(true);
            ToastUtils.showMsg("取消下载");
        }
    };

    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private Notification getNotifycation(String title, int progress) {
        Intent intent = new Intent(this, DownloadActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(pi);
        builder.setContentTitle(title);
        if (progress > 0) {
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }
        return builder.build();
    }

}
