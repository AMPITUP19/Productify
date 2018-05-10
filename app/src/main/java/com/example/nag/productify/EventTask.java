package com.example.nag.productify;

import android.accounts.Account;
import android.annotation.TargetApi;
import android.content.Intent;

import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.FreeBusyRequest;
import com.google.api.services.calendar.model.FreeBusyResponse;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

@TargetApi(26)

/**
 * An com.example.nag.productify.EventTask object will store the start-date, end-date, name, and estimated duration of an assignment. Hours are in military time.
 * @author NAG
 */
public class EventTask implements Serializable{

	private int startYear;
	private int startMonth;
	private int startDay;
	private int startHour;
	private int startMinute;

	private int endYear;
	private int endMonth;
	private int endDay;
	private int endHour;
	private int endMinute;

	private Boolean mon;
	private Boolean tues;
	private Boolean wed;
	private Boolean thurs;
	private Boolean fri;
	private Boolean sat;
	private Boolean sund;

	private String name;
	private double predictedTime;

	private DateTime dst;
	private DateTime det;

	/* Use later to take time zones into account
	private TimeZone tz;
	private ZoneId zi;

	private ZonedDateTime zdst;
	private ZonedDateTime zdet;
	*/

	/*
	 * An empty constructor for an com.example.nag.productify.EventTask object.
	 */
	public EventTask()
	{
		startYear = 1;
		startMonth = 1;
		startDay = 1;
		startHour = 0;
		startMinute = 0;

		endYear = 2;
		endMonth = 2;
		endDay = 2;
		endHour = 2;
		endMinute = 2;

		mon = true;
		tues = true;
		wed = true;
		thurs = true;
		fri = true;
		sat = true;
		sund = true;

		name = "Unset";
		predictedTime = 0;

		dst = makeDST();
		det = makeDET();

		/* Use later to take time zones into account
		tz = TimeZone.getDefault();
		zi = tz.toZoneId();


		zdst = ZonedDateTime.of(startYear, startMonth, startDay, startHour, startMinute, startSecond, startNanoSecond, zi);
		zdet = ZonedDateTime.of(endYear, endMonth,endDay,endHour,endMinute,endSecond,endNanoSecond,zi);
		*/
	}

	/*
	 * An com.example.nag.productify.EventTask object;
	 */
	public EventTask(int sy, int sm, int sd, int sh, int smin, int ey, int em, int ed, int eh, int emin, String nm, double pred, Boolean mo, Boolean tu, Boolean we, Boolean th, Boolean fr, Boolean sa, Boolean su)
	{
		startYear = sy;
		startMonth = sm;
		startDay = sd;
		startHour = sh;
		startMinute = smin;

		endYear = ey;
		endMonth = em;
		endDay = ed;
		endHour = eh;
		endMinute = emin;

		mon = mo;
		tues = tu;
		wed = we;
		thurs = th;
		fri = fr;
		sat = sa;
		sund = su;

		name = nm;
		predictedTime = pred;

		dst = makeDST();
		det = makeDET();

		/* Use in future to take time zones into account
		tz = TimeZone.getDefault();
		zi = tz.toZoneId();

		zdst = ZonedDateTime.of(startYear, startMonth, startDay, startHour, startMinute, startSecond, startNanoSecond, zi);
		zdet = ZonedDateTime.of(endYear, endMonth,endDay,endHour,endMinute,endSecond,endNanoSecond,zi);
		*/
	}

	// ---------------SETTERS---------------

	/**
	 * Sets the assignment's start-date year
	 * @param sy the start-date year of the com.example.nag.productify.EventTask
	 */
	public void setSY(int sy)
	{
		startYear = sy;
	}

	/**
	 * Sets the assignment's end-date year
	 * @param ey the end-date year of the Event
	 */
	public void setEY(int ey)
	{
		endYear = ey;
	}

	/**
	 * Sets the assignment's start-date month
	 * @param sm the start-date month of the com.example.nag.productify.EventTask
	 */
	public void setSM(int sm)
	{
		startMonth = sm;
	}

	/**
	 * Sets the assignment's end-date month
	 * @param em the end-date month of the com.example.nag.productify.EventTask
	 */
	public void setEM(int em)
	{
		endMonth = em;
	}

	/**
	 * Sets the assignment's start-date day
	 * @param sd the start-date day of the com.example.nag.productify.EventTask
	 */
	public void setSD(int sd)
	{
		startDay = sd;
	}

	/**
	 * Sets the assignment's end-date day
	 * @param ed the end-date day of the com.example.nag.productify.EventTask
	 */
	public void setED(int ed)
	{
		endDay = ed;
	}

