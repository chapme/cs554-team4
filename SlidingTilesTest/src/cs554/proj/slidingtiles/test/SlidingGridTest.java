package cs554.proj.slidingtiles.test;

import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import cs554.proj.slidingtiles.SlidingGrid;

public class SlidingGridTest extends
		ActivityInstrumentationTestCase2<SlidingGrid> {
	
	SlidingGrid sg;
	
	public SlidingGridTest() {
		super(SlidingGrid.class);
	}
	
	public void testButtonPressOnGrid() {
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
		int[] hbLoc = sg.userGrid.getHiddenButtonLocation();
		assertTrue("Hidden button at 1,1", hbLoc[0] == 1);
		assertTrue("Hidden button at 1,1", hbLoc[1] == 1);
		Button b = (Button) sg.findViewById(cs554.proj.slidingtiles.R.id.gb15);
		TouchUtils.clickView(this, b);
		hbLoc = sg.userGrid.getHiddenButtonLocation();
		assertTrue("Hidden button at 1,5", hbLoc[0] == 1);
		assertTrue("Hidden button at 1,5", hbLoc[1] == 5);
		String text = sg.userGrid.getButtonText(1, 1);
		assertTrue("Text at 1,1 is 12", text.equals("12"));
		text = sg.userGrid.getButtonText(1, 2);
		assertTrue("Text at 1,2 is 13", text.equals("13"));
		text = sg.userGrid.getButtonText(1, 3);
		assertTrue("Text at 1,3 is 14", text.equals("14"));
		text = sg.userGrid.getButtonText(1, 4);
		assertTrue("Text at 1,4 is 15", text.equals("15"));
		b = (Button) sg.findViewById(cs554.proj.slidingtiles.R.id.gb55);
		TouchUtils.clickView(this, b);
		hbLoc = sg.userGrid.getHiddenButtonLocation();
		assertTrue("Hidden button at 5,5", hbLoc[0] == 5);
		assertTrue("Hidden button at 5,5", hbLoc[1] == 5);
		text = sg.userGrid.getButtonText(1, 5);
		assertTrue("Text at 1,5 is 25", text.equals("25"));
		text = sg.userGrid.getButtonText(2, 5);
		assertTrue("Text at 2,5 is 35", text.equals("35"));
		text = sg.userGrid.getButtonText(3, 5);
		assertTrue("Text at 3,5 is 45", text.equals("45"));
		text = sg.userGrid.getButtonText(4, 5);
		assertTrue("Text at 4,5 is 55", text.equals("55"));
		b = (Button) sg.findViewById(cs554.proj.slidingtiles.R.id.gb51);
		TouchUtils.clickView(this, b);
		hbLoc = sg.userGrid.getHiddenButtonLocation();
		assertTrue("Hidden button at 5,1", hbLoc[0] == 5);
		assertTrue("Hidden button at 5,1", hbLoc[1] == 1);
		text = sg.userGrid.getButtonText(5, 2);
		assertTrue("Text at 5,2 is 51", text.equals("51"));
		text = sg.userGrid.getButtonText(5, 3);
		assertTrue("Text at 5,3 is 52", text.equals("52"));
		text = sg.userGrid.getButtonText(5, 4);
		assertTrue("Text at 5,4 is 53", text.equals("53"));
		text = sg.userGrid.getButtonText(5, 5);
		assertTrue("Text at 5,5 is 54", text.equals("54"));
		b = (Button) sg.findViewById(cs554.proj.slidingtiles.R.id.gb11);
		TouchUtils.clickView(this, b);
		hbLoc = sg.userGrid.getHiddenButtonLocation();
		assertTrue("Hidden button at 1,1", hbLoc[0] == 1);
		assertTrue("Hidden button at 1,1", hbLoc[1] == 1);
		text = sg.userGrid.getButtonText(2, 1);
		assertTrue("Text at 2,1 is 12", text.equals("12"));
		text = sg.userGrid.getButtonText(3,1);
		assertTrue("Text at 3,1 is 21", text.equals("21"));
		text = sg.userGrid.getButtonText(4,1);
		assertTrue("Text at 4,1 is 31", text.equals("31"));
		text = sg.userGrid.getButtonText(5,1);
		assertTrue("Text at 5,1 is 41", text.equals("41"));
	}

}
