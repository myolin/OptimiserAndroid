package com.myolin.optimiserandroid;

import android.content.Context;
import android.graphics.Color;
import android.text.InputFilter;
import android.util.TypedValue;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

/**
 * Created by mzlmy on 3/31/2017.
 */

public class ComboBox extends AutoCompleteTextView {

    public ComboBox(Context context, String[] dataArray, String defaultValue){
        super(context);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, dataArray);
        setThreshold(1);
        setEms(3);
        setSingleLine(true);
        setAdapter(adapter);
        setText(defaultValue);
        setPadding(0,35,0,35);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
    }
}
