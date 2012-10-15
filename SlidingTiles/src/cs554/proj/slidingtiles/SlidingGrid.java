package cs554.proj.slidingtiles;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class SlidingGrid extends Activity {
	private int gridSize = 5;
	private int[] gbIDs = {R.id.gb11, R.id.gb12, R.id.gb13, R.id.gb14, R.id.gb15, R.id.gb21, R.id.gb22, R.id.gb23, R.id.gb24, R.id.gb25, R.id.gb31, R.id.gb32, R.id.gb33, R.id.gb34, R.id.gb35, R.id.gb41, R.id.gb42, R.id.gb43, R.id.gb44, R.id.gb45, R.id.gb51, R.id.gb52, R.id.gb53, R.id.gb54, R.id.gb55};
	private static int maxSize = 5;
	private int hbCol = -1;
	private int hbRow = -1;

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
    
    public int getGridSize() {
    	return gridSize;
    }
    
    public void back(View view) {
    	Intent intent = new Intent(this, MainMenu.class);
    	startActivity(intent);
    }
    
    private boolean checkBounds(int row, int col) {
    	if((row < 1) || (row > maxSize) || (col < 1) || (col > maxSize))
    		return false;
    	return true;
    }
    
    public boolean hideButton(int row, int col) {
    	if(!checkBounds(row, col))
    		return false;
    	Button b = (Button) findViewById(gbIDs[(row-1)*maxSize+col-1]);
    	b.setVisibility(View.GONE);
    	if(hbCol == -1) {
    		hbCol = col;
    		hbRow = row;
    	} else {
    		showButton(hbRow, hbCol);
    	}
    	hbCol = col;
    	hbRow = row;
    	return true;
    }
    
    private boolean showButton(int row, int col) {
    	if(!checkBounds(row,col))
    		return false;
    	Button b = (Button) findViewById(gbIDs[(row-1)*maxSize+col-1]);
    	b.setVisibility(View.VISIBLE);
    	return true;
    }
    
    public boolean setButtonText(int row, int col, String text) {
    	if(!checkBounds(row,col))
    		return false;
    	Button b = (Button) findViewById(gbIDs[(row-1)*maxSize+col-1]);
    	b.setText(text);
    	return true;
    }
    
    public String getButtonText(int row, int col) {
    	if(!checkBounds(row, col))
    		return "";
    	Button b = (Button) findViewById(gbIDs[(row-1)*maxSize+col-1]);
    	return (String) b.getText();
    }
    
    public int getRow(int button) {
    	for(int i = 0; i < gbIDs.length; i++) {
    		if(button == gbIDs[i]) {
    			return i/maxSize+1;
    		}
    	}
    	return -1;
    }
    
    public int getCol(int button) {
    	for(int i = 0; i < gbIDs.length; i++) {
    		if(button == gbIDs[i]) {
    			return i % maxSize +1;
    		}
    	}
    	return -1;
    }
    
    private void processButtonPress(int row, int col) {
    	if(row == hbRow) {
    		if(col > hbCol) {
    			for(int i = hbCol; i < col; i++)
    				setButtonText(hbRow, i, getButtonText(hbRow, i+1));
    		} else {
    			for(int i = hbCol; i > col; i--)
    				setButtonText(hbRow, i, getButtonText(hbRow, i-1));
    		}
    		hideButton(row, col);
    	} else if(col == hbCol) {
    		if(row > hbRow) {
        		for(int i = hbRow; i < row; i++)
        			setButtonText(i, hbCol, getButtonText(i+1, hbCol));
    		} else {
        		for(int i = hbRow; i > row; i--)
        			setButtonText(i, hbCol, getButtonText(i-1, hbCol));
    		}
    		hideButton(row, col);
    	}    	
    }
    
    public void processButtonPress(View view) {
    	int id = view.getId();
    	int row = getRow(id);
    	int col = getCol(id);
    	processButtonPress(row, col);
    }
    
    public void scrambleGrid(int numMoves) {
    	Random gen = new Random();
    	while(numMoves > 0) {
    		int dir = gen.nextInt(2);
    		int tile = gen.nextInt(4)+1;
    		if(dir == 0) {
    			if(tile >= hbRow)
    				tile++;
    			processButtonPress(tile, hbCol);
    		} else {
    			if(tile >= hbCol)
    				tile++;
    			processButtonPress(hbRow, tile);
    		}
    		numMoves--;
    	}
    }
}
