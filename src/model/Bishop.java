package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


public class Bishop extends Piece {
	public Bishop(Color color) {
		super(color, (color == Color.WHITE) ? 'B' : 'b');
	}

	@Override
	public List<Move> getMoves(Position pos, Board board) {
		List<Move> moves = new ArrayList<>();
		int r = pos.getR();
		int c = pos.getC();

		int[][] directions = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };
		for (int[] dir : directions) {

			int dr = dir[0];

			int dc = dir[1];
			for (int i = 1; i < 8; i++) {
				int nextR = r + dr * i;
				int nextC = c + dc * i;
				Position nextPos = new Position(nextR, nextC);
				if (!nextPos.isValid()) {
					break;
				}
				Piece targetPiece = board.get(nextPos);
				if (targetPiece == null) {
					moves.add(new Move(pos, nextPos));
				} else {
					if (this.isOpposite(targetPiece)) {
						moves.add(new Move(pos, nextPos));
					}

					break;
				}
			}
		}
		return moves;
	}

}
