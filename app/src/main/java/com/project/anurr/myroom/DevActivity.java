package com.project.anurr.myroom;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by anurr on 5/22/2017.
 */

public class DevActivity extends AppCompatActivity {

    CoordinatorLayout coordev;
    TextView sites, copyright;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        coordev = (CoordinatorLayout) findViewById(R.id.coordev);

        setContentView(R.layout.developer);

        sites = (TextView) findViewById(R.id.sites);
        copyright = (TextView) findViewById(R.id.copyright);

        sites.setMovementMethod(LinkMovementMethod.getInstance());
        copyright.setMovementMethod(LinkMovementMethod.getInstance());

        ImageView instagram = (ImageView)findViewById(R.id.instagram);
        ImageView github = (ImageView)findViewById(R.id.github);
        ImageView gplus = (ImageView)findViewById(R.id.gplus);
        ImageView email = (ImageView)findViewById(R.id.email);


        instagram.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://instagram.com/rofffiq"));
                startActivity(intent);
            }
        });

        github.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://github.com/rofffiq63"));
                startActivity(intent);
            }
        });

        gplus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://plus.google.com/u/0/108294348952401297999"));
                startActivity(intent);
            }
        });

        email.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String recepient ="a.nurrofiqi@gmail.com";
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SENDTO);
                //emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"a.nurrofiqi@gmail.com"});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hey! subject here");
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Ask me anything :)");
                emailIntent.setData(Uri.parse("mailto:" + recepient));
                //emailIntent.setType("message/rfc822");
                startActivity(Intent.createChooser(emailIntent, "Yay! a letter for me"));
            }
        });


        //Window window = this.getWindow();
        //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent));

        initToolbar();

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.devtoolbar);
        toolbar.setTitle("Developer");
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
