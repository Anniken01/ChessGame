package CSCI1933P2;
//Written by Laura Stephenson and Anniken Rabben

public class Knight extends Piece{

    public Knight(int row, int col, boolean isBlack){
        super.row = row;
        super.col = col;
        super.isBlack = isBlack;

        if (isBlack){
            super.representation = '\u265e';
        }
        else{
            super.representation = '\u2658';
        }
    }

    //Method to check if choice is legal, knowing that Knights move in an 'L' shape,
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        int rowDiff = Math.abs(endRow-this.row);
        int colDiff = Math.abs(endCol-this.col);
        //if the user does not move their piece in an L shape, the move is not valid
        if (!((rowDiff == 2 && colDiff == 1) || (colDiff == 2 && rowDiff == 1)) ){
            return false;
        }
        // check if the endposition is empty
        Piece endPiece = board.getPiece(endRow, endCol);
        // clear endPiece and swith the color so it´s the others turn
        return endPiece == null || endPiece.getIsBlack() != isBlack;
    }
}
