package cn.edu.gdmec.android.databaseDemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by asus on 2018/1/2.
 */

public class MyAdapter extends BaseAdapter {
    private List<Comment> list;
    private LayoutInflater mInflater;

    public MyAdapter(List<Comment> list, Context context) {
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object getItem(int position) {
        return list.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView  == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.main_item,null);
            viewHolder.tv1 = (TextView)convertView.findViewById(R.id.tv_1);
            viewHolder.tv2 = (TextView)convertView.findViewById(R.id.tv_2);
            viewHolder.tv3 = (TextView)convertView.findViewById(R.id.tv_3);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Comment comment = list.get(position);
        viewHolder.tv1.setText("评论者："+"\n"+"  "+comment.getTitle());
        viewHolder.tv2.setText("评论内容："+"\n"+"  "+comment.getContent());
        viewHolder.tv3.setText("评论时间："+"\n"+"  "+comment.getTimer());
        return convertView;
    }
    class ViewHolder{
        public TextView tv1;
        public TextView tv2;
        public TextView tv3;
    }
}
