package cs554.proj.slidingtiles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;

public class SetupNumberGame extends Activity {
	/**
	 * The grid size that is currently selected (5 by default)
	 */
	private int gridSize = 5;
	
	/**
	 * String used to index info passed to the next activity
	 */
	public final static String GRID_SIZE = "cs554.proj.slidingtiles.GRID_SIZE";

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
    public void onRadioButtonClicked(View view) {
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
     * Function for handling the start game button. This will pass the options 
     * selected on to the next activity.
     * 
     * @param view
     */
    public void startGame(View view) {
    	Intent intent = new Intent(this, NumberMode.class);
    	
    	// Pass the grid size
    	intent.putExtra(GRID_SIZE, this.gridSize);
    	
    	startActivity(intent);
    }
}
