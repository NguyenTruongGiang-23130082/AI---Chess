package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Board;
import model.Move;
import model.Piece;
import model.Position;

public class GameWindow extends JFrame {
	private JButton exitButton;
	private JButton guideButton;
	private Board board;
	private JButton[][] chessSquares = new JButton[8][8];
	private JButton newGameButton;
	
	private static final Color DARK_SQUARE_COLOR = new Color(181, 136, 99); 
    private static final Color LIGHT_SQUARE_COLOR = new Color(240, 217, 181); 
    private static final Color BORDER_COLOR = new Color(70, 70, 70);
    private static final Color CONTROL_PANEL_BG = new Color(245, 245, 245);
    private static final Color BUTTON_COLOR = new Color(60, 130, 200);
  
    private static final Color SELECTED_SQUARE_COLOR = new Color(133, 193, 233);
    private static final Color POSSIBLE_MOVE_COLOR = new Color(255, 102, 102, 180);
	public GameWindow() {

		this.board = new Board();

        setTitle("CỜ VUA");
        setSize(1000, 700); 
        setMinimumSize(new Dimension(850, 650));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 0)); 

        
        JPanel mainBoardContainer = new JPanel(new BorderLayout(5, 5));
        mainBoardContainer.setBackground(LIGHT_SQUARE_COLOR); 

     
        JPanel boardPanel = new JPanel(new GridLayout(8, 8));
        
        for (int r = 7; r >= 0; r--) { 
            for (int c = 0; c < 8; c++) { 
                JButton square = new JButton();
              
                if ((r + c) % 2 == 0) {
                    square.setBackground(LIGHT_SQUARE_COLOR); 
                } else {
                    square.setBackground(DARK_SQUARE_COLOR);
                }
                
                square.setPreferredSize(new Dimension(75, 75));
                square.setBorderPainted(false); 
                square.setFocusPainted(false);
                
               
                chessSquares[r][c] = square;
                boardPanel.add(square);
            }
        }
        
        boardPanel.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 3));


        // --- THÊM KÝ HIỆU HÀNG (1-8) ---
        JPanel rowLabels = new JPanel(new GridLayout(8, 1));
        rowLabels.setBackground(CONTROL_PANEL_BG);
        rowLabels.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5)); 
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        
        for (int r = 8; r >= 1; r--) {
            JLabel label = new JLabel(String.valueOf(r), JLabel.CENTER);
            label.setFont(labelFont);
            label.setForeground(BORDER_COLOR);
            rowLabels.add(label);
        }

        // --- THÊM KÝ HIỆU CỘT (a-h) ---
        JPanel colLabels = new JPanel(new GridLayout(1, 8));
        colLabels.setBackground(CONTROL_PANEL_BG);
        colLabels.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0)); 
        
        for (char c = 'a'; c <= 'h'; c++) {
            JLabel label = new JLabel(String.valueOf(c), JLabel.CENTER);
            label.setFont(labelFont);
            label.setForeground(BORDER_COLOR);
            colLabels.add(label);
        }
      
        mainBoardContainer.add(rowLabels, BorderLayout.WEST);
        mainBoardContainer.add(boardPanel, BorderLayout.CENTER);
        mainBoardContainer.add(colLabels, BorderLayout.SOUTH);
  
        JPanel centerWrapper = new JPanel();
        centerWrapper.setBackground(CONTROL_PANEL_BG);
        centerWrapper.add(mainBoardContainer);
        add(centerWrapper, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout((LayoutManager) new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(CONTROL_PANEL_BG);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 20)); 
        rightPanel.setPreferredSize(new Dimension(220, rightPanel.getPreferredSize().height));

    
        JLabel titleLabel = new JLabel("CHỨC NĂNG");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        rightPanel.add(titleLabel);
        rightPanel.add(Box.createVerticalStrut(30)); 

       
        String[] buttonNames = {"Chơi Game Mới", "Quay lại (Undo)", "Hướng Dẫn Chơi", "Thoát Game"};
        Font buttonFont = new Font("Arial", Font.PLAIN, 16);
        
        for (String name : buttonNames) {
            JButton btn = new JButton(name);
            btn.setFont(buttonFont);
            btn.setPreferredSize(new Dimension(190, 45));
            btn.setMaximumSize(new Dimension(190, 45));
            btn.setBackground(BUTTON_COLOR);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(BUTTON_COLOR.darker(), 1)); 
            btn.setAlignmentX(CENTER_ALIGNMENT);
            
      
            if (name.equals("Thoát Game")) {
                this.exitButton = btn; 
            }
            else if (name.equals("Hướng Dẫn Chơi")) {
                this.guideButton = btn; 
            }
            else if (name.equals("Chơi Game Mới")) { 
                this.newGameButton = btn; 
            }

            rightPanel.add(btn);
            rightPanel.add(Box.createVerticalStrut(15));
        }
	        add(rightPanel, BorderLayout.EAST);
        
   
        updateBoardGUI(); 
        new controller.GameController(this.board, this, this.chessSquares,this.exitButton,this.guideButton,this.newGameButton);
        setVisible(true);
	}

	public void updateBoardGUI() {
	    for (int r = 0; r < 8; r++) {
	        for (int c = 0; c < 8; c++) {
	            // ... (Phần logic tìm Piece và đặt Icon/Text giữ nguyên) ...
	            Position pos = new Position(r, c);
	            Piece piece = board.get(pos);
	            JButton square = chessSquares[r][c];

	            if (piece != null) {
	                // ... logic tải và đặt Icon/Text ...
	                String colorPrefix = (piece.getColor() == Color.WHITE) ? "w" : "b";
	                String pieceName = String.valueOf(piece.getShortName()).toUpperCase();
	                String imagePath = "img/" + colorPrefix + pieceName + ".png";
	                
	                    // Cố gắng tải ảnh
	                    ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(imagePath));
	                    square.setIcon(icon);
	              
	            } else {
	                // Nếu ô trống
	                square.setIcon(null);
	                square.setText(null);
	            }
	            
	            // 💡 QUAN TRỌNG: Ép buộc ô cờ vẽ lại
	            square.repaint(); 
	        }
	    }
	    
	    // Yêu cầu cửa sổ chính tính toán lại bố cục
	    revalidate(); 
	}

	public void resetHighlights() {
	    // Đặt lại màu nền và đường viền của tất cả các ô về trạng thái ban đầu
	    for (int r = 0; r < 8; r++) {
	        for (int c = 0; c < 8; c++) {
	            chessSquares[r][c].setBackground(getSquareOriginalColor(r, c));
	            chessSquares[r][c].setBorderPainted(false); // Đặt lại về không có viền
	        }
	    }
	}

	private Color getSquareOriginalColor(int r, int c) {
	    return (r + c) % 2 == 0 ? LIGHT_SQUARE_COLOR : DARK_SQUARE_COLOR;
	}
	public void highlightSquares(Position selected, List<Move> moves) {
		updateBoardGUI();
	    resetHighlights(); // Đảm bảo xóa nổi bật cũ

	    // 1. Làm nổi bật ô đang chọn
	    chessSquares[selected.getR()][selected.getC()].setBackground(SELECTED_SQUARE_COLOR);

	    // 2. Làm nổi bật các ô đích khả thi
	    for (Move move : moves) {
	        Position target = move.getEnd();
	        JButton targetSquare = chessSquares[target.getR()][target.getC()];


	        // Nếu ô đó trống, tô màu cho ô đó
	        if (board.get(target) == null) {
	            targetSquare.setBackground(POSSIBLE_MOVE_COLOR);
	        } 
	        // Nếu ô đó có quân để ăn, có thể thay đổi đường viền hoặc dùng màu khác 
	        // Ở đây ta sẽ làm đường viền nổi bật (cần setBorderPainted(true) trên nút)
	        else {
	             targetSquare.setBorder(BorderFactory.createLineBorder(POSSIBLE_MOVE_COLOR.darker(), 3));
	             targetSquare.setBorderPainted(true);
	        }
	    }
	}
	public void setBoard(Board board) {
	    this.board = board;
	}
}