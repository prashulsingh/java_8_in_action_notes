package Java8_In_action.Chapter12_New_Date_Time_API;

import java.time.*;
import java.time.temporal.ChronoField;
import  java.time.temporal.TemporalAdjuster.*;
import java.time.temporal.TemporalAdjusters;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.*;

class Main
{
    public static void main(String[] args) {
        //In Java 1.0 the only support for date and time was the java.util.Date class

        //name, this class doesn’t represent a date but a point in time with milliseconds precision.

        Date date = new Date();
        // Time in milliseconds

        System.out.println( date.getTime() );
        LocalDate localDate = LocalDate.now();

        System.out.println(localDate );

        //Cannot get tiem from the new Date APi
        System.out.println( localDate.getMonth() + " " + localDate.getDayOfMonth() +" " +  localDate.getYear());

        //Time is represented by LocalTime now
        LocalTime localTime =   LocalTime.now();

        System.out.println(localTime);
        //Both LocalDate and LocalTime can be created by parsing a String representing them.
        // You can achieve this using their parse static methods:


        //12.1.2. Combining a date and a time


        //The composite class called LocalDateTime pairs a LocalDate and a LocalTime.
        LocalDateTime localDateTime = LocalDateTime.now();

        localDateTime.toLocalDate();
        localDateTime.toLocalTime();
        System.out.println(localDateTime);

        //12.1.3. Instant: a date and time for machines

/*
As humans we’re used to thinking of dates and time in terms of weeks,
days, hours, and minutes. Nonetheless, this representation isn’t easy for a
 computer to deal with. From a machine point of view, the most natural format to
  model time is with a single large number representing a point on a continuous timeline.
  This is the approach used by the new java.time.Instant class, which basically represents
  the number of seconds passed since the Unix epoch time, set by convention to midnight of January 1, 1970 UTC.

 */
        //equal to earlier Date.getTime();
        System.out.println(System.currentTimeMillis());
        System.out.println( Instant.now().getEpochSecond());

        //12.1.4. Defining a Duration or a Period
    //The next natural step is to create a duration between two temporal objects.
        // Moreover, because the Duration class is used to represent an amount of time measured
        // in seconds and eventually nanoseconds, you can’t pass a LocalDate to the between method
        //e. You can create a duration between two LocalTimes, two LocalDateTimes, or two Instants as follows:


        Duration d1 = Duration.between(LocalTime.now(), LocalTime.now());

        System.out.println(d1.isZero() );

        //When you need to model an amount of time in terms of years, months, and days, you can use the Period class. Y

        Period tenDays = Period.between(LocalDate.of(2014, 3, 8),
                LocalDate.of(2014, 3, 18));


        //12.2. MANIPULATING, PARSING, AND FORMATTING DATES
        //Quiz 12.1: Manipulating a LocalDate

        LocalDate date1 = LocalDate.of(2014, 3, 18);

        date1 = date1.with(ChronoField.MONTH_OF_YEAR, 9); // change date to 2014-9-18
        date1= date1.plusYears(2).minusDays(10); // 2016-9-08
        date1.withYear(2011);

        //12.2.1. Working with TemporalAdjusters

        /*
        ll the date manipulations you’ve seen so far are relatively straightforward.
         Sometimes, you may need to perform more advanced operations, such as adjusting a date to
          the next Sunday, the next working day, or the last day of the month.

         */
        date1 = LocalDate.of(2014, 3, 18);
        System.out.println( date1.with(TemporalAdjusters.lastDayOfMonth()) );
        //he getValue() method of java.time.DayOfWeek is an in-built function in Java which return
        // the integer value assigned to the 7 days of the week, i.e, Monday, Tuesday, Wednesday, Thursday,
        // Friday, Saturday and Sunday. The int value follows the ISO-8601 standard, from 1 (Monday) to 7 (Sunday).

        // date previous to the date1 and when the day was friday
        System.out.println( date1.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY)) );
        date1 = LocalDate.now();
        System.out.println( date1.with(TemporalAdjusters.next(DayOfWeek.MONDAY)) );


        //Quiz 12.2: Implementing a custom TemporalAdjuster
        System.out.println(date1.getDayOfYear());


    }
}