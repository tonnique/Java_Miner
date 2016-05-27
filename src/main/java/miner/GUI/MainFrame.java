/**
 * Этот класс представляет собой окно приложения с игрой и является интерфесом, -
 * Класс содержит объект gameLogic
  */

package miner.GUI;

/**
 * Created by Antony on 25.05.2016.
 */

import logic.GameLogic;
import miner.IGameLogic;
import miner.IUserInterface;

import javax.swing.*;
import java.awt.*;



public class MainFrame extends JFrame implements IUserInterface {
    private static int WIDTH = 400;
    private static int HEIGHT = 400;

    private IGameLogic game;
    private UserAction listener;
    private JLabel lblMinesCount;

    private MineField mineField;


    public  MainFrame()
    {
        this("Miner", WIDTH, HEIGHT);
    }

    /**
     * Constructor with 3 patameters
     * @param title - title for the application frame
     * @param width
     * @param height
     */
    public MainFrame(String title, int width, int height)
    {
        super(title);
        setSize(width, height);
        SetFrameOnCenter(this);

        StartGame();
        listener = new UserAction(game, this);
        mineField.addMouseListener(listener);
        addStartGameMenu();
        addControls();

        setResizable(false);
        pack();

    }

    /**
     * Метод создает объект типа GameLogic
     * а так же минное поле (MineField), при создании которого в конструктор
     * передается размеры минного поля, определенные в логике игры (gameLogic)
     * После создания минное поля (MineField) ссылка на этот объект передается
     * в объект gameLogic и вызвается метод initGame объекта gameLogic
     */
    public  void StartGame()
    {
        game = new GameLogic();
        game.setUserInterface(this);
        mineField = new MineField(game.getHeight(), game.getWidth());
        game.setMineField(mineField);
        game.initGame();
    }

    /**
     * Puts a Frame on the Center of the Screen
     * @param f is a Frame
     */
    public void SetFrameOnCenter(JFrame f) {
        Dimension us = f.getSize();
        Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
        int newX = (them.width - us.width) / 2;
        int newY = (them.height - us.height) / 2;
        f.setLocation(newX, newY);
    }

    private void addStartGameMenu()
    {
        JMenuBar aMenuBar = new JMenuBar();
        JMenuItem aMenuItem = new JMenuItem("Start new game");
        aMenuItem.setActionCommand("aMenuItem");
        aMenuItem.addActionListener(listener);

        aMenuBar.add(aMenuItem);
        this.setJMenuBar(aMenuBar);
    }

    private void addControls()
    {
        Container c = this.getContentPane();
        c.add(mineField, BorderLayout.CENTER);

        JPanel statusPanel = new JPanel();
        lblMinesCount = new JLabel();
        setStatusText(game.getMinesNumber());

        statusPanel.setLayout(new BorderLayout());
        statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        statusPanel.add(lblMinesCount, BorderLayout.WEST);

        c.add(statusPanel, BorderLayout.SOUTH);

    }

    public void win() { JOptionPane.showMessageDialog(null, "You win!.");    }

    public void lose() {
        JOptionPane.showMessageDialog(null, "Boom!, You lose.");
    }

    public void  showErrorMessage(String s)
    {
        JOptionPane.showMessageDialog(null, s);
    }

    public void setStatusText(int flagsLeft)
    {
        lblMinesCount.setText(flagsLeft +  " Mines left");
    }

}
