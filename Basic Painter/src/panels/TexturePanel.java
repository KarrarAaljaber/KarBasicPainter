package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;

import Utils.ExtensionFileFilter;
import main.MainFrame;

public class TexturePanel extends JPanel implements MouseListener{
	


	public static JButton buttonarray[]= new JButton[20];
	
	public static ArrayList<JButton> buttons = new ArrayList<JButton>();
	private JButton nonebtn;
	private JButton cobblebtn;
	private JButton gravelbtn;
	private JButton camobtn;
	private JButton leatherbtn;
	private JButton barkbtn;
	
	private JButton flowerbtn;
	private JButton brickbtn;
	private JButton grassbtn;
	private JButton walnutbtn;
	private JButton oldpaperbtn;
	

	public JPanel PutIn;
	private JScrollPane scroll;

	
	private JPanel addTextures;
	private JButton addbtn;
	
	public TexturePanel(){
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(145, 170, 173));
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		
		PutIn= new JPanel();
		PutIn.setLayout(new GridBagLayout());
		
		addTextures = new JPanel();
		addTextures.setPreferredSize(new Dimension(this.getWidth(), 25));
		addTextures.setBorder(BorderFactory.createRaisedBevelBorder());
		addTextures.setBackground(MainFrame.MainColor);
		addTextures.setLayout(new FlowLayout(FlowLayout.LEFT));
		addbtn = new JButton("Add Textures");
		addbtn.setPreferredSize(new Dimension(150,20));
		addbtn.addMouseListener(this);
		addbtn.setBorder(BorderFactory.createRaisedBevelBorder());
		addTextures.add(addbtn);
		add(addTextures, BorderLayout.SOUTH);
		
		//none = [0]
		
		
		
		
		nonebtn= new JButton();
		nonebtn.setIcon(new ImageIcon("icons/none.png"));
		nonebtn.setBackground(new Color(145, 170, 173));
		nonebtn.setToolTipText("None");

		//cobblebtn = [1]
		cobblebtn = new JButton();
		cobblebtn.setIcon(new ImageIcon("icons/cobblebtn.jpg"));
		cobblebtn.setToolTipText("Cobble Texture");



		
		//gravelbtn = [2]
		gravelbtn = new JButton();
		gravelbtn.setIcon(new ImageIcon("icons/gravelbtn.jpg"));
		gravelbtn.setToolTipText("Gravel Texture");

		//camobtn = [3]
		camobtn = new JButton();
		camobtn.setIcon(new ImageIcon("icons/camobtn.jpg"));
		camobtn.setToolTipText("Camouflage Texture");

		//leatherbtn = [4]
		leatherbtn = new JButton();
		leatherbtn.setIcon(new ImageIcon("icons/leatherbtn.jpg"));
		leatherbtn.setToolTipText("Leather Texture");


		//barkbtn = [5]
		barkbtn = new JButton();
		barkbtn.setIcon(new ImageIcon("icons/Barkbtn.jpg"));
		barkbtn.setToolTipText("Bark Texture");

		
		//brickbtn = [6]
		brickbtn = new JButton();
		brickbtn.setIcon(new ImageIcon("icons/brickbtn.jpg"));
		brickbtn.setToolTipText("Brick Texture");

		
		//grassbtn = [7]
		grassbtn = new JButton();
		grassbtn.setIcon(new ImageIcon("icons/grassbtn.jpg"));
		grassbtn.setToolTipText("Grass Texture");


		//flowerbtn =[8]
		flowerbtn = new JButton();
		flowerbtn.setIcon(new ImageIcon("icons/flowerbtn.jpg"));
		flowerbtn.setToolTipText("Flower Pattern Texture");

		walnutbtn  =  new JButton();
		walnutbtn.setIcon(new ImageIcon("icons/walnutbtn.jpg"));
		walnutbtn.setToolTipText("American Walnut Texture");
		
		oldpaperbtn = new JButton();
		oldpaperbtn.setIcon(new ImageIcon("icons/oldpaperbtn.jpg"));
		oldpaperbtn.setToolTipText("Old Paper Texture");
		
		buttons.add(nonebtn);
		buttons.add(cobblebtn);
		buttons.add(gravelbtn);
		buttons.add(camobtn);
		buttons.add(leatherbtn);
		buttons.add(barkbtn);
		buttons.add(brickbtn);
		buttons.add(grassbtn);
		buttons.add(flowerbtn);
		buttons.add(walnutbtn);
		buttons.add(oldpaperbtn);

		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy =0;
		PutIn.add(nonebtn, gbc);
		
		gbc.gridx = 1;
		gbc.gridy =0;
		PutIn.add(cobblebtn, gbc);
		
		gbc.gridx = 2;
		gbc.gridy =0;
		PutIn.add(gravelbtn, gbc);
		
		gbc.gridx = 0;
		gbc.gridy =1;
		PutIn.add(camobtn, gbc);
		
