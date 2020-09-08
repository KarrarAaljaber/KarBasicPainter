package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import panels.MenuBar;
import panels.PaintArea;
import panels.ShapesPanel;
import panels.TexturePanel;
import panels.Toolbar;

public class MainFrame   extends JFrame implements WindowListener, ActionListener
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static final Dimension size = new Dimension(1280, 920);
	
	
	//inizalizing the components
	private PaintArea paint	;
	private Toolbar tb;
	private MenuBar menu;
	private ShapesPanel shapesPanel;
	private TexturePanel texturepanel;
	
	
	
	//the maintheme color
	public static Color MainColor = new Color(255, 0, 0);
	
	
	private JScrollPane paintscroll;
	public static Dimension ImageSize = new Dimension(1024,720);
	
	

	public MainFrame() {
		super("PaintJar");
	    this.addWindowListener(this);
		this.setLayout(new BorderLayout());
		
	
	    

		paint = new PaintArea();	
		menu = new MenuBar();
		tb = new Toolbar();
		shapesPanel = new ShapesPanel();
		texturepanel = new TexturePanel();
		paintscroll = new JScrollPane();
		paintscroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		paintscroll.setViewportView(paint);
		paintscroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


		//adding the menu and toolbar
		this.setJMenuBar(menu);
		this.add(tb, BorderLayout.NORTH);
		
		//menu actions save file open file exit ect
		
		menu.OpenFile(paint);
		menu.SaveFile(paint);
		menu.Exit(paint);
		MenuBar.Redo(paint);
		MenuBar.Undo(paint);
		menu.FlipHor(paint);
		menu.FlipVer(paint);
		menu.Filter(paint);
		menu.HSB(paint);
		menu.NewProject(this);

		
	
		//internal frames one for shapes one for paintarea and one for the textures
		JDesktopPane desktop = new JDesktopPane();
	    this.add(desktop);
	    JInternalFrame shapesframe = new JInternalFrame("Shapes", false, false, false, true);
	    JInternalFrame paintframe = new JInternalFrame("Painter", false, false, false, true);
	    JInternalFrame textureframe = new JInternalFrame("Textures", true, false, false, true);

	    //adding the internal frames inside the desktoppane
	    desktop.add(shapesframe);
	    desktop.add(paintframe);
	    desktop.add(textureframe);
	    
	    
	    //setting the protperties of the different internalframes
	    shapesframe.setBounds(1280-200, 0, 200, 250);
	    shapesframe.setVisible(true);
	    shapesframe.add(shapesPanel);
	    shapesframe.setDoubleBuffered(true);
	    shapesframe.setResizable(false);
	    shapesframe.setClosable(false);
	    
	    
	    paintframe.setDoubleBuffered(true);
	    paintframe.setSize((int)ImageSize.getWidth(), (int)ImageSize.getHeight());
	    paintframe.setVisible(true);
	    paintframe.add(paint);
	    paintframe.setForeground(new Color(127,0,0));
	
	    textureframe.setDoubleBuffered(true);
	    textureframe.setBounds(1280-200, 252, 200, 280);
	    textureframe.setVisible(true);
	    textureframe.add(texturepanel);
	    textureframe.setForeground(new Color(127,0,0));
	

		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setMaximumSize(size);
		this.setLocationRelativeTo(null);
		this.setResizable(true); 
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
		
	}

	public  void close() {
		dispose();

	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosing(WindowEvent arg0) {
		
		//When windows is closing open a yes no optionpane and ask the user if he wants to save before closing. using same method as menu.SaveFile
		int result = JOptionPane.showConfirmDialog(null, "Do you want to save before closing?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
		if(result == JOptionPane.YES_OPTION) {
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogTitle("Save file");
			chooser.setApproveButtonText("Save");
			int returnval = chooser.showOpenDialog(chooser);
			
			if(returnval == JFileChooser.APPROVE_OPTION ) {
				File file = chooser.getSelectedFile();
			
			
					try {
						paint.scan( file);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
		
				
				System.exit(0);
				
			
			}else {
				System.exit(0);
			}
		
			
		}else {
			System.exit(0);
		}
		
	}


	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowOpened(WindowEvent e) {
		//CanvasSize();
	}

	private JLabel wLabel, hLabel;
	private JTextField width, height;
	private int WIDTH, HEIGHT;
	public  JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setPreferredSize(new Dimension( 200, 120));
		
		wLabel = new JLabel("Width");
		hLabel = new JLabel("Height");
		width = new JTextField();
		height = new JTextField();
		width.setPreferredSize(new Dimension(50,5));

		width.addActionListener(this);
		height.addActionListener(this);

		
		
		panel.setForeground(new Color(127,0,0));
		panel.add(wLabel);
		panel.add(width);
		
		panel.add(hLabel);
		panel.add(height);
		
		
		return panel;
	}
	
	public void CanvasSize() {
		JPanel panel = createPanel();
		  int dialogResponse = JOptionPane.showOptionDialog
		            (this,                 
		             panel,
		             "Canvas Size",
		             JOptionPane.OK_CANCEL_OPTION,
		             JOptionPane.QUESTION_MESSAGE,
		             null, null, null
		            );
		    if (JOptionPane.OK_OPTION == dialogResponse) {
		    	setImageSize(WIDTH, HEIGHT);
		    }
		    
		    if (JOptionPane.CANCEL_OPTION == dialogResponse) {
		    	System.exit(0);
		    }
		    if (JOptionPane.CLOSED_OPTION == dialogResponse) {
		    	System.exit(0);
		    }
		}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == width) {
			WIDTH = Integer.parseInt(width.getText());
		}
		if(e.getSource() == height) {
			HEIGHT = Integer.parseInt(height.getText());
		}
	}
	
	public static Dimension getImageSize() {
		return ImageSize;
	}

	public static void setImageSize(int w, int h ) {
		ImageSize = new Dimension(w,h);
	}

}
