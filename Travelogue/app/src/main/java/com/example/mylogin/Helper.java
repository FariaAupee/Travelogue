package com.example.mylogin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Helper extends BaseAdapter {

    private LayoutInflater minflater;
    String[] places;
    String[] packages;
    Context context;

    public Helper(Context c, String[] i, String[] d)
    {
        this.context=c;
        places=i;
        packages=d;
        minflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return places.length;
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v=minflater.inflate(R.layout.my_layout,parent,false);

        TextView placetextview = (TextView) v.findViewById(R.id.listplace);
        TextView packtextview = (TextView) v.findViewById(R.id.listpack);

        String place=places[position];
        String pack=packages[position];

        placetextview.setText(place);
        packtextview.setText(pack);

        return v;
    }
}
