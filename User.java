package CalendarApp;

import java.util.*;
import java.util.Calendar;
import java.util.HashMap;

public class User {

    String ID = UUID.randomUUID().toString();
    boolean darkTheme = false;
    boolean makePrivate = false;
    String timeZone;
    HashMap<String, Calendar> calendars = new HashMap<>();
    HashMap<String, Event> eventsTitleList = new HashMap<>();
    HashMap<String, String> privatelist = new HashMap<>();

    void sUserID(String ID) {
        this.ID = ID;

    }

    String gUserID() {
        return this.ID;
    }

    void addCalendar(Calendar myCalendar) {
        myCalendar = new GregorianCalendar();

    }

    void removeCalendar(String ID) {
        calendars.remove(ID);
    }

    Calendar getCalendar(String cID) {
        return this.calendars.get(cID);
    }

    void addEvent(String ID) {
        EventDetails info = new EventDetails();
        Event theEvent = new Event();
        theEvent.sEventInfo(info);

    }

    void sTimezone(String timeZone) {
        this.timeZone = timeZone;
    }

    String gTimezone() {
        return this.timeZone;
    }

    void sDarkThemeActive() {
        darkTheme = true;
    }


    void sPrivate() {
        makePrivate = true;
    }
}
