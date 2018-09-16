package com.example.user.Leszy;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.CalendarView;

import java.util.Locale;

public class CalendarActivity extends SideMenuActivity {

    private static final String TAG = "CalendarActivity";

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        CalendarView simpleCalendarView = (CalendarView) findViewById(R.id.calendarView);
        simpleCalendarView.setFirstDayOfWeek(2);

        String languageToLoad = "pl";
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        this.setContentView(R.layout.activity_calendar);
    }
}