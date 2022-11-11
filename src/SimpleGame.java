

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class SimpleGame {

    //colored fonts
    public static final String blackColor = "\033[1;30m";  // BLACK
    public static final String redColor = "\033[1;31m";    // RED
    public static final String greenColor = "\033[1;32m";  // GREEN
    public static final String yellowColor = "\033[1;33m"; // YELLOW
    public static final String blueColor = "\033[1;34m";   // BLUE
    public static final String purpleColor = "\033[1;35m"; // PURPLE
    public static final String cyanColor = "\033[1;36m";   // CYAN
    public static final String whiteColor = "\033[1;37m";  // WHITE


    //colored Backgrounds
    public static final String blackBG = "\u001B[40m";
    public static final String redBG = "\u001B[41m";
    public static final String greenBG = "\u001B[42m";
    public static final String yelowBG = "\u001B[43m";
    public static final String blueBG = "\u001B[44m";
    public static final String purpleBG = "\u001B[45m";
    public static final String cyanBG = "\033[46m";
    public static final String whiteBG = "\u001B[47m";

    //reset String to default color or background
    public static final String reset = "\u001B[0m";

    //Scanner (Users Input)
    public static Scanner sc = new Scanner(System.in);

    //arrays, it is public because I will use it in (almost) every method
    public static String[][] cards = new String[4][4];
    public static String[][] board = new String[4][4];

    //This method prints "default" board that is shown at every round
    public static void printHiddenBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j]);
                System.out.print(blackColor + "|");
            }
            System.out.println();
        }
    }

    //this method will shuffle cards
    public static void shuffleCards() {
        Random rand = new Random();
        ArrayList<String> symbols = new ArrayList<>();
        int indexOf;

        //array of symbols
        //1
        symbols.add(whiteBG + blackColor + ":D" + reset);
        symbols.add(whiteBG + blackColor + ":D" + reset);
        //2
        symbols.add(blueBG + redColor + ":(" + reset);
        symbols.add(blueBG + redColor + ":(" + reset);
        //3
        symbols.add(blackBG + yellowColor + "XD" + reset);
        symbols.add(blackBG + yellowColor + "XD" + reset);
        //4
        symbols.add(redBG + greenColor + ":O" + reset);
        symbols.add(redBG + greenColor + ":O" + reset);
        //5
        symbols.add(purpleBG + blueColor + ":P" + reset);
        symbols.add(purpleBG + blueColor + ":P" + reset);
        //6
        symbols.add(yelowBG + cyanColor + ":)" + reset);
        symbols.add(yelowBG + cyanColor + ":)" + reset);
        //7
        symbols.add(blackBG + whiteColor + ":/" + reset);
        symbols.add(blackBG + whiteColor + ":/" + reset);
        //8
        symbols.add(cyanBG + purpleColor + ":|" + reset);
        symbols.add(cyanBG + purpleColor + ":|" + reset);

        for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < cards.length; j++) {
                indexOf = rand.nextInt(symbols.size());
                cards[i][j] = symbols.get(indexOf);
            }
        }
    }

    public static void input(String[][] cards) {


        while (true) {

            if (!gameOver()) {

                System.out.println("take number between 1 - 4 ");
                int firstPick = sc.nextInt();
                System.out.println("take number between 1 - 4 again ");
                int secondPick = sc.nextInt();


                //first guess

                if (!board[firstPick - 1][secondPick - 1].equals(" __ ")) {
                    System.out.println("Already selected");
                    System.out.println();
                    printHiddenBoard();
                    continue;
                } else {
                    board[firstPick - 1][secondPick - 1] = " " + cards[firstPick - 1][secondPick - 1] + " ";
                    printHiddenBoard();
                }


                //second guess
                System.out.println("take number between 1 - 4 ");
                int firstPick2 = sc.nextInt();
                System.out.println("take number between 1 - 4 again ");
                int secondPick2 = sc.nextInt();


                if (!board[firstPick2 - 1][secondPick2 - 1].equals(" __ ")) {
                    System.out.println("Already selected");
                    System.out.println();
                    printHiddenBoard();
                } else {
                    board[firstPick2 - 1][secondPick2 - 1] = " " + cards[firstPick2 - 1][secondPick2 - 1] + " ";


                    //comparing between first board + cards and second board + cards.

                    if (board[firstPick - 1][secondPick - 1].equals(board[firstPick2 - 1][secondPick2 - 1])) {

                        System.out.println("Lucky!");
                        printHiddenBoard();
                    } else {
                        System.out.println("false");
                        board[firstPick - 1][secondPick - 1] = " __ ";
                        board[firstPick2 - 1][secondPick2 - 1] = " __ ";
                        printHiddenBoard();


                    }
                }
            } else {
                System.out.println("GameOver");
                break;
            }


        }
    }

    public static boolean gameOver() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].equals(" __ ")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println(whiteColor + "type " + greenColor + " 'start '" + whiteColor + " to start new game " + redColor + " 'ff' " + whiteColor + " to forfeit" + reset);
            String startOrForfeit = sc.nextLine();
            if (startOrForfeit.toLowerCase(Locale.ROOT).equals("start")) {

                shuffleCards();
                for (int i = 0; i < cards.length; i++) {
                    for (int j = 0; j < cards.length; j++) {
                        board[i][j] = " __ ";
                    }
                }
                printHiddenBoard();
                input(cards);
                break;
            } else if (startOrForfeit.toLowerCase(Locale.ROOT).equals("ff")) {
                break;
            }
        }
    }
}
