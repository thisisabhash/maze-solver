import java.util.ArrayList;

/**
 * A state in the search represented by the (x,y) coordinates of the square and
 * the parent. In other words a (square,parent) pair where square is a Square,
 * parent is a State.
 * <p>
 * You should fill the getSuccessors(...) method of this class.
 */
public class State {

    private Square square;
    private State parent;

    // Maintain the gValue (the distance from start)
    // You may not need it for the BFS but you will
    // definitely need it for AStar
    private int gValue;

    // States are nodes in the search tree, therefore each has a depth.
    private int depth;

    /**
     * @param square current square
     * @param parent parent state
     * @param gValue total distance from start
     */
    public State(Square square, State parent, int gValue, int depth) {
        this.square = square;
        this.parent = parent;
        this.gValue = gValue;
        this.depth = depth;
    }

    /**
     * @param explored explored[i][j] is true if (i,j) is already explored
     * @param maze     initial maze to get find the neighbors
     * @return all the successors of the current state
     */
    public ArrayList<State> getSuccessors(boolean[][] explored, Maze maze) {
        // check all four neighbors in left, down, right, up order
        // remember that each successor's depth and gValue are +1 of this object.

        ArrayList<State> successors = new ArrayList<>();

        //check left square and add it
        if (maze.getSquareValue(this.square.X, this.square.Y - 1) != '%' && !explored[this.square.X][this.square.Y - 1]) {
            successors.add(new State(new Square(this.square.X, this.square.Y - 1), this, this.gValue + 1, this.depth + 1));
        }

        //check down square and add it
        if (maze.getSquareValue(this.square.X + 1, this.square.Y) != '%' && !explored[this.square.X + 1][this.square.Y]) {
            successors.add(new State(new Square(this.square.X + 1, this.square.Y), this, this.gValue + 1, this.depth + 1));
        }

        //check right square and add it
        if (maze.getSquareValue(this.square.X, this.square.Y + 1) != '%' && !explored[this.square.X][this.square.Y + 1]) {
            successors.add(new State(new Square(this.square.X, this.square.Y + 1), this, this.gValue + 1, this.depth + 1));
        }

        //check up square and add it
        if (maze.getSquareValue(this.square.X - 1, this.square.Y) != '%' && !explored[this.square.X - 1][this.square.Y]) {
            successors.add(new State(new Square(this.square.X - 1, this.square.Y), this, this.gValue + 1, this.depth + 1));
        }

        return successors;
    }

    /**
     * @return x coordinate of the current state
     */
    public int getX() {
        return square.X;
    }

    /**
     * @return y coordinate of the current state
     */
    public int getY() {
        return square.Y;
    }

    /**
     * @param maze initial maze
     * @return true is the current state is a goal state
     */
    public boolean isGoal(Maze maze) {
        if (square.X == maze.getGoalSquare().X
                && square.Y == maze.getGoalSquare().Y)
            return true;

        return false;
    }

    /**
     * @param maze initial maze
     * @return true is the current state is a goal state
     */
    public boolean isStart(Maze maze) {
        if (square.X == maze.getPlayerSquare().X
                && square.Y == maze.getPlayerSquare().Y)
            return true;

        return false;
    }

    /**
     * @return the current state's square representation
     */
    public Square getSquare() {
        return square;
    }

    /**
     * @return parent of the current state
     */
    public State getParent() {
        return parent;
    }

    /**
     * You may not need g() value in the BFS but you will need it in A-star
     * search.
     *
     * @return g() value of the current state
     */
    public int getGValue() {
        return gValue;
    }

    /**
     * @return depth of the state (node)
     */
    public int getDepth() {
        return depth;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof State) {
            State state = (State) obj;
            return this.getSquare().X == state.getSquare().X && this.getSquare().Y == state.getSquare().Y;
        }

        return false;
    }
}