	/**
	 * Sets the assignment's start-date hour
	 * @param sh the start-date hour of the com.example.nag.productify.EventTask
	 */
	public void setSH(int sh)
	{
		startHour = sh;
	}

	/**
	 * Sets the assignment's end-date hour
	 * @param eh the end-date hour of the com.example.nag.productify.EventTask
	 */
	public void setEH(int eh)
	{
		endHour = eh;
	}

	/**
	 * Sets the assignment's start-date minute
	 * @param smin the start-date minute of the com.example.nag.productify.EventTask
	 */
	public void setSMIN(int smin)
	{
		startMinute = smin;
	}

	/**
	 * Sets the assignment's end-date minute
	 * @param emin the end-date minute of the com.example.nag.productify.EventTask
	 */
	public void setEMIN(int emin)
	{
		endMinute = emin;
	}

	/**
	 * Determines if Monday is an available day
	 * @param monday if true, means that Monday is available for working, if false, means that Monday is not available
	 */
	public void setMon(Boolean monday)
	{
		mon = monday;
	}

	/**
	 * Determines if Tuesday is an available day
	 * @param tuesday if true, means that Tuesday is available for working, if false, means that Tuesday is not available
	 */
	public void setTues(Boolean tuesday)
	{
		tues = tuesday;
	}

	/**
	 * Determines if Wednesday is an available day
	 * @param wednesday if true, means that Wednesday is available for working, if false, means that Wednesday is not available
	 */
	public void setWed(Boolean wednesday)
	{
		wed = wednesday;
	}

	/**
	 * Determines if Thursday is an available day
	 * @param thursday if true, means that Thursday is available for working, if false, means that Thursday is not available
	 */
	public void setThurs(Boolean thursday)
	{
		thurs = thursday;
	}

	/**
	 * Determines if Friday is an available day
	 * @param friday if true, means that Friday is available for working, if false, means that Friday is not available
	 */
	public void setFri(Boolean friday)
	{
		fri = friday;
	}

	/**
	 * Determines if Saturday is an available day
	 * @param saturday if true, means that Saturday is available for working, if false, means that Saturday is not available
	 */
	public void setSat(Boolean saturday)
	{
		sat = saturday;
	}

	/**
	 * Determines if Sunday is an available day
	 * @param sunday if true, means that Sunday is available for working, if false, means that Sunday is not available
	 */
	public void setSund(Boolean sunday)
	{
		sund = sunday;
	}

	/**
	 * Sets the assignment's name
	 * @param nm the name of the com.example.nag.productify.EventTask
	 */
	public void setName(String nm)
	{
		name = nm;
	}

	/**
	 * Sets the assignment's predicted time
	 * @param pred the predicted time of the com.example.nag.productify.EventTask
	 */
	public void setPred(double pred)
	{
		predictedTime = pred;
	}

	// ---------------GETTERS---------------

	/**
	 * Gets the assignment's start-date year
	 * @return the start-date year of the com.example.nag.productify.EventTask
	 */
	public int getSY()
	{
		return startYear;
	}

	/**
	 * Gets the assignment's end-date year
	 * @return the end-date year of the com.example.nag.productify.EventTask
	 */
	public int getEY()
	{
		return endYear;
	}

	/**
	 * Gets the assignment's start-date month
	 * @return the start-date month of the com.example.nag.productify.EventTask
	 */
	public int getSM()
	{
		return startMonth;
	}

	/**
	 * Gets the assignment's end-date month
	 * @return the end-date month of the com.example.nag.productify.EventTask
	 */
	public int getEM()
	{
		return endMonth;
	}

	/**
	 * Gets the assignment's start-date day
	 * @return the start-date day of the com.example.nag.productify.EventTask
	 */
	public int getSD()
	{
		return startDay;
	}

	/**
	 * Gets the assignment's end-date day
	 * @return the end-date day of the com.example.nag.productify.EventTask
	 */
	public int getED()
	{
		return endDay;
	}

	/**
	 * Gets the assignment's start-date hour
	 * @return the start-date hour of the com.example.nag.productify.EventTask
	 */
	public int getSH()
	{
		return startHour;
	}

	/**
	 * Gets the assignment's end-date hour
	 * @return the end-date hour of the com.example.nag.productify.EventTask
	 */
	public int getEH()
	{
		return endHour;
	}

	/**
	 * Gets the assignment's start-date minute
	 * @return the start-date minute of the com.example.nag.productify.EventTask
	 */
	public int getSMIN()
	{
		return startMinute;
	}

