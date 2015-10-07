package com.ea.orbit.samples.chat;

import com.ea.orbit.actors.Actor;
import com.ea.orbit.concurrent.Task;

/**
 * Created by alan.kydd on 15-10-07.
 */
public interface UserProfile extends Actor
{
    Task<Boolean> authenticate(String password);

    Task<Boolean> register(String password);
}
