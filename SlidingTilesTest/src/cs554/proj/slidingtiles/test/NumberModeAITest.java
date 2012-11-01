package cs554.proj.slidingtiles.test;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import cs554.proj.slidingtiles.NumberMode;
import cs554.proj.slidingtiles.NumberModeAI;
import cs554.proj.slidingtiles.SetupNumberGame;

public class NumberModeAITest  extends 
		ActivityInstrumentationTestCase2<NumberMode> {

	public NumberModeAITest() {
		super(NumberMode.class);
	}
	
	private NumberModeAI setupTest(int gridSize, int difficulty) {
		Intent intent = new Intent();
		intent.putExtra(SetupNumberGame.GRID_SIZE, gridSize);
		intent.putExtra(SetupNumberGame.AI_ENABLED, true);
		intent.putExtra(SetupNumberGame.AI_DIFFICULTY, difficulty);
		setActivityIntent(intent);
		NumberMode nm = getActivity();
		return new NumberModeAI(nm.aiGrid, nm, difficulty);
	}
	
	public void testDemoCase() {
		NumberModeAI nmAI = setupTest(5, 1);
		// Test a function here
		assertTrue("Dummy test", true);
	}
}
