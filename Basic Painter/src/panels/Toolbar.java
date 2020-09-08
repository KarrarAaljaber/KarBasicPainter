package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.GraphicsEnvironment;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Utils.JGradientButton;
import main.MainFrame;

public class Toolbar extends JPanel implements MouseListener, ItemListener  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton brush;
	private String brushes[] = {"Basic Brush", "Wall Brush", "Floor Brush", "Star Brush","Triangle Brush" };
	private JLabel brushLabel;
	
	private JButton eraser;
	private Icon eraserIcon;
	private JLabel eraserLabel;
	
	private JComboBox<Integer> brushSize;
	private JLabel sizeLabel;
	
	//Lower and Top / labels and contents
	private JPanel TopPanel;
	private JPanel LowerPanel;
	
	
	
	private JButton clearPage;
	private Icon clearIcon;
	private JLabel ClearLabel;
	
	//private JColorChooser color;	
	private static JGradientButton colorbtn;
	public static Color color = new Color(0,0,0);
	
	
	//text
	private JButton textbutton;
	private JComboBox<Integer> FontSize;
	private JLabel FontLabel; 
	private JComboBox<?> FontType;
	private String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	private JLabel FontTypeLabel;
	
	Border raisedbevel;

	
	//colorpicker
	private JButton colorpicker;

	private Color labelcolor = new Color(0,0,0);
	
	
	public Toolbar() {
		this.setPreferredSize(new Dimension(1280, 80));
		this.setLayout(new BorderLayout());
		
		raisedbevel = BorderFactory.createRaisedBevelBorder();

		
		
		this.setBorder(raisedbevel);
		
		TopPanel = new JPanel();
		TopPanel.setBorder(raisedbevel);
		TopPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		LowerPanel = new JPanel	();
		LowerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		TopPanel.setBackground(MainFrame.MainColor);
		LowerPanel.setBackground(MainFrame.MainColor);
		
		eraserIcon = new ImageIcon("icons/eraser.png");
		
		
		
		//brushsize from 10 - 100
		Vector<Integer> v = new Vector<Integer>();
		for(int i = 2; i<= 100; i+=2) {
			v.addElement(i);
		}
		
		Vector<Integer> vv = new Vector<Integer>();
		for(int i = 10; i<= 100; i+=2) {
			vv.addElement(i);
		}
		//Vector<Object> vvv = new Vector<Object>();
		
		
		brushSize = new JComboBox<Integer>(v);
		brushSize.setBackground(MainFrame.MainColor);
		brushSize.addItemListener(this);
		brushSize.setBorder(raisedbevel);
		brushSize.setForeground(labelcolor);
		brushSize.setPreferredSize(new Dimension(70,35));
		sizeLabel = new JLabel("brush size");
		sizeLabel.setPreferredSize(new Dimension(260,10));
		sizeLabel.setForeground(labelcolor);
		
		brush = new JButton();
		brush.setPreferredSize(new Dimension(50,35));
		brush.setBackground(MainFrame.MainColor);
		brush.setIcon(new ImageIcon("icons/brushes.png"));
		brush.setBorder(raisedbevel);
		brush.addMouseListener(this);
		brushLabel = new JLabel("Brushes");
		brushLabel.setPreferredSize(new Dimension(50,10));
		brushLabel.setForeground(labelcolor);

		
		eraser = new JButton();
		eraser.setIcon(eraserIcon);
		eraser.setPreferredSize(new Dimension(50,35));
		eraser.setBackground(MainFrame.MainColor);
		eraser.setBorder(raisedbevel);
		eraser.addMouseListener(this);
		eraserLabel = new JLabel("Eraser");
		eraserLabel.setPreferredSize(new Dimension(60, 10));
		eraserLabel.setForeground(labelcolor);

		clearPage = new JButton();
		clearIcon = new ImageIcon("icons/clear.png");
		clearPage.setIcon(clearIcon);
		clearPage.setPreferredSize(new Dimension(50,35));
		clearPage.setBackground(MainFrame.MainColor);
		clearPage.setBorder(raisedbevel);
		clearPage.addMouseListener(this);
		ClearLabel = new JLabel("Clear Page");
		ClearLabel.setPreferredSize(new Dimension(150,15));
		ClearLabel.setForeground(labelcolor);

		
		

		setColorbtn(new JGradientButton("Pick Brush Color"));
		getColorbtn().setPreferredSize(new Dimension(200, 35));
		getColorbtn().addMouseListener(this);
		getColorbtn().setBackground(getColor());
		getColorbtn().setBorder(raisedbevel);


		
		textbutton = new JButton("Add Text");
		textbutton.addMouseListener(this);
		textbutton.setBorder(raisedbevel);
		textbutton.setBackground(MainFrame.MainColor);
		textbutton.setPreferredSize(new Dimension(90,35));
		textbutton.setForeground(labelcolor);

		FontSize = new JComboBox<Integer>(vv);
		FontSize.setForeground(labelcolor);
		FontSize.addItemListener(this);
		FontSize.setBorder(raisedbevel);
		FontSize.setBackground(MainFrame.MainColor);
		FontSize.setPreferredSize(new Dimension(70,35));
		FontLabel = new JLabel("Font Size");
		FontLabel.setPreferredSize(new Dimension(80,10));
		FontLabel.setForeground(labelcolor);

		FontType = new JComboBox<String>(fonts);
		FontType.setForeground(labelcolor);
		FontType.setSelectedItem(fonts[5]);
		FontType.addItemListener(this);
		FontType.setBorder(raisedbevel);
		FontType.setBackground(MainFrame.MainColor);
		FontType.setPreferredSize(new Dimension(110,35));
		FontTypeLabel = new JLabel("Font");
		FontTypeLabel.setPreferredSize(new Dimension(80,10));
		FontTypeLabel.setForeground(labelcolor);

		colorpicker = new JButton();
		colorpicker.setPreferredSize(new Dimension(50,35));
		colorpicker.setBackground(MainFrame.MainColor);
		colorpicker.addMouseListener(this);
		colorpicker.setIcon(new ImageIcon("icons/picker.png"));
		colorpicker.setBorder(raisedbevel);

		
		add(TopPanel, BorderLayout.NORTH);
		add(LowerPanel, BorderLayout.SOUTH);
		
		LowerPanel.add(brush, FlowLayout.LEFT);
		LowerPanel.add(eraser);
		LowerPanel.add(brushSize);
		LowerPanel.add(getColorbtn());
		LowerPanel.add(clearPage);
		LowerPanel.add(textbutton);
		LowerPanel.add(FontSize);
		LowerPanel.add(FontType);
		LowerPanel.add(colorpicker);
		
		TopPanel.add(brushLabel, FlowLayout.LEFT);
		TopPanel.add(eraserLabel);
		TopPanel.add(sizeLabel);
		TopPanel.add(ClearLabel);
		TopPanel.add(FontLabel);
		TopPanel.add(FontTypeLabel);



		
		
	
	}

	




	public void mouseClicked(MouseEvent e) {
	
	}



	public static Color getColor() {
		return color;
	}






	public static void setColor(Color color) {
		Toolbar.color = color;
		
	}






	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == brush ) {
			brush.setBackground(new Color(127, 0,0));
		}
		if(e.getSource() == eraser ) {
			eraser.setBackground(new Color(127, 0,0));
		}
		if(e.getSource() == getColorbtn() ) {
			getColorbtn().setBackground(new Color(127, 0,0));
		}
		if(e.getSource() == clearPage ) {
			clearPage.setBackground(new Color(127, 0,0));
		}
		if(e.getSource() == textbutton ) {
			textbutton.setBackground(new Color(127, 0,0));
		}
		if(e.getSource() == colorpicker ) {
			colorpicker.setBackground(new Color(127, 0,0));
		}
		
	}



	public void mouseExited(MouseEvent e) {
		if(e.getSource() == brush ) {
			brush.setBackground(MainFrame.MainColor);
		}
		if(e.getSource() == eraser ) {
			eraser.setBackground(MainFrame.MainColor);
		}
		if(e.getSource() == getColorbtn() ) {
			getColorbtn().setBackground(getColor());
		}
		if(e.getSource() == clearPage ) {
			clearPage.setBackground(MainFrame.MainColor);
		}
		if(e.getSource() == textbutton ) {
			textbutton.setBackground(MainFrame.MainColor);
		}
		if(e.getSource() == colorpicker ) {
			colorpicker.setBackground(MainFrame.MainColor);
		}
		
	}



	public void mousePressed(MouseEvent e) {
		if(e.getSource() == brush) {
			PaintArea.brushed = true;
			PaintArea.TriangleOn = false;
			PaintArea.eraser = false;
			PaintArea.clear = false;
			PaintArea.WriteText = false;
			PaintArea.RectangleOn = false;
			PaintArea.CircleOn = false;
			PaintArea.PickerOn = false;
			PaintArea.wallbrush = false;
			PaintArea.floorBrush = false;
			PaintArea.TriangleBrush = false;
			PaintArea.StarBrush = false;
			PaintArea.star = false;
			
			String str = (String) (JOptionPane.showInputDialog(null, "Brushes", " Pick Brush",
					JOptionPane.PLAIN_MESSAGE, new ImageIcon("icons/brushes.png"), brushes, 
					"Basic Brush"));
			
			if (str == "Basic Brush") {
				PaintArea.brushed = true;
				PaintArea.wallbrush = false;
				PaintArea.PickerOn = false;
				PaintArea.TriangleBrush = false;
				PaintArea.WriteText = false;
				PaintArea.TriangleOn = false;
				PaintArea.clear = false;
				PaintArea.eraser = false;
				PaintArea.RectangleOn = false;
				PaintArea.CircleOn = false;
				PaintArea.floorBrush = false;
				PaintArea.StarBrush = false;
				PaintArea.star = false;
				
				brush.setIcon(new ImageIcon("icons/brush.png"));


			}
			if (str == "Wall Brush") {
				PaintArea.wallbrush = true;
				PaintArea.brushed = false;
				PaintArea.TriangleBrush = false;
				PaintArea.PickerOn = false;
				PaintArea.WriteText = false;
				PaintArea.TriangleOn = false;
				PaintArea.clear = false;
				PaintArea.eraser = false;
				PaintArea.RectangleOn = false;
				PaintArea.CircleOn = false;
				PaintArea.floorBrush = false;
				PaintArea.StarBrush = false;
				PaintArea.star = false;
				
				brush.setIcon(new ImageIcon("icons/wall.png"));

			}
			
			if (str == "Floor Brush") {
				PaintArea.floorBrush = true;
				PaintArea.wallbrush = false;
				PaintArea.brushed = false;
				PaintArea.TriangleBrush = false;
				PaintArea.PickerOn = false;
				PaintArea.WriteText = false;
				PaintArea.TriangleOn = false;
				PaintArea.clear = false;
				PaintArea.eraser = false;
				PaintArea.RectangleOn = false;
				PaintArea.CircleOn = false;
				PaintArea.StarBrush = false;
				PaintArea.star = false;
				
				brush.setIcon(new ImageIcon("icons/floor.png"));

			}
				
			if(str == "Star Brush") {
				PaintArea.StarBrush = true;
				PaintArea.TriangleBrush = false;
				PaintArea.floorBrush = false;
				PaintArea.wallbrush = false;
				PaintArea.brushed = false;
				PaintArea.PickerOn = false;
				PaintArea.WriteText = false;
				PaintArea.TriangleOn = false;
				PaintArea.clear = false;
				PaintArea.eraser = false;
				PaintArea.RectangleOn = false;
				PaintArea.CircleOn = false;
				PaintArea.star = false;
				
				brush.setIcon(new ImageIcon("icons/star.png"));


			}
			if(str == "Triangle Brush") {
				PaintArea.TriangleBrush = true;
				PaintArea.StarBrush = false;
				PaintArea.floorBrush = false;
				PaintArea.wallbrush = false;
				PaintArea.brushed = false;
				PaintArea.PickerOn = false;
				PaintArea.WriteText = false;
				PaintArea.TriangleOn = false;
				PaintArea.clear = false;
				PaintArea.eraser = false;
				PaintArea.RectangleOn = false;
				PaintArea.CircleOn = false;
				PaintArea.star = false;
				
				brush.setIcon(new ImageIcon("icons/trianglebrush.png"));

			}
					
			

			}
		
		if(e.getSource() == eraser) {
			PaintArea.eraser = true;
			PaintArea.TriangleOn = false;
			PaintArea.brushed = false;
			PaintArea.clear = false;
			PaintArea.WriteText = false;
			PaintArea.RectangleOn = false;
			PaintArea.CircleOn = false;
			PaintArea.PickerOn = false;
			PaintArea.wallbrush = false;
			PaintArea.floorBrush = false;
			PaintArea.TriangleBrush = false;
			PaintArea.StarBrush = false;
			PaintArea.star = false;

			}
		
		if(e.getSource() == clearPage) {
			PaintArea.clear = true;
			PaintArea.TriangleOn = false;
			PaintArea.brushed = false;
			PaintArea.eraser = false;
			PaintArea.WriteText = false;
			PaintArea.RectangleOn = false;
			PaintArea.CircleOn = false;
			PaintArea.PickerOn = false;
			PaintArea.wallbrush = false;
			PaintArea.floorBrush = false;
			PaintArea.TriangleBrush = false;
			PaintArea.StarBrush = false;
			PaintArea.star = false;

		}
		
		if(e.getSource() == getColorbtn()) {
			color = JColorChooser.showDialog(this, "Choose Brush Color", null);
		}
		
		if(e.getSource() == textbutton) {
			PaintArea.WriteText = true;
			PaintArea.TriangleOn = false;
			PaintArea.clear = false;
			PaintArea.brushed = false;
			PaintArea.eraser = false;
			PaintArea.RectangleOn = false;
			PaintArea.CircleOn = false;
			PaintArea.PickerOn = false;
			PaintArea.wallbrush = false;
			PaintArea.floorBrush = false;
			PaintArea.TriangleBrush = false;
			PaintArea.StarBrush = false;
			PaintArea.star = false;


		}
		
		if(e.getSource() == colorpicker) {
			PaintArea.PickerOn = true;
			PaintArea.WriteText = false;
			PaintArea.TriangleOn = false;
			PaintArea.clear = false;
			PaintArea.brushed = false;
			PaintArea.eraser = false;
			PaintArea.RectangleOn = false;
			PaintArea.CircleOn = false;
			PaintArea.wallbrush = false;
			PaintArea.floorBrush = false;
			PaintArea.TriangleBrush = false;
			PaintArea.StarBrush = false;
			PaintArea.star = false;

		}
	}



	
	public void mouseReleased(MouseEvent arg0) {
		
	}






	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == brushSize) {
			PaintArea.brushsize =  (int) brushSize.getSelectedItem();
		}
		
		if(e.getSource() == FontSize) {
			PaintArea.FontSize =  (int) FontSize.getSelectedItem();
		}
		if(e.getSource() == FontType) {
			PaintArea.Fonttype =  (String) FontType.getSelectedItem();
		}
		

	}
	
	
	
	public static JGradientButton getColorbtn() {
		return colorbtn;
	}






	public void setColorbtn(JGradientButton colorbtn) {
		Toolbar.colorbtn = colorbtn;
	}




}
