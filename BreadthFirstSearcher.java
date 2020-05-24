import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Breadth-First Search (BFS)
 * <p>
 * You should fill the search() method of this class.
 */
public class BreadthFirstSearcher extends Searcher {

    /**
     * Calls the parent class constructor.
     *
     * @param maze initial maze.
     * @see Searcher
     */
    public BreadthFirstSearcher(Maze maze) {
        super(maze);
    }

    /**
     * Main breadth first search algorithm.
     *
     * @return true if the search finds a solution, false otherwise.
     */
    public boolean search() {
        // explored list is a 2D Boolean array that indicates if a state associated with a given position in the maze has already been explored.
        boolean[][] explored = new boolean[maze.getNoOfRows()][maze.getNoOfCols()];

        // Get the start state and enqueue it in the frontier
        Square startSquare = maze.getPlayerSquare();
        State startState = new State(startSquare, null, 0, 0);

        // Queue implementing the Frontier list
        LinkedList<State> queue = new LinkedList<State>();
        queue.add(startState);

        while (!queue.isEmpty()) {
            // update maxSizeOfFrontier
            maxSizeOfFrontier = Math.max(maxSizeOfFrontier, queue.size());

            State current = queue.pop();
            // node is being expanded, increment noOfNodesExpanded
            noOfNodesExpanded++;

            // update explored array and maxDepthSearched
            explored[current.getSquare().X][current.getSquare().Y] = true;
            maxDepthSearched = Math.max(maxDepthSearched, current.getDepth());

            if (current.isGoal(maze)) {
                State previous = current.getParent();
                while (previous != null && !previous.isStart(maze)) {
                    maze.setOneSquare(previous.getSquare(), '.');
                    previous = previous.getParent();
                }
                cost = current.getDepth();
                return true;
            } else {
                for (State state : current.getSuccessors(explored, maze)) {
                    // if state is not already present in queue, only then add it to the queue
                    if (!queue.contains(state)) {
                        queue.add(state);
                    }
                }
            }
        }

        // return false if no solution
        return false;
    }
}
