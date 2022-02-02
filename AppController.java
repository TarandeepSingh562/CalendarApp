package CalendarApp;

import java.util.*;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;

public class AppController {
    Scanner scan = new Scanner(System.in);
    HashMap<String, Calendar> newCalendars = new HashMap<>();
    HashMap<String, Event> eventsList = new HashMap<>();

    void mainMenu() {
        System.out.println("                                                                                        ");
        System.out.println("                             WELCOME TO CALENDAR'S APP                                  ");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("                             A: Add a Calender (Private or Public)                      "); // done
        System.out.println("                             T: Change Timezone                                         "); // done
        System.out.println("                             D: Delete a Calender                                       "); // done
        System.out.println("                             V: Update and View Calendar List                           "); // done
        System.out.println("                             E: Add Event                                               "); // done
        System.out.println("                             M: Monthly View(dark theme/light theme)                    "); // done
        System.out.println("                             Y: Yearly View                                             "); // work
        System.out.println("                             R: Remove Event                                            "); // work
        System.out.println("                             U: Update and View Events                                  "); // done
        System.out.println("                             H: Share Calendar with User                                "); // work
        System.out.println("                             S: Search Event                                            "); // work
        System.out.println("                             Q: Quit                                                    "); // done
        System.out.println("----------------------------------------------------------------------------------------");
    }

