package mini2.model;

import mini2.controller.Exit;
import mini2.controller.Room;
import mini2.controller.Item;
import mini2.gameExceptions.GameException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RoomDB {
    private static RoomDB instance;
    private ArrayList<Room> rooms;

    private RoomDB() throws GameException {
        rooms = new ArrayList<>();
        readRooms();
    }

    public static RoomDB getInstance() throws GameException {
        if (instance == null) {
            instance = new RoomDB();
        }
        return instance;
    }

    private void readRooms() throws GameException {
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/data/Rooms.txt"))) {
            String line;
            Room currentRoom = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty() || line.startsWith("#")) {
                    continue; // Skip empty lines and comments
                }

                if (line.startsWith("---")) {
                    // End of current room definition
                    if (currentRoom != null) {
                        rooms.add(currentRoom);
                    }
                    currentRoom = null;
                } else if (currentRoom == null) {
                    // Start of a new room
                    String[] parts = line.split("\\|");
                    if (parts.length < 3) {
                        throw new GameException("Invalid room format: " + line);
                    }
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String description = parts[2].trim();
                    currentRoom = new Room(id);
                    currentRoom.setName(name);
                    currentRoom.setDescription(description);
                } else {
                    // Process exits and items
                    String[] parts = line.split(" ", 2);
                    if (parts.length != 2) {
                        throw new GameException("Invalid exit format: " + line);
                    }
                    String keyword = parts[0].trim();
                    String value = parts[1].trim();

                    if ("EAST".equalsIgnoreCase(keyword) || "WEST".equalsIgnoreCase(keyword) ||
                            "NORTH".equalsIgnoreCase(keyword) || "SOUTH".equalsIgnoreCase(keyword) ||
                            "UP".equalsIgnoreCase(keyword) || "DOWN".equalsIgnoreCase(keyword) ||
                            "E".equalsIgnoreCase(keyword) || "W".equalsIgnoreCase(keyword) ||
                            "N".equalsIgnoreCase(keyword) || "S".equalsIgnoreCase(keyword) ||
                            "U".equalsIgnoreCase(keyword) || "D".equalsIgnoreCase(keyword)) {
                        Exit exit = new Exit();
                        exit.buildExit(keyword + " " + value);
                        currentRoom.getExits().add(exit);
                    } else {
                        try {
                            int itemId = Integer.parseInt(keyword);
                            Item item = ItemDB.getInstance().getItem(itemId);
                            currentRoom.getRoomItems().add(item);
                        } catch (NumberFormatException e) {
                            throw new GameException("Invalid exit or item format: " + line);
                        }
                    }
                }
            }

            // Add the last room if it exists
            if (currentRoom != null) {
                rooms.add(currentRoom);
            }
        } catch (IOException e) {
            throw new GameException("Rooms file not found.");
        } catch (NumberFormatException e) {
            throw new GameException("Invalid number format in room data.");
        }
    }

    public Room getRoom(int roomID) throws GameException {
        for (Room room : rooms) {
            if (room.getId() == roomID) {
                return room;
            }
        }
        throw new GameException("Room ID not found.");
    }

    public ArrayList<Item> getItems(int roomID) throws GameException {
        Room room = getRoom(roomID);
        return room.getRoomItems();
    }

    public void updateRoom(Room room) throws GameException {
        int index = rooms.indexOf(room);
        if (index == -1) {
            throw new GameException("Room not found.");
        }
        rooms.set(index, room);
    }

    public String getMap() {
        StringBuilder map = new StringBuilder();
        for (Room room : rooms) {
            map.append(room.getDescription()).append("\n");
        }
        return map.toString();
    }
}
