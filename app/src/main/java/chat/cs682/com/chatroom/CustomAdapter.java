
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


  /*public static final int TYPE_ODD = 0;
    public static final int TYPE_EVEN = 1;
    public static final int TYPE_WHITE = 2;
    public static final int TYPE_BLACK = 3;*/

    private String[] objects;


   /* @Override
    public int getViewTypeCount() {
        return 4;
    }*/
/*
    @Override
    public int getItemViewType(int position) {
        return objects[position].getType();
    }*/

    public CustomAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       // RecyclerView.ViewHolder viewHolder = null;
        String listViewItem = objects[position];
    //    int listViewItemType = getItemViewType(position);
        List<String> l= Arrays.asList(listViewItem.split(":"));

        if (convertView == null) {
            System.out.println(((NavDrawer) this.getContext()).getUsername());
            if ( l.get(0).contains(((NavDrawer) this.getContext()).getUsername())) {
                convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.me_list_item, null);
            } else {
                convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.others_list_item, null);
            }
            TextView textView = (TextView)convertView.findViewById(R.id.text1);

           /* viewHolder = new RecyclerView.ViewHolder(textView);

            convertView.setTag(viewHolder);*/
            textView.setText(listViewItem);

        }



        return convertView;
    }

}
