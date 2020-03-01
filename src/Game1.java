//Preet Patel
//January 18, 2019
//To build a successful game.In this case it is Connect 4
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;

public class Game1 extends Applet implements ActionListener
{
	//All the Global variables are listed
	JLabel turnpic;
	int turn =1;
	String piece1= "g1.png";
	String piece2= "g2.png";
	String piece0= "g0.png";
	JLabel star, flowers, BFlower, helicopter, leaf, shell, suit;
	JButton question, instructions;

	Panel p_card;  //to hold all of the screens
	Panel card1, card2, card3, card4, card5, card6,	card7; //the  screens
	CardLayout cdLayout = new CardLayout ();

	//grid
	int row = 6;
	int col = 7;
	JButton a[] = new JButton [row * col];	
	int b[] [] = {{0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 0, 0}};

	//Comments for the save and open
	String d [] [] = {{"0", "0", "0", "0", "0", "0", "0"}, 
			{"0", "0", "0", "0", "0", "0", "0"}, 
			{"0", "0", "0", "0", "0", "0", "0"}, 
			{"0", "0", "0", "0", "0", "0", "0"}, 
			{"0", "0", "0", "0", "0", "0", "0"}, 
			{"0", "0", "0", "0", "0", "0", "0"}};

	int re[] [] = {{0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 0, 0}};

	public void save (String filename) {
		//method for save
		PrintWriter out;
		try {
			out = new PrintWriter (new FileWriter (filename));
			for(int i=0; i<row; i++) {
				for(int j = 0; j<col; j++) {
					out.println (""+b[i][j]);
				}
			}
			for(int i=0; i<row; i++) {
				for(int j = 0; j<col; j++) {
					out.println (""+d[i][j]);
					out.close ();
				}
			}
		}
		catch (IOException e) {
			System.out.println ("Error opening file " + e);
		}
	}

	public void open(String filename){
		//method for open
		BufferedReader in;
		try {
			in = new BufferedReader (new FileReader (filename));
			String input = in.readLine ();
			for(int i=0; i<row; i++) {
				for(int j = 0; j<col; j++) {
					b[i][j]= Integer.parseInt (input);
					input = in.readLine ();
				}
			}
			for(int i=0; i<row; i++) {
				for(int j = 0; j<col; j++) {
					d[i][j]= input;
					input = in.readLine ();
				}
			}
			in.close ();
		}
		catch (IOException e) {
			System.out.println ("Error opening file " + e);
		}
		redraw();
	}

	public void initMenu ()
	{
		// This holds save and open options in the file
		JMenuBar menuBar = new JMenuBar ();
		JMenu menu;
		JMenuItem menuItem;

		menu = new JMenu ("File");
		menuBar.add (menu);

		menuItem = new JMenuItem ("Save");
		menuItem.addActionListener (this);
		menuItem.setActionCommand ("save1");
		menu.add (menuItem);

		menuItem = new JMenuItem ("Open");
		menuItem.addActionListener (this);
		menuItem.setActionCommand ("open1");
		menu.add (menuItem);

		menuItem = new JMenuItem ("Exit");
		menuItem.addActionListener (this);
		menuItem.setActionCommand ("exit");
		menu.add (menuItem);
		add ("North", menuBar);


	}

	AudioClip soundFile;
	public void init ()
	{
		//the sound

		p_card = new Panel ();
		p_card.setLayout (cdLayout);
		screen1 ();
		screen2 ();
		screen3 ();
		screen4 ();
		resize (500, 730);
		setLayout (new BorderLayout ());
		initMenu ();
		add ("Center", p_card);

	}

