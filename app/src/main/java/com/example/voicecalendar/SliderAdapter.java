package com.example.voicecalendar;


import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    public int[] slide_images={
            R.drawable.smile2,
            R.drawable.mic2,
            R.drawable.calendar_img,
//            R.drawable.del_img,
//            R.drawable.av_img,
            R.drawable.alarm_clock,
            R.drawable.small_mic,
            R.drawable.av_img,
            R.drawable.del_img,
            R.drawable.notif_img,
            R.drawable.smile2
    };

    public String[] slide_headings={
            "Welcome to Your Voice Calender app!!",
            "Voice commands...",
            "Setting Dates",
            "Setting Time",
            "Writing Your Schedule",
            "Add and View",
            "Delete",
            "Alarms and Notifications",
            "Good Luck !"
    };

    public String[] slide_des={
            "Follow the instructions to successfully navigate through the app.",
            "Click and always hold the mic button while giving commands...",
            "To mark the date of your event you can use commands like: Make event, Mark date, Set Date, Date",
            "To set the time for the event you can use commands like: Set time, Set time to, Time",
            "To start dictating your schedule or event, click and hold the mic button and start speaking.",
            "To add your event or to view all your schedules, you can use commands like add and view respectively" ,
            "Select the date and the give the command 'delete' to delete all your events on that day",
            "Alarms and notifications will be automatically set up everyday to make sure you check your schedule list",
            "We hope you can use this app efficiently to organize your day !"
    };
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);


        ImageView slideImageView = (ImageView)view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView)view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView)view.findViewById(R.id.slide_description);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_des[position]);

        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((RelativeLayout)object);
    }
}
