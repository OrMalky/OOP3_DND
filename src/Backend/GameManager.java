package Backend;

import Backend.GameBoard.Direction;

public class GameManager {
    GameBoard board;
    private RenderCallBack renderCallBack;
    boolean isOver;
    CharacterFactory cf;
    String systemMessage;
    boolean gameStarted;

    public GameManager() {
        isOver = false;
        gameStarted = false;
        cf = new CharacterFactory();
        String systemMessage = "";
    }

    public void startGame(String number) {
        try {
            board = new GameBoard(cf.createPlayer(number), cf);
            board.initializeBoard();
            systemMessage = "Game started";
            renderCallBack.renderSystemMessage(systemMessage);
            gameStarted = true;
        } catch (Exception e) {
            systemMessage = "Invalid input: " + number + " try again";
            renderCallBack.renderSystemMessage(systemMessage);

        }
    }

    public void handleInput(String input) {
        switch (input) {
            case "w":
                systemMessage = board.movePlayer(Direction.UP);
                break;
            case "a":
                systemMessage = board.movePlayer(Direction.LEFT);
                break;
            case "s":
                systemMessage = board.movePlayer(Direction.DOWN);
                break;
            case "d":
                systemMessage = board.movePlayer(Direction.RIGHT);
                break;
            case "q":
                break;
            case "e":
                systemMessage = board.castSpecialAbility();
                break;
            case "":
                break;
            default:
                systemMessage = ("Invalid input: " + input + " try again");
                break;
        }
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
            renderCallBack.renderSystemMessage(systemMessage);
        }
    }

    public void setRenderCallback(RenderCallBack renderCallBack) {
        this.renderCallBack = renderCallBack;
    }

}
