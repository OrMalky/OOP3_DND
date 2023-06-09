package FrontEnd;

import Backend.GameManager;
import Backend.RenderCallBack;
import java.util.Scanner;

public class GameCLI implements RenderCallBack {
    private GameManager gameManager;
    private Scanner scanner;

    public GameCLI(GameManager gameManager) {
        this.gameManager = gameManager;
        gameManager.setRenderCallback(this);
        this.scanner = new Scanner(System.in);

    }

    public void start() {
        String StartCommand;
        String command = "";
        printOpeningScreen();

        while (!gameManager.isGameStarted()) {
            StartCommand = scanner.nextLine();
            gameManager.startGame(StartCommand);

        }

        // Game loop
        while (!gameManager.isOver()) {
            // Display the game state
            // displayGameState();

            // Process user input
            processCommand(command);
            command = scanner.nextLine();
            clearConsole();

        }

        System.out.println("Game Over!");
    }

    private void processCommand(String command) {
        gameManager.handleInput(command);
    }

    @Override
    public void renderScreen(String output) {
        System.out.println(output);
    }

    @Override
    public void renderPlayerBar(String output) {
        System.out.println(output);
    }

    @Override
    public void renderSystemMessage(String output) {
        System.out.println(output);
    }

    public void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                // For Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // For Linux and macOS
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            // Handle any exceptions that may occur
            e.printStackTrace();
        }
    }

    public void printOpeningScreen() {
        System.out.println("Welcome to the game!");
        System.out.println("Choose your player: ");
        System.out.println(getPlayerSelectionString());
    }

    public String getPlayerSelectionString() {
        StringBuilder selectionString = new StringBuilder("Select player:\n");
        selectionString.append(
                "1. Jon Snow             Health: 300/300         Attack: 30              Defense: 4              Level: 1                Experience: 0/50                Cooldown: 0/3\n");
        selectionString.append(
                "2. The Hound            Health: 400/400         Attack: 20              Defense: 6              Level: 1                Experience: 0/50                Cooldown: 0/5\n");
        selectionString.append(
                "3. Melisandre           Health: 100/100         Attack: 5               Defense: 1              Level: 1                Experience: 0/50                Mana: 75/300            Spell Power: 15\n");
        selectionString.append(
                "4. Thoros of Myr        Health: 250/250         Attack: 25              Defense: 4              Level: 1                Experience: 0/50                Mana: 37/150            Spell Power: 20\n");
        selectionString.append(
                "5. Arya Stark           Health: 150/150         Attack: 40              Defense: 2              Level: 1                Experience: 0/50                Energy: 100/100\n");
        selectionString.append(
                "6. Bronn                Health: 250/250         Attack: 35              Defense: 3              Level: 1                Experience: 0/50                Energy: 100/100\n");
        selectionString.append(
                "7. Ygritte              Health: 220/220         Attack: 30              Defense: 2              Level: 1                Experience: 0/50                Arrows: 10              Range: 6\n");

        return selectionString.toString();
    }

}
