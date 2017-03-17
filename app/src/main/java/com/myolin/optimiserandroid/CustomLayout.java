package com.myolin.optimiserandroid;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.InputFilter;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by mzlmy on 3/14/2017.
 */

public class CustomLayout extends LinearLayout {

    LinearLayout labelView;
    LinearLayout controlView;
    ReadAsset asset = new ReadAsset(getContext());

    public CustomLayout(Context context, int width, int rows, int start, int end){
        super(context);
        labelView = new LinearLayout(context);
        controlView = new LinearLayout(context);

        this.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        labelView.setLayoutParams(new LinearLayout.LayoutParams(width/2 - 65, LayoutParams.MATCH_PARENT));
        controlView.setLayoutParams(new LinearLayout.LayoutParams(width/2, LayoutParams.MATCH_PARENT));

        //LayoutParams lp = new LayoutParams(width/2, LayoutParams.MATCH_PARENT);
        //lp.setMargins(0,30,0,30);
        //controlView.setLayoutParams(lp);



        labelView.setBackgroundResource(R.drawable.border);
        controlView.setBackgroundResource(R.drawable.border);

        this.setOrientation(LinearLayout.HORIZONTAL);
        labelView.setOrientation(LinearLayout.VERTICAL);
        controlView.setOrientation(LinearLayout.VERTICAL);

        for(int i=start; i<end; i++){
            String label =asset.getStringDataCell(1,i);
            TextView textView = new TextView(context);
            textView.setText(label);
            textView.setPadding(0,30,0,30);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            labelView.addView(textView);
        }

        ///for(int i=start; i<end; i++){
         //   String type = asset.getStringDataCell(6,i);
       // }
        TextBox textBox = new TextBox(context);
        controlView.addView(textBox);
        TextBox textBox2 = new TextBox(context);
        controlView.addView(textBox2);

        addSpinner(context);
        addSpinner2(context);
        hier(context);
        yearBuiltSpinner(context);

        this.addView(labelView);
        this.addView(controlView);
    }

    public void addSpinner(Context context){
        String stateString = asset.getStringDataCell(7,15);
        String[] stateArray = stateString.split(",");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, stateArray);
        Spinner spinner = new Spinner(context);
        spinner.setAdapter(adapter);
        //AutoCompleteTextView actv = new AutoCompleteTextView(context);
        //actv.setThreshold(1);
        //actv.setAdapter(adapter);
        //actv.setTextColor(Color.RED);
        controlView.addView(spinner);
    }

    public void addSpinner2(Context context){
        String stateString = asset.getStringDataCell(7,15);
        String[] stateArray = stateString.split(",");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, stateArray);
        final AutoCompleteTextView actv = new AutoCompleteTextView(context);
        actv.setThreshold(1);
        actv.setEms(3);
        actv.setFilters(new InputFilter[] {new InputFilter.LengthFilter(2)});
        actv.setSingleLine(true);
        actv.setText("CO");
        actv.setAdapter(adapter);
        actv.setTextColor(Color.RED);
        controlView.addView(actv);

        actv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                actv.showDropDown();
            }
        });
    }

    public void hier(final Context context){
        HorizontalScrollView scrollView = new HorizontalScrollView(context);
        final LinearLayout layout = new LinearLayout(context);
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        layout.setOrientation(HORIZONTAL);
        String[] s1 = {"Gas", "Electric"};
        String[] gas = {"Boiler", "Direct Heater", "Furnace", "Steam Boiler"};
        String[] electric = {"Generice Pre 1960", "Generic 1960-1969", "Generic 1970-1974"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,R.layout.support_simple_spinner_dropdown_item,s1);
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(context,R.layout.support_simple_spinner_dropdown_item,gas);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context,R.layout.support_simple_spinner_dropdown_item,electric);

        Spinner spinner1 = new Spinner(context);
        spinner1.setAdapter(adapter);

        layout.addView(spinner1);

        final Spinner spinner2 = new Spinner(context);
        layout.addView(spinner2);

        scrollView.addView(layout);



        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               if(position == 0){
                   spinner2.setAdapter(adapter1);
               }else{
                   spinner2.setAdapter(adapter2);
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        controlView.addView(scrollView);

    }

    public void yearBuiltSpinner(Context context){
        int lowLimit = Integer.parseInt(asset.getStringDataCell(8,29));
        int highLimit = Integer.parseInt(asset.getStringDataCell(9,29));
        int increment = Integer.parseInt(asset.getStringDataCell(10,29));
        int defaultValue = Integer.parseInt(asset.getStringDataCell(12,29));
        Integer[] lowHighArray = new Integer[(highLimit - lowLimit) / increment + 1];
        int index = 0;
        for(int i=lowLimit; i<=highLimit; i=i+increment){
            lowHighArray[index] = i;
            index++;
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(context, R.layout.support_simple_spinner_dropdown_item, lowHighArray);
        final AutoCompleteTextView actv = new AutoCompleteTextView(context);
        actv.setInputType(InputType.TYPE_CLASS_NUMBER);
        actv.setThreshold(2);
        actv.setEms(3);
        actv.setSingleLine(true);
        actv.setFilters(new InputFilter[] {new InputFilter.LengthFilter(4)});
        actv.setText(Integer.toString(defaultValue));
        actv.setAdapter(adapter);
        actv.setTextColor(Color.RED);
        controlView.addView(actv);

        actv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                actv.showDropDown();
            }
        });
    }
}
