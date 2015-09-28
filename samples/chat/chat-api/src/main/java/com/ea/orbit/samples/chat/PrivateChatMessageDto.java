package com.ea.orbit.samples.chat;

import java.io.Serializable;
import java.util.Date;

public class PrivateChatMessageDto implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Date when;
    private String sender;
    private String target;
    private String message;

    public static String TYPE = "privateChatMessage";

    public Date getWhen()
    {
        // wasteful clone just to keep code analysers happy
        return new Date(when.getTime());
    }

    public void setWhen(final Date when)
    {
        this.when = new Date(when.getTime());
    }

    public String getSender()
    {
        return sender;
    }

    public void setSender(final String sender)
    {
        this.sender = sender;
    }

    public String getTarget()
    {
        return target;
    }

    public void setTarget(final String target)
    {
        this.target = target;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(final String message)
    {
        this.message = message;
    }
}
