package mini2.controller;

import mini2.gameExceptions.GameException;

class Command {
    private Player player;

    public Command(Player player) {
        this.player = player;
    }

    public void execute(String command) throws GameException {
        String[] parts = command.split(" ");
        String action = parts[0];

        switch (action) {
            case "move":
                if (parts.length > 1) {
                    player.move(parts[1]);
                } else {
                    System.out.println("Move where?");
                }
                break;
            case "look":
                player.look();
                break;
            case "take":
                if (parts.length > 1) {
                    player.takeItem(parts[1]);
                } else {
                    System.out.println("Take what?");
                }
                break;
            case "drop":
                if (parts.length > 1) {
                    player.dropItem(parts[1]);
                } else {
                    System.out.println("Drop what?");
                }
                break;
            default:
                System.out.println("Unknown command.");
                break;
        }
    }
}
