package chess;
import java.util.Scanner;
public class Chess {
    /** GOALS
    * player moves piece(chess notation?)
    * checks valid moves
    * option to display captured pieces
    * make simpler prompt method used for every prompt
    **/
    static Scanner input = new Scanner(System.in);
    static char[][] board = createBoard();

    public static void main(String[] args) {
        int start = 0;
        while(start != 1) {
            start = intro();
        }
        System.out.println();
        printBoard(board);

        String currentPlayer = "White";
        for(int i = 0; i < 1000; i++) {
            currentPlayer = takeTurn(currentPlayer);
            checkWin();
        }    
        input.close();
    }

//STARTUP METHODS
    //intro method
    public static int intro() {
        switch (yesOrNo("Hello! Would you like to play a game of chess?")) {
            case 1 -> {
                System.out.println("Great! Starting game...");
                return 1;
            }
            case 0 -> {
                System.out.println(":(");
                System.exit(0);
                return 0;
            }
            default -> {
                return 0;
            }
        }
    }

    //create board method
    public static char[][] createBoard() {
        char[] pieces = {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'};
        char[][] playingBoard = new char[8][8];
    
        for(int i = 0; i < 8; i++) {
            playingBoard[1][i] = 'p';
            playingBoard[6][i] = 'P'; 
        }
         
        for(int i = 0; i < 8; i++) {
            playingBoard[0][i] = pieces[i];
            playingBoard[7][i] = Character.toUpperCase(pieces[i]);
        }
            
        for(int i = 2; i <= 5; i++) {
            for(int j = 0; j < 8; j++) {
                playingBoard[i][j] = '.';
            }
        }
        return playingBoard;
    }

//PLAYING METHODS

    //take turn method
    public static String takeTurn(String player) {
        boolean completedTurn = false;
        do {
            System.out.println("What would " + player + " like to do?");
            String menuSelection = question("[Move/Resign]");

            if(menuSelection.toUpperCase().equals("MOVE")) {
                String pieceSelection = question("Which piece?");
                char piece = selectPiece(pieceSelection);
                String placeSelection = question("To where?");
                placePiece(placeSelection, piece);
                printBoard(board);
                completedTurn = true;

            } else if (menuSelection.toUpperCase().equals("RESIGN")) {
                completedTurn = resignation(player);
            }
        } while(!completedTurn);

        if(player.equals("White")) {
            player = "Black";
        } else {
            player = "White";
        }
        return player;
    }

    //selects a piece method
    public static char selectPiece(String square) {
        String c = square.substring(0,1);
        String r = square.substring(1,2);

        int col = c.toLowerCase().charAt(0) - 'a';
        int row = Integer.parseInt(r) - 1;

        char selectedPiece = board[row][col];
        board[row][col] = '.';
        return selectedPiece;
        
    }

    //places a piece method
    public static void placePiece(String square, char piece) {
        String c = square.substring(0,1);
        String r = square.substring(1,2);

        int col = c.toLowerCase().charAt(0) - 'a';
        int row = Integer.parseInt(r) - 1;

        board[row][col] = piece;
    }

    //resignation method
    public static boolean resignation(String player) {
        if(yesOrNo("Are you sure?") == 1) {
            if(player.equals("White")) {
                System.out.println("Congratulations Black! You win!");
            } else {
                System.out.println("Congratulations White! You win!");
            }
            System.exit(0);
            return true;
        }
        return false;
    }

    //check if kings are missing method 
    public static void checkWin() {
        boolean blackAlive = false;
        boolean whiteAlive = false;
        for (char[] row : board) {
            for (int i = 0; i < row.length; i++) {
                if(row[i] == 'k') {
                    blackAlive = true;
                } else if(row[i] == 'K') {
                    whiteAlive = true;
                }
            }
            
        }
        if(!blackAlive) {
            System.out.println("Congratulations White! You win!");
            System.exit(0);
        }
        if(!whiteAlive) {
            System.out.println("Congratulations Black! You win!");
            System.exit(0);
        }

    }

//PRINTING METHODS

    //print question method
    public static String question(String question) {
        System.out.println(question);
        String output = input.nextLine();
        System.out.println();
        return output;
    }

    //yes or no method
    @SuppressWarnings("ConvertToStringSwitch")

    public static int yesOrNo(String question) {
        System.out.println(question);
        System.out.println("[Y/N]");
        String yesOrNo = input.nextLine();
        System.out.println();
        if(yesOrNo.toUpperCase().equals("Y") || yesOrNo.toUpperCase().equals("YES")) {
            return 1;
        } else if(yesOrNo.toUpperCase().equals("N") || yesOrNo.toUpperCase().equals("NO")) {
            return 0;
        } else {
            System.out.println("Error - Invalid Answer");
            System.out.println();
            return -1;
        }
        
    }

    //print board method
    public static void printBoard(char[][] board) {
        for (char[] board1 : board) {
            for (int j = 0; j < board1.length; j++) {
                System.out.print(board1[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

//OLD METHODS

//create chessboard method
    public static char[][] createBoardOld() {
        char[][] playingBoard = new char[8][8];
        boolean white = false;
        for (char[] board1 : board) {
            white = !white;
            for (int k = 0; k < board1.length; k++) {
                if (white) {
                    board1[k] = 'W';
                    white = false;
                } else {
                    board1[k] = 'B';
                    white = true;
                }
            }
        }
        return playingBoard;
    }
}
