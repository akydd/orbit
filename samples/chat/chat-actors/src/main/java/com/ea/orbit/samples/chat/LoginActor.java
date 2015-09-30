/*
 Copyright (C) 2015 Electronic Arts Inc.  All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:

 1.  Redistributions of source code must retain the above copyright
     notice, this list of conditions and the following disclaimer.
 2.  Redistributions in binary form must reproduce the above copyright
     notice, this list of conditions and the following disclaimer in the
     documentation and/or other materials provided with the distribution.
 3.  Neither the name of Electronic Arts, Inc. ("EA") nor the names of
     its contributors may be used to endorse or promote products derived
     from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY ELECTRONIC ARTS AND ITS CONTRIBUTORS "AS IS" AND ANY
 EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL ELECTRONIC ARTS OR ITS CONTRIBUTORS BE LIABLE FOR ANY
 DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

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
public class LoginActor extends AbstractActor<LoginActor.State> implements Login
{
    public static class State
    {
        ObserverManager<LoginObserver> observers = new ObserverManager<>();
        LinkedList<String> users = new LinkedList<>();
    }

    // TODO: prevent multiple logins of users
    @Override
    public Task<Void> addUser(final LoginMessageDto message)
    {
        state().users.add(message.getNickName());
        state().observers.notifyObservers(o -> o.receiveMessage(message));
        writeState().join();
        return Task.done();
    }

    @Override
    public Task<Void> removeUser(final LogoutMessageDto message)
    {
        state().users.remove(message.getNickName());
        state().observers.notifyObservers(o -> o.receiveMessage(message));
        writeState().join();
        return Task.done();
    }

    @Override
    public Task<Boolean> join(final LoginObserver observer)
    {
        state().observers.addObserver(observer);
        return writeState().thenApply(x -> true);
    }

    @Override
    public Task<Boolean> leave(final LoginObserver observer)
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
