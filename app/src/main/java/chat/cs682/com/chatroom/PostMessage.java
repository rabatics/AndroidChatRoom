package chat.cs682.com.chatroom;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by rajesh on 6/4/2016.
 */
public class PostMessage extends AsyncTask<String, Void, Void> {
    private  ProgressDialog progress;
    private final Context context;
    private String username;
    private String to;
    private String msg;

    public PostMessage(Context c,String u,String t,String msg){
        this.context = c;
        this.username=u;
        this.to=t;
        this.msg=msg;

    }

    protected void onPreExecute(){
        progress= new ProgressDialog(this.context);
        progress.setMessage("Loading");
        progress.show();
    }

    @Override
    protected Void doInBackground(String... params) {
        try {

            //final TextView outputView = (TextView) findViewById(R.id.showOutput);
            URL url = new URL("http://"+R.string.IP+":3000/users/api/add");

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            String urlParameters = "fromuser="+this.username+"&to="+this.to+"&content="+this.msg+"";
            connection.setRequestMethod("POST");
            connection.setRequestProperty("USER-AGENT", "Mozilla/5.0");
            connection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");
            connection.setDoOutput(true);
            DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
            dStream.writeBytes(urlParameters);
            dStream.flush();
            dStream.close();
            int responseCode = connection.getResponseCode();

            final StringBuilder output = new StringBuilder("Request URL " + url);
            output.append(System.getProperty("line.separator") + "Request Parameters " + urlParameters);
            output.append(System.getProperty("line.separator")  + "Response Code " + responseCode);
            output.append(System.getProperty("line.separator")  + "Type " + "POST");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder responseOutput = new StringBuilder();
            System.out.println("output===============" + br);
            while((line = br.readLine()) != null ) {
                responseOutput.append(line);
            }
            br.close();

            output.append(System.getProperty("line.separator") + "Response " + System.getProperty("line.separator") + System.getProperty("line.separator") + responseOutput.toString());

            ((NavDrawer)this.context).runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    //outputView.setText(output);
                    progress.dismiss();
                }
            });

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}