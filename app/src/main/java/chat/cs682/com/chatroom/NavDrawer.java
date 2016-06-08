package chat.cs682.com.chatroom;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class NavDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String username;
    String to;
    String emailtxt;
    TextView user;
    TextView email;
    JSONArray posts=new JSONArray();
    JSONArray friends=new JSONArray();
    JSONArray groups=new JSONArray();


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public JSONArray getFriends() {
        return friends;
    }

    public void setFriends(JSONArray friends) {
        this.friends = friends;
    }



    public JSONArray getGroups() {
        return groups;
    }

    public void setGroups(JSONArray groups) {
        this.groups = groups;
    }



    public JSONArray getPosts() {
        return posts;
    }

    public void setPosts(JSONArray posts) {
        this.posts = posts;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        user=(TextView)header.findViewById(R.id.username);
        email=(TextView)header.findViewById(R.id.email);
        Bundle extras= getIntent().getExtras();

        username=extras.getString("user");
        emailtxt=extras.getString("email");


        user.setText(username);
        email.setText(emailtxt);
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            GetFriends g = new GetFriends(this, username);
            g.execute();
           /* f = new FragmentFriends();*/
        }else{
            Toast.makeText(this, "Bad Internet Connection........ Please try again later. ", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fm=getSupportFragmentManager();
        Fragment f=new Fragment();
        if (id == R.id.prof) {

        } else if (id == R.id.friends) {
            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                GetFriends g = new GetFriends(this, username);
                g.execute();
                f = new FragmentFriends();
            }else{
                Toast.makeText(this, "Bad Internet Connection........ Please try again later. ", Toast.LENGTH_LONG).show();
            }

        } else if (id == R.id.groups) {

        } else if (id == R.id.find) {

        } else if (id == R.id.exit) {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
        fm.beginTransaction().replace(R.id.content, f).commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
