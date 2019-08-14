package panels;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.color.ColorSpace;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Utils.SizedStack;
import Utils.Star;
import main.MainFrame;

public class PaintArea extends JPanel implements MouseMotionListener, MouseListener, KeyListener, ChangeListener {


	private static final long serialVersionUID = 1L;

	//This is basically used to get the point where the mouse is first clicked. i use this in the mousedragged method to set the start location of a shape
	private Point oldPoint = new Point(0,0);
	
	
	//the different tools boolean
	public static boolean brushed = true;
	public static boolean wallbrush = false;
	public static boolean eraser = false;
	public static boolean clear = false;
	public static boolean WriteText = false;
	public static boolean RectangleOn = false;
	public static boolean CircleOn = false;
	public static boolean TriangleOn = false;
	public static boolean PickerOn = false;
	public static boolean floorBrush = false;
	public static boolean TriangleBrush = false;
	public static boolean StarBrush = false;
	public static boolean filled = false;
	public static boolean star = false;
	
	//textures
	public static  boolean noneOn = true;
	public static boolean cobbleOn = false;
	public static boolean gravelOn = false;
	public static boolean camoOn = false;
	public static boolean leatherOn = false;
	public static boolean barkOn = false;
	public static boolean grassOn = false;
	public static boolean brickOn = false;
	public static boolean flowerOn = false;
	public static boolean AddedOn = false;
	
	public static boolean TextureOn [] = new boolean[20];
	

	public static TexturePaint cobbletp;
    public static TexturePaint graveltp;
    public static TexturePaint camotp;
    public static TexturePaint leathertp;
    public static TexturePaint barktp;
    public static TexturePaint grasstp;
    public static TexturePaint bricktp;
    public static TexturePaint flowertp;
    public static TexturePaint walnuttp;
    public static TexturePaint oldpapertp;

    public static TexturePaint textures[] = new TexturePaint[20];

    
	private BufferedImage none;
	private BufferedImage cobble;
	private BufferedImage gravel;
	private BufferedImage camo;
	private BufferedImage leather;
	private BufferedImage bark;
	private BufferedImage grass;
	private BufferedImage flower;
	private BufferedImage brick;
	private BufferedImage walnut;
	private BufferedImage oldpaper;

	public static TexturePaint paint = null;

	
	

	public static int brushsize = 2;
	public static int FontSize = 10;
	public static boolean openfile = false;
	

	public static String Fonttype= "arial";

	private static boolean addOn;
	
	
	private Image background;
	private Image img;
	private Graphics2D g2d;
	



	Border raisedbevel;
	
	
	//stack for undo and redo
	private final SizedStack<Image> undoStack = new SizedStack<>(50);
	private final SizedStack<Image> redoStack = new SizedStack<>(50);

	
	
	public static final int FLIP_VERTICAL = 1;
	public static final int FLIP_HORIZONTAL = -1;

	public static double zoomFactor = 1;
	
	private JPanel infopanel;
	private JLabel PosInfo;
	private JLabel WidthnHeight;
	//public  Dimension CanvasSize = new Dimension((int)MainFrame.getImageSize().getWidth(), (int) MainFrame.getImageSize().getHeight()); 

