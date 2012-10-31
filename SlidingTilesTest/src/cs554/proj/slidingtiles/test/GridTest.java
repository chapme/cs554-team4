package cs554.proj.slidingtiles.test;

import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import cs554.proj.slidingtiles.Grid;
import cs554.proj.slidingtiles.SlidingGrid;

public class GridTest extends
ActivityInstrumentationTestCase2<SlidingGrid> {
	SlidingGrid sg;
	Grid g;
	
	public GridTest() {
		super(SlidingGrid.class);
	}
	
	public void testGridSize2() {

		sg = getActivity();
		sg.runOnUiThread(new Runnable() {
			public void run() {
				sg.onCreate(new Bundle(), 2);
				sg.userGrid.hideButton(1, 1);
			}
		});		
		try {
			Thread.sleep(3000);
		} catch(Exception e) {
		}
		
		assertTrue("Grid size 2", sg.userGrid.getGridSize() == 2);
	}
	
	public void testGridSize3() {
		sg = getActivity();
		sg.runOnUiThread(new Runnable() {
			public void run() {
				sg.onCreate(new Bundle(), 3);
				sg.userGrid.hideButton(1, 1);
			}
		});		
		try {
			Thread.sleep(3000);
		} catch(Exception e) {
		}
		
		assertTrue("Grid size 3", sg.userGrid.getGridSize() == 3);
	}
	
	public void testGridSize4() {
		sg = getActivity();
		sg.runOnUiThread(new Runnable() {
			public void run() {
				sg.onCreate(new Bundle(), 4);
				sg.userGrid.hideButton(1, 1);
			}
		});		
		try {
			Thread.sleep(3000);
		} catch(Exception e) {
		}
		
		assertTrue("Grid size 4", sg.userGrid.getGridSize() == 4);
	}
	
	public void testGridSize5() {
		sg = getActivity();
		sg.runOnUiThread(new Runnable() {
			public void run() {
				sg.onCreate(new Bundle(), 5);
				sg.userGrid.hideButton(1, 1);
			}
		});		
		try {
			Thread.sleep(3000);
		} catch(Exception e) {
		}
		
		assertTrue("Grid size 5", sg.userGrid.getGridSize() == 5);
	}
	
	public void testCheckBounds() {
		sg = getActivity();
		sg.runOnUiThread(new Runnable() {
			public void run() {
				sg.onCreate(new Bundle(), 3);
				sg.userGrid.hideButton(1, 1);
			}
		});		
		try {
			Thread.sleep(3000);
		} catch(Exception e) {
		}
		
		g = sg.userGrid;
		assertTrue("In bounds", g.checkBounds(1, 1));
		assertTrue("In bounds", g.checkBounds(3, 3));
		assertFalse("Out of bounds low", g.checkBounds(0, 2));
		assertFalse("Out of bounds low", g.checkBounds(2,0));
		assertFalse("Out of bounds high", g.checkBounds(2,4));
		assertFalse("Out of bounds high", g.checkBounds(4, 2));
	}
	
	public void testGetButtonID() {
		sg = getActivity();
		sg.runOnUiThread(new Runnable() {
			public void run() {
				sg.onCreate(new Bundle(), 3);
				sg.userGrid.hideButton(1, 1);
			}
		});		
		try {
			Thread.sleep(3000);
		} catch(Exception e) {
		}
		g = sg.userGrid;
		
		assertTrue("Valid button id", g.getButtonID(2, 2) == cs554.proj.slidingtiles.R.id.gb22);
		assertTrue("Invalid button", g.getButtonID(4,2) == -1);
	}
	
	public void testHideButton() {
		sg = getActivity();
		sg.runOnUiThread(new Runnable() {
			public void run() {
				sg.onCreate(new Bundle(), 3);
				sg.userGrid.hideButton(1, 1);
			}
		});		
		try {
			Thread.sleep(3000);
		} catch(Exception e) {
		}
		
		Button b = (Button) sg.findViewById(cs554.proj.slidingtiles.R.id.gb11);
		assertTrue("Button 1,1 is not visible", b.getVisibility() == View.GONE);
		int[] hbLoc = sg.userGrid.getHiddenButtonLocation();
		assertTrue("Hidden button location is 1,1", (hbLoc[0] == 1) && (hbLoc[1] == 1));
		sg.runOnUiThread(new Runnable() {
			public void run() {
				sg.userGrid.hideButton(2, 2);
			}
		});		
		try {
			Thread.sleep(1000);
		} catch(Exception e) {
		}
		assertTrue("Button 1,1 is visible", b.getVisibility() == View.VISIBLE);
		b = (Button) sg.findViewById(cs554.proj.slidingtiles.R.id.gb22);
		assertTrue("Button 2,2 is not visible", b.getVisibility() == View.GONE);
		hbLoc = sg.userGrid.getHiddenButtonLocation();
		assertTrue("Hidden button location is 2,2", (hbLoc[0] == 2) && (hbLoc[1] == 2));
	}
	
	public void testSetButtonText() {
		sg = getActivity();
		sg.runOnUiThread(new Runnable() {
			public void run() {
				sg.onCreate(new Bundle(), 3);
				sg.userGrid.hideButton(1, 1);
				sg.userGrid.setButtonText(2, 2, "t");
			}
		});		
		try {
			Thread.sleep(3000);
		} catch(Exception e) {
		}
		Button b = (Button) sg.findViewById(cs554.proj.slidingtiles.R.id.gb22);
		assertTrue("Button 2,2 has text t", b.getText().equals("t"));
		assertFalse("Set text on button out of bounds", sg.userGrid.setButtonText(4, 4, "test"));
	}
	
	public void testGetButtonText() {
		sg = getActivity();
		sg.runOnUiThread(new Runnable() {
			public void run() {
				sg.onCreate(new Bundle(), 3);
				sg.userGrid.hideButton(1, 1);
			}
		});		
		try {
			Thread.sleep(3000);
		} catch(Exception e) {
		}
		Button b = (Button) sg.findViewById(cs554.proj.slidingtiles.R.id.gb13);
		assertTrue("Get button text for 1,3", sg.userGrid.getButtonText(1, 3).equals(b.getText()));
		assertTrue("Get button text for out of bounds button", sg.userGrid.getButtonText(4, 4).equals(""));
	}
	
	public void testGetRow() {
		sg = getActivity();
		sg.runOnUiThread(new Runnable() {
			public void run() {
				sg.onCreate(new Bundle(), 3);
				sg.userGrid.hideButton(1, 1);
			}
		});		
		try {
			Thread.sleep(3000);
		} catch(Exception e) {
		}
		assertTrue("Button 2,2 in row 2", sg.userGrid.getRow(cs554.proj.slidingtiles.R.id.gb22) == 2);
		assertTrue("Invalid button, row is -1", sg.userGrid.getRow(cs554.proj.slidingtiles.R.id.gb55) == -1);
		assertTrue("Button from another grid", sg.userGrid.getRow(cs554.proj.slidingtiles.R.id.gbAI11) == -1);
	}
	
	public void testGetCol() {
		sg = getActivity();
		sg.runOnUiThread(new Runnable() {
			public void run() {
				sg.onCreate(new Bundle(), 3);
				sg.userGrid.hideButton(1, 1);
			}
		});		
		try {
			Thread.sleep(3000);
		} catch(Exception e) {
		}
		assertTrue("Button 2,2 in row 2", sg.userGrid.getCol(cs554.proj.slidingtiles.R.id.gb22) == 2);
		assertTrue("Invalid button, row is -1", sg.userGrid.getCol(cs554.proj.slidingtiles.R.id.gb55) == -1);
		assertTrue("Button from another grid", sg.userGrid.getCol(cs554.proj.slidingtiles.R.id.gbAI11) == -1);
	}
	
	public void testButtonPress() {
		sg = getActivity();
		sg.runOnUiThread(new Runnable() {
			public void run() {
				sg.onCreate(new Bundle(), 3);
				sg.userGrid.hideButton(1, 1);
			}
		});		
		try {
			Thread.sleep(3000);
		} catch(Exception e) {
		}
		
		g = sg.userGrid;sg.runOnUiThread(new Runnable() {
			public void run() {
				g.processButtonPress(1, 3);
			}
		});		
		try {
			Thread.sleep(1000);
		} catch(Exception e) {
		}
		int hbLoc[] = g.getHiddenButtonLocation();
		assertTrue("Hidden button at 1,3", (hbLoc[0] == 1) && (hbLoc[1] == 3));
		String text = g.getButtonText(1, 1);
		assertTrue("Button 1,1 has text 12", text.equals("12"));
		text = g.getButtonText(1,2);
		assertTrue("Button 1,2 has text 13", text.equals("13"));
		
		g = sg.userGrid;sg.runOnUiThread(new Runnable() {
			public void run() {
				g.processButtonPress(3, 3);
			}
		});		
		try {
			Thread.sleep(1000);
		} catch(Exception e) {
		}
		hbLoc = g.getHiddenButtonLocation();
		assertTrue("Hidden button at 3,3", (hbLoc[0] == 3) && (hbLoc[1] == 3));
		text = g.getButtonText(1, 3);
		assertTrue("Button 1,3 has text 23", text.equals("23"));
		text = g.getButtonText(2,3);
		assertTrue("Button 2,3 has text 33", text.equals("33"));
		
		g = sg.userGrid;sg.runOnUiThread(new Runnable() {
			public void run() {
				g.processButtonPress(3, 1);
			}
		});		
		try {
			Thread.sleep(1000);
		} catch(Exception e) {
		}
		hbLoc = g.getHiddenButtonLocation();
		assertTrue("Hidden button at 3,1", (hbLoc[0] == 3) && (hbLoc[1] == 1));
		text = g.getButtonText(3, 3);
		assertTrue("Button 3,3 has text 32", text.equals("32"));
		text = g.getButtonText(3, 2);
		assertTrue("Button 3,2 has text 31", text.equals("31"));
		
		g = sg.userGrid;sg.runOnUiThread(new Runnable() {
			public void run() {
				g.processButtonPress(1, 1);
			}
		});		
		try {
			Thread.sleep(1000);
		} catch(Exception e) {
		}
		hbLoc = g.getHiddenButtonLocation();
		assertTrue("Hidden button at 1,1", (hbLoc[0] == 1) && (hbLoc[1] == 1));
		text = g.getButtonText(2, 1);
		assertTrue("Button 2,1 has text 12", text.equals("12"));
		text = g.getButtonText(3,1);
		assertTrue("Button 3,1 has text 21", text.equals("21"));
		
		g = sg.userGrid;sg.runOnUiThread(new Runnable() {
			public void run() {
				g.processButtonPress(1, 4);
			}
		});		
		try {
			Thread.sleep(1000);
		} catch(Exception e) {
		}
		hbLoc = g.getHiddenButtonLocation();
		assertTrue("Hidden button at 1,1", (hbLoc[0] == 1) && (hbLoc[1] == 1));
		
		g = sg.userGrid;sg.runOnUiThread(new Runnable() {
			public void run() {
				g.processButtonPress(2, 2);
			}
		});		
		try {
			Thread.sleep(1000);
		} catch(Exception e) {
		}
		hbLoc = g.getHiddenButtonLocation();
		assertTrue("Hidden button at 1,1", (hbLoc[0] == 1) && (hbLoc[1] == 1));
	}

	public void testScrambleGrid() {
		sg = getActivity();
		sg.runOnUiThread(new Runnable() {
			public void run() {
				sg.onCreate(new Bundle(), 3);
				sg.userGrid.hideButton(1, 1);
				sg.userGrid.scrambleGrid(1);
			}
		});		
		try {
			Thread.sleep(4000);
		} catch(Exception e) {
		}
		
		int[] hbLoc = sg.userGrid.getHiddenButtonLocation();
		assertTrue("Hidden button is not at 1,1", (hbLoc[0] != 1) || (hbLoc[1] != 1));
	}
	
	public void testCopyGrid() {
		sg = getActivity();
		sg.runOnUiThread(new Runnable() {
			public void run() {
				sg.onCreate(new Bundle(), 3);
				sg.userGrid.hideButton(1, 1);
			}
		});		
		try {
			Thread.sleep(3000);
		} catch(Exception e) {
		}
		
		int[][] ids = {{cs554.proj.slidingtiles.R.id.gbAI11, cs554.proj.slidingtiles.R.id.gbAI12},{cs554.proj.slidingtiles.R.id.gbAI21, cs554.proj.slidingtiles.R.id.gbAI22}};
    	Button[][] buttons = new Button[2][2];
    	for(int i = 0; i < 2; i++)
    		for(int j = 0; j < 2; j++)
    			buttons[i][j] = new Button(sg.getApplicationContext());
    	g = new Grid(2, ids, buttons);
    	
    	assertFalse("Wrong grid size", sg.userGrid.copyGrid(g));
		sg = getActivity();
		sg.runOnUiThread(new Runnable() {
			public void run() {
				sg.onCreate(new Bundle(), 2);
				sg.userGrid.hideButton(1, 1);
				g.setButtonText(1, 1, "1");
				g.setButtonText(1, 2, "2");
				g.setButtonText(2, 1, "3");
				g.hideButton(2, 2);
				sg.userGrid.copyGrid(g);
			}
		});		
		try {
			Thread.sleep(5000);
		} catch(Exception e) {
		}
		
		int[] hbLoc = sg.userGrid.getHiddenButtonLocation();
		assertTrue("Hidden button is at 2,2", (hbLoc[0] == 2) && (hbLoc[1] == 2));
		assertTrue("Button 1,1 text is 1", sg.userGrid.getButtonText(1, 1).equals("1"));
		assertTrue("Button 1,2 text is 2", sg.userGrid.getButtonText(1, 2).equals("2"));
		assertTrue("Button 2,1 text is 3", sg.userGrid.getButtonText(2, 1).equals("3"));
	}
}
