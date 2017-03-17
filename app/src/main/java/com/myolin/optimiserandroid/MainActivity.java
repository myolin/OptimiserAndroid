package com.myolin.optimiserandroid;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Fragment auditorFragment;
    private Fragment ownerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(false);

        ActionBar.Tab auditorTab = actionBar.newTab().setText(getString(R.string.ui_tabname_auditor));
        ActionBar.Tab ownerTab = actionBar.newTab().setText(getString(R.string.ui_tabname_owner));

        //auditorFragment = new AuditorFragment();
        //ownerFragment = new OwnerFragment();

        auditorTab.setTabListener(new MyTabsListener(auditorFragment, getApplicationContext()));
        ownerTab.setTabListener(new MyTabsListener(ownerFragment, getApplicationContext()));

        actionBar.addTab(auditorTab);
        actionBar.addTab(ownerTab);
    }

    class MyTabsListener implements ActionBar.TabListener{
        public Fragment fragment;

        public MyTabsListener(Fragment f, Context context){
            fragment = f;
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