	public PaintArea() {
		this.setLayout(new BorderLayout());
		this.setFocusable(true);
		this.addKeyListener(this);
	//	this.setPreferredSize(new Dimension((int)MainFrame.getImageSize().getWidth(), (int)MainFrame.getImageSize().getHeight()));
		
		raisedbevel = BorderFactory.createRaisedBevelBorder();
		
		infopanel = new JPanel();
		infopanel.setPreferredSize(new Dimension(this.getWidth(), 20));
		infopanel.setBorder(raisedbevel);
		infopanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		PosInfo = new JLabel();
	//	WidthnHeight = new JLabel(" | " + (int)MainFrame.getImageSize().getWidth() + " x " + (int)MainFrame.getImageSize().getHeight());
		infopanel.add(PosInfo);
		//infopanel.add(WidthnHeight);
		
		try {
			cobble = ImageIO.read(new File("textures/cobble.jpg"));
			gravel = ImageIO.read(new File("textures/gravel.jpg"));
			camo = ImageIO.read(new File("textures/camo.jpg"));
			leather = ImageIO.read(new File("textures/leather.jpg"));
			bark = ImageIO.read(new File("textures/Bark.jpg"));
			brick = ImageIO.read(new File("textures/brick.jpg"));
			grass = ImageIO.read(new File("textures/grass.jpg"));
			flower = ImageIO.read(new File("textures/flower.jpg"));
			walnut = ImageIO.read(new File("textures/walnut.jpg"));
			oldpaper = ImageIO.read(new File("textures/oldpaper.jpg"));


		} catch (IOException e) {
			e.printStackTrace();
		}
		
		textures[0] = new TexturePaint(cobble, new Rectangle(0, 0, 200, 100));
		textures[1] = new TexturePaint(gravel, new Rectangle(0, 0, 500, 250));
		textures[2] = new TexturePaint(camo, new Rectangle(0, 0, 200, 100));
		textures[3] = new TexturePaint(leather, new Rectangle(0, 0, 500, 250));
		textures[4] = new TexturePaint(bark, new Rectangle(0, 0, 500, 250));
		textures[5] = new TexturePaint(brick, new Rectangle(0, 0, 500, 250));
		textures[6] = new TexturePaint(grass, new Rectangle(0, 0, 500, 250));
		textures[7] = new TexturePaint(flower, new Rectangle(0, 0, 200, 100));
		textures[8] = new TexturePaint(walnut, new Rectangle(0, 0, 200, 100));
		textures[9] = new TexturePaint(oldpaper, new Rectangle(0, 0, 600, 450));



		add(infopanel, BorderLayout.SOUTH);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setBackground(new Color(200,200,200));
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(img == null) {
			
			img = createImage((int)MainFrame.ImageSize.getWidth() , (int)MainFrame.ImageSize.getHeight());
			g2d = (Graphics2D) img.getGraphics();
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);	
			clear();
		}
	
