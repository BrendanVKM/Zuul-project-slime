import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * game motor 
 * @author Brendan VICTOIRE
 * @version 2021.01.20 + 2021.02.17 + 2021.02.26
 */
public class GameEngine
{

    private HashMap<String, Room> aRooms;
    private Parser aParser;
    private UserInterface aGui;
    private Player aPlayer;

    /**
     * Default constructor 
     */
    public GameEngine()
    {
        this.aParser = new Parser();
        this.aRooms = new HashMap<String, Room>();
        this.createRooms();
    } // GameEngine()

    /**
     * set the GUI
     * @param pUserInterface the user interface
     */
    public void setGUI( final UserInterface pUserInterface )
    {
        this.aGui = pUserInterface;
        this.aPlayer.setGUI( this.aGui );
        this.printWelcome();
    } // setGUI(.)
    
    /**
     * create and declare Rooms and Items
     */
    private void createRooms()
    {
        Room vSpawn = new Room( "somewhere in a cave asking yourself where is Beatrix.", "spawn" );
        Room vCave2 = new Room( "somewhere in a cave.", "cave2" );
        Room vCave5 = new Room( "somewhere in a cave. You see a corridor that you can't reach", "cave5" );
        Room vCave6 = new Room( "somewhere in a cave and your sense tells you that there is something hidden."
            , "cave6" );
        Room vCave7 = new Room( "somewhere in a cave. Be careful if you jump, you won't be able to come back here.", "cave7" );
        Room vCave8 = new Room( "somewhere in a cave. You have crossed the secret path.", "cave8" );
        Room vUpFlower = new Room( "somewhere in a cave with a flower floor.", "upFlower" );
        Room vFlower2 = new Room( "somewhere in a cave with a flower floor.", "flower2");
        Room vFlowerTrail = new Room( "somewhere in a cave with a flower floor.", "flowerTrail" );
        Room vOnMagistone = new Room( "somewhere in a cave on a Magistone.", "onMagistone" );
        Room vBottomOfLake = new Room( "deep down in a lake.", "bottomOfLake" );
        Room vSurfaceOfLake = new Room( "swimming on the surface of a lake.", "surfaceOfLake" );
        Room vLake = new Room( "on the surface of a lake.", "lake" );
        Room vJumpToDragon = new Room( "somewhere in a cave asking yourself if you should jump."
            , "jumpToDragon" );
        Room vMeetTempest = new Room( "somewhere in a cave you met Veldora.", "meetTempest" );
        Room vSeeTheDoor = new Room( "somewhere in a cave and you see a door.", "seeTheDoor" );
        Room vDoor = new Room( "looking at the door.", "door" );
        Room vGetOut = new Room( "opening the door.", "getOut" );
        Room vOutside = new Room( "finally outside.", "outside" );
        Room vMeetBeatrix = new Room( "finally back to Beatrix.", "meetBreeder" ); 
        
        Item vStone = new Item( "stone", "just a stone, what did you expect !?", 4.69);
        Item vMagistone = new Item( "magistone", "a magic stone, can it be useful somewhere? ", 35.82 );
        Item vFlower = new Item( "flower", "a beautiful flower", 0.026);
        Item vDragonSoul = new Item( "dragonSoul", "the soul of Veldora", 10E-100);
        Item vTree = new Item( "tree", "just a tree and yes you can carry it you are a slime after all", 735);
        
        Beamer vBeamer = new Beamer();

        Door vSecretPassage = new Door( vDragonSoul, false);
        Door vPassTheDoor = new Door( vDragonSoul, false);

        this.aRooms.put( "spawn", vSpawn );
        this.aRooms.put( "cave2", vCave2 );
        this.aRooms.put( "cave5", vCave5 );
        this.aRooms.put( "cave6", vCave6 );
        this.aRooms.put( "cave7", vCave7 );
        this.aRooms.put( "cave8", vCave8 );
        this.aRooms.put( "up flower", vUpFlower );
        this.aRooms.put( "flower2", vFlower2 );
        this.aRooms.put( "flower trail", vFlowerTrail );
        this.aRooms.put( "on magistone", vOnMagistone );
        this.aRooms.put( "bottom of lake", vBottomOfLake );
        this.aRooms.put( "surface of lake", vSurfaceOfLake );
        this.aRooms.put( "lake", vLake );
        this.aRooms.put( "jump to dragon", vJumpToDragon );
        this.aRooms.put( "meet tempest", vMeetTempest );
        this.aRooms.put( "see the door", vSeeTheDoor );
        this.aRooms.put( "door", vDoor );
        this.aRooms.put( "get out", vGetOut );
        this.aRooms.put( "outside", vOutside );
        this.aRooms.put( "meet beatrix", vMeetBeatrix );

        vSpawn.setExit( "south", vCave2 );
        vSpawn.setItem( vStone );
        vSpawn.setItem( vBeamer );

        vCave2.setExit( "north", vSpawn ); 
        vCave2.setExit( "south", vUpFlower );
        vCave2.setItem( vMagistone );
        vCave2.setItem( vFlower );

        vCave5.setExit( "north", vLake );
        vCave5.setExit( "south", vSeeTheDoor );

        vCave6.setExit( "west", vFlowerTrail );
        vCave6.setDoors( "south", vSecretPassage );
        vCave6.setExit( "south", vCave8);
        vCave6.setItem( vMagistone );

        vCave7.setExit( "north", vOnMagistone );
        vCave7.setExit( "south", vFlowerTrail );
        vCave7.setExit( "down", vBottomOfLake);

        vCave8.setExit( "down", vCave5);
        vCave8.setDoors( "north", vSecretPassage );
        vCave8.setExit( "north", vCave6);
        vCave8.setItem( vMagistone );

        vUpFlower.setExit( "north", vCave2 );
        vUpFlower.setExit( "west", vOnMagistone );
        vUpFlower.setExit( "down", vFlower2);

        vFlower2.setExit( "up", vUpFlower );
        vFlower2.setItem( vFlower );

        vFlowerTrail.setExit( "north", vCave7 );
        vFlowerTrail.setExit( "east", vCave6 );
        vFlowerTrail.setExit( "west", vJumpToDragon);
        vFlowerTrail.setItem( vFlower );

        vOnMagistone.setExit( "south", vCave7);
        vOnMagistone.setExit( "east", vUpFlower );
        vOnMagistone.setItem( vMagistone );

        vBottomOfLake.setExit( "up", vSurfaceOfLake );

        vSurfaceOfLake.setExit( "south", vLake);
        vSurfaceOfLake.setExit( "down", vBottomOfLake);

        vLake.setExit( "north", vSurfaceOfLake);
        vLake.setExit( "south", vCave5 );

        vJumpToDragon.setExit( "south", vMeetTempest );
        vJumpToDragon.setExit( "east", vFlowerTrail );

        vMeetTempest.setExit( "north", vJumpToDragon );
        vMeetTempest.setItem( vDragonSoul );

        vSeeTheDoor.setExit( "north", vCave5 );
        vSeeTheDoor.setExit( "west", vDoor );
        vSeeTheDoor.setItem( vMagistone );

        vDoor.setExit( "east", vSeeTheDoor );
        vDoor.setDoors("west", vPassTheDoor);
        vDoor.setExit( "west", vGetOut );

        vGetOut.setDoors("east", vPassTheDoor);
        vGetOut.setExit( "east", vDoor );
        vGetOut.setExit( "west", vOutside );

        vOutside.setExit( "east", vGetOut );
        vOutside.setExit( "west", vMeetBeatrix );
        vOutside.setItem( vTree );

        vMeetBeatrix.setExit( "east", vOutside );

        this.aPlayer = new Player();
        this.aPlayer.setCurrentRoom(vSpawn);
        this.aPlayer.getPreviousRooms().push( new Room( "... Wait! Where are you?", "") );
    } // createRooms()

