import java.util.ArrayList;
import java.util.Stack;

public class Player
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private Stack<Room> roomStack;
    private ArrayList<Item> bag;
    private int bagWeigth;
    private static final int MAX_WEIGTH = 40;
    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        // initialise instance variables
        currentRoom = null;
        roomStack = new Stack<Room>();
        bag = new ArrayList<>();
        bagWeigth = 0;
    }
    
    public void setCurrentRoom(Room room){
        currentRoom = room;
    }
    
    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("¿Ir donde?");
            return;
        }

        String direction = command.getSecondWord();
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        
        if (nextRoom == null) {
            System.out.println("¡Aquí no hay puerta!");
        }
        else {
            roomStack.push(currentRoom);
            currentRoom = currentRoom.getExit(direction);
            look();
        }
    }
    
    public void look() {   
        System.out.println(currentRoom.getLongDescription());
    }

    public void eat() {   
        System.out.println("You have eaten now and you are not hungry any more");
    }
    
    public void back()         
    {
        if (!roomStack.empty()){
            currentRoom = roomStack.pop();
            look();
        }
        else{
            System.out.println("Ya estas en la sala inicial, no puedes volver atras.");
        }
    }
    
    public void take(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know the item to take...
            System.out.println("No has indicado el ID del objeto a coger");
            return;
        }
        String positionItem = command.getSecondWord();
        Item itemToTake = currentRoom.getItem(positionItem);

        if (itemToTake != null && bagWeigth + itemToTake.getWeigth() < MAX_WEIGTH){
            System.out.println("Has cogido el siguiente objeto:" + "\n");
            System.out.println(itemToTake.getDescripcionItem());
            bagWeigth += itemToTake.getWeigth();
            bag.add(itemToTake);
            currentRoom.removeItem(itemToTake);
        }

        else{
            if (itemToTake == null){
                System.out.println("No hay objetos en la habitacion.");
            }
            else{
                System.out.println("No puedes cargar con este objeto.");
            }
        }
    }
}