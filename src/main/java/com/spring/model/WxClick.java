package com.spring.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by YanJun on 2016/3/30.
 */

@XStreamAlias("wxClick")
public class WxClick {
    @XStreamAlias("eventName")
    private String eventName;

    @XStreamImplicit
    private List<WxClickEvent> clickEventList;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public List<WxClickEvent> getClickEventList() {
        return clickEventList;
    }

    public void setClickEventList(List<WxClickEvent> clickEventList) {
        this.clickEventList = clickEventList;
    }
}
