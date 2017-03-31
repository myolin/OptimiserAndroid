package com.myolin.optimiserandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.RequiresPermission;
import android.text.InputFilter;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by mzlmy on 3/14/2017.
 */

public class CustomLayout extends LinearLayout {

    LinearLayout labelView;
    LinearLayout controlView;
    //ReadAsset asset = new ReadAsset(getContext());
    ReadAsset asset;
    ImageView imageView;

    View[] view = new View[2];

    public CustomLayout(final Context context, int width, int rows, int start, int end, ReadAsset asset){
        super(context);
        this.asset = asset;
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
            //textView.setPadding(0,30,0,30);
            textView.setPadding(0,35,0,35);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            labelView.addView(textView);

            final String tooltip = asset.getStringDataCell(24,i);
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, tooltip, Toast.LENGTH_SHORT).show();
                }
            });

        }

        for(int i=start; i<end; i++) {
            String label = asset.getStringDataCell(6, i);
            if (label.equals("TextBox")) {
                TextBox textBox = new TextBox(context);
                controlView.addView(textBox);
            }else if(label.equals("Date")) {
                Date date = new Date(context);
                controlView.addView(date);
            }else if(label.equals("Memo")) {
                Memo memo = new Memo(context);
                controlView.addView(memo);
            }else if(label.equals("CheckBox")){
                CheckBox checkBox = new CheckBox(context);
                checkBox.setPadding(0,35,0,35);
                controlView.addView(checkBox);
            } else if (label.equals("ListBox")) {
                String data = asset.getStringDataCell(7, i);
                String[] dataArray = data.split(",");
                Listbox listbox = new Listbox(context, dataArray);
                controlView.addView(listbox);
            }else if(label.equals("DualListBox") || label.equals("NearDualListBox")) {
                String data = asset.getStringDataCell(11, i);
                String[] dataArray = data.split(",");
                Listbox listbox = new Listbox(context, dataArray);
                controlView.addView(listbox);
            }else if(label.equals("ComboBox")){
                String data = asset.getStringDataCell(7,i);
                String[] dataArray = new String[10];
                //String defaultValue = asset.getStringDataCell(12,i);
                String defaultValue = asset.getStringDataCell(21,i);
                ComboBox comboBox = new ComboBox(context, dataArray, defaultValue);
                controlView.addView(comboBox);
            } else if (label.equals("Spinners")) {
                int lowLimit = Integer.parseInt(asset.getStringDataCell(8, i));
                int highLimit = Integer.parseInt(asset.getStringDataCell(9, i));
                int increment = Integer.parseInt(asset.getStringDataCell(10, i));
                //int defaultValue = Integer.parseInt(asset.getStringDataCell(12, i));
                int defaultValue = Integer.parseInt(asset.getStringDataCell(21,i));
                Integer[] dataArray = new Integer[(highLimit - lowLimit) / increment + 1];
                int index = 0;
                for (int j = lowLimit; j <= highLimit; j = j + increment) {
                    dataArray[index] = j;
                    index++;
                }
                Spinwheel spinwheel = new Spinwheel(context, dataArray, defaultValue);
                controlView.addView(spinwheel);
            } else {
                TextView textView = new TextView(context);
                textView.setText("CONTROL");
                textView.setPadding(0,30,0,30);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                controlView.addView(textView);
            }
        }

        //hier(context);
        hier2(context);
        //yearBuiltSpinner(context);
        addImage(context);

        final TextView textView = new TextView(context);
        textView.setBackgroundResource(R.drawable.border);
        controlView.addView(textView);
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                numPicker(context, textView);
            }
        });


        this.addView(labelView);
        this.addView(controlView);
    }

    //not used
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

    //not used
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

    //not used
    public void yearBuiltSpinner(Context context){
        int lowLimit = Integer.parseInt(asset.getStringDataCell(8,29));
        int highLimit = Integer.parseInt(asset.getStringDataCell(9,29));
        int increment = Integer.parseInt(asset.getStringDataCell(10,29));
        int defaultValue = Integer.parseInt(asset.getStringDataCell(21,29));
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

    public void addImage(Context context){
        imageView = new ImageView(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,300);
        lp.setMargins(0,35,0,35);
        imageView.setLayoutParams(lp);
        imageView.setBackgroundResource(R.drawable.border);
        imageView.setPadding(0,35,0,35);
        controlView.addView(imageView);
    }

    public ImageView getImageView(){
        return imageView;
    }

    public void numPicker(Context context, final TextView textView){
        final NumberPicker picker = new NumberPicker(context);
        picker.setMinValue(1);
        picker.setMaxValue(50);

        final FrameLayout layout = new FrameLayout(context);
        layout.addView(picker, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER));

        new AlertDialog.Builder(context).setView(layout).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                picker.clearFocus();
                int num = picker.getValue();
                textView.setText(Integer.toString(num));
            }
        }).setNegativeButton("Cancel", null).show();

    }

    public void hier2(final Context context){
        HorizontalScrollView scrollView = new HorizontalScrollView(context);
        final LinearLayout layout = new LinearLayout(context);
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        layout.setOrientation(VERTICAL);
        //String[] s1 = {"Gas", "Electric"};
        //String[] gas = {"Boiler", "Direct Heater", "Furnace", "Steam Boiler"};
        //String[] electric = {"Generice Pre 1960", "Generic 1960-1969", "Generic 1970-1974"};
        String fuel = asset.getStringDataCell(11,169);
        String[] fuelArray = fuel.split(",");
        String type1 = asset.getStringDataCell(12,170);
        String[] type1Array = type1.split(",");
        String type2 = asset.getStringDataCell(13,170);
        String[] type2Array = type2.split(",");
        String type3 = asset.getStringDataCell(14,170);
        String[] type3Array = type3.split(",");
        String type4 = asset.getStringDataCell(15,170);
        String[] type4Array = type4.split(",");
        String type5 = asset.getStringDataCell(16,170);
        String[] type5Array = type5.split(",");
        String type6 = asset.getStringDataCell(17,170);
        String[] type6Array = type6.split(",");
        String type7 = asset.getStringDataCell(18,170);
        String[] type7Array = type7.split(",");
        String type8 = asset.getStringDataCell(19,170);
        String[] type8Array = type8.split(",");
        String type9 = asset.getStringDataCell(20,170);
        String[] type9Array = type9.split(",");
        String[] none = {"None"};


        ArrayAdapter<String> fueladapter = new ArrayAdapter<String>(context,R.layout.support_simple_spinner_dropdown_item,fuelArray);
        final ArrayAdapter<String> type1Adapter = new ArrayAdapter<String>(context,R.layout.support_simple_spinner_dropdown_item,type1Array);
        final ArrayAdapter<String> type2Adapter = new ArrayAdapter<String>(context,R.layout.support_simple_spinner_dropdown_item,type2Array);
        final ArrayAdapter<String> type3Adapter = new ArrayAdapter<String>(context,R.layout.support_simple_spinner_dropdown_item,type3Array);
        final ArrayAdapter<String> type4Adapter = new ArrayAdapter<String>(context,R.layout.support_simple_spinner_dropdown_item,type4Array);
        final ArrayAdapter<String> type5Adapter = new ArrayAdapter<String>(context,R.layout.support_simple_spinner_dropdown_item,type5Array);
        final ArrayAdapter<String> type6Adapter = new ArrayAdapter<String>(context,R.layout.support_simple_spinner_dropdown_item,type6Array);
        final ArrayAdapter<String> type7Adapter = new ArrayAdapter<String>(context,R.layout.support_simple_spinner_dropdown_item,type7Array);
        final ArrayAdapter<String> type8Adapter = new ArrayAdapter<String>(context,R.layout.support_simple_spinner_dropdown_item,type8Array);
        final ArrayAdapter<String> type9Adapter = new ArrayAdapter<String>(context,R.layout.support_simple_spinner_dropdown_item,type9Array);
        final ArrayAdapter<String> noneAdapter = new ArrayAdapter<String>(context,R.layout.support_simple_spinner_dropdown_item,none);

        String system = asset.getStringDataCell(11,171);
        String[] systemArray = system.split(",");
        final ArrayAdapter<String> systemAdapter = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, systemArray);

        final Spinner spinner1 = new Spinner(context);
        spinner1.setAdapter(fueladapter);

        layout.addView(spinner1);

        final Spinner spinner2 = new Spinner(context);
        spinner2.setAdapter(type1Adapter);
        layout.addView(spinner2);

        final Spinner spinner3 = new Spinner(context);
        spinner3.setAdapter(systemAdapter);
        layout.addView(spinner3);

        scrollView.addView(layout);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        spinner2.setAdapter(type1Adapter);
                        break;
                    case 1:
                        spinner2.setAdapter(type2Adapter);
                        break;
                    case 2:
                        spinner2.setAdapter(type3Adapter);
                        break;
                    case 3:
                        spinner2.setAdapter(type4Adapter);
                        break;
                    case 4:
                        spinner2.setAdapter(type5Adapter);
                        break;
                    case 5:
                        spinner2.setAdapter(type6Adapter);
                        break;
                    case 6:
                        spinner2.setAdapter(type7Adapter);
                        break;
                    case 7:
                        spinner2.setAdapter(type8Adapter);
                        break;
                    case 8:
                        spinner2.setAdapter(type9Adapter);
                        break;
                    case 9:
                        spinner2.setAdapter(noneAdapter);
                        spinner3.setAdapter(noneAdapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner3.setAdapter(systemAdapter);
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        controlView.addView(scrollView);

    }

}
