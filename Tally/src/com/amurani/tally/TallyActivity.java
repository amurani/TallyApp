package com.amurani.tally;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TallyActivity extends Activity {
	
	String[] alphabets = new String[] {
			"a", "b", "c", "d", "e",
			"f", "g", "h", "i", "j",
			"k", "l", "m", "n", "o",
			"p", "q", "r", "s" ,"t",
			"u", "v", "w", "x", "y",
			"z"
		};
	
	boolean started = false;
	
	GridView gridView;
	Tally mTally;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tally_activity);
		
		mTally = new Tally(alphabets);
		
		gridView = (GridView) findViewById(R.id.list);
		gridView.setNumColumns(4);
		gridView.setAdapter(new TallyItemAdapter());
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		switch (menuItem.getItemId()) {
			case R.id.undo:
				if (started) {
					mTally.undolast();
					View holder = gridView.getChildAt(mTally.getLastPosition());
					TextView count = (TextView) holder.findViewById(R.id.count);
					count.setText(String.valueOf(mTally.getLastChange()));
				}
				return true;
			default:
				return super.onOptionsItemSelected(menuItem);
		}
	}
	
	protected void Toast(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
	}
	
	protected class TallyItemAdapter extends BaseAdapter {
		
		public View getView(final int position, View convertView, ViewGroup parent) {
			View holder = convertView;
			if (holder == null) {
				holder = (RelativeLayout) getLayoutInflater().inflate(R.layout.tally_item, null);
				
				final TextView count = (TextView) holder.findViewById(R.id.count);
				count.setText("0");
				
				TextView button = (TextView) holder.findViewById(R.id.button);
				button.setText(alphabets[position]);
				
				button.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						if (!started)
							started = true;
						mTally.increment(position);
						
						int mCount = mTally.getValueAt(position);
						count.setText(String.valueOf(mCount));
					}
				});
			}
			return holder;
		}
		
		public int getCount() {
			return alphabets.length;
		}
		
		public Object getItem(int position) {
			return null;
		}
		
		public long getItemId(int position) {
			return 0;
		}
		
	}
	
}