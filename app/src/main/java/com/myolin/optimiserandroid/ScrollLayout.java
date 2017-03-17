package com.myolin.optimiserandroid;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by mzlmy on 3/16/2017.
 */

public class ScrollLayout extends ScrollView {

    CustomLayout customLayout;

    public ScrollLayout(Context context, int width, int row, int start, int end) {
        super(context);

        customLayout = new CustomLayout(context, width, row, start, end);

        this.addView(customLayout);
    }
}
