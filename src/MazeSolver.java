import java.io.File;
import java.util.Scanner;


/**
 * @author Thomas Kwashnak & Priscilla Esteves
 *
 * Includes the main method, prompting the user to choose a file name and start/end points, and then solves the maze
 * using a recursive algorithm
 */
public class MazeSolver {


	/**
	 * main method
	 * @param args
	 * @author Priscilla Esteves
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		File file = promptFile(scanner);
		Maze maze = Maze.loadMaze(file.getAbsolutePath());

		System.out.println("Please type the starting point");
		Point start = getValidPoint(maze, scanner);
		System.out.println("Please type the goal");
		Point goal = getValidPoint(maze, scanner);

		System.out.println(maze.toString());

		if (solveMaze(maze, start, goal)) {
			System.out.println("The maze was solved");
			Maze.saveMaze(file.getName() + ".solved", maze);
		} else {
			System.out.println("The maze was not solved");
		}

		System.out.println(maze.toString());


	}


	/**
	 * prompts the user to provide a file name until the user types an existing file name
	 * @param scanner
	 * @return
	 * @author Priscilla Esteves
	 */
	public static File promptFile(Scanner scanner) {
		System.out.println("Please type a file name.");
		File file = new File(scanner.nextLine());
		if (file.exists()) {
			return file;
		} else {
			return promptFile(scanner);
		}
	}

	/**
	 * Prompts the user to type coordinates of a point until the user types valid points within the maze (maze must
	 * be empty at those points)
	 * @param maze
	 * @param scanner
	 * @return
	 * @author Priscilla Esteves
	 */
	public static Point getValidPoint(Maze maze, Scanner scanner) {
		try {

			System.out.println("Please type coordinates with a space between X and Y.");
			String line = scanner.nextLine();
			Scanner scanline = new Scanner(line);
			int X = scanline.nextInt();
			int Y = scanline.nextInt();
			Point P = new Point(X, Y);
			if (maze.inBounds(P) && maze.get(P) == Maze.EMPTY) {
				return P;
			} else {
				return getValidPoint(maze, scanner);
			}
		} catch (Exception e) {
			return getValidPoint(maze, scanner);
		}
	}


	/**
	 * Solves the maze using the recursive method findGoal
	 * @param maze
	 * @param start
	 * @param end
	 * @return
	 * @author Thomas Kwashnak
	 */
	public static boolean solveMaze(Maze maze, Point start, Point end) {
		maze.set(end, Maze.GOAL);
		boolean ret = findGoal(maze, start);
		maze.set(start, Maze.START);

		return ret;
	}

	/**
	 * Solves the maze via brute force by checking all possible directions / paths
	 * @param maze
	 * @param pos
	 * @return
	 * @author Thomas Kwashnak
	 */
	public static boolean findGoal(Maze maze, Point pos) {
		try {
			switch (maze.get(pos)) {
				case Maze.WALL:
				case Maze.VISITED:
				case Maze.START:
				case Maze.PATH:
					return false;
				case Maze.GOAL:
					return true;
				default:
					maze.set(pos, Maze.PATH);
					for (Point p : pos.getAdjacentPoints()) {
						if (findGoal(maze, p)) {
							return true;
						}
					}
					maze.set(pos, Maze.VISITED);
					return false;
			}
		} catch(Exception e) {
			return false;
		}
	}
}
