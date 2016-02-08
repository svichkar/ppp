package com.nixsolutions.studentgrade.webservice.provider;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by svichkar on 2/8/2016.
 */

public class DateAdapter extends XmlAdapter<String, Date> {


    private static final ThreadLocal<DateFormat> DATE_FORMAT_TL = new ThreadLocal<DateFormat>() {

        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        }

    };

    @Override
    public Date unmarshal(String v) throws Exception {
        return Date.valueOf(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        return DATE_FORMAT_TL.get().format(v);
    }

}
