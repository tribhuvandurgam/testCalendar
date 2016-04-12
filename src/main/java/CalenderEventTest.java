/**
 * Created by tribhuvand on 6/4/16.
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;

public class CalenderEventTest {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        Event event = new Event();
        EventReminder[] reminderOverrides = new EventReminder[] {
                new EventReminder().setMethod("email").setMinutes(60),
                new EventReminder().setMethod("popup").setMinutes(10),
        };
        Calendar service =null;

        event.setSummary("Event summary");
        event.setLocation("Your Location");
        event.setDescription("Desired description");
        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
        event.setReminders(reminders);

        ArrayList<EventAttendee> attendees = new ArrayList<EventAttendee>();
        attendees.add(new EventAttendee().setEmail("Attendeee mail ID"));
        // ...
        event.setAttendees(attendees);


        // set the number of days
        java.util.Calendar startCal = java.util.Calendar.getInstance();
        startCal.set(java.util.Calendar.MONTH, 3);
        startCal.set(java.util.Calendar.DATE, 6);
        startCal.set(java.util.Calendar.HOUR_OF_DAY, 9);
        startCal.set(java.util.Calendar.MINUTE, 0);
        Date startDate = startCal.getTime();

        java.util.Calendar endCal = java.util.Calendar.getInstance();
        endCal.set(java.util.Calendar.MONTH, 3);
        endCal.set(java.util.Calendar.DATE, 6);
        endCal.set(java.util.Calendar.HOUR_OF_DAY, 18);
        endCal.set(java.util.Calendar.MINUTE, 0);
        Date endDate = endCal.getTime();


        DateTime start = new DateTime(startDate);
        event.setStart(new EventDateTime().setDateTime(start));
        DateTime end = new DateTime(endDate);
        event.setEnd(new EventDateTime().setDateTime(end));

        service = new CalendarService().configure();
        Event createdEvent = service.events().insert("primary", event).execute();

        System.out.println("Data is :"+createdEvent.getId());
    }
}