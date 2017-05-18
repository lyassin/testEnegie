package demo.energie.fr.energiese.ui;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;

import demo.energie.fr.energiese.R;
import demo.energie.fr.energiese.helper.SQLiteHandler;
import demo.energie.fr.energiese.helper.SessionManager;

/**
 * Created by yassin on 18/05/2017.
 */

public class UserAccountActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private SessionManager session;
    private SQLiteHandler db;
    HashMap<String, String> data;
    private  String id;
    private String email;
    // Make sure to be using android.support.v7.app.ActionBarDrawerToggle version.
    // The android.support.v4.app.ActionBarDrawerToggle has been deprecated.
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_acount);

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerToggle = setupDrawerToggle();
        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        db = new SQLiteHandler(getApplicationContext());
        data = db.getUserDetails();
        session = new SessionManager(getApplicationContext());
        id = data.get("id");
        email = data.get("email");
        if (!session.isLoggedIn()) {
            logoutUser();
        }
    }

    
    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked

        switch(menuItem.getItemId()) {
            case R.id.email:
                Toast.makeText(getApplicationContext(), email, Toast.LENGTH_LONG).show();
                break;
            case R.id.id:
                Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();
                break;
            case R.id.deconnexion:
                Toast.makeText(getApplicationContext(), "Déconexion", Toast.LENGTH_LONG).show();
                logoutUser();
                break;

        }

        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(UserAccountActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}