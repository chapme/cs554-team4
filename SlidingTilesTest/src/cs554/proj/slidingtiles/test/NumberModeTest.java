package cs554.proj.slidingtiles.test;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import cs554.proj.slidingtiles.Grid;
import cs554.proj.slidingtiles.NumberMode;
import cs554.proj.slidingtiles.R;
import cs554.proj.slidingtiles.SetupNumberGame;

public class NumberModeTest extends
		ActivityInstrumentationTestCase2<NumberMode> {
	NumberMode nm;

	public NumberModeTest() {
		super(NumberMode.class);
	}
	
	public void testAIOn() {
		Intent intent = new Intent();
		intent.putExtra(SetupNumberGame.AI_ENABLED, true);
		setActivityIntent(intent);
		nm = getActivity();
		ViewStub vs = (ViewStub) nm.findViewById(R.id.ai_stub);
		assertTrue("View stub is null", vs == null);
		View v = nm.findViewById(R.id.aiUI);
		assertTrue("AI grid is visible", v.getVisibility() == View.VISIBLE);
	}
	
	public void testCheckForWin() {
		/*nm = getActivity();
		int[][] ids = {{R.id.gb11, R.id.gb12},{R.id.gb21, R.id.gb22}};
		Button[][] buttons = new Button[2][2];
    	for(int i = 0; i < 2; i++)
    		for(int j = 0; j < 2; j++)
    			buttons[i][j] = new Button(nm.getApplicationContext());
		Grid g = new Grid(2, ids, buttons);
		g.hideButton(2, 2);
		g.setButtonText(1, 1, "1");
		g.setButtonText(1, 2, "2");
		g.setButtonText(2, 1, "3");
		assertTrue("Valid grid", nm.checkForWin(g));
		
		nm.runOnUiThread(new Runnable() {
			public void run() {
				nm.userGrid.hideButton(2, 2);
				nm.userGrid.setButtonText(1, 1, "1");
				nm.userGrid.setButtonText(1, 2, "2");
				nm.userGrid.setButtonText(2, 1, "3");
			}
		});		
		try {
			Thread.sleep(3000);
		} catch(Exception e) {
		}
		assertTrue("Valid grid", nm.checkForWin(nm.userGrid));

		nm.runOnUiThread(new Runnable() {
			public void run() {
				nm.userGrid.hideButton(1, 1);
				nm.userGrid.setButtonText(1, 2, "2");
				nm.userGrid.setButtonText(2, 1, "1");
				nm.userGrid.setButtonText(2, 2, "3");
			}
		});		
		try {
			Thread.sleep(3000);
		} catch(Exception e) {
		}
		assertFalse("Invalid grid", nm.checkForWin(nm.userGrid));

		nm.runOnUiThread(new Runnable() {
			public void run() {
				nm.userGrid.hideButton(1, 2);
				nm.userGrid.setButtonText(1, 1, "1");
				nm.userGrid.setButtonText(2, 1, "2");
				nm.userGrid.setButtonText(2, 2, "3");
			}
		});		
		try {
			Thread.sleep(3000);
		} catch(Exception e) {
		}
		assertFalse("Invalid grid", nm.checkForWin(nm.userGrid));*/
	}
	
	public void testProcessButtonPress() {}
	
	public void testSetAndGetWon() {
		nm = getActivity();
		assertTrue("Won is false", nm.getWon() == false);
		nm.setWon(true);
		assertTrue("Won is true", nm.getWon() == true);
	}
}
