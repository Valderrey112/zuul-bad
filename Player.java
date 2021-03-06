import java.util.ArrayList;
import java.util.Stack;

public class Player
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private Stack<Room> roomStack;
    private ArrayList<Item> bag;
    private int bagWeigth;
    private static int MAX_WEIGTH = 40;
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
            System.out.println("�Ir donde?");
            return;
        }

        String direction = command.getSecondWord();
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        
        if (nextRoom == null) {
            System.out.println("�Aqu� no hay puerta!");
        }
        else {
            roomStack.push(currentRoom);
            currentRoom = currentRoom.getExit(direction);
            look();
        }
    }
    
    /** 
     * Vuelve a imprimir por pantalla la informacion de la sala actual.
     */
    public void look() {   
        System.out.println(currentRoom.getLongDescription());
    }
    
    public void eat() {   
        System.out.println("You have eaten now and you are not hungry any more");
    }
    
    /** 
     * Vuelve a la habitacion anterior
     */
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
    
    /** 
     * Coge un objeto de la habitacion y lo guarda en la mochila si no supera el peso maximo
     */
    public void take(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know the item to take...
            System.out.println("No has indicado el ID del objeto a coger");
            return;
        }
        String itemID = command.getSecondWord();
        Item itemToTake = currentRoom.getItem(itemID);

        if (itemToTake != null && bagWeigth + itemToTake.getWeigth() < MAX_WEIGTH && itemToTake.getPuedeCogerse() == true){
            System.out.println("Has cogido el siguiente objeto:" + "\n");
            System.out.println(itemToTake.getDescripcionItem());
            bagWeigth += itemToTake.getWeigth();
            bag.add(itemToTake);
            currentRoom.removeItem(itemToTake);
        }

        else{
            if (itemToTake == null){
                System.out.println("No hay objetos en la habitacion o lo has escrito mal.");
            }
            else{
                System.out.println("No puedes cargar con este objeto.");
            }
        }
    }
    
    /**
     * Muestra los objetos que tiene el jugador encima.
     */
    public void items() 
    {
        if (bag.size() > 0){
            System.out.println("En tu mochila tienes.");
            for (int i = 0; i < bag.size(); i++){
                System.out.println(bag.get(i).getDescripcionItem());
            }
        }
        else{
            System.out.println("Tu mochila esta vacia.");
        }
    }
    
    /**
     * Deja un objeto de la mochila en la sala indicando el id del objeto.
     */
    public void drop(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know the item to take...
            System.out.println("Especificame que es lo que quieres dejar o escribe bien el ID");
            return;
        }
        String itemID = command.getSecondWord();
        Item itemToDrop = null;
        int cont = 0;
        while (itemToDrop == null && bag.size() > cont){
            if (bag.get(cont).getItemId().equals(itemID)){
                itemToDrop = bag.get(cont);
            }
            cont++;
        }

        if (bag.size() > 0 && itemToDrop != null){
            System.out.println("Has dejado en el suelo:" + "\n");
            System.out.println(itemToDrop.getDescripcionItem());
            bagWeigth -= itemToDrop.getWeigth();
            bag.remove(itemToDrop);
            currentRoom.addItem(itemToDrop);
        }
        else{
            System.out.println("No hay objetos en la mochila.");
        }
    }
    
    public void upWeigth(){
        MAX_WEIGTH = 45;
    }
    
    /**
     * Drop an item into the room choosing the item id
     */
    public void call(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("No has indicado a quien llamar.");
            return;
        }
        String itemID = command.getSecondWord();
        Item itemToCompare = currentRoom.getItem(itemID);
        if (itemToCompare != null && itemToCompare.getItemId().equals("Fuhrer")){
            System.out.println("Esta en comunicacion por varios segundos, hasta que te cogen el telefono");
            System.out.println("Es el temido Fuhrer, que al saber que eres el hijo del due�o del campo de concentracion,");
            System.out.println("te condecora con una medalla nazi que hay en la misma sala de horno en la que estas.");
            System.out.println("Notas que te haces mas fuerte.");
            MAX_WEIGTH = 45;
            }
        else{
            System.out.println("Aqui no hay ningun numero de telefono.");
        }
    }
}