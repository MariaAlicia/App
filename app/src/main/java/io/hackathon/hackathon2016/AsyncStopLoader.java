package io.hackathon.hackathon2016;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sami on 1/05/2016.
 */
public class AsyncStopLoader extends AsyncTask<String, String, String> {
	private List<String> stopTimes;

	public AsyncStopLoader(List<String> stopTimes)
	{
		this.stopTimes = stopTimes;
	}

	@Override
	protected String doInBackground(String... params) {
		String stopId = params[0];
		DataController dataController = new DataController();
		dataController.addStopsToList(stopId, stopTimes);

		return null;
	}
}
