package fi.tuni.work_time_tracker;

public class WorkHour {

    int _id;
    String _day;
    String _hours;
    String _comment;

    public WorkHour(){   }

    public WorkHour(int id, String date, String workHours, String comment){
        this._id = id;
        this._day = date;
        this._hours = workHours;
        this._comment = comment;
    }

    public WorkHour(String date, String workHours, String comment){
        this._day = date;
        this._hours = workHours;
        this._comment = comment;
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

    public String getComment() {
        return _comment;
    }

    public void setComment(String _comment) {
        this._comment = _comment;
    }
}
