package TicTacToe.gui;

import TicTacToe.GameControler.GameController;
import TicTacToe.GameControler.MultiplayerClientController;
import TicTacToe.Main;

import javax.swing.*;
import javax.websocket.DeploymentException;
import java.io.IOException;

public class MultiplayerClientGameScreen extends GameScreen {

    public MultiplayerClientGameScreen() throws IOException, DeploymentException {
        super();

        Main.game = new MultiplayerClientController(this, GameController.Tile.O);
    }

    /**
     * Stop game and close connection
     */
    @Override
    public void stopGame() {
        if (Main.game != null)
            ((MultiplayerClientController) Main.game).closeConnection();
        super.stopGame();
    }

    /**
     * Display end state in controller
     * @param game Current GameController
     */
    @Override
    public void gameOver(GameController game) {
        updateScreen(game);

        switch (game.getCurrentState()) {
            case END_DRAW:
                JOptionPane.showMessageDialog(this, "Draw!");
                break;
            case END_CIRCLE:
                JOptionPane.showMessageDialog(this, "You lost!");
                break;
            case END_CROSS:
                JOptionPane.showMessageDialog(this, "You Won!");
                break;
        }
        stopGame();
    }

    /**
     * Update screen and current turn
     * @param game Current GameController
     */
    @Override
    public void updateScreen(GameController game) {
        super.updateScreen(game);

        if (game.getCurrentState() == GameController.State.CROSS)
            currentTurn.setText("Your turn");
        else if (game.getCurrentState() == GameController.State.CIRCLE)
            currentTurn.setText("Other player");
    }
}
