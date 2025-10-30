import java.util.Scanner;
public class Chess {
    /** GOALS
    * player moves piece(chess notation?)
    * checks valid moves
    * option to display captured pieces
    * make simpler prompt method used for every prompt
    **/
    static Scanner input = new Scanner(System.in);
    static char[][] board = createVisualBoard();

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
    public static char[][] createVisualBoard() {
        char[] pieces = {' ', ' ', 'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'};
        char[] letters = {' ', ' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        char[] numbers = {' ', ' ', '1', '2', '3', '4', '5', '6', '7', '8'};
        char[][] playingBoard = new char[10][10];
    
        for(int i = 2; i < 10; i++) {
            playingBoard[3][i] = 'p';
            playingBoard[8][i] = 'P'; 
        }
         
        for(int i = 2; i < 10; i++) {
            playingBoard[i][0] = numbers[i];
            playingBoard[0][i] = letters[i];
            playingBoard[2][i] = pieces[i];
            playingBoard[9][i] = Character.toUpperCase(pieces[i]);
        }
            
        for(int i = 4; i <= 7; i++) {
            for(int j = 2; j < 10; j++) {
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
                String[] pieceData = selectPiece(pieceSelection);
                String placeSelection = question("To where?");
                placePiece(placeSelection, pieceData);
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

//MOVE METHODS

    }
    //square selector
    public static String[] selectSquare(String square) {
        String c = square.substring(0,1);
        String r = square.substring(1,2);

        String col = String.valueOf(c.toLowerCase().charAt(0) - 'a' + 2);
        String row = String.valueOf(Integer.parseInt(r) + 1);

        String[] position = {col, row};
        return position;
    }

    //selects a piece method
    public static String[] selectPiece(String square) {
        String[] position = selectSquare(square);
        int c = Integer.parseInt(position[0]);
        int r = Integer.parseInt(position[1]);

        String selectedPiece = String.valueOf(board[r][c]);
        

        String col = String.valueOf(c);
        String row = String.valueOf(r);
        String[] pieceData = {col, row, selectedPiece};

        board[r][c] = '.';
        return pieceData;
    }

    //places a piece method
    public static void placePiece(String square, String[] pieceData) {
        String[] position = selectSquare(square);
        int c = Integer.parseInt(position[0]);
        int r = Integer.parseInt(position[1]);

        validMove(position, pieceData);
        String piece = pieceData[2];
        board[r][c] = piece.charAt(0);
    }
    //need to return row and col, 

//VALIDATOR METHODS

   //distributor for different pieces
    public static boolean validMove(String[] position, String[] pieceData) {
        String type = pieceData[2];
        char piece = type.charAt(0);
        
        if (piece == 'p' || piece == 'P') {

            return validPawn(piece, pieceData);
        }
        
        return true;
    }

    public static boolean validPawn(char piece, String[] pieceData) {
        boolean valid = false;
        int c = Integer.parseInt(pieceData[0]);
        int r = Integer.parseInt(pieceData[1]);
        if (piece == 'P') {
            //String[] validMoves

            //valid positions are col -1 or 2)
            return valid;
        } else if (piece =='p') {
            //valid positions are col +1 or 2
            return valid;
        } else {
            System.out.print("System Error");
            return valid;
        }
        
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
        System.out.print(" ");
        for (char[] board1 : board) {
            for (int j = 0; j < board1.length; j++) {
                System.out.print(board1[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


//CONVERT ARRAY DATA METHODS
    //String to int
    public static int convertToInt(String input) {
        int value = Integer.parseInt(input);
        return value;
    }

    //String to char
    public static char convertToChar(String input) {
        char c = input.charAt(0);
        return c;
    }

    //String to bool
    public static boolean convertToBool(String input) {
        int value = Integer.parseInt(input);
        switch (value) {
            case 1 -> {
                return true;
            }
            case 0 -> {
                return false;
            }
            default -> {
                System.out.println("Error");
                return false;
            }
        }
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
