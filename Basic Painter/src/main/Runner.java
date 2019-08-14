package main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;




public class Runner {
	
	public Runner() {
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
			    
				
			try {
				UIManager.setLookAndFeel(new com.jtattoo.plaf.noire.NoireLookAndFeel());
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			MainFrame frame = new MainFrame();

			
			}
		});
	}
	
	public static void main(String[]args) {
		new Runner();
	}

}
