package pkg_game;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

import pkg_game.pkg_command.*;
import pkg_game.pkg_item.Item;
import pkg_game.pkg_item.Beamer;

/**
 * game motor
 * 
 * @author Brendan VICTOIRE
 * @version 2021.01.20 + 2021.02.17 + 2021.02.26
 */
public class GameEngine {

    private HashMap<String, Room> aRooms;
    private Parser aParser;
    private UserInterface aGui;
    private Player aPlayer;
    private Character aVeldora;
    private MovingCharacter aPinkSlime, aTabbySlime, aPhosphorSlime, aRockSlime;
    private boolean aTestMode;
    private String aNotRandomRoom;
    private int aMeeting;

    /**
     * Default constructor
     */
    public GameEngine() {
        this.aParser = new Parser();
        this.aRooms = new HashMap<String, Room>();
        this.createRooms();
        this.aTestMode = false;
    } // GameEngine()

    /**
     * create and declare Rooms and Items
     */
    private void createRooms() {
        Room vSpawn = new Room("somewhere in a cave asking yourself where is Beatrix.", "spawn");
        Room vCave2 = new Room("somewhere in a cave.", "cave2");
        Room vCave5 = new Room("somewhere in a cave. You see a corridor that you can't reach", "cave5");
        Room vCave6 = new Room("somewhere in a cave and your sense tells you that there is something hidden.", "cave6");
        Room vCave7 = new Room("somewhere in a cave. Be careful if you jump, you won't be able to come back here.",
                "cave7");
        Room vCave8 = new Room("somewhere in a cave. You have crossed the secret path.", "cave8");
        Room vUpFlower = new Room("somewhere in a cave with a flower floor.", "upFlower");
        Room vFlower2 = new Room("somewhere in a cave with a flower floor.", "flower2");
        Room vFlowerTrail = new Room("somewhere in a cave with a flower floor.", "flowerTrail");
        Room vOnMagistone = new Room("somewhere in a cave on a Magistone.", "onMagistone");
        Room vBottomOfLake = new Room("deep down in a lake.", "bottomOfLake");
        Room vSurfaceOfLake = new Room("swimming on the surface of a lake.", "surfaceOfLake");
        Room vLake = new Room("on the surface of a lake.", "lake");
        Room vJumpToDragon = new Room("somewhere in a cave asking yourself if you should jump.", "jumpToDragon");
        Room vMeetTempest = new Room("somewhere in a cave you met Veldora.", "meetTempest");
        Room vSeeTheDoor = new Room("somewhere in a cave and you see a door.", "seeTheDoor");
        Room vDoor = new Room("looking at the door.", "door");
        Room vGetOut = new Room("opening the door.", "getOut");
        Room vOutside = new Room("finally outside.", "outside");
        Room vMeetBeatrix = new Room(
                "finally back to Beatrix. \nThanks for playing! \nYou can keep moving around or leave the game as you please.",
                "meetBreeder");
        Room vSomewhere = new Room("somewhere.", "somewhere");

        Item vStone = new Item("stone", "just a stone, what did you expect !?", 4.69);
        Item vMagistone = new Item("magistone", "a magic stone, can it be useful somewhere? ", 35.82);
        Item vFlower = new Item("flower", "a beautiful flower", 0.026);
        Item vDragonSoul = new Item("dragonSoul", "the soul of Veldora", 0);
        Item vTree = new Item("tree", "just a tree and yes you can carry it you are a slime after all", 735);

        Beamer vBeamer = new Beamer();

        Door vSecretPassage = new Door(vFlower);
        Door vPassTheDoor = new Door(vDragonSoul);

        this.aRooms.put("spawn", vSpawn);
        this.aRooms.put("cave2", vCave2);
        this.aRooms.put("cave5", vCave5);
        this.aRooms.put("cave6", vCave6);
        this.aRooms.put("cave7", vCave7);
        this.aRooms.put("cave8", vCave8);
        this.aRooms.put("upFlower", vUpFlower);
        this.aRooms.put("flower2", vFlower2);
        this.aRooms.put("flowerTrail", vFlowerTrail);
        this.aRooms.put("onMagistone", vOnMagistone);
        this.aRooms.put("bottomOfLake", vBottomOfLake);
        this.aRooms.put("surfaceOfLake", vSurfaceOfLake);
        this.aRooms.put("lake", vLake);
        this.aRooms.put("jumpToDragon", vJumpToDragon);
        this.aRooms.put("meetTempest", vMeetTempest);
        this.aRooms.put("seeTheDoor", vSeeTheDoor);
        this.aRooms.put("door", vDoor);
        this.aRooms.put("getOut", vGetOut);
        this.aRooms.put("outside", vOutside);
        this.aRooms.put("meetBeatrix", vMeetBeatrix);
        this.aRooms.put("somewhere", vSomewhere);

        vSpawn.setExit("south", vCave2);
        vSpawn.setItem(vStone);
        vSpawn.setItem(vBeamer);

        vCave2.setExit("north", vSpawn);
        vCave2.setExit("south", vUpFlower);
        vCave2.setItem(vMagistone);
        vCave2.setItem(vFlower);

        vCave5.setExit("north", vLake);
        vCave5.setExit("south", vSeeTheDoor);

        vCave6.setExit("west", vFlowerTrail);
        vCave6.setDoors("south", vSecretPassage);
        vCave6.setExit("south", vCave8);
        vCave6.setItem(vMagistone);

        vCave7.setExit("north", vOnMagistone);
        vCave7.setExit("south", vFlowerTrail);
        vCave7.setExit("down", vBottomOfLake);

        vCave8.setExit("down", vCave5);
        vCave8.setDoors("north", vSecretPassage);
        vCave8.setExit("north", vCave6);
        vCave8.setItem(vMagistone);

        vUpFlower.setExit("north", vCave2);
        vUpFlower.setExit("west", vOnMagistone);
        vUpFlower.setExit("down", vFlower2);

        vFlower2.setExit("up", vUpFlower);
        vFlower2.setItem(vFlower);

        vFlowerTrail.setExit("north", vCave7);
        vFlowerTrail.setExit("east", vCave6);
        vFlowerTrail.setExit("west", vJumpToDragon);
        vFlowerTrail.setItem(vFlower);

        vOnMagistone.setExit("south", vCave7);
        vOnMagistone.setExit("east", vUpFlower);
        vOnMagistone.setItem(vMagistone);

        vBottomOfLake.setExit("up", vSurfaceOfLake);

        vSurfaceOfLake.setExit("south", vLake);
        vSurfaceOfLake.setExit("down", vBottomOfLake);

        vLake.setExit("north", vSurfaceOfLake);
        vLake.setExit("south", vCave5);

        vJumpToDragon.setExit("south", vMeetTempest);
        vJumpToDragon.setExit("east", vFlowerTrail);

        vMeetTempest.setExit("north", vJumpToDragon);
        this.aVeldora = new Character("Veldora", vMeetTempest);
        aVeldora.setItem(vDragonSoul);

        vSeeTheDoor.setExit("north", vCave5);
        vSeeTheDoor.setExit("west", vDoor);
        vSeeTheDoor.setItem(vMagistone);

        vDoor.setExit("east", vSeeTheDoor);
        vDoor.setDoors("west", vPassTheDoor);
        vDoor.setExit("west", vGetOut);

        vGetOut.setDoors("east", vPassTheDoor);
        vGetOut.setExit("east", vDoor);
        vGetOut.setExit("west", vOutside);

        vOutside.setExit("east", vGetOut);
        vOutside.setExit("west", vMeetBeatrix);
        vOutside.setItem(vTree);

        vMeetBeatrix.setExit("east", vOutside);
        vMeetBeatrix.setExit("west", vSomewhere);
        this.aPinkSlime = new MovingCharacter("Pink_Slime", vMeetBeatrix);
        this.aTabbySlime = new MovingCharacter("Tabby_Slime", vMeetBeatrix);
        this.aPhosphorSlime = new MovingCharacter("Phosphor_Slime", vMeetBeatrix);
        this.aRockSlime = new MovingCharacter("Rock_Slime", vMeetBeatrix);

        this.aPlayer = new Player();
        this.aPlayer.setCurrentRoom(vSpawn);
        this.aPlayer.getPreviousRooms().push(new Room("... Wait! Where are you?", ""));

        this.aMeeting = 0;
    } // createRooms()

