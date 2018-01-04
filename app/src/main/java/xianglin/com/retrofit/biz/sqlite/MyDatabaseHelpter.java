package xianglin.com.retrofit.biz.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;

import xianglin.com.retrofit.utils.ToastUtils;

/**
 * 数据库
 * Created by ex-caoyanchang on 2018/1/2.
 */

public class MyDatabaseHelpter extends SQLiteOpenHelper {

    private Context mContext;

    public static final String CREATE_BOOK = "create table Book ("
            + "id integer PRIMARY KEY autoincrement,"
            + "author text,"
            + "price real,"
            + "pages integer,"
            + "name text)";

    private static final String CREATE_CATEGARY = "create table Category("
            + "id integer primary key autoincrement,"
            + "categotry_name text,"
            + "categotry_code integer)";

    public MyDatabaseHelpter(Context context, String name,
                             SQLiteDatabase.CursorFactory factory,
                             int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (db == null) {
            return;
        }
        db.execSQL(CREATE_BOOK);
        ToastUtils.showMsg("创建数据库成功");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (db == null) {
            return;
        }
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);
    }
}
