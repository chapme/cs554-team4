package cs554.proj.slidingtiles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.RadioButton;

public class SetupNumberGame extends Activity {
	private int gridSize = 5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_number_game);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_setup_number_game, menu);
        return true;
    }
    
    public void back(View view) {
    	Intent intent = new Intent(this, MainMenu.class);
    	startActivity(intent);
    }
    
    public void onRadioButtonClicked(View view) {
    	boolean checked = ((RadioButton) view).isChecked();
    	if(!checked)
    		return;
    	
    	switch(view.getId()) {
    	case R.id.radioGrid2:
    		this.gridSize = 2;
    		break;
    	case R.id.radioGrid3:
    		this.gridSize = 3;
    		break;
    	case R.id.radioGrid4:
    		this.gridSize = 4;
    		break;
    	case R.id.radioGrid5:
    		this.gridSize = 5;
    		break;
    }
    }
}
