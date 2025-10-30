import java.util.Scanner;
public class Chess {
    /** GOALS
    * chess notation -> library full of moves?
    * checks valid moves
    * option to display captured pieces
    * make simpler prompt method used for every prompt
    * remove system.exits
    * change array length to 8, format the print board properly
    **/
    static Scanner input = new Scanner(System.in); //Scanner
    static String[][] board = createVisualBoard(); //Chessboard

    public static void main(String[] args) { //Main
        int start = 0;
        while(start != 1) {
            start = intro();
        }
        System.out.println();
        Util.printBoard(board);

        String currentPlayer = "White";
        for(int i = 0; i < 1000; i++) {
            currentPlayer = takeTurn(currentPlayer);
            WinConditions.checkWin();
        }    
        input.close();
    }

//STARTUP METHODS
    //Intro
    public static int intro() {
        switch (Util.yesOrNo("Hello! Would you like to play a game of chess?")) {
            case 1 -> { //Selects 'Y'
                System.out.println("Great! Starting game...");
                return 1;
            }
            case 0 -> { //Selects 'N'
                System.out.println(":(");
                System.exit(0);
                return 0;
            }
            default -> {
                return 0;
            }
        }
    }

    //Create Board
    public static String[][] createVisualBoard() {
        String[][] playingBoard = new String[8][8]; //initialize and declare chessboard
        for (String[] matrix : playingBoard) {
            for (int j = 0; j < playingBoard.length; j++) {
                matrix[j] = " ";
            }
        }

        String[] pieces = {"r", "n", "b", "q", "k", "b", "n", "r"};
        //String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h"};
        //String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8"};

        for(int i = 0; i < 8; i++) { // initialize pawns
            playingBoard[1][i] = "p";
            playingBoard[6][i] = "P"; 
        }
         
        for(int i = 0; i < 8; i++) { //initialize row and column labels, and chess pieces
            playingBoard[0][i] = pieces[i];
            playingBoard[7][i] = pieces[i].toUpperCase();
        }
            
        for(int i = 2; i <= 5; i++) { // fill chessboard with '.' for visuals
            for(int j = 0; j < 8; j++) {
                playingBoard[i][j] = ".";
            }
        }
        return playingBoard;
    }


//PLAYING METHODS

    //take turn method
    //simplify
    public static String takeTurn(String player) {
        boolean completedTurn = false;
        do {
            System.out.println("What would " + player + " like to do?");
            String menuSelection = Util.question("[Move/Resign]");

            if(menuSelection.toUpperCase().equals("MOVE")) {
                String pieceSelection = Util.question("Which piece?");
                String[] pieceData = Move.selectPiece(pieceSelection);
                String placeSelection = Util.question("To where?");
                Move.placePiece(placeSelection, pieceData);
                Util.printBoard(board);
                completedTurn = true;

            } else if (menuSelection.toUpperCase().equals("RESIGN")) {
                completedTurn = WinConditions.resignation(player);
            }
        } while(!completedTurn);

        if(player.equals("White")) {
            player = "Black";
        } else {
            player = "White";
        }
        return player;

    }
}
