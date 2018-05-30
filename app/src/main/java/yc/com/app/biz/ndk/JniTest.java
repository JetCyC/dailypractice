package yc.com.app.biz.ndk;

/**
 * description： describe
 * anthor： caoyanchang
 * time： 2018/5/30 下午6:21
 */
public class JniTest {
    /**
     * 将用C++代码实现，在android代码中调用的方法：获取一段文字
     */
    public static native String getStrFromC();

    /**
     * 配置加载的so库的文件名字===>如 ：libmyLib.so
     */
    static {
        System.loadLibrary("myLib");
    }


}
