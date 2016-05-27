package logic;

import miner.ICell;
import miner.IGameLogic;
import miner.IMineField;
import miner.IUserInterface;
import java.util.Random;

enum Status {BEGIN, WIN, LOSE};

/**
 * Created by Antony on 25.05.2016.
 */
public class GameLogic implements IGameLogic{

    private final int MINESFIELD_HEIGHT;
    private final int MINESFIELD_WIDTH;
    private final int MINES_NUMBER ;

    private IUserInterface userInterface;

    private IMineField mineField;
    private ICell[][] cells;
    private Status s;
    private int flagsCounter ; //number of flags to mark mines


    public GameLogic()
    {
        MINESFIELD_HEIGHT = IGameLogic.MINEFIELD_HEIGHT;
        MINESFIELD_WIDTH = IGameLogic.MINEFIELD_WIDTH;
        MINES_NUMBER = IGameLogic.MINES_NUMBER;

        flagsCounter = MINES_NUMBER;
    }

    public void setUserInterface(IUserInterface ui)
    {
        userInterface = ui;
    }

    public int getMinesNumber()
    {
        return MINES_NUMBER;
    }

    public int getFlagsNumber() { return flagsCounter;}

    public int getHeight()
    {
        return MINESFIELD_HEIGHT;
    }

    public int getWidth()
    {
        return MINESFIELD_WIDTH;
    }


    /**
     * This method puts mines into the mining field
     * @param mf the mining field to be put mines on it
     */
    public void setMines(IMineField mf)
    {
        if (cells == null) {
            cells = mf.getCells();
        }
        if (cells != null) {
            int mines = 0;

            Random rand = new Random();

            do {
                int row = rand.nextInt(MINESFIELD_HEIGHT);
                int col = rand.nextInt(MINESFIELD_WIDTH);

                if (!cells[row][col].isBomb()) {
                    cells[row][col].setIsBomb(true);

                    //surrounding cells with numbers
                    if (row != 0 && col != 0)
                        cells[row - 1][col - 1].setNeibourMinesQty(cells[row - 1][col - 1].getNeibourMinesQty() + 1);
                    if (row != 0) cells[row - 1][col].setNeibourMinesQty(cells[row - 1][col].getNeibourMinesQty() + 1);
                    if (row != 0 && col != MINESFIELD_WIDTH - 1)
                        cells[row - 1][col + 1].setNeibourMinesQty(cells[row - 1][col + 1].getNeibourMinesQty() + 1);
                    if (col != 0) cells[row][col - 1].setNeibourMinesQty(cells[row][col - 1].getNeibourMinesQty() + 1);
                    if (col != MINESFIELD_WIDTH - 1)
                        cells[row][col + 1].setNeibourMinesQty(cells[row][col + 1].getNeibourMinesQty() + 1);
                    if (row != MINESFIELD_HEIGHT - 1 && col != 0)
                        cells[row + 1][col - 1].setNeibourMinesQty(cells[row + 1][col - 1].getNeibourMinesQty() + 1);
                    if (row != MINESFIELD_HEIGHT - 1)
                        cells[row + 1][col].setNeibourMinesQty(cells[row + 1][col].getNeibourMinesQty() + 1);
                    if (row != MINESFIELD_HEIGHT - 1 && col != MINESFIELD_WIDTH - 1)
                        cells[row + 1][col + 1].setNeibourMinesQty(cells[row + 1][col + 1].getNeibourMinesQty() + 1);
                    mines++;
                }
            }
            while (mines != MINES_NUMBER);
        }
        else
        {
            userInterface.showErrorMessage("Возникла ошибка в процессе растановки мин.");
        }
    }

    /**
     * This method opens the cells in the Mining Field
     * @param row
     * @param col
     */
    public void OpenCell(int row, int col, boolean flag)
    {
        if (s == Status.WIN || s == Status.LOSE) return;

        if(cells == null) {
            cells = mineField.getCells();
        }
        if (cells == null) return;

        //If user pressed Left mouse button
        if(flag == false) {

            if (cells[row][col].isFlag()) return;

            if (!cells[row][col].isUncovered() && cells[row][col].isBomb()) {
                s = Status.LOSE;
                mineField.BlastAllBombs();
                userInterface.lose();
            } else {

                if (!cells[row][col].isUncovered() && cells[row][col].getNeibourMinesQty() == 0) {
                    cells[row][col].setIsUncovered(true);


                    OpenSurroundingCells(row, col);
                } else if (!cells[row][col].isUncovered() && cells[row][col].getNeibourMinesQty() != 0) {
                    cells[row][col].setIsUncovered(true);
                }
            }
        }
        // if user has pressed right button
        else
        {
            if (!cells[row][col].isFlag())
            {
                cells[row][col].setIsFlag(true);
                flagsCounter--;
            }
            else
            {
                cells[row][col].setIsFlag(false);
                flagsCounter++;
            }
        }

        mineField.drawCells();

        if (checkVictory())
        {
            s = Status.WIN;
            userInterface.win();
        }
    }

    /**
     * This method calculates the opening of surrounding cells
     * @param row - rows
     * @param col - columns
     */
    private void OpenSurroundingCells(int row, int col)
    {
        // открыть примыкающие клетки слева, справа, сверху, снизу
        if (col != 0) this.OpenCell(row, col - 1, false);
        if (row != 0) this.OpenCell(row - 1, col, false);
        if (col != MINESFIELD_WIDTH - 1) this.OpenCell(row, col + 1, false);
        if (row != MINESFIELD_HEIGHT - 1) this.OpenCell(row + 1, col, false);

        //примыкающие диагонально
        if (row != 0 && col != 0) this.OpenCell(row - 1, col - 1, false);
        if (row != 0 && col != MINESFIELD_WIDTH - 1) this.OpenCell(row - 1, col + 1, false);
        if (row != MINESFIELD_HEIGHT - 1 && col != 0) this.OpenCell(row + 1, col - 1, false);
        if (row != MINESFIELD_HEIGHT - 1 && col != MINESFIELD_WIDTH - 1) this.OpenCell(row + 1, col + 1, false);

    }

    public void initGame()
    {
        if (mineField != null)
        {
            s = Status.BEGIN;
            mineField.createCells();
            setMines(mineField);
            flagsCounter = MINES_NUMBER;
        }
    }

    public void setMineField(IMineField mf)
    {
        mineField = mf;
    }

    public boolean checkVictory()
    {
        int minesWithFlag = 0;  // number of mines that are found

        for (int row = 0; row < MINESFIELD_HEIGHT; row++)
            for (int col = 0; col < MINESFIELD_WIDTH; col++)
                if (cells[row][col].isBomb() && cells[row][col].isFlag()) minesWithFlag++;

        return (minesWithFlag == MINES_NUMBER ? true : false);
    }
}
