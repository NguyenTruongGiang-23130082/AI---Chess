import java.util.*;

public class Node {
	int n;
	List<Integer> state;
	List<Node> neighbours;
	Node parent;
	boolean visited;

	public Node(int n) {
		this.n = n;
		this.state = new ArrayList<>();
		this.neighbours = new ArrayList<>();
		this.parent = null;
		this.visited = false;
	}

	public Node(int n, List<Integer> state) {
		this.n = n;
		this.state = new ArrayList<>(state);
		this.neighbours = new ArrayList<>();
		this.parent = null;
		this.visited = false;
	}

	public void addNeighbours(Node neighbourNode) {
		this.neighbours.add(neighbourNode);
	}

	public boolean isValid(List<Integer> state) {
		int col = state.size() - 1;
		int newRow = state.get(col);

		for (int i = 0; i < col; i++) {
			int row = state.get(i);
			if (row == newRow)
				return false; 
			if (Math.abs(row - newRow) == Math.abs(i - col))
				return false; 
		}
		return true;
	}

	private List<Integer> place(int x) {
		List<Integer> newState = new ArrayList<>(state);
		newState.add(x);
		if (isValid(newState))
			return newState;
		return null;
	}

	public List<Node> getNeighbours() {
		if (state.size() == n)
			return Collections.emptyList();
		for (int row = 0; row < n; row++) {
			List<Integer> newState = place(row);
			if (newState != null) {
				Node child = new Node(n, newState);
				addNeighbours(child);
			}
		}
		return neighbours;
	}

	public boolean isGoal() {
		return state.size() == n;
	}

	@Override
	public String toString() {
		return state.toString();
	}
}
