package com.myolin.optimiserandroid;

import android.content.Context;
import android.support.annotation.IntegerRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyActivity extends AppCompatActivity {

    ReadAsset asset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Bundle bundle = new Bundle();

        asset = new ReadAsset(getApplicationContext());

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(false);

        ArrayList<String> s = asset.readColumn(0);
        ArrayList<Integer> num = asset.rowsInFrag(0);

        int start = 3;
        int end;
        for(int i=0; i<s.size(); i++){
            end = start+num.get(i);
            CreateFragment f = new CreateFragment();
            actionBar.addTab(actionBar.newTab().setText(s.get(i)).setTabListener(new MyTabsListener(f,getApplicationContext(), num.get(i), start, end)));
            start = end;
        }
    }

    class MyTabsListener implements ActionBar.TabListener {
        public Fragment fragment;
        Bundle bundle = new Bundle();

        public MyTabsListener(Fragment f, Context context, int row, int start, int end) {
            fragment = f;
            bundle.putInt("Rows", row);
            bundle.putInt("Start", start);
            bundle.putInt("End", end);
            fragment.setArguments(bundle);
        }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.replace(R.id.fragment_container, fragment);
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            ft.remove(fragment);
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }
    }
}
