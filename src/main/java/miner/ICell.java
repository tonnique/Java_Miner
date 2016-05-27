/**
 * Интерфейс описывает поведение ячейки на минном поле
 */

package miner;

/**
 * Created by Antony on 25.05.2016.
 */


public interface ICell {

    boolean isBomb();
    boolean isFlag();
    boolean isUncovered();

    void setIsBomb(boolean b);
    void setIsFlag(boolean b);
    void setIsUncovered(boolean b);

    void setNeibourMinesQty(int i);
    int getNeibourMinesQty();

    /**
     * Draw/redraw a cell on a minefield
     */
    void drawCell();

    /**
     * Draw a bomb in anyway you like
     */
    void drawBomb();

    void drawFlag();
}
