package mini2.controller;

import mini2.model.RoomDB;
import mini2.gameExceptions.GameException;

public class GameController {
    private Room currentRoom;

    public GameController() throws GameException {
        RoomDB roomDB = RoomDB.getInstance();
        this.currentRoom = roomDB.getRoom(1); // Starting with room ID 1
    }

    public void displayFirstRoom() {
        currentRoom.display();
    }

    public void move(String direction) throws GameException {
        for (Exit exit : currentRoom.getExits()) {
            if (exit.getDirection().equalsIgnoreCase(direction)) {
                RoomDB roomDB = RoomDB.getInstance();
                currentRoom = roomDB.getRoom(exit.getDestination());
                currentRoom.display();
                return;
            }
        }
        System.out.println("You can't go that way.");
    }

    public void look() {
        currentRoom.display();
    }
}
