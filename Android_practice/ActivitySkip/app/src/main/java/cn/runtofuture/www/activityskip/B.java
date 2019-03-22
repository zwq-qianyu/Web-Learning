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
    private static TextView mLifecycleTextB;
    public TextView getLifecycleTextB() {
        return mLifecycleTextB;
    }

    public static void setLifecycleTextB(String str) {
        mLifecycleTextB.setText(str);
    }

    private TextView mStatusTextB;
    int offset = A.getOffset();

    private static final int REQUEST_CODE_A = 1;
    private static final int REQUEST_CODE_B = 2;
    private static final int REQUEST_CODE_C = 3;

    protected static final String GET_STATUS = "cn.runtofuture.www.activityskip.status_info";
    protected static final String SEND_STATUS = "cn.runtofuture.www.activityskip.status_info";
    protected static final String SEND_STATUS_ADD = "cn.runtofuture.www.activityskip.status_info_add";
    protected static final String GET_STATUS_ADD = "cn.runtofuture.www.activityskip.status_info_add";
    protected static final String LIFI_CYCLE = "cn.runtofuture.www.activityskip.life_cycle";

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
        String get  = m.getStringExtra(GET_STATUS);
        if(get != null){
            mStatusTextB.append(m.getStringExtra(GET_STATUS));
        }

        Data.appendLifeCycleData("Activity B.onCreate()\n");
//        mLifecycleTextB.append("Activity B.onCreate()\n");

    }

    @Override
    protected void onStart() {
        super.onStart();
        mLifecycleTextB = (TextView)findViewById(R.id.lifestyle_text_b);
        mStatusTextB = (TextView)findViewById(R.id.status_text_b);
        Data.appendLifeCycleData("Activity B.onStart()\n");
//        mLifecycleTextB.append("Activity B.onStart()\n");
        Log.d("TEST", "B - >>> onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLifecycleTextB = (TextView)findViewById(R.id.lifestyle_text_b);
        mStatusTextB = (TextView)findViewById(R.id.status_text_b);
        Data.appendLifeCycleData("Activity B.onResume()\n");
//        mLifecycleTextB.append("Activity B.onResume()\n");
        try{
            mLifecycleTextB.setText(Data.getLifeCycleData());
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
        setResult(RESULT_OK, data);

        // 点击跳转至 A
        findViewById(R.id.start_a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "A ------ >>> onClick");
                Intent intent = new Intent(B.this, A.class);
                intent.putExtra(SEND_STATUS,mStatusTextB.getText().toString());
                intent.putExtra(SEND_STATUS_ADD, "Activity B: Stoped\n");
                startActivityForResult(intent, REQUEST_CODE_A);
            }
        });

        // 点击跳转至 C
        findViewById(R.id.start_c).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "C ------ >>> onClick");
                Intent intent = new Intent(B.this, C.class);
                intent.putExtra(SEND_STATUS,mStatusTextB.getText().toString());
                intent.putExtra(SEND_STATUS_ADD, "Activity B: Stoped\n");
                startActivityForResult(intent, REQUEST_CODE_C);
            }
        });

        // 结束B
        findViewById(R.id.finish_b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "B ------ >>> finish click");
                offset += 5 * mLifecycleTextB.getLineHeight();
                finish();
            }
        });

        // dialog
        findViewById(R.id.dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(B.this, ActivityDialog.class);
                startActivityForResult(intent, 4);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        refreshLogView("Activity B.onPause()\n");
        mStatusTextB.setText("Activity B: paused\n");
        Log.d("TEST", "B - >>> onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        refreshLogView("Activity B.onStop()\n");
        try{
            A.setLifestyleTextA(Data.getLifeCycleData());
        }catch(Exception e){

        }
        try{
            B.setLifecycleTextB(Data.getLifeCycleData());
        }catch(Exception e){

        }
        try{
            C.setLifestyleTextC(Data.getLifeCycleData());
        }catch(Exception e){

        }
        Log.d("TEST", "B - >>> onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        refreshLogView("Activity B.onDestroy()\n");
        try{
            A.setLifestyleTextA(Data.getLifeCycleData());
        }catch(Exception e){

        }
        try{
            B.setLifecycleTextB(Data.getLifeCycleData());
        }catch(Exception e){

        }
        try{
            C.setLifestyleTextC(Data.getLifeCycleData());
        }catch(Exception e){

        }
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
        switch (requestCode){
            case 1:
                mStatusTextB.setText(data.getStringExtra(A.SEND_STATUS)+"Activity B:Resumed");
                break;
            case 3:
                mStatusTextB.setText(data.getStringExtra(C.SEND_STATUS)+"Activity B:Resumed");
                break;
        }
    }

    void refreshLogView(String msg){
        Data.appendLifeCycleData(msg);
//        mLifecycleTextB.append(msg);
        offset=(mLifecycleTextB.getLineCount() - 3) * mLifecycleTextB.getLineHeight();
        A.setOffset(offset);
        if(offset>mLifecycleTextB.getHeight()){
            mLifecycleTextB.scrollTo(0,offset-mLifecycleTextB.getHeight());
        }
    }
}
