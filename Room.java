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
    private Room northExit;
    private Room northWestExit;
    private Room southExit;
    private Room eastExit;
    private Room southEastExit;
    private Room westExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
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
    public void setExits(Room north, Room northWest, Room east, Room south, Room southEast, Room west) 
    {
        if(north != null){
            northExit = north;
        }
        if(east != null){
            eastExit = east;
        }
        if(south != null){
            southExit = south;
        }
        if(southEast != null){
            southEastExit = southEast;
        }
        if(west != null){
            westExit = west;  
        }
        if(northWest != null){
            northWestExit = northWest;
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
            roomToReturn = northExit;
        }
        if (direction.equals("south")){
            roomToReturn = southExit;
        }
        if (direction.equals("east")){
            roomToReturn = eastExit;
        }
        if (direction.equals("southeast")){
            roomToReturn = southEastExit;
        }
        if (direction.equals("west")){
            roomToReturn = westExit;
        }
        if (direction.equals("northwest")){
            roomToReturn = northWestExit;
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
        if(northExit != null) {
            cadena += " north";
        }
        if(southExit != null) {
            cadena += " south";
        }
        if(eastExit != null) {
            cadena += " east";
        }
        if(southEastExit != null) {
            cadena += " southeast";
        }
        if(westExit != null) {
            cadena += " west";
        }
        if(northWestExit != null) {
            cadena += " northwest";
        }
        return cadena;
    }
}
