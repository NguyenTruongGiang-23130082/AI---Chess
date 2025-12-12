package controller;

import java.awt.Color; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.Board;
import model.Position;
import view.GameWindow;
import model.Move;
import model.Move.SpecialType;
import model.Piece;
import model.Pawn; // Cần thiết để kiểm tra Tốt


public class GameController implements ActionListener {
    
    private Board board;
    private GameWindow view;
    private JButton exitButton; 
    private JButton guideButton;
    private JButton newGameButton;
    
    // ĐỔI TÊN: playerColor -> myPlayerColor (Tránh nhầm lẫn với CurrentTurn)
    private Color myPlayerColor; 
    
    private JButton[][] chessSquares;
    private Position selectedPosition = null; 
    private List<Move> possibleMoves = new ArrayList<Move>();
    private ChessAI chessAI;
    private Color aiColor;

    public GameController(Board board, GameWindow view, JButton[][] chessSquares, JButton exitButton,JButton guideButton,JButton newGameButton) {
        this.board = board;
        this.view = view;
        this.chessSquares = chessSquares;
        this.exitButton = exitButton;
        this.guideButton = guideButton;
        this.newGameButton = newGameButton;

        // Khởi tạo AI tạm thời (sẽ được khởi tạo lại trong startNewGame)
        // Giả sử người chơi luôn là Trắng khi bắt đầu game mới
        this.myPlayerColor = Color.WHITE;
        this.aiColor = Color.BLACK;
        this.chessAI = new ChessAI(this.aiColor);

        attachListener();
        attachSquareListeners();
    }
    
