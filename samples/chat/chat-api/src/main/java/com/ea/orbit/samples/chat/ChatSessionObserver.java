package com.ea.orbit.samples.chat;


import com.ea.orbit.actors.ActorObserver;
import com.ea.orbit.actors.annotation.OneWay;
import com.ea.orbit.concurrent.Task;

public interface ChatSessionObserver extends ActorObserver
{
    @OneWay
    Task<Void> onLogin(LoginMessageDto message);

    @OneWay
    Task<Void> onLogout(LogoutMessageDto message);
}
