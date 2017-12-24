package xianglin.com.retrofit.downloadservice;

/**
 * 下载回调
 * Created by ex-caoyanchang on 2017/12/24.
 */

public interface DownloadListener {

    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();

}
