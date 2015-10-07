package com.ea.orbit.samples.chat;

import com.ea.orbit.actors.runtime.AbstractActor;
import com.ea.orbit.concurrent.Task;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by alan.kydd on 15-10-07.
 */
public class UserProfileActor extends AbstractActor<UserProfileActor.State> implements UserProfile
{
    public static class State
    {
        String password;
    }

    @Override
    public Task<Boolean> authenticate(final String password)
    {
        String hashedPassword;

        try
        {
            hashedPassword = this.hashString(password);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return Task.fromValue(false);
        }

        if (hashedPassword.equals(state().password))
        {
            return Task.fromValue(true);
        }

        return Task.fromValue(false);
    }

    @Override
    public Task<Boolean> register(final String password)
    {
        String hashedPassword = null;
        try
        {
            hashedPassword = this.hashString(password);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return Task.fromValue(false);
        }

        state().password = hashedPassword;
        return writeState().thenApply(x -> true);
    }

    private String hashString(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        digest.reset();
        byte[] input = digest.digest(s.getBytes("UTF-8"));

        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(input);
    }
}
