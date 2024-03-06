package ex2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class BallMove extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int Width = 400;
	private static final int Height = 600;
	private static final int sizeBall = 50;

	private int ballX = 40;
	private int ballY = 40;

	private int ballXSpeed = 3;
	private int ballYSpeed = 3;

	public BallMove() {
		setTitle("Ball Move");
		setSize(Width, Height);
		setLocationRelativeTo(null);
		setResizable(false);

		Timer timer = new Timer(15, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				moveBall();
				repaint();
			}
		});
		timer.start();

	}

	private void moveBall() {

		if (ballX + ballXSpeed < 0 || ballX + ballXSpeed > Width - sizeBall)
			ballXSpeed = -ballXSpeed;

		if (ballY + ballYSpeed < 0 || ballY + ballYSpeed > Height - sizeBall)
			ballYSpeed = -ballYSpeed;
		ballX += ballXSpeed;
		ballY += ballYSpeed;

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.blue);
		g.fillOval(ballX, ballY, sizeBall, sizeBall);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BallMove bm = new BallMove();
					bm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
