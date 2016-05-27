/**
 * Интерфейс IMineField описывает поведение минного поля, - различные методы,
 * которые необходимы для создания игры Miner
 */

package miner;

/**
 * Created by Antony on 25.05.2016.
 */
public interface IMineField {


    /**
     *  Makes cells array in MinesField
     */
    void createCells();

    /**
     * Blows all bombs on a field when a user is failed
     */
    void BlastAllBombs();

    ICell[][] getCells();

    /**
     * redraw all cells on a field
     */
    void drawCells();


}
