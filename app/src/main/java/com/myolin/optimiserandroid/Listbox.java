package com.myolin.optimiserandroid;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by mzlmy on 3/22/2017.
 */

public class Listbox extends Spinner {

    public Listbox(Context context, String[] dataArray){
        super(context);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, dataArray);
        setAdapter(adapter);
        setPadding(0,35,0,35);

    }


}
