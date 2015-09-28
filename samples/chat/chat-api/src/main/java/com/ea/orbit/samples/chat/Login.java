package com.ea.orbit.samples.chat;

import com.ea.orbit.actors.Actor;
import com.ea.orbit.actors.annotation.NoIdentity;
import com.ea.orbit.actors.annotation.OneWay;
import com.ea.orbit.concurrent.Task;

import java.util.List;

@NoIdentity
public interface Login extends Actor
{
    @OneWay
    Task<Void> say(LoginMessageDto message);

    Task<Boolean> join(LoginObserver observer);

    Task<Boolean> leave(LoginObserver observer);

    Task<List<String>> getUsers();
}
