package com.testing.admin.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

/**
 * Created by admin on 10/26/2016.
 */
public class CustomGrid extends BaseAdapter {
    private Context mContext;
    private final String[] web;

    public CustomGrid(Context c, String[] web ) {
        mContext = c;
        this.web = web;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return web.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.item, null);
            CheckBox textView = (CheckBox) grid.findViewById(R.id.grid_item_label);
            textView.setText(web[position]);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}
