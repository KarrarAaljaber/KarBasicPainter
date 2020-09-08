package main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;





public class Runner {
	
	public Runner() {
  

		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
			    
				try {
					UIManager.setLookAndFeel(new  FlatLightLaf());
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		
			new MainFrame();
			FlatLightLaf.install();


			
			}
		});
	}
	
	public static void main(String[]args) {
		new Runner();
	}

}
