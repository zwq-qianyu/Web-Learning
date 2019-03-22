package cn.runtofuture.www.activityskip;

import android.app.Activity;
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

public class C extends AppCompatActivity {
    private static TextView mLifestyleTextC;

    public TextView getLifestyleTextC() {
        return mLifestyleTextC;
    }

    public static void setLifestyleTextC(String str) {
        mLifestyleTextC.setText(str);
    }



    public TextView getStatusTextC() {
        return mStatusTextC;
    }

    public void setStatusTextC(TextView statusTextC) {
        mStatusTextC = statusTextC;
    }

    private TextView mStatusTextC;
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

        Log.d("TEST", "C - >>> onCreate");

        setContentView(R.layout.activity_c);
        mLifestyleTextC = (TextView)findViewById(R.id.lifestyle_text_c);
        mLifestyleTextC.setMovementMethod(ScrollingMovementMethod.getInstance());
        mStatusTextC = (TextView)findViewById(R.id.status_text_c);
        mStatusTextC.setMovementMethod(ScrollingMovementMethod.getInstance());

        // 将intent中存放的记录先放入显示框中
        Intent m = getIntent();
        String get  = m.getStringExtra(GET_STATUS);
        if(get != null){
            mStatusTextC.append(m.getStringExtra(GET_STATUS));
        }
        Data.appendLifeCycleData("Activity C.onCreate()\n");
//        mLifestyleTextC.append("Activity C.onCreate()\n");

    }

    @Override
    protected void onStart() {
        super.onStart();
        mLifestyleTextC = (TextView)findViewById(R.id.lifestyle_text_c);
        mStatusTextC = (TextView)findViewById(R.id.status_text_c);
        Data.appendLifeCycleData("Activity C.onStart()\n");
//        mLifestyleTextC.append("Activity C.onStart()\n");
        Log.d("TEST", "C - >>> onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLifestyleTextC = (TextView)findViewById(R.id.lifestyle_text_c);
        mStatusTextC = (TextView)findViewById(R.id.status_text_c);
        Data.appendLifeCycleData("Activity C.onResume()\n");
//        mLifestyleTextC.append("Activity C.onResume()\n");
        try{
            mLifestyleTextC.setText(Data.getLifeCycleData());
        }catch(Exception e){
            System.out.println(e);
        }

        try{
            mStatusTextC.setText(getIntent().getStringExtra(GET_STATUS_ADD));
        }catch(Exception e){
            System.out.println(e);
        }

        mStatusTextC.append("Activity C: Resumed\n");
        mLifestyleTextC.scrollTo(0,offset-mLifestyleTextC.getHeight());

        Log.d("TEST", "C - >>> onResume");

        // 设置结果值
        Intent data = new Intent();
        setResult(RESULT_OK, data);

        // 点击跳转至 A
        findViewById(R.id.start_a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "A ------ >>> onClick");
                Intent intent = new Intent(C.this, A.class);
                intent.putExtra(SEND_STATUS,mStatusTextC.getText().toString());
                intent.putExtra(SEND_STATUS_ADD, "Activity C: Stoped\n");
                startActivityForResult(intent, REQUEST_CODE_A);
            }
        });

        // 点击跳转至 B
        findViewById(R.id.start_b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "B ------ >>> onClick");
                Intent intent = new Intent(C.this, B.class);
                intent.putExtra(SEND_STATUS,mStatusTextC.getText().toString());
                intent.putExtra(SEND_STATUS_ADD, "Activity C: Stoped\n");
                startActivityForResult(intent, REQUEST_CODE_B);
            }
        });

        //结束 C
        findViewById(R.id.finish_c).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "C ------ >>> finish click");
                offset += 5 * mLifestyleTextC.getLineHeight();
                finish();
            }
        });

        // dialog
        findViewById(R.id.dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(C.this, ActivityDialog.class);
                startActivityForResult(intent,4);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        refreshLogView("Activity C.onPause()\n");
        mStatusTextC.setText("Activity C: paused\n");
        Log.d("TEST", "C - >>> onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        refreshLogView("Activity C.onStop()\n");
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
        Log.d("TEST", "C - >>> onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        refreshLogView("Activity C.onDestroy()\n");
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
        Log.d("TEST", "C - >>> onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        refreshLogView("Activity C.onRestart()\n");
//        mLifestyleTextC.append("Activity C.onRestart()\n");
        Log.d("TEST", "C - >>> onRestart");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        switch (requestCode){
            case 1:
                mStatusTextC.setText(data.getStringExtra(A.SEND_STATUS)+"Activity C:Resumed");
                break;
            case 2:
                mStatusTextC.setText(data.getStringExtra(B.SEND_STATUS)+"Activity C:Resumed");
                break;
        }
    }

    void refreshLogView(String msg){
        Data.appendLifeCycleData(msg);
//        mLifestyleTextC.append(msg);
        offset=(mLifestyleTextC.getLineCount() - 3) * mLifestyleTextC.getLineHeight();
        A.setOffset(offset);
        if(offset>mLifestyleTextC.getHeight()){
            mLifestyleTextC.scrollTo(0,offset-mLifestyleTextC.getHeight());
        }
    }
}