	public void screen1 ()
	{ //screen 1 is set up.
		card1 = new Panel ();
		card1.setBackground(new Color(0, 173, 236));

		JButton splash = new JButton (createImageIcon("splash1.png"));
		splash.setActionCommand ("splash");
		splash.setBorderPainted(false);
		splash.addActionListener (this);
		splash.setPreferredSize (new Dimension (500, 640));

		JLabel name = new JLabel ("By: Preet Patel");
		name.setFont (new Font ("Ariel", Font.BOLD, 30));
		name.setForeground (Color.red);

		card1.add (splash);
		card1.add(name);
		p_card.add ("1", card1);

	}
	public void screen2 ()
	{ //screen 2 is set up.
		card2 = new Panel ();
		card2.setBackground(new Color(51,204,255));
		JLabel title = new JLabel ("Click on a theme");
		title.setFont (new Font ("Algerian", Font.BOLD, 40));
		title.setForeground (Color.red);
		//Shows the 4 themes
		JButton bg1 = new JButton (createImageIcon("MarioBackground.png"));
		bg1.setPreferredSize (new Dimension (261, 145));
		bg1.setBorderPainted(false);
		bg1.setActionCommand ("bg1");
		bg1.addActionListener (this);

		JButton bg2 = new JButton (createImageIcon("SportsBackground.png"));
		bg2.setActionCommand ("bg2");
		bg2.addActionListener (this);
		bg2.setBorderPainted(false);
		bg2.setPreferredSize (new Dimension (259, 145));

		JButton bg3 = new JButton (createImageIcon("AnimalsBackground.png"));
		bg3.setActionCommand ("bg3");
		bg3.addActionListener (this);
		bg3.setBorderPainted(false);
		bg3.setPreferredSize (new Dimension (261, 145));

		JButton bg4 = new JButton (createImageIcon("WeatherBackground.png"));
		bg4.setActionCommand ("bg4");
		bg4.addActionListener (this);
		bg4.setBorderPainted(false);
		bg4.setPreferredSize (new Dimension (259, 145));

		card2.add (title);
		card2.add (bg1);
		card2.add (bg2);
		card2.add (bg3);
		card2.add (bg4);
		p_card.add ("2", card2);
	}

	public void screen3 ()
	{ //screen 3 is set up.
		card3 = new Panel ();
		card3.setBackground(new Color(51,204,255));
		turnpic= new JLabel (createImageIcon (piece1));
		Panel d = new Panel (new GridLayout (row, col));

		int move = 0;
		for (int i = 0 ; i < row ; i++)
		{	for (int j = 0 ; j < col ; j++)
		{ //take out when you've got pictures
			a [move] = new JButton ("");
			//add in when you have pictures
			//the grid is being made
			a [move] = new JButton (createImageIcon ("g0.png"));
			a [move].setPreferredSize (new Dimension (60, 60));
			a [move].addActionListener (this);
			a [move].setActionCommand ("" + move);
			a [move].setBorderPainted(false);
			d.add (a [move]);
			move++;
		}			}
		//the pictures are being created
		star = new JLabel (createImageIcon("star.png"));
		star.setPreferredSize (new Dimension (53, 53));
		flowers = new JLabel (createImageIcon("flowers.png"));
		flowers.setPreferredSize (new Dimension (53, 53));
		BFlower = new JLabel (createImageIcon("BlueFlowers.png"));
		BFlower.setPreferredSize (new Dimension (53, 53));
		helicopter = new JLabel (createImageIcon("helicopter.jpg"));
		helicopter.setPreferredSize (new Dimension (53, 53));
		leaf = new JLabel (createImageIcon("leaf.jpg"));
		leaf.setPreferredSize (new Dimension (53, 53));
		shell = new JLabel (createImageIcon("shell.png"));
		shell.setPreferredSize (new Dimension (53, 53));
		suit = new JLabel (createImageIcon("suit.jpg"));
		suit.setPreferredSize (new Dimension (53, 53));

		Panel p = new Panel ();

		JLabel title = new JLabel ("Connect 4");
		title.setFont (new Font ("Algerian", Font.BOLD, 40));
		title.setForeground(new Color(255,0,0));
		JLabel inst = new JLabel ("Click on the grid to drop a checker.");
		inst.setFont (new Font ("Arial", Font.BOLD, 20));
		inst.setForeground(new Color(255,255,255));

		Panel p2 = new Panel ();

		JLabel turn = new JLabel ("Turn: ");
		turn.setFont (new Font ("Arial", Font.BOLD, 20));
		turn.setForeground(new Color(255,255,255));

		JButton back = new JButton ("Theme Selection");
		back.setActionCommand ("return");
		back.addActionListener (this);
		back.setBorderPainted(false);
		back.setFont (new Font ("Arial", Font.PLAIN, 14));

		JButton reset = new JButton ("Reset");
		reset.setActionCommand ("reset");
		reset.addActionListener (this);
		reset.setBorderPainted(false);
		reset.setFont (new Font ("Arial", Font.PLAIN, 14));

		JButton off = new JButton ("Sound Off");
		off.setActionCommand ("off");
		off.addActionListener (this);
		off.setBorderPainted(false);
		off.setFont (new Font ("Arial", Font.PLAIN, 14));

		JButton save = new JButton ("Save");
		save.setActionCommand ("save1");
		save.addActionListener (this);
		save.setBorderPainted(false);
		save.setFont (new Font ("Arial", Font.PLAIN, 14));

		JButton open = new JButton ("Open");
		open.setActionCommand ("open1");
		open.addActionListener (this);
		open.setBorderPainted(false);
		open.setFont (new Font ("Arial", Font.PLAIN, 14));

		question = new JButton (createImageIcon("QuestionCoin3.png"));
		question.addActionListener (this);
		question.setPreferredSize (new Dimension (75, 75));
		question.setActionCommand ("enter");
		question.setBorderPainted(false);

		//the layout of the screen is being created
		Panel board = new Panel ( new BorderLayout ());

		board.add("Center", d); 
		board.add("North", p); 
		board.add("South", p2);
		card3.add (board);
		card3.add (title);
		card3.add (inst);
		card3.add(d);

		Panel colPick=new Panel();

		colPick.add (star);
		colPick.add (flowers);
		colPick.add (BFlower);
		colPick.add (helicopter);
		colPick.add (leaf);
		colPick.add (shell);
		colPick.add (suit);

		card3.add(colPick);
		Panel turnName=new Panel();
		turnName.add (turn);
		turnName.add (turnpic);

		card3.add(turnName);

		Panel buttons = new Panel();
		buttons.add (back);
		buttons.add (reset);
		buttons.add (off);	
		buttons.add(question);
		card3.add(buttons);
		p_card.add ("3", card3);
	}
	public void screen4 ()
	{ //screen 4 is set up.
		card4 = new Panel ();
		card4.setBackground (Color.cyan);
		//this is the instruction screen
		instructions = new JButton (createImageIcon("instructions.png"));
		instructions.setActionCommand ("instructions");
		instructions.setBorderPainted(false);
		instructions.addActionListener (this);
		instructions.setPreferredSize (new Dimension (500, 700));

		card4.add (instructions);
		p_card.add ("4", card4);
	}
	protected static ImageIcon createImageIcon (String path)
	{ //change the red to your class name
		java.net.URL imgURL = Game1.class.getResource (path);
		if (imgURL != null)
		{
			return new ImageIcon (imgURL);
		}
		else
		{
			System.err.println ("Couldn't find file: " + path);
			return null;
		}
	}

