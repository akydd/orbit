package com.ea.orbit.samples.chat;

import com.ea.orbit.actors.Actor;
import com.ea.orbit.actors.annotation.NoIdentity;
import com.ea.orbit.actors.annotation.OneWay;
import com.ea.orbit.concurrent.Task;

import java.util.List;

@NoIdentity
public interface ChatSessionManager extends Actor
{
    @OneWay
    Task<Void> addUser(LoginMessageDto message);

    @OneWay
    Task<Void> removeUser(LogoutMessageDto message);

    Task<Boolean> join(ChatSessionObserver observer);

    Task<Boolean> leave(ChatSessionObserver observer);

    Task<List<String>> getUsers();
}
