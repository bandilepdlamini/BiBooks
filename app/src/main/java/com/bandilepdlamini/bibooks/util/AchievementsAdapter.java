package com.bandilepdlamini.bibooks.util;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.bandilepdlamini.bibooks.R;
import com.bandilepdlamini.bibooks.datasource.UserDetailMobImpl;

import java.util.ArrayList;

public class AchievementsAdapter extends ArrayAdapter<String> {
    private ArrayList<String> dataSet;
    Context mContext;
    private UserDetailMobImpl userDetailMob;
    private ListView listView;
    private TextView text;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView value;
        TextView books;
    }

    public AchievementsAdapter(ArrayList<String> data, Context context) {
        super(context, R.layout.achievements_row, data);
        this.dataSet = data;
        this.mContext=context;
        userDetailMob = new UserDetailMobImpl(mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final String dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final AchievementsAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new AchievementsAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.achievements_row, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.textView);
            viewHolder.value = (TextView) convertView.findViewById(R.id.value);
            viewHolder.books = (TextView) convertView.findViewById(R.id.books);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (AchievementsAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        if(dataModel != null){
            String[] splits = dataModel.split(",");
            viewHolder.txtName.setText(splits[0]);
            viewHolder.value.setText(splits[1]);

            if(splits[2].equals("0")){
                viewHolder.books.setText("New Testament");
            }else if(splits[2].equals("1")){
                viewHolder.books.setText("Old Testament");
            }else if(splits[2].equals("2")){
                viewHolder.books.setText("All BiBooks");
            }
        }


        // Return the completed view to render on screen
        return convertView;
    }
}
