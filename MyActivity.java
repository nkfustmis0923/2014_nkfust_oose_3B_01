package com.example.user.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;


import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;



public class MyActivity extends Activity {
  private   String[] singerdata={"大衣和風衣","夾克外套"};
    private String[] singerdata2={"襯衫","T恤","帽T","休閒衫","針織毛衣"};
  private   String[] singerdata3={"短褲","長褲","牛仔褲"};


    int mCount;
    int mCount2;
    int mCount3;
    private ArrayAdapter<String> listAdapter;
    private ArrayAdapter<String> listAdapter2;
    private ArrayAdapter<String> listAdapter3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        final ScrollView scroller=(ScrollView)findViewById(R.id.scroller);

        final ImageView image1=(ImageView)findViewById(R.id.hyorin);
        final ImageView image2=(ImageView)findViewById(R.id.bora);
        final ImageView image3=(ImageView)findViewById(R.id.soyou);

        final ListView list1=(ListView)findViewById(R.id.listView2);
        final ListView list2=(ListView)findViewById(R.id.listView3);
        final ListView list3=(ListView)findViewById(R.id.listView4);



        mCount=0;
        mCount2=0;
        mCount3=0;

       listAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,singerdata);
        listAdapter2=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,singerdata2);
        listAdapter3=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,singerdata3);
        list1.setAdapter(listAdapter);
        list2.setAdapter(listAdapter2);
        list3.setAdapter(listAdapter3);

list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    public void onItemClick(AdapterView<?> arg0, View arg1, int postion, long arg3) {
        Intent intent = null;
        switch (postion) {
            case 0:
                intent = new Intent(arg0.getContext(), ActivityZoom.class);
                break;
            case 1:
                intent=new Intent(arg0.getContext(),ActivityZoom.class);
                break;

        }
        startActivity(intent);
    }
});
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCount++;

                    list1.setVisibility(View.VISIBLE);
                list2.setVisibility(View.GONE);
                list3.setVisibility(View.GONE);

                mCount2=0;
                mCount3=0;

                if(mCount%2==0)
                    list1.setVisibility(View.GONE);
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] location = new int[2];
                image2.getLocationInWindow(location);
                int x = location[0];
                int y = location[1];
               scroller.smoothScrollTo(x,y);
                mCount2++;
                mCount=0;
                mCount3=0;


                    list2.setVisibility(View.VISIBLE);
                list1.setVisibility(View.GONE);
                list3.setVisibility(View.GONE);


                if(mCount2%2==0)
                    list2.setVisibility(View.GONE);
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCount3++;

                mCount2=0;
                mCount=0;

                    list3.setVisibility(View.VISIBLE);
                list1.setVisibility(View.GONE);
                list2.setVisibility(View.GONE);


                if(mCount3%2==0)
                    list3.setVisibility(View.GONE);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
