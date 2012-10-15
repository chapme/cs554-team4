package cs554.proj.slidingtiles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

/**
 * @author me
 *
 * This class supports the main menu that is displayed when the app first opens
 */
public class MainMenu extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }
    
    /**
     * This function is for the button to select the number mode game
     * 
     * @param view
     */
    public void selectNumberMode(View view) {
    	Intent intent = new Intent(this, SetupNumberGame.class);
    	startActivity(intent);
    }
    
    /**
     * This function is for the button to select the math mode game
     * @param view
     */
    public void selectMathMode(View view) {
    	Intent intent = new Intent(this, MathMode.class);
    	startActivity(intent);
    }
}
