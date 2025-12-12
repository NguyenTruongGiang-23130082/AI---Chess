package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color, (color == Color.WHITE) ? 'N' : 'n');
    }

    @Override
    public List<Move> getMoves(Position pos, Board board) {
        List<Move> moves = new ArrayList<>();
        int r = pos.getR();
        int c = pos.getC();

      
        int[][] directions = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };

        for (int[] dir : directions) {
            int nextR = r + dir[0];
            int nextC = c + dir[1];
            
            Position nextPos = new Position(nextR, nextC);

            if (!nextPos.isValid()) continue; 

            Piece targetPiece = board.get(nextPos);
            
            if (targetPiece == null || this.isOpposite(targetPiece)) {
                moves.add(new Move(pos, nextPos));
            }
        }
        return moves;
    }
}