package controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.Board;
import model.Move;
import model.Piece;
import model.Position;

import model.Pawn;
import model.Knight;
import model.Bishop;
import model.Rook;
import model.Queen;
import model.King;


public class ChessAI {
    private Color aiColor;
    private static final int SEARCH_DEPTH = 3; 

    // --- 1. GIÁ TRỊ VẬT CHẤT CƠ BẢN (MATERIAL VALUE) ---
    private static final int PAWN_VALUE = 100;
    private static final int KNIGHT_VALUE = 320;
    private static final int BISHOP_VALUE = 330;
    private static final int ROOK_VALUE = 500;
    private static final int QUEEN_VALUE = 900;
    private static final int KING_VALUE = 30000; // King phải có giá trị cực cao

    // --- 2. BẢNG GIÁ TRỊ VỊ TRÍ QUÂN CỜ (PIECE-SQUARE TABLES - PST) ---

    // 2.1. PAWN (Tốt)
    private static final int[] PAWN_PST = {
        0, 0, 0, 0, 0, 0, 0, 0, 50, 50, 50, 50, 50, 50, 50, 50,
        10, 10, 20, 30, 30, 20, 10, 10, 5, 5, 10, 25, 25, 10, 5, 5,
        0, 0, 0, 20, 20, 0, 0, 0, 5, -5, -10, 0, 0, -10, -5, 5,
        5, 10, 10, -20, -20, 10, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0
    };

    // 2.2. KNIGHT (Mã)
    private static final int[] KNIGHT_PST = {
        -50, -40, -30, -30, -30, -30, -40, -50, -40, -20, 0, 5, 5, 0, -20, -40,
        -30, 5, 10, 15, 15, 10, 5, -30, -30, 0, 15, 20, 20, 15, 0, -30,
        -30, 5, 15, 20, 20, 15, 5, -30, -30, 0, 10, 15, 15, 10, 0, -30,
        -40, -20, 0, 0, 0, 0, -20, -40, -50, -40, -30, -30, -30, -30, -40, -50
    };
    
    // 2.3. BISHOP (Tượng)
    private static final int[] BISHOP_PST = {
        -20, -10, -10, -10, -10, -10, -10, -20, -10, 0, 0, 0, 0, 0, 0, -10,
        -10, 0, 5, 10, 10, 5, 0, -10, -10, 5, 5, 10, 10, 5, 5, -10,
        -10, 0, 10, 10, 10, 10, 0, -10, -10, 10, 10, 10, 10, 10, 10, -10, 
        -10, 5, 0, 0, 0, 0, 5, -10, -20, -10, -10, -10, -10, -10, -10, -20
    };
    
    // 2.4. ROOK (Xe)
    private static final int[] ROOK_PST = {
        0, 0, 0, 0, 0, 0, 0, 0, 5, 10, 10, 10, 10, 10, 10, 5, 
        -5, 0, 0, 0, 0, 0, 0, -5, -5, 0, 0, 0, 0, 0, 0, -5,
        -5, 0, 0, 0, 0, 0, 0, -5, -5, 0, 0, 0, 0, 0, 0, -5,
        -5, 0, 0, 0, 0, 0, 0, -5, 0, 0, 0, 5, 5, 0, 0, 0 
    };

    // 2.5. QUEEN (Hậu)
    private static final int[] QUEEN_PST = {
        -20, -10, -10, -5, -5, -10, -10, -20, -10, 0, 0, 0, 0, 0, 0, -10,
        -10, 0, 5, 5, 5, 5, 0, -10, -5, 0, 5, 5, 5, 5, 0, -5,
        0, 0, 5, 5, 5, 5, 0, -5, -10, 5, 5, 5, 5, 5, 0, -10,
        -10, 0, 5, 0, 0, 0, 0, -10, -20, -10, -10, -5, -5, -10, -10, -20
    };
    
