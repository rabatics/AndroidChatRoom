
package chat.cs682.com.chatroom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class CustomAdapter extends ArrayAdapter {


    private String[] objects;



    public CustomAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

       // RecyclerView.ViewHolder viewHolder = null;
        String listViewItem = objects[position];
    //    int listViewItemType = getItemViewType(position);
        List<String> l= Arrays.asList(listViewItem.split(":"));
    ViewHolder vh;
        String user=((NavDrawer) getContext()).getUsername();
        if (convertView == null) {
            vh=new ViewHolder();

            System.out.println(user + ":" + l.get(0));
            if ( l.get(0).contains(user)) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.me_list_item, null);
                vh.mText= (TextView)convertView.findViewById(R.id.text1);

            } else {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.others_list_item, null);
                vh.mText= (TextView)convertView.findViewById(R.id.text2);
            }

            convertView.setTag(vh);

        }
        else{
            vh = (ViewHolder) convertView.getTag();
            if ( l.get(0).contains(user)) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.me_list_item, null);
                vh.mText= (TextView)convertView.findViewById(R.id.text1);

            } else {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.others_list_item, null);
                vh.mText= (TextView)convertView.findViewById(R.id.text2);
            }

            convertView.setTag(vh);
           // convertView.setTag(vh);
        }


        vh.mText.setText(listViewItem);




        return convertView;
    }



    class ViewHolder{
        TextView mText;

    }
}
