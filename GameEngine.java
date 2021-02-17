import java.util.HashMap;

/**
 * game motor 
 * @author Brendan VICTOIRE
 * @version 2021.01.20 + 2021.02.08 + 2021.02.09
 */
public class GameEngine
{

    private HashMap<Room, String> aRooms;
    private Room aCurrentRoom;
    private Parser aParser;
    private UserInterface aGui;

    /**
     * Default constructor 
     */
    public GameEngine()
    {
        this.aParser = new Parser();
        this.aRooms = new HashMap<Room, String>();
        this.createRooms();
    } // GameEngine()

    /**
     * set the GUI
     */
    public void setGUI( final UserInterface pUserInterface )
    {
        this.aGui = pUserInterface;
        this.printWelcome();
    } // setGUI()
    
    /**
     * create and declare Room
     */
    private void createRooms()
    {
        Room vSpawn = new Room( "somewhere in a cave.", "spawn.jpg" );
        Room vCave2 = new Room( "somewhere in a cave.", "cave2.jpg" );
        Room vCave5 = new Room( "somewhere in a cave.", "cave5.jpg" );
        Room vCave6 = new Room( "somewhere in a cave and your sense tells you that there is something hidden."
            , "cave6.jpg" );
        Room vCave7 = new Room( "somewhere in a cave.", "cave7.jpg" );
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
        
        this.aRooms.put( vSpawn, vSpawn.getDescription() );
        this.aRooms.put( vCave2, vCave2.getDescription() );
        this.aRooms.put( vCave5, vCave5.getDescription() );
        this.aRooms.put( vCave6, vCave6.getDescription() );
        this.aRooms.put( vCave7, vCave7.getDescription() );
        this.aRooms.put( vCave8, vCave8.getDescription() );
        this.aRooms.put( vUpFlower, vUpFlower.getDescription() );
        this.aRooms.put( vFlower2, vFlower2.getDescription() );
        this.aRooms.put( vFlowerTrail, vFlowerTrail.getDescription() );
        this.aRooms.put( vOnMagistone, vOnMagistone.getDescription() );
        this.aRooms.put( vBottomOfLake, vBottomOfLake.getDescription() );
        this.aRooms.put( vSurfaceOfLake, vSurfaceOfLake.getDescription() );
        this.aRooms.put( vLake, vLake.getDescription() );
        this.aRooms.put( vJumpToDragon, vJumpToDragon.getDescription() );
        this.aRooms.put( vMeetTempest, vMeetTempest.getDescription() );
        this.aRooms.put( vSeeTheDoor, vSeeTheDoor.getDescription() );
        this.aRooms.put( vDoor, vDoor.getDescription() );
        this.aRooms.put( vGetOut, vGetOut.getDescription() );
        this.aRooms.put( vOutside, vOutside.getDescription() );
        this.aRooms.put( vMeetBeatrix, vMeetBeatrix.getDescription() );

        vSpawn.setExit( "south", vCave2 );
        vCave2.setExit( "north", vSpawn ); 
        vCave2.setExit( "south", vUpFlower );
        vCave5.setExit( "north", vLake );
        vCave5.setExit( "south", vSeeTheDoor );
        vCave6.setExit( "west", vFlowerTrail );
        vCave7.setExit( "north", vOnMagistone );
        vCave7.setExit( "south", vFlowerTrail );
        vCave7.setExit( "down", vBottomOfLake);
        vCave8.setExit( "down", vCave5);
        vUpFlower.setExit( "north", vCave2 );
        vUpFlower.setExit( "west", vOnMagistone );
        vUpFlower.setExit( "down", vFlower2);
        vFlower2.setExit( "up", vUpFlower );
        vFlowerTrail.setExit( "north", vCave7 );
        vFlowerTrail.setExit( "east", vCave6 );
        vFlowerTrail.setExit( "west", vJumpToDragon);
        vOnMagistone.setExit( "south", vCave7);
        vOnMagistone.setExit( "east", vUpFlower );
        vBottomOfLake.setExit( "up", vSurfaceOfLake );
        vSurfaceOfLake.setExit( "south", vLake);
        vSurfaceOfLake.setExit( "down", vBottomOfLake);
        vLake.setExit( "north", vSurfaceOfLake);
        vLake.setExit( "south", vCave5 );
        vJumpToDragon.setExit( "south", vMeetTempest );
        vJumpToDragon.setExit( "east", vFlowerTrail );
        vMeetTempest.setExit( "north", vJumpToDragon );
        vSeeTheDoor.setExit( "north", vCave5 );
        vSeeTheDoor.setExit( "west", vDoor );
        vDoor.setExit( "east", vSeeTheDoor );
        vDoor.setExit( "west", vGetOut );
        vGetOut.setExit( "east", vDoor );
        vGetOut.setExit( "west", vOutside );
        vOutside.setExit( "east", vGetOut );
        vOutside.setExit( "west", vMeetBeatrix );
        vMeetBeatrix.setExit( "east", vOutside );

        Item VMagistone = new Item( "Magistone", "a magic rock", 35 );
        vOnMagistone.setItem( "Magistone", VMagistone ); 
        
        this.aCurrentRoom = vSpawn;
    } // createRooms()
    
