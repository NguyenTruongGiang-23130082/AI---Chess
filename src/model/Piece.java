package model;

import java.awt.Color;
import java.util.List;

public abstract class Piece {
	private Color color;
	private char shortName; 
	public Piece(Color color, char shortName){ 
		this.color=color; 
		this.shortName=shortName; 
		}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public char getShortName() {
		return shortName;
	}
	public void setShortName(char shortName) {
		this.shortName = shortName;
	}
	public abstract List<Move> getMoves(Position pos, Board board); 
	public boolean isOpposite(Piece p) { 
		return p!=null && p.color!=this.color;
		}
	
}
