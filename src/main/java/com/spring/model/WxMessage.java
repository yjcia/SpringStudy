package com.spring.model;

import com.spring.annotation.DataAttribute;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.io.Serializable;

/**
 * Created by YanJun on 2016/3/29.
 */

@XStreamAlias("xml")
public class WxMessage implements Serializable {

    @XStreamAlias("ToUserName")
    @DataAttribute.NeedDataFormatter(true)
    private String toUserName;

    @XStreamAlias("FromUserName")
    @DataAttribute.NeedDataFormatter(true)
    private String fromUserName;

    @XStreamAlias("CreateTime")
    private Long createTime;

    @XStreamAlias("MsgType")
    @DataAttribute.NeedDataFormatter(true)
    private String msgType;

    @XStreamAlias("MsgId")
    private Long msgId;

    @XStreamAlias("Content")
    @DataAttribute.NeedDataFormatter(true)
    private String content;

    @XStreamAlias("Event")
    @DataAttribute.NeedDataFormatter(true)
    private String event;

    @XStreamAlias("EventKey")
    @DataAttribute.NeedDataFormatter(true)
    private String eventKey;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    @Override
    public String toString() {
        return "WxMessage{" +
                "toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime=" + createTime +
                ", msgType='" + msgType + '\'' +
                ", msgId=" + msgId +
                ", content='" + content + '\'' +
                ", event='" + event + '\'' +
                ", eventKey='" + eventKey + '\'' +
                '}';
    }
}
