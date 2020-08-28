package teste1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import  javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import  javax.swing.JFrame;

public class Janela extends JFrame implements ActionListener{
	
	boolean start = false , anima = false;
	String nome;
	int px = 13 , py = 23 , dir ,score ; 
	Timer timer;
	
	char mp[][] = {//    4    5    6    7    8    9   10   11   12   13   14   15   16   17   18   19   20   21   22   23   24   25   26   27   28      
	        {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},//01
	        {'x', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'x', 'x', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'x'},//02
	        {'x', 'o', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'o', 'x'},//03
	        {'x', '-', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'x', 'x', '-', 'x'},//04
	        {'x', 'o', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'o', 'x'},//05
	        {'x', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'x'},//06
	        {'x', 'o', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'o', 'x'},//07
	        {'x', 'o', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'o', 'x'},//08
	        {'x', 'o', 'o', 'o', 'o', 'o', 'o', 'x', 'x', 'o', 'o', 'o', 'o', 'x', 'x', 'o', 'o', 'o', 'o', 'x', 'x', 'o', 'o', 'o', 'o', 'o', 'o', 'x'},//09
	        {'x', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'x'},//10
	        {'x', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'x'},//11
	        {'x', 'x', 'x', 'x', 'x', 'x', ' ', 'x', 'x', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'x'},//12
	        {'x', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'x'},//13
	        {'x', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'x'},//14
	        {' ', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', ' '},//15
	        {'x', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'x'},//16
	        {'x', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'x'},//17
	        {'x', 'x', 'x', 'x', 'x', 'x', ' ', 'x', 'x', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'x'},//18
	        {'x', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'x'},//19
	        {'x', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'x'},//20
	        {'x', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'x', 'x', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'x'},//21
	        {'x', 'o', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'o', 'x'},//22
	        {'x', 'o', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'o', 'x'},//23
	        {'x', '-', 'o', 'o', 'x', 'x', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'x', 'x', 'o', 'o', '-', 'x'},//24
	        {'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x'},//25
	        {'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x'},//26
	        {'x', 'o', 'o', 'o', 'o', 'o', 'o', 'x', 'x', 'o', 'o', 'o', 'o', 'x', 'x', 'o', 'o', 'o', 'o', 'x', 'x', 'o', 'o', 'o', 'o', 'o', 'o', 'x'},//27
	        {'x', 'o', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'o', 'x'},//28
	        {'x', 'o', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'o', 'x', 'x', 'o', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'o', 'x'},//29
	        {'x', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'x'},//30
	        {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}};//31
	
	ImageIcon caminhao1 = new ImageIcon(getClass() .getResource("direita.png"));
	ImageIcon caminhao2 = new ImageIcon(getClass() .getResource("esquerda.png"));
	ImageIcon caminhao3 = new ImageIcon(getClass() .getResource("cima.png"));
	ImageIcon caminhao4 = new ImageIcon(getClass() .getResource("baixo.png"));
	
	ImageIcon pilula1 = new ImageIcon(getClass() .getResource("lixo1.png"));
	ImageIcon pilula2 = new ImageIcon(getClass() .getResource("lixo2.png"));
	ImageIcon ponto = new ImageIcon(getClass() .getResource("ponto.png"));

	
	ImageIcon fundo = new ImageIcon(getClass() .getResource("tela.png"));


	JLabel pac = new JLabel(caminhao1);
	JLabel foto = new JLabel(fundo);
	JLabel texto = new JLabel("Coletor"); 
	Font fonte = new Font  ("Serif",Font.BOLD+ALLBITS ,25);
	JLabel Ponto[][] = new JLabel[31][28];
	
	
	public void jogo() {
		if (start) {
			if(mp[py][px]== 'o') {
				mp[py][px] = ' ';
				score++;
			}	else if(mp[py][px]== '-') {
					mp[py][px] = ' ';
					score += 5;
					
			}
			if(score==312) {
				
			    StringBuilder mensagem = new StringBuilder();

			    nome = JOptionPane.showInputDialog("Parabens a cidade esta limpa , Digite seu nome ");
			    mensagem.append("Obrigado por jogar  ").append(nome).append("!");
			    JOptionPane.showMessageDialog(null, mensagem);
			}
			
			
			
			for(int i =0;  i<28;++i) {
				for(int j =0;  j<31;++j) {
					switch(mp[j][i]) {
					case 'o':
						Ponto [j][i].setIcon(ponto);
						break;
					case '-':
						
						Ponto [j][i].setIcon(pilula1);
						break;
					case ' ':
						Ponto [j][i].setBounds(0, 0, 0, 0);;
						break;
						default:
							break;
					}}
				}
			
			if(!anima) {
				anima = true; 
			}
			else {
				anima = false; 
			}
			switch(dir) {
			case 1 :
				if(mp[py][px+1] != 'x') {
					px+=1;
				}
				break;
			case 2 :
				if(mp[py][px-1] != 'x') {
					px-=1;
				}
				break;
			case 3 :
				if(mp[py+1][px] != 'x') {
					py+=1;
				}
				break;
			case 4 :
				if(mp[py-1][px] != 'x') {
					py-=1;
				}
				break;
				default:
					break;
			}
			
			if(px<=0) {
				px=27;
			}else if(px>=27) {
				px=0;
			}
			
			texto.setText(Integer.toString(score));
			animaPac(dir);
			pac.setBounds(px *27 +10,py*20-3, 45, 43);
		}
	}
	
	public void animaPac(int modo) {
		if(anima) {
			switch(modo) {
			case 1:
				pac.setIcon(caminhao1);
				break;
			case 2:
				pac.setIcon(caminhao2);
				break;
			case 3:
				pac.setIcon(caminhao3);
				break;
			case 4:
				pac.setIcon(caminhao4);
				break;
				default:
					break;
		}}
		
	}
	
	public void display() {
		setTitle("Coletou"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,690);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		setVisible(true);
		pac.setBounds(365, 458, 45, 43);
		texto.setBounds(860, 200, 130, 30);
		texto.setFont(fonte);
		texto.setForeground(Color.red);
		add(texto);
		add(pac);
		for(int i =0;  i<28;++i) {
		for(int j =0;  j<31;++j) {
			switch(mp[j][i]) {
			
			case 'o': 
				Ponto[j][i] = new JLabel(ponto);
				Ponto[j][i].setBounds(i * 27 + 22  , j * 20+12,15,15);
				add(Ponto[j][i]);
				break;
				
			case '-': 
				Ponto[j][i] = new JLabel(pilula1);
				Ponto[j][i].setBounds(i * 27 + 18  , j * 20+8,20,20);
				add(Ponto[j][i]);
				break;
				
			case ' ': 
				Ponto[j][i] = new JLabel(pilula1);
				Ponto[j][i].setBounds(0,0,0,0);
				add(Ponto[j][i]);
				break;
			default:
				break;
			}
		}
		}
		add(foto);
		foto.setBounds(0, 0, 1000, 606);
		timer = new Timer(150 , this);
		timer.start();
	}
	public void mover() {			
			addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e ) {}

			@Override
			public void keyPressed(KeyEvent e) {
				start = true ;
				
					if(e.getKeyCode() == 39 && mp[py][px+1] != 'x' ) {
					dir = 1;	
					}
					if(e.getKeyCode() == 37 && mp[py][px-1] != 'x' ) {
						dir = 2;	
						}
					if(e.getKeyCode() == 40 && mp[py+1][px] != 'x' ) {
						dir = 3;	
						}
					if(e.getKeyCode() == 38 && mp[py-1][px] != 'x' ) {
						dir = 4;	
						}			
		}
			
			@Override
			public void keyReleased(KeyEvent e) {

			}
			});	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		jogo();
	}
}