package cs554.proj.slidingtiles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

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
    
    public void selectNumberMode(View view) {
    	Intent intent = new Intent(this, MainMenu.class);
    	startActivity(intent);
    }
    
    public void selectMathMode(View view) {
    	Intent intent = new Intent(this, MainMenu.class);
    	startActivity(intent);
    }
}
