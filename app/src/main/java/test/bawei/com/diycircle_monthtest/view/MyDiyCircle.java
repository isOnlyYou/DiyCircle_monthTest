package test.bawei.com.diycircle_monthtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by acer on 2017/4/29.
 */
public class MyDiyCircle extends View {

    private Context context;

    private Paint paint;

    //外圆半径
    private float outCircleRadius;

    //内圆半径
    private float inCircleRadius;

    //圆环颜色
    private int ringColor;

    //中心显示文本
    private String centerText;

    //字体大小
    private int textSize;

    //圆环宽度
    private float ringWidth;
    private int centerX;
    private int centerY;
    private float x;
    private float y;

    public void setRingWidth(float ringWidth) {
        this.ringWidth = ringWidth;
    }

    public void setOutCircleRadius(float outCircleRadius) {
        this.outCircleRadius = outCircleRadius;
    }

    public void setInCircleRadius(float inCircleRadius) {
        this.inCircleRadius = inCircleRadius;
    }

    public void setRingColor(int ringColor) {
        this.ringColor = ringColor;
    }

    public void setCenterText(String centerText) {
        this.centerText = centerText;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public MyDiyCircle(Context context) {
        super(context);
        this.context = context;
    }

    public MyDiyCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        //画笔对象
        paint = new Paint();
    }

    public MyDiyCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //圆心坐标
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;

        //设置外层矩形的颜色
        paint.setColor(Color.GREEN);
        //设置矩形为实心
        paint.setStyle(Paint.Style.FILL);
        //矩形的参数
        Rect rect = new Rect();
        rect.left = (int) (centerX - outCircleRadius - ringWidth / 2);
        rect.right = (int) (centerX + outCircleRadius + ringWidth / 2);
        rect.top = (int) (centerY - outCircleRadius - ringWidth / 2);
        rect.bottom = (int) (centerY + outCircleRadius + ringWidth / 2);
        //画矩形
        canvas.drawRect(rect, paint);


        //设置抗锯齿
        paint.setAntiAlias(true);
        //设置外层圆为空心
        paint.setStyle(Paint.Style.STROKE);
        //设置画笔颜色
        paint.setColor(ringColor);
        //设置圆环宽度
        paint.setStrokeWidth(ringWidth);
        //画圆
        canvas.drawCircle(centerX, centerY, outCircleRadius, paint);


        //设置内层的圆为实心
        paint.setStyle(Paint.Style.FILL);
        //设置内圆颜色
        paint.setColor(Color.WHITE);
        //画圆
        canvas.drawCircle(centerX, centerY, inCircleRadius, paint);


        //设置字体颜色
        paint.setColor(Color.BLACK);
        //设置字体大小
        paint.setTextSize(textSize);
        //设置画笔宽度
        paint.setStrokeWidth(0);
        //测量字体宽度
        float textWidth = paint.measureText(centerText);
        //写字体
        canvas.drawText(centerText, centerX - textWidth / 2, centerY + textWidth / 5, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                x = event.getX();
                y = event.getY();

                break;
            case MotionEvent.ACTION_UP:
                if(getClickSlant() < getInRadiusSquare()){
                    Toast.makeText(context, "在小圆内", Toast.LENGTH_SHORT).show();
                }

                if (getClickSlant() > getInRadiusSquare() && getClickSlant() < getRingSquare()){
                    Toast.makeText(context, "在圆环内", Toast.LENGTH_SHORT).show();
                }

                if(getClickSlant() > getRingSquare()
                        && getClickSlant() < (getRingSquare() + getRingSquare())){
                    Toast.makeText(context, "在矩形内", Toast.LENGTH_SHORT).show();
                }

                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return true;
    }

    //得到实际的外圆半径的平方，
    // 而在Android中，若画圆有圆环宽度，则所谓半径为：除去圆环后的半径+圆环宽度的一半
    private float getRingSquare() {
        return (outCircleRadius+ringWidth/2)*(outCircleRadius+ringWidth/2);
    }

    //得到内圆的半径的平方
    private float getInRadiusSquare() {
        return inCircleRadius * inCircleRadius;
    }

    //得到点击处的斜边的平方
    private float getClickSlant() {
        return (centerX - x) * (centerX - x)+(centerY - y)*(centerY - y);
    }
}
