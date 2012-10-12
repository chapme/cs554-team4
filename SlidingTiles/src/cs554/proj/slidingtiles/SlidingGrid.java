package cs554.proj.slidingtiles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class SlidingGrid extends Activity {
	private int gridSize = 5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_grid_5);
    }
    
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
    
    public void setGridSize(int gridSize) {
    	this.gridSize = gridSize;
    }
    
    public int getGridSize(int gridSize) {
    	return gridSize;
    }
    
    public void back(View view) {
    	Intent intent = new Intent(this, MainMenu.class);
    	startActivity(intent);
    }
}
