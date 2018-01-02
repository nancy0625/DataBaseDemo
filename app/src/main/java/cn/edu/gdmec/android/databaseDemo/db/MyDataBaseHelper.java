package cn.edu.gdmec.android.databaseDemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.gdmec.android.databaseDemo.Comment;

/**
 * Created by asus on 2018/1/2.
 */

public class MyDataBaseHelper extends SQLiteOpenHelper{
    private Context mContext;
    private SQLiteDatabase db;
    private static final String CREATE_COMMENT = "create table comment ("
            + " id integer primary key autoincrement, "
            + " title varchar(25), "
            + " content text, "
            + " timer integer)";


    public MyDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_COMMENT   );
        Toast.makeText(mContext,"创建成功",Toast.LENGTH_LONG).show();

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public List<Comment> select(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Comment> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(" select title,content,strftime('%Y-%m-%d %H:%M:%S',datetime(round(timer/1000),'unixepoch'))" +
                " from comment" +
                " group by strftime('%Y-%m-%d %H:%M:%S',datetime(round(timer/1000),'unixepoch'))" +
                " order by 1 desc",null);
        cursor.moveToFirst();

        do {
            Comment comment = new Comment();

            comment.setTitle(cursor.getString(0));
            comment.setContent(cursor.getString(1));
            comment.setTimer(cursor.getString(2));
            list.add(comment);


        }while (cursor.moveToNext());
        cursor.close();
        return list;
    }
    public void insertData(String title,String content){
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        contentValues.put("content",content);
        contentValues.put("timer",new Date().getTime());
        db.insert("comment",null,contentValues);
        Log.i("成功","插入");
    }
}
