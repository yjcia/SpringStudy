package com.spring.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by YanJun on 2016/3/30.
 */

@XStreamAlias("wxClickEvent")
public class WxClickEvent {

    @XStreamAlias("eventKey")
    private String eventKey;

    @XStreamAlias("eventMapingClass")
    private String eventMapingClass;

    @XStreamAlias("eventMapingMethod")
    private String eventMapingMethod;

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getEventMapingClass() {
        return eventMapingClass;
    }

    public void setEventMapingClass(String eventMapingClass) {
        this.eventMapingClass = eventMapingClass;
    }

    public String getEventMapingMethod() {
        return eventMapingMethod;
    }

    public void setEventMapingMethod(String eventMapingMethod) {
        this.eventMapingMethod = eventMapingMethod;
    }
}
