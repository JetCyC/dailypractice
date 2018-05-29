package yc.com.app.biz.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import yc.com.app.R;

public class SqActivity extends AppCompatActivity {


    @BindView(R.id.bt_create_sq)
    Button btCreateSq;
    @BindView(R.id.bt_create_insert)
    Button btCreateInsert;
    @BindView(R.id.bt_create_delete)
    Button btCreateDelete;
    @BindView(R.id.bt_create_update)
    Button btCreateUpdate;
    @BindView(R.id.bt_create_selete)
    Button btCreateSelete;
    private MyDatabaseHelpter myDatabaseHelpter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq);
        unbinder = ButterKnife.bind(this);
        myDatabaseHelpter = new MyDatabaseHelpter(this,
                "BookStore.db",
                null,
                2);
        btCreateSq = (Button) findViewById(R.id.bt_create_sq);
        btCreateSq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myDatabaseHelpter != null) {
                    myDatabaseHelpter.getWritableDatabase();
                }
            }
        });


    }

    @OnClick({R.id.bt_create_sq, R.id.bt_create_insert, R.id.bt_create_delete, R.id.bt_create_selete, R.id.bt_create_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_create_sq:
                if (myDatabaseHelpter != null) {
                    myDatabaseHelpter.getWritableDatabase();
                }
                break;
            case R.id.bt_create_insert:
                SQLiteDatabase sqLiteDatabase = myDatabaseHelpter.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                //1
                contentValues.put("author", "jet");
                contentValues.put("price", 88.88);
                contentValues.put("pages", 66);
                contentValues.put("name", "Bookname1");
                sqLiteDatabase.insert("Book", null, contentValues);
                contentValues.clear();
                //2
                contentValues.put("author", "cyc");
                contentValues.put("price", 66.66);
                contentValues.put("pages", 22);
                contentValues.put("name", "Bookname2");
                sqLiteDatabase.insert("Book", null, contentValues);
                break;
            case R.id.bt_create_delete:
                SQLiteDatabase sqLiteDatabase2 = myDatabaseHelpter.getWritableDatabase();
                sqLiteDatabase2.delete("Book", "pages > ?", new String[]{"30"});
                break;
            case R.id.bt_create_selete:
                SQLiteDatabase sqLiteDatabase3 = myDatabaseHelpter.getWritableDatabase();
                Cursor cursor = sqLiteDatabase3.query(
                        "Book",
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                );
                if (cursor.moveToFirst()) {
                    do {
//                        +"id integer primary key autoincrement,"
//                                + "author text,"
//                                + "price real,"
//                                + "pages integer,"
//                                + "name text)";
                        int id = cursor.getInt(cursor.getColumnIndex("id"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        Log.e("TAG" + "  id", id + "");
                        Log.e("TAG" + "  author", author);
                        Log.e("TAG" + "  price", price + "");
                        Log.e("TAG" + "  pages", pages + "");
                        Log.e("TAG" + "  name", name);

                    } while (cursor.moveToNext());

                }
                cursor.close();
                break;
            case R.id.bt_create_update:
                SQLiteDatabase sqLiteDatabase1 = myDatabaseHelpter.getWritableDatabase();
                ContentValues contentValues1 = new ContentValues();
                contentValues1.put("price", 1.11);
                sqLiteDatabase1.update("Book", contentValues1, "name = ?", new String[]{"Bookname2"});
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder == null) {
            unbinder.unbind();
        }
    }
}
