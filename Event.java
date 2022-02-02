package CalendarApp;


import java.util.*;

public class Event {
    String eventID = UUID.randomUUID().toString();
    ArrayList<User> users = new ArrayList<>();
    EventDetails details = new EventDetails();

    void shareWithUser(User id){
        users.add(id);
    }

    void sEventID(String id) {
        eventID = id;

    }

    String gEventID() {
        return eventID;

    }

    void sEventInfo(EventDetails det) {
        details.calendarID = det.calendarID;
        details.repeat = det.repeat;
        details.title = det.title;
        details.timeEnd = det.timeEnd;
        details.timeStart = det.timeStart;
        details.startDate = det.startDate;
        details.endDate = det.endDate;

    }

    void createTimer(){

    }

    void removeTimer(){

    }

    void searchEvent(){

    }

    void shareEvent(){

    }
}
