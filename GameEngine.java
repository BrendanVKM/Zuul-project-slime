import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * game motor 
 * @author Brendan VICTOIRE
 * @version 2021.01.20 + 2021.02.17
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
     * create and declare Room
     */
    private void createRooms()
    {
        Room vSpawn = new Room( "somewhere in a cave asking yourself where is Beatrix.", "spawn.jpg" );
        Room vCave2 = new Room( "somewhere in a cave.", "cave2.jpg" );
        Room vCave5 = new Room( "somewhere in a cave.", "cave5.jpg" );
        Room vCave6 = new Room( "somewhere in a cave and your sense tells you that there is something hidden."
            , "cave6.jpg" );
        Room vCave7 = new Room( "somewhere in a cave. Be careful if you jump, you won't be able to come back here.", "cave7.jpg" );
        Room vCave8 = new Room( "somewhere in a cave.", "cave8.jpg" );
        Room vUpFlower = new Room( "somewhere in a cave with a flower floor.", "upFlower.jpg" );
        Room vFlower2 = new Room( "somewhere in a cave with a flower floor.", "flower2.jpg");
        Room vFlowerTrail = new Room( "somewhere in a cave with a flower floor.", "flowerTrail.jpg" );
        Room vOnMagistone = new Room( "somewhere in a cave on a Magistone.", "onMagistone.jpg" );
        Room vBottomOfLake = new Room( "deep down in a lake.", "bottomOfLake.jpg" );
        Room vSurfaceOfLake = new Room( "swimming on the surface of a lake.", "surfaceOfLake.jpg" );
        Room vLake = new Room( "on the surface of a lake.", "lake.jpg" );
        Room vJumpToDragon = new Room( "somewhere in a cave asking yourself if you should jump."
            , "jumpToDragon.jpg" );
        Room vMeetTempest = new Room( "somewhere in a cave you met Veldora.", "meetTempest.jpg" );
        Room vSeeTheDoor = new Room( "somewhere in a cave and you see a door.", "seeTheDoor.jpg" );
        Room vDoor = new Room( "looking at the door.", "door.jpg" );
        Room vGetOut = new Room( "opening the door.", "getOut.jpg" );
        Room vOutside = new Room( "finally outside.", "outside.jpg" );
        Room vMeetBeatrix = new Room( "finally back to Beatrix.", "meetBreeder.jpg" ); 
        
        Item vStone = new Item( "stone", "just a stone, what did you expect !?", 4.69);
        Item vMagistone = new Item( "magistone", "a magic stone, can it be useful somewhere? ", 35.82 );
        Item vFlower = new Item( "flower", "a beautiful flower", 0.026);
        Item vDragonSoul = new Item( "dragonSoul", "the soul of Veldora", 0);
        Item vTree = new Item( "tree", "just a tree and yes you can carry it you are a slime after all", 735);

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
        vSpawn.getItems().setItem( vStone );

        vCave2.setExit( "north", vSpawn ); 
        vCave2.setExit( "south", vUpFlower );
        vCave2.getItems().setItem( vMagistone );
        vCave2.getItems().setItem( vFlower );

        vCave5.setExit( "north", vLake );
        vCave5.setExit( "south", vSeeTheDoor );

        vCave6.setExit( "west", vFlowerTrail );
        vCave6.getItems().setItem( vMagistone );

        vCave7.setExit( "north", vOnMagistone );
        vCave7.setExit( "south", vFlowerTrail );
        vCave7.setExit( "down", vBottomOfLake);

        vCave8.setExit( "down", vCave5);
        vCave8.getItems().setItem( vMagistone );

        vUpFlower.setExit( "north", vCave2 );
        vUpFlower.setExit( "west", vOnMagistone );
        vUpFlower.setExit( "down", vFlower2);

        vFlower2.setExit( "up", vUpFlower );
        vFlower2.getItems().setItem( vFlower );

        vFlowerTrail.setExit( "north", vCave7 );
        vFlowerTrail.setExit( "east", vCave6 );
        vFlowerTrail.setExit( "west", vJumpToDragon);
        vFlowerTrail.getItems().setItem( vFlower );

        vOnMagistone.setExit( "south", vCave7);
        vOnMagistone.setExit( "east", vUpFlower );
        vOnMagistone.getItems().setItem( vMagistone );

        vBottomOfLake.setExit( "up", vSurfaceOfLake );

        vSurfaceOfLake.setExit( "south", vLake);
        vSurfaceOfLake.setExit( "down", vBottomOfLake);

        vLake.setExit( "north", vSurfaceOfLake);
        vLake.setExit( "south", vCave5 );

        vJumpToDragon.setExit( "south", vMeetTempest );
        vJumpToDragon.setExit( "east", vFlowerTrail );

        vMeetTempest.setExit( "north", vJumpToDragon );
        vFlowerTrail.getItems().setItem( vDragonSoul );

        vSeeTheDoor.setExit( "north", vCave5 );
        vSeeTheDoor.setExit( "west", vDoor );
        vSeeTheDoor.getItems().setItem( vMagistone );

        vDoor.setExit( "east", vSeeTheDoor );
        vDoor.setExit( "west", vGetOut );

        vGetOut.setExit( "east", vDoor );
        vGetOut.setExit( "west", vOutside );

        vOutside.setExit( "east", vGetOut );
        vOutside.setExit( "west", vMeetBeatrix );
        vOutside.getItems().setItem( vTree );

        vMeetBeatrix.setExit( "east", vOutside );

        this.aPlayer = new Player();
        this.aPlayer.setCurrentRoom(vSpawn);
        this.aPlayer.getPreviousRooms().push( new Room( "... Wait! Where are you?", null) );
    } // createRooms()
    
    /**
     * interpret a command
     * @param pCom a command
     */
    public void interpretCommand( final String pCom ) 
    {
        this.aGui.println( "\n" + "> " + pCom );
        Command vCom = this.aParser.getCommand( pCom );

        if ( vCom.isUnknown() ) {
            this.aGui.println( "I don't know what you mean..." );
            this.aGui.println( "" );
            return;
        }
        switch( vCom.getCommandWord().toLowerCase() ){
            case "go":
                this.aPlayer.goRoom(vCom);
                break;
            case "back":
                this.aPlayer.goBack(vCom);
                break;
            case "look":
                this.aPlayer.look(vCom);
                break;
            case "eat":
                this.aPlayer.eat(vCom);
                break;
                case "take":
                this.aPlayer.take(vCom);
                break;
            case "drop":
                this.aPlayer.drop(vCom);
                break;
            case "help":
                this.aPlayer.printHelp();
                break;
            case "quit":
                this.aPlayer.quit(vCom);
                break;
            case "test":
                test(vCom);
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
        this.aGui.println( "Type \' help \' if you need help. \n" );
        this.aPlayer.printLocationInfo();
        this.aPlayer.getPreviousRooms().pop();
    } // printWelcome()

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
    }
}// GameEngine
