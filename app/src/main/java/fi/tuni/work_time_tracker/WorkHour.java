package fi.tuni.work_time_tracker;

import java.sql.Time;
import java.util.Date;

public class WorkHour {

    int _id;
    String _day;
    String _hours;

    public WorkHour(){   }

    public WorkHour(int id, String date, String workHours){
        this._id = id;
        this._day = date;
        this._hours = workHours;
    }

    public WorkHour(String date, String workHours){
        this._day = date;
        this._hours = workHours;
    }
    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getDay(){
        return this._day;
    }

    public void setDay(String date){
        this._day = date;
    }

    public String getHours(){
        return this._hours;
    }

    public void setHours(String workHours){
        this._hours = workHours;
    }
}
