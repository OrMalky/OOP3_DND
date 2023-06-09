package Backend;

public class GameManager {
    GameBoard board;
    private RenderCallBack renderCallBack;
    boolean isOver;
    CharacterFactory cf;
    String systemMessage;

    public GameManager() {
        isOver = false;
        cf = new CharacterFactory();
        String systemMessage = "";

    }

    public void startGame(String number) {
        try {
            board = new GameBoard(cf.createPlayer(number), cf);
            board.initializeBoard();
        } catch (Exception e) {
            systemMessage = ("Invalid input: " + number + " try again");

        }
    }

    
    public void handleInput(String input) {
        switch (input) {
            case "w":
                board.movePlayerUp();

            case "a":
                board.movePlayerLeft();

            case "s":
                board.movePlayerDown();

            case "d":
                board.movePlayerRight();

            default:
                systemMessage = ("Invalid input: " + input + " try again");
                ;

        }
        updateCLI();
    }

    public boolean isOver() {
        return isOver;
    }

    public void updateCLI() {
        if (renderCallBack != null) {
            renderCallBack.renderPlayerBar(board.getPlayerInfo());
            renderCallBack.renderScreen(board.getBoardAsString());
        }
    }

    public void setRenderCallback(RenderCallBack renderCallBack) {
        this.renderCallBack = renderCallBack;
    }

}
