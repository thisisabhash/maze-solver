import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * A* algorithm search
 * <p>
 * You should fill the search() method of this class.
 */
public class AStarSearcher extends Searcher {

    /**
     * Calls the parent class constructor.
     *
     * @param maze initial maze.
     * @see Searcher
     */
    public AStarSearcher(Maze maze) {
        super(maze);
    }

    /**
     * Main a-star search algorithm.
     *
     * @return true if the search finds a solution, false otherwise.
     */
    public boolean search() {
        // explored list is a Boolean array that indicates if a state associated with a given position in the maze has already been explored.
        boolean[][] explored = new boolean[maze.getNoOfRows()][maze.getNoOfCols()];

        // Get the start state and enqueue it in the frontier
        Square startSquare = maze.getPlayerSquare();
        State startState = new State(startSquare, null, 0, 0);
        PriorityQueue<StateFValuePair> frontier = new PriorityQueue<StateFValuePair>();

        //initialize the root state and add to frontier list
        frontier.add(new StateFValuePair(startState, getFValue(startState)));

        while (!frontier.isEmpty()) {
            // update maxSizeOfFrontier
            maxSizeOfFrontier = Math.max(maxSizeOfFrontier, frontier.size());

            StateFValuePair current = frontier.poll();
            // node is being expanded, increment noOfNodesExpanded
            noOfNodesExpanded++;

            // update explored array and maxDepthSearched
            explored[current.getState().getSquare().X][current.getState().getSquare().Y] = true;
            maxDepthSearched = Math.max(maxDepthSearched, current.getState().getDepth());

            if (current.getState().isGoal(maze)) {
                State previous = current.getState().getParent();
                while (previous != null && !previous.isStart(maze)) {
                    maze.setOneSquare(previous.getSquare(), '.');
                    previous = previous.getParent();
                }
                cost = current.getState().getDepth();
                return true;
            } else {
                for (State state : current.getState().getSuccessors(explored, maze)) {
                    StateFValuePair newlyGeneratedState = new StateFValuePair(state, getFValue(state));
                    if (!frontier.contains(newlyGeneratedState)) {
                        frontier.add(newlyGeneratedState);
                    } else {
                    	//System.out.println("Already contains same state");
                        // frontier contains the same state, fetch it
                        Iterator<StateFValuePair> iterator = frontier.iterator();
                        StateFValuePair alreadyExistingState = null;
                        while (iterator.hasNext()) {
                            alreadyExistingState = iterator.next();
                            if (alreadyExistingState.equals(newlyGeneratedState)) {
                                break;
                            }
                        }

                        // check if g(newly generated node) < g(already existing node)
                        if (alreadyExistingState != null && newlyGeneratedState.getState().getGValue() < alreadyExistingState.getState().getGValue()) {
							//System.out.println("gValue of new state is lesser than the existing one");
                        	frontier.remove(alreadyExistingState); // remove already existing state
							frontier.add(newlyGeneratedState); // add newly generated state
                        }
                    }
                }
            }
        }

        //return false if no solution
        return false;
    }

    /**
     * Returns FValue of a given state
     *
     * @param state
     * @return double FValue of given state
     */
    private double getFValue(State state) {
        return state.getGValue() +
                Math.sqrt(
                        (maze.getGoalSquare().X - state.getSquare().X) * (maze.getGoalSquare().X - state.getSquare().X) +
                                (maze.getGoalSquare().Y - state.getSquare().Y) * (maze.getGoalSquare().Y - state.getSquare().Y));
    }

}
