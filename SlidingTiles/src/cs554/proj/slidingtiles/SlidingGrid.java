package cs554.proj.slidingtiles;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class SlidingGrid extends Activity {
	private int gridSize = 5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_grid);
    }
    
    public void onCreate(Bundle savedInstanceState, int gridSize) {
    	super.onCreate(savedInstanceState);
    	this.gridSize = gridSize;
        setContentView(R.layout.activity_sliding_grid);
        TextView textView = new TextView(this);
        textView.setText(Integer.toString(gridSize));
        setContentView(textView);
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
}
