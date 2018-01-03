package xianglin.com.retrofit.biz.sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import xianglin.com.retrofit.R;

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
                break;
            case R.id.bt_create_delete:
                break;
            case R.id.bt_create_selete:
                break;
            case R.id.bt_create_update:
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
