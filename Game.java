package CSCI1933P2;
//Written by Laura Stephenson and Anniken Rabben
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Board myBoard = new Board();
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", myBoard);

        Scanner scanner = new Scanner(System.in);
        boolean whitesTurn = true;

        while (!myBoard.isGameOver()) {
            System.out.println(myBoard.toString());

            if (whitesTurn) {
                System.out.println("It's white's turn");
            } else {
                System.out.println("It's black's turn");
            }

            System.out.println("How do you want to move? Format like: [starting row],[starting column],[end row],[end column]");
            System.out.println("Type 'exit' to quit the program");
            String input = scanner.nextLine();
            if (input.equals("exit")){
                break;
            }
            String[] arr = input.split(",");

            int startRow = Integer.parseInt(arr[0]);
            int startCol = Integer.parseInt(arr[1]);
            int endRow = Integer.parseInt(arr[2]);
            int endCol = Integer.parseInt(arr[3]);

            boolean validMove = false;
            while (!validMove) {
                Piece piece = myBoard.getPiece(startRow, startCol);
                boolean promote = false;
                if (piece instanceof Pawn){
                    if ((!piece.getIsBlack() && endRow == 0) || (piece.getIsBlack() && endRow == 7)) {
                        ((Pawn) piece).promotePawn(myBoard, startRow, startCol, piece.getIsBlack());
                        piece = null;
                        promote=true;
                    }
                }

                if (myBoard.movePiece(startRow, startCol, endRow, endCol)) {
                    validMove = true;

                    // change player
                    whitesTurn = !whitesTurn;
                } else {
                    System.out.println("Illegal move, try entering different coordinates:");
                    System.out.println("Type 'exit' to quit the program");
                    if (input.equals("exit")){
                        break;
                    }
                    input = scanner.nextLine();
                    arr = input.split(",");
                    startRow = Integer.parseInt(arr[0]);
                    startCol = Integer.parseInt(arr[1]);
                    endRow = Integer.parseInt(arr[2]);
                    endCol = Integer.parseInt(arr[3]);
                }
            }
        }

        System.out.println("Game over!");
        scanner.close();
    }
}

