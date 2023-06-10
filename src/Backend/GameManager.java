package Backend;

import java.util.Scanner;

import Backend.GameBoard.Direction;
import Frontend.GameCLI;

public class GameManager {
    private Scanner scanner;

    private GameBoard board;
    private GameCLI gameCLI;
    private CharacterFactory cf;
    
    private Player player;

    private boolean gameStarted;
    private boolean isOver;

    private final String UP = "w";
    private final String DOWN = "s";
    private final String LEFT = "a";
    private final String RIGHT = "d";
    private final String WAIT = "q";
    private final String SPECIAL_ABILITY = "e";
    
    public GameManager() {
        isOver = false;
        gameStarted = false;

        cf = new CharacterFactory();
        scanner = new Scanner(System.in);
        gameCLI = new GameCLI();
    }

    public void startGame() {
        CharacterFactory.Character playerCharacter = characterSelection();
        player = cf.choosePlayer(playerCharacter);
        board = new GameBoard(cf, player);
        board.parseLevel(1);
        gameLoop();
    }

    private CharacterFactory.Character characterSelection(){
        CharacterFactory.Character character = null;
        int input = 0;
        do {
            gameCLI.clearConsole();
            gameCLI.printOpeningScreen(cf.getCharacters());
            try{
                input = scanner.nextInt();
                character = CharacterFactory.Character.values()[input - 1];
                gameStarted = true;
            } catch (Exception e) {
                scanner.nextLine();
                gameCLI.addMessage("Invalid input try again");
            }
        } while (!gameStarted);
        return character;
    }

    public void gameLoop() {
        while (!isOver) {
            boolean isValid = false;
            do{
                gameCLI.clearConsole();
                gameCLI.renderPlayerBar(player);
                gameCLI.renderBoard(board);
                gameCLI.printMessages();
                gameCLI.render("Choose an action: ");
                String input = scanner.next();
                isValid = handleInput(input);
            } while (!isValid);
            board.tick();
        }
    }

    public boolean handleInput(String input) {
        Direction dir = null;
        switch (input) {
            case UP:
                dir = Direction.UP;
                break;
            case DOWN:
                dir = Direction.DOWN;
                break;
            case LEFT:
                dir = Direction.LEFT;
                break;
            case RIGHT:
                dir = Direction.RIGHT;
                break;
            case WAIT:
                return true;
            case SPECIAL_ABILITY:
                board.castSpecialAbility();
                break;
            default:
                gameCLI.addMessage("Invalid input " + input + " try again");
                return false;
        }
        return playInput(dir);
    }

    private boolean playInput(Direction direction) {
        if(!board.interact(direction))
        {
            gameCLI.addMessage("Invalid move");
            return false;
        }
        return true;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public boolean isOver() {
        return isOver;
    }
}
