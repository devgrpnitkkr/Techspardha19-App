package com.nitkkr.techspardha.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nitkkr.techspardha.R;

import com.nitkkr.techspardha.Category.Cat;

import java.util.ArrayList;
import java.util.List;

import co.ceryle.fitgridview.FitGridAdapter;

public class GrigViewMyAdapter extends FitGridAdapter {


    String cat[];


    private int[] drawables = {
            R.drawable.deafultpic,R.drawable.deafultpic,R.drawable.deafultpic,R.drawable.deafultpic,
            R.drawable.deafultpic,R.drawable.deafultpic,R.drawable.deafultpic,R.drawable.deafultpic,
            R.drawable.deafultpic,R.drawable.deafultpic,R.drawable.deafultpic,R.drawable.deafultpic};

    public Context context;

    GrigViewMyAdapter(Context context) {
        super(context, R.layout.grid_item);
        this.context = context;
    }

    @Override
    public void onBindView(final int position, View itemView) {
        Log.d("testing     ",position+"");

        ImageView iv = (ImageView) itemView.findViewById(R.id.grid_item_iv);
        if(position<12)
            iv.setImageResource(drawables[position]);

        cat = context.getResources().getStringArray(R.array.Categories);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,Cat.class);
                i.putExtra("Category",cat[position]);
                context.startActivity(i);

            }
        });
    }
}
