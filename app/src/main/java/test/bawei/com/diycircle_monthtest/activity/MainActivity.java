package test.bawei.com.diycircle_monthtest.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import test.bawei.com.diycircle_monthtest.R;
import test.bawei.com.diycircle_monthtest.view.MyDiyCircle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDiyCircle my_diy = (MyDiyCircle) findViewById(R.id.my_diy);

        my_diy.setCenterText("圆心");
        my_diy.setInCircleRadius(80);
        my_diy.setOutCircleRadius(100);
        my_diy.setRingColor(Color.YELLOW);
        my_diy.setTextSize(40);
        my_diy.setRingWidth(60);
    }
}
