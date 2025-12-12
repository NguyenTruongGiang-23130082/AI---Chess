package model;

import java.awt.Color;
import java.util.List;

// Giả định PieceFactory đã tồn tại
// import model.PieceFactory; 

public class Board {
	private Piece[][] grid = new Piece[8][8];
	private Color currentTurn;
	public Move lastMove = null;
	private boolean isGameOver = false;

	// Constructor Mặc định (Khởi tạo game)
	public Board() {
		init();
		this.currentTurn = Color.WHITE;
	}

	public Board(Board other) {
		this.currentTurn = other.currentTurn;
		this.lastMove = other.lastMove;
		this.isGameOver = other.isGameOver;

		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				Piece originalPiece = other.grid[r][c];

				if (originalPiece != null) {
					// Đảm bảo PieceFactory đã tồn tại!
					this.grid[r][c] = PieceFactory.createCopy(originalPiece);
				} else {
					this.grid[r][c] = null;
				}
			}
		}
	}

	public Color getCurrentTurn() {
		return this.currentTurn;
	}

	public void setCurrentTurn(Color color) {
		this.currentTurn = color;
	}

	public Piece get(Position pos) {
		return grid[pos.getR()][pos.getC()];
	}

	private void switchTurn() {
		this.currentTurn = (this.currentTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	// *** HÀM MOVE ***
	public void move(Move move) {
		Position start = move.getStart();
		Position end = move.getEnd();

		Piece pieceToMove = get(start);
		if (pieceToMove == null)
			return;

		// 1. Cập nhật trạng thái đã di chuyển của King và Rook
		if (pieceToMove instanceof King) {
			((King) pieceToMove).setHasMoved(true);
		} else if (pieceToMove instanceof Rook) {
			((Rook) pieceToMove).setHasMoved(true);
		}

		// 2. Thực hiện di chuyển quân
		grid[end.getR()][end.getC()] = pieceToMove;
		grid[start.getR()][start.getC()] = null;

		// 3. Xử lý các luật đặc biệt
		handleSpecialMoves(move);

		// 4. Lưu nước đi
		this.lastMove = move;

		// 5. Đổi lượt
		switchTurn();
	}

	private void handleSpecialMoves(Move move) {

		// Xử lý BẮT TỐT QUA ĐƯỜNG (EN_PASSANT)
		if (move.getSpecialType() == Move.SpecialType.EN_PASSANT) {
			// Xóa quân Tốt bị bắt (nằm ở hàng xuất phát, cột của ô đích)
			int capturedPawnRow = move.getStart().getR();
			int capturedPawnCol = move.getEnd().getC();
			grid[capturedPawnRow][capturedPawnCol] = null;
		}

		// Xử lý PHONG CẤP (PROMOTION)
		if (move.isPromotion()) { // Sử dụng hàm isPromotion()

			Color color = get(move.getEnd()).getColor();
			Piece newPiece = null;

			switch (move.getSpecialType()) {
			// Đảm bảo sử dụng các enum của Move
			case PROMOTION_QUEEN:
				newPiece = new Queen(color);
				break;
			case PROMOTION_ROOK:
				newPiece = new Rook(color);
				break;
			case PROMOTION_BISHOP:
				newPiece = new Bishop(color);
				break;
			case PROMOTION_KNIGHT:
				newPiece = new Knight(color);
				break;
			default:
				break;
			}
			if (newPiece != null) {
				grid[move.getEnd().getR()][move.getEnd().getC()] = newPiece;
			}
		}

		// Xử lý NHẬP THÀNH (CASTLING)
		if (move.getCastlingType() != Move.CastlingType.NONE) {
			int row = move.getStart().getR();

			if (move.getCastlingType() == Move.CastlingType.KINGSIDE) {
				// Di chuyển Xe từ cột 7 sang cột 5
				Piece rook = grid[row][7];
				grid[row][5] = rook;
				grid[row][7] = null;
			} else if (move.getCastlingType() == Move.CastlingType.QUEENSIDE) {
				// Di chuyển Xe từ cột 0 sang cột 3
				Piece rook = grid[row][0];
				grid[row][3] = rook;
				grid[row][0] = null;
			}
		}
	}

	public Move getLastMove() {
		return lastMove;
	}

	public void set(Position pos, Piece piece) {
		if (pos.isValid()) {
			grid[pos.getR()][pos.getC()] = piece;
		}
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	public void init() {
		// Khởi tạo các quân cờ
		grid[0][0] = new Rook(Color.WHITE);
		grid[0][1] = new Knight(Color.WHITE);
		grid[0][2] = new Bishop(Color.WHITE);
		grid[0][3] = new Queen(Color.WHITE);
		grid[0][4] = new King(Color.WHITE);
		grid[0][5] = new Bishop(Color.WHITE);
		grid[0][6] = new Knight(Color.WHITE);
		grid[0][7] = new Rook(Color.WHITE);
		for (int c = 0; c < 8; c++)
			grid[1][c] = new Pawn(Color.WHITE);

		grid[7][0] = new Rook(Color.BLACK);
		grid[7][1] = new Knight(Color.BLACK);
		grid[7][2] = new Bishop(Color.BLACK);
		grid[7][3] = new Queen(Color.BLACK);
		grid[7][4] = new King(Color.BLACK);
		grid[7][5] = new Bishop(Color.BLACK);
		grid[7][6] = new Knight(Color.BLACK);
		grid[7][7] = new Rook(Color.BLACK);
		for (int c = 0; c < 8; c++)
			grid[6][c] = new Pawn(Color.BLACK);
	}

	public void print() {
		for (int r = 7; r >= 0; r--) {
			System.out.print((r + 1) + " ");
			for (int c = 0; c < 8; c++) {
				Piece p = grid[r][c];
				System.out.print((p == null ? "." : p.getShortName()) + " ");
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	// *** KIỂM TRA Ô BỊ TẤN CÔNG (Dùng trong AI và kiểm tra chiếu) ***
	public boolean isSquareAttacked(Position pos, Color defendingColor) {
		Color opponentColor = (defendingColor == Color.WHITE) ? Color.BLACK : Color.WHITE;

		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				Piece piece = grid[r][c];

				if (piece != null && piece.getColor() == opponentColor) {
					Position currentPos = new Position(r, c);

					// Lấy các nước đi *tấn công* của quân đối thủ
					// Hàm getMoves() của Piece thường trả về nước đi không kiểm tra an toàn Vua
					List<Move> potentialAttacks = piece.getMoves(currentPos, this);

					for (Move move : potentialAttacks) {
						if (move.getEnd().equals(pos)) {
							return true;
						}
					}
				}
			}

		}
		return false;
	}

	// *** TÌM VỊ TRÍ VUA ***
	public Position findKingPosition(Color color) {
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				Piece piece = grid[r][c];
				if (piece instanceof model.King && piece.getColor() == color) {
					return new Position(r, c);
				}
			}
		}
		return null;
	}

}