    // 2.6. KING (Vua - Giữ an toàn trong Midgame)
    private static final int[] KING_MIDGAME_PST = {
        -30, -40, -40, -50, -50, -40, -40, -30, -30, -40, -40, -50, -50, -40, -40, -30,
        -30, -40, -40, -50, -50, -40, -40, -30, -30, -40, -40, -50, -50, -40, -40, -30,
        -20, -30, -30, -40, -40, -30, -30, -20, -10, -20, -20, -20, -20, -20, -20, -10,
        20, 20, 0, 0, 0, 0, 20, 20, 20, 30, 10, 0, 0, 10, 30, 20
    };

    public ChessAI(Color aiColor) {
        this.aiColor = aiColor;
    }

    // --- LOGIC CƠ BẢN KIỂM TRA CHIẾU ---
    
    public Position findKing(Board board, Color color) {
        // [Logic tìm Vua]
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece piece = board.get(new Position(r, c));
                if (piece instanceof model.King && piece.getColor() == color) {
                    return new Position(r, c);
                }
            }
        }
        return null; 
    }
    
    public boolean isKingInCheck(Board board, Color color) {
        Position kingPos = findKing(board, color);
        if (kingPos == null) return false;
        return board.isSquareAttacked(kingPos, color); 
    }

    public boolean isMoveSafe(Board board, Move move, Color movingColor) {
        // Tạo bản sao sâu của Board và thực hiện nước đi
        Board tempBoard = new Board(board);
        tempBoard.move(move); 
        
        return !isKingInCheck(tempBoard, movingColor);
    }
    
    public List<Move> generateAllLegalMoves(Board board, Color color) {
        List<Move> allMoves = new ArrayList<>();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Position startPos = new Position(r, c);
                Piece piece = board.get(startPos);
                
                if (piece != null && piece.getColor() == color) {
                    List<Move> potentialMoves = piece.getMoves(startPos, board);
                    
                    for (Move move : potentialMoves) {
                        if (isMoveSafe(board, move, color)) {
                            allMoves.add(move);
                        }
                    }
                }
            }
        }
        return allMoves;
    }

    // --- HÀM TÍNH ĐIỂM PST ---
    private int getPstScore(Piece piece, int r, int c) {
        int index = r * 8 + c;
        int flippedIndex = (7 - r) * 8 + c; 
        
        int[] pst;
        
        if (piece instanceof model.Pawn) pst = PAWN_PST;
        else if (piece instanceof model.Knight) pst = KNIGHT_PST;
        else if (piece instanceof model.Bishop) pst = BISHOP_PST;
        else if (piece instanceof model.Rook) pst = ROOK_PST;
        else if (piece instanceof model.Queen) pst = QUEEN_PST;
        else if (piece instanceof model.King) pst = KING_MIDGAME_PST;
        else return 0; 

        if (piece.getColor() == Color.WHITE) {
            return pst[index]; 
        } else {
            return pst[flippedIndex];
        }
    }

    private int getMaterialValue(Piece piece) {
        if (piece instanceof model.Pawn) return PAWN_VALUE;
        if (piece instanceof model.Knight) return KNIGHT_VALUE;
        if (piece instanceof model.Bishop) return BISHOP_VALUE;
        if (piece instanceof model.Rook) return ROOK_VALUE;
        if (piece instanceof model.Queen) return QUEEN_VALUE;
        if (piece instanceof model.King) return KING_VALUE;
        return 0;
    }

    /**
     * Gán điểm số cho bàn cờ hiện tại. Điểm số dương có lợi cho AI.
     */
    public int evaluateBoard(Board board) {
        int score = 0;
        Color opponentColor = (aiColor == Color.WHITE) ? Color.BLACK : Color.WHITE;

        // 1. TÍNH TỔNG ĐIỂM VẬT CHẤT VÀ VỊ TRÍ
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece piece = board.get(new Position(r, c));

                if (piece != null) {
                    int material = getMaterialValue(piece);
                    int positionScore = getPstScore(piece, r, c);
                    
                    // !!! QUAN TRỌNG: GIẢM TRỌNG SỐ PST (chia cho 15) ĐỂ CÂN BẰNG ĐIỂM SỐ !!!
                    positionScore = positionScore / 15;

                    int totalPieceValue = material + positionScore;

                    if (piece.getColor() == aiColor) {
                        score += totalPieceValue;
                    } else {
                        score -= totalPieceValue;
                    }
                }
            }
        }
        
        // 2. PHẠT VUA BỊ CHIẾU
        if (isKingInCheck(board, aiColor)) {
            score -= 100; 
        }
        if (isKingInCheck(board, opponentColor)) {
            score += 100; 
        }
        
        return score;
    }


    // --- THUẬT TOÁN ALPHA-BETA PRUNING ---
    
    private int minimax(Board board, int depth, int alpha, int beta, boolean isMaximizingPlayer) {
        
        Color currentColor = isMaximizingPlayer ? aiColor : (aiColor == Color.WHITE ? Color.BLACK : Color.WHITE);
        List<Move> legalMoves = generateAllLegalMoves(board, currentColor);

        // Xử lý Chiếu hết/Hòa (Điều kiện dừng)
        if (legalMoves.isEmpty()) {
            if (isKingInCheck(board, currentColor)) {
                // Chiếu hết
                return isMaximizingPlayer ? -999999 : 999999; 
            } else {
                // Hòa cờ (Stalemate)
                return 0;
            }
        }
        
        // Điều kiện dừng độ sâu (SAU khi kiểm tra chiếu hết)
        if (depth == 0) {
            // Tối ưu hóa: Thay thế bằng Quiescence Search nếu có
            return evaluateBoard(board); 
        }
        
        // Tối ưu hóa: Sắp xếp nước đi (Move Ordering)
        // Đây là cách đơn giản để giảm lag, ưu tiên nước bắt quân.
        legalMoves.sort(new Comparator<Move>() {
            @Override
            public int compare(Move m1, Move m2) {
                // Lấy quân bị bắt (Captured Piece)
                Piece captured1 = board.get(m1.getEnd());
                Piece captured2 = board.get(m2.getEnd());

                int val1 = (captured1 != null) ? getMaterialValue(captured1) : 0;
                int val2 = (captured2 != null) ? getMaterialValue(captured2) : 0;
                
                // Sắp xếp giảm dần (Nước bắt quân có giá trị cao hơn được ưu tiên)
                return Integer.compare(val2, val1);
            }
        });

        
        if (isMaximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (Move move : legalMoves) {
                Board nextBoard = new Board(board);
                nextBoard.move(move);
                int eval = minimax(nextBoard, depth - 1, alpha, beta, false);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, maxEval);
                if (beta <= alpha) {
                    break; 
                }
            }
            return maxEval;
        } else { // Minimizing Player (Đối thủ)
            int minEval = Integer.MAX_VALUE;
            for (Move move : legalMoves) {
                Board nextBoard = new Board(board);
                nextBoard.move(move);
                int eval = minimax(nextBoard, depth - 1, alpha, beta, true);
                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, minEval);
                if (beta <= alpha) {
                    break; 
                }
            }
            return minEval;
        }
    }


    // --- HÀM TÌM NƯỚC ĐI TỐT NHẤT ---
    
    public Move findBestMove(Board board) {
        List<Move> legalMoves = generateAllLegalMoves(board, aiColor);
        if (legalMoves.isEmpty()) return null; 
        
        Move bestMove = null;
        int bestEval = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        // Tối ưu hóa: Sắp xếp nước đi cho vòng lặp ngoài cùng
        legalMoves.sort(new Comparator<Move>() {
            @Override
            public int compare(Move m1, Move m2) {
                Piece captured1 = board.get(m1.getEnd());
                Piece captured2 = board.get(m2.getEnd());

                int val1 = (captured1 != null) ? getMaterialValue(captured1) : 0;
                int val2 = (captured2 != null) ? getMaterialValue(captured2) : 0;
                
                return Integer.compare(val2, val1);
            }
        });


        // Bắt đầu tìm kiếm
        for (Move move : legalMoves) {
            Board nextBoard = new Board(board);
            nextBoard.move(move);
            
            int eval = minimax(nextBoard, SEARCH_DEPTH - 1, alpha, beta, false); 
            
            if (eval > bestEval) {
                bestEval = eval;
                bestMove = move;
            }
            alpha = Math.max(alpha, bestEval);
        }
        
        return bestMove;
    }
}