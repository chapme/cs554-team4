package cs554.proj.slidingtiles;

import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NumberMode extends SlidingGrid {
	/**
	 * Stores whether or not the user has won the game yet.
	 * Once the game has been won, the tile buttons are
	 * disabled so no more moves can be made.
	 */
	private boolean won = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        int gridSize = intent.getIntExtra(SetupNumberGame.GRID_SIZE, 5);
        super.onCreate(savedInstanceState, gridSize);
        
        // Generate a winning grid
        generateValidGrid(gridSize);
        
        // Scramble the grid to create a new game for the user. 
        // Having the number of moves made be the square of the number of tiles
        // seems to be sufficient to generate a game.
        if(gridSize == 2)
        	scrambleGrid(9);
        else if(gridSize == 3)
        	scrambleGrid(64);
        else if(gridSize == 4)
        	scrambleGrid(225);
        else
        	scrambleGrid(576);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_number_mode, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    /**
     * Generates a winning grid for the number mode game.
     * The blank tile will either be in the top left or bottom right location.
     * The remaining tiles will be numbered 1 to N where N=gridSize*gridSize-1.
     * 
     * @param gridSize Size of the grid we're numbering
     */
    private void generateValidGrid(int gridSize) {
    	// Decide at random which button to hide
    	Random gen = new Random();
    	int hb = gen.nextInt(2);
    	int count;
    	if(hb == 0) {
    		// Hide the top left. Start the count at 0 so the first visible button
    		// we label is 1.
    		hideButton(1,1);
    		count = 0;
    	} else {
    		// Hide the bottom right. Start the count at 1 since the first button is
    		// visible. The hidden tile will be labeled 25.
    		hideButton(gridSize, gridSize);
    		count = 1;
    	}
    	
    	// Label each of the buttons, incrementing count by 1 after each labeling
    	for(int i = 1; i <= gridSize; i++) {
    		for(int j = 1; j <= gridSize; j++) {
    			setButtonText(i, j, Integer.toString(count));
    			count++;
    		}
    	}
    }
    
    /**
     * Checks whether or not the user has won the game
     * 
     * @return True if grid is in a valid layout
     */
    private boolean checkForWin() {
    	// Get the location of the hidden button
    	int hbLoc[] = getHiddenButtonLocation();
    	int count;
    	
    	// Check where the hidden button is
    	if((hbLoc[0] == 1) && (hbLoc[1] == 1)) {
    		// The hidden button is in the top left corner
    		// We'll start counting on the second tile so 
    		// set count to 0 so we ignore the first tile
    		count = 0;
    	} else if((hbLoc[0] == getGridSize()) && (hbLoc[1] == getGridSize())) {
    		// The hidden button is in the bottom right corner
    		// Start counting on 1 for the first tile
    		count = 1;
    	} else {
    		// The hidden button is not at one of the valid locations
    		// for winning the game so we know the user hasn't won
    		return false;
    	}
    	
    	// Check the grid and make sure the correct number is on each tile
    	for(int i = 1; i <= getGridSize(); i++) {
    		for(int j = 1; j <= getGridSize(); j++) {
    			if((count != 0) && (count != getGridSize()*getGridSize())) {
    				if(getButtonText(i,j) != Integer.toString(count)) {
    					// Wrong number is on the tile, user hasn't won
    					return false;
    				}
    			}
    			count++;
    		}
    	}
    	
    	// The user has won!
    	return true;
    }
    
    /**
     * Overrides the super class's processing function for button presses. 
     * We want to check and see after the button press if the grid is in a winning
     * position
     * 
     * @see cs554.proj.slidingtiles.SlidingGrid#processButtonPress(android.view.View)
     */
    public void processButtonPress(View view) {
    	// If the user has already won, ignore button presses so the grid stays in
    	// the winning layout
    	if(won == true)
    		return;
    	
    	// Call super function to process move
    	super.processButtonPress(view);
    	
    	// Check if the user has won or not
    	if(checkForWin()) {
    		// User won! Add text to screen saying they won and set won to true so
    		// future button presses on the grid are ignored
    		LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    		TextView tv = new TextView(this);
    		tv.setText("Winner!");
    		tv.setVisibility(View.VISIBLE);
    		View ll = findViewById(R.id.llForPlayingGame);
    		((LinearLayout) ll).addView(tv);
    		won = true;
    	}
    }

}
