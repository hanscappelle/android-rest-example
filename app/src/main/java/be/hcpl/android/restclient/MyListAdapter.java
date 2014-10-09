package be.hcpl.android.restclient;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * A custom adapter for our listview
 * <p/>
 * If you check http://developer.android.com/reference/android/widget/Adapter.html you'll notice
 * there are several types. BaseAdapter is a good generic adapter that should suit all your needs.
 * Just implement all what's abstract and add your collection of data
 * <p/>
 * Created by hanscappelle on 7/10/14.
 */
public class MyListAdapter extends BaseAdapter {

    /**
     * this is our own collection of data, can be anything we want it to be as long as we get the
     * abstract methods implemented using this data and work on this data (see getter) you should
     * be fine
     */
    private List<Item> mData;

    /**
     * some context can be useful for getting colors and other resources for layout
     */
    private Context mContext;

    /**
     * our ctor for this adapter, we'll accept all the things we need here
     *
     * @param mData
     */
    public MyListAdapter(final Context context, final List<Item> mData) {
        this.mData = mData;
        this.mContext = context;
    }

    public List<Item> getData() {
        return mData;
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return mData != null ? mData.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        // just returning position as id here, could be the id of your model object instead
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // this is where we'll be creating our view, anything that needs to update according to
        // your model object will need a view to visualize the state of that propery

        // the viewholder pattern for performance
        ViewHolder viewHolder;
        if (convertView == null) {

            // inflate the layout, see how we can use this context reference?
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_item, parent, false);

            // we'll set up the ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.description = (TextView) convertView.findViewById(R.id.description);
            viewHolder.enabled = (CheckBox) convertView.findViewById(R.id.checked);

            // store the holder with the view.
            convertView.setTag(viewHolder);

        } else {
            // we've just avoided calling findViewById() on resource every time
            // just use the viewHolder instead
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // object item based on the position
        Item obj = mData.get(position);

        // assign values if the object is not null
        if (mData != null) {
            // get the TextView from the ViewHolder and then set the text (item name) and other values
            viewHolder.title.setText(obj.getTitle());
            viewHolder.description.setText(obj.getDescription());
            viewHolder.enabled.setChecked(obj.isChecked());
        }
        return convertView;
    }

    static class ViewHolder {
        TextView title;
        TextView description;
        CheckBox enabled;
    }
}
