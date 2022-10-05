package com.example.quytrnhthitk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Collections;

public class MainActivity_2 extends Activity {
    TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        tableLayout=(TableLayout) findViewById(R.id.tablelayoutanh);
        int a=3,b=3;
        Collections.shuffle(MainActivity.arrayname);
        int vt=0;
        for(int i=1;i<=a;i++){
            TableRow row=new TableRow(MainActivity_2.this);
            for(int j=1;j<=b;j++){
                //kich thuoc anh
                ImageView anh=new ImageView(MainActivity_2.this);
                TableRow.LayoutParams layoutParams=new TableRow.LayoutParams(350,350);
                anh.setLayoutParams(layoutParams);

                int hinh=getResources().getIdentifier(MainActivity.arrayname.get(vt),"drawable",getPackageName());
                anh.setImageResource(hinh);
                row.addView(anh);
                int finalVt = vt;
                anh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent();
                        intent.putExtra("dulieuanh", MainActivity.arrayname.get(finalVt));
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                });
                vt=vt+1;
            }
            tableLayout.addView(row);
        }
    }
}