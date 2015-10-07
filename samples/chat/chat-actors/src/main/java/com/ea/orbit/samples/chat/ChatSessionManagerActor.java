package com.ea.orbit.samples.chat;

import com.ea.orbit.actors.ObserverManager;
import com.ea.orbit.actors.runtime.AbstractActor;
import com.ea.orbit.concurrent.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by akydd on 27/09/15.
 */
public class ChatSessionManagerActor extends AbstractActor<ChatSessionManagerActor.State> implements ChatSessionManager
{
    public static class State
    {
        ObserverManager<ChatSessionObserver> observers = new ObserverManager<>();
        LinkedList<String> users = new LinkedList<>();
    }

    // TODO: prevent multiple logins of users
    @Override
    public Task<Void> addUser(final LoginMessageDto message)
    {
        state().users.add(message.getNickName());
        state().observers.notifyObservers(o -> o.onLogin(message));
        writeState().join();
        return Task.done();
    }

    @Override
    public Task<Void> removeUser(final LogoutMessageDto message)
    {
        state().users.remove(message.getNickName());
        state().observers.notifyObservers(o -> o.onLogout(message));
        writeState().join();
        return Task.done();
    }

    @Override
    public Task<Boolean> join(final ChatSessionObserver observer)
    {
        state().observers.addObserver(observer);
        return writeState().thenApply(x -> true);
    }

    @Override
    public Task<Boolean> leave(final ChatSessionObserver observer)
    {
        state().observers.removeObserver(observer);
        return writeState().thenApply(x -> true);
    }

    @Override
    public Task<List<String>> getUsers()
    {
        final LinkedList<String> users = state().users;
        return Task.fromValue(new ArrayList<>(users));
    }
}
