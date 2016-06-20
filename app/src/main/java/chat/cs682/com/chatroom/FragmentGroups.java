package chat.cs682.com.chatroom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import chat.cs682.com.chatroom.NavDrawer;
import chat.cs682.com.chatroom.R;

/**
 * Created by rajesh on 3/24/2016.
 */
public class FragmentGroups extends Fragment {

    ListView group;
    View chatview;
    public FragmentGroups(){

    }

    @Override

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancetate){

        View dataview=inflater.inflate(R.layout.fragment_groups, container, false);
       // chatview=inflater.inflate(R.layout.fragment_chat,container,false);
        group=(ListView)dataview.findViewById(R.id.listViewG);
        final String username=(getActivity()).getIntent().getExtras().getString("user");
        String email=(getActivity()).getIntent().getExtras().getString("email");




        JSONArray ob=((NavDrawer)getActivity()).getGroups();

        int length = ob.length();
        List<String> listContents = new ArrayList<String>(length);
        //     List<String> listNum=new ArrayList<>(30);
        for (int i = 0; i < length; i++)
        {
            try {
                listContents.add(ob.getJSONObject(i).getString("groupname"));
            }
            catch(JSONException e){

            }

            group.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.drawer_list_item, listContents));
        }

        group.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When clicked, show a toast with the TextView text
                String name = (String) group.getItemAtPosition(position);
                ((NavDrawer) getActivity()).setTo(name, getActivity().getResources().getString(R.string.g));
                GetGroupPosts g = new GetGroupPosts(getActivity(), name);
                g.execute();
                ((NavDrawer) getActivity()).addRecent(name, getActivity().getResources().getString(R.string.g));

                Toast.makeText(getActivity(),
                        ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
                ((NavDrawer) getActivity()).invalidateOptionsMenu();
            }
        });





        return dataview;
    }
}




