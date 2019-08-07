package com.era.cal;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class history extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // 获取布局
        View v = findViewById(R.id.page);
        v.setOnClickListener(this);
        String[] strArr = getIntent().getStringArrayExtra("data");

        // 获取TextView
        TextView tv = findViewById(R.id.tv);

        //
        StringBuilder sb = new StringBuilder();
        if(!(strArr == null)){
            for(String str: strArr){
                sb.append(str.concat("\r\n"));
            }
            tv.setText(sb.toString());
        }



    }

    @Override
    public void onClick(View view) {
        history.this.finish();
    }
}
