
package org.ithacacounseling.moviesapp1;

import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.ithacacounseling.popularmovies11.CustomArrayAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

//need to include doInBackground() and PostExecute()


/**
 * A fragment to hold the custom ArrayAdapter.
 */
public class MainActivityFragment extends Fragment {

    /**
     *
     */
    public ArrayAdapter<String> mMovieAdapter;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This line added in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    //@Override
    //public void onCreateOptionsMenu(Menu menu MenuInflater inflater) {
      //  inflater.inflate(R.menu_main, menu);
    //}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_popular_movies) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Grid <Image> movies= new Arraylist<image>(Arrays.asGrid(data));

        mMovieAdapter=

                new ArrayAdapter<String>(
                        getActivity(),
                        R.layout.grid_item_image,
                        R.id.item_movie_gridview,
                        movies);
    }

    View rootView= inflater.inflate(R.layout.gridview, container, false);

    GridView gridview = (GridView) findViewById(R.id.gridview);


    gridView.setAdapter(mMovieAdapter);

    public class FetchMoviesTask extends AsyncTask<String, Void, Void> {


        // These two need to be declared outside the try/catch
// so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String movieJsonStr = null;

        try {
            // Construct the URL for the Movie query images

            URL url = new URL("http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg");


            //In order to request popular movies, request data from the /discover/movie endpoint. An API Key is appended
            // to the HTTP request as a URL parameter.

            http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=[9b1d9c81e6891f12a39eb60c89496a96]


            // Create the request to the movie API, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            movieJsonStr = buffer.toString();
        } catch (IOException e) {
            Log.e("MainActivityFragment", "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attempting
            // to parse it.
            return null;
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("MainActivityFragment", "Error closing stream", e);
                }
            }
        }


        @Override
        protected void onPostExecute(Integer result){
            super.onPostExecute(result);

            if (result==1) {
                CustomArrayAapter = new CustomArrayAdapter(mContext. movieObjects);
                movieView.setAdapter((ListAdapter) CustomArrayAdapter);
            }
        }



        // Process the json string and convert into a list of MovieInfo objects
        // using the Gson library.

        private void processMovieInfo(String infoString) {

            //Create a new Gson object
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();

            //Use the Gson library to automatically process the string and convert it into the list of MovieInfo objects.

            movieInfoList = new ArrayList<MovieInfo>();
            movieInfoList = Arrays.asList(gson.fromJson(infoString, MovieInfo[].class));
        }

    }

    return rootview;
}
}


