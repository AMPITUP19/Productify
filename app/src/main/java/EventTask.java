import android.annotation.TargetApi;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TimeZone;

@TargetApi(26)

/**
 * An EventTask object will store the start-date, end-date, name, and estimated duration of an assignment. Hours are in military time.
 * @author NAG
 *
 */
public class EventTask {

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

	private String name;
	private double predictedTime;

	private LocalDateTime ldst;
	private LocalDateTime ldet;

	/* Use later to take time zones into account
	private TimeZone tz;
	private ZoneId zi;

	private ZonedDateTime zdst;
	private ZonedDateTime zdet;
	*/

	/*
	 * An empty constructor for an Event object.
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

		name = "Unset";
		predictedTime = 0;

		ldst = makeLDST();
		ldet = makeLDET();

		/* Use later to take time zones into account
		tz = TimeZone.getDefault();
		zi = tz.toZoneId();


		zdst = ZonedDateTime.of(startYear, startMonth, startDay, startHour, startMinute, startSecond, startNanoSecond, zi);
		zdet = ZonedDateTime.of(endYear, endMonth,endDay,endHour,endMinute,endSecond,endNanoSecond,zi);
		*/
	}

	/*
	 * An Event object;
	 */
	public EventTask(int sy, int sm, int sd, int sh, int smin, int ey, int em, int ed, int eh, int emin, String nm, double pred)
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

		name = nm;
		predictedTime = pred;

		ldst = makeLDST();
		ldet = makeLDET();

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
	 * @param sy the start-date year of the Event
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
	 * @param sm the start-date month of the Event
	 */
	public void setSM(int sm)
	{
		startMonth = sm;
	}

	/**
	 * Sets the assignment's end-date month
	 * @param em the end-date month of the Event
	 */
	public void setEM(int em)
	{
		endMonth = em;
	}

	/**
	 * Sets the assignment's start-date day
	 * @param sd the start-date day of the Event
	 */
	public void setSD(int sd)
	{
		startDay = sd;
	}

	/**
	 * Sets the assignment's end-date day
	 * @param ed the end-date day of the Event
	 */
	public void setED(int ed)
	{
		endDay = ed;
	}

	/**
	 * Sets the assignment's start-date hour
	 * @param sh the start-date hour of the Event
	 */
	public void setSH(int sh)
	{
		startHour = sh;
	}

	/**
	 * Sets the assignment's end-date hour
	 * @param eh the end-date hour of the Event
	 */
	public void setEH(int eh)
	{
		endHour = eh;
	}

	/**
	 * Sets the assignment's start-date minute
	 * @param smin the start-date minute of the Event
	 */
	public void setSMIN(int smin)
	{
		startMinute = smin;
	}

	/**
	 * Sets the assignment's end-date minute
	 * @param emin the end-date minute of the Event
	 */
	public void setEMIN(int emin)
	{
		endMinute = emin;
	}

	/**
	 * Sets the assignment's name
	 * @param nm the name of the Event
	 */
	public void setName(String nm)
	{
		name = nm;
	}

	/**
	 * Sets the assignment's predicted time
	 * @param pred the predicted time of the Event
	 */
	public void setPred(double pred)
	{
		predictedTime = pred;
	}

	// ---------------GETTERS---------------

	/**
	 * Gets the assignment's start-date year
	 * @return the start-date year of the Event
	 */
	public int getSY()
	{
		return startYear;
	}

	/**
	 * Gets the assignment's end-date year
	 * @return the end-date year of the Event
	 */
	public int getEY()
	{
		return endYear;
	}

	/**
	 * Gets the assignment's start-date month
	 * @return the start-date month of the Event
	 */
	public int getSM()
	{
		return startMonth;
	}

	/**
	 * Gets the assignment's end-date month
	 * @return the end-date month of the Event
	 */
	public int getEM()
	{
		return endMonth;
	}

	/**
	 * Gets the assignment's start-date day
	 * @return the start-date day of the Event
	 */
	public int getSD()
	{
		return startDay;
	}

	/**
	 * Gets the assignment's end-date day
	 * @return the end-date day of the Event
	 */
	public int getED()
	{
		return endDay;
	}

	/**
	 * Gets the assignment's start-date hour
	 * @return the start-date hour of the Event
	 */
	public int getSH()
	{
		return startHour;
	}

	/**
	 * Gets the assignment's end-date hour
	 * @return the end-date hour of the Event
	 */
	public int getEH()
	{
		return endHour;
	}

	/**
	 * Gets the assignment's start-date minute
	 * @return the start-date minute of the Event
	 */
	public int getSMIN()
	{
		return startMinute;
	}

	/**
	 * Gets the assignment's end-date minute
	 * @return the end-date minute of the Event
	 */
	public int getEMIN()
	{
		return endMinute;
	}

	/**
	 * Gets the assignment's name
	 * @return name of the Event
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Gets the assignment's predicted duration
	 * @return the predicted duration of the Event
	 */
	public double getPred()
	{
		return predictedTime;
	}

	/**
	 * Returns the assignment's start-date as a LocalDateTime
	 * @return the start-date of the Event
	 */
	public LocalDateTime getLDST()
	{
		return makeLDST();
	}

	/**
	 * Returns the assignment's end-date as a LocalDateTime
	 * @return the end-date of the Event
	 */
	public LocalDateTime getLDET()
	{
		return makeLDET();
	}

	// ---------------HELPER METHODS---------------

	/**
	 * Creates a string containing the information of the assignment's start date
	 * @return start date infoString of the Event
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
			infoString += "0" + startDay + " ";
		}
		else
			infoString += startDay + " ";

		if(startHour < 10)
		{
			infoString += "0" + startHour + ":";
		}
		else
			infoString += startHour + ":";

		if(startMinute < 10)
		{
			infoString += "0" + startMinute;
		}
		else
			infoString += startMinute;

		return infoString;
	}

	/**
	 * Creates a string containing the information of the assignment's end date
	 * @return end date infoString of the Event
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
			infoString += "0" + endDay + " ";
		}
		else
			infoString += endDay + " ";

		if(endHour < 10)
		{
			infoString += "0" + endHour + ":";
		}
		else
			infoString += endHour + ":";

		if(endMinute < 10)
		{
			infoString += "0" + endMinute;
		}
		else
			infoString += endMinute;

		return infoString;
	}

	/**
	 * Creates a LocalDateTime object for the assignment's start-date
	 * @return the start-date of the Event
	 */
	private LocalDateTime makeLDST()
	{
		String infoString = makeInfoStringStart();
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime LDST = LocalDateTime.parse(infoString, timeFormat);
		return LDST;
	}

	/**
	 * Creates a LocalDateTime object for the assignment's end-date
	 * @return the end-date of the Event
	 */
	private LocalDateTime makeLDET()
	{
		String infoString = makeInfoStringEnd();
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime LDET = LocalDateTime.parse(infoString, timeFormat);
		return LDET;
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
