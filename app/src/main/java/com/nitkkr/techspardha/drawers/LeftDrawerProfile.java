package com.nitkkr.techspardha.drawers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.root.DetailsDialogue;
import com.nitkkr.techspardha.root.RootActivity;
import com.nitkkr.techspardha.root.UserLogin;
import com.nitkkr.techspardha.root.db.userDataStore;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class LeftDrawerProfile extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;
    Button sign_out;
    TextView nameTV;
    TextView emailTV;
    TextView phone;
    TextView college;
    TextView branch;
    TextView year;
    ImageView photoIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setTitle("Profile");




        userDataStore userData=userDataStore.getInstance(this);
        if(!userData.getState()){
            DetailsDialogue detailsDialogue=new DetailsDialogue();

            detailsDialogue.showDialog(LeftDrawerProfile.this,userData.getData().getInformation().getEmail());
        }


        sign_out = findViewById(R.id.logout);
        nameTV = findViewById(R.id.name);
        emailTV = findViewById(R.id.email);
        photoIV = findViewById(R.id.pic);
        phone=findViewById(R.id.phone);
        college=findViewById(R.id.college);
        branch=findViewById(R.id.branch);
        year=findViewById(R.id.year);

        if(userData.getData().getInformation().getName()!=NULL){
            nameTV.setText(userData.getData().getInformation().getName());
        }
        if(userData.getData().getInformation().getEmail()!=NULL){
            emailTV.setText(userData.getData().getInformation().getEmail());
        }
        if(userData.getData().getInformation().getPhone()!=NULL){
            phone.setText(userData.getData().getInformation().getPhone());
        }
        if(userData.getData().getInformation().getCollege()!=NULL){
            college.setText(userData.getData().getInformation().getCollege());
        }
        if(userData.getData().getInformation().getYear()!=NULL){
            year.setText(userData.getData().getInformation().getYear());
        }
        if (userData.getData().getInformation().getPicture()!=NULL){
            Glide.with(this)
                    .load(userData.getData().getInformation().getPicture())
                    .into(photoIV);
        }








    }


}