		gbc.gridx = 1;
		gbc.gridy =1;
		PutIn.add(leatherbtn, gbc);
		
		gbc.gridx = 2;
		gbc.gridy =1;
		PutIn.add(barkbtn, gbc);
		

		
		gbc.gridx = 0;
		gbc.gridy =2;
		PutIn.add(brickbtn, gbc);
		
		gbc.gridx = 1;
		gbc.gridy =2;
		PutIn.add(grassbtn, gbc);
		
		gbc.gridx = 2;
		gbc.gridy =2;
		PutIn.add(flowerbtn, gbc);
		
		gbc.gridx = 0;
		gbc.gridy =3;
		PutIn.add(walnutbtn, gbc);
		
		
		gbc.gridx = 1;
		gbc.gridy =3;
		PutIn.add(oldpaperbtn, gbc);

		scroll = new JScrollPane(PutIn);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

	
		for(JButton i : buttons) {
			i.setPreferredSize(new Dimension(50, 40));
			i.setBorder(BorderFactory.createLineBorder(null));
			i.addMouseListener(this);

		}
		
		
		add(scroll);
		
		
		
		
	}

		
	

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		for(JButton i: buttons) {
			if(e.getSource() == i) {
				i.setBorder(BorderFactory.createLineBorder(Color.GREEN));
			}
		}
	}

	
	public void mouseExited(MouseEvent e) {

		for(JButton i: buttons) {
			if(e.getSource() == i) {
				i.setBorder(BorderFactory.createLineBorder(null));
			}
		}
		
	}

	public void mousePressed(MouseEvent e) {

	
		
		if(e.getSource() == nonebtn) {
			PaintArea.noneOn = true;
			PaintArea.TextureOn[0] = false;
			PaintArea.TextureOn[1] = false;
			PaintArea.TextureOn[2] = false;
			PaintArea.TextureOn[3] = false;
			PaintArea.TextureOn[4] = false;
			PaintArea.TextureOn[5] = false;
			PaintArea.TextureOn[6] = false;
			PaintArea.TextureOn[9] = false;

			PaintArea.TextureOn[7] = false;
			PaintArea.AddedOn = false;		
		}
	
		
		
		if(e.getSource() == cobblebtn) {
			PaintArea.TextureOn[0] = true;
			PaintArea.TextureOn[1] = false;
			PaintArea.TextureOn[2] = false;
			PaintArea.TextureOn[3] = false;
			PaintArea.TextureOn[4] = false;
			PaintArea.TextureOn[5] = false;
			PaintArea.TextureOn[6] = false;
			PaintArea.TextureOn[7] = false;
			PaintArea.TextureOn[8] = false;
			PaintArea.TextureOn[9] = false;

			PaintArea.AddedOn = false;
			PaintArea.noneOn = false;

		}
		if(e.getSource() == gravelbtn) {
			PaintArea.TextureOn[0] = false;
			PaintArea.TextureOn[1] = true;
			PaintArea.TextureOn[2] = false;
			PaintArea.TextureOn[3] = false;
			PaintArea.TextureOn[4] = false;
			PaintArea.TextureOn[5] = false;
			PaintArea.TextureOn[6] = false;
			PaintArea.TextureOn[7] = false;
			PaintArea.TextureOn[8] = false;
			PaintArea.TextureOn[9] = false;

			PaintArea.AddedOn = false;
			PaintArea.noneOn = false;

		}
		if(e.getSource() == camobtn) {
			PaintArea.TextureOn[0] = false;
			PaintArea.TextureOn[1] = false;
			PaintArea.TextureOn[2] = true;
			PaintArea.TextureOn[3] = false;
			PaintArea.TextureOn[4] = false;
			PaintArea.TextureOn[5] = false;
			PaintArea.TextureOn[6] = false;
			PaintArea.TextureOn[7] = false;
			PaintArea.TextureOn[8] = false;
			PaintArea.TextureOn[9] = false;

			PaintArea.AddedOn = false;
			PaintArea.noneOn = false;

		}

		if(e.getSource() == leatherbtn) {
			PaintArea.TextureOn[0] = false;
			PaintArea.TextureOn[1] = false;
			PaintArea.TextureOn[2] = false;
			PaintArea.TextureOn[3] = true;
			PaintArea.TextureOn[4] = false;
			PaintArea.TextureOn[5] = false;
			PaintArea.TextureOn[6] = false;
			PaintArea.TextureOn[7] = false;
			PaintArea.TextureOn[8] = false;
			PaintArea.TextureOn[9] = false;

			PaintArea.AddedOn = false;
			PaintArea.noneOn = false;

		}
		
		if(e.getSource() == barkbtn) {
			PaintArea.TextureOn[0] = false;
			PaintArea.TextureOn[1] = false;
			PaintArea.TextureOn[2] = false;
			PaintArea.TextureOn[3] = false;
			PaintArea.TextureOn[4] = true;
			PaintArea.TextureOn[5] = false;
			PaintArea.TextureOn[6] = false;
			PaintArea.TextureOn[7] = false;
			PaintArea.TextureOn[8] = false;
			PaintArea.TextureOn[9] = false;

			PaintArea.AddedOn = false;
			PaintArea.noneOn = false;

		}
		if(e.getSource() == brickbtn) {
			PaintArea.TextureOn[0] = false;
			PaintArea.TextureOn[1] = false;
			PaintArea.TextureOn[2] = false;
			PaintArea.TextureOn[3] = false;
			PaintArea.TextureOn[4] = false;
			PaintArea.TextureOn[5] = true;
			PaintArea.TextureOn[6] = false;
			PaintArea.TextureOn[7] = false;
			PaintArea.TextureOn[8] = false;
			PaintArea.TextureOn[9] = false;

			PaintArea.AddedOn = false;
			PaintArea.noneOn = false;

		}
		if(e.getSource() == grassbtn) {
			PaintArea.TextureOn[0] = false;
			PaintArea.TextureOn[1] = false;
			PaintArea.TextureOn[2] = false;
			PaintArea.TextureOn[3] = false;
			PaintArea.TextureOn[4] = false;
			PaintArea.TextureOn[5] = false;
			PaintArea.TextureOn[6] = true;
			PaintArea.TextureOn[7] = false;
			PaintArea.TextureOn[8] = false;
			PaintArea.TextureOn[9] = false;

			PaintArea.AddedOn = false;
			PaintArea.noneOn = false;

		}
		
		
		if(e.getSource() == flowerbtn) {
			PaintArea.TextureOn[0] = false;
			PaintArea.TextureOn[1] = false;
			PaintArea.TextureOn[2] = false;
			PaintArea.TextureOn[3] = false;
			PaintArea.TextureOn[4] = false;
			PaintArea.TextureOn[5] = false;
			PaintArea.TextureOn[6] = false;
			PaintArea.TextureOn[7] = true;
			PaintArea.TextureOn[8] = false;
			PaintArea.TextureOn[9] = false;

			PaintArea.AddedOn = false;
			PaintArea.noneOn = false;

		}
		if(e.getSource() == walnutbtn) {
			PaintArea.TextureOn[0] = false;
			PaintArea.TextureOn[1] = false;
			PaintArea.TextureOn[2] = false;
			PaintArea.TextureOn[3] = false;
			PaintArea.TextureOn[4] = false;
			PaintArea.TextureOn[5] = false;
			PaintArea.TextureOn[6] = false;
			PaintArea.TextureOn[7] = false;
			PaintArea.TextureOn[8] = true;
			PaintArea.TextureOn[9] = false;

			PaintArea.AddedOn = false;
			PaintArea.noneOn = false;

		}
		
		if(e.getSource() == oldpaperbtn) {
			PaintArea.TextureOn[0] = false;
			PaintArea.TextureOn[1] = false;
			PaintArea.TextureOn[2] = false;
			PaintArea.TextureOn[3] = false;
			PaintArea.TextureOn[4] = false;
			PaintArea.TextureOn[5] = false;
			PaintArea.TextureOn[6] = false;
			PaintArea.TextureOn[7] = false;
			PaintArea.TextureOn[8] = false;
			PaintArea.TextureOn[9] = true;

			PaintArea.AddedOn = false;
			PaintArea.noneOn = false;

		}
		
		
		if(e.getSource() == addbtn) {
			PaintArea.TextureOn[0] = false;
			PaintArea.TextureOn[1] = false;
			PaintArea.TextureOn[2] = false;
			PaintArea.TextureOn[3] = false;
			PaintArea.TextureOn[4] = false;
			PaintArea.TextureOn[5] = false;
			PaintArea.TextureOn[6] = false;
			PaintArea.TextureOn[7] = false;
			PaintArea.TextureOn[8] = false;
			PaintArea.TextureOn[9] = false;

			PaintArea.noneOn = false;
			PaintArea.AddedOn = true;

			
			
			getpaint();
		}
		
		
		
	}
	public void getpaint() {
		 JFileChooser choosefile = new JFileChooser();
		 FileFilter filter1 = new ExtensionFileFilter("PNG AND JPEG", new String[] { "PNG", "JPG", "JPEG"});
		 choosefile.setFileFilter(filter1);
		 choosefile.setDialogTitle("Open Texture");
		 choosefile.setApproveButtonText("Open");
		int returnval = choosefile.showOpenDialog(choosefile);
		
		if(returnval == JFileChooser.APPROVE_OPTION) {
			File file = choosefile.getSelectedFile();
			
				try {
					BufferedImage img = ImageIO.read(file);
					PaintArea.getPaintFromUser(file, this);


				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		
				
	}

	public void mouseReleased(MouseEvent arg0) {
		
	}




	

}
