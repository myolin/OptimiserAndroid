package com.myolin.optimiserandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CreateFragment extends Fragment {

    View parent_View;

    //CustomLayout customLayout;
    ScrollLayout scrollLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        int rows = bundle.getInt("Rows");
        int start = bundle.getInt("Start");
        int end = bundle.getInt("End");
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        scrollLayout = new ScrollLayout(getContext(), width, rows, start, end);
        parent_View = scrollLayout;
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return parent_View;
    }
}
