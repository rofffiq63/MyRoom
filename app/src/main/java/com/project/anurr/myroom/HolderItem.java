package com.project.anurr.myroom;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by anurr on 5/11/2017.
 */

public class HolderItem extends RecyclerView.ViewHolder{

    TextView txtpmlk, txtfslt, txtalmt, harga;
    String url = "maps.google.com";
    CardView cardroom;
    ImageView imgcard, imgac, imgkipas, imgjendela, imglemari, imgkasur, imgmeja, dir;
    double lat, lon;

    HolderItem(View itemView){
        super(itemView);

        cardroom = (CardView) itemView.findViewById(R.id.cardroom);
        txtpmlk = (TextView) itemView.findViewById(R.id.txtpmlk);
        txtalmt = (TextView) itemView.findViewById(R.id.alamat);
        harga = (TextView) itemView.findViewById(R.id.harga);
        imgcard = (ImageView) itemView.findViewById(R.id.imgcard);
        imgac = (ImageView) itemView.findViewById(R.id.imgac);
        imgkipas = (ImageView) itemView.findViewById(R.id.imgkipas);
        imgjendela = (ImageView) itemView.findViewById(R.id.imgjendela);
        imglemari = (ImageView) itemView.findViewById(R.id.imglemari);
        imgkasur = (ImageView) itemView.findViewById(R.id.imgkasur);
        imgmeja = (ImageView) itemView.findViewById(R.id.imgmeja);
        dir = (ImageView) itemView.findViewById(R.id.dir);

    }
}
