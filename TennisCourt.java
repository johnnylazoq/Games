package Tennis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

public class TennisCourt extends JPanel implements ActionListener {
	
			private Timer tim = new Timer(100, this);
			private JLabel Score_p1, Score_p2;
			private int p1, p2;
			private int xMax, yMax;
			private int radiusBall, ballPos_x0, ballPos_y0; 
			private int xStep, yStep;
			private int v, v0 = 5;
			private int left_Racquet, right_Racquet,racquet_Length, racquet_Step;
			
			public void init(JLabel l1, JLabel l2) {
				
				Score_p1 = l1;
				Score_p2 = l2;
				xMax = getSize().width-1;
				yMax = getSize().height-1;
				radiusBall = yMax/20;
				racquet_Length = 3*radiusBall;
				racquet_Step = radiusBall;
				
				addKeyListener(kl);  //listens to keyboard
				addComponentListener(cl);  //listens to plan
				resetGame();
			}
			
			public void resetGame() {
				p1 = 0;
				p2 = 0;
				Score_p1.setText(" 0 ");
				Score_p2.setText(" 0 ");
				xStep = yStep=v=v0=5;  // Initial velocity
				ballPos_x0 = radiusBall +1;
				ballPos_y0 = yMax/2;
				left_Racquet = right_Racquet = yMax/2 - racquet_Length/2; 
				
			}
			
			public void startGame() {
				
				tim.start();
			}
			
			public void stopGame(){
				
				tim.stop();
			}
			
			public void newGame() {
				
				stopGame();
				resetGame();
				startGame();
			}
			
			public void actionPerformed(ActionEvent e){
				
				if(ballPos_x0 - radiusBall <= 0) {
					
					if(ballPos_y0 < left_Racquet || ballPos_y0 > left_Racquet + racquet_Length) {
						
						Toolkit.getDefaultToolkit().beep();
						Score_p2.setText(" " + String.valueOf(++p2) + " ");
						
						if(p2 == 10){
							v = v0;
						}
						
					}else {
						
						v++;
						xStep = v;
					}
				
				}else if (ballPos_x0 + radiusBall >= xMax) {
					
					if (ballPos_y0 < right_Racquet || ballPos_y0 > right_Racquet +racquet_Length) {
						
						Toolkit.getDefaultToolkit().beep();
						Score_p1.setText(" " + String.valueOf(++p1) + " ");
						
						if(p1 == 10){
							v = v0;
						}
					
					}else {
						
						v++;
						xStep = -v;			
					}	
				}
				
				if(ballPos_y0 - radiusBall <= 0 || ballPos_y0 + radiusBall >= yMax ){
					
					yStep = -yStep;
					
				}
				
				ballPos_x0 += xStep;
				ballPos_y0 += yStep;
				
				if(ballPos_x0 < radiusBall) {
					
					ballPos_x0 = radiusBall;
					
				}
				else if (ballPos_x0 > xMax - radiusBall) {
					ballPos_x0 = xMax -radiusBall + 1;
					
				}
				
				if (ballPos_y0 < radiusBall) {
					
					ballPos_y0 = radiusBall;
				
				}else if (ballPos_y0 > yMax - radiusBall ) {                      
					ballPos_y0 = yMax - radiusBall + 1;
				}
				
				repaint();
				
			}
			
			public void paintComponent(Graphics g) {
				
				super.paintComponent(g);
				g.setColor(Color.red);
				g.fillOval(ballPos_x0 - radiusBall, ballPos_y0 - radiusBall, 2*radiusBall, 2*radiusBall);
				g.setColor(Color.BLACK);
				g.fillRect(0, left_Racquet, 2, racquet_Length);						
				g.fillRect(xMax -1, right_Racquet, 2, racquet_Length);   				
				
			}
			
			KeyListener kl = new KeyAdapter() {
			
				public void keyPressed(KeyEvent e) {
				
					if(e.getKeyCode() == KeyEvent.VK_A) {
					
						left_Racquet = Math.max(0, left_Racquet - racquet_Step);
					}
					else if(e.getKeyCode() == KeyEvent.VK_Z) {
						left_Racquet = Math.min(yMax - racquet_Length, left_Racquet + racquet_Step);
					}
				
					if(e.getKeyCode() == KeyEvent.VK_UP) {
					
						right_Racquet = Math.max(0, right_Racquet - racquet_Step);
					}
					else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
						right_Racquet = Math.min(yMax-racquet_Length, right_Racquet + racquet_Step);
					}
				
				}
			};
			
			ComponentListener cl = new ComponentAdapter() { 
			
				public void componentResized(ComponentEvent e) {
					xMax = e.getComponent().getSize().width - 1;
					yMax = e.getComponent().getSize().height -1;
					e.getComponent().requestFocus();
					repaint();
				}
			};
		}

