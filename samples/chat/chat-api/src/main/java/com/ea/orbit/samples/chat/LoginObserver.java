package com.ea.orbit.samples.chat;


import com.ea.orbit.actors.ActorObserver;
import com.ea.orbit.actors.annotation.OneWay;
import com.ea.orbit.concurrent.Task;

public interface LoginObserver extends ActorObserver
{
    @OneWay
    Task<Void> receiveMessage(LoginMessageDto message);

    @OneWay
    Task<Void> receiveMessage(LogoutMessageDto message);
}