    /**
     * interpret commands
     * stop the game if you have executed to much command
     * @param pCom a command
     */
    public void interpretCommand( final String pCom ) 
    {
        this.aGui.println( "\n" + "> " + pCom );
        Command vCom = this.aParser.getCommand( pCom );
        CommandWord vCW = vCom.getCommandWord();

        if ( this.aPlayer.commandCounter() ){
            this.aGui.println( "You are too tired to continue.");
            this.quit( new Command( CommandWord.QUIT, null ) );
            return;
        }

        switch ( vCW ){
            case UNKNOWN : 
                this.aGui.println("I don't know what you mean...");
                break;
            case GO:
                this.goRoom(vCom);
                break;
            case BACK:
                this.goBack(vCom);
                break;
            case MEMORIZE:
                this.memorize(vCom);
                break;
            case TELEPORT:
                this.teleport(vCom);
                break;
            case LOOK:
                this.look(vCom);
                break;
            case EAT:
                this.eat(vCom);
                break;
            case ITEMS:
                this.Items(vCom);
                break;
            case TAKE:
                this.take(vCom);
                break;
            case DROP:
                this.drop(vCom);
                break;
            case HELP:
                this.printHelp();
                break;
            case QUIT:
                this.quit(vCom);
                break;
            case TEST:
                this.test(vCom);
                break;
        }
    } // interpretCommand(.)
    
