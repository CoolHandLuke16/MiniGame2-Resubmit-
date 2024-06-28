package mini2.controller;

import java.util.ArrayList;

public class Room {
    private int id;
    private String name;
    private String description;
    private ArrayList<Exit> exits;
    private ArrayList<Item> roomItems;

    public Room(int id) {
        this.id = id;
        this.exits = new ArrayList<>();
        this.roomItems = new ArrayList<>();
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Exit> getExits() {
        return exits;
    }

    public void setExits(ArrayList<Exit> exits) {
        this.exits = exits;
    }

    public ArrayList<Item> getRoomItems() {
        return roomItems;
    }

    public void setRoomItems(ArrayList<Item> roomItems) {
        this.roomItems = roomItems;
    }

    // Method to display room information
    public void display() {
        System.out.println("Room: " + name);
        System.out.println(description);
        if (!roomItems.isEmpty()) {
            System.out.println("Items in the room:");
            for (Item item : roomItems) {
                System.out.println("- " + item.getItemName());
            }
        }
        if (!exits.isEmpty()) {
            System.out.print("Exits: ");
            for (Exit exit : exits) {
                System.out.print(exit.getDirection() + " ");
            }
            System.out.println();
        }
    }

    // Method to check if a direction is valid
    public boolean validDirection(String direction) {
        for (Exit exit : exits) {
            if (exit.getDirection().equalsIgnoreCase(direction)) {
                return true;
            }
        }
        return false;
    }

    // Method to remove an item from the room
    public void removeItem(Item item) {
        roomItems.remove(item);
    }

    // Method to add an item to the room
    public void addItem(Item item) {
        roomItems.add(item);
    }

    // Method to get an item by name
    public Item getItem(String itemName) {
        for (Item item : roomItems) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", exits=" + exits +
                ", roomItems=" + roomItems +
                '}';
    }
}
