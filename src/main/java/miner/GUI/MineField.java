/**
 * этот класс описывает минное поле
 * Он наследует интерфейс IMineField которое описывает поведение минного поля
 */

package miner.GUI;

import miner.*;
import javax.swing.*;
import java.awt.*;


/**
 * Created by Antony on 25.05.2016.
 */

public class MineField extends JPanel implements IMineField {

    //a table of minefield
    private ICell[][] cells = null;

    private int height; // a height of minefield
    private int width; // a width of minefield

    public MineField(int h, int w) {
            this.height = h;
            this.width = w;

        setPreferredSize(new Dimension(width * Cell.CELLSIZE, height * Cell.CELLSIZE));
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
    }

    /**
     * Данный метод создаёт таблицу (матрицу) объектов ICell - ячеек на минном поле
     * А если таблица уже создана (когда пользователь начинает новую игру)
     * обновляет значения полей в объекте ICell на начальные
     */
    public void createCells()
    {
        if (cells ==null) {
            cells = new Cell[height][width];
        }

        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                if (cells[x][y] == null) {
                    cells[x][y] = new Cell();
                    this.add((Cell) cells[x][y]);
                }
                else {
                    cells[x][y].setIsUncovered(false);
                    cells[x][y].setIsBomb(false);
                    cells[x][y].setIsFlag(false);
                    cells[x][y].setNeibourMinesQty(0);
                }
            }
        }
         drawCells();
     }

    /**
     * Метод "отображает" ячейки
     */
    public void drawCells() {
        for (int x = 0; x < height; x++)
        {
            for (int y = 0; y < width; y++)
            {
                cells[x][y].drawCell();
            }
        }
    }

    /**
     * Метод отображает все бомбы (мины) взорванными
     * т.е. показывает все мины когда проигрывает пользователь
     */
    public void BlastAllBombs()
    {
        for (int x = 0; x < height; x++)
        {
            for (int y = 0; y < width; y++)
            {
                if (cells[x][y].isBomb()) {
                    cells[x][y].setIsUncovered(true);
                    cells[x][y].drawCell();
                }
            }
        }
    }

    /**
     * Метод возвращает "таблицу" (2-у мерный массив) объектов ICell
     * Класс требуется для доступа класса, описывающий логику игры
     *
     * @return ICell[][]  2-умерный  массив ячеек на минном поле
     */
    public ICell[][] getCells()
    {
        return cells;
    }

}
