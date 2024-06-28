package mini2.controller;

import mini2.gameExceptions.GameException;
import mini2.model.RoomDB;

public class Player {
    private Room currentRoom;

    public Player() throws GameException {
        RoomDB roomDB = RoomDB.getInstance();
        this.currentRoom = roomDB.getRoom(1); // Starting with room ID 1
    }

    public void move(String direction) throws GameException {
        if (currentRoom.validDirection(direction)) {
            // Move logic here, e.g., updating currentRoom based on direction
        } else {
            System.out.println("You can't go that way.");
        }
    }

    public void look() {
        currentRoom.display();
    }

    public void takeItem(String itemName) throws GameException {
        Item item = currentRoom.getItem(itemName);
        if (item != null) {
            currentRoom.removeItem(item);
            // Add item to player's inventory
            System.out.println(itemName + " has been added to your inventory.");
        } else {
            System.out.println(itemName + " is not in the room.");
        }
    }

    public void dropItem(String itemName) {
        // Logic to drop item from player's inventory
        // Assuming a method getInventoryItem exists that retrieves an item from the player's inventory
        Item item = getInventoryItem(itemName);
        if (item != null) {
            currentRoom.addItem(item);
            // Remove item from player's inventory
            System.out.println(itemName + " has been dropped in the room.");
        } else {
            System.out.println("You don't have " + itemName + ".");
        }
    }

    private Item getInventoryItem(String itemName) {
        // Logic to retrieve item from inventory
        return null; // Placeholder
    }
}
