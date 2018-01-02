package cn.edu.gdmec.android.wusihan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.wusihan.db.MyDataBaseHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private MyDataBaseHelper dataBaseHelper;
    private MyAdapter adapter;
    private ListView lv;
    private List<Comment> list;
    private Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBaseHelper = new MyDataBaseHelper(this, "Comment.db", null, 1);
        list = new ArrayList<>();
        initView();
    }

    private void initView() {
        textView = (TextView) findViewById(R.id.textView);
        lv = (ListView)findViewById(R.id.lv_activity) ;

        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search:
                list = dataBaseHelper.select(2);
                adapter = new MyAdapter(list,this);
                lv.setAdapter(adapter);

                break;
        }
    }
}
