package CSCI1933P2;
//Written by Laura Stephenson and Anniken Rabben

public abstract class Piece {

    protected int row;

    protected int col;

    protected boolean isBlack;

    protected char representation;

    public abstract boolean isMoveLegal(Board board, int endRow, int endCol);


    public void setPosition(int row, int col){
        this.row = row;
        this.col = col;
    }


    public int getRow(){
        return row;
    }


    public int getCol(){
        return col;
    }


    public boolean getIsBlack(){
        return isBlack;
    }


    public String toString() {
        return representation + "";
    }

}
