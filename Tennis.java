package Tennis;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Tennis extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TennisCourt gameCourt = new TennisCourt();
	private JLabel Score_p1 = new JLabel("0", JLabel.CENTER);
	private JLabel Score_p2 = new JLabel("0", JLabel.CENTER);
	private JPanel panel = new JPanel();	
	private JButton[] b = new JButton[4];
	private String[] s = { "New Game","Paus", "Continue", "Quit" };
	
	public Tennis(){
		
		setTitle("Tennis");
		gameCourt.setPreferredSize(new Dimension(350, 250));
		gameCourt.setBackground(Color.WHITE);
		Score_p1.setFont(new Font("SansSerif", Font.BOLD, 24));
		Score_p2.setFont(new Font("SansSerif", Font.BOLD, 24));
		panel.setLayout(new FlowLayout());
		
		for(int i=0; i<b.length; i++){
			b[i] = new JButton();
			b[i].setText(s[i]);
			b[i].addActionListener(this);
			
			panel.add(b[i]);	
		}
		add(gameCourt, BorderLayout.CENTER);
		add(Score_p1, BorderLayout.WEST);
		add(Score_p2, BorderLayout.EAST);
		add(panel, BorderLayout.SOUTH);
		
		pack();
		
		gameCourt.init(Score_p1,Score_p2);
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e){ //invoked when a an action is performed
		
		gameCourt.requestFocus();
		
		if(e.getSource() == b[0]) {
			
			gameCourt.newGame();
		
		}else if (e.getSource() == b[1]) {
			
			gameCourt.stopGame();	
			
		}else if (e.getSource() == b[2]) {
			
			gameCourt.startGame();
			
		}else if(e.getSource() == b[3]){
			
			System.exit(0);
		}
	
	}

	
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		
		Tennis s = new Tennis();

	}
}

