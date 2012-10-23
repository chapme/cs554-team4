package cs554.proj.slidingtiles;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import java.util.Random;

/**
 * @author me
 *
 * The MathMode class generates a valid game layout for the math mode game for
 * sliding tiles. This class contains all of the support functions needed for this
 * mode.
 */
public class MathMode extends SlidingGrid {

    /**
     * Generate a valid grid for the math mode game
     * 
     * @see cs554.proj.slidingtiles.SlidingGrid#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, 5);
        // Generate a valid game layout and then scramble the grid so the
        // user can play.
        generateGame();
        userGrid.scrambleGrid(25);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_math_mode, menu);
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
     * This function fills out a row in the grid
     * 
     * @param index Which row to fill out (1 to gridSize)
     * @param v1 The value for the first tile in the row
     * @param v2 The value for the second tile in the row
     * @param v3 The value for the third tile in the row
     * @param v4 The value for the fourth tile in the row
     * @param v5 The value for the fifth tile in the row
     */
    private void setRow(int index, String v1, String v2, String v3, String v4, String v5) {
    	userGrid.setButtonText(index, 1, v1);
    	userGrid.setButtonText(index, 2, v2);
    	userGrid.setButtonText(index, 3, v3);
    	userGrid.setButtonText(index, 4, v4);
    	userGrid.setButtonText(index, 5, v5);
    }
    
    /**
     * The function fills out a column in the grid.
     * 
     * @param index Which column to fill out (1 to gridSize)
     * @param v1 The value for the first tile in the column
     * @param v2 Value for the second tile
     * @param v3 Value for the third tile
     * @param v4 Value for the fourth tile
     * @param v5 Value for the fifth tile
     */
    private void setCol(int index, String v1, String v2, String v3, String v4, String v5) {
    	userGrid.setButtonText(1, index, v1);
    	userGrid.setButtonText(2, index, v2);
    	userGrid.setButtonText(3, index, v3);
    	userGrid.setButtonText(4, index, v4);
    	userGrid.setButtonText(5, index, v5);
    }
    
    
    /**
     * Randomly chooses a tile value from the set of valid values.
     * 
     * @return Text for the tile
     */
    private String generateTile() {
    	Random gen = new Random();
    	int val = gen.nextInt(14);
    	if(val < 10)
    		return Integer.toString(val);
    	else if(val == 10)
    		return "+";
    	else if(val == 11)
    		return "-";
    	else if(val == 12)
    		return "x";
    	else
    		return "=";
    }
    
    /**
     * Generates a valid game layout with one valid equation in either
     * a row or column. The direction of the equation and it's values
     * are chosen uniformly at random.
     */
    private void generateGame() {
    	Random gen = new Random();
    	// Whether we go across a row or down a column
    	int dir = gen.nextInt(2);
    	// Which row or column we'll use
    	int index = gen.nextInt(userGrid.getGridSize())+1;
    	// Will the equals sign be on the second or fourth tile
    	int eqLoc = gen.nextInt(2);
    	// The first number used
    	int i = gen.nextInt(10);
    	// The second number used
    	int j = gen.nextInt(10);
    	// Which operator we'll use
    	String op;
    	// Operation result
    	int k;
    	
    	// Choose which operator we'll use and calculate the value for the equation
    	if(i*j < 10) {
    		op = "x";
    		k = i*j;
    	} else if(i+j < 10) {
    		op = "+";
    		k = i+j;
    	} else {
    		op = "-";
    		if(i-j < 0) {
    			int temp = i;
    			i = j;
    			j = temp;
    		}
    		k = i-j;
    	}
    	
    	// Enter the equation in the grid
    	if(dir == 0) {
    		// We're using a row. Fill in the equation based on location of the equals sign
    		if(eqLoc == 0)
    			setRow(index, Integer.toString(i), op, Integer.toString(j), "=", Integer.toString(k));
    		else
    			setRow(index, Integer.toString(k), "=", Integer.toString(i), op, Integer.toString(j));
    		//Fill out the rest of the grid
    		for(int x = 1; x <= userGrid.getGridSize(); x++) {
    			if(x == index)
    				continue;
    			setRow(x, generateTile(), generateTile(), generateTile(), generateTile(), generateTile());
    		}
    		// Choose which button to hide
    		int hr = gen.nextInt(userGrid.getGridSize()-1)+1;
    		if(hr >= index)
    			hr++;
    		int hc = gen.nextInt(userGrid.getGridSize())+1;
    		userGrid.hideButton(hr, hc);
    	} else {
    		// We're filling out a column. Fill out equation based on location of equals sign
    		if(eqLoc == 0)
    			setCol(index, Integer.toString(i), op, Integer.toString(j), "=", Integer.toString(k));
    		else
    			setCol(index, Integer.toString(k), "=", Integer.toString(i), op, Integer.toString(j));
    		// Fill out the rest of the grid
    		for(int x = 1; x <= userGrid.getGridSize(); x++) {
    			if(x == index)
    				continue;
    			setCol(x, generateTile(), generateTile(), generateTile(), generateTile(), generateTile());
    		}
    		// Choose a button to hide
    		int hr = gen.nextInt(userGrid.getGridSize())+1;
    		int hc = gen.nextInt(userGrid.getGridSize()-1)+1;
    		if(hc >= index)
    			hc++;
    		userGrid.hideButton(hr, hc);
    	}
    }

}
