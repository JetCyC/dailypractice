package xianglin.com.retrofit.biz.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import xianglin.com.retrofit.R;

/**
 * Description:自定义view练习
 * Author: ex-caoyanchang
 * Create: 2018/3/22 10:53
 */

public class ViewOne extends View {

    private int defaultSize;

    public ViewOne(Context context) {
        super(context);
    }

    public ViewOne(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ViewOne);
        //
        defaultSize = array.getDimensionPixelSize(R.styleable.ViewOne_default_size, 100);

        array.recycle();
    }

    public ViewOne(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ViewOne(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(100, widthMeasureSpec);

        int height = getMySize(100, heightMeasureSpec);

        if (width < height) {
            height = width;
        } else {
            width = height;
        }
        setMeasuredDimension(width, height);
    }

    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
                mySize = defaultSize;
                break;
            case MeasureSpec.AT_MOST:
                mySize = size;
                break;
            case MeasureSpec.EXACTLY:
                mySize = size;
                break;
        }
        return mySize;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //半径
        int r = getMeasuredWidth() / 2;
        //坐标
        int circleX = getLeft() + r;
        int circleY = getTop() + r;
        //画笔
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);

        canvas.drawCircle(circleX, circleY, r, paint);
    }
}
