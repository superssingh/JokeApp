package com.santossingh.jokeapp;

import com.santossingh.jokeapp.AsyncTask.AsyncResponse;
import com.santossingh.jokeapp.AsyncTask.EndpointsAsyncTask;

import junit.framework.TestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Stark on 07/09/2016.
 */

public class AsyncTaskUnitTest extends TestCase {
    private final String key = "test";
    String mJoke;
    boolean called;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        called = false;
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testAsyncTask() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);

        try {
            new EndpointsAsyncTask(new AsyncResponse() {
                @Override
                public void processFinish(String result) {
                    mJoke = result;
                    called = true;
                    signal.countDown();
                }
            }).execute(key);
            signal.await(10, TimeUnit.SECONDS);
            assertTrue(called);
            assertNotNull("Null Joke", mJoke);
            assertFalse("Joke Fetched Empty", mJoke.isEmpty());
        } catch (Exception e) {
            fail();
        }
    }
}