    /**
     * interpret a command
     * @param a command
     */
    public void interpretCommand( final String pCom ) 
    {
        this.aGui.println( "> " + pCom );
        Command vCom = this.aParser.getCommand( pCom );

        if ( vCom.isUnknown() ) {
            this.aGui.println( "I don't know what you mean..." );
            this.aGui.println( "" );
            return;
        }
        String vComWord = vCom.getCommandWord().toLowerCase();

        if ( vComWord.toLowerCase().equals("help") )
            printHelp();
        else if ( vComWord.toLowerCase().equals("go") )
            goRoom(vCom);
        else if ( vComWord.toLowerCase().equals("look") )
            look(vCom);
        else if ( vComWord.toLowerCase().equals("eat") )
            eat(vCom);
        else if ( vComWord.equals("quit") )
            if ( vCom.hasSecondWord() )
                this.aGui.println( "Quit what?" );
            else
                quit();
    } // interpretCommand()
    
    /**
     * print welcome message
     */
    private void printWelcome()
    {
        this.aGui.println( "Welcome to Searching for the slime breeder" );
        this.aGui.println( "Searching for the slime breeder is a new, incredibly boring adventure game." );
        this.aGui.println( "Type \' help \' if you need help. \n" );
        printLocationInfo();
    } // printWelcome()

    /**
     * method exected with the command word "go"
     * @param command to move to the next
     */
    private void goRoom(final Command pDep)
    {
        if ( !pDep.hasSecondWord() ){
            this.aGui.println( "Go where ?" );
            return;
        }
        else {
            String vDrct = pDep.getSecondWord().toLowerCase(); 
            Room vNextRoom = this.aCurrentRoom.getExit(vDrct);
            if ( vNextRoom == null ) this.aGui.println( "There is no door ! \n" );
            else {
                this.aCurrentRoom = vNextRoom;
                printLocationInfo();
            }
        }
    } // goRoom()

    /**
     * help message
     */
    private void printHelp()
    {
        this.aGui.println( "You are lost. You are alone." );
        this.aGui.println( "You wander around at the cave.\n" );
        this.aGui.println( "Your command words are:" );
        this.aGui.println( aParser.getCommandString() );
        this.aGui.println( "" );
    } // printHelp()

    /**
     * look 
     * @param a command
     */
    private void look( final Command pCom )
    {
        String vSW = pCom.getSecondWord(); 
        if ( pCom.hasSecondWord() )
            if (vSW.equals( this.aCurrentRoom.getItem(vSW).getName() ) ) 
            this.aGui.println( this.aCurrentRoom.getItem(vSW).toString() + "\n");
            else this.aGui.println ( "I don't know what this is! \n" );
        else this.aGui.println( this.aCurrentRoom.getLongDescription() + "\n" +
                                this.aCurrentRoom.getItemString()  + "\n");
    } // look()

    /**
     * eat
     * @param a command
     */
    private void eat( final Command pCom )
    {
        if ( !pCom.hasSecondWord() )
            this.aGui.println( "What do you want to eat ?" + "\n" );
        else this.aGui.println( "You have eaten now and you are not hungry any more." + "\n");
    } // eat()

    /**
     * print location info
     */
    private void printLocationInfo()
    {
        this.aGui.print( this.aCurrentRoom.getLongDescription() + "\n" );
        if ( this.aCurrentRoom.getImageName() != null )
            this.aGui.showImage( this.aCurrentRoom.getImageName() );
        this.aGui.println("");
    } // printLocationInfo()
    
    /**
     * quit the game
     * @param a command 
     * @return true if the Command doesn't have a second word
     */
    private void quit()
    {
        this.aGui.println( "Thank you for playing.  Good bye." );
        this.aGui.enable( false );
    }
}// GameEngine
