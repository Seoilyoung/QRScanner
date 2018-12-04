package com.example.administrator.qrcode.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qrcode.Model.QRData;
import com.example.administrator.qrcode.Network.Controller;
import com.example.administrator.qrcode.Network.ServerInterface;
import com.example.administrator.qrcode.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by seoil on 2016-11-05.
 */
public class DataActivity extends AppCompatActivity{
    Handler handler = new Handler();
    TextView txt_data ;
    String hashkey="", date="", mail="", type="" ;
    private ServerInterface api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Controller application = Controller.getInstance();
        application.buildNetworkService();
        api = Controller.getInstance().getNetworkService();

        Intent intent = getIntent();
        txt_data = (TextView)findViewById(R.id.txt_data);
        String[] str_temp = intent.getStringExtra("data").split("/");
        if(str_temp.length !=4 )
            Toast.makeText(getApplicationContext(),"입력 데이터 오류 "+intent.getStringExtra("data"),Toast.LENGTH_SHORT).show();
        else {
            hashkey = str_temp[0];
            date = str_temp[1];
            mail = str_temp[2];
            type = str_temp[3];
            txt_data.setText(hashkey + "\n" + date + "\n" + mail + "\n" + type);

            if(hashkey.equals("null") || date.equals("null") || mail.equals("null") || type.equals("null"))
                Toast.makeText(getApplicationContext(),"입력 데이터 오류 "+intent.getStringExtra("data"),Toast.LENGTH_SHORT).show();
            else {
                Log.d("Mytag", "not null");
                Call<QRData> verifyqr = api.verifyqr(mail, hashkey, type);
                verifyqr.enqueue(new Callback<QRData>() {
                    @Override
                    public void onResponse(Call<QRData> call, Response<QRData> response) {

                        if (response.body().result.equals("success")) {
                            Toast.makeText(getApplicationContext(), "verify success", Toast.LENGTH_SHORT).show();
                            // 여기다가 아두이노 움직이는거 넣기!
                            // 여기가 서버에 QR코드 데이터 전송해서 검증후 성공했을 때 오는 곳
                            //
                            //
                            //
                        } else {
                            int statusCode = response.code();
                            Log.i("MyTag", "verify fail");
                            Log.i("MyTag", "응답코드 : " + statusCode);
                        }
                    }

                    @Override
                    public void onFailure(Call<QRData> call, Throwable t) {
                        Log.d("MyTag", "Server not connect");
                    }
                });
            }
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
