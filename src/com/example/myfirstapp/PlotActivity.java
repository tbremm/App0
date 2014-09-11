package com.example.myfirstapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.LineGraphView;

public class PlotActivity extends Activity {

	private RelativeLayout m_layout;
	
	private final Handler mHandler = new Handler();
    private Runnable mTimer1;
    private Runnable mTimer2;
    private GraphViewSeries exampleSeries1;
    private GraphViewSeries exampleSeries2;
    private double graph2LastXValue = 5d;
    private GraphViewSeries exampleSeries3;

    private double getRandom() {
        double high = 3;
        double low = 0.5;
        return Math.random() * (high - low) + low;
    }
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plot);
		// Show the Up button in the action bar.
		setupActionBar();
		
		m_layout = (RelativeLayout) findViewById(R.id.activity_plot);
		doPlot ();
	}
	
	/** On click handler for Plot! button **/
	public void onPlot(View view) {
		// Respond to the button click
	}

	private void doPlot() {
		// first init data
		// sin curve
		int num = 150;
		GraphViewData[] data = new GraphViewData[num];
		double v=0;
		for (int i=0; i<num; i++) {
		  v += 0.2;
		  data[i] = new GraphViewData(i, Math.sin(v));
		}
		GraphViewSeries exampleSeries1 = new GraphViewSeries("Sin", new GraphViewSeriesStyle(Color.rgb(200, 50, 00), 3), data);
		 
		// cos curve
		data = new GraphViewData[num];
		v=0;
		for (int i=0; i<num; i++) {
		  v += 0.2;
		  data[i] = new GraphViewData(i, Math.cos(v));
		}
		GraphViewSeries exampleSeries3 = new GraphViewSeries("Cos", new GraphViewSeriesStyle(Color.rgb(90, 250, 00), 3), data);
		 
		// random curve
		num = 1000;
		data = new GraphViewData[num];
		v=0;
		for (int i=0; i<num; i++) {
		  v += 0.2;
		  data[i] = new GraphViewData(i, Math.sin(Math.random()*v));
		}
		GraphViewSeries seriesRnd = new GraphViewSeries("Rand", null, data);
		 
		/*
		 * create graph
		 */
		GraphView graphView = new LineGraphView(
		    this
		    , "GraphViewDemo"
		);
		// add data
		graphView.addSeries(exampleSeries3);
		graphView.addSeries(exampleSeries1);
		graphView.addSeries(seriesRnd);
		// optional - set view port, start=2, size=10
		graphView.setViewPort(2, 10);
		graphView.setScalable(true);
		// optional - legend
		graphView.setShowLegend(true);
		
		/*
		GraphViewDataInterface gvDataInterface = new GraphViewDataInterface() {
			private double m_old_x = 0;
			private double m_new_x = 0;
			private double m_y = 0;
			
			@Override
			public double getY() {
				// Return sin values increasing with time (time delta = 1)
				return Math.sin(m_y++);
			}
			
			@Override
			public double getX() {
				// Safely increment x automatically (time delta = 1)
				do {
					m_old_x = m_new_x;
					return m_new_x++;
				} while (m_old_x < m_new_x);
			}
		};
		*/
		
		// Add graph to the view
		m_layout.addView(graphView);
		/*
		Toast.makeText(getApplicationContext(), "Graph added to view, looping...", Toast.LENGTH_SHORT).show();
		
		v = 0;
		for (int i=0; i<num; i++) {
			  v += 0.2;
			  GraphViewData my_data = new GraphViewData(i, Math.sin(Math.random()*v));
			  seriesRnd.appendData(my_data, true, 50);
			  graphView.redrawAll();
			}
			*/
	}
	

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.plot, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	/*
	// *******************************
	// COPY/PASTE
	// *******************************
	
	 @Override
	    protected void onPause() {
	        mHandler.removeCallbacks(mTimer1);
	        mHandler.removeCallbacks(mTimer2);
	        super.onPause();
	    }

	    @Override
	    protected void onResume() {
	        super.onResume();
	        mTimer1 = new Runnable() {
	            @Override
	            public void run() {
	                exampleSeries1.resetData(new GraphViewData[] {
	                        new GraphViewData(1, getRandom())
	                        , new GraphViewData(2, getRandom())
	                        , new GraphViewData(2.5, getRandom()) // another frequency
	                        , new GraphViewData(3, getRandom())
	                        , new GraphViewData(4, getRandom())
	                        , new GraphViewData(5, getRandom())
	                });
	                exampleSeries3.resetData(new GraphViewData[] {
	                        new GraphViewData(2, getRandom())
	                        , new GraphViewData(2.5, getRandom()) // another frequency
	                        , new GraphViewData(3, getRandom())
	                        , new GraphViewData(4, getRandom())
	                });
	                mHandler.postDelayed(this, 300);
	            }
	        };
	        mHandler.postDelayed(mTimer1, 300);

	        mTimer2 = new Runnable() {
	            @Override
	            public void run() {
	                graph2LastXValue += 1d;
	                exampleSeries2.appendData(new GraphViewData(graph2LastXValue, getRandom()), true, 10);
	                mHandler.postDelayed(this, 200);
	            }
	        };
	        mHandler.postDelayed(mTimer2, 1000);
	    }
	    
	    // ***********************
	    // /COPY/PASTE
	    // ***********************
*/
}
