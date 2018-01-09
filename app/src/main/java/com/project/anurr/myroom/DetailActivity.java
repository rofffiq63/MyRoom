package com.project.anurr.myroom;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by anurr on 5/14/2017.
 */

public class DetailActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{

    private TextView pemilik, alamat, harga, avail, ukuran, kapasitas, kontak, tanggal, deskripsi, gmapslink;
    private Integer luas;
    private String[] images = new String[3];
    public LinearLayout ac, kipas, jendela, kasur, lemari, meja;
    private CoordinatorLayout coordetail;
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    private CardView detailcard, detailcall, detailsms;
    private Fragment maps;
    private Window window;
    public static Double lat;
    public static Double lon;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        coordetail = (CoordinatorLayout) findViewById(R.id.coordetail);

        window = this.getWindow();

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getIntent().getStringExtra("pemilik"));
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorAccent));
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.colorAccent));

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(this);

        toolbarTextAppearance();

        lat = getIntent().getDoubleExtra("lat",0.0);
        lon = getIntent().getDoubleExtra("lon",0.0);

        pemilik = (TextView) findViewById(R.id.title_pemilik);
        alamat = (TextView) findViewById(R.id.title_alamat);
        harga = (TextView) findViewById(R.id.title_harga);
        avail = (TextView) findViewById(R.id.avail);
        ukuran = (TextView) findViewById(R.id.ukuran);
        kapasitas = (TextView) findViewById(R.id.kapasitas);
        kontak = (TextView) findViewById(R.id.kontak);
        tanggal = (TextView) findViewById(R.id.tanggal);
        deskripsi = (TextView) findViewById(R.id.deskripsi);
        gmapslink = (TextView) findViewById(R.id.gmapslink);

        ac = (LinearLayout) findViewById(R.id.ac_layout);
        kipas = (LinearLayout) findViewById(R.id.kipas_layout);
        jendela = (LinearLayout) findViewById(R.id.jendela_layout);
        kasur = (LinearLayout) findViewById(R.id.kasur_layout);
        lemari = (LinearLayout) findViewById(R.id.lemari_layout);
        meja = (LinearLayout) findViewById(R.id.meja_layout);

        gmapslink.setMovementMethod(LinkMovementMethod.getInstance());

        detailcard = (CardView) findViewById(R.id.detail_direction);
        detailcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://maps.google.com/maps?"  +
                                        "&daddr=" + lat + ","
                                        + lon ));

                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });

        detailcall = (CardView) findViewById(R.id.detail_call);
        detailcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentcall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + getIntent().getStringExtra("nohp")));
                startActivity(intentcall);
            }
        });

        detailsms = (CardView) findViewById(R.id.detail_sms);
        detailsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", getIntent().getStringExtra("nohp"), null)));

            }
        });

        images[0] = "http://tugas.nurrofiqi.com/myRoomIMG/"+getIntent().getStringExtra("gambar")+".jpg";
        images[1] = "http://tugas.nurrofiqi.com/myRoomIMG/"+getIntent().getStringExtra("gambar1")+".jpg";
        images[2] = "http://tugas.nurrofiqi.com/myRoomIMG/"+getIntent().getStringExtra("gambar2")+".jpg";

        initToolbar();

        if (!Objects.equals(getIntent().getStringExtra("tersedia"), "y")){
            avail.setTextColor(0xffff7043);
            avail.setText("Not Available");
        } else {
            avail.setTextColor(0xff43a047);
            avail.setText("Available");
        }

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd");
        SimpleDateFormat mf = new SimpleDateFormat("MMM");
        String date = df.format(c.getTime());
        String month = mf.format(c.getTime());

        luas = getIntent().getIntExtra("panjang",0)*getIntent().getIntExtra("lebar",0);

        ViewPager viewpager = (ViewPager) findViewById(R.id.vpager);
        SamplePagerAdapter adapter = new SamplePagerAdapter(DetailActivity.this,images);
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.cindicator);

        viewpager.setAdapter(new SamplePagerAdapter(DetailActivity.this,images));
        indicator.setViewPager(viewpager);
        viewpager.setAdapter(adapter);

        pemilik.setText(getIntent().getStringExtra("pemilik"));
        alamat.setText(getIntent().getStringExtra("alamat"));
        tanggal.setText("Today, "+date+" "+month);
        //tanggal.setText("After 03:00 PM");
        ukuran.setText(luas+"M Square");
        kapasitas.setText(getIntent().getIntExtra("kapasitas",0)+" Person(s)");
        kontak.setText(getIntent().getStringExtra("nohp"));
        harga.setText("IDR "+getIntent().getStringExtra("harga")+"K/night");
        deskripsi.setText(Html.fromHtml(getIntent().getStringExtra("deskripsi")));

        if (!Objects.equals(getIntent().getStringExtra("ac"), "y")){
            ac.setAlpha(.1f);
        }

        if (!Objects.equals(getIntent().getStringExtra("kipas"), "y")){
            kipas.setAlpha(.1f);
        }

        if (!Objects.equals(getIntent().getStringExtra("jendela"), "y")){
            jendela.setAlpha(.1f);
        }

        if (!Objects.equals(getIntent().getStringExtra("kasur"), "y")){
            kasur.setAlpha(.1f);
        }

        if (!Objects.equals(getIntent().getStringExtra("lemari"), "y")){
            lemari.setAlpha(.1f);
        }

        if (!Objects.equals(getIntent().getStringExtra("meja"), "y")){
            meja.setAlpha(.1f);
        }

        MapsFragment();

    }

    private void toolbarTextAppearance() {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.report, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String recepient = "a.nurrofiqi@gmail.com";

        if (id == R.id.report) {
            Intent emailIntent = new Intent(android.content.Intent.ACTION_SENDTO);
            //emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"a.nurrofiqi@gmail.com"});
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Report room number " + getIntent().getStringExtra("id") + " by " + getIntent().getStringExtra("pemilik") + ".");
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Tell me your problem");
            emailIntent.setData(Uri.parse("mailto:" + recepient));
            //emailIntent.setType("message/rfc822");
            startActivity(Intent.createChooser(emailIntent, "Sent report..."));
        }

        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.detailtoolbar);
        toolbar.setTitle(getIntent().getStringExtra("pemilik"));
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

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        if (verticalOffset == 0)
        {
            // Collapsed
            //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent));
        }
        else
        {
            // Not collapsed
            //window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.transparent));
            //window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public static class MapsActivity extends Fragment implements OnMapReadyCallback{

        private GoogleMap maps;
        private LatLng room;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            SupportMapFragment fragment = new SupportMapFragment();
            transaction.add(R.id.maps, fragment);
            transaction.commit();

            fragment.getMapAsync(this);

            return rootView;

        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            maps = googleMap;

            // Add a marker in Location and move the camera
            room = new LatLng(lat,lon);
            maps.addMarker(new MarkerOptions().position(room).title("Room by "+getActivity().getIntent().getStringExtra("pemilik")));
            maps.setTrafficEnabled(true);
            maps.getUiSettings().setAllGesturesEnabled(false);
            maps.animateCamera(CameraUpdateFactory.newLatLngZoom(room, 17f));
        }

    }

    public void MapsFragment(){
        maps = Fragment.instantiate(this, MapsActivity.class.getName());
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.maps_container, maps);
        fragmentTransaction.commit();
    }

}

