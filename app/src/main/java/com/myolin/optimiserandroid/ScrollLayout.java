package com.myolin.optimiserandroid;

import android.content.Context;
import android.media.Image;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by mzlmy on 3/16/2017.
 */

public class ScrollLayout extends ScrollView {

    CustomLayout customLayout;
    ImageView imageView;

    public ScrollLayout(Context context, int width, int row, int start, int end, ReadAsset asset) {
        super(context);

        customLayout = new CustomLayout(context, width, row, start, end, asset);

        imageView = customLayout.getImageView();

        this.addView(customLayout);
    }

    public ImageView getImageView(){
        return imageView;
    }
}
