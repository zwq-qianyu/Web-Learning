package cn.runtofuture.www.activityskip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ActivityDialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TEST", "Activity Dialog.onCeate()\n");
        setContentView(R.layout.activity_dialog);
        Button closeButton = (Button)findViewById(R.id.CloseButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                ActivityDialog.this.setResult(RESULT_OK);
//                mStatusTextA.setText(oldStatus);
            }
        });
    }
}
