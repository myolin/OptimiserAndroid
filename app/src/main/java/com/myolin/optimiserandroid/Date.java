package com.myolin.optimiserandroid;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by mzlmy on 3/31/2017.
 */

public class Date extends TextView {

    Calendar calendar = Calendar.getInstance();

    public Date(final Context context){
        super(context);

        setPadding(0,35,0,35);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        setBackgroundResource(R.drawable.border);


        final DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                setText((month+1) + "/" + dayOfMonth + "/" + year);
            }
        };

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(context, dateListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                                        Calendar.DAY_OF_MONTH).show();
            }
        });
    }



}
