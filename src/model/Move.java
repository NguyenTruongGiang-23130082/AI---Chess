package model;

// Cần import các thư viện cần thiết, giả sử Position đã có
// import java.util.Objects; 

public class Move {
    // SỬA: Đảm bảo các enum là public để Board có thể truy cập trực tiếp
    public enum CastlingType { NONE, KINGSIDE, QUEENSIDE }
    public enum SpecialType { 
        NONE, 
        EN_PASSANT, 
        DOUBLE_PAWN_PUSH, 
        PROMOTION_QUEEN, 
        PROMOTION_ROOK, 
        PROMOTION_BISHOP, 
        PROMOTION_KNIGHT 
    }
    
    private final Position start;
    private final Position end;
    private final CastlingType castlingType;
    private final SpecialType specialType;


    public Move(Position start, Position end) {
        this(start, end, CastlingType.NONE, SpecialType.NONE);
    }
    
    public Move(Position start, Position end, CastlingType cType) {
        this(start, end, cType, SpecialType.NONE);
    }
    
    public Move(Position start, Position end, SpecialType sType) {
        this(start, end, CastlingType.NONE, sType);
    }

    public Move(Position start, Position end, CastlingType cType, SpecialType sType) {
        this.start = start;
        this.end = end;
        this.castlingType = cType;
        this.specialType = sType;
    }
    
    // --- PHƯƠNG THỨC MỚI ĐỂ KIỂM TRA PHONG CẤP (Đã có sẵn) ---
    public boolean isPromotion() {
        return specialType == SpecialType.PROMOTION_QUEEN ||
               specialType == SpecialType.PROMOTION_ROOK ||
               specialType == SpecialType.PROMOTION_BISHOP ||
               specialType == SpecialType.PROMOTION_KNIGHT;
    }
    // ---------------------------------------------
    
    public Position getStart() { return start; }
    public Position getEnd() { return end; }
    public CastlingType getCastlingType() { return castlingType; }
    public SpecialType getSpecialType() { return specialType; }
}