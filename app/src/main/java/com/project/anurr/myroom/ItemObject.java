package com.project.anurr.myroom;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anurr on 5/11/2017.
 */

public class ItemObject {

    @SerializedName("room")
    public List<Children> room;

    public class Children {
        @SerializedName("id")
        public String id;
        @SerializedName("imgroom")
        public String imgroom;
        @SerializedName("pemilik")
        public String pemilik;
        @SerializedName("fasilitas")
        public String fasilitas;
        @SerializedName("nohp")
        public String nohp;
        @SerializedName("harga")
        public String harga;
        @SerializedName("alamat")
        public String alamat;
        @SerializedName("gambar")
        public String gambar;
        @SerializedName("gambar1")
        public String gambar1;
        @SerializedName("gambar2")
        public String gambar2;
        @SerializedName("ac")
        public String ac;
        @SerializedName("kipas")
        public String kipas;
        @SerializedName("jendela")
        public String jendela;
        @SerializedName("lemari")
        public String lemari;
        @SerializedName("kasur")
        public String kasur;
        @SerializedName("meja")
        public String meja;
        @SerializedName("deskripsi")
        public String deskripsi;
        @SerializedName("tersedia")
        public String tersedia;
        @SerializedName("kapasitas")
        public Integer kapasitas;
        @SerializedName("panjang")
        public Integer panjang;
        @SerializedName("lebar")
        public Integer lebar;
        @SerializedName("Latitude")
        public Double x;
        @SerializedName("Longitude")
        public Double y;
    }
}
