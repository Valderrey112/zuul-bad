import java.util.HashMap;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param southEast The southeast exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room south, Room east, Room west, Room northWest, Room southEast) 
    {
        if(north != null){
            exits.put("north", north);
        }
        if(south != null){
            exits.put("south", south);
        }
        if(east != null){
            exits.put("east", east);
        }
        if(west != null){
            exits.put("west", west);  
        }
        if(northWest != null){
            exits.put("northWest", northWest);
        }
        if(southEast != null){
            exits.put("southEast", southEast);
        }
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public Room getExit(String direction)
    {
        Room roomToReturn = null;
        if (direction.equals("north")){
            roomToReturn = exits.get("north");
        }
        if (direction.equals("south")){
            roomToReturn = exits.get("south");
        }
        if (direction.equals("east")){
            roomToReturn = exits.get("east");
        }
        if (direction.equals("west")){
            roomToReturn = exits.get("west");
        }
        if (direction.equals("northWest")){
            roomToReturn = exits.get("northWest");
        }
        if (direction.equals("southEast")){
            roomToReturn = exits.get("southEast");
        }
        return roomToReturn;
    }
    
    /**
     * Devuelve la información de las salidas existentes
     * Por ejemplo: "Exits: north east west"
     *
     * @return Una descripción de las salidas existentes.
     */
    public String getExitString(){
        String cadena = "Salidas:";
        if(exits.get("north") != null) {
            cadena += " north";
        }
        if(exits.get("south") != null) {
            cadena += " south";
        }
        if(exits.get("east") != null) {
            cadena += " east";
        }
        if(exits.get("west") != null) {
            cadena += " west";
        }
        if(exits.get("northWest") != null) {
            cadena += " northWest";
        }
        if(exits.get("southEast") != null) {
            cadena += " southEast";
        }
        return cadena;
    }
}
