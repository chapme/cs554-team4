package cs554.proj.slidingtiles;

import java.util.Random;

import android.widget.LinearLayout;
import android.widget.TextView;


public class NumberModeAI implements Runnable {
	
	/**
	 * The AI's grid state
	 */
	private Grid aiGrid;
	
	/**
	 * The instance of the number mode class that called this class
	 */
	private NumberMode nm;
	
	/**
	 * How difficult the AI should be
	 */
	private int difficulty;
	
	/**
	 * The row and column indices for the next move that will be made by the UI
	 * Must be a class parameter to work correctly with threading 
	 */
	private int row, col;
	
	/**
	 * Setup the AI instance with it's grid and the NumberMode instance that's calling
	 * it
	 * 
	 * @param g AI grid
	 * @param n Calling instance of NumberMode
	 * @param d The difficulty level of the AI
	 */
	public NumberModeAI(Grid g, NumberMode n, int d) {
		aiGrid = g;
		nm = n;
		difficulty = d;
	}

	/**
	 * Thread for running the AI
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// Make the thread sleep so the user has time to see the grid
		try {
			Thread.sleep(5000);
		} catch(InterruptedException e) {
		}
		
		// Loop until the game is finished
		while(!nm.getWon()) {
			// Get the linear layout for the AI - we'll need this to update the UI
			LinearLayout ll = (LinearLayout) nm.findViewById(R.id.llForPlayingGame);
			
			// TODO: Implement the AI. You'll want to call a function here that decides
			// what the next move will be and put that information in this.row and 
			// this.col.
			//
			// For now I'm just making random moves
			Random r = new Random();
			row = r.nextInt(aiGrid.getGridSize())+1;
			col = r.nextInt(aiGrid.getGridSize())+1;
			
			// This makes the move, posts it to the UI and checks for a win
			// If the game was won by the AI, the UI is updated accordingly
			ll.post(new Runnable() {
				public void run() {
					aiGrid.processButtonPress(row, col);
					if(nm.checkForWin(aiGrid)) {
						nm.setWon(true);
						TextView tv = (TextView) nm.findViewById(R.id.winnerTextArea);
						tv.setText("The AI won :(");
					}
				}
			});
			
			// Sleep for some amount of time
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
			}
		}
	}

}
