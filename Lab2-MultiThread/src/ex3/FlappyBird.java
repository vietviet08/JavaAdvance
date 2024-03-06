package ex3;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class FlappyBird extends JFrame implements ActionListener, KeyListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	private int birdY;
	private int score = 0;

	private boolean gameOver = false;

	private Timer timer;

	private Rectangle bird;
	private ArrayList<Rectangle> columns;

	private Random random;

	public FlappyBird() {

		bird = new Rectangle(WIDTH / 2 - 100, HEIGHT / 2 - 100, 20, 20);
		columns = new ArrayList<Rectangle>();
		random = new Random();

		addKeyListener(this);
		addMouseListener(this);
		setFocusable(true);

		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);

		timer = new Timer(20, this);
		timer.start();
	}

	private void addColumn(boolean start) {
		int space = 120;
		int width = 70;
		int height = 50 + random.nextInt(300);

		if (start) {
			columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height, width, height));
			columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
		} else {
			columns.add(new Rectangle(columns.get(columns.size() - 1).x + 270, HEIGHT - height, width, height));
			columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		g.setColor(Color.gray);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(Color.BLUE);
		g.fillRect(bird.x, bird.y, bird.width, bird.height);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 35));
		g.drawString(score + "", 40, 60);

		for (Rectangle column : columns) {
			g.setColor(Color.green);
			g.fillRect(column.x, column.y, column.width, column.height);
		}

		if (gameOver) {
			g.setColor(Color.white);
			g.drawString("GAME OVER", WIDTH / 2 - 100, HEIGHT / 2);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (birdY < 15) {
			birdY += 2;
		}

		for (Rectangle column : columns) {
			column.x -= 7;
		}

		for (int i = 0; i < columns.size(); i++) {
			Rectangle column = columns.get(i);
			if (column.x + column.width < 0) {
				columns.remove(i);
				if (column.y == 0)
					addColumn(false);
			}
		}

		bird.y += birdY;

		for (Rectangle column : columns) {

			if (column.y == 0 && bird.x + bird.width / 2 > column.x + column.width / 2 - 10
					&& bird.x + bird.width / 2 < column.x + column.width / 2 + 10) {

				if (!gameOver)
					score++;
			}
			if (column.intersects(bird)) {
				gameOver = true;
				if (bird.x <= column.x) {
					bird.x = column.x - bird.width;

				} else {
					if (column.y != 0) {
						bird.y = column.y - bird.height;
					} else if (bird.y < column.height) {
						bird.y = column.height;
					}
				}
			}

		}

		if (bird.y > HEIGHT)
			gameOver = true;

		repaint();

	}

	private void jump() {

		if (gameOver) {
			bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
			columns.clear();
			birdY = 0;
			score = 0;

			addColumn(true);
			addColumn(true);
			addColumn(true);
			addColumn(true);

			gameOver = false;
		}

		if (!gameOver) {
			if (birdY > 0) {
				birdY = 0;
			}

			birdY -= 13;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			jump();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		jump();
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlappyBird fb = new FlappyBird();
					fb.setTitle("Flappy Bird");
					fb.setSize(WIDTH, HEIGHT);
					fb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					fb.setResizable(false);
					fb.setLocationRelativeTo(null);
					fb.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
