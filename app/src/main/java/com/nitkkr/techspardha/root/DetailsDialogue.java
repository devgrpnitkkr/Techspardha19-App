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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.ListAdapter;

import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.retrofit.Interface;
import com.nitkkr.techspardha.retrofit.RetroClient;
import com.nitkkr.techspardha.root.db.userDataStore;
import com.nitkkr.techspardha.root.registerPojo.RegisterData;
import com.nitkkr.techspardha.root.userPojo.Udata;
import com.nitkkr.techspardha.root.userPojo.userInfo;
import com.reginald.editspinner.EditSpinner;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class DetailsDialogue {
    final List<userInfo> lst=new ArrayList<>();
    Boolean isOnboarded=false;

    public void showDialog(final Activity activity, final String email){

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.user_detail);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        final EditText phone=dialog.findViewById(R.id.mobile);
        final EditText college=dialog.findViewById(R.id.college);
        final EditSpinner year = dialog.findViewById(R.id.year);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,android.R.layout.simple_spinner_dropdown_item,
                dialog.getContext().getApplicationContext().getResources().getStringArray(R.array.years));
        year.setAdapter(adapter);


        Button register = (Button) dialog.findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("details",phone.getText().toString());



                Interface service = RetroClient.getClient().create(Interface.class);






                    service .registerUser(String.valueOf(phone.getText()),String.valueOf(college.getText()),String.valueOf(year.getText()),email)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<RegisterData>() {

                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(RegisterData udata) {

                                    lst.add(udata.getInformation());

                                }

                                @Override
                                public void onError(Throwable e) {

                                    Log.i("Code", e.getMessage());

                                }

                                @Override
                                public void onComplete() {


                                    if (lst.get(0).getOnBoard().equals("true")){
                                        Log.i("OnCLean", lst.get(0).getOnBoard());
                                        userDataStore userData=userDataStore.getInstance(activity.getApplicationContext());
                                        userData.saveData(lst.get(0),"true");
                                        userData.changeState("true");
                                        isOnboarded=true;
                                    }

                                    dialog.cancel();

                                }
                            });
                }







        });

        dialog.show();

    }

    public boolean getResult(){
        return isOnboarded;
    }
}
