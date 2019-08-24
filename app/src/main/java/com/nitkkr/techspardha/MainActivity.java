package com.nitkkr.techspardha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

	private DrawerLayout drawer;
	ActionBarDrawerToggle toggle;
	private NavigationView navLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		drawer = findViewById(R.id.main_drawer_layout);
		NavigationView navigationView = findViewById(R.id.nav_view);


//		navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//			@Override
//			public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//				switch (menuItem.getItemId()){
//					case R.id.nav_home:
//						getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//								Home).commit();
//						populate();
//						break;
//					case R.id.nav_upadteprofile:
//						getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//								UpdateProfile).commit();
//				}
//				drawer.closeDrawer(GravityCompat.START);
//				return true;
//			}
//		});

		toggle = new ActionBarDrawerToggle(this,drawer,
				R.string.navigation_drawer_open,R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		BottomNavigationView bottomNavigationView = findViewById(R.id.main_bottom_navigation_view);
		bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
		getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container,
				new FragmentHome()).commit();

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
							selectedFragment = new FragmentHome();
							break;
						case R.id.nav_sponsi:
							selectedFragment = new FragmentSponsership();
							break;
						case R.id.nav_food:
							selectedFragment = new FragmentFood();
							break;
					}

					getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container,
							selectedFragment).commit();

					return true;

//					return false;
				}
			};
}
