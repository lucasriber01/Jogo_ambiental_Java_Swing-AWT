import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4303526958373197815L;
	private JLabel uPrompt, pPrompt, gPrompt, or, message, logo, credits;
	protected JTextField usernameInput;
	protected JPasswordField passwordInput;
	protected JButton enter, register, guest; // protected so that it can be accessed in MainFrame's actionlistener
	protected LoginFile data;


	public Login(){
		setPreferredSize(new Dimension(400,640));
		setBorder(BorderFactory.createLineBorder(Color.blue));
		setLayout(null);
		setBackground(Color.black);
		setFocusable(true);
		setDoubleBuffered(true);
		setUpPage();
		data = new LoginFile();
	}

	public void setUpPage(){
		ImageIcon logoIcon = new ImageIcon(this.getClass().getResource("/images/Logo.png"));
		logo = new JLabel(logoIcon);
		logo.setBounds(57, 50, 300, 150);
		add(logo);	
		
		uPrompt = new JLabel("<html><h3>Username");
		uPrompt.setForeground(Color.blue);
		uPrompt.setBounds(80, 220, 135, 30);
		add(uPrompt);
		usernameInput = new JTextField(10);
		usernameInput.setBounds(150, 220, 135, 30);
		add(usernameInput);
		
		pPrompt = new JLabel("<html><h3>Password");
		pPrompt.setForeground(Color.blue);
		pPrompt.setBounds(82, 260, 135, 30);
		add(pPrompt);
		passwordInput = new JPasswordField(10);
		passwordInput.setBounds(150, 260, 135, 30);
		add(passwordInput);

		enter = new JButton("Play");
		enter.setBounds(125, 305, 75, 25);
		add(enter);

		register = new JButton("Register");
		register.setBounds(210, 305, 75, 25);
		add(register);		

		or = new JLabel("<html><h1>Or");
		or.setForeground(Color.blue);
		or.setBounds(187, 370, 135, 20);
		add(or);

		gPrompt = new JLabel("<html><p align=\"center\"><b>Play as a Guest.</b></p><p align=\"center\"><b>Your data will not be saved.</b></p>");
		gPrompt.setForeground(Color.blue);
		gPrompt.setBounds(115, 395, 200, 100);
		add(gPrompt);

		guest = new JButton("Guest Play");
		guest.setBounds(155, 475, 100, 25);
		add(guest);

		credits = new JLabel("<html> <p align=\"center\"><b>Created by: Isaac Gluck</b></p>");
		credits.setForeground(Color.red);
		credits.setBounds(120, 500, 200, 125);
		add(credits);

		message = new JLabel("");
	}

	public void giveActionListener(ActionListener a){
		enter.addActionListener(a);
		register.addActionListener(a);
		guest.addActionListener(a);
	}

	public void registerSuccessMessage(){
		if (message.getParent() != null)
			remove(message);
		message.setText("You have registered. Login to play.");
		message.setForeground(Color.red);
		message.setBounds(70, 332, 300, 50);
		add(message);
		repaint();
	}

	public void registerErrorMessage(){
		if (message.getParent() != null)
			remove(message);
		message.setText("This username is taken, please try again.");
		message.setForeground(Color.red);
		message.setBounds(70, 332, 300, 50);
		add(message);
		repaint();
	}

	public void loginErrorMessage(){
		if (message.getParent() != null)
			remove(message);
		message.setText("It seems you don't exist, please try again.");
		message.setForeground(Color.red);
		message.setBounds(70, 332, 300, 50);
		add(message);
		repaint();
	}
}