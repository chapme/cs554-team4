package cs554.proj.slidingtiles.test;

import android.test.ActivityInstrumentationTestCase2;
import cs554.proj.slidingtiles.MathMode;

public class MathModeTest extends
ActivityInstrumentationTestCase2<MathMode> {
	MathMode mm;
	
	public MathModeTest() {
		super(MathMode.class);
	}
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		mm = getActivity();
	}

	public void testValidateEquation() {
		String[] t1 = {"1", "+", "2", "=", "3"};
		String[] t2 = {"4", "=", "2", "+", "2"};
		String[] t3 = {"3", "-", "1", "=", "2"};
		String[] t4 = {"3", "=", "7", "-", "4"};
		String[] t5 = {"2", "x", "2", "=", "4"};
		String[] t6 = {"6", "=", "2", "x", "3"};
		String[] t7 = {"4", "=", "2", "x", "2"};
		String[] t8 = {"x", "4", "=", "3", "3"};
		String[] t9 = {"4", "+", "4", "=", "1"};
		String[] t10 = {"4", "=", "2", "-", "2"};
		String[] t11 = {"3", "-", "2", "=", "2"};
		String[] t12 = {"8", "=", "7", "-", "4"};
		String[] t13 = {"2", "x", "3", "=", "4"};
		String[] t14 = {"2", "=", "2", "x", "3"};
		
		assertTrue("+=", mm.validateEquation(t1));
		assertTrue("=+", mm.validateEquation(t2));
		assertTrue("-=", mm.validateEquation(t3));
		assertTrue("=-", mm.validateEquation(t4));
		assertTrue("x=", mm.validateEquation(t5));
		assertTrue("=x", mm.validateEquation(t6));
		assertFalse("Repeat equation", mm.validateEquation(t7));
		assertFalse("Repeat equation", mm.validateEquation(t1));
		assertFalse("Bad equation", mm.validateEquation(t8));
		assertFalse("Bad equation", mm.validateEquation(t9));
		assertFalse("Bad equation", mm.validateEquation(t10));
		assertFalse("Bad equation", mm.validateEquation(t11));
		assertFalse("Bad equation", mm.validateEquation(t12));
		assertFalse("Bad equation", mm.validateEquation(t13));
		assertFalse("Bad equation", mm.validateEquation(t14));
	}
}
