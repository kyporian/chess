public class Move {

//MOVE METHODS
    //Square Selector
    public static String[] selectSquare(String square) {
        String c = square.substring(0,1);
        String r = square.substring(1,2); //Gets row and column from input

        String col = String.valueOf(c.toLowerCase().charAt(0) - 'a');
        String row = String.valueOf(Integer.parseInt(r) - 1); //Converts to numerical value

        String[] position = {col, row}; //Creates array containing the data of the square
        return position;
    }

    //Piece Selector
    public static String[] selectPiece(String square) {
        String[] position = selectSquare(square); 
        int c = Integer.parseInt(position[0]);
        int r = Integer.parseInt(position[1]); //Gets selected square data

        String selectedPiece = String.valueOf(Chess.board[r][c]); //Gets game piece data from selected square
    

        String[] pieceData = {position[0], position[1], selectedPiece}; //Creates array containing data of the piece and square it's on
        return pieceData;
    }

    //Piece Placer
    public static void placePiece(String square, String[] pieceData) {
        String[] position = selectSquare(square);
        int c = Integer.parseInt(position[0]);
        int r = Integer.parseInt(position[1]); //Gets selected square data

        if(Validator.validMove(position, pieceData)) { //Validates move
            String piece = pieceData[2];
            Chess.board[r][c] = piece; //
            Chess.board[Integer.parseInt(pieceData[1])][Integer.parseInt(pieceData[0])] = "."; //Replaces old location with an empty tile
        } else {
            System.out.println("Skipped turn!");
            System.out.println();
        }

        //Move all this into if statement with validMove condition
        
    }
}
