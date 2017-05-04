package com.example.vijay.radiodhoni912;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by vijay on 5/4/2017.
 */

public class ViewPagerAdapterGallery extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images = {R.drawable.slider1_f,R.drawable.slider2_f,R.drawable.slider3_f,R.drawable.slider4_f,R.drawable.slider5_f,R.drawable.slider6_f};

    public ViewPagerAdapterGallery(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_gallery,null);
        ImageView imageView = (ImageView)view.findViewById(R.id.imageView2);
        imageView.setImageResource(images[position]);


        ViewPager vp = (ViewPager)container;
        vp.addView(view,0);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager)container;
        View view = (View)object;
        vp.removeView(view);
    }

}