	public void redraw ()
	//this is the redraw
	{
		int move = 0;
		for (int i = 0 ; i < row ; i++)
		{
			for (int j = 0 ; j < col ; j++)
			{
				if(b[i][j]==1)
					a [move].setIcon (createImageIcon (piece1));
				else if(b[i][j]==2)
					a [move].setIcon (createImageIcon (piece2));
				else
					a [move].setIcon (createImageIcon (piece0));
				move++;
			}
		}
	}

	public boolean horizontalwin()
	//the horizontal win for all pieces
	{
		for (int x=0; x<row; x++)
		{
			for(int y=0; y<=3; y++)
			{
				if(b[x][y]!=0 && b[x][y]==b[x][y+1] && b[x][y]== b[x][y+2] && b [x][y]== b[x][y+3])
				{
					if (b[x][y]==1) {
						JOptionPane.showMessageDialog (null, createImageIcon (piece1), "You Win",
								JOptionPane.INFORMATION_MESSAGE);
						reset ();
					}
					else
						JOptionPane.showMessageDialog (null, createImageIcon (piece2), "You Win",
								JOptionPane.INFORMATION_MESSAGE);
					reset ();
					return true;
				}
			}
		}
		return false;	
	}

	public boolean Verticalwin()
	//the verical win for all pieces
	{
		for (int y=0; y<col; y++)
		{
			for(int x=0; x<3; x++)
			{
				if(b[x][y]!=0 && b[x][y]==b[x+1][y] && b[x][y]== b[x+2][y] && b [x][y]== b[x+3][y])
				{
					if (b[x][y]==1) {
						JOptionPane.showMessageDialog (null, createImageIcon (piece1), "You Win",
								JOptionPane.INFORMATION_MESSAGE);
						reset ();
					}
					else						
						JOptionPane.showMessageDialog (null, createImageIcon (piece2), "You Win",
								JOptionPane.INFORMATION_MESSAGE);
					reset ();
					return true;
				}
			}
		}
		return false;	
	}

