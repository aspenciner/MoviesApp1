package org.ithacacounseling.popularmovies11;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.ithacacounseling.moviesapp1.R;

import java.util.ArrayList;

//include findViewbyId() and getView()


//Initialize a custom ArrayAdapter

public class CustomArrayAdapter extends ArrayAdapter<GridItem> {

    private Context mContext;
    private int layoutResourceId;
    private ArrayList<GridItem> mGridData = newArrayList<GridItem>();

    public CustomArrayAdapter (Context mContext, int layoutResourceId, ArrayList<GridItem> mGridData) {
        super (mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }

// Grid data update on MainActivityFragment.Java

    public void setGridData(ArrayList<GridItem> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }


    // Custom ArrayAdapter overrides getView()

    @Override
    public View GetView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        ViewHolder holder;

        if row==null){
            LayoutInflater inflater = (Activity) mContext).getLayoutInflator()
            row = inflator.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) row.findViewById(R.grid_item_image);
            row.setTag(holder);
        } else{
            holder = (ViewHolder) row.getTag();
        }

        GridItem item = mGridData.get(position);
        holder.titleTextView.setText(Html.fromHtml(item.getTitle());

        //Download the image from the URL and display on image view
        Picasso.with(mContext).load(item.getImage()).into(holder.imageView);
        return row;


    }

    static class ViewHolder {
        TextView titleTextView;
        ImageView imageView;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_array_adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_custom_array_adapter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
