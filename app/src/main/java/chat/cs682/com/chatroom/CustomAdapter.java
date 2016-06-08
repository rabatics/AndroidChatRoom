/*
package chat.cs682.com.chatroom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter {

  */
/*  public static final int TYPE_ODD = 0;
    public static final int TYPE_EVEN = 1;
    public static final int TYPE_WHITE = 2;
    public static final int TYPE_BLACK = 3;

    private ListViewItem[] objects;
*//*

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        return objects[position].getType();
    }

    public CustomAdapter(Context context, int resource, ListViewItem[] objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RecyclerView.ViewHolder viewHolder = null;
        ListViewItem listViewItem = objects[position];
        int listViewItemType = getItemViewType(position);


        if (convertView == null) {

            if (listViewItemType == TYPE_EVEN) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.type_even, null);
            } else if (listViewItemType == TYPE_ODD) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.type_odd, null);
            } else if (listViewItemType == TYPE_WHITE) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.type_white, null);
            } else {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.type_black, null);
            }

            TextView textView = (TextView) convertView.findViewById(R.id.text);
            viewHolder = new RecyclerView.ViewHolder(textView);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (RecyclerView.ViewHolder) convertView.getTag();
        }

        viewHolder.getText().setText(listViewItem.getText());

        return convertView;
    }

}*/
