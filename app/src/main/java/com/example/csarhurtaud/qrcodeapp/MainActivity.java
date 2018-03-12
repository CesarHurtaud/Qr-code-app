package com.example.csarhurtaud.qrcodeapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    //View Objects
    private Button buttonScan;
    private TextView textViewWelcome;

    //qr code scanner object
    private IntentIntegrator qrScan;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemClicked = item.getItemId();
        if (itemClicked == R.id.waza ) {
            Toast.makeText(this, "WAZAAAAAAAAAAAAAA", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View objects
        buttonScan = (Button) findViewById(R.id.buttonScan);
        textViewWelcome = (TextView) findViewById(R.id.textViewWelcome);

        //attaching onclick listener
        buttonScan.setOnClickListener(this);

        //intializing scan object
        qrScan = new IntentIntegrator(this);
    }

    @Override
    public void onClick(View view) {
        //initiating the qr code scan
        qrScan.initiateScan();
    }


    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qr contains data

            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {

                if (result.getContents().startsWith("http")) {

                    Intent webViewLauncher = new Intent(this, WebViewActivity.class);
                    webViewLauncher.putExtra("message", result.getContents());
                    startActivity(webViewLauncher);

                } else {
                    try {

                        JSONObject obj = new JSONObject(result.getContents());
                        Intent webViewLauncher = new Intent(this, WebViewActivity.class);
                        webViewLauncher.putExtra("message", obj.getString("url"));
                        startActivity(webViewLauncher);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        //if control comes here
                        //that means the encoded format not matches
                        //in this case you can display whatever data is available on the qrcode
                        //to a toast
                        Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}
