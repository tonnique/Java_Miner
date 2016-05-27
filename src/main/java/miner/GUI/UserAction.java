/**
 * Этот класс для взаимодействия пользователя с окном программы и
 * минным полем..
 */

package miner.GUI;

import miner.IGameLogic;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Antony on 25.05.2016.
 */
public class UserAction implements ActionListener, MouseListener {

    private IGameLogic game;
    private  MainFrame app;

    public UserAction(IGameLogic gl, MainFrame frame)
    {
        game = gl;
        app = frame;
    }

    /**
     * Обработчик нажатия кнопки
     * @param e
     */
    public void actionPerformed(ActionEvent e) {

        game.initGame();
        app.setStatusText(game.getFlagsNumber());
    }

    /**
     * Обработчик события нажатия кнопки мыши
     *
      * @param e с помощью параметра e мы определяем,
     *           какая кнопка была нажата
     */

    public void mouseClicked(MouseEvent e) {
        int y = e.getX() / Cell.CELLSIZE;
        int x = e.getY() / Cell.CELLSIZE;
        boolean flag = (e.getButton() == MouseEvent.BUTTON3) ? true: false;
        game.OpenCell(x, y , flag);

        if (flag) app.setStatusText(game.getFlagsNumber());
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }


    public void mouseExited(MouseEvent e) {

    }

}
