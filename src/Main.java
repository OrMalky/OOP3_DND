
import Backend.GameManager;
import FrontEnd.GameCLI;

public class Main {
    public static void main(String[] args) {
        GameManager game = new GameManager();
        GameCLI gameCLI = new GameCLI(game);
        gameCLI.start();

    }
}