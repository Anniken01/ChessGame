package CSCI1933P2;
//Written by Laura Stephenson and Anniken Rabben

import java.util.Scanner;
public class Pawn extends Piece {

    public Pawn(int row, int col, boolean isBlack) {
        super.row = row;

        super.col = col;

        super.isBlack = isBlack;

        if (isBlack){
            //Black Pawn
            super.representation = '\u265F';
        }
        else{
            // White Pawn
            super.representation = '\u2659';
        }
    }


    public void promotePawn(Board board, int row, int col, boolean isBlack) {
            Scanner scanner1  = new Scanner(System.in);
            System.out.println("Time to promote your pawn! Pick: queen, rook, bishop or knight");
            String userChoice = scanner1.next().trim(); //Using .trim(), and .toLowerCase()

            //Source two used below:
            Piece newPiece = null;
            switch (userChoice) {
                case "queen":
                    newPiece = new Queen(row, col, isBlack);
                    break;
                case "rook":
                    newPiece = new Rook(row, col, isBlack);
                    break;
                case "bishop":
                    newPiece = new Bishop(row, col, isBlack);
                    break;
                case "knight":
                    newPiece = new Knight(row, col, isBlack);
                    break;
            }
            board.setPiece(row, col, newPiece);
        }


    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifyVertical(row, col, endRow, endCol) && board.getPiece(endRow, endCol) == null) {
            // Case 1: Forward movement to empty square.
            // Determine if the distance being moved is valid.
            if (this.isBlack) {
                return (endRow == this.row + 1) || ((endRow == this.row + 2) && (this.row == 1));
            } else {
                return (endRow == this.row - 1) || ((endRow == this.row - 2) && (this.row == 6));
            }
        }

        else if (this.col == endCol+1 || this.col == endCol-1) {
            // Case 2: Capturing a piece.
            if (board.getPiece(endRow, endCol) != null && board.getPiece(endRow, endCol).getIsBlack() != this.isBlack) {
                // There is a piece of the opposite color to be captured.
                if (this.isBlack) {
                    return (endRow == this.row + 1);
                } else {
                    return (endRow == this.row - 1);
                }
            } else {
                return false;
            }
        }

        else {
            // Case 3: Moving in a non-adjacent column. (illegal move)
            return false;
        }
    }


}