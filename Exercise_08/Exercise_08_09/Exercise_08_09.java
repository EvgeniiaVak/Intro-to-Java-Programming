/*********************************************************************************
* (Game: play a tic-tac-toe game) In a game of tic-tac-toe, two players take     *
* turns marking an available cell in a 3 * 3 grid with their respective tokens   *
* (either X or O). When one player has placed three tokens in a horizontal,      *
* vertical, or diagonal row on the grid, the game is over and that player has    *
* won. A draw (no winner) occurs when all the cells on the grid have been filled *
* with tokens and neither player has achieved a win. Create a program for        *
* playing tic-tac-toe. The program prompts two players to enter an X token       *
* and O token alternately. Whenever a token is entered, the program redisplays   *
* the board on the console and determines the status of the game (win, draw, or  *
* continue).                                                                     *
*********************************************************************************/
import java.util.Scanner;

public class Exercise_08_09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean win = false;
        char[][] checkedPlaces = new char[3][3];
        for (int i = 0; i < checkedPlaces.length; i++) {
            for (int j = 0; j < checkedPlaces[i].length; j++) {
                checkedPlaces[i][j] = ' ';
            }
        }

        // draw the board
        drawBoard(checkedPlaces);

        int counter = 0;
        while (!win) {
            char player = ' ';
            if (counter % 2 == 0)
                player = 'X';
            else
                player = 'O';
            // ask for input from X or O
            int[] coords = promptForPlacement(scanner, checkedPlaces, player);
            
            //redraw the board with new data
            updateBoard(checkedPlaces, coords, player);
            drawBoard(checkedPlaces);

            // repeat if nobody win
            char winner = lookForWinner(checkedPlaces, player);
            win = winner != ' ';

            if (win) {
                System.out.printf("%c player won", player);
            }

            System.out.println("\n");
            counter++;
        }

    }

    private static char lookForWinner(char[][] checkedPlaces, char player) {
        int boardSize = checkedPlaces.length;
        char nobody = ' ';

        //check horizontally
        for (int row = 0; row < boardSize; row++) {
            if (hasThree(checkedPlaces[row], player))
                return player;
        }

        //check vertically
        for (int i = 0; i < boardSize; i++) {
            char[] column = new char[boardSize];

            for (int row = 0; row < boardSize; row++) {
                column[row] = checkedPlaces[row][i];
            }

            if (hasThree(column, player))
                return player;
        }

        //check diagonally
        char[] diagonal1 = new char[boardSize];
        for (int i = 0; i < boardSize; i++) {
            diagonal1[i] = checkedPlaces[i][i];
        }
        if (hasThree(diagonal1, player))
            return player;

        char[] diagonal2 = new char[boardSize];
        for (int i = 0; i < boardSize; i++) {
            diagonal2[i] = checkedPlaces[i][2 - i];
        }
        if (hasThree(diagonal2, player))
            return player;

        return nobody;
    }

    private static boolean hasThree(char[] places, char player) {
        for (int i = 0; i < places.length; i++) {
            if (places[i] != player)
                return false;
        }
        return true;
    }

    private static void updateBoard(char[][] checkedPlaces, int[] coords, char player) {
        for (int i = 0; i < checkedPlaces.length; i++) {
            for (int j = 0; j < checkedPlaces[i].length; j++) {
                if (i == coords[0] && j == coords[1])
                    checkedPlaces[i][j] = player;
            }
        }
    }


    private static int[] promptForPlacement(Scanner scanner,char[][] checkedPlaces, char player) {
        int[] coords = new int[2];

        while (true){
            System.out.printf("Enter a row (0, 1, or 2) for player %c: ", player);
            coords[0] = scanner.nextInt();
            System.out.printf("Enter a column (0, 1, or 2) for player %c: ", player);
            coords[1] = scanner.nextInt();
            if (!isValidInput(checkedPlaces, coords))
                System.out.println("Invalid input!");
            else
                break;
        }
        return coords;
    }

    private static boolean isValidInput (char[][] checkedPlaces, int[] coords) {
        return  isBetween0and2(coords[0]) &&
                isBetween0and2(coords[1]) &&
                checkedPlaces[coords[0]][coords[1]] == ' ';
    }

    private static boolean isBetween0and2(int number) {
        return number >= 0 && number <= 2;
    }

    private static void drawBoard(char[][] checkedPlaces) {
        System.out.println("-------------");
        for (int row = 0; row < checkedPlaces.length; row++) {
            System.out.print("|");
            for (int cell = 0; cell < checkedPlaces[row].length; cell++) {
                System.out.printf(" %c |", checkedPlaces[row][cell]);
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
}
