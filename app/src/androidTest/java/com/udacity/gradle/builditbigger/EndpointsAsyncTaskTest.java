package com.udacity.gradle.builditbigger;

import android.text.TextUtils;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {

    @Test
    public void test() {

        OnJokeListener onJokeListener = new OnJokeListener() {
            @Override
            public void onJokeLoaded(String joke) {
            }
        };

        try {
            String joke = new EndpointsAsyncTask().execute(onJokeListener).get();
            assertNotNull(joke);
            assertNotSame(0, joke.length());
            assertFalse(TextUtils.isEmpty(joke));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}