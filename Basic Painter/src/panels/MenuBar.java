 package panels;


import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;

import Utils.ExtensionFileFilter;
import main.MainFrame;

public class MenuBar extends JMenuBar implements ChangeListener  {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Menu and menuitems
	private JMenu file;
	private JMenuItem OpenFile;
	private static JMenuItem SaveFile;
	private static JMenuItem NewProject;

	private JMenuItem Exit;
	
	private JMenu Edit;
	private static JMenuItem Undo;
	private static JMenuItem Redo;
	
	private JMenu Image;
	private static JMenuItem FlipHor;
	private static JMenuItem FlipVer;
	
	private JMenu Effects;
	private static JMenuItem Filter;
	private JMenuItem test;
	
	private String filters[] = {"Red Filter", "Green Filter", "Blue Filter", "Gray Filter"};

	
	Border raisedbevel;
	public MenuBar() {
		raisedbevel = BorderFactory.createRaisedBevelBorder();

		this.setBackground(MainFrame.MainColor);
		this.setBorder(raisedbevel);
		file = new JMenu("File");
		Edit = new JMenu("Edit");
		Image = new JMenu("Image");
		Effects = new JMenu("Effects");
		add(file);
		add(Edit);
		add(Image);
		add(Effects);
		
		
		NewProject = new JMenuItem("New Project");
		OpenFile = new JMenuItem("Open File              Ctrl + O");
		SaveFile = new JMenuItem("Save File              Ctrl + S");
		Exit = new JMenuItem("Exit");
		file.add(NewProject);
		file.add(OpenFile);
		file.add(SaveFile);
		file.add(Exit);

		Undo = new JMenuItem("Undo                 Ctrl + Z");
		Redo = new JMenuItem("Redo                 Ctrl + Y");
		Edit.add(Undo);
		Edit.add(Redo);
		
		FlipHor = new JMenuItem("Flip Horizontal");
		FlipVer = new JMenuItem("Flip Vertical");
		Image.add(FlipHor);
		Image.add(FlipVer);
		
		Filter = new JMenuItem("Add A Color Filter");
		test = new JMenuItem("HSB");
		Effects.add(Filter);
		Effects.add(test);
		
		
		
	}
	
	public void HSB(PaintArea paint) {
		test.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			 paint.HSB();			
			
			

			}
		
		});
	

}

	
	
	
	public void NewProject(MainFrame frame) {
		NewProject.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Do you want create a new project?", "New Project", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if(result == JOptionPane.YES_OPTION) {
					frame.close();
					new MainFrame();

				}
			}
			
			
		});
	}
	public  void Filter(PaintArea paint) {
		Filter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				paint.saveToStack(paint.getImg());

				String str = (String) (JOptionPane.showInputDialog(null, "Filters", " Pick A Filter",
						JOptionPane.PLAIN_MESSAGE, null, filters, 
						"Red Filter"));
				
				if(str == "Red Filter") {
					paint.ColorFilter(0);

				}
				if(str == "Green Filter") {
					paint.ColorFilter(1);

				}
				if(str == "Blue Filter") {
					paint.ColorFilter(2);

				}
		
				if(str == "Gray Filter") {
					paint.GrayScale();

				}
			

			}
			
		});
	}
	
	public  void FlipVer(PaintArea paint) {
		FlipVer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				paint.flip(PaintArea.FLIP_VERTICAL);
		

			}
			
		});
	}
	
	public  void FlipHor(PaintArea paint) {
		FlipHor.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				paint.flip(PaintArea.FLIP_HORIZONTAL);
			}
			
		});
	}


	
	
	
	public static void Undo(PaintArea paint) {
		Undo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				paint.undo();
			}
		
		});
	
	}
	
	public static void Redo(PaintArea paint) {
		Redo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				paint.redo();
			}
		
		});
	
	}
	public void Exit(PaintArea paint) {
		Exit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				
				
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Save file");
				chooser.setApproveButtonText("Save");
				int returnval = chooser.showOpenDialog(chooser);
				
				if(returnval == JFileChooser.APPROVE_OPTION ) {
					File file = chooser.getSelectedFile();
				
				
						try {
							paint.scan( file);
							System.exit(0);

						} catch (Exception e1) {
							e1.printStackTrace();
						}
				
				}else {
					System.exit(0);

				}
			}
			
		});
	}
	public void OpenFile(PaintArea paint) {
		//opening file via filechooser and addinga filter so it only shows png and jpg
		OpenFile.addActionListener(new ActionListener() {
					
				public void actionPerformed(ActionEvent e) {

					JFileChooser choosefile = new JFileChooser();
					 FileFilter filter1 = new ExtensionFileFilter("PNG AND JPEG", new String[] { "PNG", "JPG", "JPEG"});
					 choosefile.setFileFilter(filter1);
					 choosefile.setDialogTitle("Open file");
					 choosefile.setApproveButtonText("Open");
					int returnval = choosefile.showOpenDialog(choosefile);
					
					if(returnval == JFileChooser.APPROVE_OPTION) {
						File file = choosefile.getSelectedFile();
						try {
							//Buffered img reading the file and then we gonna draw this image on top on our existing image  in paintarea
							BufferedImage img = ImageIO.read(file);
							Graphics g = paint.getImg().getGraphics();
				//			MainFrame.setImageSize(img.getWidth(), img.getHeight());
							g.drawImage(img, 0,0, (int)MainFrame.getImageSize().getWidth(),(int) MainFrame.getImageSize().getHeight(), null);

							paint.repaint();
							
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						
						
					
					}
				
			}
		});
		
	}
	
	
	
	public  void SaveFile(PaintArea paint) {
		SaveFile.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				
				//save file adding filter here aswell and using the scan method from paintarea
		
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Save file");
				chooser.setApproveButtonText("Save");
				int returnval = chooser.showOpenDialog(chooser);
				
				if(returnval == JFileChooser.APPROVE_OPTION ) {
					File file = chooser.getSelectedFile();
				
				
						try {
							BufferedImage img = (BufferedImage) paint.getImg();
							paint.saveToStack(img);
							paint.scan( file);
						
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
			
					
					
					
				
				}
				
			}
		
		});
		

	
}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}




}
