package cs554.proj.slidingtiles;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import cs554.proj.slidingtiles.R;

/**
 * @author me
 *
 * Base class used to implement the sliding grid and support functions
 */
public class SlidingGrid extends Activity {
	/**
	 * Size of the grid we're playing on
	 */
	private int gridSize = 5;
	
	/**
	 * Array of the button identifiers. 
	 * This is a flat array and the buttons are entered row wise.
	 * R.id.gbRC is the identifier for the button in row R and column C of the grid.
	 * The indexes are 1 counted.
	 */
	private int[] gbIDs = {R.id.gb11, R.id.gb12, R.id.gb13, R.id.gb14, R.id.gb15, R.id.gb21, R.id.gb22, R.id.gb23, R.id.gb24, R.id.gb25, R.id.gb31, R.id.gb32, R.id.gb33, R.id.gb34, R.id.gb35, R.id.gb41, R.id.gb42, R.id.gb43, R.id.gb44, R.id.gb45, R.id.gb51, R.id.gb52, R.id.gb53, R.id.gb54, R.id.gb55};
	
	/**
	 * The maximum grid size we'll generate
	 */
	private static int maxSize = 5;
	
	/**
	 * The column of the button currently hidden
	 */
	private int hbCol = -1;
	
	/**
	 * The row of the button currently hidden 
	 */
	private int hbRow = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_grid_5);
    }
    
    /**
     * Chooses the correct view based on the grid size and sets the gridSize value
     * 
     * @param savedInstanceState
     * @param gridSize Size of the grid to generate
     */
    public void onCreate(Bundle savedInstanceState, int gridSize) {
    	super.onCreate(savedInstanceState);
    	this.gridSize = gridSize;
    	if(gridSize == 2)
    		setContentView(R.layout.activity_sliding_grid_2);
    	else if(gridSize == 3)
    		setContentView(R.layout.activity_sliding_grid_3);
    	else if(gridSize == 4)
    		setContentView(R.layout.activity_sliding_grid_4);
    	else
    		setContentView(R.layout.activity_sliding_grid_5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_sliding_grid, menu);
        return true;
    }
    
    /**
     * Gets the size of the grid being used
     * 
     * @return The size of the grid
     */
    public int getGridSize() {
    	return gridSize;
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
     * Makes sure the button specified by row and col is contained in this grid
     * 
     * @param row Row of the button we're interested in 
     * @param col Column of the button we're interested in
     * @return True if grid contains the button, false otherwise
     */
    private boolean checkBounds(int row, int col) {
    	// Row and column have to be at least 1 and at most maxSize
    	if((row < 1) || (row > maxSize) || (col < 1) || (col > maxSize))
    		return false;
    	return true;
    }
    
    /**
     * Hides a button on the grid. Only one button can be hidden at a time. If another
     * button was previously hidden, it is made visible again.
     * 
     * @param row Row of the button to hide
     * @param col Column of the button to hide
     * @return True if the button was hidden, false otherwise
     */
    public boolean hideButton(int row, int col) {
    	// Make sure button is in grid
    	if(!checkBounds(row, col))
    		return false;
    	
    	// Get button and set visibility
    	Button b = (Button) findViewById(gbIDs[(row-1)*maxSize+col-1]);
    	b.setVisibility(View.GONE);
    	
    	// If a previous button was hidden, make visible
    	if(hbCol != -1) {
    		showButton(hbRow, hbCol);
    	}
    	
    	// Update hidden button indices
    	hbCol = col;
    	hbRow = row;
    	
    	return true;
    }
    
    /**
     * Make a button visible
     * 
     * @param row Row of the button
     * @param col Column of the button
     * @return True if made visible, false otherwise
     */
    private boolean showButton(int row, int col) {
    	// Make sure button is in grid
    	if(!checkBounds(row,col))
    		return false;
    	
    	// Get button and make visible
    	Button b = (Button) findViewById(gbIDs[(row-1)*maxSize+col-1]);
    	b.setVisibility(View.VISIBLE);
    	return true;
    }
    
    /**
     * Set the text shown on a button
     * 
     * @param row Row index of the button
     * @param col Column index of the button
     * @param text Text for the button
     * @return True if value was set, false otherwise
     */
    public boolean setButtonText(int row, int col, String text) {
    	// Make sure button is in grid
    	if(!checkBounds(row,col))
    		return false;
    	
    	// Set text on button
    	Button b = (Button) findViewById(gbIDs[(row-1)*maxSize+col-1]);
    	b.setText(text);
    	return true;
    }
    
    /**
     * Get the text currently on a button
     * 
     * @param row Row index of the button
     * @param col Column index of the button
     * @return Value on the button or empty string if button isn't in grid
     */
    public String getButtonText(int row, int col) {
    	// Make sure button is in grid
    	if(!checkBounds(row, col))
    		return "";
    	
    	// Get button text
    	Button b = (Button) findViewById(gbIDs[(row-1)*maxSize+col-1]);
    	return (String) b.getText();
    }
    
    /**
     * Get the row index of a button by button id
     * 
     * @param ID for the button (from R.java)
     * @return row of button or -1 if button not in grid
     */
    public int getRow(int button) {
    	// Find button
    	for(int i = 0; i < gbIDs.length; i++) {
    		if(button == gbIDs[i]) {
    			// Return row
    			return i/maxSize+1;
    		}
    	}
    	
    	// Button not in grid
    	return -1;
    }
    
    /**
     * Get the column index of a button by button id
     * 
     * @param button ID for the button (from R.java)
     * @return Column index for button or -1 if button not in grid
     */
    public int getCol(int button) {
    	// Find button in grid
    	for(int i = 0; i < gbIDs.length; i++) {
    		if(button == gbIDs[i]) {
    			// Return column
    			return i % maxSize +1;
    		}
    	}
    	
    	// Button not in grid
    	return -1;
    }
    
    /**
     * Processes a grid button being pressed. If the button pressed is in the row or
     * column of the missing tile, that button and any between it and the missing tile
     * are shifted one space toward the missing tile. The missing tile will end up
     * being in the location of this button when finished. If the button pressed is not
     * in line with the hidden tile, nothing happens.
     * 
     * @param row Row index of button that was pressed
     * @param col Column index of the button that was pressed
     */
    private void processButtonPress(int row, int col) {
    	// Check if row or column matches
    	if(row == hbRow) {
    		// Check if we're shifting right or shifting left in the row
    		if(col > hbCol) {
    			// Shift left
    			for(int i = hbCol; i < col; i++)
    				setButtonText(hbRow, i, getButtonText(hbRow, i+1));
    		} else {
    			// Shift right
    			for(int i = hbCol; i > col; i--)
    				setButtonText(hbRow, i, getButtonText(hbRow, i-1));
    		}
    		// Move missing tile
    		hideButton(row, col);
    	} else if(col == hbCol) {
    		// Check if we're shifting up or down in the column
    		if(row > hbRow) {
    			// We're shifting up
        		for(int i = hbRow; i < row; i++)
        			setButtonText(i, hbCol, getButtonText(i+1, hbCol));
    		} else {
    			// We're shifting down
        		for(int i = hbRow; i > row; i--)
        			setButtonText(i, hbCol, getButtonText(i-1, hbCol));
    		}
    		// Move missing tile
    		hideButton(row, col);
    	}    	
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
    	int row = getRow(id);
    	int col = getCol(id);
    	
    	// Handle the button press
    	processButtonPress(row, col);
    }
    
    /**
     * Scrambles a grid by making numMoves on the grid. All moves are valid. The higher
     * the number, the more scrambled the grid will be from its original layout.
     * 
     * @param numMoves Number of sliding moves to make
     */
    public void scrambleGrid(int numMoves) {
    	// Use a random number generate to choose which tiles to slide
    	Random gen = new Random();
    	
    	// Loop until all moves have been made
    	while(numMoves > 0) {
    		// For choosing whether we move a row or column
    		int dir = gen.nextInt(2);
    		
    		// For choosing which tile is "clicked". We're using a value of
    		// one less then the grid size here so we don't choose the hidden tile
    		int tile = gen.nextInt(gridSize-1)+1;
    		
    		if(dir == 0) {
    			// We're moving a column. If the tile value is greater or equal
    			// to the hidden button row we need to add one to account for the
    			// hidden tile
    			if(tile >= hbRow)
    				tile++;
    			
    			// Move column based on the button at row tile and column hbCol
    			// being pressed
    			processButtonPress(tile, hbCol);
    		} else {
    			// We're moving a row. If the tile value is greater or equal to
    			// the hidden button column we need to add one to account for the
    			// hidden tile
    			if(tile >= hbCol)
    				tile++;
    			
    			// Move row based on the button at row hbRow and column tile
    			// being pressed
    			processButtonPress(hbRow, tile);
    		}
    		
    		// Count this move
    		numMoves--;
    	}
    }
}
