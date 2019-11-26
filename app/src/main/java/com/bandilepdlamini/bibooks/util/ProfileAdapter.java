package com.bandilepdlamini.bibooks.util;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.bandilepdlamini.bibooks.R;
import com.bandilepdlamini.bibooks.datasource.UserDetailMobImpl;

import java.util.ArrayList;

public class ProfileAdapter extends ArrayAdapter<UserDetail>{

    private ArrayList<UserDetail> dataSet;
    Context mContext;
    private UserDetailMobImpl userDetailMob;
    private ListView listView;
    private TextView text;

    // View lookup cache
    private static class ViewHolder {
        EditText txtName;
        ImageButton remove;
        ImageButton save;
        ImageButton select;
    }

    public ProfileAdapter(ArrayList<UserDetail> data, Context context, ListView pListView, TextView pSaveTxt) {
        super(context, R.layout.profile_row, data);
        this.dataSet = data;
        this.mContext=context;
        userDetailMob = new UserDetailMobImpl(mContext);
        listView = pListView;
        text = pSaveTxt;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final UserDetail dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.profile_row, parent, false);
            viewHolder.txtName = (EditText) convertView.findViewById(R.id.textView);
            viewHolder.remove = (ImageButton) convertView.findViewById(R.id.btn_remove);
            viewHolder.save = (ImageButton) convertView.findViewById(R.id.button);
            viewHolder.select = (ImageButton) convertView.findViewById(R.id.button2);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }


        viewHolder.txtName.setText(dataModel.getUsername());

        viewHolder.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDetailMob.open();
                ArrayList<UserDetail> arrayList = userDetailMob.fetchAllUsers();
                userDetailMob.close();
                for(int i = 0; i < arrayList.size(); i++){
                    if(arrayList.get(i).getActive().equals("1")){
                        arrayList.get(i).setActive("");
                        userDetailMob.open();
                        userDetailMob.updateUserDetail(arrayList.get(i), arrayList.get(i).getUsername());
                        userDetailMob.close();
                    }
                }
                dataModel.setActive("1");


                userDetailMob.open();
                userDetailMob.updateUserDetail(dataModel, dataModel.getUsername());
                userDetailMob.close();

                text.setText(dataModel.getUsername() + " Selected!");
                text.setVisibility(View.VISIBLE);

                CountDownTimer timer = new CountDownTimer(5000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }

                    @Override
                    public void onFinish() {
                        text.setVisibility(View.INVISIBLE);
                    }
                };
                timer.start();
            }
        });

        viewHolder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDetailMob.open();
                ArrayList<UserDetail> userDetails = userDetailMob.fetchAllUsers();
                boolean found = false;
                for(int i = 0; i < userDetails.size(); i++){
                    if(userDetails.get(i).getUsername().equals(viewHolder.txtName.getText().toString().trim())){
                        found = true;
                    }
                }
                userDetailMob.close();

                if(!found){
                    userDetailMob.open();
                    userDetailMob.insertUserDetail(new UserDetail(viewHolder.txtName.getText().toString(),
                            dataModel.getBestScore(), dataModel.getBestTime(), dataModel.getActive()));
                    userDetailMob.close();
                    text.setText(viewHolder.txtName.getText().toString() + " Saved!");
                }else{
                    text.setText(viewHolder.txtName.getText().toString() + " already exists!");
                }


                text.setVisibility(View.VISIBLE);

                CountDownTimer timer = new CountDownTimer(5000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }

                    @Override
                    public void onFinish() {
                        text.setVisibility(View.INVISIBLE); //(or GONE)
                    }
                };
                timer.start();
            }
        });

        viewHolder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDetailMob.open();
                userDetailMob.deleteUserDetail(dataModel.getUsername());
                ArrayList<UserDetail> arrayList = userDetailMob.fetchAllUsers();
                ProfileAdapter profileAdapter = new ProfileAdapter(arrayList, mContext, listView, text);
                listView.setAdapter(profileAdapter);
                userDetailMob.close();
            }
        });
        // Return the completed view to render on screen
        return convertView;
    }
}
