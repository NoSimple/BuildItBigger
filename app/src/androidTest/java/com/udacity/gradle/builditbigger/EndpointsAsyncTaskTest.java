package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.text.TextUtils;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {

    @Test
    public void test() {

        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        try {
            String joke = new EndpointsAsyncTask().execute((OnJokeListener) context).get();
            assertNotNull(joke);
            assertNotSame(0, joke.length());
            assertFalse(TextUtils.isEmpty(joke));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}