	/**
	 * Gets the assignment's end-date minute
	 * @return the end-date minute of the com.example.nag.productify.EventTask
	 */
	public int getEMIN()
	{
		return endMinute;
	}

	/**
	 * Gets the user's availability for Monday
	 * @return the availability of Monday
	 */
	public Boolean getMon()
	{
		return mon;
	}

	/**
	 * Gets the user's availability for Tuesday
	 * @return the availability of Tuesday
	 */
	public Boolean getTues()
	{
		return tues;
	}

	/**
	 * Gets the user's availability for Wednesday
	 * @return the availability of Wednesday
	 */
	public Boolean getWed()
	{
		return wed;
	}

	/**
	 * Gets the user's availability for Thursday
	 * @return the availability of Thursday
	 */
	public Boolean getThurs()
	{
		return thurs;
	}

	/**
	 * Gets the user's availability for Friday
	 * @return the availability of Friday
	 */
	public Boolean getFri()
	{
		return fri;
	}

	/**
	 * Gets the user's availability for Saturday
	 * @return the availability of Saturday
	 */
	public Boolean getSat()
	{
		return sat;
	}

	/**
	 * Gets the user's availability for Sunday
	 * @return the availability of Sunday
	 */
	public Boolean getSund()
	{
		return sund;
	}
	/**
	 * Gets the assignment's name
	 * @return name of the com.example.nag.productify.EventTask
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Gets the assignment's predicted duration
	 * @return the predicted duration of the com.example.nag.productify.EventTask
	 */
	public double getPred()
	{
		return predictedTime;
	}

	/**
	 * Returns the assignment's start-date as a DateTime
	 * @return the start-date of the com.example.nag.productify.EventTask
	 */
	public DateTime getDST()
	{
		return makeDST();
	}

	/**
	 * Returns the assignment's end-date as a DateTime
	 * @return the end-date of the com.example.nag.productify.EventTask
	 */
	public DateTime getDET()
	{
		return makeDET();
	}

	// ---------------FUNCTIONAL METHODS---------------

	/**
	 * Puts events into the Google Calendar.
	 * @param mCredential the user's Google account
	 */
	public void createEvents(GoogleAccountCredential mCredential) {

        ArrayList<Event> dailyEvents = new ArrayList<Event>();
        int scheduledDays = getDaysBetween();
        double eventLength = predictedTime / ((double) scheduledDays);

        int hourLength = (int) eventLength;
        int minuteLength = (int) (eventLength - ((int) eventLength)) * 60;

        for (int j = getSY(); j <= getEY(); j++) {
            int year = j;

            for (int k = getSM(); k <= getEM(); k++) {
                int month = k;

                for (int d = getSD() + 1; d < getED(); d++) {
                    int day = d;

                    LocalDateTime checkDate = LocalDateTime.of(year, month, day, 0,0);
                    String dayOfWeek = checkDate.getDayOfWeek().toString();

                    if(!mon && dayOfWeek == "MONDAY")
                    {

                    }
                    else if(!tues && dayOfWeek == "TUESDAY")
                    {

                    }
                    else if(!wed && dayOfWeek == "WEDNESDAY")
                    {

                    }
                    else if(!thurs && dayOfWeek == "THURSDAY")
                    {

                    }
                    else if(!fri && dayOfWeek == "FRIDAY")
                    {

                    }
                    else if(!sat && dayOfWeek == "SATURDAY")
                    {

                    }
                    else if(!sund && dayOfWeek == "SUNDAY")
                    {

                    }
                    else {
                        DateTime beginTime = createDateTime(year, month, day, 0, 0);
                        DateTime endTime = createDateTime(year, month, day, hourLength, minuteLength);

                        Event event = new Event();
                        event.setSummary(name + "Assignment: " + month + "/" + day + "/" + year);
                        dailyEvents.add(event);
                    }
                }
            }
        }

		Account calendarId = mCredential.getSelectedAccount();


	}

