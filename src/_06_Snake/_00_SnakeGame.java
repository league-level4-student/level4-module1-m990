package _06_Snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

// Go through the methods and complete the steps in this class
// and the Snake class

public class _00_SnakeGame implements ActionListener, KeyListener {
	public static final Color BORDER_COLOR = Color.WHITE;
	public static final Color BACKGROUND_COLOR = Color.BLACK;
	public static final Color FOOD_COLOR = Color.RED;
	public static final int WIDTH = 15;
	public static final int HEIGHT = 12;
	public static final int WINDOW_SCALE = 50;
	public static final int WINDOW_WIDTH = WINDOW_SCALE * WIDTH;
	public static final int WINDOW_HEIGHT = WINDOW_SCALE * HEIGHT;

	private JFrame window;
	private JPanel panel;

	private Snake snake;

	private Timer timer;

	private Location foodLocation;

	public _00_SnakeGame() {
		snake = new Snake(new Location(WIDTH / 2, HEIGHT / 2));

		window = new JFrame("Snake");
		panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;

				g2.setColor(BACKGROUND_COLOR);
				g2.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

				g2.setColor(FOOD_COLOR);
				g2.drawOval(foodLocation.x, foodLocation.y, Snake.BODY_SIZE,
						Snake.BODY_SIZE);
				snake.draw(g);
			}
		};

		panel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		window.add(panel);

		timer = new Timer(0, this);

		window.pack();
		window.addKeyListener(this);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		setFoodLocation();

		startGame();
	}

	public void startGame() {
		//1. Save the instructions for the game in the following string variable.
		String instructions = "Try to get the dot thing and make your snake longer. You can't hit yourself.";
		
		String[] options = new String[] { "Expert", "Moderate", "Beginner" };
		int input = JOptionPane.showOptionDialog(null, instructions, "Snake", 0, -1, null, options, 0);

		String choice = options[input];
		
		//2. Use a switch statement to determine which difficulty was chosen.
		//   Use timer.setDelay(delay) with different numbers to change the speed
		//   of the game. The smaller the number, the faster it goes.
		switch (choice) {
		case "Expert":
			timer.setDelay(50);
			break;
		case "Moderate":
			timer.setDelay(75);
			break;
		case "Beginner":
			timer.setDelay(100);
		}
		//3. start the timer
		timer.start();
	}

	public static void main(String[] args) {
		new _00_SnakeGame();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		//1. Use a switch statement on e.getKeyCode()
		//   to determine which key was pressed.
		
		// if an arrow key is pressed, set the snake's 
		// direction accordingly
		
		// if the space key is pressed, call the snake's feed method
		switch(e.getKeyCode()) {
		case KeyEvent.VK_DOWN:

			snake.setDirection(Direction.DOWN);
			break;
		case KeyEvent.VK_UP:

			snake.setDirection(Direction.UP);
			break;
		case KeyEvent.VK_LEFT:

			snake.setDirection(Direction.LEFT);
			break;
		case KeyEvent.VK_RIGHT:

			snake.setDirection(Direction.RIGHT);
			break;
		case KeyEvent.VK_SPACE:
			snake.feed();
		default:
			System.out.println("invalid");
		}
		
	}

	private void setFoodLocation() {
		//1. Create a new Location object that is set to a random location
		Location newLocation = new Location(new Random().nextInt(600), new Random().nextInt(600));
		System.out.println(newLocation.x);
		System.out.println(newLocation.y);
		//2. set the foodLocation variable equal to the Location object you just created.
		foodLocation = newLocation;
		//   use the snake's isLocationOnSnake method to make sure you don't put the food on the snake
		
	}

	private void gameOver() {
		System.out.println("Game Over");
		//1. stop the timer
		timer.stop();
		//2. tell the user their snake is dead
		JOptionPane.showMessageDialog(null, "Your snake is dead");
		//3. ask them if they want to play again.
		String playAgian = JOptionPane.showInputDialog("Do you want to play again?");
		//4. if they want to play again
		if (playAgian.equalsIgnoreCase("yes")) {
			snake.reset(new Location(10, 10));
			setFoodLocation();
			timer.start();
		}
		else {
			System.exit(0);
		}
		//   reset the snake and the food and start the timer
		//   else, exit the game
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//1. update the snake
		snake.update();
		//2. if the snake is colliding with its own body 
		//   or if the snake is out of bounds, call gameOver
		if (snake.isHeadCollidingWithBody() || snake.isOutOfBounds()) {
			gameOver();
		}
		//3. if the location of the head is equal to the location of the food,
		// 	 feed the snake and set the food location
		System.out.println("Snake x" + snake.getHeadLocation().x);
		System.out.println("SnakeY:" + snake.getHeadLocation().y);
		System.out.println("Food location x: " + foodLocation.x);
		System.out.println("Food location y: " + foodLocation.y);
		System.out.println("\n");
		if (foodLocation.x + 50 > snake.getHeadLocation().x * 50 &&
				foodLocation.x - 50 < snake.getHeadLocation().x * 50 &&
				foodLocation.y + 50 > snake.getHeadLocation().y * 50 &&
				foodLocation.y - 50 < snake.getHeadLocation().y * 50) {
			System.out.println("in x box thing");
			setFoodLocation();
			snake.feed();
		}
		//4. call panel.repaint();
		panel.repaint();
	}
}
