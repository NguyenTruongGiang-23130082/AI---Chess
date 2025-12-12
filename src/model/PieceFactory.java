package model;

import java.awt.Color;

public class PieceFactory {
    /**
     * Tạo bản sao sâu (deep copy) của một Piece.
     */
    public static Piece createCopy(Piece original) {
    
        if (original instanceof King) {
            King originalKing = (King) original;
            King copy = new King(originalKing.getColor());
            copy.setHasMoved(originalKing.getHasMoved());
            return copy;
        } 
      
        else if (original instanceof Rook) {
            Rook originalRook = (Rook) original;
            Rook copy = new Rook(originalRook.getColor());
            copy.setHasMoved(originalRook.getHasMoved());
            return copy;
        } 
        else if (original instanceof Queen) {
            return new Queen(original.getColor());
        } else if (original instanceof Bishop) {
            return new Bishop(original.getColor());
        } else if (original instanceof Knight) {
            return new Knight(original.getColor());
        } else if (original instanceof Pawn) {
            return new Pawn(original.getColor());
        }
        return null; 
    }
}