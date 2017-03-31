package com.myolin.optimiserandroid;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ReadAsset implements Parcelable{
    protected ReadAsset(Parcel in) {
    }

    public static final Creator<ReadAsset> CREATOR = new Creator<ReadAsset>() {
        @Override
        public ReadAsset createFromParcel(Parcel in) {
            return new ReadAsset(in);
        }

        @Override
        public ReadAsset[] newArray(int size) {
            return new ReadAsset[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    Sheet workSheet;

    public ReadAsset(Context context){
        workSheet = getWorkSheet(context);
    }

    public Sheet getWorkSheet(Context context) {
        Sheet s = null;
        try {
            AssetManager am = context.getAssets();
            InputStream is = am.open("newmobiletakeoff.XLS");
            Workbook wb = Workbook.getWorkbook(is);
            s = wb.getSheet(1);
        } catch (Exception e) {

        }
        return s;
    }

    public String getStringDataCell(int col, int row){
        String result = "";
        Cell cell = workSheet.getCell(col,row);
        result = result + cell.getContents();
        return result;
    }

    public ArrayList<String> readColumn(int col){
        ArrayList<String> result = new ArrayList<String>();
        int numOfRows = workSheet.getRows();
        for(int row=3; row<numOfRows; row++){
            Cell cell = workSheet.getCell(col,row);
            if(cell.getContents().equals("end")){
                break;
            }
            if(!cell.getContents().equals("")) {
                result.add(cell.getContents());
            }
        }
        return result;
    }

    public ArrayList<Integer> rowsInFrag(int col){
        ArrayList<Integer> result = new ArrayList<Integer>();
        int numOfRows = workSheet.getRows();

        int count = 1;
        for(int row=4; row<numOfRows; row++){
            Cell cell = workSheet.getCell(col,row);
            if(!cell.getContents().equals("")){
                result.add(count);
                count=0;
            }
            count++;
        }
        return result;

    }
}
