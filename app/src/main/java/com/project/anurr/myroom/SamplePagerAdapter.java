package com.project.anurr.myroom;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by anurr on 5/15/2017.
 */

public class SamplePagerAdapter extends PagerAdapter {

    private int mSize;
    Activity activity;
    String[] images;
    LayoutInflater inflater;


    public SamplePagerAdapter(Activity activity, String[] images) {
        this.activity = activity;
        this.images = images;
    }

    public SamplePagerAdapter(int count) {
        mSize = count;
    }

    @Override public int getCount() {
        return images.length;
    }

    @Override public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View)object);
    }

    @Override public Object instantiateItem(ViewGroup container, int position) {

        inflater = (LayoutInflater)activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_fragment, container ,false);

        ImageView image;
        image = (ImageView)itemView.findViewById(R.id.vpager_image);
        DisplayMetrics dis = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dis);
        int height = dis.heightPixels;
        int width = dis.widthPixels;
        image.setMinimumHeight(height);
        image.setMinimumWidth(width);

        try{
            Glide.with(activity.getApplicationContext())
                    .load(images[position])
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .centerCrop()
                    .into(image);
        }

        catch (Exception ex){
        }

        container.addView(itemView);
        return itemView;
    }

}
