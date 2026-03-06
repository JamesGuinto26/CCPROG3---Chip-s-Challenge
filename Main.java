import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main (String[] args)
    {
        Styling style = new Styling();
        style.showMainMenu(); // start here
    }

    public static void startGame(JFrame frame)
    {
        frame.dispose();
        GameSystem game = new GameSystem();
        game.startGame();
    }

    public static void exitGame(JFrame frame)
    {
        frame.dispose();
    }
}
