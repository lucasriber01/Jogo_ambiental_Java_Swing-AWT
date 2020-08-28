import java.util.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;


public class Board extends JPanel implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6512715596441462556L;
	//  Coordinates for creating the maze
	private double xcor;
	private double ycor;
	private int rows = 29;
	private int cols = 19;

	// Player info
	protected Pacman player;
	private Image pacLives;
	private int lives;
	private boolean mouthAnimate = true;

	//Actions for the key bindings
	private Action moveRight, moveLeft, moveUp, moveDown, quitGame, pauseGame;

	// Thread info
	private Thread playerThread;
	private boolean allowPlay;
	private boolean pause;

	// Monsters
	private Monster red;
	private Monster yellow;
	private Monster pink;
	private Monster blue;

	// User info
	protected boolean guest = true;
	protected int highscore = 0;
	protected String username;
	protected int levelNum;

	// Miscellaneous
	protected JButton mainMenu; // protected so that it can be accessed in MainFrame's actionlistener
	private boolean winSequence;
	private int winCounter;
	private boolean showLives = false;
	private boolean levelUp = false;
	private Image winScreen;

	private final String[] levels = {"level1.txt", "level2.txt"};
	private int[][] currentLevel;
	

	class MoveRight extends AbstractAction{
		/**
		 * 
		 */
		private static final long serialVersionUID = -7307727927346233533L;
		public MoveRight(){
			putValue(NAME, "moveRight");
			putValue(SHORT_DESCRIPTION, "Move right.");
			putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_D));
		}
		public void actionPerformed(ActionEvent e){
			player.moveRight();
		}
	}
	class MoveLeft extends AbstractAction{
		/**
		 * 
		 */
		private static final long serialVersionUID = -6577605768388189590L;
		public MoveLeft(){
			putValue(NAME, "moveLeft");
			putValue(SHORT_DESCRIPTION, "Move left.");
			putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_A));
		}
		public void actionPerformed(ActionEvent e){
			player.moveLeft();
		}
	}
	class MoveUp extends AbstractAction{
		/**
		 * 
		 */
		private static final long serialVersionUID = 98945696041142724L;
		public MoveUp(){
			putValue(NAME, "moveUp");
			putValue(SHORT_DESCRIPTION, "Move up.");
			putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_W));
		}
		public void actionPerformed(ActionEvent e){
			player.moveUp();
		}
	}
	class MoveDown extends AbstractAction{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1494733363758292840L;
		public MoveDown(){
			putValue(NAME, "moveDown");
			putValue(SHORT_DESCRIPTION, "Move down.");
			putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_S));
		}
		public void actionPerformed(ActionEvent e){
			player.moveDown();
		}
	}
	class PauseGame extends AbstractAction{
		/**
		 * 
		 */
		private static final long serialVersionUID = 7465314020514651973L;
		public PauseGame(){
			putValue(NAME, "pause");
			putValue(SHORT_DESCRIPTION, "Pause the game.");
			putValue(MNEMONIC_KEY, new Integer('p'));
		}
		public void actionPerformed(ActionEvent e){
			if (lives != 0)
				pause = !pause;
		}
	}
	class QuitGame extends AbstractAction{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1605500690089626784L;
		public QuitGame(){
			putValue(NAME, "quit");
			putValue(SHORT_DESCRIPTION, "Quit the game.");
			putValue(MNEMONIC_KEY, new Integer('q'));
		}
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}


	public Board(){
		// set up the board panel
		setPreferredSize(new Dimension(400,640));
		setBorder(BorderFactory.createLineBorder(Color.blue));
		setLayout(null);
		setBackground(Color.black);

		xcor = this.getLocation().getX();
		ycor = this.getLocation().getY();
		player = new Pacman();
		red = new Monster(1);
		yellow = new Monster(2);
		pink = new Monster(3);
		blue = new Monster(4);

		// The different Actions
		moveRight = new MoveRight();
		moveLeft = new MoveLeft();
		moveUp = new MoveUp();
		moveDown = new MoveDown();
		pauseGame = new PauseGame();
		quitGame = new QuitGame();

		mainMenu = new JButton("Main Menu");
		mainMenu.setBounds(155, 375, 100, 25);
		winScreen = new ImageIcon(this.getClass().getResource("/images/win.png")).getImage();

		setUpBoard(1,3);

		// Key Bindings
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "moveRight");
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "moveRight");
		getActionMap().put("moveRight", moveRight);
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "moveLeft");
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "moveLeft");
		getActionMap().put("moveLeft", moveLeft);
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "moveUp");
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "moveUp");
		getActionMap().put("moveUp", moveUp);
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "moveDown");
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "moveDown");
		getActionMap().put("moveDown", moveDown);
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "pause");
		getActionMap().put("pause", pauseGame);
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0), "quit");
		getActionMap().put("quit", quitGame);

		playerThread = new Thread(this);
    	playerThread.start();
	}

	public void setUpBoard(int level, int lives){
		this.lives = lives;
		importLevel(levels[level-1]);
		player.setScore(0);
		allowPlay = true;
		pause = true;
		winSequence = false;
		winCounter = currentLevel.length-1;
	}

	public void importLevel(String levelFile){
		currentLevel = new int[29][19];
		try{
			Scanner scanner = new Scanner(this.getClass().getResourceAsStream("levels/"+levelFile));
            scanner.useDelimiter("\n");
            String[] temp = new String[19];
            for(int j=0;j<29;j++) {
            	temp = scanner.next().split(",");
            	int[] tempInt = new int[19];
            	for (int i=0;i<19; i++) {
            		tempInt[i] = Integer.parseInt(temp[i]);
            	}
            	currentLevel[j] = tempInt;
            }
        	scanner.close();
        }
        catch(Exception e){}
	}

	private void drawMaze(Graphics g){
		Graphics2D surface = (Graphics2D) g;
		int tmpx = (int) xcor+20;
		int tmpy = (int) ycor+20;
		Color dot = new Color(223,236,42);

		surface.setColor(Color.blue);
		surface.drawRect(0,0,400,600);

		//  loop over the currentLevel and draw it on the board: 1=yellow dot  2=blue wall
		for (int i=0; i<rows;i++){
			tmpx = (int) xcor+20;
			for (int k=0; k<cols;k++){
				// yellow dots
				if (lives>0 && (currentLevel[i][k]==1 || currentLevel[i][k]==11)){
					surface.setColor(dot);
					surface.fillOval(tmpx-2, tmpy-2, 4, 4);
				}
				// power up yellow dots
				if (lives>0 && currentLevel[i][k]==21){
					surface.setColor(dot);
					surface.fillOval(tmpx-4, tmpy-4, 9, 9);
				}
				// horizontal lines
				if (lives>0 && k < cols-1){
					if (currentLevel[i][k] == 2 && currentLevel[i][k+1]==2){
						surface.setColor(Color.blue);
						surface.drawLine(tmpx, tmpy, tmpx+20, tmpy);
					}
				}
				// vertical lines
				if (lives>0 && i < rows-1){
					if (currentLevel[i][k] == 2 && currentLevel[i+1][k]==2){
						surface.setColor(Color.blue);
						surface.drawLine(tmpx, tmpy, tmpx, tmpy+20);
					}
				}
				tmpx+=20;
			}
			tmpy+=20;
		}
	}

	public void drawInfo(Graphics g){
		Graphics2D surface = (Graphics2D) g;

		// Show lives with pacman pictures
		if (lives==3)
			pacLives = new ImageIcon(this.getClass().getResource("/images/threeLives.png")).getImage();
		if (lives==2)
			pacLives = new ImageIcon(this.getClass().getResource("/images/twoLives.png")).getImage();
		if (lives==1)
			pacLives = new ImageIcon(this.getClass().getResource("/images/oneLife.png")).getImage();
		
		String showScore = "Score: "+ player.score;

		surface.setColor(Color.blue);
		surface.drawRect(0,600,400,40);
		if (lives != 0)
			surface.drawImage(pacLives,20,612, this);
		surface.setColor(Color.white);
		surface.drawString(showScore, 320, 625);

		if (!guest){
			if (player.score > highscore){
				highscore = player.score;
			}
			String showHighScore = "High Score: " + highscore;
			surface.setColor(Color.white);
			surface.drawString(showHighScore, 150, 625);
		}
	}

	public void paintComponent(Graphics g){
		add(mainMenu);
		if (mainMenu.getParent() != null && (lives > 0 && !winSequence)) // Hides the main menu button if lives > 0
				remove(mainMenu);

		super.paintComponent(g);
		drawMaze(g);
		drawInfo(g);
		Graphics2D surface = (Graphics2D) g;
		
		if (lives>0 && !winSequence){
			// show the players
			surface.drawImage(player.getImage(), player.getX(), player.getY(), this);
			if (!red.dead)
				surface.drawImage(red.getImage(), red.getX(), red.getY(), this);
			if (!yellow.dead)
				surface.drawImage(yellow.getImage(), yellow.getX(), yellow.getY(), this);
			if (!pink.dead)
				surface.drawImage(pink.getImage(), pink.getX(), pink.getY(), this);
			if (!blue.dead)
				surface.drawImage(blue.getImage(), blue.getX(), blue.getY(), this);
		}

		// If you lose a life
		if (showLives && lives != 0){
			String lifeMessage;
			if (lives != 1)
				lifeMessage = "You have " + lives + " lives left";
			else
				lifeMessage = "You have " + lives + " life left";
			g.setFont(new Font("sans-serif", Font.BOLD, 30));
			g.setColor(Color.white);
			surface.drawString(lifeMessage, 25, 290);
		}

		// If you have no lives left
		if (lives <= 0){
			g.setFont(new Font("sans-serif", Font.BOLD, 30));
			g.setColor(Color.white);
			surface.drawString("You died.", 137, 280);
			surface.drawString("Better luck next time!", 40, 320);
			surface.drawString("Score: " + player.score, 145, 360);

		}

		// If the game is paused
		if (pause){
			g.setFont(new Font("sans-serif", Font.BOLD, 30));
			g.setColor(Color.white);
			surface.drawString("Press P to Play or Pause.", 17, 200);
			surface.drawString("Press Q to quit the game.", 6, 240);
			surface.drawString("Score: " + player.score, 145, 280);
		}

		// Leveling up
		if (levelUp){
			g.setFont(new Font("sans-serif", Font.BOLD, 30));
			g.setColor(Color.white);
			surface.drawString("Level" + levelNum, 148, 280);
			surface.drawString("Score: " + player.score, 145, 320);
		}

		// You win!
		if (winSequence){
			surface.drawImage(winScreen, -25, 110, this);
			g.setFont(new Font("sans-serif", Font.BOLD, 40));
			g.setColor(Color.blue);
            surface.drawString("You won!", 95, 258);
            surface.drawString("Praise the Lord!", 40, 308);
            surface.drawString("Score: " + player.score, 105, 358);
		}

		Toolkit.getDefaultToolkit().sync();
	}

	// -----Consolidation-----
	public void playerMove(int[][] currentLevel, Pacman player, boolean mouthAnimate){
		player.detectEdge();
		player.detectWall(currentLevel);
		player.eatFruit(currentLevel);
		player.move(this.mouthAnimate);
		player.move2(this.mouthAnimate);
		if(player.getPower()){
			player.superCounter++;
			if(player.superCounter>40){
				player.noPower();
				if (red.dead)
					red.monsterReset(1);
				if (yellow.dead)
					yellow.monsterReset(2);
				if (pink.dead)
					pink.monsterReset(3);
				if (blue.dead)
					blue.monsterReset(4);
			}
		}
	}

	public void monsterMove(int[][] currentLevel, Pacman player){
		red.moveSearch(currentLevel, player);
		yellow.moveSearch(currentLevel, player);
		pink.moveSearch(currentLevel, player);
		blue.moveSearch(currentLevel, player);
	}

	public void moveAll(){
		this.mouthAnimate =! this.mouthAnimate;
		this.playerMove(currentLevel, player, mouthAnimate);
		this.monsterMove(currentLevel, player);
	}

	public void resetAll(){
		player.pacReset();
		red.monsterReset(1);
		yellow.monsterReset(2);
		pink.monsterReset(3);
		blue.monsterReset(4);
	}
	// -------------------------
	
	public boolean checkDeath(Pacman player){
		if (red.kill(player) || yellow.kill(player) || pink.kill(player) || blue.kill(player))
			return true;
		return false;
	}

	public boolean checkWin(){
		for (int i=0;i<currentLevel.length;i++){
			for (int k=0;k<currentLevel[0].length;k++){
				if (currentLevel[i][k] == 1 || currentLevel[i][k] == 11 || currentLevel[i][k] == 21)
					return false;
			}
		}
		return true;
	}

	public void nextLevel(){
		levelNum += 1;
		importLevel(levels[levelNum-1]);
		resetAll();
	}

	public void giveActionListener(ActionListener a){
		mainMenu.addActionListener(a);
	}

	public void delay(int time){
		try{
           	Thread.sleep(time);
       	}catch (InterruptedException e){
       	   	System.out.println("Interrupted");
        }
	}

	public void run(){
		while (allowPlay){
			if (!pause){ // Game paused
				if (checkDeath(player)){
					lives--;
					resetAll();				
					showLives = true;
					repaint();
					delay(3000);
           			showLives = false;
				}
				else if (checkWin()) {
					if(levelNum != levels.length){
						nextLevel();
						levelUp = true;
						repaint();
						delay(3000);
						levelUp = false;
					}
					else{
						if (winCounter>=0){
                            for (int i=0;i<currentLevel[0].length;i++)
                                    currentLevel[winCounter][i] = 0;
                            winCounter--;
                        }
						else
							winSequence = true;

					}
				}
				else
					moveAll();
			}
			repaint();
			delay(150);
		}
	}
}