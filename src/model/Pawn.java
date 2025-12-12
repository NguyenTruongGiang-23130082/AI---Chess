package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color, (color == Color.WHITE) ? 'P' : 'p');
    }

    @Override
    public List<Move> getMoves(Position pos, Board board) {
        List<Move> moves = new ArrayList<>();
        int r = pos.getR();
        int c = pos.getC();
        
        int direction = (this.getColor() == Color.WHITE) ? 1 : -1;
        
        // Hàng xuất phát
        int startRow = (this.getColor() == Color.WHITE) ? 1 : 6;
        
        // Hàng phong cấp (Không cần thiết cho logic tạo Move, chỉ cần cho GameController)
        // int promotionRow = (this.getColor() == Color.WHITE) ? 7 : 0; 
        
        Position target;

        // --- 1. DI CHUYỂN THẲNG (1 ô) ---
        target = new Position(r + direction, c);
        if (target.isValid() && board.get(target) == null) {
            
            // KHÔNG KIỂM TRA promotionRow ở đây. 
            // Cứ thêm nước đi bình thường (NONE)
            moves.add(new Move(pos, target)); 

            // --- 2. DI CHUYỂN THẲNG (2 ô - chỉ ở nước đi đầu tiên) ---
            if (r == startRow) {
                Position targetTwo = new Position(r + 2 * direction, c);
                // Kiểm tra ô đích thứ 2 và ô trung gian (target) đều trống
                if (targetTwo.isValid() && board.get(targetTwo) == null) {
                    moves.add(new Move(pos, targetTwo, Move.SpecialType.DOUBLE_PAWN_PUSH));
                }
            }
        }

        // --- 3. BẮT QUÂN (Đường chéo 1 ô) ---
        int[] captureCols = {c - 1, c + 1};
        for (int col : captureCols) {
            target = new Position(r + direction, col);
            if (target.isValid()) {
                Piece pieceAtTarget = board.get(target);
                
                // Kiểm tra xem có quân đối phương ở ô chéo không
                if (pieceAtTarget != null && pieceAtTarget.getColor() != this.getColor()) {
                    
                    // KHÔNG KIỂM TRA promotionRow ở đây. 
                    // Cứ thêm nước đi bình thường (NONE)
                    moves.add(new Move(pos, target)); 
                }
            }
        }
        
        // --- 4. BẮT TỐT QUA ĐƯỜNG (En Passant) ---
        Move lastMove = board.getLastMove(); 
        
        // Điều kiện Tốt nằm ở hàng có thể bắt Tốt qua đường (Hàng 5 cho Trắng, Hàng 4 cho Đen)
        if (lastMove != null && r == (this.getColor() == Color.WHITE ? 4 : 3)) {
            Piece movedPiece = board.get(lastMove.getEnd());
            
            // Kiểm tra: Quân đi cuối cùng là Tốt và đi 2 ô
            if (movedPiece instanceof Pawn && lastMove.getSpecialType() == Move.SpecialType.DOUBLE_PAWN_PUSH) {
                
                int lastMoveEndC = lastMove.getEnd().getC();
                
                // Kiểm tra Tốt đối thủ nằm ngay bên cạnh (c == lastMoveEndC - 1 HOẶC c == lastMoveEndC + 1)
                if (Math.abs(lastMoveEndC - c) == 1) { 
                    // Kiểm tra xem nó có vừa đi 2 ô (không cần kiểm tra 2 * direction * -1 nếu DOUBLE_PAWN_PUSH đã được gắn đúng)
                    
                    target = new Position(r + direction, lastMoveEndC);
                    moves.add(new Move(pos, target, Move.SpecialType.EN_PASSANT));
                }
            }
        }

        return moves;
    }

    // BỎ HÀM addPromotionMoves, vì GameController sẽ tạo Move Promotion
    // private void addPromotionMoves(Position start, Position end, List<Move> moves) { ... }
}