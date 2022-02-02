package CalendarApp;

public class EventDetails {
    String title;
    String timeStart;
    String timeEnd;
    String startDate;
    String endDate;
    String details;
    String calendarID;
    boolean repeat;

    void sTitle(String newTitle) {
        title = newTitle;
    }

    String gTitle() {
        return title;
    }

    void sTimeStart(String newTimeStart){
        timeStart = newTimeStart;
    }


    String gTimeStart() {
        return timeStart;
    }

    void sTimeEnd(String newTimeEnd){
        timeEnd  = newTimeEnd;
    }

    String gTimeEnd() {
        return timeEnd;
    }

    void sStartDate(String sDate){
        startDate = sDate;
    }

    String gStartDate(){
        return startDate;
    }

    void sEndDate(String eDate){
        endDate = eDate;
    }

    String gEndDate(){
        return startDate;
    }

    void sCalendarID(String ID){
        calendarID = ID;
    }
}
