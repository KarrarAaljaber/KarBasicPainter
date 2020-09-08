package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import main.MainFrame;

public class ShapesPanel extends JPanel implements MouseListener, ItemListener, MouseMotionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel TopShapesPanel;
	private JPanel LowShapesPanel;
	
	/*
	private JPanel ShapesPanel;
	private JPanel ShowStatsPanel;
	*/
	
	private JButton rectangle;
	private JButton circle;
	private JButton Triangle;
	private JButton Star;
	
	
	private JCheckBox fill;
	private JLabel fillLabel;

	
	Border raisedbevel;
	
	public ShapesPanel() {
		raisedbevel = BorderFactory.createRaisedBevelBorder();
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(200,400));
		this.setBackground(MainFrame.MainColor);
		this.setBorder(raisedbevel);
		this.addMouseMotionListener(this);
		
		
		
			
			
			TopShapesPanel = new JPanel();
			TopShapesPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			TopShapesPanel.setBackground(MainFrame.MainColor);
			TopShapesPanel.setBorder(raisedbevel);
			
			
			LowShapesPanel = new JPanel();
			LowShapesPanel.setLayout(new GridBagLayout());
			LowShapesPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));



			
			rectangle = new JButton();
			rectangle.setPreferredSize(new Dimension(40,100));
			rectangle.setIcon(new ImageIcon("icons/rectangle.png"));
			rectangle.addMouseListener(this);
			rectangle.setBackground(new Color(145, 170, 173));
			rectangle.setBorder(raisedbevel);
			rectangle.setToolTipText("Rectangle");

			circle = new JButton();
			circle.setPreferredSize(new Dimension(40,100));
			circle.setIcon(new ImageIcon("icons/circle.png"));
			circle.addMouseListener(this);
			circle.setBackground(new Color(145, 170, 173));
			circle.setBorder(raisedbevel);
			circle.setToolTipText("Circle");

			Triangle = new JButton();
			Triangle.setPreferredSize(new Dimension(40,100));
			Triangle.setIcon(new ImageIcon("icons/triangle.png"));
			Triangle.addMouseListener(this);
			Triangle.setBackground(new Color(145, 170, 173));
			Triangle.setBorder(raisedbevel);
			Triangle.setToolTipText("Triangle");


			Star = new JButton ();
			Star.setPreferredSize(new Dimension(40,100));
			Star.setIcon(new ImageIcon("icons/star.png"));
			Star.addMouseListener(this);
			Star.setBackground(new Color(145, 170, 173));
			Star.setBorder(raisedbevel);
			Star.setToolTipText("Star");



		
			fill = new JCheckBox();
			fill.addItemListener(this);
			fill.setBackground(new Color(180,180,180));
			fill.setToolTipText("Fill The Shapes Below");

			fillLabel = new JLabel("Fill Shape");
			fillLabel.setPreferredSize(new Dimension(60, 20));
			fillLabel.setBackground(new Color(180,180,180));

			this.add(TopShapesPanel, BorderLayout.NORTH);
			TopShapesPanel.add(fill, FlowLayout.LEFT);
			TopShapesPanel.add(fillLabel);
		

			this.add(LowShapesPanel, BorderLayout.CENTER);
			
			GridBagConstraints gbc = new GridBagConstraints();
			
			gbc.gridx =0;
			gbc.gridy =0;
			LowShapesPanel.add(rectangle, gbc);
			gbc.gridx = 1;
			gbc.gridy = 0;
			LowShapesPanel.add(circle, gbc);
			
			gbc.gridx = 2;
			gbc.gridy = 0;
			LowShapesPanel.add(Triangle);
			
			gbc.gridx =3;
			gbc.gridy =0;
			LowShapesPanel.add(Star, gbc);
			
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

	}
	
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
			
			
			if(e.getSource() == rectangle) {
				rectangle.setBackground(Color.GREEN);
				}
				if(e.getSource() == circle) {
					circle.setBackground(Color.GREEN);
				}
				if(e.getSource() == Triangle) {
					Triangle.setBackground(Color.GREEN);
				}
				if(e.getSource() == Star) {
					Star.setBackground(Color.GREEN);
				}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == rectangle) {
			rectangle.setBackground(new Color(145, 170, 173));
			}
			if(e.getSource() == circle) {
				circle.setBackground(new Color(145, 170, 173));
				}
			if(e.getSource() == Triangle) {
				Triangle.setBackground(new Color(145, 170, 173));
				}
			if(e.getSource() == Star) {
				Star.setBackground(new Color(145, 170, 173));
				}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() == rectangle) {
			PaintArea.RectangleOn = true;
			PaintArea.TriangleOn = false;
			PaintArea.CircleOn = false;
			PaintArea.eraser = false;
			PaintArea.brushed = false;
			PaintArea.clear = false;
			PaintArea.WriteText = false;
			PaintArea.PickerOn = false;
			PaintArea.wallbrush = false;
			PaintArea.floorBrush = false;
			PaintArea.TriangleBrush = false;
			PaintArea.StarBrush = false;
			PaintArea.star = false;
		}
		if(e.getSource() == circle) {
			PaintArea.CircleOn = true;
			PaintArea.TriangleOn = false;
			PaintArea.RectangleOn = false;
			PaintArea.eraser = false;
			PaintArea.brushed = false;
			PaintArea.clear = false;
			PaintArea.WriteText = false;
			PaintArea.PickerOn = false;
			PaintArea.wallbrush = false;
			PaintArea.floorBrush = false;
			PaintArea.TriangleBrush = false;
			PaintArea.StarBrush = false;
			PaintArea.star = false;
		}
		
		if(e.getSource() == Triangle) {
			PaintArea.TriangleOn = true;
			PaintArea.CircleOn = false;
			PaintArea.RectangleOn = false;
			PaintArea.eraser = false;
			PaintArea.brushed = false;
			PaintArea.clear = false;
			PaintArea.WriteText = false;
			PaintArea.PickerOn = false;
			PaintArea.wallbrush = false;
			PaintArea.floorBrush = false;
			PaintArea.TriangleBrush = false;
			PaintArea.StarBrush = false;
			PaintArea.star = false;
		}
		
	
		if(e.getSource() == Star) {
			PaintArea.star = true;
			PaintArea.TriangleOn = false;
			PaintArea.CircleOn = false;
			PaintArea.RectangleOn = false;
			PaintArea.eraser = false;
			PaintArea.brushed = false;
			PaintArea.clear = false;
			PaintArea.WriteText = false;
			PaintArea.PickerOn = false;
			PaintArea.wallbrush = false;
			PaintArea.floorBrush = false;
			PaintArea.TriangleBrush = false;
			PaintArea.StarBrush = false;
		}		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
			PaintArea.filled = true;
			
	}else {
		PaintArea.filled = false;
	}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	
	
}
