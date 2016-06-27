package chat.cs682.com.chatroom;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    EditText user;
    EditText pass;
    Button login;
    String username;
    Context context;
    String email;
    int status=2;

    public void setStatus(int s){
        status=s;
    }

    public void setEmail(String e){
        email=e;
    }

    public int getStatus(){
        return status;
    }

    public String getEmail(){
        return email;
    }

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getApplicationContext();
        setContentView(R.layout.activity_login);
        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //      setSupportActionBar(toolbar);
        user=(EditText)findViewById(R.id.editText);
        pass=(EditText)findViewById(R.id.editText2);
        login=(Button)findViewById(R.id.on1);

       // reset=(Button)findViewById(R.id.visible);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(user.getText().toString().contentEquals("")){
                    Snackbar.make(view, "Please enter the username for your account", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                if(pass.getText().toString().contentEquals("")){
                    Snackbar.make(view, "Please enter the password for your account", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else{
                    username=user.getText().toString();
                    checkUser(user.getText().toString(),pass.getText().toString(),view);
                }
            }

        });

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterPage(view);

            }
        });*/
    }



  /*  public void RegisterPage(View view){
        Intent intent= new Intent(this,Register_activity.class);

        startActivity(intent);
        finish();
    }*/

    public void checkUser(String un,String pw,View view){
        if((!un.contentEquals("") && !pw.contentEquals(""))) {
            GetLogin g = new GetLogin(this, un, pw);
            g.execute();

        }
        else{
            Snackbar.make(view, "Please enter the username and password. Try again!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }


    public void goAhead(){

            if (status == 1) {

                Intent intent = new Intent(this, NavDrawer.class);
            //    username = un;


                intent.putExtra("user", username);
                intent.putExtra("email", email);
                preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                editor=preferences.edit();
                Log.d("Error check", "Paused Nav  :  "+username+"-"+email);
                editor.putString("user", username);
                editor.putString("email",email);
                editor.putString("weight", "4");
                editor.putString("height", "90");
                editor.putBoolean("loggedin", true);
                editor.commit();

                startActivity(intent);
                finish();
            } else if (status == 0) {
                Toast.makeText(this, "The username and password you entered did not match any record, Try Again!", Toast.LENGTH_LONG).show();
            }
        else{
                Toast.makeText(this, "Try Again!", Toast.LENGTH_LONG).show();
            }


    }




    @Override
    public void onRestart(){
        super.onRestart();
        Log.d("myApp", "Error: Main restart");
        preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username=preferences.getString("user", "");
        String emailtxt=preferences.getString("email", "");
        String height=preferences.getString("height", "4");
        String weight=preferences.getString("weight", "90");
        Boolean log=preferences.getBoolean("loggedin",false);
        editor=preferences.edit();
        editor.clear();
        editor.commit();
        if(log){
            Intent intent=new Intent(this,NavDrawer.class);

            //   Bundle args=new Bundle();
            //  args.putString("user",user);
            //     intent.
            intent.putExtra("user", username);
            intent.putExtra("email", emailtxt);
            intent.putExtra("height",height);
            intent.putExtra("weight", weight);


            startActivity(intent);
            finish();
        }


    }


    @Override
    public void onStart(){
        super.onStart();
        Log.d("myApp", "Error: Main start");
        context=getApplicationContext();
        preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String user=preferences.getString("user","user");
        Boolean log=preferences.getBoolean("loggedin",false);

        if(log && !user.contentEquals("user")){
            Intent intent=new Intent(this,NavDrawer.class);
            intent.putExtra("user",user );
            intent.putExtra("email", preferences.getString("email","raj@gmail.com"));
            intent.putExtra("height", "4");
            intent.putExtra("weight", "90");

            //   Bundle args=new Bundle();
            //  args.putString("user",user);
            //     intent.

            // intent.putExtra("email","raj@gmail.com");



            startActivity(intent);
            finish();
        }


    }


}
