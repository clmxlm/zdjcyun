package com.zdjc.zdjcyun.pickerview;

import android.content.Context;
import android.view.View;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.pickerview.adapters.NumericWheelAdapter;
import com.zdjc.zdjcyun.pickerview.config.PickerConfig;
import com.zdjc.zdjcyun.pickerview.data.source.TimeRepository;
import com.zdjc.zdjcyun.pickerview.utils.PickerContants;
import com.zdjc.zdjcyun.pickerview.utils.Utils;
import com.zdjc.zdjcyun.pickerview.wheel.OnWheelChangedListener;
import com.zdjc.zdjcyun.pickerview.wheel.WheelView;

import java.util.Calendar;

/**
 * Created by jzxiang on 16/4/20.
 */
public class TimeWheel {
    Context mContext;

    WheelView year, month, day, hour, minute ,seconds;
    NumericWheelAdapter mYearAdapter, mMonthAdapter, mDayAdapter, mHourAdapter, mMinuteAdapter,mSecondsAdapter;

    PickerConfig mPickerConfig;
    TimeRepository mRepository;
    OnWheelChangedListener yearListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            updateMonths();
        }
    };
    OnWheelChangedListener monthListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            updateDays();
        }
    };
    OnWheelChangedListener dayListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            updateHours();
        }
    };
    OnWheelChangedListener minuteListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            updateMinutes();
        }
    };

    OnWheelChangedListener secondsListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            updateSeconds();
        }
    };

    public TimeWheel(View view, PickerConfig pickerConfig) {
        mPickerConfig = pickerConfig;

        mRepository = new TimeRepository(pickerConfig);
        mContext = view.getContext();
        initialize(view);
    }

    public void initialize(View view) {
        initView(view);
        initYear();
        initMonth();
        initDay();
        initHour();
        initMinute();
        initSeconds();
    }


    void initView(View view) {
        year = view.findViewById(R.id.year);
        month = view.findViewById(R.id.month);
        day = view.findViewById(R.id.day);
        hour = view.findViewById(R.id.hour);
        minute =  view.findViewById(R.id.minute);
        seconds =  view.findViewById(R.id.second);

        switch (mPickerConfig.mType) {
            case ALL:

                break;
            case YEAR_MONTH_DAY:
                Utils.hideViews(hour, minute);
                break;
            case YEAR_MONTH:
                Utils.hideViews(day, hour, minute);
                break;
            case MONTH_DAY_HOUR_MIN:
                Utils.hideViews(year);
                break;
            case HOURS_MINS:
                Utils.hideViews(year, month, day);
                break;
            case YEAR:
                Utils.hideViews(month, day, hour, minute);
                break;
        }

        year.addChangingListener(yearListener);
        year.addChangingListener(monthListener);
        year.addChangingListener(dayListener);
        year.addChangingListener(minuteListener);
        year.addChangingListener(secondsListener);
        month.addChangingListener(monthListener);
        month.addChangingListener(dayListener);
        month.addChangingListener(minuteListener);
        month.addChangingListener(secondsListener);
        day.addChangingListener(dayListener);
        day.addChangingListener(minuteListener);
        day.addChangingListener(secondsListener);
        hour.addChangingListener(minuteListener);
        hour.addChangingListener(secondsListener);
    }

    void initYear() {
        int minYear = mRepository.getMinYear();
        int maxYear = mRepository.getMaxYear();

        mYearAdapter = new NumericWheelAdapter(mContext, minYear, maxYear, PickerContants.FORMAT, mPickerConfig.mYear);
        mYearAdapter.setConfig(mPickerConfig);
        year.setViewAdapter(mYearAdapter);
        year.setCurrentItem(mRepository.getDefaultCalendar().year - minYear);
    }

    void initMonth() {
        updateMonths();
        int curYear = getCurrentYear();
        int minMonth = mRepository.getMinMonth(curYear);
        month.setCurrentItem(mRepository.getDefaultCalendar().month - minMonth);
        month.setCyclic(mPickerConfig.cyclic);
    }

    void initDay() {
        updateDays();
        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();

        int minDay = mRepository.getMinDay(curYear, curMonth);
        day.setCurrentItem(mRepository.getDefaultCalendar().day - minDay);
        day.setCyclic(mPickerConfig.cyclic);
    }

    void initHour() {
        updateHours();
        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        int curDay = getCurrentDay();

        int minHour = mRepository.getMinHour(curYear, curMonth, curDay);
        hour.setCurrentItem(mRepository.getDefaultCalendar().hour - minHour);
        hour.setCyclic(mPickerConfig.cyclic);
    }

    void initMinute() {
        updateMinutes();
        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        int curDay = getCurrentDay();
        int curHour = getCurrentHour();
        int minMinute = mRepository.getMinMinute(curYear, curMonth, curDay, curHour);

        minute.setCurrentItem(mRepository.getDefaultCalendar().minute - minMinute);
        minute.setCyclic(mPickerConfig.cyclic);

    }

    void initSeconds() {
        updateSeconds();
        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        int curDay = getCurrentDay();
        int curHour = getCurrentHour();
        int curMinute = getCurrentMinute();
        int minSeconds = mRepository.getMinSeconds(curYear, curMonth, curDay, curHour,curMinute);

        seconds.setCurrentItem(mRepository.getDefaultCalendar().seconds - minSeconds);
        seconds.setCyclic(mPickerConfig.cyclic);

    }

    void updateMonths() {
        if (month.getVisibility() == View.GONE)
            return;

        int curYear = getCurrentYear();
        int minMonth = mRepository.getMinMonth(curYear);
        int maxMonth = mRepository.getMaxMonth(curYear);
        mMonthAdapter = new NumericWheelAdapter(mContext, minMonth, maxMonth, PickerContants.FORMAT, mPickerConfig.mMonth);
        mMonthAdapter.setConfig(mPickerConfig);
        month.setViewAdapter(mMonthAdapter);

        if (mRepository.isMinYear(curYear)) {
            month.setCurrentItem(0, false);
        }
    }

    void updateDays() {
        if (day.getVisibility() == View.GONE)
            return;

        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year.getCurrentItem());
        calendar.set(Calendar.MONTH, curMonth);

        int maxDay = mRepository.getMaxDay(curYear, curMonth);
        int minDay = mRepository.getMinDay(curYear, curMonth);
        mDayAdapter = new NumericWheelAdapter(mContext, minDay, maxDay, PickerContants.FORMAT, mPickerConfig.mDay);
        mDayAdapter.setConfig(mPickerConfig);
        day.setViewAdapter(mDayAdapter);

        if (mRepository.isMinMonth(curYear, curMonth)) {
            day.setCurrentItem(0, true);
        }

        int dayCount = mDayAdapter.getItemsCount();
        if (day.getCurrentItem() >= dayCount) {
            day.setCurrentItem(dayCount - 1, true);
        }
    }

    void updateHours() {
        if (hour.getVisibility() == View.GONE)
            return;

        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        int curDay = getCurrentDay();

        int minHour = mRepository.getMinHour(curYear, curMonth, curDay);
        int maxHour = mRepository.getMaxHour(curYear, curMonth, curDay);

        mHourAdapter = new NumericWheelAdapter(mContext, minHour, maxHour, PickerContants.FORMAT, mPickerConfig.mHour);
        mHourAdapter.setConfig(mPickerConfig);
        hour.setViewAdapter(mHourAdapter);

        if (mRepository.isMinDay(curYear, curMonth, curDay))
            hour.setCurrentItem(0, false);
    }

    void updateMinutes() {
        if (minute.getVisibility() == View.GONE)
            return;

        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        int curDay = getCurrentDay();
        int curHour = getCurrentHour();

        int minMinute = mRepository.getMinMinute(curYear, curMonth, curDay, curHour);
        int maxMinute = mRepository.getMaxMinute(curYear, curMonth, curDay, curHour);

        mMinuteAdapter = new NumericWheelAdapter(mContext, minMinute, maxMinute, PickerContants.FORMAT, mPickerConfig.mMinute);
        mMinuteAdapter.setConfig(mPickerConfig);
        minute.setViewAdapter(mMinuteAdapter);

        if (mRepository.isMinHour(curYear, curMonth, curDay, curHour))
            minute.setCurrentItem(0, false);
    }

    void updateSeconds() {
        if (seconds.getVisibility() == View.GONE)
            return;

        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        int curDay = getCurrentDay();
        int curHour = getCurrentHour();
        int curMinute = getCurrentMinute();

        int minSeconds = mRepository.getMinSeconds(curYear, curMonth, curDay, curHour,curMinute);
        int maxSeconds = mRepository.getMaxSeconds(curYear, curMonth, curDay, curHour,curMinute);

        mSecondsAdapter = new NumericWheelAdapter(mContext, minSeconds, maxSeconds, PickerContants.FORMAT, mPickerConfig.mSeconds);
        mSecondsAdapter.setConfig(mPickerConfig);
        seconds.setViewAdapter(mSecondsAdapter);

        if (mRepository.isMinMinute(curYear, curMonth, curDay, curHour,curMinute))
            seconds.setCurrentItem(0, false);
    }

    public int getCurrentYear() {
        return year.getCurrentItem() + mRepository.getMinYear();
    }

    public int getCurrentMonth() {
        int curYear = getCurrentYear();
        return month.getCurrentItem() + +mRepository.getMinMonth(curYear);
    }

    public int getCurrentDay() {
        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        return day.getCurrentItem() + mRepository.getMinDay(curYear, curMonth);
    }

    public int getCurrentHour() {
        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        int curDay = getCurrentDay();
        return hour.getCurrentItem() + mRepository.getMinHour(curYear, curMonth, curDay);
    }

    public int getCurrentMinute() {
        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        int curDay = getCurrentDay();
        int curHour = getCurrentHour();

        return minute.getCurrentItem() + mRepository.getMinMinute(curYear, curMonth, curDay, curHour);
    }

    public int getCurrentSeconds() {
        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        int curDay = getCurrentDay();
        int curHour = getCurrentHour();
        int curMinute = getCurrentMinute();

        return seconds.getCurrentItem() + mRepository.getMinSeconds(curYear, curMonth, curDay, curHour,curMinute);
    }


}
