import java.util.Stack;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Player player;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player();
        player.setCurrentRoom(createRooms());
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private Room createRooms()
    {
        Room campo, vestuarios, duchas, pasilloDorm1, pasilloDorm2, dormitorios, armeria,
        pasilloSecreto, almacen, hornos, salaCeniza, salaGas;

        Item casco;
        
        // create the rooms
        campo = new Room("en pleno exterior del campo de concentracion.");
        vestuarios = new Room("en un vestuario lleno de taquillas sin nombre.");
        duchas = new Room("en las duchas, parece que no se han pasado buenos momentos.");
        pasilloDorm1 = new Room("en un largo pasillo, al fondo se ven camas.");
        pasilloDorm2 = new Room("aun en el pasillo, logr�s ver un dormitorio.");
        dormitorios = new Room("en los dormitorio, no hay nadie.");
        pasilloSecreto = new Room("en un pasillo oculto en las paredes.");
        armeria = new Room("en una sala llena de armamento militar.");
        almacen = new Room("en un gran almacen lleno de alimentos de baja calidad.");
        hornos = new Room("en los hornos crematorios del campo.");
        salaCeniza = new Room("en una sala repleta de ceniza, parece que aqui almacenan los restos.");
        salaGas = new Room("en la sala donde controlan el gas de las duchas.");

        // create the room items
        campo.addItem(new Item(12, "Pistola Parabellum", "Parabellum", true));
        almacen.addItem(new Item(7, "Saco de patatas", "SacoPatatas", true));
        
        // initialise room exits
        campo.setExit("north", hornos);
        campo.setExit("south", almacen);
        campo.setExit("east", vestuarios);
        campo.setExit("west", pasilloDorm1);
        vestuarios.setExit("east", duchas);
        vestuarios.setExit("west", campo);
        duchas.setExit("west", vestuarios);
        duchas.setExit("southEast", salaGas);
        pasilloDorm1.setExit("east", campo);
        pasilloDorm1.setExit("west", pasilloDorm2);
        pasilloDorm2.setExit("south", pasilloSecreto);
        pasilloDorm2.setExit("east", pasilloDorm1);
        pasilloDorm2.setExit("west", dormitorios);
        pasilloSecreto.setExit("north", pasilloDorm2);
        pasilloSecreto.setExit("south", armeria);
        dormitorios.setExit("east", pasilloDorm1);
        armeria.setExit("north", pasilloSecreto);
        hornos.setExit("north", salaCeniza);
        hornos.setExit("south", campo);
        salaCeniza.setExit("south", hornos);
        salaGas.setExit("northWest", duchas);

        return campo;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Gracias por jugar. Hasta la proxima");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("El judio con el piyama de cuadros, el nuevo roleplay de EA");
        System.out.println("No tan bienvenido al campo de concentracion de tu padre");
        System.out.println("Escribe 'help' si necesitas ayuda");
        System.out.println();
        player.look();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("No se que estas diciendo...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            player.goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {  
            player.look();
        }
        else if (commandWord.equals("eat")) {   
            player.eat();
        }
        else if (commandWord.equals("back")) {
            player.back();
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("�Quitar que?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
