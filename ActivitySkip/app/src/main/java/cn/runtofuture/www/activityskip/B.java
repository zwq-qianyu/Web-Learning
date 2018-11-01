package cn.runtofuture.www.activityskip;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class B extends AppCompatActivity {
    private TextView mLifecycleTextB;
    private TextView mStatusTextB;
    int offset = A.getOffset();

    private static final int REQUEST_CODE_A = 1;
    private static final int REQUEST_CODE_B = 2;
    private static final int REQUEST_CODE_C = 3;

    protected static final String GET_LIFE_CYCLE = "cn.runtofuture.www.activityskip.lifecycle_info";
    protected static final String GET_STATUS = "cn.runtofuture.www.activityskip.status_info";
    protected static final String SEND_LIFE_CYCLE = "cn.runtofuture.www.activityskip.lifecycle_info";
    protected static final String SEND_STATUS = "cn.runtofuture.www.activityskip.status_info";
    protected static final String SEND_LIFE_CYCLE_ADD = "cn.runtofuture.www.activityskip.lifecycle_info_add";
    protected static final String SEND_STATUS_ADD = "cn.runtofuture.www.activityskip.status_info_add";
    protected static final String GET_LIFE_CYCLE_ADD = "cn.runtofuture.www.activityskip.lifecycle_info_add";
    protected static final String GET_STATUS_ADD = "cn.runtofuture.www.activityskip.status_info_add";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("TEST", "B - >>> onCreate");

        setContentView(R.layout.activity_b);
        mLifecycleTextB = (TextView)findViewById(R.id.lifestyle_text_b);
        mLifecycleTextB.setMovementMethod(ScrollingMovementMethod.getInstance());
        mStatusTextB = (TextView)findViewById(R.id.status_text_b);
        mStatusTextB.setMovementMethod(ScrollingMovementMethod.getInstance());

        // 将intent中存放的记录先放入显示框中
        Intent m = getIntent();
        String get  = m.getStringExtra(GET_LIFE_CYCLE);
        if(get != null){
            mLifecycleTextB.append(m.getStringExtra(GET_LIFE_CYCLE));
            mStatusTextB.append(m.getStringExtra(GET_STATUS));
        }

        mLifecycleTextB.append("Activity B.onCreate()\n");

    }

    @Override
    protected void onStart() {
        super.onStart();
        mLifecycleTextB = (TextView)findViewById(R.id.lifestyle_text_b);
        mStatusTextB = (TextView)findViewById(R.id.status_text_b);
        mLifecycleTextB.append("Activity B.onStart()\n");
        Log.d("TEST", "B - >>> onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLifecycleTextB = (TextView)findViewById(R.id.lifestyle_text_b);
        mStatusTextB = (TextView)findViewById(R.id.status_text_b);
        mLifecycleTextB.append("Activity B.onResume()\n");
        try{
            mLifecycleTextB.append(getIntent().getStringExtra(GET_LIFE_CYCLE_ADD));
        }catch(Exception e){
            System.out.println(e);
        }

        try{
            mStatusTextB.setText(getIntent().getStringExtra(GET_STATUS_ADD));
        }catch(Exception e){
            System.out.println(e);
        }
        mStatusTextB.append("Activity B: Resumed\n");
        mLifecycleTextB.scrollTo(0,offset-mLifecycleTextB.getHeight());

        Log.d("TEST", "B - >>> onResume");

        // 设置结果值
        Intent data = new Intent();
        data.putExtra(SEND_LIFE_CYCLE, mLifecycleTextB.getText().toString() + "Activity B.onPause()\n");
        data.putExtra(SEND_STATUS,mStatusTextB.getText().toString());
        data.putExtra(SEND_LIFE_CYCLE_ADD, "Activity B.onStop()\nActivity B.onDestroy()\n");
        setResult(RESULT_OK, data);

        // 点击跳转至 A
        findViewById(R.id.start_a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "A ------ >>> onClick");
                Intent intent = new Intent(B.this, A.class);
                intent.putExtra(SEND_LIFE_CYCLE, mLifecycleTextB.getText().toString() + "Activity B.onPause()\n");
                intent.putExtra(SEND_LIFE_CYCLE_ADD, "Activity B.onStop()\n");
                intent.putExtra(SEND_STATUS,mStatusTextB.getText().toString());
                intent.putExtra(SEND_STATUS_ADD, "Activity B: Stoped\n");
//                startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE_A);
            }
        });

        // 点击跳转至 C
        findViewById(R.id.start_c).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "C ------ >>> onClick");
                Intent intent = new Intent(B.this, C.class);
                intent.putExtra(SEND_LIFE_CYCLE, mLifecycleTextB.getText().toString() + "Activity B.onPause()\n");
                intent.putExtra(SEND_LIFE_CYCLE_ADD, "Activity B.onStop()\n");
                intent.putExtra(SEND_STATUS,mStatusTextB.getText().toString());
                intent.putExtra(SEND_STATUS_ADD, "Activity B: Stoped\n");
//                startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE_C);
            }
        });

        // 结束B
        findViewById(R.id.finish_b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "B ------ >>> finish click");
                offset += 5 * mLifecycleTextB.getLineHeight();
                Log.d("TEST offset", String.valueOf(offset));
                Log.d("TEST finish", String.valueOf(mLifecycleTextB.getLineHeight()));
                finish();
            }
        });

        // dialog
        findViewById(R.id.dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(B.this, ActivityDialog.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        refreshLogView("Activity B.onPause()\n");
//        mLifecycleTextB.append("Activity B.onPause()\n");
        mStatusTextB.setText("Activity B: paused\n");
        Log.d("TEST", "B - >>> onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        refreshLogView("Activity B.onStop()\n");
//        offset -= 3 * mLifecycleTextB.getLineHeight();
//        Log.d("TEST STOP", String.valueOf(mLifecycleTextB.getLineHeight()));
//        mStatusTextB.append("B: Stoped\n");
        Log.d("TEST", "B - >>> onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        refreshLogView("Activity B.onDestroy()\n");
        Log.d("TEST", "B - >>> onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        refreshLogView("Activity B.onRestart()\n");
        Log.d("TEST", "B - >>> onRestart");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        mLifecycleTextB.setText(data.getStringExtra(GET_LIFE_CYCLE));
        mStatusTextB.setText(data.getStringExtra(GET_STATUS));
    }

    void refreshLogView(String msg){
        mLifecycleTextB.append(msg);
        offset=(mLifecycleTextB.getLineCount() - 3) * mLifecycleTextB.getLineHeight();
        A.setOffset(offset);
        Log.d("TEST offset", String.valueOf(offset));
        Log.d("TEST getHeight", String.valueOf(mLifecycleTextB.getLineHeight()));
        if(offset>mLifecycleTextB.getHeight()){
            mLifecycleTextB.scrollTo(0,offset-mLifecycleTextB.getHeight());
        }
    }
}
