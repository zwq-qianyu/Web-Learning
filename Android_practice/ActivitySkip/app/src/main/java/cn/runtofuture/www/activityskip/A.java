package cn.runtofuture.www.activityskip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class A extends AppCompatActivity {
    public TextView getLifestyleTextA() {
        return mLifestyleTextA;
    }

    public static void setLifestyleTextA(String str) {
        mLifestyleTextA.setText(str);
    }

    private static TextView mLifestyleTextA;
    private TextView mStatusTextA;
    private static int offset;

    public static int getOffset(){
        return offset;
    }

    public static void setOffset(int n){
        offset = n;
    }

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

        Log.d("TEST", "A - >>> onCreate");

        setContentView(R.layout.activity_a);
        mLifestyleTextA = (TextView)findViewById(R.id.lifecycle_text_a);
        mLifestyleTextA.setMovementMethod(ScrollingMovementMethod.getInstance());
        mStatusTextA = (TextView)findViewById(R.id.status_text_a);
        mStatusTextA.setMovementMethod(ScrollingMovementMethod.getInstance());

        // 将intent中存放的记录先放入显示框中
        Intent m = getIntent();
        String get  = m.getStringExtra(GET_STATUS);
        if(get != null){
            mStatusTextA.append(m.getStringExtra(GET_STATUS));
        }

        Data.appendLifeCycleData("Activity A.onCreate()\n");
//        mLifestyleTextA.append("Activity A.onCreate()\n");
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLifestyleTextA = (TextView)findViewById(R.id.lifecycle_text_a);
        mStatusTextA = (TextView)findViewById(R.id.status_text_a);
        Data.appendLifeCycleData("Activity A.onStart()\n");
//        mLifestyleTextA.append("Activity A.onStart()\n");
        Log.d("TEST", "A - >>> onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Data.appendLifeCycleData("Activity A.onResume()\n");
//        mLifestyleTextA.append("Activity A.onResume()\n");
        try{
            mLifestyleTextA.setText(Data.getLifeCycleData());
        }catch(Exception e){
            System.out.println(e);
        }

        try{
            // GET_STATUS_ADD 添加
            mStatusTextA.setText(getIntent().getStringExtra(GET_STATUS_ADD));
        }catch(Exception e){
            System.out.println(e);
        }
        mStatusTextA.append("Activity A: Resumed\n");
        mLifestyleTextA.scrollTo(0,offset - mLifestyleTextA.getHeight());

        Log.d("TEST", "A - >>> onResume");

        // 设置结果值
        Intent data = new Intent();
        setResult(RESULT_OK, data);

        // 点击跳转至 B
        findViewById(R.id.start_b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "B ------ >>> onClick");
                Intent intent = new Intent(A.this, B.class);
                intent.putExtra(SEND_STATUS,mStatusTextA.getText().toString());
                intent.putExtra(SEND_STATUS_ADD, "Activity A: Stoped\n");
                startActivityForResult(intent, REQUEST_CODE_B);
            }
        });

        // 点击跳转至 C
        findViewById(R.id.start_c).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "C ------ >>> onClick");
                Intent intent = new Intent(A.this, C.class);
                intent.putExtra(SEND_STATUS,mStatusTextA.getText().toString());
                intent.putExtra(SEND_STATUS_ADD, "Activity A: Stoped\n");
                startActivityForResult(intent, REQUEST_CODE_C);
            }
        });

        //结束 A
        findViewById(R.id.finish_a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", "A ------ >>> finish click");
                offset += 5 * mLifestyleTextA.getLineHeight();
                finish();
            }
        });

        // dialog
        findViewById(R.id.dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(A.this, ActivityDialog.class);
                startActivityForResult(intent, 4);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        refreshLogView("Activity A.onPause()\n");
        mStatusTextA.setText("Activity A: paused\n");
        Log.d("TEST", "A - >>> onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        refreshLogView("Activity A.onStop()\n");
        mLifestyleTextA.setText(Data.getLifeCycleData());
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
        Log.d("TEST", "A - >>> onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        refreshLogView("Activity A.onDestroy()\n");
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
        Log.d("TEST", "A - >>> onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        refreshLogView("Activity A.onRestart()\n");
        Log.d("TEST", "A - >>> onRestart");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        switch (requestCode){
            case 3:
                mStatusTextA.setText(data.getStringExtra(C.SEND_STATUS)+"Activity A:Resumed");
                break;
            case 2:
                mStatusTextA.setText(data.getStringExtra(B.SEND_STATUS)+"Activity A:Resumed");
                break;
        }
    }

    void refreshLogView(String msg){
        Data.appendLifeCycleData(msg);
//        mLifestyleTextA.append(msg);
        offset=(mLifestyleTextA.getLineCount() - 3) * mLifestyleTextA.getLineHeight();
        if(offset>mLifestyleTextA.getHeight()){
            mLifestyleTextA.scrollTo(0,offset - mLifestyleTextA.getHeight());
        }
    }
}