    /**
     * print welcome message
     */
    private void printWelcome()
    {
        this.aGui.println( "Welcome to Searching for the slime breeder" );
        this.aGui.println( "Searching for the slime breeder is a new, incredibly boring adventure game." );
        this.aGui.println( "Type \' " + CommandWord.HELP + " \' if you need help. \n" );
        this.aPlayer.printLocationInfo();
    } // printWelcome()

    /**
     * method executed with the command word "go"
     * let you go forward
     * @param pDep command to move to the next
     */
    public void goRoom( final Command pDep )
    {
        if ( !pDep.hasSecondWord() )
            this.aGui.println( "Go where ?" );
        else {
            String vDrct = pDep.getSecondWord(); 
            int vNumber = this.aPlayer.goRoom(vDrct);
            if ( vNumber == 0 ) this.aGui.println( "There is no corridor !" );
            else if ( vNumber == 1 ){
                this.aPlayer.goRoom(vDrct);
                this.aGui.println( "You can't pass without the dragonSoul");
            }
        }
    } // goRoom(.)

    /**
     * method executed with the command word "back"
     * let you go backward
     * @param pCom a command
     */
    public void goBack( final Command pCom )
    {
        if ( !pCom.hasSecondWord() ){
            if ( this.aPlayer.getPreviousRoom().getImageName().equals( "" )) 
                this.aGui.println( "You were" + this.aPlayer.getPreviousRoom().getDescription() );
            //if you are in the bottom of the lake you can't go back to the cave you have to go further
            else if ( this.aPlayer.getCurrentRoom().getImageName().equals( "bottomOfLake" ) 
                && this.aPlayer.getPreviousRoom().getImageName().equals( "cave7" ) ){
                    this.aPlayer.getPreviousRooms().clear();
                    this.aGui.println( "You were warned, you can't go back after jumping here." );
                }
            else if ( this.aPlayer.getCurrentRoom().equals( this.aRooms.get( "cave5" ) ) 
                && this.aPlayer.getPreviousRoom().equals( this.aRooms.get( "cave8" ) ) ) {
                    this.aPlayer.getPreviousRooms().clear();
                    this.aGui.println( "You can't go back after falling here." );
                }
            else {
                this.aPlayer.goBack();
            }
        }
        else this.aGui.println( "Back what ?!" );
    } // goBack(.)

    /**
     * charged the beamer with the current room
     * @param pCom a command
     */
    public void memorize( final Command pCom )
    {
        if ( this.aPlayer.getInventory().containsItem("teleportation") )
            if ( !pCom.hasSecondWord() ){
                    if ( this.aPlayer.memorize() )
                        this.aGui.println( "You already used teleport!" );
                    else {
                        this.aPlayer.memorize();
                        this.aGui.println( "Room memorized.");
                    }
            }
            else this.aGui.println( "You can only memorize your current room" );
        else this.aGui.println( "You cannot use that at the moment");
    } // memorize(.)

    /**
     * teleport the player to the memorized room
     * @param pCom a command
     */
    public void teleport( final Command pCom )
    {
        if ( this.aPlayer.getInventory().containsItem("teleportation") ){
            if ( !pCom.hasSecondWord() ){
                    if ( this.aPlayer.getBeamer().isUsed() )
                        this.aGui.println( "You already used it");
                    else {
                        if ( this.aPlayer.teleport() )
                            this.aPlayer.teleport();
                        else this.aGui.println( "You need to memorize a room" );
                    }
            }
            else this.aGui.println( "You can only teleport to your memorized room" );
        }
        else this.aGui.println( "You cannot use that at the moment" );
    } // teleport(.)

