package com.example.quytrnhthitk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.CountDownTimer;
import android.text.Selection;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends Activity {
    ImageView ivhoi,ivtra;
    TextView tvkt,tvdiem;
    String hinhgoc;
    int tatol=100;
    public static ArrayList<String> arrayname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivhoi=(ImageView) findViewById(R.id.imagehoi);
        ivtra=(ImageView) findViewById(R.id.imagetraloi);
        ivtra.setImageResource(R.drawable.android);
        tvkt=(TextView) findViewById(R.id.textViewkt);
        tvdiem=(TextView) findViewById(R.id.textViewdiem);
        tvdiem.setText(tatol+"");

        String[] name=getResources().getStringArray(R.array.list_name);
        arrayname = new ArrayList<>(Arrays.asList(name));
        //tron mang
        Collections.shuffle(arrayname);
        hinhgoc=arrayname.get(2);
        int hinh=getResources().getIdentifier(arrayname.get(2),"drawable",getPackageName());
        ivhoi.setImageResource(hinh);
        ivtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivity_2.class);
                startActivityForResult(intent,123);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==123 && resultCode == RESULT_OK && data!=null){
            String hinhanh = data.getStringExtra("dulieuanh");
            int hinhmoi=getResources().getIdentifier(hinhanh,"drawable",getPackageName());
            ivtra.setImageResource(hinhmoi);
            if(hinhgoc.equals(hinhanh)){
                Toast.makeText(this, "Dung roi!\nBan duoc cong 15 diem", Toast.LENGTH_SHORT).show();
                tatol=tatol+15;
                tvdiem.setText(tatol+"");
                new CountDownTimer(2000,100){
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        Collections.shuffle(arrayname);
                        hinhgoc=arrayname.get(2);
                        int hinh=getResources().getIdentifier(arrayname.get(2),"drawable",getPackageName());
                        ivhoi.setImageResource(hinh);
                    }
                }.start();
            }
            else{
                Toast.makeText(this, "Sai roi!\nBan bi tru 10 diem", Toast.LENGTH_SHORT).show();
                tatol=tatol-10;
                tvdiem.setText(tatol+"");
            }
        }
        if(requestCode==123 && resultCode==RESULT_CANCELED){
            Toast.makeText(this, "Ban chua chon!\nBan bi tru 15 diem", Toast.LENGTH_SHORT).show();
            tatol=tatol-10;
            tvdiem.setText(tatol+"");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_custom,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menuload){
            Collections.shuffle(arrayname);
            hinhgoc=arrayname.get(2);
            int hinh=getResources().getIdentifier(arrayname.get(2),"drawable",getPackageName());
            ivhoi.setImageResource(hinh);
        }
        return super.onOptionsItemSelected(item);
    }
}
