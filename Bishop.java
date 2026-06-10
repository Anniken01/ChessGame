package CSCI1933P2;
//Written by Laura Stephenson and Anniken Rabben

public class Bishop extends Piece {

    public Bishop(int row, int col, boolean isBlack){
        super.row = row;
        super.col = col;
        super.isBlack = isBlack;

        if (isBlack){
            super.representation = '\u265d';
        }
        else{
            super.representation = '\u2657';
        }
    }

    //Method to check that the move is valid, knowing that Bishops can only move diagonally
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        // check if the move is diagonal
        if (Math.abs(row - endRow) == Math.abs(col - endCol)) {
            // check if there is other pieces between
            if (board.verifyDiagonal(this.row, this.col, endRow, endCol)) {
                // check if the endposition is empty
                Piece endPiece = board.getPiece(endRow, endCol);
                // clear endPiece and switch the color so it´s the others turn
                return endPiece == null || endPiece.getIsBlack() != isBlack;
            }
        }
        return false;
    }
}
