package Backend;

import java.util.ArrayList;

import Backend.GameBoard.Direction;

public class GameManager implements MassageCallBack {
    GameBoard board;
    private RenderCallBack renderCallBack;
    boolean isOver;
    CharacterFactory cf;
    ArrayList<String> systemMessages;
    boolean gameStarted;

    public GameManager() {
        isOver = false;
        gameStarted = false;

        cf = new CharacterFactory(this);
        systemMessages = new ArrayList<String>();
    }

    public void startGame(String number) {
        try {
            board = new GameBoard(cf.createPlayer(number), cf, this);
            board.initializeBoard();
            systemMessages.add("Game started");

            gameStarted = true;
        } catch (Exception e) {
            systemMessages.add("Invalid input: " + number + " try again");

        }
    }

    public void handleInput(String input) {
        switch (input) {
            case "w":
                board.movePlayer(Direction.UP);
                break;
            case "a":
                board.movePlayer(Direction.LEFT);
                break;
            case "s":
                board.movePlayer(Direction.DOWN);
                break;
            case "d":
                board.movePlayer(Direction.RIGHT);
                break;
            case "q":
                break;
            case "e":
                board.castSpecialAbility();
                break;
            case "":
                break;
            default:
                systemMessages.add("Invalid input: " + input + " try again");
                break;
        }
        board.tick();
        updateCLI();
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public boolean isOver() {
        return isOver;
    }

    public void updateCLI() {
        if (renderCallBack != null) {
            renderCallBack.renderPlayerBar(board.getPlayerInfo());
            renderCallBack.renderScreen(board.getBoardAsString());
            renderSystemMessages();
        }
    }

    public void renderSystemMessages() {
        if (renderCallBack != null) {
            for (String message : systemMessages) {
                renderCallBack.renderSystemMessage(message);
            }
            systemMessages.clear();
        }
    }

    public void setRenderCallback(RenderCallBack renderCallBack) {
        this.renderCallBack = renderCallBack;
    }

    @Override
    public void addSystemMassage(String message) {
        systemMessages.add(message);
    }

}
