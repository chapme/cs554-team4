package cs554.proj.slidingtiles;

import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

public class NumberMode extends SlidingGrid {

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

}
