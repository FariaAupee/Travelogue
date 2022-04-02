package com.example.mylogin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Request_Adapter extends ArrayAdapter<BookingClass> {

    private Activity context;
    private List<BookingClass>bookinglist;

    public Request_Adapter(Context context, List<BookingClass> bookinglist) {
        super(context,R.layout.show_request_layout,bookinglist);
        this.context = (Activity) context;
        this.bookinglist = bookinglist;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        @SuppressLint({"InflateParams", "ViewHolder"}) View view;
        view = inflater.inflate(R.layout.show_request_layout,null,true);

        BookingClass bookingClass=bookinglist.get(position);

        TextView t1=view.findViewById(R.id.place);
        TextView t2=view.findViewById(R.id.name);
        TextView t3=view.findViewById(R.id.mail);
        TextView t4=view.findViewById(R.id.date);
        TextView t5=view.findViewById(R.id.people);

        t1.setText("Chosen destination: "+bookingClass.getPlace());
        t2.setText("Name: "+bookingClass.getName());
        t3.setText("Email: "+bookingClass.getMail());
        t4.setText("Date: "+bookingClass.getDate());
        t5.setText("Number of people: "+bookingClass.getPeople());

        return view;
    }
}
