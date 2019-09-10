package com.nitkkr.techspardha.root;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
//import com.nitkkr.techspardha.FragmentSponsership;
import com.nitkkr.techspardha.Fragments.food.FragmentFood;
import com.nitkkr.techspardha.Fragments.guestLecture.FragmentGuestLecture;
import com.nitkkr.techspardha.Fragments.home.FragmentEventCategory;
import com.nitkkr.techspardha.Fragments.sponsership.FragmentSponsership;
import com.nitkkr.techspardha.drawers.AboutUs.AboutUs;
import com.nitkkr.techspardha.drawers.LeftDrawerProfile;
import com.nitkkr.techspardha.R;
import com.nitkkr.techspardha.drawers.developers.Developers;


public class RootActivity extends AppCompatActivity {

	private DrawerLayout drawer;
	ActionBarDrawerToggle toggle;
	private NavigationView navLayout;
    GoogleSignInClient mGoogleSignInClient;
    LinearLayout logout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		if(mGoogleSignInClient.)
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account == null) {
            finish();
            System.exit(0);
        }

		drawer = findViewById(R.id.main_drawer_layout);
        logout = findViewById(R.id.nav_logout);
		NavigationView navigationView = findViewById(R.id.nav_view);
		final View navHeader = navigationView.getHeaderView(0);
//		final View navHeader = navigationView.inflateHeaderView(R.layout.nav_header);
		ImageView navHeaderPic = navHeader.findViewById(R.id.nav_header_image);
		TextView name = navHeader.findViewById(R.id.nav_name);
		Uri personPhoto = account.getPhotoUrl();
		Log.d("testing...",personPhoto+"      "+navHeaderPic);
		Glide.with(this).load(personPhoto).into(navHeaderPic);
		name.setText(account.getDisplayName());

		// Configure sign-in to request the user's ID, email address, and basic
		// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
		GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
				.requestEmail()
				.build();

		// Build a GoogleSignInClient with the options specified by gso.
		mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


		navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
				switch (menuItem.getItemId()){
					case R.id.drawer_profile:
//                        Toast.makeText(getApplicationContext(),"clicled",Toast.LENGTH_LONG).show();
						Intent intent = new Intent(getApplicationContext(), LeftDrawerProfile.class);
						startActivity(intent);
						break;
					case R.id.drawer_developers:
//						Toast.makeText(getApplicationContext(),"clicled",Toast.LENGTH_LONG).show();
						intent = new Intent(getApplicationContext(), Developers.class);
						startActivity(intent);
						break;
					case R.id.drawer_aboutus:
//						Toast.makeText(getApplicationContext(),"clicled",Toast.LENGTH_LONG).show();
						intent = new Intent(getApplicationContext(), AboutUs.class);
						startActivity(intent);
						break;
//					case R.id.drawer_MyEvents:
//						getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//								UpdateProfile).commit();
				}
				drawer.closeDrawer(GravityCompat.START);
				return true;
			}
		});

		toggle = new ActionBarDrawerToggle(this,drawer,
				R.string.navigation_drawer_open,R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		BottomNavigationView bottomNavigationView = findViewById(R.id.main_bottom_navigation_view);
		bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
		getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container,
				new FragmentEventCategory()).commit();
		getSupportActionBar().setTitle("Home");

		logout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				signOut();
			}
		});

	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		if(toggle.onOptionsItemSelected(item))
			return true;
		return super.onOptionsItemSelected(item);
	}

	private BottomNavigationView.OnNavigationItemSelectedListener navListener =
			new BottomNavigationView.OnNavigationItemSelectedListener() {
				@Override
				public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

					Fragment selectedFragment = null;

					switch (menuItem.getItemId()){
						case R.id.nav_home:
							selectedFragment = new FragmentEventCategory();
							getSupportActionBar().setTitle("Home");
							break;
						case R.id.nav_sponsi:
							selectedFragment = new FragmentSponsership();
							getSupportActionBar().setTitle("Sponsors");
							break;
						case R.id.nav_food:
							selectedFragment = new FragmentFood();
							getSupportActionBar().setTitle("Food");
							break;
						case R.id.nav_GL:
							selectedFragment = new FragmentGuestLecture();
							getSupportActionBar().setTitle("Guest Lecture");
							break;
					}

					getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container,
							selectedFragment).commit();

					return true;

//					return false;
				}
			};
	private void signOut() {
		mGoogleSignInClient.signOut()
				.addOnCompleteListener(this, new OnCompleteListener<Void>() {
					@Override
					public void onComplete(@NonNull Task<Void> task) {
						Toast.makeText(RootActivity.this,"Successfully signed out",Toast.LENGTH_SHORT).show();
						startActivity(new Intent(RootActivity.this, UserLogin.class));
						finish();
					}
				});
	}
}
