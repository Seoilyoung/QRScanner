package com.example.administrator.qrcode.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.qrcode.Activity.DataActivity;
import com.example.administrator.qrcode.R;
import com.google.zxing.integration.android.IntentIntegrator;


public class MainActivity extends AppCompatActivity {
int count =0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        new IntentIntegrator(this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == Activity.RESULT_OK) {
            Intent intent = new Intent(getApplicationContext(), DataActivity.class);
            intent.putExtra("data", data.getStringExtra("SCAN_RESULT"));
            startActivity(intent);
            finish();
        }
    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(result.getContents()));
        startActivity(intent);

    }
*/



}