    private void attachListener() {
        exitButton.addActionListener(this); 
        guideButton.addActionListener(this);
        newGameButton.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == exitButton) {
            handleExitConfirmation();
        } 
        else if (e.getSource() == guideButton) {
            showGuide();
        }
        else if (e.getSource() == newGameButton) {
            startNewGame(); 
        }
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (e.getSource() == chessSquares[r][c]) {
                    handleSquareClick(new Position(r, c));
                    return; 
                }
            }
        }
       
    }
    
    
    private void handleExitConfirmation() {
        int choice = JOptionPane.showConfirmDialog(
            view,
            "Bạn có chắc chắn muốn thoát game không?", 
            "Xác Nhận Thoát", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE 
        );

        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    private void showGuide() {
        // ... (Giữ nguyên nội dung)
        String guideText = 
            "Chào mừng bạn đến với Game Cờ Vua AI!\n\n" +
            "LUẬT CHƠI CƠ BẢN:\n" +
            "1. Mục tiêu: Chiếu hết (Checkmate) Vua đối phương.\n" +
            "2. Lượt đi: Trắng đi trước, sau đó luân phiên.\n\n" +
            "DI CHUYỂN CÁC QUÂN CỜ:\n" +
            "- Vua (King): Di chuyển 1 ô bất kỳ. Có thể Nhập Thành (Castling).\n" +
            "- Hậu (Queen): Đi thẳng, ngang, chéo bao nhiêu ô tùy ý.\n" +
            "- Xe (Rook): Đi thẳng và ngang bao nhiêu ô tùy ý.\n" +
            "- Tượng (Bishop): Đi chéo bao nhiêu ô tùy ý.\n" +
            "- Mã (Knight): Di chuyển hình chữ L (2 ô thẳng, 1 ô ngang).\n" +
            "- Tốt (Pawn): Đi thẳng 1 ô (hoặc 2 ô ở nước đầu tiên), bắt quân chéo 1 ô. Có Bắt Tốt Qua Đường (En Passant) và Phong Cấp (Promotion).\n\n" +
            "CHỨC NĂNG ĐẶC BIỆT:\n" +
            "Bạn đang chơi với thuật toán Alpha-Beta Pruning. Hãy cẩn thận!";

        JOptionPane.showMessageDialog(
            view,
            guideText, 
            "Hướng Dẫn Chơi Cờ Vua", 
            JOptionPane.INFORMATION_MESSAGE 
        );
    }
    
    private void startNewGame() {
        
    	this.myPlayerColor = Color.WHITE; // Mặc định người chơi là Trắng
        this.board = new Board(); // Khởi tạo Board
        
        this.view.setBoard(this.board);
        
        this.aiColor = (this.myPlayerColor == Color.WHITE) ? Color.BLACK : Color.WHITE;
        this.chessAI = new ChessAI(this.aiColor); // Khởi tạo AI với màu đối thủ
        
        this.selectedPosition = null; 
        this.possibleMoves.clear();
        this.board.setCurrentTurn(Color.WHITE); // Trắng đi trước
        
        view.updateBoardGUI(); 
        view.resetHighlights(); 

        String startMessage = "Game mới đã bắt đầu. Bạn là **Quân Trắng** và được đi trước.";
        
        JOptionPane.showMessageDialog(
            view, 
            startMessage, 
            "Bắt Đầu Game", 
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    private void attachSquareListeners() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                chessSquares[r][c].addActionListener(this);
            }
        }
    }

    // --- SỬA ĐỔI CHÍNH TRONG handleSquareClick ---
    private void handleSquareClick(Position pos) {
   
        if (this.myPlayerColor == null || board.getCurrentTurn() == null) {
            JOptionPane.showMessageDialog(view, "Hãy bấm 'Chơi Game Mới' để bắt đầu!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!board.getCurrentTurn().equals(this.myPlayerColor)) { 
            // Nếu đến lượt AI, hoặc đã kết thúc game
            JOptionPane.showMessageDialog(view, "Chưa đến lượt bạn đi!", "Lỗi Lượt Đi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Piece piece = board.get(pos);

        // Trường hợp 1: Chưa có quân nào được chọn
        if (selectedPosition == null) {
            if (piece != null && piece.getColor() == myPlayerColor) {
                selectedPosition = pos;
                // Lấy nước đi hợp lệ đã lọc chiếu vua
                possibleMoves = generatePlayerLegalMoves(pos, board); 
                view.highlightSquares(selectedPosition, possibleMoves);
            }
        }

        // Trường hợp 2: Quân đã được chọn và click vào ô đích khả thi
        else if (isPossibleMove(pos)) {
            
            Move move = null;
            
            // BẮT ĐẦU LOGIC PHONG CẤP TỐT MỚI
            Piece selectedPiece = board.get(selectedPosition);
            
            // Kiểm tra: Có phải Tốt đang đi đến hàng cuối (hàng 0 cho Trắng, hàng 7 cho Đen) không?
            boolean isPawnPromotion = (selectedPiece instanceof Pawn) && 
                                      ((selectedPiece.getColor() == Color.WHITE && pos.getR() == 7) ||
                                       (selectedPiece.getColor() == Color.BLACK && pos.getR() == 0));

            if (isPawnPromotion) {
                // Nếu là Phong cấp, gọi hàm tương tác để người chơi chọn
                move = handlePromotionSelection(selectedPosition, pos);
            } else {
                // Nước đi bình thường (hoặc bắt quân)
                move = findMove(selectedPosition, pos);
            }
            
            // KẾT THÚC LOGIC PHONG CẤP TỐT MỚI
            
            
            if (move != null) {
            	view.resetHighlights();
                // 1. Cập nhật Model (Thực hiện nước đi)
                board.move(move); 
                
                // 2. Cập nhật View
                view.updateBoardGUI();
//                view.resetHighlights(); 
                
                // 3. Kiểm tra trạng thái kết thúc game
                checkGameStatus();

                // 4. GỌI LƯỢT AI
                if (!board.isGameOver() && board.getCurrentTurn() == this.aiColor) {
                     handleAITurn(); 
                }
            }
            // Reset trạng thái
            selectedPosition = null;
            possibleMoves.clear();
        }
        else if (piece != null && piece.getColor() == myPlayerColor) {
            view.resetHighlights();
            selectedPosition = pos;
            possibleMoves = generatePlayerLegalMoves(pos, board); 
            view.highlightSquares(selectedPosition, possibleMoves);
        }

        // Trường hợp 4: Click vào ô không hợp lệ (Reset)
        else {
            view.resetHighlights();
            selectedPosition = null;
            possibleMoves.clear();
        }
    }


    // --- HÀM BỔ SUNG CHO PHONG CẤP ---
    
    /**
     * Hiển thị hộp thoại để người chơi chọn quân cờ Phong cấp.
     * Trả về Move Phong cấp đã hoàn chỉnh.
     */
    private Move handlePromotionSelection(Position start, Position end) {
        // 1. Định nghĩa các lựa chọn cho người chơi
        Object[] options = {"Queen (Hậu)", "Rook (Xe)", "Bishop (Tượng)", "Knight (Mã)"};
        
        // 2. Mở hộp thoại (JOptionPane) để người chơi chọn
        int choice = JOptionPane.showOptionDialog(
            view,
            "Tốt đã tiến đến hàng cuối. Bạn muốn phong cấp thành quân nào?",
            "Chọn Quân Phong Cấp",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0] // Mặc định chọn Hậu
        );

        // 3. Gán SpecialType dựa trên lựa chọn
        SpecialType promotionType = SpecialType.PROMOTION_QUEEN; // Mặc định
        switch (choice) {
            case 0: promotionType = SpecialType.PROMOTION_QUEEN; break;
            case 1: promotionType = SpecialType.PROMOTION_ROOK; break;
            case 2: promotionType = SpecialType.PROMOTION_BISHOP; break;
            case 3: promotionType = SpecialType.PROMOTION_KNIGHT; break;
            default: promotionType = SpecialType.PROMOTION_QUEEN; break; // Nếu đóng/hủy, vẫn phong Hậu
        }

        // 4. Trả về đối tượng Move Promotion đã hoàn chỉnh
        return new Move(start, end, promotionType);
    }
    
    
    // --- HÀM BỔ SUNG CẦN THIẾT ---
    
    private List<Move> generatePlayerLegalMoves(Position startPos, Board board) {
        Piece piece = board.get(startPos);
        if (piece == null) return new ArrayList<>();
        
        List<Move> legalMoves = new ArrayList<>();
        List<Move> potentialMoves = piece.getMoves(startPos, board); 
        
        for (Move move : potentialMoves) {
            // Sử dụng logic kiểm tra chiếu của AI
            if (this.chessAI.isMoveSafe(board, move, this.myPlayerColor)) {
                legalMoves.add(move);
            }
        }
        return legalMoves;
    }

    private boolean isPossibleMove(Position target) {
        for (Move move : possibleMoves) {
            if (move.getEnd().equals(target)) {
                return true;
            }
        }
        return false;
    }

    // Cần đảm bảo hàm này trả về Move KHÔNG PHONG CẤP nếu có nhiều loại Move Promotion đến cùng 1 ô đích.
    // NHƯNG với logic mới, chúng ta không cần lo lắng vì Promotion đã được xử lý riêng.
    private Move findMove(Position start, Position end) {
        for (Move move : possibleMoves) {
            if (move.getStart().equals(start) && move.getEnd().equals(end)) {
                 // Nếu đây là nước Promotion, chúng ta sẽ bỏ qua, vì nó đã được xử lý ở handlePromotionSelection
                 if (!move.getSpecialType().toString().startsWith("PROMOTION")) {
                    return move;
                 }
            }
        }
        // Giả sử nếu là nước Promotion, hàm này sẽ trả về null (và đó là lý do chúng ta cần handlePromotionSelection)
        // Nhưng nếu không phải Promotion, chúng ta sẽ trả về Move bình thường.
        
        // Vì list possibleMoves đã chứa các Move Promotion, chúng ta vẫn trả về được
        for (Move move : possibleMoves) {
             if (move.getStart().equals(start) && move.getEnd().equals(end)) {
                 return move; // Trả về bất kỳ Move nào khớp start/end (hãy để logic Promotion xử lý loại Move)
             }
        }
        
        return null;
    }

    private void handleAITurn() {
        if (board.getCurrentTurn() != aiColor) return;
        
        // Vô hiệu hóa tương tác người dùng trong khi AI đang tính toán (Tốt nhất nên có trong View)
        
        // 1. Tìm nước đi tốt nhất
        Move aiMove = chessAI.findBestMove(board);
        
        if (aiMove != null) {
            // 2. Thực hiện nước đi trên Board
            board.move(aiMove); 
            
            // 3. Cập nhật giao diện
            view.updateBoardGUI(); 
            view.resetHighlights();
            
            // 4. Kiểm tra Chiếu hết/Hòa
            checkGameStatus();
        } else {
             // Game kết thúc (Chiếu hết hoặc Hòa)
             checkGameStatus();
        }
        
        // Kích hoạt lại tương tác cho người chơi
        // view.setGameActive(true); 
    }
    
    private void checkGameStatus() {
        Color currentPlayer = board.getCurrentTurn();
        // Cần đảm bảo rằng hàm generateAllLegalMoves đã có trong ChessAI
        List<Move> legalMoves = chessAI.generateAllLegalMoves(board, currentPlayer);
        
        if (legalMoves.isEmpty()) {
            if (chessAI.isKingInCheck(board, currentPlayer)) {
                // Chiếu hết
                Color winner = (currentPlayer == myPlayerColor) ? aiColor : myPlayerColor;
                JOptionPane.showMessageDialog(view, "Chiếu hết! " + (winner == aiColor ? "AI" : "Người chơi") + " thắng!");
                board.setGameOver(true);
            } else {
                // Hòa cờ (Stalemate)
                JOptionPane.showMessageDialog(view, "Hòa cờ (Stalemate).");
                board.setGameOver(true);
            }
        }
    }
    
    public void setPlayerColor(Color color) {
        this.myPlayerColor = color;
    }
}