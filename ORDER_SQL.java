package com.example.user.hello;

import static com.example.user.hello.DbConstants.GRAYC1;
import static com.example.user.hello.DbConstants.GRAYC2;
import static com.example.user.hello.DbConstants.TABLE_NAME;
import static com.example.user.hello.DbConstants.TABLE_ORDER;
import static com.example.user.hello.DbConstants.TSHIRT;
import static com.example.user.hello.DbConstants.JEANS;
import static com.example.user.hello.DbConstants.NYLON;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by user on 2014/12/28.
 */
public class ORDER_SQL extends Activity implements View.OnClickListener {
    private DBOrder dborder = null;

    private EditText editgrayc1=null;
    private EditText editgrayc2=null;
    private EditText editjeans=null;
    private EditText edittshirt=null;
    private EditText editnylon=null;

    private Button btnOrder = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        orderView();

        openDatabase();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeDatabase();
    }

    private void openDatabase(){
        dborder = new DBOrder(this);
    }

    private void closeDatabase(){
        dborder.close();
    }
    private void orderView(){
        editgrayc1 = (EditText) findViewById(R.id.GrayC1);
        editgrayc2 = (EditText) findViewById(R.id.GrayC2);
        editnylon = (EditText) findViewById(R.id.Nylon);
        edittshirt = (EditText) findViewById(R.id.TShirt);
        editjeans=(EditText)findViewById(R.id.Jeans);



        btnOrder = (Button) findViewById(R.id.btnOrder);
        btnOrder.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOrder:

                add();
                ShowAlertDialog();
                break;
            default:
                break;
        }
    }
    private void add(){
        SQLiteDatabase db = dborder.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GRAYC1, editgrayc1.getText().toString());
        values.put(GRAYC2, editgrayc2.getText().toString());
        values.put(NYLON, editnylon.getText().toString());
        values.put(TSHIRT,edittshirt.getText().toString());
        values.put(JEANS,editjeans.getText().toString());


        db.insert(TABLE_ORDER, null, values);


    }
    private void ShowAlertDialog()
    {
        AlertDialog.Builder MyAlertDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(ORDER_SQL.this);
        View alert_view = inflater.inflate(R.layout.order,null);



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
        Intent it=new Intent(ORDER_SQL.this,ActivityZoom.class);
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(it);
    }
}
