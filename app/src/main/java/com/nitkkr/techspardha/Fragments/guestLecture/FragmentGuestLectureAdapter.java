package com.nitkkr.techspardha.Fragments.guestLecture;

import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.events.categoryPojo.Data;
import com.nitkkr.techspardha.guestLecture.lecturesPojo.Lectures;
//import com.yarolegovich.discretescrollview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yarolegovich on 07.03.2017.
 */

public class FragmentGuestLectureAdapter extends RecyclerView.Adapter<FragmentGuestLectureAdapter.ViewHolder> {
    List<Lectures> lst;
    TextView name,date,time,desc;
    List<ImageView> img;



//    private static final Uri URL_TAYA_BEHANCE = Uri.parse("https://www.behance.net/yurkivt");
//    private static final Uri URL_SHOP_PHOTOS = Uri.parse("https://herriottgrace.com/collections/all");
//    private static final Uri URL_CITY_ICONS = Uri.parse("https://www.flaticon.com");
//    private static final Uri URL_APP_REPO = Uri.parse("https://github.com/yarolegovich/DiscreteScrollView");


        public FragmentGuestLectureAdapter(List<Lectures> lst,TextView name,TextView date,TextView time,TextView desc) {
            this.lst=lst; this.name=name;this.date=date;this.time=time;this.desc=desc;
        }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_shop_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Glide.with(holder.itemView.getContext())
                .load(lst.get(position).getImageUrl())
                .into(holder.image);
        holder.image.setPadding(20,20,20,20);

        //holder.name.setText(lst.get(position).getName());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                for(int i=0;i<5;i++){
//                    Log.d("position",i+""+position);
//                    if(i==position)
//                        view.setBackgroundResource(R.drawable.buttonbackground);
////                    else
////                        img.get(i).setBackgroundResource(R.drawable.imagebackground);
//                }
                Log.d("position",""+position);
                name.setText(lst.get(position).getName());
                date.setText(lst.get(position).getDate());
                time.setText(lst.get(position).getTime());
                desc.setText(lst.get(position).getDesc());
            }
        });

    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            image.setBackgroundResource(R.drawable.imagebackground);
//            if(image!=null)
//                img.add(image);
        }
    }
}
