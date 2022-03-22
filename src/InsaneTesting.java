public class InsaneTesting {

	public static void main(String[] args) {
		double density = 30;

		int width = 100, height = 100;

		Maze maze = new Maze(width,height);
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				if(Math.random() * 100 <= density) {
					maze.set(new Point(x,y),Maze.WALL);
				}
			}
		}

		maze.set(new Point(0,0),Maze.EMPTY);
		maze.set(new Point(width-1,height-1),Maze.EMPTY);

		long start = System.currentTimeMillis();
		System.out.println(MazeSolver.solveMaze(maze,new Point(0,0),new Point(width-1,height-1)));
		System.out.println(maze.toString());
		System.out.println("Elapsed: " + (System.currentTimeMillis() - start) + " ms");
	}
}
