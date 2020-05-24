/**
 * A data-structure to keep (state,fValue) pairs. This class will helpful to add
 * states to priority queues. You can add a state with the f() value to a queue
 * by adding (state,f() value) pair to the priority queue.
 * 
 * You do not need to change this class.
 */
public class StateFValuePair implements Comparable<StateFValuePair> {
	private State state;
	private double fValue;

	public StateFValuePair(State state, double fValue) {
		this.state = state;
		this.fValue = fValue;
	}

	public State getState() {
		return state;
	}

	public double getFValue() {
		return fValue;
	}

	@Override
	public int compareTo(StateFValuePair o) {
		if (this.fValue > o.fValue)
			return 1;
		else if (this.fValue < o.fValue)
			return -1;

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof  StateFValuePair) {
			StateFValuePair stateFValuePair = (StateFValuePair) obj;
			return this.getState().getSquare().X == stateFValuePair.getState().getSquare().X &&
					this.getState().getSquare().Y == stateFValuePair.getState().getSquare().Y;
		}

		return false;
	}
}
