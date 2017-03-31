package com.myolin.optimiserandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class CreateFragment extends Fragment {

    View parent_View;

    //CustomLayout customLayout;
    ScrollLayout scrollLayout;

    private static int TAKE_PICTURE = 1;
    ImageView imageView;

    ReadAsset asset;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        int rows = bundle.getInt("Rows");
        int start = bundle.getInt("Start");
        int end = bundle.getInt("End");
        asset = bundle.getParcelable("Asset");
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        scrollLayout = new ScrollLayout(getContext(), width, rows, start, end, asset);
        parent_View = scrollLayout;
        imageView = scrollLayout.getImageView();
        init();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return parent_View;
    }

    public void init(){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, TAKE_PICTURE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == TAKE_PICTURE && resultCode == Activity.RESULT_OK){
            Bitmap bmp = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
