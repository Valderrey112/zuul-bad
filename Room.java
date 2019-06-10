import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
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
    private ArrayList<Item> listaObjetos;
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
        listaObjetos = new ArrayList<>();
    }

    public void setExit(String direction, Room nextRoom) {
        exits.put(direction, nextRoom);
    }
    
    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description ;
    }
    
    public String getDescripcionObjeto() {
        String itemActual = "";
        if (listaObjetos.size() > 0){
            for (Item item : listaObjetos){
                itemActual += item.getDescripcionCompleta() + ".\n";
            }
        }
        return itemActual;
    }

    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    
    /**
     * Devuelve la información de las salidas existentes
     * Por ejemplo: "Exits: north east west"
     *
     * @return Una descripción de las salidas existentes.
     */
    public String getExitString(){
        Set<String> namesOfDirections = exits.keySet();
        String exitsDescription = "Salidas: ";
        for(String direction: namesOfDirections){
            exitsDescription += direction + " ";
        }
        return exitsDescription;
    }
    
    /**
      * Devuelve un texto con la descripcion larga de la habitacion del tipo:
      *     You are in the 'name of room'
      *     Exits: north west southwest
      * @return Una descripcion de la habitacion incluyendo sus salidas
      */
     public String getLongDescription() {
         return "Estas " + description + "\n" + getDescripcionObjeto() + "\n" + getExitString();
    }
    
    /**
     * Añade un item a la habitacion
     */
    public void addItem(Item item){
        listaObjetos.add(item);
    }
}
