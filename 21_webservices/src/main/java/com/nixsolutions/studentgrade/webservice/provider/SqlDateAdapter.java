package com.nixsolutions.studentgrade.webservice.provider;

import org.springframework.beans.factory.annotation.Configurable;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Date;

/**
 * Created by svichkar on 2/8/2016.
 */
@Configurable
public class SqlDateAdapter extends XmlAdapter<String, Date> {

    @Override
    public Date unmarshal(String v) throws Exception {
        return Date.valueOf(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        return //v.toString()

        "FUCK YOU!!!";
        // ;
    }
}
