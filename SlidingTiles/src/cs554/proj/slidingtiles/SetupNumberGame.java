package cs554.proj.slidingtiles;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SetupNumberGame extends Activity {

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
}
