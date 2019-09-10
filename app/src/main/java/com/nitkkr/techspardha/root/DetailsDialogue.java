package com.nitkkr.techspardha.root;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.retrofit.Interface;
import com.nitkkr.techspardha.retrofit.RetroClient;
import com.nitkkr.techspardha.root.db.userDataStore;
import com.nitkkr.techspardha.root.registerPojo.RegisterData;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class DetailsDialogue {
    List<RegisterData> lst=new ArrayList<>();

    public void showDialog(final Activity activity, final String email){

        final Dialog dialog = new Dialog(activity
        );
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.user_detail);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        final MaterialEditText phone=(MaterialEditText)dialog.findViewById(R.id.mobile);
        final MaterialEditText college=(MaterialEditText)dialog.findViewById(R.id.college);
        final MaterialEditText branch=(MaterialEditText)dialog.findViewById(R.id.branch);
        final MaterialEditText year=(MaterialEditText)dialog.findViewById(R.id.year);

        Button register = (Button) dialog.findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("details",phone.getText().toString());



                Interface service = RetroClient.getClient().create(Interface.class);

                if((String.valueOf(phone.getText()) !=NULL)  &&
                        (String.valueOf(college.getText()) !=NULL) &&
                        (String.valueOf(branch.getText()) !=NULL) &&
                        (String.valueOf(year.getText()) !=NULL)) {



                    service
                            .registerUser(String.valueOf(phone.getText()),String.valueOf(college.getText()),String.valueOf(year.getText()),String.valueOf(branch.getText()),email)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<RegisterData>() {

                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(RegisterData udata) {

                                    lst.add(udata);

                                }

                                @Override
                                public void onError(Throwable e) {

                                    Log.i("Code", e.getMessage());

                                }

                                @Override
                                public void onComplete() {

                                    Log.i("succs", lst.get(0).getInformation().getCollege());
                                    dialog.cancel();

                                }
                            });
                }







            }
        });

        dialog.show();
    }
}
