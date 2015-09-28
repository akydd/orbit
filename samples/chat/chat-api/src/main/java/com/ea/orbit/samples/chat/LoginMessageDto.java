package com.ea.orbit.samples.chat;

import java.io.Serializable;

public class LoginMessageDto implements Serializable
{
	private static final long serialVersionUID = 1L;

    public static final String TYPE = "loginMessage";

    private String nickName;

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(final String nickName)
    {
        this.nickName = nickName;
    }
}
