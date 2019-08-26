package com.nitkkr.techspardha.Fragments.guestLecture;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nitkkr.techspardha.R;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;
import java.util.List;

public class FragmentGuestLecture extends Fragment {



    List<String> lst=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_guest_lecture,container,false);

        lst.add("https://res.cloudinary.com/demo/image/upload/w_200/lady.jpg");
        lst.add("https://res.cloudinary.com/demo/image/upload/sample.jpg");
        lst.add("https://res.cloudinary.com/demo/image/fetch/w_400,f_png/https://upload.wikimedia.org/wikipedia/commons/4/46/Jennifer_Lawrence_at_the_83rd_Academy_Awards.jpg");
        lst.add("https://res.cloudinary.com/demo/image/upload/w_200/lady.jpg");
        lst.add("https://res.cloudinary.com/demo/image/upload/c_imagga_crop/family_bench.jpg");
        lst.add("https://firebasestorage.googleapis.com/v0/b/sports-2f00f.appspot.com/o/images%2F7e65a871-0a64-4fd9-9b22-33a4673bf62f?alt=media&token=d0b9f4a9-6e94-4988-b591-f7048413285b");

        DiscreteScrollView scrollView = view.findViewById(R.id.picker);
        scrollView.setAdapter(new FragmentGuestLectureAdapter(lst));

        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }


}
