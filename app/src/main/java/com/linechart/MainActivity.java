package com.linechart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements InterfaceTouch {

    String[] ages = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    //String[]ages = new String[]{"0","1","2","3"};
    List<String> ageList;

    //x轴坐标对应的数据
    private List<String> xValue = new ArrayList<>();
    //y轴坐标对应的数据
    private List<Integer> yValue = new ArrayList<>();
    //折线对应的数据
    private Map<String, Integer> value = new HashMap<>();


    //x轴坐标对应的数据
    private List<String> xValue1 = new ArrayList<>();
    //y轴坐标对应的数据
    private List<Integer> yValue1 = new ArrayList<>();
    //折线对应的数据
    private Map<String, Integer> value1 = new HashMap<>();

    //x轴坐标对应的数据
    private List<String> xValue2 = new ArrayList<>();
    //y轴坐标对应的数据
    private List<Integer> yValue2 = new ArrayList<>();
    //折线对应的数据
    private Map<String, Integer> value2 = new HashMap<>();


    private ChartView chartview;
    private ChartView1 chartview1;
    private ChartView2 chartview2;
    private AgeAdapter ageAdapter;
    private AutoLocateHorizontalView mContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 12; i++) {
            xValue.add((i + 1) + "月");
            value.put((i + 1) + "月", (int) (Math.random() * 181 + 60));//60--240

            xValue1.add((i + 1) + "月");
            value1.put((i + 1) + "月", (int) (Math.random() * 101 + 80));//60--240

            xValue2.add((i + 1) + "月");
            value2.put((i + 1) + "月", (int) (Math.random() * 101 + 80));//60--240
        }

        for (int i = 0; i < 6; i++) {
            yValue.add(i * 60);
            yValue1.add(i * 50);
            yValue2.add(i * 70);
        }

        chartview = (ChartView) findViewById(R.id.chartview);
        chartview1 = (ChartView1) findViewById(R.id.chartview1);
        chartview2 = (ChartView2) findViewById(R.id.chartview2);

        mContent = (AutoLocateHorizontalView) findViewById(R.id.recyleview);

        chartview.setValue(value, xValue, yValue);
        chartview1.setValue(value1, xValue1, yValue1);
        chartview2.setValue(value2, xValue2, yValue2);

        chartview.setOnTouch(this);
        chartview1.setOnTouch(this);
        chartview2.setOnTouch(this);

        //
        ageList = new ArrayList<>();
        for (String age : ages) {
            ageList.add(age);
        }
        ageAdapter = new AgeAdapter(this, ageList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mContent.setLayoutManager(linearLayoutManager);
        mContent.setOnSelectedPositionChangedListener(new AutoLocateHorizontalView.OnSelectedPositionChangedListener() {
            @Override
            public void selectedPositionChanged(int pos) {
                Toast.makeText(MainActivity.this, "pos:" + pos, Toast.LENGTH_SHORT).show();
                chartview.setSelectIndex(pos);
                chartview1.setSelectIndex(pos);
                chartview2.setSelectIndex(pos);
            }
        });
        mContent.setInitPos(5);
        mContent.setAdapter(ageAdapter);

    }

    @Override
    public void action_down(Object view, float startX) {
        if (view instanceof ChartView) {
            chartview1.action_down(view, startX);
            chartview2.action_down(view, startX);
        } else if (view instanceof ChartView1) {
            chartview.action_down(view, startX);
            chartview2.action_down(view, startX);
        } else {
            chartview.action_down(view, startX);
            chartview1.action_down(view, startX);
        }

    }

    @Override
    public void action_move(Object view, float startX, float xInit) {
        if (view instanceof ChartView) {
            chartview1.action_move(view, startX, xInit);
            chartview2.action_move(view, startX, xInit);
        } else if (view instanceof ChartView1) {
            chartview.action_move(view, startX, xInit);
            chartview2.action_move(view, startX, xInit);
        } else {
            chartview.action_move(view, startX, xInit);
            chartview1.action_move(view, startX, xInit);
        }
    }

    @Override
    public void action_up(Object view, MotionEvent motionEvent) {
        if (view instanceof ChartView) {
            chartview1.action_up(view, motionEvent);
            chartview2.action_up(view, motionEvent);
        } else if (view instanceof ChartView1) {
            chartview.action_up(view, motionEvent);
            chartview2.action_up(view, motionEvent);
        } else {
            chartview.action_up(view, motionEvent);
            chartview1.action_up(view, motionEvent);
        }
    }

    @Override
    public void action_cancel(Object view) {
        if (view instanceof ChartView) {
            chartview1.action_cancel(view);
        } else {
            chartview.action_cancel(view);
        }

        if (view instanceof ChartView) {
            chartview1.action_cancel(view);
            chartview2.action_cancel(view);
        } else if (view instanceof ChartView1) {
            chartview.action_cancel(view);
            chartview2.action_cancel(view);
        } else {
            chartview.action_cancel(view);
            chartview1.action_cancel(view);
        }

    }
}

