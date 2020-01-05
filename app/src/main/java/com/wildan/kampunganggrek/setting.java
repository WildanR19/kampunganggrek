package com.wildan.kampunganggrek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class setting extends AppCompatActivity {

    ImageView usergb, emailgb, call, sms, loc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        usergb = (ImageView) findViewById(R.id.usergb);
        emailgb = (ImageView) findViewById(R.id.emailgb);
        call = (ImageView) findViewById(R.id.callgb);
        sms = (ImageView) findViewById(R.id.smsgb);
        loc = (ImageView) findViewById(R.id.loc);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri call_uri = Uri.parse("tel: +6281244556677");
                Intent call = new Intent(Intent.ACTION_DIAL, call_uri);
                call.putExtra("Call", "Call center");
                startActivity(call);
            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri sms_uri = Uri.parse("smsto:+6281244556677");
                Intent sms = new Intent(Intent.ACTION_SENDTO, sms_uri);
                sms.putExtra("SMS", "SMS center");
                startActivity(sms);
            }
        });

        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loc = "Kampoeng Anggrek Kediri";
                Uri addressUri = Uri.parse("geo:-7.942040,112.223721?q=" + loc);
                Intent map = new Intent(Intent.ACTION_VIEW, addressUri);
                startActivity(map);
            }
        });
    }
}
