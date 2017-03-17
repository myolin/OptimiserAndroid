package com.myolin.optimiserandroid;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.InputType;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;

/**
 * Created by mzlmy on 3/16/2017.
 */

public class TextBox extends EditText {

    public TextBox(Context context) {
        super(context);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0,15,0,15);
        this.setLayoutParams(lp);
        //this.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        this.setEms(10);
        this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        this.setBackgroundResource(R.drawable.border);
        this.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
    }
}
