package com.project.anurr.myroom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private CoordinatorLayout coorlay;
    private ProgressDialog pd;
    private Snackbar sb;
    private Fragment demoFragment;
    private String url = "http://tugas.nurrofiqi.com/php/data.php";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = this.getWindow();
        //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent));

        initToolbar();

        coorlay = (CoordinatorLayout) findViewById(R.id.coorlay);
        pd = new ProgressDialog(MainActivity.this);
        pd.setTitle("");
        pd.setMessage("Finding room for you");

        AmbilData(url);

    }

    public void AmbilData(String URL) {
        pd.show();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ConnectedFragment();
                sb = Snackbar.make(coorlay,"Connected.",Snackbar.LENGTH_LONG);
                sb.show();
                pd.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NotConnectedFragment();
                pd.hide();
                Snackbar snackbar  = Snackbar.make(coorlay,"Please, check your internet connection.",Snackbar.LENGTH_INDEFINITE)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sb = Snackbar.make(coorlay,"Reconnecting...",Snackbar.LENGTH_INDEFINITE);
                                View view = sb.getView();
                                view.setBackgroundColor(getResources().getColor(R.color.colorRed));
                                AmbilData(url);
                                sb.show();

                            }
                        });
                View view = snackbar.getView();
                view.setBackgroundColor(getResources().getColor(R.color.colorRed));
                TextView tv = (TextView)view.findViewById(android.support.design.R.id.snackbar_action);
                tv.setTextColor(Color.WHITE);
                snackbar.show();
            }
        });
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_dev) {
            Intent nav = new Intent(getApplicationContext(), DevActivity.class);
            startActivity(nav);

        } else if (id == R.id.nav_about) {
            Intent bout = new Intent(getApplicationContext(), AboutApp.class);
            startActivity(bout);

        }  else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static class NoConnection extends Fragment {

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_no_connection, container, false);
        }
    }

    public void ConnectedFragment(){
        demoFragment = Fragment.instantiate(this, DataFragment.class.getName());
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.fragment_container, demoFragment);
        fragmentTransaction.commit();
    }

    public void NotConnectedFragment(){
        demoFragment = Fragment.instantiate(this, NoConnection.class.getName());
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.fragment_container, demoFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String recepient = "a.nurrofiqi@gmail.com";

        if (id == R.id.action_report) {
            Intent emailIntent = new Intent(android.content.Intent.ACTION_SENDTO);
            //emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"a.nurrofiqi@gmail.com"});
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject here");
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Tell me your problem");
            emailIntent.setData(Uri.parse("mailto:" + recepient));
            //emailIntent.setType("message/rfc822");
            startActivity(Intent.createChooser(emailIntent, "Sent report..."));
        } else if (id == R.id.action_rrefresh) {
            AmbilData(url);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("MYROOM");
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
