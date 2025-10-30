public class Validator {
//VALIDATOR METHODS

   //distributor for different pieces
    public static boolean validMove(String[] position, String[] pieceData) {
        String type = pieceData[2];
        char piece = type.charAt(0);
        
        if (piece == 'p' || piece == 'P') {

            return validPawn(pieceData, position);
        }
        if (piece == 'r' || piece == 'R') {

            return validRook(pieceData, position);
        }
        return true;
    }

    public static boolean validRook(String[] pieceData, String[] position) {
        int currentColumn = Integer.parseInt(pieceData[0]);
        int currentRow = Integer.parseInt(pieceData[1]);
        String currentPosition = String.valueOf(currentColumn) + String.valueOf(currentRow);
        String desiredPosition = position[0] + position[1];

        String[] validMoves = new String[14];
    
        for(int i = 0; i < 8; i++) {
            if(!(currentPosition.equals(String.valueOf(currentColumn) + String.valueOf(i)))) {
                validMoves[i] = String.valueOf(currentColumn) + String.valueOf(i);
            }
            if(!(currentPosition.equals(String.valueOf(i) + String.valueOf(currentRow)))) {
                validMoves[i+6] = String.valueOf(i) + String.valueOf(currentRow);
            } 
        }
        for (String i : validMoves) {
            if (desiredPosition.equals(i)) {
                return true;
            }   
        }
        return false;
    }

    public static boolean validBishop(String[] pieceData, String[] position) {
        int currentColumn = Integer.parseInt(pieceData[0]);
        int currentRow = Integer.parseInt(pieceData[1]);
        String currentPosition = String.valueOf(currentColumn) + String.valueOf(currentRow);
        String desiredPosition = position[0] + position[1];

        String[] validMoves = new String[14];
    
        for(int i = 0; i < 8; i++) {
            if(!(currentPosition.equals(String.valueOf(currentColumn) + String.valueOf(i)))) {
                validMoves[i] = String.valueOf(currentColumn) + String.valueOf(i);
            }
            if(!(currentPosition.equals(String.valueOf(i) + String.valueOf(currentRow)))) {
                validMoves[i+6] = String.valueOf(i) + String.valueOf(currentRow);
            } 
        }
        for (String i : validMoves) {
            if (desiredPosition.equals(i)) {
                return true;
            }   
        }
        return false;

        //if c8: d7 e6 f5 g4 h3 and b7, a6
    }


        




    public static boolean validPawn(String[] pieceData, String[] position) {
        boolean valid = true;
        int currentColumn = Integer.parseInt(pieceData[0]);
        int currentRow = Integer.parseInt(pieceData[1]);
        if (pieceData[2].equals("P")) {
            String[] validMoves = {String.valueOf(currentRow -1), String.valueOf(currentRow -2), String.valueOf(currentColumn)};
                if(validMoves[0].equals(position[1]) && validMoves[2].equals(position[0]) || validMoves[1].equals(position[1]) && validMoves[2].equals(position[0])) {
                    return valid;
                }          
        } else if (pieceData[2].equals("p")) {
            String[] validMoves = { String.valueOf(currentRow +1), String.valueOf(currentRow +2), String.valueOf(currentColumn)};
                if(validMoves[0].equals(position[1]) && validMoves[2].equals(position[0]) || validMoves[1].equals(position[1]) && validMoves[2].equals(position[0])) {
                    return valid;
                }
        } else {
            System.out.print("System Error");
        }
        return !valid;
    }

    
}
