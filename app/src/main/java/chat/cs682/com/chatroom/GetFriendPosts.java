package chat.cs682.com.chatroom;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.concurrent.Semaphore;

/**
 * Created by rajesh on 4/13/2016.
 */
public class GetFriendPosts extends AsyncTask<String,Void,String> {
    private static String TAG = "myApp";
    private  ProgressDialog progressDialog;
    private Context mContext;
    private String username;
    private String tofriend;
  //  private View view;
    // private Semaphore s=new Semaphore(0);

    GetFriendPosts(Context context,String user,String tofriend){

        this.mContext = context;
        this.username=user;
        this.tofriend=tofriend;
   //     this.view=v;
    }

    @Override

    protected String doInBackground(String... params) {

        String result = "";

        try {
            Calendar rightnow= Calendar.getInstance();

            URL url = new URL("http://"+this.mContext.getResources().getString(R.string.IP)+":3000/users/api/change?from="+this.username+"&name="+this.tofriend+"");

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            Log.v("Error check","1");
            InputStream responseStream = urlConnection.getInputStream();
            if(responseStream!=null) {
                InputStream in = new BufferedInputStream(responseStream);

                StringBuffer sb = new StringBuffer();

                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                String read;

                while ((read = br.readLine()) != null) {

                    sb.append(read);

                }

                br.close();

                result = sb.toString();
            }
        }catch (MalformedURLException e) {
            // Replace this with your exception handling
        /*    e.printStackTrace();
            progressDialog.setMessage("Could not connect to server");
            progressDialog.cancel();*/

        } catch (Exception e) {

            Log.d(TAG, "Error: " + e.toString());
          /*  progressDialog.setMessage("Could not connect to server");
            progressDialog.cancel();
*/

        }

        return result;

    }



    @Override
    protected void onPreExecute() {
       /* try {
            s.acquire();
        }catch(InterruptedException e){

        }*/
        ((NavDrawer) this.mContext).setPosts(new JSONArray());
        progressDialog = new ProgressDialog(
                mContext);
        progressDialog.setMessage("Getting Posts......");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    protected void onPostExecute(String data){
        JSONObject o = new JSONObject();


        try {
            if(!data.contentEquals("")) {
                o = new JSONObject(data);
                JSONArray f=o.optJSONArray("posts");

                ((NavDrawer) this.mContext).setPosts(f);

            }
            else{
                o = new JSONObject();
                o.put("posts", new JSONArray());


            }
        }
        catch(JSONException e){
            Log.v(TAG, "Error = ");

            try {

                o.put("posts",new JSONArray());

                //  ((LoginActivity) this.mContext).setStatus(o.getInt("status"));
                //   ((LoginActivity)this.mContext).goAhead();


            }catch(JSONException e1){
                Log.v(TAG, "Error = ");
            }
        }
        Log.v(TAG, "data = " + data);

//s.release();
        progressDialog.cancel();
        FragmentManager fm=((NavDrawer)this.mContext).getSupportFragmentManager();
        Fragment f=new FragmentChat();
        fm.beginTransaction().replace(R.id.content, f).commit();
    }


}
