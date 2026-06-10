package CSCI1933P2;
//Written by Laura Stephenson and Anniken Rabben

//can move vertical, horizontal, or diagonal, will have to check all
public class Queen extends Piece {

    public Queen(int row, int col, boolean isBlack) {
        super.row = row;
        super.col = col;
        super.isBlack = isBlack;

        if (isBlack) {
            super.representation = '\u265b';
        }
        else {
            super.representation = '\u2655';
        }
    }
    /*
    Method to check that Queen's move is legal, since they move in a straight line, horizontally,
    vertically, or diagonally
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        // The move is horizontal or vertical
        if (row == endRow || col == endCol) {
            // check if there is other pieces between
            if (board.verifyHorizontal(this.row, this.col, endRow, endCol) || board.verifyVertical(this.row, this.col, endRow, endCol)) {
                // check if the endposition is empty
                Piece endPiece = board.getPiece(endRow, endCol);
                // clear endPiece and switch the color so it´s the others turn
                return endPiece == null || endPiece.getIsBlack() != isBlack;
            }
        }
        // Move is diagonal
        if (board.verifyDiagonal(this.row, this.col, endRow, endCol)) {
            // check if the endposition is empty
            Piece endPiece = board.getPiece(endRow, endCol);
            // clear endPiece and switch the color so it´s the others turn
            return endPiece == null || endPiece.getIsBlack() != isBlack;
        }
        return false;
    }
}
