package yc.com.app.alive;

import android.annotation.SuppressLint;
import android.app.job.JobParameters;
import android.app.job.JobService;

/**
 * 双进程守护  方案二:JobScheduler执行任务调度保活
 * https://blog.csdn.net/pan861190079/article/details/72773549
 * description： describe
 * anthor： caoyanchang
 * time： 2018/5/14 上午8:42
 */
@SuppressLint("NewApi")
public class JobHandlerService extends JobService{
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }


}