	/**
	 * Calculates the number of days from the state date to the end date
	 * @return the number of days from the start date to the end date
	 */
	public int getDaysBetween()
	{
		int totalDays;
		int removedDays;
		LocalDateTime sDate = LocalDateTime.of(startYear, startMonth, startDay, startHour, startMinute);
		LocalDateTime eDate = LocalDateTime.of(endYear, endMonth, endDay, endHour, endMinute);

		Duration timeBetween = Duration.between(sDate, eDate);
		totalDays = (int) timeBetween.toDays();
		removedDays = 0;

		for(LocalDateTime chosenDate = sDate.plusDays(1); chosenDate.isBefore(eDate); chosenDate = chosenDate.plusDays(1))
		{
			String weekDay = chosenDate.getDayOfWeek().toString();
			if(!mon && weekDay == "MONDAY")
			{
				removedDays++;
			}
			else if(!tues && weekDay == "TUESDAY")
			{
				removedDays++;
			}
			else if(!wed && weekDay == "WEDNESDAY")
			{
				removedDays++;
			}
			else if(!thurs && weekDay == "THURSDAY")
			{
				removedDays++;
			}
			else if(!fri && weekDay == "FRIDAY")
			{
				removedDays++;
			}
			else if(!sat && weekDay == "SATURDAY")
			{
				removedDays++;
			}
			else if(!sund && weekDay == "SUNDAY")
			{
				removedDays++;
			}
		}

		return (totalDays - removedDays);
	}
	/*public String checkIfFree(DateTime startTimeCheck, DateTime endTimeCheck)
	{
		FreeBusyRequest req = new FreeBusyRequest();
		req.setTimeMin(startTimeCheck);
		req.setTimeMax(endTimeCheck);

		Calendar.Freebusy.Query fbquery = client.freebusy().query(req);

		FreeBusyResponse fbresponse = fbquery.execute();

	}*/



	// ---------------HELPER METHODS---------------

	/**
	 * Creates a string containing the information of the assignment's start date
	 * @return start date infoString of the com.example.nag.productify.EventTask
	 */
	private String makeInfoStringStart()
	{
		String infoString = startYear + "-";
		if(startMonth < 10)
		{
			infoString += "0" + startMonth + "-";
		}
		else
			infoString += startMonth + "-";

		if(startDay < 10)
		{
			infoString += "0" + startDay + "T";
		}
		else
			infoString += startDay + "T";

		if(startHour < 10)
		{
			infoString += "0" + startHour + ":";
		}
		else
			infoString += startHour + ":";

		if(startMinute < 10)
		{
			infoString += "0" + startMinute + ":00.00Z";
		}
		else
			infoString += startMinute + ":00.00Z";

		return infoString;
	}

	/**
	 * Creates a string containing the information of the assignment's end date
	 * @return end date infoString of the com.example.nag.productify.EventTask
	 */
	private String makeInfoStringEnd()
	{
		String infoString = endYear + "-";
		if(endMonth < 10)
		{
			infoString += "0" + endMonth + "-";
		}
		else
			infoString += endMonth + "-";

		if(endDay < 10)
		{
			infoString += "0" + endDay + "T";
		}
		else
			infoString += endDay + "T";

		if(endHour < 10)
		{
			infoString += "0" + endHour + ":";
		}
		else
			infoString += endHour + ":";

		if(endMinute < 10)
		{
			infoString += "0" + endMinute + ":00.00Z";
		}
		else
			infoString += endMinute + ":00.00Z";

		return infoString;
	}

    /**
     * Creates a DateTime to be used in the arraylist of Events
     * @return the DateTime to be used in the arraylist
     */
    private DateTime createDateTime(int year, int month, int day, int hour, int minute)
    {
        String infoString = year + "-";
        if(month < 10)
        {
            infoString += "0" + month + "-";
        }
        else
            infoString += month + "-";

        if(day < 10)
        {
            infoString += "0" + day + "T";
        }
        else
            infoString += day + "T";

        if(hour < 10)
        {
            infoString += "0" + hour + ":";
        }
        else
            infoString += hour + ":";

        if(minute < 10)
        {
            infoString += "0" + minute + ":00.00Z";
        }
        else
            infoString += minute + ":00.00Z";

      DateTime DT = DateTime.parseRfc3339(infoString);
      return DT;
    }

	/**
	 * Creates a DateTime object for the assignment's start-date
	 * @return the start-date of the com.example.nag.productify.EventTask
	 */
	private DateTime makeDST()
	{
		String infoString = makeInfoStringStart();
		//DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		DateTime DST = DateTime.parseRfc3339(infoString);
		return DST;
	}

    /**
     * Creates a dateTime object for the assignment's end-date
     * @return the end-date of the EventTask
     */
    private DateTime makeDET()
    {
        String infoString = makeInfoStringEnd();
        //DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        DateTime DET = DateTime.parseRfc3339(infoString);
        return DET;
    }

	/* Use in future to take time zones into account
	public ZonedDateTime getZDST()
	{
		return zdst;
	}

	public ZonedDateTime getZDET()
	{
		return zdet;
	}
	*/




}
