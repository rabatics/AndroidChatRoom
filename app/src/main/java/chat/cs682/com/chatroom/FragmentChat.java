package chat.cs682.com.chatroom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajesh on 6/3/2016.
 */
public class FragmentChat extends Fragment {
    ListView posts;
    public FragmentChat(){

    }

    @Override

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancetate) {
        View dataview = inflater.inflate(R.layout.fragment_chat, container, false);
        posts = (ListView) dataview.findViewById(R.id.listView2);
        String username = (getActivity()).getIntent().getExtras().getString("user");
        String email = (getActivity()).getIntent().getExtras().getString("email");


        //  GetData data=new GetData(getActivity());
        //   data.execute();
        JSONArray ob = ((NavDrawer) getActivity()).getPosts();

        int length = ob.length();
        List<String> listContents = new ArrayList<String>(length);
        //     List<String> listNum=new ArrayList<>(30);
        for (int i = 0; i < length; i++) {
            try {
                listContents.add(ob.getJSONObject(i).getString("from") + " : " + ob.getJSONObject(i).getString("content"));
            } catch (JSONException e) {

            }
      /*      for ( i = 0; i < 30; i++) {

                listNum.add("" + i);

            }*/

            //  ListView myListView = (ListView) findViewById(R.id.l);
            posts.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.drawer_list_item, listContents));
        }

        return dataview;
    }
}