	public boolean Diagonalwin()
	//the diagonal win for all pieces
	{
		for (int x=3; x<=5; x++)
		{
			for(int y=0; y<=3; y++)
			{
				if(b[x][y]!=0 && b[x][y]==b[x-1][y+1] && b[x][y]== b[x-2][y+2] && b [x][y]== b[x-3][y+3])
				{
					if (b[x][y]==1)
					{
						JOptionPane.showMessageDialog (null, createImageIcon (piece1), "You Win",
								JOptionPane.INFORMATION_MESSAGE);
						reset ();
					}
					else
						JOptionPane.showMessageDialog (null, createImageIcon (piece2), "You Win",
								JOptionPane.INFORMATION_MESSAGE);
					reset ();
					return true;
				}
			}
		}
		return false;	
	}

	public boolean Diagonalwin1()
	//the other diagonal win for all pieces
	{
		for (int x=0; x<=2; x++)
		{
			for(int y=0; y<=3; y++)
			{
				if(b[x][y]!=0 && b[x][y]==b[x+1][y+1] && b[x][y]== b[x+2][y+2] && b [x][y]== b[x+3][y+3])
				{
					if (b[x][y]==1) {
						JOptionPane.showMessageDialog (null, createImageIcon (piece1), "You Win",
								JOptionPane.INFORMATION_MESSAGE);
						reset ();
					}

					else
						JOptionPane.showMessageDialog (null, createImageIcon (piece2), "You Win",
								JOptionPane.INFORMATION_MESSAGE);
					reset ();
					return true;
				}
			}
		}
		return false;	
	}

	public void reset ()
	{ 
		//the reset button
		for (int i = 0 ; i < row ; i++)
			for (int j = 0 ; j < col ; j++)
				b [i] [j] = re [i] [j];
		redraw ();
	}

