import model.Board;
import view.GameWindow;

public class Test {
	public static void main(String[] args) {
		Board board = new Board();
		board.print();
		
		new GameWindow();
	}
}
