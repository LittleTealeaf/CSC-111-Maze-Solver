/**
 * @author Zach Kohlberg
 * <p>
 * Interface for a Maze of characters
 */

public interface TextMaze {
	//Constants four different characters in the maze. Use these constants instead of hard-
	//coding characters. For example, write TextMaze.WALL in your code instead of '#'.
	char
			WALL = '#',
			EMPTY = ' ',
			GOAL = 'G',
			START = 'S',
			PATH = 'P',
			VISITED = '.';

	/**
	 * Change character at a location within the maze if that location is in bounds.
	 * If the location is out of bounds, throw an IndexOutOfBoundsException showing
	 * the point that was out of bounds.
	 */
	void set(Point p, char c);

	/**
	 * Get character at a location within the maze if that location is in bounds.
	 * If the location is out of bounds, throw an IndexOutOfBoundsException showing
	 * the point that was out of bounds.
	 */
	char get(Point p);

	/**
	 * Return the maze's width.
	 */
	int width();

	/**
	 * Return the maze's height.
	 */
	int height();

	/**
	 * Returns whether a point is within the bounds of the maze.
	 */
	boolean inBounds(Point p);
}