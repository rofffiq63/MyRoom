package com.project.anurr.myroom;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Objects;

/**
 * Created by anurr on 5/14/2017.
 */

public class DataFragment extends Fragment {

    private RecyclerView item;
    private AdapterItem adapterItem;
    private ItemObject itemObject;
    private String url= "http://tugas.nurrofiqi.com/php/data.php";
    private long FADE_DURATION=1000;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_data, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        item = (RecyclerView) view.findViewById(R.id.itemlist);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        item.setLayoutManager(linearLayoutManager);
        item.setNestedScrollingEnabled(false);

        AmbilData(url);

    }


    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    public void AmbilData(String URL) {

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                itemObject = gson.fromJson(response, ItemObject.class);
                adapterItem = new AdapterItem(getActivity().getApplicationContext(), itemObject.room);
                item.setAdapter(adapterItem);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AmbilData(url);
            }
        });
        requestQueue.add(stringRequest);

    }

    public class AdapterItem extends RecyclerView.Adapter<HolderItem> {
        Context context;
        List<ItemObject.Children> itemObject;

        public AdapterItem(Context context,List<ItemObject.Children> itemObject){
            this.context = context;
            this.itemObject = itemObject;
        }

        @Override
        public HolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carditem, null, false);
            HolderItem holderItem = new HolderItem(view);

            return holderItem ;
        }

        @Override
        public void onBindViewHolder(final HolderItem holder, final int position) {

            holder.txtpmlk.setText(itemObject.get(position).pemilik);
            holder.txtalmt.setText(itemObject.get(position).alamat);
            holder.harga.setText("IDR "+itemObject.get(position).harga+"K/night");
            holder.lat = itemObject.get(position).x;
            holder.lon = itemObject.get(position).y;

            Glide.with(context)
                    .load("http://tugas.nurrofiqi.com/myRoomIMG/"+itemObject.get(position).gambar+".jpg")
                    .centerCrop()
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .dontAnimate()
                    .into(holder.imgcard);

            Glide.with(context)
                    .load("http://tugas.nurrofiqi.com/myRoomIMG/fasilitas/ac.png")
                    .error(R.drawable.placeholder)
                    .fitCenter()
                    .dontAnimate()
                    .into(holder.imgac);

            Glide.with(context)
                    .load("http://tugas.nurrofiqi.com/myRoomIMG/fasilitas/kipas.png")
                    .error(R.drawable.placeholder)
                    .fitCenter()
                    .dontAnimate()
                    .into(holder.imgkipas);

            Glide.with(context)
                    .load("http://tugas.nurrofiqi.com/myRoomIMG/fasilitas/jendela.png")
                    .error(R.drawable.placeholder)
                    .fitCenter()
                    .dontAnimate()
                    .into(holder.imgjendela);

            Glide.with(context)
                    .load("http://tugas.nurrofiqi.com/myRoomIMG/fasilitas/kasur.png")
                    .error(R.drawable.placeholder)
                    .fitCenter()
                    .dontAnimate()
                    .into(holder.imgkasur);

            Glide.with(context)
                    .load("http://tugas.nurrofiqi.com/myRoomIMG/fasilitas/lemari.png")
                    .error(R.drawable.placeholder)
                    .fitCenter()
                    .dontAnimate()
                    .into(holder.imglemari);

            Glide.with(context)
                    .load("http://tugas.nurrofiqi.com/myRoomIMG/fasilitas/meja.png")
                    .error(R.drawable.placeholder)
                    .fitCenter()
                    .dontAnimate()
                    .into(holder.imgmeja);

            if (!Objects.equals(itemObject.get(position).ac, "y")){
                holder.imgac.setAlpha(.5f);
            }

            if (!Objects.equals(itemObject.get(position).kipas, "y")){
                holder.imgkipas.setAlpha(.5f);
            }

            if (!Objects.equals(itemObject.get(position).jendela, "y")){
                holder.imgjendela.setAlpha(.5f);
            }

            if (!Objects.equals(itemObject.get(position).kasur, "y")){
                holder.imgkasur.setAlpha(.5f);
            }

            if (!Objects.equals(itemObject.get(position).lemari, "y")){
                holder.imglemari.setAlpha(.5f);
            }

            if (!Objects.equals(itemObject.get(position).meja, "y")){
                holder.imgmeja.setAlpha(.5f);
            }

            holder.dir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //String uri = "http://maps.google.com/maps?daddr=" + holder.lat +","+ holder.lon;
                    //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    String label = "Room owned by "+itemObject.get(position).pemilik;
                    String uriBegin = "geo:" + holder.lat + "," + holder.lon;
                    String query = holder.lat + "," + holder.lon + "(" + label + ")";
                    String encodedQuery = Uri.encode(query);
                    String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                    Uri uri = Uri.parse(uriString);
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.google.android.apps.maps");
                    v.getContext().startActivity(intent);
                }
            });

            holder.cardroom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent in = new Intent(v.getContext(), DetailActivity.class);
                    in.putExtra("id",itemObject.get(position).id);
                    in.putExtra("pemilik", itemObject.get(position).pemilik);
                    in.putExtra("alamat", itemObject.get(position).alamat);
                    in.putExtra("harga", itemObject.get(position).harga);
                    in.putExtra("gambar", itemObject.get(position).gambar);
                    in.putExtra("gambar1", itemObject.get(position).gambar1);
                    in.putExtra("gambar2", itemObject.get(position).gambar2);
                    in.putExtra("ac", itemObject.get(position).ac);
                    in.putExtra("kipas", itemObject.get(position).kipas);
                    in.putExtra("jendela", itemObject.get(position).jendela);
                    in.putExtra("kasur", itemObject.get(position).kasur);
                    in.putExtra("lemari", itemObject.get(position).lemari);
                    in.putExtra("meja", itemObject.get(position).meja);
                    in.putExtra("tersedia", itemObject.get(position).tersedia);
                    in.putExtra("panjang", itemObject.get(position).panjang);
                    in.putExtra("lebar", itemObject.get(position).lebar);
                    in.putExtra("kapasitas", itemObject.get(position).kapasitas);
                    in.putExtra("nohp", itemObject.get(position).nohp);
                    in.putExtra("deskripsi", itemObject.get(position).deskripsi);
                    in.putExtra("lat", itemObject.get(position).x);
                    in.putExtra("lon", itemObject.get(position).y);
                    v.getContext().startActivity(in);
                }
            });

            setScaleAnimation(holder.itemView);

        }

        @Override
        public int getItemCount() {
            return itemObject.size();
        }
    }

}
