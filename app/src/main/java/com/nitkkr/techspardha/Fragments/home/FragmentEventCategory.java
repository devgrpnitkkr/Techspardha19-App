package com.nitkkr.techspardha.Fragments.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nitkkr.techspardha.R;

import co.ceryle.fitgridview.FitGridView;

public class FragmentEventCategory extends Fragment {

    FitGridView gridView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_home,container,false);

        gridView = (FitGridView) view.findViewById(R.id.gridView);
        gridView.setFitGridAdapter(new GridViewMyAdapter(getContext()));

        // you can change grid view size any time. don't forget calling update method.
        changeSize(3, 3);

        return view;
    }

    public void onClick(View v) {
        showAlert();
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        FitGridView gridView = new FitGridView(getContext());
        gridView.setNumColumns(3);
        gridView.setNumRows(4);
        gridView.setFitGridAdapter(new GridViewMyAdapter(getContext()));
        builder.setView(gridView);

        builder.show();
    }

//    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    private int counter = 0;

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.menu_item) {
//            switch (counter) {
//                case 0:
//                    item.setTitle("2*2");
//                    changeSize(2, 2);
//                    break;
//                case 1:
//                    item.setTitle("3*3");
//                    changeSize(3, 3);
//                    break;
//                case 2:
//                    item.setTitle("4*3");
//                    changeSize(4, 3);
//                    break;
//
//            }
//        }
//        counter = ++counter % 3;
//
//        return super.onOptionsItemSelected(item);
//    }

    private void changeSize(int r, int c) {
        gridView.setNumRows(r);
        gridView.setNumColumns(c);
        gridView.update();
    }



}
