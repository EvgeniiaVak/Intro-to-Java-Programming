package chapter08;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ConnectFour {
    public static final int CONTINUE = -1,
                            DRAW = 0,
                            WIN = 1;


    public static void main(String[] args) throws IOException {
        InputStream is = new FileInputStream(new File("src/chapter08/disks.txt"));
        Scanner scanner = new Scanner(System.in);
        char[][] board = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = ' ';
            }
        }

        //draw the board
        draw(board);
        boolean validThrow;
        int status = CONTINUE;
        int counter = 0; // an integer to determine whose tern it is to throw

        while (status == CONTINUE) {
            char player = (counter % 2 == 0) ? 'Y' : 'R';
            //ask a player to throw his colored disk
            int column = promptForColumn(player, scanner);
            //redraw the board with new data
            validThrow = addDisk(player, column, board);
            if (!validThrow)
                continue;
            draw(board);

            //check if game is over, output result if it is
            status = checkGameStatus(board);
            switch (status) {
                case WIN:
                    System.out.printf("The %s player won!", (player == 'Y') ? "yellow" : "red");
                    break;
                case DRAW:
                    System.out.println("It's a draw!");
                    break;
                default:
                    break;
            }

            counter++;
        }


        scanner.close();
        is.close();
    }

    private static int checkGameStatus(char[][] board) {
        //check horizontally
        for (int row = board.length - 1; row >= 0; row--) {
            int count = 1;

            for (int cell = 1; cell < board[row].length; cell++) {
                if (board[row][cell] != ' ' && board[row][cell - 1] == board[row][cell]) {
                    count ++;
                    if (count >= 4) {
                        return WIN;
                    }
                } else {
                    count = 1;
                }
            }
        }

        //check vertically
        for (int column = 0; column < board[0].length; column++) {
            int count = 1;

            for (int cell = 1; cell < board.length; cell++) {
                if (board[cell][column] != ' ' && board[cell - 1][column] == board[cell][column]){
                    count++;
                    if (count >= 4) {
                        return WIN;
                    }
                }else {
                    count = 1;
                }
            }
        }

        //check diagonals from left-bottom to right-up
        //before and including bottom row
        for (int startRow = 3; startRow < board.length; startRow++) {
            int count = 1;

            for (int i = 0; i < startRow; i++) {
                int row = startRow - i;
                int column = i;

                if (board[row][column] != ' ' &&
                board[row][column] == board[row - 1][column + 1]) {
                    count++;
                    if (count >= 4) {
                        return WIN;
                    }
                }else {
                    count = 1;
                }
            }
        }
        //after bottom row
        for (int startColumn = 1; startColumn < board[0].length - 3; startColumn++) {
            int count = 1;

            for (int i = 0; i < board.length - startColumn; i++) {
                int row = board.length - i - 1;
                int column = startColumn + i;

                if (board[row][column] != ' ' &&
                        board[row][column] == board[row - 1][column + 1]) {
                    count++;
                    if (count >= 4) {
                        return WIN;
                    }
                }else {
                    count = 1;
                }
            }
        }

        //check diagonals from left-up to right-bottom
        //before top row
        for (int startRow = board.length - 4; startRow >= 0; startRow--) {
            int count = 1;

            for (int i = 0; i < board.length - startRow - 1; i++) {
                int row = startRow + i;
                int column = i;

                if (board[row][column] != ' ' &&
                        board[row][column] == board[row + 1][column + 1]) {
                    count++;
                    if (count >= 4) {
                        return WIN;
                    }
                }else {
                    count = 1;
                }
            }
        }
        //after top row
        for (int startColumn = 1; startColumn < board[0].length - 3; startColumn++) {
            int count = 1;

            for (int i = 0; i < board[0].length - startColumn - 1; i++) {
               int row = i;
               int column = startColumn + i;

                if (board[row][column] != ' ' &&
                        board[row][column] == board[row + 1][column + 1]) {
                    count++;
                    if (count >= 4) {
                        return WIN;
                    }
                }else {
                    count = 1;
                }
            }
        }

        //if game is not one but every cell is taken it's a draw
        int emptyCount = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ')
                    emptyCount ++;
            }
        }
        if (emptyCount < 1)
            return DRAW;


        return CONTINUE;
    }

    private static boolean addDisk(char player, int column, char[][] board) {
        int lendingRow = board.length - 1;

        //if top row is already taken exit the method an return false
        if (board[0][column] != ' ') {
            System.out.println("The column is already full, try once more");
            return false;
        }


        // check every row from the bottom
        for (int row = lendingRow; row >= 0; row--) {
            //if we encounter an empty space put the disk there
            if (board[row][column] == ' ') {
                board[row][column] = player;
                break;
            }

        }

        return true;
    }

    private static int promptForColumn(char player, Scanner scanner) {
        String color = (player == 'Y') ? "yellow" : "red";
        while (true) {
            System.out.printf("Drop a %s disk at column (0–6): ", color);
            // to test paste (int) (Math.random() * 7);//
            int column = scanner.nextInt();
            if (column >= 0 && column <= 6)
                return column;

            System.out.println("No such column!");
        }
    }

    private static void draw(char[][] board) {
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            System.out.print("|");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println("---------------");
    }
}