    /**
     * interpret commands stop the game if you have executed to much command
     * 
     * @param pCom a command
     */
    public void interpretCommand(final String pCom) {
        this.aGui.println("\n" + "> " + pCom);
        Command vCom = this.aParser.getCommand(pCom);

        if (!this.aTestMode)
            if (this.aPlayer.commandCounter()) {
                this.aGui.println("You are too tired to continue.");
                vCom = new QuitCommand();
                vCom.execute(vCom, this);
                return;
            }

        if (vCom == null) {
            aGui.println("What ?");
            return;
        } else {
            vCom.execute(vCom, this);
            this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName());
        }
        /*
         * switch (vCW) { case UNKNOWN:
         * this.aGui.println("I don't know what you mean..."); break; case GO:
         * this.goRoom(vCom); break; case BACK: this.goBack(vCom); break; case MEMORIZE:
         * this.memorize(vCom); break; case TELEPORT: this.teleport(vCom); break; case
         * LOOK: this.look(vCom); break; case EAT: this.eat(vCom); break; case ITEMS:
         * this.Items(vCom); break; case TAKE: this.take(vCom); break; case DROP:
         * this.drop(vCom); break; case HELP: this.printHelp(); break; case QUIT:
         * this.quit(vCom); break; case TEST: this.test(vCom); break; case ALEA:
         * this.alea(vCom); break; }
         */
    } // interpretCommand(.)

    /**
     * print welcome message
     */
    private void printWelcome() {
        this.aGui.println("Welcome to Searching for the slime breeder");
        this.aGui.println("Searching for the slime breeder is a new, incredibly boring adventure game.");
        this.aGui.println("Type \' " + CommandWord.HELP + " \' if you need help. \n");
        this.aPlayer.printLocationInfo();
    } // printWelcome()

    /**
     * access to the player
     * 
     * @return the player
     */
    public Player getPlayer() {
        return this.aPlayer;
    } // getPlayer()

    /**
     * access to the Room define by alea
     * 
     * @return the Room define by alea
     */
    public String getNotRandomRoom() {
        return this.aNotRandomRoom;
    } // getNotRandom

    /**
     * set the Room define by alea
     * 
     * @param pRoom room define by alea
     */
    public void setNotRandomRoom(final String pRoom) {
        this.aNotRandomRoom = pRoom;
    } // setNotRandom

    /**
     * acces to veldora
     * 
     * @return veldora
     */
    public Character getVeldora() {
        return this.aVeldora;
    } // getVeldora()

    /**
     * nullify veldora
     */
    public void nullVeldora() {
        this.aVeldora = null;
    } // nullVeldora()

    /**
     * access to a moving character
     * 
     * @param pName name of the moving character
     * @return a moving character
     */
    public MovingCharacter getMovingCharacter(final String pName) {
        switch (pName) {
            case "PhosphorSlime":
                return this.aPhosphorSlime;
            case "PinkSlime":
                return this.aPinkSlime;
            case "RockSlime":
                return this.aRockSlime;
            case "TabbySlime":
                return this.aTabbySlime;
            default:
                return null;
        }
    }

    /**
     * access to the number of meeting
     * 
     * @return the number of meeting
     */
    public int getNumberOfMeeting() {
        return this.aMeeting;
    } // getNumberOfMeeting()

    /**
     * add one to the number of meeting
     */
    public void setNumberOfMeeting() {
        this.aMeeting++;
    } // setNumberOfMeeting()

    /**
     * acces to all the rooms
     * 
     * @return all the rooms
     */
    public HashMap<String, Room> getRooms() {
        return this.aRooms;
    } // getRooms()

    /**
     * access to the user interface
     * 
     * @return user interface
     */
    public UserInterface getGui() {
        return this.aGui;
    } // getGui()

    /**
     * set the GUI
     * 
     * @param pUserInterface the user interface
     */
    public void setGUI(final UserInterface pUserInterface) {
        this.aGui = pUserInterface;
        this.aPlayer.setGUI(this.aGui);
        this.printWelcome();
    } // setGUI(.)

    /**
     * access to parsers
     * 
     * @return parsers
     */
    public Parser getParser() {
        return this.aParser;
    } // getParser()

    /**
     * know if test mode is enabled
     * 
     * @return true iif test mode is enabled
     */
    public boolean getTestMode() {
        return this.aTestMode;
    } // getTestMode(.)

    /**
     * set test mode
     * 
     * @param pTestMode true if test mode is enabled, false otherwise
     */
    public void setTestMode(boolean pTestMode) {
        this.aTestMode = pTestMode;
    } // setTestMode(.)

    /**
     * get Tabby, Phosphor, Pink and Rock to follow the player
     */
    public void slimeFollowing() {
        if (this.aMeeting == 1) {
            this.aPinkSlime.moveCharacter(this.aPlayer.getCurrentRoom());
            this.aTabbySlime.moveCharacter(this.aPlayer.getCurrentRoom());
            this.aPhosphorSlime.moveCharacter(this.aPlayer.getCurrentRoom());
            this.aRockSlime.moveCharacter(this.aPlayer.getCurrentRoom());
            ArrayList<String> vFollowing = new ArrayList<String>();
            vFollowing.add("Tabby and the others are following you ^_^ !!");
            vFollowing.add("Phosphor and the others are right by your side!");
            vFollowing.add("Pink and the others are just behind you.");
            vFollowing.add("Rock and the others are still there.");
            this.aGui.println(vFollowing.get(new Random().nextInt(vFollowing.size())));
        }
    } // slimeFollowing()
}// GameEngine
