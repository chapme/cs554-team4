package cs554.proj.slidingtiles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SetupNumberGame extends Activity {
	/**
	 * The grid size that is currently selected (5 by default)
	 */
	private int gridSize = 5;
	private boolean aiEnabled = false;
	private int aiDifficulty = 1;
	
	/**
	 * String used to index info passed to the next activity
	 */
	public final static String GRID_SIZE = "cs554.proj.slidingtiles.GRID_SIZE";
	public final static String AI_ENABLED = "cs554.proj.slidingtiles.AI_ENABLED";
	public final static String AI_DIFFICULTY = "cs554.proj.slidingtiles.AI_DIFFICULTY";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_number_game);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_setup_number_game, menu);
        return true;
    }
    
    /**
     * Function to support the back button that returns you to the main menu
     * @param view
     */
    public void back(View view) {
    	Intent intent = new Intent(this, MainMenu.class);
    	startActivity(intent);
    }
    
    /**
     * Process which radio button was clicked by the user and update the grid size
     * 
     * @param view
     */
    public void onRadioGridButtonClicked(View view) {
    	// Make sure we're processing a button being selected.
    	// We can safely ignore deselections since another button must have
    	// been selected.
    	boolean checked = ((RadioButton) view).isChecked();
    	if(!checked)
    		return;
    	
    	// Find out which case was selected and update the selected grid size
    	switch(view.getId()) {
    	case R.id.radioGrid2:
    		this.gridSize = 2;
    		break;
    	case R.id.radioGrid3:
    		this.gridSize = 3;
    		break;
    	case R.id.radioGrid4:
    		this.gridSize = 4;
    		break;
    	case R.id.radioGrid5:
    		this.gridSize = 5;
    		break;
    	}
    }
    
    /**
     * Process radio  button click for turning the AI on and off
     * 
     * @param view Selected radio button
     */
    public void onRadioAIStateClicked(View view) {
    	// Make sure we're processing a button being selected.
    	// We can safely ignore deselections since another button must have
    	// been selected.
    	boolean checked = ((RadioButton) view).isChecked();
    	if(!checked)
    		return;
    	
    	// Check which option was selected
    	TextView tv = (TextView) findViewById(R.id.aiLevel);
    	RadioGroup rg = (RadioGroup) findViewById(R.id.aiLevelRadioGrid);
    	switch(view.getId()) {
    	case R.id.radioAIOn:
    		aiEnabled = true;
    		tv.setVisibility(View.VISIBLE);
    		rg.setVisibility(View.VISIBLE);
    		break;
    	case R.id.radioAIOff:
    		aiEnabled = false;
    		tv.setVisibility(View.GONE);
    		rg.setVisibility(View.GONE);
    		break;
    	}
    }
    
    /**
     * Process radio button clicks for AI level
     * 
     * @param view Selected radio button
     */
    public void onRadioAILevelButtonClicked(View view) {
    	// Make sure we're processing a button being selected.
    	// We can safely ignore deselections since another button must have
    	// been selected.
    	boolean checked = ((RadioButton) view).isChecked();
    	if(!checked)
    		return;
    	
    	// Find out which level was selected
    	switch(view.getId()) {
    	case R.id.radioAI1:
    		aiDifficulty = 1;
    		break;
    	case R.id.radioAI2:
    		aiDifficulty = 2;
    		break;
    	case R.id.radioAI3:
    		aiDifficulty = 3;
    		break;
    	}
    }
    
    /**
     * Function for handling the start game button. This will pass the options 
     * selected on to the next activity.
     * 
     * @param view
     */
    public void startGame(View view) {
    	Intent intent = new Intent(this, NumberMode.class);
    	
    	// Pass the grid size
    	intent.putExtra(GRID_SIZE, this.gridSize);
    	
    	// Pass if the AI is on or off
    	intent.putExtra(AI_ENABLED, aiEnabled);
    	
    	// Pass AI level
    	intent.putExtra(AI_DIFFICULTY, aiDifficulty);
    	
    	startActivity(intent);
    }
}
