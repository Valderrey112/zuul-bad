import java.util.ArrayList;
import java.util.Stack;

public class Player
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private Stack<Room> roomStack;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        // initialise instance variables
        currentRoom = null;
        roomStack = new Stack<Room>();
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
}