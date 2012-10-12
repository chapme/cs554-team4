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
