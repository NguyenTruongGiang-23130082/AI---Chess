package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    private boolean hasMoved = false;

    public King(Color color) {
        super(color, (color == Color.WHITE) ? 'K' : 'k');
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public List<Move> getMoves(Position pos, Board board) {
        // ĐỔI TÊN THÀNH "potentialMoves" để làm rõ mục đích
        List<Move> potentialMoves = new ArrayList<>();
        
        int r = pos.getR();
        int c = pos.getC();

        int[][] directions = {
            {1, 1}, {1, -1}, {-1, -1}, {-1, 1},
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
        };

        for (int[] dir : directions) {
            int nextR = r + dir[0];
            int nextC = c + dir[1];
            
            Position nextPos = new Position(nextR, nextC);

            if (!nextPos.isValid()) continue;

            Piece targetPiece = board.get(nextPos);
            
            if (targetPiece == null || this.isOpposite(targetPiece)) {
                potentialMoves.add(new Move(pos, nextPos));
            }
        }
        
        // Giữ lại việc tạo nước đi Nhập thành (Castling)
        addCastlingMoves(pos, board, potentialMoves);
        
        // ❌ XÓA KHỐI LỆNH LỌC NƯỚC ĐI AN TOÀN NÀY:
        /*
        for (Move move : allPotentialMoves) {
            Position endPos = move.getEnd();
            
            if (!board.isSquareAttacked(endPos, this.getColor())) {
                legalMoves.add(move);
            }
        }
        return legalMoves;
        */
        
        // CHỈ TRẢ VỀ CÁC NƯỚC ĐI TIỀM NĂNG
        return potentialMoves; 
    }

    private void addCastlingMoves(Position pos, Board board, List<Move> moves) {
        if (this.hasMoved) return;	
        
        // ❌ XÓA/COMMENT: Tránh gọi đệ quy
        // if (board.isSquareAttacked(pos, this.getColor())) return;	

        int r = pos.getR();	
        Position rookKPos = new Position(r, 7);
        Piece rookK = board.get(rookKPos);

        if (rookK != null && rookK instanceof Rook && !((Rook)rookK).getHasMoved()) {
            if (board.get(new Position(r, 5)) == null && board.get(new Position(r, 6)) == null) {
                
                // ❌ XÓA/COMMENT: Tránh gọi đệ quy
                /*
                if (!board.isSquareAttacked(new Position(r, 5), this.getColor()) &&
                    !board.isSquareAttacked(new Position(r, 6), this.getColor())) {
                */
                
                    moves.add(new Move(pos, new Position(r, 6), Move.CastlingType.KINGSIDE));
                /*
                }
                */
            }
        }
        
        // ... (Thực hiện tương tự cho Queenside)
        Position rookQPos = new Position(r, 0);
        Piece rookQ = board.get(rookQPos);

        if (rookQ != null && rookQ instanceof Rook && !((Rook)rookQ).getHasMoved()) {
            if (board.get(new Position(r, 3)) == null && 
                board.get(new Position(r, 2)) == null && 
                board.get(new Position(r, 1)) == null) {
                
                // ❌ XÓA/COMMENT: Tránh gọi đệ quy
                /*
                if (!board.isSquareAttacked(new Position(r, 3), this.getColor()) &&
                    !board.isSquareAttacked(new Position(r, 2), this.getColor())) {
                */
                
                    moves.add(new Move(pos, new Position(r, 2), Move.CastlingType.QUEENSIDE));
                /*
                }
                */
            }
        }
    }
}