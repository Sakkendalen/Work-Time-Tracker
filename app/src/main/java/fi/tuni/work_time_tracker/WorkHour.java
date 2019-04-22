package fi.tuni.work_time_tracker;


/**
 * Entity which is used to define and save data in database.
 *
 * @author      Saku Tynjala saku.tynjala@tuni.fi
 * @version     0.3
 * @since       0.1
 */

public class WorkHour {

    int _id;
    String _day;
    String _hours;
    String _comment;

    /**
     * Basic empty constructor
     */
    public WorkHour(){   }

    /**
     * Basic Constructor with all class attributes.
     *
     * @param id ID of entity.
     * @param date Date of entity.
     * @param workHours hours of entity.
     * @param comment comment of entity.
     */
    public WorkHour(int id, String date, String workHours, String comment){
        this._id = id;
        this._day = date;
        this._hours = workHours;
        this._comment = comment;
    }

    /**
     * Basic Constructor without int id attribute.
     *
     * @param date Date of entity.
     * @param workHours hours of entity.
     * @param comment comment of entity.
     */
    public WorkHour(String date, String workHours, String comment){
        this._day = date;
        this._hours = workHours;
        this._comment = comment;
    }

    /**
     * Method for getting entity ID.
     *
     * @return int ID of entity.
     */
    public int getID(){
        return this._id;
    }

    /**
     * Method for setting entity ID.
     *
     * @param id Setting entity ID.
     */
    public void setID(int id){
        this._id = id;
    }

    /**
     *Method for getting entity date.
     *
     * @return String date of entity
     */
    public String getDay(){
        return this._day;
    }

    /**
     * Method for setting entity day.
     *
     * @param date String date of entity.
     */
    public void setDay(String date){
        this._day = date;
    }

    /**
     * Method for getting entity hours.
     *
     * @return String hours of entity.
     */
    public String getHours(){
        return this._hours;
    }

    /**
     * Method for setting entity hours.
     *
     * @param workHours String hours of entity.
     */
    public void setHours(String workHours){
        this._hours = workHours;
    }

    /**
     * Method for getting entity comment.
     *
     * @return String comment of entity
     */
    public String getComment() {
        return _comment;
    }

    /**
     * Method for setting entity comment.
     *
     * @param _comment String comment of entity.
     */
    public void setComment(String _comment) {
        this._comment = _comment;
    }
}
