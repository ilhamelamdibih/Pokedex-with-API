package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> pokemonName ;
    ArrayList<String> image ;
    LayoutInflater inflater;

    public GridAdapter(Context context, ArrayList<String> pokemonName, ArrayList<String> image) {
        this.context = context;
        this.pokemonName = pokemonName;
        this.image = image;
    }

    @Override
    public int getCount() {
        return pokemonName.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = inflater.inflate(R.layout.grid_item,null);

        ImageView imageView = view.findViewById(R.id.gri_image);
        TextView textView = view.findViewById(R.id.item_name);

        Glide.with(view).load(image.get(position)).into(imageView);
        textView.setText(pokemonName.get(position));

        return view;
    }
}