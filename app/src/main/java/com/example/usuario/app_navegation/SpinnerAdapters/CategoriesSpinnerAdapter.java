package com.example.usuario.app_navegation.SpinnerAdapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.usuario.app_navegation.Entities.ItemCategory;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CategoriesSpinnerAdapter extends ArrayAdapter<ItemCategory>{

    Context context;
    ArrayList<ItemCategory>values;

    public CategoriesSpinnerAdapter(Context context, int resource,  ArrayList<ItemCategory> objects) {
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
    public ItemCategory getItem(int position) {
        return values.get(position);
    }
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

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
