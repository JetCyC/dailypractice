package yc.com.app.biz.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import yc.com.app.R;

/**
 * @Description: https://www.jianshu.com/p/e9de56679315
 * @Author: caoyanchang
 * @Date: 2019-09-29 17:25
 */
public class MyTouchBall extends View {

    private int mImgSampleSize = 10;//火箭缩放比例
    private int mDuration = 500;//火箭动画的时间
    private Bitmap mBitmap;
    private int mImgHeight;//缩放后，图片的实际高度
    private int mImgWidth;//缩放后，图片的实际宽度

    private Paint mPaint;//画笔


    public MyTouchBall(Context context) {
        super(context);
    }

    //在这里获取自定义属性，并赋值给成员变量
    public MyTouchBall(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTouchBall);
        int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.MyTouchBall_duration:
                    break;
                case R.styleable.MyTouchBall_imgSampleSize:
                    break;
                default:
                    break;
            }
        }
    }

    public MyTouchBall(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyTouchBall(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


}

