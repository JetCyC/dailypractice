package yc.com.app.biz.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import yc.com.app.R;

/**
 * progress circle
 *
 * @Description: 例子里只有onDraw https://blog.csdn.net/mengks1987/article/details/77771465
 * @Author: caoyanchang
 * @Date: 2019-12-17 14:37
 */
public class RoundView extends View {

    //圆环宽度，默认半径的1/5
    private float mRingWidth = 0;
    //圆环颜色   默认 #CBCBCB
    private int mRingColor = 0;
    //圆环半径  默认：Math.min(getHeight()/2,getWidth()/2)
    private float mRadius = 0;
    //底环画笔
    private Paint mRingPaint;


    //进度条圆环宽度
    private float mProgressRingWidth = 0;
    //进度条圆环开始颜色，进度条圆环是渐变的,默认
    private int mProgressRingStartColor = 0;
    //进度条圆环结束颜色，进度条圆环是渐变的,默认
    private int mProgressRingEndColor = 0;
    //进度条圆环Paint
    private Paint mProgressRingPaint;
    //进度条圆环渐变shader
    private Shader mProgressRingShader;
    private int[] arcColors;//渐变数组


    public RoundView(Context context) {
        this(context, null);
    }

    public RoundView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //去属性的值
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundView);
        mRingWidth = typedArray.getDimension(R.styleable.RoundView_ring_width, 0);
        mRingColor = typedArray.getColor(R.styleable.RoundView_ring_color, Color.parseColor("#CBCBCB"));
        mRadius = typedArray.getDimension(R.styleable.RoundView_radius, 0);
        mProgressRingWidth = typedArray.getDimension(R.styleable.RoundView_progress_ring_width, 0);
        mProgressRingStartColor = typedArray.getColor(R.styleable.RoundView_progress_ring_start_color, Color.parseColor("#f90aa9"));
        mProgressRingEndColor = typedArray.getColor(R.styleable.RoundView_progress_ring_end_color, Color.parseColor("#931c80"));

        init();
    }


    private void init() {
        mRingPaint = new Paint();
        mRingPaint.setAntiAlias(true);//抗锯齿效果
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setColor(mRingColor);

        arcColors = new int[]{mProgressRingStartColor, mProgressRingEndColor};
        mProgressRingPaint = new Paint();
        mProgressRingPaint.setAntiAlias(true);
        mProgressRingPaint.setStyle(Paint.Style.STROKE);
        mProgressRingPaint.setStrokeCap(Paint.Cap.ROUND);//圆形笔头
        mProgressRingPaint.setStrokeWidth(mProgressRingWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float x = getWidth() / 2;
        float y = getHeight() / 2;
        //如果未设置半径，则半径的值为view的宽、高一半的较小值
        mRadius = mRadius == 0 ? Math.min(getWidth() / 2, getHeight() / 2) : mRadius;
        //圆环的宽度默认为半径的1／5
        mRingWidth = mRingWidth == 0 ? mRadius / 5 : mRingWidth;
        //由于圆环本身有宽度，所以半径要减去圆环宽度的一半，不然一部分圆会在view外面
        mRadius = mRadius - mRingWidth / 2;
        mRingPaint.setStrokeWidth(mRingWidth);
        //底环
        canvas.drawCircle(x, y, mRadius, mRingPaint);

        if (mProgressRingShader == null) {
            mProgressRingShader = new SweepGradient(x, y, arcColors, null);
            mProgressRingPaint.setShader(mProgressRingShader);
        }

        //计算进度圆环宽度,默认和底部圆滑一样大
        float progressRingWidth = mProgressRingWidth == 0 ? mRingWidth : mProgressRingWidth;
        mProgressRingPaint.setStrokeWidth(progressRingWidth);


    }


}
