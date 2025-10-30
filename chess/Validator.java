public class Validator {
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

    


}
