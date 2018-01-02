package cn.edu.gdmec.android.wusihan;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.edu.gdmec.android.wusihan.db.MyDataBaseHelper;

public class EtActivityActivity extends Activity implements View.OnClickListener {

    private TextView textView4;
    private MyDataBaseHelper dataBaseHelper;
    private String title, content;
    private Button btn_fabiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.et_activity);
        initView();

        textView4 = (TextView) findViewById(R.id.textView4);
        dataBaseHelper = new MyDataBaseHelper(this, "Comment.db", null, 1);

    }

    private EditText getEtFabiao() {
        return (EditText) findViewById(R.id.et_fabiao);
    }

    private EditText getEtTitle() {
        return (EditText) findViewById(R.id.et_title);
    }

    private boolean huoquzhi() {
        content = getEtFabiao().getText().toString().trim();
        title = getEtTitle().getText().toString().trim();
        if (content != null && title != null) {
            return true;
        }
        return false;

    }

    private void initView() {
        btn_fabiao = (Button) findViewById(R.id.btn_fabiao);

        btn_fabiao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_fabiao:
                if (huoquzhi()) {
                    dataBaseHelper.insertData(title, content);
                }

                break;
        }
    }
}
