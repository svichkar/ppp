package com.nixsolutions.studentgrade.webservice.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Date;

/**
 * Created by svichkar on 2/8/2016.
 */
public class SqlDateAdapter extends XmlAdapter<String, Date> {

    @Override
    public Date unmarshal(String stringDate) throws Exception {
        return Date.valueOf(stringDate);
    }

    @Override
    public String marshal(Date date) throws Exception {
        return date.toString();
    }
}
