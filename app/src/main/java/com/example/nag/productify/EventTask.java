package com.example.nag.productify;

import android.annotation.TargetApi;
import android.util.Log;

import com.google.api.client.util.DateTime;

//import org.joda.time.Days;
//import org.joda.time.ReadableDuration;

import java.io.Serializable;
import java.util.ArrayList;

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

	private boolean mon;
	private boolean tues;
	private boolean wed;
	private boolean thurs;
	private boolean fri;
	private boolean sat;
	private boolean sund;

	private String name;
	private double predictedTime;

	private DateTime dst;
	private DateTime det;

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

	}

	/*
	 * An com.example.nag.productify.EventTask object;
	 */
	public EventTask(int sy, int sm, int sd, int sh, int smin, int ey, int em, int ed, int eh, int emin, String nm, double pred, boolean mo, boolean tu, boolean we, boolean th, boolean fr, boolean sa, boolean su)
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
	public void setMon(boolean monday)
	{
		mon = monday;
	}

	/**
	 * Determines if Tuesday is an available day
	 * @param tuesday if true, means that Tuesday is available for working, if false, means that Tuesday is not available
	 */
	public void setTues(boolean tuesday)
	{
		tues = tuesday;
	}

	/**
	 * Determines if Wednesday is an available day
	 * @param wednesday if true, means that Wednesday is available for working, if false, means that Wednesday is not available
	 */
	public void setWed(boolean wednesday)
	{
		wed = wednesday;
	}

	/**
	 * Determines if Thursday is an available day
	 * @param thursday if true, means that Thursday is available for working, if false, means that Thursday is not available
	 */
	public void setThurs(boolean thursday)
	{
		thurs = thursday;
	}

	/**
	 * Determines if Friday is an available day
	 * @param friday if true, means that Friday is available for working, if false, means that Friday is not available
	 */
	public void setFri(boolean friday)
	{
		fri = friday;
	}

	/**
	 * Determines if Saturday is an available day
	 * @param saturday if true, means that Saturday is available for working, if false, means that Saturday is not available
	 */
	public void setSat(boolean saturday)
	{
		sat = saturday;
	}

	/**
	 * Determines if Sunday is an available day
	 * @param sunday if true, means that Sunday is available for working, if false, means that Sunday is not available
	 */
	public void setSun(boolean sunday)
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
	public boolean getMon()
	{
		return mon;
	}

	/**
	 * Gets the user's availability for Tuesday
	 * @return the availability of Tuesday
	 */
	public boolean getTues()
	{
		return tues;
	}

	/**
	 * Gets the user's availability for Wednesday
	 * @return the availability of Wednesday
	 */
	public boolean getWed()
	{
		return wed;
	}

	/**
	 * Gets the user's availability for Thursday
	 * @return the availability of Thursday
	 */
	public boolean getThurs()
	{
		return thurs;
	}

	/**
	 * Gets the user's availability for Friday
	 * @return the availability of Friday
	 */
	public boolean getFri()
	{
		return fri;
	}

	/**
	 * Gets the user's availability for Saturday
	 * @return the availability of Saturday
	 */
	public boolean getSat()
	{
		return sat;
	}

	/**
	 * Gets the user's availability for Sunday
	 * @return the availability of Sunday
	 */
	public boolean getSun() { return sund; }
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
	 * Creates an arraylist of datetimes which contain the starting time and ending time of the interim due dates
	 * @return an arraylist of datetimes which contain the starting time and ending time of the interim due dates
	 */
	public ArrayList<DateTime> createEventDates()
    {
		ArrayList<DateTime> dateList = new ArrayList<>();

		int scheduledDays = getDaysBetween();
		Log.d("days between", scheduledDays + "");
		double eventTime = ((double) predictedTime)/scheduledDays;
		int hourLength = (int) eventTime;
		int minuteLength = (int) ((double)(eventTime - hourLength)*60);


		org.joda.time.DateTime start = new org.joda.time.DateTime(startYear, startMonth, startDay, startHour, startMinute, 0);
		org.joda.time.DateTime end = new org.joda.time.DateTime(endYear,endMonth,endDay,endHour,endMinute,0);

			for(org.joda.time.DateTime currentDate = start.plusDays(1); currentDate.isBefore(end); currentDate = currentDate.plusDays(1))
			{
				int year = currentDate.getYear();
				int month = currentDate.getMonthOfYear();
				int day = currentDate.getDayOfMonth();

				int dayOfWeek = currentDate.getDayOfWeek();

				if (!mon && dayOfWeek == 1) {

				} else if (!tues && dayOfWeek == 2) {

				} else if (!wed && dayOfWeek == 3) {

				} else if (!thurs && dayOfWeek == 4) {

				} else if (!fri && dayOfWeek == 5) {

				} else if (!sat && dayOfWeek == 6) {

				} else if (!sund && dayOfWeek == 7) {

				} else {
					DateTime beginTime = createDateTime(year, month, day, 8, 0);
					DateTime endTime = createDateTime(year, month, day, hourLength + 8, minuteLength);

					dateList.add(beginTime);
					dateList.add(endTime);

				}
			}

        if(getSY() != getEY()) {

            for (int j = getSY() + 1; j == getSY(); j++) {
                int year = j;
                for (int k = 1; k <= getEM(); k++) {

                    int month = k;

                    for (int d = 1; d < getED(); d++) {

                        int day = d;

                        org.joda.time.DateTime checkTime = new org.joda.time.DateTime(year,month,day,0,0);
                        int dayOfWeek = checkTime.getDayOfWeek();

                        if (!mon && dayOfWeek == 1) {

                        } else if (!tues && dayOfWeek == 2) {

                        } else if (!wed && dayOfWeek == 3) {

                        } else if (!thurs && dayOfWeek == 4) {

                        } else if (!fri && dayOfWeek == 5) {

                        } else if (!sat && dayOfWeek == 6) {

                        } else if (!sund && dayOfWeek == 7) {

                        } else {
                            DateTime beginTime = createDateTime(year, month, day, 0, 0);
                            DateTime endTime = createDateTime(year, month, day, hourLength, minuteLength);

                            dateList.add(beginTime);
                            dateList.add(endTime);
                        }
                    }
                }
            }
        }
        return dateList;
    }

	/**
	 * Calculates the number of days from the state date to the end date
	 * @return the number of days from the start date to the end date
	 */
    private int getDaysBetween()
    {
        int scheduledDays = 0;
        org.joda.time.DateTime start = new org.joda.time.DateTime(startYear, startMonth, startDay, startHour, startMinute);
        org.joda.time.DateTime end = new org.joda.time.DateTime(endYear, endMonth, endDay, endHour, endMinute);

        for (org.joda.time.DateTime currentDate = start.plusDays(1); currentDate.isBefore(end); currentDate = currentDate.plusDays(1)) {
            int year = currentDate.getYear();
            int month = currentDate.getMonthOfYear();
            int day = currentDate.getDayOfMonth();

            int dayOfWeek = currentDate.getDayOfWeek();

            if (!mon && dayOfWeek == 1) {

            } else if (!tues && dayOfWeek == 2) {

            } else if (!wed && dayOfWeek == 3) {

            } else if (!thurs && dayOfWeek == 4) {

            } else if (!fri && dayOfWeek == 5) {

            } else if (!sat && dayOfWeek == 6) {

            } else if (!sund && dayOfWeek == 7) {

            } else {
                scheduledDays++;

            }
        }

        return scheduledDays;
    }

	// ---------------HELPER METHODS---------------

    /**
     * Creates a DateTime to be used in the arraylist of Events
     * @return the DateTime to be used in the arraylist
     */
    private DateTime createDateTime(int year, int month, int day, int hour, int minute)
    {
        String monthStr;
        String dayStr;
        String hourStr;
        String minuteStr;
        String yearStr = year + "-";
        if(month < 10)
        {
            monthStr = "0" + month + "-";
        }
        else
            monthStr = month + "-";
        if(day < 10)
        {
            dayStr = "0" + day + "T";
        }
        else
            dayStr = day + "T";
        if(hour < 10)
        {
            hourStr = "0" + hour + ":";
        }
        else
            hourStr = hour + ":";
        if(minute < 10)
        {
            minuteStr = "0" + minute + ":00-04:00";
        }
        else
            minuteStr = minute + ":00-04:00";

        DateTime DT = new DateTime(yearStr + monthStr + dayStr + hourStr + minuteStr);
        return DT;
    }

	/**
	 * Creates a DateTime object for the assignment's start-date
	 * @return the start-date of the com.example.nag.productify.EventTask
	 */
	private DateTime makeDST()
	{
        String monthStr;
        String dayStr;
        String hourStr;
        String minuteStr;
        String yearStr = startYear + "-";
        if(startMonth < 10)
        {
            monthStr = "0" + startMonth + "-";
        }
        else
            monthStr = startMonth + "-";
        if(startDay < 10)
        {
            dayStr = "0" + startDay + "T";
        }
        else
            dayStr = startDay + "T";
        if(startHour < 10)
        {
            hourStr = "0" + startHour + ":";
        }
        else
            hourStr = startHour + ":";
        if(startMinute < 10)
        {
            minuteStr = "0" + startMinute + ":00-04:00";
        }
        else
            minuteStr = startMinute + ":00-04:00";

        DateTime DST = new DateTime(yearStr + monthStr + dayStr + hourStr + minuteStr);
		return DST;
	}

    /**
     * Creates a dateTime object for the assignment's end-date
     * @return the end-date of the EventTask
     */
    private DateTime makeDET()
    {
        String monthStr;
        String dayStr;
        String hourStr;
        String minuteStr;
        String yearStr = endYear + "-";
        if(endMonth < 10)
        {
            monthStr = "0" + endMonth + "-";
        }
        else
            monthStr = endMonth + "-";
        if(endDay < 10)
        {
            dayStr = "0" + endDay + "T";
        }
        else
            dayStr = endDay + "T";
        if(endHour < 10)
        {
            hourStr = "0" + endHour + ":";
        }
        else
            hourStr = endHour + ":";
        if(endMinute < 10)
        {
            minuteStr = "0" + endMinute + ":00-04:00";
        }
        else
            minuteStr = endMinute + ":00-04:00";

        DateTime DET = new DateTime(yearStr + monthStr + dayStr + hourStr + minuteStr);

        return DET;
    }

}
