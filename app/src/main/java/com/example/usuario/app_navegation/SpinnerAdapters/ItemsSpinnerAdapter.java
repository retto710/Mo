package com.example.usuario.app_navegation.SpinnerAdapters;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.usuario.app_navegation.Entities.Item;
import com.example.usuario.app_navegation.Entities.ItemCategory;

import java.util.ArrayList;
import java.util.List;

public class ItemsSpinnerAdapter extends ArrayAdapter<Item> {

    Context context;
    List<Item>values;


    public ItemsSpinnerAdapter(@NonNull Context context, int resource, List<Item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.values = objects;

    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Nullable
    @Override
    public Item getItem(int position) {
        return values.get(position);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView label = (TextView)super.getView(position,convertView,parent);
        label.setTextColor(Color.BLACK);
        label.setText(values.get(position).getDescription());
        label.setTextSize(30);

        return label;

    }

    @Override
    public View getDropDownView(int position,  View convertView,  ViewGroup parent) {


        TextView label = (TextView)super.getDropDownView(position,convertView,parent);
        label.setTextColor(Color.BLACK);
        label.setText(values.get(position).getDescription());
        label.setTextSize(30);

        return label;

    }


}
