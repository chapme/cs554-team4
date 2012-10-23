package cs554.proj.slidingtiles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.widget.Button;
import cs554.proj.slidingtiles.R;

/**
 * @author me
 *
 * Base class used to implement the sliding grid and support functions
 */
public class SlidingGrid extends Activity {	
	/**
	 * The maximum grid size we'll generate. Public so other classes have this info.
	 */
	public static int maxSize = 5;
	
	/**
	 * Grid for the user game. Public member so child classes can set up grid.
	 */
	public Grid userGrid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreate(savedInstanceState, maxSize);
    }
    
    /**
     * Chooses the correct view based on the grid size and sets the gridSize value
     * 
     * @param savedInstanceState
     * @param gridSize Size of the grid to generate
     */
    public void onCreate(Bundle savedInstanceState, int gridSize) {
    	super.onCreate(savedInstanceState);
    	if(gridSize == 2)
    		setContentView(R.layout.activity_sliding_grid_2);
    	else if(gridSize == 3)
    		setContentView(R.layout.activity_sliding_grid_3);
    	else if(gridSize == 4)
    		setContentView(R.layout.activity_sliding_grid_4);
    	else
    		setContentView(R.layout.activity_sliding_grid_5);
    	
    	// Set up user grid with button IDs and Button objects from the UI
    	int[][] ids = {{R.id.gb11, R.id.gb12, R.id.gb13, R.id.gb14, R.id.gb15},{R.id.gb21, R.id.gb22, R.id.gb23, R.id.gb24, R.id.gb25},{R.id.gb31, R.id.gb32, R.id.gb33, R.id.gb34, R.id.gb35},{R.id.gb41, R.id.gb42, R.id.gb43, R.id.gb44, R.id.gb45},{R.id.gb51, R.id.gb52, R.id.gb53, R.id.gb54, R.id.gb55}};
    	Button[][] buttons = new Button[maxSize][maxSize];
    	for(int i = 0; i < maxSize; i++)
    		for(int j = 0; j < maxSize; j++)
    			buttons[i][j] = (Button) findViewById(ids[i][j]);
    	userGrid = new Grid(gridSize, ids, buttons);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_sliding_grid, menu);
        return true;
    }
    
    /**
     * Button function to return the user to the main menu
     * 
     * @param view
     */
    public void back(View view) {
    	Intent intent = new Intent(this, MainMenu.class);
    	startActivity(intent);
    }
    
    /**
     * Function for button to process when the button in the grid is pressed. This will
     * slide the tiles in the grid if it is inline with the missing tile.
     * 
     * @param view
     */
    public void processButtonPress(View view) {
    	// Get the row and column of the button pressed
    	int id = view.getId();
    	int row = userGrid.getRow(id);
    	int col = userGrid.getCol(id);
    	
    	// Handle the button press
    	userGrid.processButtonPress(row, col);
    }
}
