package mini2.view;

import mini2.controller.GameController;
import mini2.gameExceptions.GameException;

import java.util.Scanner;

public class Adventure {
    public static void main(String[] args) {
        try {
            GameController gameController = new GameController();
            gameController.displayFirstRoom();

            Scanner scanner = new Scanner(System.in);
            String command;
            do {
                System.out.print("What would you like to do? ");
                command = scanner.nextLine().trim().toLowerCase();

                switch (command) {
                    case "n":
                    case "north":
                        gameController.move("N");
                        break;
                    case "s":
                    case "south":
                        gameController.move("S");
                        break;
                    case "e":
                    case "east":
                        gameController.move("E");
                        break;
                    case "w":
                    case "west":
                        gameController.move("W");
                        break;
                    case "u":
                    case "up":
                        gameController.move("U");
                        break;
                    case "d":
                    case "down":
                        gameController.move("D");
                        break;
                    case "look":
                        gameController.look();
                        break;
                    case "x":
                        System.out.println("Thank you for playing my game.");
                        break;
                    default:
                        System.out.println("Invalid command. Try again.");
                        break;
                }

            } while (!command.equals("x"));

        } catch (GameException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