	public void actionPerformed (ActionEvent e)
	{ //moves between the screens
		if (e.getActionCommand ().equals ("s1"))
			cdLayout.show (p_card, "1");

		else if (e.getActionCommand ().equals ("splash"))
			cdLayout.show (p_card, "2");

		else if (e.getActionCommand ().equals ("bg1")) {
			cdLayout.show (p_card, "3");
			piece1="g1.png";
			piece2="g2.png";
			piece0= "g0.png";
			soundFile = getAudioClip (getDocumentBase (), "MarioSong.wav");
			soundFile.loop ();
			star.setIcon(createImageIcon("star.png"));
			flowers.setIcon(createImageIcon("flowers.png"));
			BFlower.setIcon(createImageIcon("BlueFlowers.png"));
			helicopter.setIcon(createImageIcon("helicopter.jpg"));
			leaf.setIcon(createImageIcon("leaf.jpg"));
			shell.setIcon(createImageIcon("shell.png"));
			suit.setIcon(createImageIcon("suit.jpg"));
			question.setIcon(createImageIcon("QuestionCoin3.png"));	
			instructions.setIcon(createImageIcon("instructions.png"));

			//setIcon for turn pic			
			turnpic.setIcon(createImageIcon(piece1));
			redraw();
		}
		else if (e.getActionCommand ().equals ("bg2")) {
			//this changes screen 3 and substitutes the information with this
			piece1="z1.png";
			piece2="z2.png";
			piece0= "z0.png";		
			soundFile = getAudioClip (getDocumentBase (), "Waka1.wav");
			soundFile.loop ();
			turnpic.setIcon(createImageIcon(piece1));			
			star.setIcon(createImageIcon("Baseball.png"));
			flowers.setIcon(createImageIcon("beachball.png"));
			BFlower.setIcon(createImageIcon("bowlingball.png"));
			helicopter.setIcon(createImageIcon("cricket1.png"));
			leaf.setIcon(createImageIcon("football.png"));
			shell.setIcon(createImageIcon("tennis.png"));
			suit.setIcon(createImageIcon("volleyball.png"));
			question.setIcon(createImageIcon("squestion.png"));
			instructions.setIcon(createImageIcon("sportsinstructions.png"));
			redraw();
			cdLayout.show (p_card, "3");
		}
		else if (e.getActionCommand ().equals ("bg3")) {
			//this changes screen 3 and substitutes the information with this
			piece1="a1.png";
			piece2="a2.png";
			piece0= "a0.png";
			soundFile = getAudioClip (getDocumentBase (), "soothing.wav");
			soundFile.loop ();
			turnpic.setIcon(createImageIcon(piece1));
			star.setIcon(createImageIcon("lion1.png"));
			flowers.setIcon(createImageIcon("pig.jpg"));
			BFlower.setIcon(createImageIcon("elephant.png"));
			helicopter.setIcon(createImageIcon("goat.jpg"));
			leaf.setIcon(createImageIcon("monkey.jpg"));
			shell.setIcon(createImageIcon("rabbit.jpg"));
			suit.setIcon(createImageIcon("zebra.jpg"));
			question.setIcon(createImageIcon("aquestion.png"));
			instructions.setIcon(createImageIcon("animalinstructions.png"));
			redraw();
			cdLayout.show (p_card, "3");
		}
		else if (e.getActionCommand ().equals ("bg4")) {
			//this changes screen 3 and substitutes the information with this
			piece1="p1.png";
			piece2="p2.png";
			piece0= "p0.png";	
			soundFile = getAudioClip (getDocumentBase (), "rainsound.wav");
			soundFile.loop ();
			turnpic.setIcon(createImageIcon(piece1));
			star.setIcon(createImageIcon("sunny.jpg"));
			flowers.setIcon(createImageIcon("lightning.jpg"));
			BFlower.setIcon(createImageIcon("snow.png"));
			helicopter.setIcon(createImageIcon("sunrain.jpg"));
			leaf.setIcon(createImageIcon("suncloud.jpg"));
			shell.setIcon(createImageIcon("tornado.jpg"));
			suit.setIcon(createImageIcon("thunderrain.jpg"));
			question.setIcon(createImageIcon("sun.jpg"));
			instructions.setIcon(createImageIcon("weatherbackground1.png"));
			redraw();
			cdLayout.show (p_card, "3");
		}

		else if (e.getActionCommand ().equals ("instructions"))
			cdLayout.show (p_card, "3");

		else if (e.getActionCommand ().equals ("enter"))
			cdLayout.show (p_card, "4");

		else if (e.getActionCommand ().equals ("off")) {
			//stops all the sound from playing
			soundFile = getAudioClip (getDocumentBase (), "soothing.wav");
			soundFile.stop ();
			soundFile = getAudioClip (getDocumentBase (), "Waka1.wav");
			soundFile.stop ();
			soundFile = getAudioClip (getDocumentBase (), "MarioSong.wav");
			soundFile.stop ();
			soundFile = getAudioClip (getDocumentBase (), "rainsound.wav");
			soundFile.stop ();
		}
		else if (e.getActionCommand ().equals ("return"))
		{
			//returns to the theme option
			reset ();	
			soundFile = getAudioClip (getDocumentBase (), "soothing.wav");
			soundFile.stop ();
			soundFile = getAudioClip (getDocumentBase (), "Waka1.wav");
			soundFile.stop ();
			soundFile = getAudioClip (getDocumentBase (), "MarioSong.wav");
			soundFile.stop ();
			soundFile = getAudioClip (getDocumentBase (), "rainsound.wav");
			soundFile.stop ();
			cdLayout.show (p_card, "2");
		}
		else if (e.getActionCommand ().equals ("exit"))
			System.exit(0);

		else if (e.getActionCommand ().equals("reset"))
			reset ();
		else if(e.getActionCommand().equals("save1")){
			//Saves the array
			save("filename.txt");
		} else if (e.getActionCommand().equals("open1")){
			//opens the array
			open("filename.txt");
		}
		else
		{ //code to handle the game
			int n = Integer.parseInt (e.getActionCommand ());
			int x = n / col;
			int y = n % col;
			showStatus ("turn: "+turn);

			while (x<row && b[x][y]==0)
				x++;
			x--;

			if (x>=0 && b [x][y] == 0)
			{		
				//every time a checker is placed a sound comes
				soundFile = getAudioClip (getDocumentBase (), "jumpsound.wav");
				soundFile.play ();

				b[x][y] = turn;
				redraw();
				// the wins pops up once someone wins
				horizontalwin();
				Diagonalwin();
				Diagonalwin1();
				Verticalwin();

				if(turn ==1)
				{ turn=2;
				turnpic.setIcon(createImageIcon(piece2));
				}

				else 
				{ turn=1;
				turnpic.setIcon(createImageIcon (piece1));

				}
			}			

			else 
				JOptionPane.showMessageDialog (null, "Pick an empty section on the grid", "Can't go there",
						JOptionPane.ERROR_MESSAGE);
		}
	}
}