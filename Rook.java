package CSCI1933P2;

public class Rook extends Piece {

    public Rook(int row, int col, boolean isBlack) {
        super.row = row;
        super.col = col;
        super.isBlack = isBlack;

        if (isBlack) {
            super.representation = '\u265C'; //black rook
        } else {
            super.representation = '\u2656'; //white rook
        }
    }


    //Method to check if move is horizontally or vertically
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        // check if the move is horizontal or vertical
        if (this.row == endRow || this.col == endCol) {
            boolean pathClear = (this.row == endRow && board.verifyHorizontal(this.row, this.col, endRow, endCol)) ||
                    (this.col == endCol && board.verifyVertical(this.row, this.col, endRow, endCol));
            if (pathClear) {
                Piece endPiece = board.getPiece(endRow, endCol);
                return endPiece == null || endPiece.getIsBlack() != isBlack;
            }
        }
        return false;
    }
}
