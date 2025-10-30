public class WinConditions {
    public static boolean resignation(String player) {
        if(Util.yesOrNo("Are you sure?") == 1) {
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
        for (String[] row : Chess.board) {
            for (String row1 : row) {
                if ("k".equals(row1)) {
                    blackAlive = true;
                } else if ("K".equals(row1)) {
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
}
