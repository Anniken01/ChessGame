package CSCI1933P2;
//Written by Laura Stephenson and Anniken Rabben
public class Board {

    public Piece[][] board;


    public Board() {
        // initialize the board to chessboard dimensions.
        this.board =  new Piece[8][8];

    }


    public Piece getPiece(int row, int col) {
        return board[row][col];
    }


    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = piece;

    }

    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {

        if (startRow < 0 || startCol < 0 || endRow < 0 || endCol < 0 ||
                startRow >= 8 || startCol >= 8 || endRow >= 8 || endCol >= 8) {
            return false;
        }
        if (board[startRow][startCol] == null) {
            return false;
        }
        if (board[startRow][startCol].getIsBlack() != isBlack) {
            return false;
        }
        // end spot either empty or opponent is there
        if (board[endRow][endCol] == null || board[endRow][endCol].getIsBlack() != isBlack) {
            return true;
        }

        return false;
    }

    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {

        int diffCol = Math.abs(endCol - startCol);
        int diffRow = Math.abs(endRow - startRow);

        if (diffRow <= 1 && diffCol <= 1) {
            return true;
        }
        return false;
    }


    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow != endRow) {
            return false;
        }
        if (startCol <= endCol) {
            for (int i = startCol + 1; i < endCol; i++) {
                if (board[startRow][i] != null) {
                    return false;
                }
            }
            } else {
                for (int i = endCol + 1; i < startCol; i++) {
                    if (board[startRow][i] != null) {
                        return false;
                    }
                }
            }
        return true; //path is clear for the horizontal move
        }


    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if (startCol != endCol) {
            return false;
        }
        if (startRow < endRow) { // Move down
            for (int i = startRow + 1; i < endRow; i++) {
                if (board[i][startCol] != null) {
                    return false;
                }
            }
        } else { // Move up
            for (int i = startRow - 1; i > endRow; i--) {
                if (board[i][startCol] != null) {
                    return false;
                }
            }
        }
        return true;
    }


    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        int difRow = Math.abs(endRow - startRow);
        int difCol = Math.abs(endCol - startCol);

        if (difRow != difCol) {
            return false;
        }
        if ((startCol == endCol) && (startRow == endRow)){
            return true;
        }
        //up right direction
        if (endRow > startRow && endCol > startCol) {
            startRow++;
            startCol++;
            while (startRow < endRow && startCol < endCol) {
                if (this.board[startRow][startCol] != null) {
                    return false;
                }
                startRow++;
                startCol++;
            }
            return true;
        }
        //up left direction
        else if (endRow > startRow && endCol < startCol) {
            startRow++;
            startCol--;
            while (startRow < endRow && startCol > endCol) {
                if (this.board[startRow][startCol] != null) {
                    return false;
                }
                startRow++;
                startCol--;
            }
            return true;
        }

        //down right direction
        else if (endRow < startRow && endCol > startCol) {
            startRow--;
            startCol++;
            while (endRow < startRow && endCol > startCol) {
                if (this.board[startRow][startCol] != null) {
                    return false;
                }
                startRow--;
                startCol++;
            }
            return true;
        }

        //down left direction
        else if (endRow < startRow && endCol < startCol) {
            startRow--;
            startCol--;
            while (endRow < startRow && endCol < startCol) {
                if (this.board[startRow][startCol] != null) {
                    return false;

                }
                startRow--;
                startCol--;
            }
            return true;
        }
        return false;
    }


    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        Piece piece = board[startRow][startCol];

        if (piece == null) { // Ensure the starting position contains a piece
            return false;
        }

        if (!piece.isMoveLegal(this, endRow, endCol)) { // Ensure the move is legal for this piece
            return false;
        }

        piece.setPosition(endRow, endCol);
        board[endRow][endCol] = piece;
        board[startRow][startCol] = null;

        return true;
    }

    public boolean isGameOver() {
        int kingCount = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece != null) {
                    char pieceRep = piece.toString().charAt(0);

                    // check if there is a white or black king
                    if (pieceRep == '\u2654' || pieceRep == '\u265A') {
                        kingCount++;
                    }
                }
            }
        }
        if (kingCount <2){
            return true;
        }
        return false;
    }

    public void clear() {
        for (int i= 0; i<8; i++){
            for (int j=0; j<8; j++){
                board[i][j] = null;
            }
        }
    }

    public void display() {
        System.out.print("\t\t\t");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + "\t\t");
        }
        System.out.print("\n");
        for (int i = 0; i < 8; i++) {
            System.out.print("\t" + i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print("|\t\t");
                } else {
                    System.out.print("|\t" + board[i][j] + "\t");
                }
            }
            System.out.print("|\n");
        }
    }

    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for(int i = 0; i < 8; i++){
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for(int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for(int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }
}