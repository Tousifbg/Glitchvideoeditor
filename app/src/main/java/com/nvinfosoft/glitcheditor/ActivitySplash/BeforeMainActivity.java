package com.nvinfosoft.glitcheditor.ActivitySplash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.nvinfosoft.glitcheditor.ActivityMain.ActivityMain;
import com.nvinfosoft.glitcheditor.ActivityNoInternet.ActivityNoInternet;
import com.nvinfosoft.glitcheditor.R;



public class BeforeMainActivity extends AppCompatActivity {


    public ImageView btn1, share,moreapp;
    private AdView mAdView;
    Intent intent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        btn1 = findViewById(R.id.btn_1);
        share = findViewById(R.id.btn_2);
        moreapp = findViewById(R.id.btn_3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected(BeforeMainActivity.this)) {
                    Intent intent = new Intent(BeforeMainActivity.this, ActivityMain.class);
                    startActivity(intent);
                } else {
                    Intent n = new Intent(BeforeMainActivity.this, ActivityNoInternet.class);
                    startActivity(n);
                }
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://drive.google.com/file/d/1f3E-xS3UAlwkaouNj0BH5nfv1rs3qH83/view?usp=sharing");
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
            }
        });

        moreapp.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             intent = new Intent(android.content.Intent.ACTION_VIEW);

             //Copy App URL from Google Play Store.
             intent.setData(Uri.parse("https://play.google.com/store/apps/collection/topselling_free?clp=ChcKFQoPdG9wc2VsbGluZ19mcmVlEAcYAw%3D%3D:S:ANO1ljLwMrI&gsr=ChkKFwoVCg90b3BzZWxsaW5nX2ZyZWUQBxgD:S:ANO1ljIxP20&hl=en&gl=US"));

             startActivity(intent);
         }});

    }


    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wifiInfo != null && wifiInfo.isConnected()) || (mobileInfo != null && mobileInfo.isConnected())) {
            return true;
        } else {

            return false;
        }


    }


}