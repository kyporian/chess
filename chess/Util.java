public class Util {

    //yes or no method
    @SuppressWarnings("ConvertToStringSwitch")

    public static int yesOrNo(String question) {
        System.out.println(question);
        System.out.println("[Y/N]");
        String yesOrNo = Chess.input.nextLine();
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


//PRINTING METHODS
    //print question method
    public static String question(String question) {
        System.out.println(question);
        String output = Chess.input.nextLine();
        System.out.println();
        return output;
    }

    //print board method
    public static void printBoard(String[][] board) {
        for (String[] board1 : board) {
            for (String board11 : board1) {
                System.out.print(board11 + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
