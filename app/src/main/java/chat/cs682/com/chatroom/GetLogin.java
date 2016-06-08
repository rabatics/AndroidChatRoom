package chat.cs682.com.chatroom;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.concurrent.Semaphore;

/**
 * Created by rajesh on 4/13/2016.
 */
public class GetLogin extends AsyncTask<String,Void,String> {
    private static String TAG = "myApp";
   private  ProgressDialog progressDialog;
    private Context mContext;
    private String username;
    private String password;
   // private Semaphore s=new Semaphore(0);

    GetLogin(Context context,String user,String pass){

        this.mContext = context;
        this.username=user;
        this.password=pass;
    }

    @Override

    protected String doInBackground(String... params) {

        String result = "";

        try {
            Calendar rightnow= Calendar.getInstance();

            URL url = new URL("http://"+R.string.IP+":3000/userLogin/api/login?username="+this.username+"&password="+this.password+"");

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            StringBuffer sb = new StringBuffer();

            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String read;

            while((read = br.readLine())!=null){

                sb.append(read);

            }

            br.close();

            result = sb.toString();

        } catch (IOException e) {

            Log.d(TAG, "Error: " + e.toString());

        }

        return result;

    }



    @Override
    protected void onPreExecute() {
       /* try {
            s.acquire();
        }catch(InterruptedException e){

        }*/
        progressDialog = new ProgressDialog(
                mContext);
        progressDialog.setMessage("Checking User......");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    protected void onPostExecute(String data){
        JSONObject o = new JSONObject();
        progressDialog.cancel();

        try {
            if(!data.contentEquals("")) {
                o = new JSONObject(data);

                ((LoginActivity) this.mContext).setStatus(o.getInt("status"));
                ((LoginActivity) this.mContext).setEmail(o.getString("email"));
                ((LoginActivity)this.mContext).goAhead();
            }
            else{
                o = new JSONObject();
                o.put("status",0);

                ((LoginActivity) this.mContext).setStatus(o.getInt("status"));
                ((LoginActivity)this.mContext).goAhead();
            }
        }
        catch(JSONException e){
            Log.v(TAG, "Error = ");

            try {

                o.put("status",0);

                ((LoginActivity) this.mContext).setStatus(o.getInt("status"));
                ((LoginActivity)this.mContext).goAhead();


            }catch(JSONException e1){
                Log.v(TAG, "Error = ");
            }
        }
        Log.v(TAG, "data = " + data);

//s.release();
    }


}
