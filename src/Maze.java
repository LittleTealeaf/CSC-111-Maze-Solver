import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Object representing a maze, it's walls,boundaries, and start/end positions marked using characters
 *
 * @author Thomas Kwashnak & Priscilla Esteves
 */

public class Maze implements TextMaze {

	private final char[][] maze;
	private final int width;
	private final int height;

	/**
	 * Creates an empty maze with dimensions of width and height
	 * @param width
	 * @param height
	 * @author Thomas Kwashnak
	 */
	public Maze(int width, int height) {
		this.width = width;
		this.height = height;
		maze = new char[height][width];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				maze[y][x] = TextMaze.EMPTY;
			}
		}
	}

	/**
	 * Loads a maze from a given file, returns null if any exceptions
	 * @param fileName
	 * @return Maze object imported from file, null if any errors
	 * @author Thomas Kwashnak & Priscilla Esteves
	 */
	public static Maze loadMaze(String fileName) {
		File file = new File(fileName);
		try {
			Scanner scanner = new Scanner(file);
			int width = scanner.nextInt(), height = scanner.nextInt();
			Maze retMaze = new Maze(width, height);
			scanner.nextLine();
			for (int i = 0; i < height; i++) {
				String line = scanner.nextLine();
				for (int j = 0; j < line.length(); j++) {
					Point point = new Point(j, height - i - 1);
					retMaze.set(point, line.charAt(j));
				}
			}
			scanner.close();
			return retMaze;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Saves a maze object to a file, printing the width and height and then maze itself
	 * @author Thomas Kwashnak
	 * @param fileName
	 * @param maze
	 */
	public static void saveMaze(String fileName, Maze maze) {
		File file = new File(fileName);
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(maze.width + " " + maze.height + "\n");
			writer.write(maze.toString());
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets point p to specified char
	 * throws PointOutOfBoundsException if point is out of bounds
	 * @param p
	 * @param c
	 * @author Priscilla Esteves
	 */
	@Override
	public void set(Point p, char c) {

		if (inBounds(p)) {
			maze[maze.length - 1 - p.y][p.x] = c;
		} else {
			throw new PointOutOfBoundsException(p.toString());
		}
	}

	/**
	 * Gets the character stored at point P
	 * @param p
	 * @return
	 * @author Priscilla Esteves
	 */
	@Override
	public char get(Point p) {
		if (inBounds(p)) {
			return maze[maze.length - 1 - p.y][p.x];
		} else {
			throw new PointOutOfBoundsException(p.toString());
		}
	}

	/**
	 * @return
	 * @author Priscilla Esteves
	 */
	@Override
	public int width() {
		return width;
	}

	/**
	 * @return
	 * @author Priscilla Esteves
	 */
	@Override
	public int height() {
		return height;
	}

	/**
	 * Returns whether or not a point is in the bounds of the maze
	 * @param p
	 * @return
	 * @author Thomas Kwashnak
	 */
	@Override
	public boolean inBounds(Point p) {
		return p.x >= 0 && p.x < width && p.y >= 0 && p.y < height;
	}

	/**
	 * Compiles maze to a string representing a 2d visual of the maze
	 * @return
	 * @author Thomas Kwashnak
	 */
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (char[] arr : maze) {
			stringBuilder.append(Arrays.toString(arr).replace("[", "").replace("]", "").replace(", ", ""));
			stringBuilder.append("\n");
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		return stringBuilder.toString();
	}
}
