package com.example.user.hello;

import static android.provider.BaseColumns._ID;

import static com.example.user.hello.DbConstants.NAME;
import static com.example.user.hello.DbConstants.TABLE_NAME;
import static com.example.user.hello.DbConstants.TEL;
import static com.example.user.hello.DbConstants.ACC;
import static com.example.user.hello.DbConstants.PWD;
import static com.example.user.hello.DbConstants.ADR;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class SQL extends Activity implements OnClickListener {

    private DBHelper dbhelper = null;

    private EditText editName = null;
    private EditText editTel = null;
    private EditText editEmail = null;
    private EditText  editAdr = null;
    private EditText editAcc=null;
    private EditText editPwd=null;

    private Button btnSure = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);

        initView();

        openDatabase();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDatabase();
    }

    private void openDatabase(){
        dbhelper = new DBHelper(this);
    }

    private void closeDatabase(){
        dbhelper.close();
    }

    private void initView(){
        editName = (EditText) findViewById(R.id.editName);
        editTel = (EditText) findViewById(R.id.editTel);
        editAdr = (EditText) findViewById(R.id.editAdr);
        editAcc=(EditText)findViewById(R.id.account);
        editPwd=(EditText)findViewById(R.id.pwd);


        btnSure = (Button) findViewById(R.id.btnSure);
        btnSure.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSure:

                add();
                ShowAlertDialog();
                break;
            default:
                break;
        }
    }

    private void add(){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, editName.getText().toString());
        values.put(TEL, editTel.getText().toString());
        values.put(ACC,editAcc.getText().toString());
        values.put(PWD,editPwd.getText().toString());
        values.put(ADR,editAdr.getText().toString());

        db.insert(TABLE_NAME, null, values);


    }
    private void ShowAlertDialog()
    {
        AlertDialog.Builder MyAlertDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(SQL.this);
        View alert_view = inflater.inflate(R.layout.register,null);



        MyAlertDialog.setView(alert_view);
        final AlertDialog dialog = MyAlertDialog.setPositiveButton(getString(R.string.ok_label),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //如果未輸入提示使用者輸入
                        launchIntent();
                    }
                }).create();

        dialog.show();
    }
    private  void launchIntent()
    {
        Intent it=new Intent(SQL.this,MyActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(it);
    }

  /*  private Cursor getCursor(){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String[] columns = { _ID,NAME,ACC,PWD, TEL, EMAIL,ADR};

        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
        startManagingCursor(cursor);

        return cursor;
    } */



}

