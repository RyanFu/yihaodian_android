// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thoughtworks.xstream.converters.extended;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
import java.util.*;

public class ISO8601GregorianCalendarConverter extends AbstractSingleValueConverter
{

    public ISO8601GregorianCalendarConverter()
    {
    }

    static Class _mthclass$(String s)
    {
        Class class1;
        try
        {
            class1 = Class.forName(s);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
        return class1;
    }

    @Override
	public boolean canConvert(Class class1)
    {
        Class class2;
        if(class$java$util$GregorianCalendar == null)
        {
            class2 = _mthclass$("java.util.GregorianCalendar");
            class$java$util$GregorianCalendar = class2;
        } else
        {
            class2 = class$java$util$GregorianCalendar;
        }
        return class1.equals(class2);
    }

    @Override
	public Object fromString(String s)
    {
        int i = 0;
_L6:
        if(i >= formattersUTC.length) goto _L2; else goto _L1
_L1:
        Calendar calendar;
        DateTimeFormatter datetimeformatter = formattersUTC[i];
        try
        {
            calendar = datetimeformatter.parseDateTime(s).toCalendar(Locale.getDefault());
            calendar.setTimeZone(TimeZone.getDefault());
        }
        catch(IllegalArgumentException illegalargumentexception1)
        {
            i++;
            continue; /* Loop/switch isn't completed */
        }
_L3:
        return calendar;
_L2:
        String s1;
        int j;
        s1 = TimeZone.getDefault().getID();
        j = 0;
_L4:
        if(j >= formattersNoUTC.length)
            break MISSING_BLOCK_LABEL_113;
        calendar = formattersNoUTC[j].withZone(DateTimeZone.forID(s1)).parseDateTime(s).toCalendar(Locale.getDefault());
        calendar.setTimeZone(TimeZone.getDefault());
          goto _L3
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
        j++;
          goto _L4
        throw new ConversionException("Cannot parse date " + s);
        if(true) goto _L6; else goto _L5
_L5:
    }

    @Override
	public String toString(Object obj)
    {
        return (new DateTime(obj)).toString(formattersUTC[0]);
    }

    static Class class$java$util$GregorianCalendar;
    private static final DateTimeFormatter formattersNoUTC[];
    private static final DateTimeFormatter formattersUTC[];

    static 
    {
        DateTimeFormatter adatetimeformatter[] = new DateTimeFormatter[19];
        adatetimeformatter[0] = ISODateTimeFormat.dateTime();
        adatetimeformatter[1] = ISODateTimeFormat.dateTimeNoMillis();
        adatetimeformatter[2] = ISODateTimeFormat.basicDateTime();
        adatetimeformatter[3] = ISODateTimeFormat.basicOrdinalDateTime();
        adatetimeformatter[4] = ISODateTimeFormat.basicOrdinalDateTimeNoMillis();
        adatetimeformatter[5] = ISODateTimeFormat.basicTime();
        adatetimeformatter[6] = ISODateTimeFormat.basicTimeNoMillis();
        adatetimeformatter[7] = ISODateTimeFormat.basicTTime();
        adatetimeformatter[8] = ISODateTimeFormat.basicTTimeNoMillis();
        adatetimeformatter[9] = ISODateTimeFormat.basicWeekDateTime();
        adatetimeformatter[10] = ISODateTimeFormat.basicWeekDateTimeNoMillis();
        adatetimeformatter[11] = ISODateTimeFormat.ordinalDateTime();
        adatetimeformatter[12] = ISODateTimeFormat.ordinalDateTimeNoMillis();
        adatetimeformatter[13] = ISODateTimeFormat.time();
        adatetimeformatter[14] = ISODateTimeFormat.timeNoMillis();
        adatetimeformatter[15] = ISODateTimeFormat.tTime();
        adatetimeformatter[16] = ISODateTimeFormat.tTimeNoMillis();
        adatetimeformatter[17] = ISODateTimeFormat.weekDateTime();
        adatetimeformatter[18] = ISODateTimeFormat.weekDateTimeNoMillis();
        formattersUTC = adatetimeformatter;
        DateTimeFormatter adatetimeformatter1[] = new DateTimeFormatter[22];
        adatetimeformatter1[0] = ISODateTimeFormat.basicDate();
        adatetimeformatter1[1] = ISODateTimeFormat.basicOrdinalDate();
        adatetimeformatter1[2] = ISODateTimeFormat.basicWeekDate();
        adatetimeformatter1[3] = ISODateTimeFormat.date();
        adatetimeformatter1[4] = ISODateTimeFormat.dateHour();
        adatetimeformatter1[5] = ISODateTimeFormat.dateHourMinute();
        adatetimeformatter1[6] = ISODateTimeFormat.dateHourMinuteSecond();
        adatetimeformatter1[7] = ISODateTimeFormat.dateHourMinuteSecondFraction();
        adatetimeformatter1[8] = ISODateTimeFormat.dateHourMinuteSecondMillis();
        adatetimeformatter1[9] = ISODateTimeFormat.hour();
        adatetimeformatter1[10] = ISODateTimeFormat.hourMinute();
        adatetimeformatter1[11] = ISODateTimeFormat.hourMinuteSecond();
        adatetimeformatter1[12] = ISODateTimeFormat.hourMinuteSecondFraction();
        adatetimeformatter1[13] = ISODateTimeFormat.hourMinuteSecondMillis();
        adatetimeformatter1[14] = ISODateTimeFormat.ordinalDate();
        adatetimeformatter1[15] = ISODateTimeFormat.weekDate();
        adatetimeformatter1[16] = ISODateTimeFormat.year();
        adatetimeformatter1[17] = ISODateTimeFormat.yearMonth();
        adatetimeformatter1[18] = ISODateTimeFormat.yearMonthDay();
        adatetimeformatter1[19] = ISODateTimeFormat.weekyear();
        adatetimeformatter1[20] = ISODateTimeFormat.weekyearWeek();
        adatetimeformatter1[21] = ISODateTimeFormat.weekyearWeekDay();
        formattersNoUTC = adatetimeformatter1;
    }
}
