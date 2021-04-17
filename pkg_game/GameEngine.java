package pkg_game;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import pkg_game.pkg_command.Parser;
import pkg_game.pkg_command.Command;
import pkg_game.pkg_command.CommandWord;
import pkg_game.pkg_item.Item;
import pkg_game.pkg_item.Beamer;

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
    private boolean aTestMode;
    private String aNotRandomRoom;

    /**
     * Default constructor 
     */
    public GameEngine()
    {
        this.aParser = new Parser();
        this.aRooms = new HashMap<String, Room>();
        this.createRooms();
        this.aTestMode = false;
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
        Room vSomewhere = new Room( "somewhere.", "somewhere" ); 
        
        Item vStone = new Item( "stone", "just a stone, what did you expect !?", 4.69);
        Item vMagistone = new Item( "magistone", "a magic stone, can it be useful somewhere? ", 35.82 );
        Item vFlower = new Item( "flower", "a beautiful flower", 0.026);
        Item vDragonSoul = new Item( "dragonSoul", "the soul of Veldora", 0);
        Item vTree = new Item( "tree", "just a tree and yes you can carry it you are a slime after all", 735);
        
        Beamer vBeamer = new Beamer();

        Door vSecretPassage = new Door( vFlower );
        Door vPassTheDoor = new Door( vDragonSoul );

        this.aRooms.put( "spawn", vSpawn );
        this.aRooms.put( "cave2", vCave2 );
        this.aRooms.put( "cave5", vCave5 );
        this.aRooms.put( "cave6", vCave6 );
        this.aRooms.put( "cave7", vCave7 );
        this.aRooms.put( "cave8", vCave8 );
        this.aRooms.put( "upFlower", vUpFlower );
        this.aRooms.put( "flower2", vFlower2 );
        this.aRooms.put( "flowerTrail", vFlowerTrail );
        this.aRooms.put( "onMagistone", vOnMagistone );
        this.aRooms.put( "bottomOfLake", vBottomOfLake );
        this.aRooms.put( "surfaceOfLake", vSurfaceOfLake );
        this.aRooms.put( "lake", vLake );
        this.aRooms.put( "jumpToDragon", vJumpToDragon );
        this.aRooms.put( "meetTempest", vMeetTempest );
        this.aRooms.put( "seeTheDoor", vSeeTheDoor );
        this.aRooms.put( "door", vDoor );
        this.aRooms.put( "getOut", vGetOut );
        this.aRooms.put( "outside", vOutside );
        this.aRooms.put( "meetBeatrix", vMeetBeatrix );
        this.aRooms.put( "somewhere", vSomewhere );

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
        vMeetTempest.setCharacter("Veldora");
        vMeetTempest.setCharacterItem(vDragonSoul);

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
        vMeetBeatrix.setExit( "west", vSomewhere );

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

        if ( !this.aTestMode )
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
            case ALEA:
                this.alea(vCom);
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
    private void goRoom( final Command pDep )
    {
        if ( !pDep.hasSecondWord() )
            this.aGui.println( "Go where ?" );
        else {
            if ( this.aPlayer.getCurrentRoom().equals( this.aRooms.get( "somewhere" ) ) ){
                ArrayList<String> vArray = new ArrayList<String>( this.aRooms.keySet() );
                if ( this.aNotRandomRoom == null )
                    this.aNotRandomRoom = vArray.get( new Random().nextInt( vArray.size() ) );
                this.aPlayer.goRandom( this.aRooms.get( this.aNotRandomRoom  ) );
                return;
            }
            String vDrct = pDep.getSecondWord(); 
            int vNumber = this.aPlayer.goRoom(vDrct);
            if ( vNumber == 0 ) this.aGui.println( "There is no corridor !" );
            else if ( vNumber == 1 ){
                this.aGui.println( "You can't pass without the dragonSoul");
            }
            if ( this.aPlayer.getCurrentRoom().equals(this.aRooms.get("meetTempest")))
                this.aGui.println( this.aPlayer.getCurrentRoom().getCharacter().PlayerEnterRoom() );
        }
    } // goRoom(.)

    /**
     * method executed with the command word "back"
     * let you go backward
     * @param pCom a command
     */
    private void goBack( final Command pCom )
    {
        if ( !pCom.hasSecondWord() ){
            if ( this.aPlayer.getPreviousRoom().getImageName().equals( "" )) 
                this.aGui.println( "You were" + this.aPlayer.getPreviousRoom().getDescription() );
            //if you are in the bottom of the lake you can't go back to the cave you have to go further
            else if ( this.aPlayer.getCurrentRoom().equals( this.aRooms.get( "bottomOfLake" ) )
                && this.aPlayer.getPreviousRoom().equals( this.aRooms.get( "cave7" ) ) ){
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
    private void memorize( final Command pCom )
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
    private void teleport( final Command pCom )
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
    private void look( final Command pCom )
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
    private void eat( final Command pCom )
    {
        if ( !pCom.hasSecondWord() )
            this.aGui.println( "What do you want to eat ?");
        else {
            String vSW = pCom.getSecondWord();
            int vNumber = this.aPlayer.eat(vSW);
            if ( vNumber == 0 )
                this.aGui.println( "You have eaten the Stone. Now you can carry more items." );
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
    private void Items( final Command pCom )
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
    private void take( final Command pCom )
    {
        if ( !pCom.hasSecondWord() )
            this.aGui.println( "Absorb what ?");
        else {
            String vSW = pCom.getSecondWord();
            int vNumber = this.aPlayer.take( vSW );
            if ( vNumber == 0 )
                        this.aGui.println( "You don't have enough space to absorb this."); 
            else if ( vNumber == 1 ){
                if ( this.aPlayer.getCurrentRoom().equals( this.aRooms.get( "onMagistone") ) && vSW.equals( "magistone" )) {
                    this.aPlayer.getCurrentRoom().setImage("takeMagistone");
                    this.aGui.showImage( this.aPlayer.getCurrentRoom().getImageName() );
                }
                this.aGui.println( "You absorb " + vSW + "\n"
                    + this.aPlayer.getCurrentRoom().getItemsString() + "\n"
                    +this.aPlayer.getInventoryString() );
            }
            else if ( vNumber == 3 ){
                this.aGui.println( this.aPlayer.getCurrentRoom().getCharacter().PlayerAbsorbCharacter() 
                + "\n You have acquired the sould of Veldora." );
                this.aPlayer.getCurrentRoom().removeCharacter();
            }
            else this.aGui.println( "What is this ? Is it there ?" );
        }
    } // take(.)

    /**
     * method executed with the command word "drop" 
     * if possible drop an item on the floor of the room
     * @param pCom a command
     */
    private void drop( final Command pCom )
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
    private void printHelp()
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
    private void quit( final Command pCom )
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
            this.aTestMode = true;
            while ( vScanner.hasNextLine() ){
                String vLigne = vScanner.nextLine();
                this.interpretCommand( vLigne );
            }
            this.aTestMode = false;
        }
        catch ( final FileNotFoundException pFNFE ){
            this.aGui.println( "File not found." );
        }
    } // test(.)

    private void alea ( final Command pCom )
    {
        if ( !this.aTestMode )
            this.aGui.println( "You need to be in the test mode!" );
        else {
            if ( !pCom.hasSecondWord() ){
                this.aNotRandomRoom = null;
                this.aGui.println( "the randomness of the transporter room has been reset." );
            }
            else {
                this.aNotRandomRoom = pCom.getSecondWord();
        }
    }
    }
}// GameEngine
