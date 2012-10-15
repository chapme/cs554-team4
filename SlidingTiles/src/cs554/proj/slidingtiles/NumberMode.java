package cs554.proj.slidingtiles;

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
        /*
         * Hi Andrew: Since I haven't commented my code yet, you can delete
         * this note once you read it. SlidingGrid implements sliding tiles around
         * with button clicks. All you need to do is in this function set the value 
         * on each tile and hide the initial button that will be hid. The 
         * tiles are indexed by row and column (1 counted, not zero counted). 
         * So the tile at row 1 and column 1 is the top leftmost tile and row 5 column 5
         * is the bottom rightmost tile.
         */
        
        // Hid a button - you'll want to hide either the top left or bottom right button
        hideButton(1, 1);
        
        // Put numbers on the rest of the tiles in the valid winning order
        setButtonText(1, 2, "12");
        
        // Scramble the grid to create a new game for the user. The number passed in is
        // the number of moves. You might want to play around with this number (high is
        // more difficult).
        scrambleGrid(50);
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

}
