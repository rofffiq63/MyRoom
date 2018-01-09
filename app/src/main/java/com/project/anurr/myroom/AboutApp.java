package com.project.anurr.myroom;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by anurr on 5/23/2017.
 */

public class AboutApp extends AppCompatActivity {

    TextView volley, gson, glide, askpermission, circleindicator, circleimageview, vers;
    String ver;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.about_app);

        //Window window = this.getWindow();
        //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent));

        try {
            PackageInfo pinfo = getPackageManager().getPackageInfo(getPackageName(),0);
            ver = pinfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        vers = (TextView) findViewById(R.id.version);
        vers.setText("MYROOM ver. "+ ver);

        initToolbar();

        volley = (TextView) findViewById(R.id.volley);
        gson = (TextView) findViewById(R.id.gson);
        glide = (TextView) findViewById(R.id.glide);
        askpermission = (TextView) findViewById(R.id.askpermission);
        circleindicator = (TextView) findViewById(R.id.circleindicator);
        circleimageview = (TextView) findViewById(R.id.circleimageview);

        volley.setMovementMethod(LinkMovementMethod.getInstance());
        gson.setMovementMethod(LinkMovementMethod.getInstance());
        glide.setMovementMethod(LinkMovementMethod.getInstance());
        askpermission.setMovementMethod(LinkMovementMethod.getInstance());
        circleindicator.setMovementMethod(LinkMovementMethod.getInstance());
        circleimageview.setMovementMethod(LinkMovementMethod.getInstance());


    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.devtoolbar);
        toolbar.setTitle("About");
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
