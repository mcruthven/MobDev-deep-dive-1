package com.example.guiwidget;

//import com.cumulus.MoveCopy.R;

import com.example.guiwidget.R.string;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {
	private static final String TAG = "Transition";
	private int pinkLevel=255,blueLevel=255,yellowLevel=255;
	private boolean setvar=true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// checkbox that disables all other buttons and check boxes
		addListenerOnDisableCheck();
		
		// button that changes clock between digital and analog
		addListenerOnClocks();
		
		// change background based on check boxes and the seekbar
		seekBarChange();
	}

	// when checked disable everything
	public void addListenerOnDisableCheck() {

		CheckBox checkbox= (CheckBox) findViewById(R.id.disableCheck);

		checkbox.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				// when checkbox is checked, disables
				// when it is not checked it enables
				boolean enableStatus=!((CheckBox) v).isChecked();
				
				// changing statuses of buttons between enabled and disabled
				findViewById(R.id.pinkCheck).setEnabled(enableStatus);
				findViewById(R.id.yellowCheck).setEnabled(enableStatus);
				findViewById(R.id.blueCheck).setEnabled(enableStatus);
				findViewById(R.id.background_button).setEnabled
				(enableStatus);
				findViewById(R.id.seekBar1).setEnabled(enableStatus);
			}
		});
	}
	
	// modifies background color from seek bar values
	public void seekBarChange(){
		SeekBar seekbar=(SeekBar) findViewById(R.id.seekBar1);
		seekbar.setOnSeekBarChangeListener( new OnSeekBarChangeListener()
		{
			// progress bar value
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser)
			{
				// bar value converted into color level
				int bar_related=((int)(255*(1-progress/100.0)));	
				
				// checked boxes make that color dependent on the bar value
				if(((CheckBox)findViewById(R.id.pinkCheck)).isChecked())
					pinkLevel=bar_related;
				if(((CheckBox)findViewById(R.id.yellowCheck)).isChecked())
					yellowLevel=bar_related;
				if(((CheckBox)findViewById(R.id.blueCheck)).isChecked())
					blueLevel=bar_related;
				
				// set background color based on bar position
				findViewById(R.id.background).setBackgroundColor(Color.argb( 255,
						blueLevel ,pinkLevel,yellowLevel));
			}
			public void onStartTrackingTouch(SeekBar seekBar)
			{
			}

			public void onStopTrackingTouch(SeekBar seekBar)
			{
			}
		});
	}
	
	// changes clock
	public void addListenerOnClocks() {

		Button checkbox= (Button) findViewById(R.id.background_button);

		checkbox.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(findViewById(R.id.analogClock1).getVisibility()==View.INVISIBLE){
					((Button) findViewById(R.id.background_button)).setText(string.digital);
					findViewById(R.id.digitalClock).setVisibility(View.INVISIBLE);
					findViewById(R.id.analogClock1).setVisibility(View.VISIBLE);
				}else{
					((Button) findViewById(R.id.background_button)).setText(string.analog);
					findViewById(R.id.analogClock1).setVisibility(View.INVISIBLE);
					findViewById(R.id.digitalClock).setVisibility(View.VISIBLE);
				}
			}
		});

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