	       g.drawImage(img,  0,0, (int)MainFrame.ImageSize.getWidth() , (int)MainFrame.ImageSize.getHeight() ,null);


	}

	public void mouseDragged(MouseEvent e) {
		Point newPoint= new Point(e.getX(), e.getY());
		if(g2d != null && brushed) {
			g2d.setColor(Toolbar.color);
			g2d.setStroke(new BasicStroke(brushsize,  BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g2d.setPaint(getPaint());
			g2d.drawLine((int)oldPoint.getX(), (int)oldPoint.getY(),e.getX(), e.getY());
			repaint();
			oldPoint = newPoint;
		}
		
		if(g2d != null && wallbrush) {
			
			g2d.setColor(Toolbar.color);
			g2d.setStroke(new BasicStroke(brushsize,  BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g2d.setPaint(getPaint());
			g2d.drawLine(e.getX(), e.getX(),e.getX(), e.getY());
			repaint();
			oldPoint = newPoint;

		}
		
		if(g2d != null && floorBrush) {
			
			g2d.setColor(Toolbar.color);
			g2d.setStroke(new BasicStroke(brushsize,  BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g2d.setPaint(getPaint());
			g2d.drawLine(e.getX() - (int)oldPoint.getY(), e.getX() - (int)oldPoint.getY(),e.getX(), e.getY());
			repaint();
			oldPoint = newPoint; 
			
			
		
		}
		if(g2d != null && StarBrush) {
			
			g2d.setColor(Toolbar.color);
			g2d.setStroke(new BasicStroke(1,  BasicStroke.CAP_SQUARE, BasicStroke.CAP_SQUARE));
			g2d.setPaint(getPaint());
			g2d.draw(Star.createDefaultStar(brushsize, e.getX(), e.getY()));
			
	  		repaint();
		}
		
		if(g2d != null && TriangleBrush) {
			Point p2 = new Point(e.getX() + (int)((e.getX() - oldPoint.getX())), e.getY());
	  		Point p3 = new Point(e.getX() +(int)((e.getX() - oldPoint.getX())/2), e.getY() - (int)(e.getY() - oldPoint.getY()));
	 
	  		int[]xpoints = new int[3];
	  		xpoints[0]= e.getX();
	  		xpoints[1]= (int) p2.getX();
	  		xpoints[2] = (int) p3.getX();
	  		
	  		int[] ypoints = new int[3];
	  		ypoints[0]= e.getY();
	  		ypoints[1]= (int) p2.getY();
	  		ypoints[2] = (int) p3.getY();
	  		
			g2d.setStroke(new BasicStroke(brushsize,  BasicStroke.CAP_SQUARE, BasicStroke.CAP_SQUARE));

	  		g2d.setColor(Toolbar.color);
	  		Polygon triangle = new Polygon(xpoints, ypoints, 3);
			g2d.setPaint(getPaint());
	  		g2d.draw(triangle);
	  		repaint();
		}
		
		if(g2d != null && eraser) {
			g2d.setColor(Color.WHITE);
			g2d.drawRect(e.getX(), e.getY(), brushsize, brushsize);
			repaint();
		}
		
	
	
	}

	private TexturePaint getPaint() {
		 if(noneOn == true) {
			 paint = null;
		 }
		
		for(int i = 0; i <TextureOn.length  ; i++) {
			if(TextureOn[i ]) {
				paint = textures[i];
				
			}
		}
		
		
		return paint;
	}
	public static void setPaint(TexturePaint paint) {
		PaintArea.paint = paint;
	}
	
	public static  void  getPaintFromUser(File file, TexturePanel panel) throws IOException {
		BufferedImage texture;

		for(int i = 0; i< textures.length; i++) {
					//get a texture from a file that the user provides and set the paint to the loaded texture
					texture = ImageIO.read(file);
					textures[i]= new TexturePaint(texture, new Rectangle(0, 0, 200, 100));
					setPaint(textures[i]);
  
				
			
		}
	
		}
	
    public void mousePressed(MouseEvent e) {
    	oldPoint = new Point(e.getX(), e.getY());
    	//create and image and save it to the undo stack so we have and image before and action is performed that we can later go back to
         saveToStack(img);
         
    	
    	  if(g2d != null  && PickerOn) {
  	    	BufferedImage bi = (BufferedImage) img;
  	    	int y = e.getY();
  	    	int x = e.getX();
  	    		int p = bi.getRGB(0, 0);
  		    	
  	    			for(y = e.getY(); y < e.getY() - getHeight(); y++) {
  	    				for(x = e.getX(); y < e.getX() - (getWidth() - 200); x++) {
  	    				}
  	    				
  	    				
  	    			}
  					Color color = new Color(bi.getRGB(x, y));
  					Toolbar.setColor(color);
  					Toolbar.getColorbtn().setBackground(color);

  					repaint();
  				

    	  
    	  }
    		if(g2d != null && clear) {
    			g2d.setColor(Color.WHITE);
    			g2d.clearRect(0, 0, getWidth(), getHeight());
    			clear();
    			repaint();
    		}					
  	    					
  	   
	    if(g2d != null && WriteText ) {
			
			JTextArea ta = new JTextArea(20, 20);
			ta.setForeground(Toolbar.color);
			Font fn = new Font(Fonttype, 0 , 15);
			ta.setFont(fn);
			switch (JOptionPane.showConfirmDialog(null, new JScrollPane(ta), "Text", 1, 1,
					new ImageIcon("icons/text.png"))) {
			    case JOptionPane.OK_OPTION:
			    	drawText(ta.getText(), ta.getFontMetrics(fn), e.getX(), e.getY(), Toolbar.color);
			    	repaint();
			    
				}
			}
		  
		}
	    
	    
  
    public void drawText(String text, FontMetrics textMetrics, int x, int y, Color color)
    {
        // Ugly code to wrap text
        int lineHeight = textMetrics.getHeight();
        String textToDraw = text;
        String[] arr = textToDraw.split(" ");
        int nIndex = 0;
        int startX = x;
        int startY = y;
        while ( nIndex < arr.length )
        {
            String line = arr[nIndex++];
            while ( ( nIndex < arr.length ) && (textMetrics.stringWidth(line + " " + arr[nIndex]) < 300) )
            {
                line = line + " " + arr[nIndex];
                nIndex++;
            }
			Font fn = new Font(Fonttype, 0 , FontSize);
			
            g2d.setFont(fn);
			g2d.setPaint(getPaint());
            g2d.drawString( line, startX, startY);
            g2d.setColor(color);
            repaint();
            startY = startY + lineHeight;
        }
    }
	
	public void mouseReleased(MouseEvent e) {
		if(g2d != null && star) {
			if(filled) {
				g2d.setColor(Toolbar.color);
				g2d.setStroke(new BasicStroke(1,  BasicStroke.CAP_SQUARE, BasicStroke.CAP_SQUARE));
				g2d.setPaint(getPaint());
				g2d.fill(Star.createDefaultStar((oldPoint.getX() - e.getX() ) / 3, oldPoint.getX(), oldPoint.getY()));
			repaint();
			}
			if(!filled) {
				g2d.setColor(Toolbar.color);
				g2d.setStroke(new BasicStroke(1,  BasicStroke.CAP_SQUARE, BasicStroke.CAP_SQUARE));
				g2d.setPaint(getPaint());
				g2d.draw(Star.createDefaultStar((oldPoint.getX() - e.getX() )/ 3, oldPoint.getX(), oldPoint.getY()));
				
				repaint();
		}
			
		}
		if(g2d != null && RectangleOn) {
			if(filled) {
			g2d.setColor(Toolbar.color);
			g2d.setPaint(getPaint());
			g2d.fillRect( (int) oldPoint.getX(), (int) oldPoint.getY(), (int)(e.getX() - oldPoint.getX()), (int) (e.getY() - oldPoint.getY()));
			repaint();
			}
			if(!filled) {
				g2d.setColor(Toolbar.color);
				g2d.setPaint(getPaint());
				g2d.drawRect( (int) oldPoint.getX(), (int) oldPoint.getY(), (int)(e.getX() - oldPoint.getX()), (int) (e.getY() - oldPoint.getY()));
				
				repaint();
		}
		}
		
			 if(g2d != null && CircleOn) {
					if(filled) {
					g2d.setColor(Toolbar.color);
					g2d.setPaint(getPaint());
					g2d.fillOval((int) oldPoint.getX(), (int) oldPoint.getY(), (int)(e.getX() - oldPoint.getX()), (int) (e.getY() - oldPoint.getY()));

					repaint();
					}
					if(!filled) {
						g2d.setColor(Toolbar.color);
						g2d.setPaint(getPaint());
						g2d.drawOval((int) oldPoint.getX(), (int) oldPoint.getY(), (int)(e.getX() - oldPoint.getX()), (int) (e.getY() - oldPoint.getY()));
						
						repaint();
					}
					
					
				}
			  if(g2d != null && TriangleOn) {
				  		Point p2 = new Point(e.getX() + (int)((e.getX() - oldPoint.getX())), e.getY());
				  		Point p3 = new Point(e.getX() +(int)((e.getX() - oldPoint.getX())/2), e.getY() - (int)(e.getY() - oldPoint.getY()));
				  
				  		if(filled) {
				  		int[]xpoints = new int[3];
				  		xpoints[0]= e.getX();
				  		xpoints[1]= (int) p2.getX();
				  		xpoints[2] = (int) p3.getX();
				  		
				  		int[] ypoints = new int[3];
				  		ypoints[0]= e.getY();
				  		ypoints[1]= (int) p2.getY();
				  		ypoints[2] = (int) p3.getY();
				  		
				  		g2d.setColor(Toolbar.color);
				  		Polygon triangle = new Polygon(xpoints, ypoints, 3);
						g2d.setPaint(getPaint());
				  		g2d.fillPolygon(triangle);
				  		}
				  		if(!filled) {
					  		int[]xpoints = new int[3];
					  		xpoints[0]= e.getX();
					  		xpoints[1]= (int) p2.getX();
					  		xpoints[2] = (int) p3.getX();
					  		
					  		int[] ypoints = new int[3];
					  		ypoints[0]= e.getY();
					  		ypoints[1]= (int) p2.getY();
					  		ypoints[2] = (int) p3.getY();
					  		
					  		g2d.setColor(Toolbar.color);
					  		Polygon triangle = new Polygon(xpoints, ypoints, 3);
							g2d.setPaint(getPaint());
					  		g2d.drawPolygon(triangle);
					  		}
				  		
						repaint();
					}
		
	}

	 public void clear() {
		 if (background != null) {
	 	        setImage(copyImage(background));
	 	    } else {
			g2d.setPaint(Color.WHITE);
			g2d.fillRect(0, 0, getWidth() , getHeight());
			g2d.setPaint(Color.BLACK);
			repaint();
		}
	 }


	 public void undo() {
	 	    if (undoStack.size() > 0) {
	 	    	redoStack.push(undoStack.lastElement());
	 	        setImage(undoStack.pop());}}
	 
	 public void redo() {
	 	    if (redoStack.size() > 0) {
	 	        setImage(redoStack.lastElement());
	 	        undoStack.push(redoStack.pop());}}
	
	 private void setImage(Image img) {
	 	    g2d = (Graphics2D) img.getGraphics();
	 	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	 	    g2d.setPaint(Color.black);
	 	    this.img = img;
	 	    repaint();
	 	}
	 public void setBackground(Image img) {background = copyImage(img);setImage(copyImage(img));}
	 	
	 	private BufferedImage copyImage(Image img) {
	 	    BufferedImage copyOfImage = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_RGB);
	 	    Graphics g = copyOfImage.createGraphics();
	 	    g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
	 	    return copyOfImage;
	 	}
	 	//add image to the undoStack
	 	public void saveToStack(Image img) {undoStack.push(copyImage(img));}
	 	
	 	public void keyPressed(KeyEvent e) {
			 if ((e.getKeyCode() == KeyEvent.VK_Z) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
				this.undo();}
			 if ((e.getKeyCode() == KeyEvent.VK_Y) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
					this.redo();
				}}
		
		//flipping the image hor and vertically
		public void flip(int direction) {
			BufferedImage img = (BufferedImage) this.getImg();
			
			int width = img.getWidth();
			int height = img.getHeight();
			BufferedImage flipped = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			
			for(int y = 0; y < height; y++) {
				for(int x = 0; x < width; x++) {
					switch(direction) {
					case FLIP_HORIZONTAL:
						flipped.setRGB((width -1 ) - x, y, img.getRGB(x, y));
						break;
					case FLIP_VERTICAL:
						flipped.setRGB(x, (height - 1) - y , img.getRGB(x, y));
						break;	
					}}}
			g2d.drawImage(flipped, 0, 0, null);
			repaint();
		}
		
		 //basically just use the print function inside the jpanel and print the screen and use IMageIO to write the img. we can use this function in the SaveFile method in menubar
		 public  void scan( File file)  {
				g2d = (Graphics2D) img.getGraphics();
				BufferedImage image = new BufferedImage((int)MainFrame.getImageSize().getWidth(), (int)MainFrame.getImageSize().getHeight() - 20, BufferedImage.TYPE_INT_RGB);
				this.paint(image.getGraphics());
				
				try {	
					ImageIO.write(image, "jpg", file);
					} catch (IOException e) {e.printStackTrace();
				
				}
			}

		public void keyTyped(KeyEvent e) {} 
		public void keyReleased(KeyEvent arg0) {}
	 	public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
		public void mouseMoved(MouseEvent e) {
			PosInfo.setText(" X: " + e.getX() + "    " + " Y: " + e.getY());
 
		}
		
		public Image getImg() {	return img;}
		public void setImg(Image img) {this.img = img;}


		public void ColorFilter(int filter) {
			BufferedImage image = (BufferedImage) this.getImg();
			
			
			for(int y = 0; y < image.getHeight(); y++) {
				for(int x = 0; x < image.getWidth(); x++) {
					Color cc = new Color(image.getRGB(x,y));
					int red = cc.getRed();
					int green = cc.getGreen();
					int blue = cc.getBlue();
					
					int onlyRed = new Color(red, 0, 0).getRGB();
					int onlyGreen = new Color(0, green, 0).getRGB();
					int onlyBlue = new Color(0, 0, blue).getRGB();
					
					switch(filter) {
					case 0:
						image.setRGB(x, y, onlyRed);
						break;
					case 1:
						image.setRGB(x, y, onlyGreen);
						break;
					case 2: 
						image.setRGB(x, y, onlyBlue);
						break;
						
						
					}

				}
			}
			g2d.drawImage(image, 0, 0, null);
			repaint();
		}

		
		public void GrayScale() {
			BufferedImage image = (BufferedImage) this.getImg();
			
			ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
			op.filter(image, image);
			
			g2d.drawImage(image, 0, 0, null);
			repaint();
		}
		
		private JSlider h,s;
		private JSlider [] sliders = {h,s} ;
		private JSlider[] createSlider()
		{
		    h = new JSlider(0,360);
		    h.setMajorTickSpacing(180);
		    h.setPaintTicks(true);
		    h.setPaintLabels(true);
		    h.setValue(180);               
		    h.addChangeListener(this);
		    
		    s = new JSlider(0,200);
		    s.setMajorTickSpacing(100);
		    s.setPaintTicks(true);
		    s.setPaintLabels(true);
		    s.setValue(100);               
		    s.addChangeListener(this);


		  
			return sliders;

		}
			private JPanel createSliderPanel(String string, JSlider h, JSlider s) {
				JPanel panel = new JPanel();
				Font fn = new Font("Arial", 0, 15);
				JLabel hue = new JLabel("Hue");
				JLabel sat = new JLabel("Sat");
				sat.setFont(fn);
				
				hue.setFont(fn);
				hue.setForeground(Color.WHITE);
				panel.setPreferredSize(new Dimension(250,100));
				panel.setLayout(new FlowLayout());
				panel.add(hue);
				panel.add(h);
				panel.add(sat);
				panel.add(s);

				return panel;
			}
			public void HSB() {
				this.saveToStack(img);

				    sliders = createSlider();
				    JPanel sliderPanel = createSliderPanel("myMessage", h,s);
				    int dialogResponse = JOptionPane.showOptionDialog
				            (this,                 
				             sliderPanel,
				             "Hue And Saturation",
				             JOptionPane.OK_CANCEL_OPTION,
				             JOptionPane.QUESTION_MESSAGE,
				             null, null, null
				            );
				    if (JOptionPane.OK_OPTION == dialogResponse) {
						this.saveToStack(img);

				    }
				    
				    if (JOptionPane.CANCEL_OPTION == dialogResponse) {
				    	g2d.drawImage(undoStack.lastElement(), 0, 0,null);
				    	repaint();
				    }
				    if (JOptionPane.CLOSED_OPTION == dialogResponse) {
				    	g2d.drawImage(undoStack.lastElement(), 0, 0,null);
				    	repaint();
				    }
				}


			@Override
			public void stateChanged(ChangeEvent e) {

				h = (JSlider) e.getSource();
				int hsource = h.getValue();

				s = (JSlider)e.getSource();
				int ssource = s.getValue();
				
		
				BufferedImage image = (BufferedImage) this.getImg();

				
				for(int x =0; x < image.getWidth(); x++) {
					for(int y =0; y < image.getHeight(); y++) {
						if (!h.getValueIsAdjusting ())
				        {
						
						Color color = new Color(image.getRGB(x, y));
					    float[] hsb = Color.RGBtoHSB(color.getRed(),color.getGreen(),color.getBlue(),null);
					    int colorHue = Color.HSBtoRGB((float)hsource/180f, hsb[1], hsb[2]);
					    Color c = new Color(colorHue);
					    image.setRGB(x, y, colorHue);

					 
			}	
						if (!s.getValueIsAdjusting ())
				        {
							Color color = new Color(image.getRGB(x, y));
						    float[] hsb = Color.RGBtoHSB(color.getRed(),color.getGreen(),color.getBlue(),null);
						    int colorHue = Color.HSBtoRGB(hsb[0], (float)ssource/200f, hsb[2]);
						    Color c = new Color(colorHue);
						    image.setRGB(x, y, colorHue);

				        }
					
				}
					
				g2d.drawImage(image, 0, 0, null);
				repaint();
		        }
			}

		

}
