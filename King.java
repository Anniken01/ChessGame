package CSCI1933P2;
//Written by Laura Stephenson and Anniken Rabben

public class King extends Piece {

    public King(int row, int col, boolean isBlack){
        super.row = row;
        super.col = col;
        super.isBlack = isBlack;

        if (isBlack){
            super.representation = '\u265a';
        }
        else{
            super.representation = '\u2654';
        }
    }
    /*
    Checks that the kings moves only one square in any direction
    (Horizontal, vertical, or diagonal)
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifyAdjacent(this.row, this.col, endRow, endCol)) {
            return true; }
        return false;

    }
}
