package com.nitkkr.techspardha.drawers.TeamTech;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.vipulasri.timelineview.TimelineView;
import com.nitkkr.techspardha.Fragments.guestLecture.lecturesPojo.Lectures;
import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.drawers.TeamTech.pojo.Contacts;
import com.nitkkr.techspardha.drawers.TeamTech.pojo.tech1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TimeLineViewHolder> {

    ArrayList<Contacts> list;
    Context context;


    public TeamAdapter(ArrayList<Contacts> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public TimeLineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.team_item, null);
        return new TimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeLineViewHolder holder, int position) {
        final Contacts myListData = list.get(position);


        holder.name.setText(myListData.getSection());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  showDialog(myListData);
            }
        });







    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TimeLineViewHolder extends RecyclerView.ViewHolder {
        public TimelineView mTimelineView;
        TextView name;
        LinearLayout linearLayout;


        public TimeLineViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.nameguest);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.team_lay);
            mTimelineView = (TimelineView) itemView.findViewById(R.id.timeline);
            mTimelineView.initLine(viewType);
        }
    }

//    public void showDialog(Contacts contacts) {
//
//        final Dialog dialog = new Dialog(context);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(true);
//        dialog.setContentView(R.layout.gl_about);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//
//
//        final ImageView image = (ImageView) dialog.findViewById(R.id.expandedImage);
//        final TextView gname = (TextView) dialog.findViewById(R.id.name);
//        final TextView desc = (TextView) dialog.findViewById(R.id.description);
//
//        Glide.with(context)
//                .load(url)
//                .into(image);
//        gname.setText(name);
//        desc.setText(Descrip);
//
//
//        dialog.show();
//    }
}