    /**
     * method executed with the command word "look"
     * print all the information of the room or the item that you are looking at
     * @param pCom a command
     */
    public void look( final Command pCom )
    {
        if ( pCom.hasSecondWord() ){
            String vSW = pCom.getSecondWord();
            this.aGui.print( "You are looking at " );
            if ( this.aPlayer.look(vSW) )
                this.aGui.print( "a " + this.aPlayer.getCurrentRoom().getItems().getItem(vSW).getItemString() + "\n" );
            else this.aGui.println( "... I don't know what you are looking at! Is this in here?" );
        }
        else this.aGui.println( this.aPlayer.getCurrentRoom().getLongDescription() );
    } // look(.)

    /**
     * method executed with the command word "eat"
     * if possible eat an item from your inventory
     * @param pCom a command
     */
    public void eat( final Command pCom )
    {
        if ( !pCom.hasSecondWord() )
            this.aGui.println( "What do you want to eat ?");
        else {
            String vSW = pCom.getSecondWord();
            int vNumber = this.aPlayer.eat(vSW);
            if ( vNumber == 0 )
                    this.aGui.println( "You have eaten the Veldora's soul. Now you can carry more items." );
            else if ( vNumber == 1 ) 
                this.aGui.println( "You have eaten now and you are not hungry any more.");
            else this.aGui.println( "You don't carry this... if this exist.");
        }
    } // eat(.)

    /**
     * method executed with the command word "items" 
     * print a list of the item carried by the player
     * @param pCom a command
     */
    public void Items( final Command pCom )
    {
        if ( pCom.hasSecondWord())
            this.aGui.println( "Items what?" );
        else this.aGui.println( this.aPlayer.Items() );
    } // Items()

    /**
     * method executed with the command word "take" 
     * if possible take an item and put it in the inventory of the player
     * @param pCom a command
     */
    public void take( final Command pCom )
    {
        if ( !pCom.hasSecondWord() )
            this.aGui.println( "Take what ?");
        else {
            String vSW = pCom.getSecondWord();
            int vNumber = this.aPlayer.take( vSW );
            if ( vNumber == 0 )
                        this.aGui.println( "You don't have enough space to take this."); 
            else if ( vNumber == 1 ){
                this.aGui.println( "You take " + vSW + "\n"
                    + this.aPlayer.getCurrentRoom().getItemsString() + "\n"
                    +this.aPlayer.getInventoryString() );
                this.aPlayer.take( vSW );
            }
            else this.aGui.println( "What is this ? Is it there ?" );
        }
    } // take(.)

    /**
     * method executed with the command word "drop" 
     * if possible drop an item on the floor of the room
     * @param pCom a command
     */
    public void drop( final Command pCom )
    {
        if ( !pCom.hasSecondWord() )
            this.aGui.println( "Drop what ?");
        else {
            String vSW = pCom.getSecondWord();
            if ( this.aPlayer.drop(vSW) ){
                this.aPlayer.drop(vSW);
                this.aGui.println( "You droped " + vSW + "\n"
                    + this.aPlayer.getCurrentRoom().getItemsString() + "\n"
                    + this.aPlayer.getInventoryString() );
            }
            else this.aGui.println( "What is this ? Do you even have this ?" );
        }
    } // drop(.)
    
    /**
     * method executed with the command word "help" 
     * print help message
     */
    public void printHelp()
    {
        this.aGui.println( "You are lost. You are alone." );
        this.aGui.println( "You wander around at the cave.\n" );
        this.aGui.println( "Your command words are:" );
        this.aGui.println( this.aParser.getCommandString() );
    } // printHelp()

    /**
     * method executed with the command word "quit" 
     * if right executed quit the game
     * @param pCom a command
     */
    public void quit( final Command pCom )
    {
        if ( pCom.hasSecondWord() )
                this.aGui.println( "Quit what?" );
        else{
            this.aGui.println( "Thank you for playing. Good bye." );
            this.aGui.enable( false );
        }
    } // quit(.)

    /**
     * Execute test 
     * @param pCom a command
     */
    private void test ( final Command pCom )
    {
        if ( !pCom.hasSecondWord() ){
            this.aGui.println( "Test What !?" );
            return;
        }
        Scanner vScanner;
        try{
            vScanner = new Scanner( new File( "./test/" + pCom.getSecondWord() + ".txt" ) );
            while ( vScanner.hasNextLine() ){
                String vLigne = vScanner.nextLine();
                this.interpretCommand( vLigne );
            }
        }
        catch ( final FileNotFoundException pFNFE ){
            this.aGui.println( "File not found." );
        }
    } // test(.)
}// GameEngine
