package cs554.proj.slidingtiles;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import java.util.Random;

public class MathMode extends SlidingGrid {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, 5);
        generateGame();
        scrambleGrid(25);
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
    
    private void setRow(int index, String v1, String v2, String v3, String v4, String v5) {
    	setButtonText(index, 1, v1);
		setButtonText(index, 2, v2);
		setButtonText(index, 3, v3);
		setButtonText(index, 4, v4);
		setButtonText(index, 5, v5);
    }
    
    private void setCol(int index, String v1, String v2, String v3, String v4, String v5) {
    	setButtonText(1, index, v1);
    	setButtonText(2, index, v2);
    	setButtonText(3, index, v3);
    	setButtonText(4, index, v4);
    	setButtonText(5, index, v5);
    }
    
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
    
    private void generateGame() {
    	Random gen = new Random();
    	int dir = gen.nextInt(2);
    	int index = gen.nextInt(getGridSize())+1;
    	int eqLoc = gen.nextInt(2);
    	int i = gen.nextInt(10);
    	int j = gen.nextInt(10);
    	String op;
    	int k;
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
    	if(dir == 0) {
    		if(eqLoc == 0)
    			setRow(index, Integer.toString(i), op, Integer.toString(j), "=", Integer.toString(k));
    		else
    			setRow(index, Integer.toString(k), "=", Integer.toString(i), op, Integer.toString(j));
    		for(int x = 1; x <= getGridSize(); x++) {
    			if(x == index)
    				continue;
    			setRow(x, generateTile(), generateTile(), generateTile(), generateTile(), generateTile());
    		}
    		int hr = gen.nextInt(getGridSize()-1)+1;
    		if(hr >= index)
    			hr++;
    		int hc = gen.nextInt(getGridSize())+1;
    		hideButton(hr, hc);
    	} else {
    		if(eqLoc == 0)
    			setCol(index, Integer.toString(i), op, Integer.toString(j), "=", Integer.toString(k));
    		else
    			setCol(index, Integer.toString(k), "=", Integer.toString(i), op, Integer.toString(j));
    		for(int x = 1; x <= getGridSize(); x++) {
    			if(x == index)
    				continue;
    			setCol(x, generateTile(), generateTile(), generateTile(), generateTile(), generateTile());
    		}
    		int hr = gen.nextInt(getGridSize())+1;
    		int hc = gen.nextInt(getGridSize()-1)+1;
    		if(hc >= index)
    			hc++;
    		hideButton(hr, hc);
    	}
    }

}
