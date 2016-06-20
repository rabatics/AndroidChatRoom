package chat.cs682.com.chatroom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajesh on 6/3/2016.
 */
public class FragmentChat extends Fragment {
    ListView posts;
    Button send;
    EditText msg;
    CustomAdapter msgs;
    List<String> listContents=new ArrayList<>();

    public FragmentChat(){

    }

    @Override

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancetate) {
        View dataview = inflater.inflate(R.layout.fragment_chat, container, false);
        send=(Button)dataview.findViewById(R.id.button);
        msg=(EditText)dataview.findViewById(R.id.editText3);
        posts = (ListView) dataview.findViewById(R.id.listView2);
        String username = (getActivity()).getIntent().getExtras().getString("user");
        String email = (getActivity()).getIntent().getExtras().getString("email");


        //  GetData data=new GetData(getActivity());
        //   data.execute();
        JSONArray ob = ((NavDrawer) getActivity()).getPosts();

        int length = ob.length();
        listContents = new ArrayList<String>();
        try {
            listContents.add("MESSAGES FROM :" + ((NavDrawer) getActivity()).getTo().getString("name").toUpperCase());
        }catch(JSONException e){

        }
        //     List<String> listNum=new ArrayList<>(30);
        for (int i = 0; i < length; i++) {
            try {
                listContents.add(ob.getJSONObject(i).getString("from") + " : " + ob.getJSONObject(i).getString("content"));
            } catch (JSONException e) {

            }
        }

            String[] s=listContents.toArray(new String[]{});
            msgs=new CustomAdapter(getActivity(), R.layout.drawer_list_item, s);
            posts.setAdapter(msgs);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = ((NavDrawer) getActivity()).getUsername();
                JSONObject t = new JSONObject();
                String to = "";
                try {
                    t = ((NavDrawer) getActivity()).getTo();
                    to = t.getString("name");
                } catch (JSONException e) {
                    Toast.makeText(getActivity(), "Invalid Recepient", Toast.LENGTH_LONG);
                }
                String ms = msg.getText().toString();
                if (!ms.contentEquals(null)) {
                    msg.setText("");
                    listContents.add(user + " : " + ms);
                    msgs.notifyDataSetChanged();
                    PostMessage p = new PostMessage(getActivity(), user, to, ms);
                    p.execute();
                    try {
                        if (t.getString("type").contentEquals(getActivity().getResources().getString(R.string.f))) {
                            GetFriendPosts f = new GetFriendPosts(getActivity(), user, to);
                            f.execute();
                        } else {
                            GetGroupPosts f = new GetGroupPosts(getActivity(), to);
                            f.execute();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(getActivity(), "Couldn't Process Recepient Type", Toast.LENGTH_LONG);
                    }
                } else {
                    Toast.makeText(getActivity(), "What do you want to say to "+user+" ?", Toast.LENGTH_LONG);
                }

            }
        });

        return dataview;
    }
}