    void changeTimezone(User user) {
        user.timeZone = "";
        SimpleDateFormat simpledateformat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");

        while (true) {

            if (user.eventsTitleList.keySet().size() == 0) {
                System.out.println("First add your events and then come back to change timezone.");
                mainMenu();
                break;
            }

            try {
                System.out.print("Set the timezone ");
                String timeZone = scan.nextLine();
                for (String TZ : ZoneId.getAvailableZoneIds()) {
                    if (timeZone.equals(TZ)) {
                        for (Event event : user.eventsTitleList.values()) {
                            String dateStartString = event.details.startDate;
                            dateStartString += " " + event.details.timeStart;
                            String dateEndString = event.details.endDate;
                            dateEndString += " " + event.details.timeEnd;

                            // start date and time
                            Date parseStartDate = simpledateformat.parse(dateStartString);
                            Date parseEndDate = simpledateformat.parse(dateEndString);
                            simpledateformat.setTimeZone(TimeZone.getTimeZone((timeZone)));
                            String splitThis = simpledateformat.format(parseStartDate);
                            String splitter = simpledateformat.format(parseEndDate);

                            String[] startParts = splitThis.split(" ", 2);
                            event.details.startDate = startParts[0];
                            event.details.timeStart = startParts[1];

                            String[] endParts = splitter.split(" ", 2);
                            event.details.endDate = endParts[0];
                            event.details.timeEnd = endParts[1];

                            user.sTimezone(timeZone);

                        }
                    }
                }
                System.out.println("Your timezone has been changed successfully to " + user.gTimezone());
                mainMenu();
                break;
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

    void addCalendar(User user) {
        Calendar myCalendar = new GregorianCalendar();
        user.addCalendar(myCalendar);
        System.out.print("Set the name of this calendar: ");
        String id = scan.nextLine();
        user.sUserID(id);
        user.calendars.put(id, myCalendar);
        while (true) {
            try {
                System.out.print("Do you want to make this calendar Private or Public? ");
                String isPrivate = scan.nextLine().toLowerCase();
                if (isPrivate.equals("private") || isPrivate.equals("public")) {
                    user.privatelist.put(id, isPrivate);
                    break;
                } else {
                    System.out.println("Select either Public or Private for calendar");
                }
            } catch (Exception e) {
                System.out.println("Please select Private or Public for your Calendar");
            }
        }
        System.out.println("Added calendar " + id + " (" + user.privatelist.get(id) + ")" + "\n" + "Current list: "
                + user.calendars.keySet());

        mainMenu();
    }

    void removeCalendar(User user) {
        while (true) {
            boolean flag = false;
            try {

                if (user.calendars.keySet().size() == 0) {
                    System.out.println("You don't have a calendar to remove. So add calendar first");
                    mainMenu();
                    break;
                }

                for (String id : user.calendars.keySet()) {
                    System.out.println("Here are your calendars: " + user.calendars.keySet());
                    System.out.print("Which calendar do you want to remove: ");
                    String userID = scan.nextLine();
                    if (userID.equals(id)) {
                        user.sUserID(userID);
                        user.removeCalendar(userID);
                        user.calendars.remove(userID);
                        System.out.print("Deleted Calendar: " + userID + "\n");
                        flag = true;
                        mainMenu();
                        break;

                    } else {
                        System.out.println("Not a valid calendar. Try again");
                    }

                }
                if (flag == true) {
                    break;
                }

            }

            catch (Exception e) {
                System.out.println("Error. Try again");
            }

        }

    }

    void updateCalendarList(User user) {
        System.out.println("These are your up-to-date calendar list: " + user.calendars.keySet());
        if (user.calendars.keySet().size() == 0) {
            System.out.println("You don't any calendars to view an updated version of.");
            mainMenu();
        } else {
            mainMenu();
        }

    }

    void monthlyView(User user) {
        CalendarView myCalendar = new CalendarView();

        while (true) {
            try {
                if (user.calendars.keySet().size() == 0) {
                    System.out.println("You don't have a calendar to view. So add calendar first");
                    mainMenu();
                    break;
                }
                System.out.println("These are your calendars: " + user.calendars.keySet());
                System.out.println("Which calendar do you want to view in monthly view form?: ");
                String cID = scan.nextLine();
                user.sUserID(cID);
                if (user.calendars.containsKey(cID)) {
                    System.out.println("----------------------------------Viewing Calendar " + cID
                            + " ----------------------------------");
                    myCalendar.displayCalendarMonthly(cID);
                    System.out.println("");
                    mainMenu();
                    break;
                } else {
                    System.out.println("Not a valid Calendar you created before");
                }
            } catch (Exception e) {
                System.out.println("Not a valid Calendar you created before");
            }
        }
    }

    void yearlyView(User user) { // implementing still

    }

    void weeklyView(User user) { // implementing still

    }

    void dailyView(User user) { // implementing still

    }

    void addEventToCalendar(User user) {
        EventDetails info = new EventDetails();
        Event myEvent = new Event();

        System.out.println("--------------------- Setting up events ---------------------");
        System.out.println("Here are your calendars that you could make events for " + user.calendars.keySet());

        while (true) {
            try {
                if (user.calendars.keySet().size() == 0) {
                    System.out.println("You don't have a calendar to view. So add calendar first");
                    mainMenu();
                    break;
                }

                while (true) {

                    boolean flag = false;
                    try {
                        System.out.print("Which calendar would you like to set it for? ");
                        String cID = scan.nextLine();
                        for (String ID : user.calendars.keySet()) {
                            if (cID.equals(ID)) {
                                flag = true;
                                info.sCalendarID(cID);
                                break;
                            }
                        }
                        if (flag == true) {
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Not a valid Calendar you have made");
                    }
                }

                System.out.print("What do you want to name this event? ");
                String title = scan.nextLine();
                info.sTitle(title);

                while (true) {
                    try {
                        System.out.print("What time do you want start this event?(ex. 7:15 PM/AM) ");
                        String timeStart = scan.nextLine();
                        if (timeStart.matches("^([1-9]|0[1-9]|1[0-2]):[0-5][0-9] ([AaPp][Mm])$")) {
                            if (isTimeValid(timeStart)) {
                                info.sTimeStart(timeStart);
                                break;
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Not a valid start time input. Try again");

                    }

                }

                while (true) {
                    try {

                        System.out.print("What time do you want to end this event?(ex. 7:15 PM/AM) ");
                        String timeEnd = scan.nextLine();
                        if (timeEnd.matches("^([1-9]|0[1-9]|1[0-2]):[0-5][0-9] ([AaPp][Mm])$")) {
                            if (isTimeValid(timeEnd)) {
                                info.sTimeEnd(timeEnd);
                                break;
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Enter a valid end time. Try again");
                    }
                }

                while (true) {
                    try {
                        System.out.print("What date do you want to start this event?(Ex. 06/24/1990) ");
                        String dateStart = scan.nextLine();
                        if (dateStart.matches("[0-9]{2}[/]{1}[0-9]{2}[/]{1}[0-9-]{4}")) {
                            if (isDateValid(dateStart)) {
                                info.sStartDate(dateStart);
                                break;
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Not a valid end time input. Try again");
                    }
                }

                while (true) {
                    try {
                        System.out.print("What date do you want to end this event?(Ex. 06/24/1990) ");
                        String dateEnd = scan.nextLine();
                        if (dateEnd.matches("[0-9]{2}[/]{1}[0-9]{2}[/]{1}[0-9-]{4}")) {
                            if (isDateValid(dateEnd)) {
                                info.sEndDate(dateEnd);
                                break;
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Enter a valid end date");
                    }
                }

                myEvent.sEventInfo(info);

                user.eventsTitleList.put(title, myEvent);

                mainMenu();
                break;
            } catch (Exception e) {
                System.out.println("");
            }

        }
    }

    public boolean isTimeValid(String theTime) {
        String format = "hh:mm a";
        DateFormat date = new SimpleDateFormat(format);
        date.setLenient(false);
        try {
            date.parse(theTime);
            return true;
        } catch (ParseException e) {
            return false;
        }

    }

    public boolean isDateValid(String theDate) {
        String pattern = "MM/dd/yyyy";
        DateFormat formatter = new SimpleDateFormat(pattern);
        formatter.setLenient(false);
        try {
            formatter.parse(theDate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    void removeEvent(User user) {
        while (true) {
            try {
                if (user.calendars.keySet().size() == 0) {
                    System.out.println("You don't have any calendars, so you cant view events");
                    mainMenu();
                    break;
                }
                if (user.eventsTitleList.keySet().size() == 0) {
                    System.out.println("These are your calendars: " + user.calendars.keySet());
                    System.out.println("You don't have any events in any of your calendars");
                    mainMenu();
                    break;
                }
                System.out.println("What calendar's events do you want to view? " + user.calendars.keySet());
                String cID = scan.nextLine();
                for (String ID : user.calendars.keySet()) {
                    ArrayList<String> removeEvents = new ArrayList<String>();
                    if (cID.equals(ID)) {
                        for (Event event : user.eventsTitleList.values()) {
                            if (event.details.calendarID.equals(cID)) {
                                removeEvents.add(event.details.title);

                            }
                        }
                        System.out.println("What event do you want to remove from this calendar? " + removeEvents);
                        String titleChosen = scan.nextLine();
                        for (String event : removeEvents) {
                            if (titleChosen.equals(event)) {
                                removeEvents.remove(titleChosen);
                                user.eventsTitleList.remove(titleChosen);
                                System.out.println("The event " + titleChosen + " has been removed.");
                                break;
                            }
                        }

                    }
                }
                mainMenu();
                break;
            }

            catch (Exception e) {
                System.out.println("Event does not exist");
            }
        }

    }

    void displayEvents(User user) {

        while (true) {
            boolean flagger = false;
            try {
                if (user.calendars.keySet().size() == 0) {
                    System.out.println("You don't have any calendars, so you cant view events");
                    mainMenu();
                    break;
                }
                if (user.eventsTitleList.keySet().size() == 0) {
                    System.out.println("These are your calendars: " + user.calendars.keySet());
                    System.out.println("You don't have any events in any of your calendars");
                    mainMenu();
                    break;
                }
                System.out.println("What calendar's events do you want to view? " + user.calendars.keySet());
                String cID = scan.nextLine();
                for (String ID : user.calendars.keySet()) {
                    ArrayList<String> events = new ArrayList<String>();
                    if (cID.equals(ID)) {
                        for (Event event : user.eventsTitleList.values()) {
                            if (event.details.calendarID.equals(cID)) {
                                events.add(event.details.title);

                            }
                        }
                        System.out.println("What event do you want to view? " + events);
                        String titleChose = scan.nextLine();
                        for (String title : user.eventsTitleList.keySet()) {
                            if (titleChose.equals(title)) {
                                System.out.println(
                                        "You are now viewing Event: " + user.eventsTitleList.get(title).details.title
                                                + " (" + user.gTimezone() + ")");
                                System.out.println("The start date for " + title + " is: "
                                        + user.eventsTitleList.get(title).details.startDate);
                                System.out.println("The end date for " + title + " is: "
                                        + user.eventsTitleList.get(title).details.endDate);
                                System.out.println("The time start date for " + title + " is: "
                                        + user.eventsTitleList.get(title).details.timeStart);
                                System.out.println("The time end date for " + title + " is: "
                                        + user.eventsTitleList.get(title).details.timeEnd);
                                flagger = true;
                                mainMenu();

                            }

                        }

                    }

                }
                if (flagger == true) {
                    break;
                }

            } catch (Exception e) {
                System.out.println("Not valid responses");
            }
        }
    }

    void choices() {
        mainMenu();
        User user = new User();
        while (true) {
            try {
                System.out.print("Make a selection: ");
                String command = scan.nextLine();
                switch (command.toLowerCase()) {
                    case ("a"):
                        addCalendar(user);
                        break;
                    case ("t"):
                        changeTimezone(user);
                        break;
                    case ("d"):
                        removeCalendar(user);
                        break;
                    case ("m"):
                        monthlyView(user);
                        break;
                    case ("v"):
                        updateCalendarList(user);
                        break;
                    case ("e"):
                        addEventToCalendar(user);
                        break;
                    case ("r"):
                        removeEvent(user);
                        break;
                    case ("u"):
                        displayEvents(user);
                        break;
                    case ("q"):
                        System.exit(1);
                }
            } catch (Exception e) {
                System.out.println("Not a valid option. Try again");
            }
        }
    }

}
