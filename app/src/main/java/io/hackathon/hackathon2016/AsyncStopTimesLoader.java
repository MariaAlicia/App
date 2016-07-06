package io.hackathon.hackathon2016;

import android.os.AsyncTask;

import java.util.List;

import io.hackathon.hackathon2016.helper.DataController;

/**
 * Created by mgoo on 1/05/16.
 */
public class AsyncStopTimesLoader extends AsyncTask<String, Void, List<String>> {

    @Override
    protected List<String> doInBackground(String... params) {
        DataController dataController = new DataController();

        return dataController.stopRoutesAndTime(params[0]);
    }

    @Override
    protected void onPostExecute(List<String> strings) {
        super.onPostExecute(strings);
    }
}
