package com.example.alonassaf.rubberducky;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by assaf on 6/7/2016.
 */
public class ActivitiesLayout extends RelativeLayout implements View.OnClickListener {
    private CheckBox checkBox;
    private TextView actTextView;

    private Activity activity;
    private Context context;

    public ActivitiesLayout(Context context) { //used by Android tools
        super(context);
    }

    public ActivitiesLayout(Context context, Activity a) {
        super(context);

        //set context
        this.context = context;

        //inflate layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.listview_activities, this, true);

        //get references to widgets
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        actTextView = (TextView) findViewById(R.id.activityTextView);

        //set listeners
        checkBox.setOnClickListener(this);
        this.setOnClickListener(this);

        //set activity data on widgets
        setActivity(a);
    }

    public void setActivity(Activity a){
        activity = a;

        //Sets text to activity's action name
        try {
            Class c = Class.forName(RubberDuckyDB2.Entities.get(activity.getAction_id()).getFQCN());
            BaseActivity ba = (BaseActivity) c.newInstance();
            actTextView.setText(ba.getDescription(activity));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //Checkbox code for setting whether its checked based on db (unnecessary)
        //May replace with db information for slider

    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.checkBox:
                try {
                    Class c = Class.forName(RubberDuckyDB2.Entities.get(activity.getAction_id()).getFQCN());
                    BaseActivity ba = (BaseActivity) c.newInstance();
                    ba.act(context, activity);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                //Code for clicking on screen content
                break;
        }
    }
}