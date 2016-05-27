/**
 *  Интерфес описывает логику игры
 */

package miner;


/**
 * Created by Antony on 25.05.2016.
 */
public interface IGameLogic {

    //easy level 8 x 8 and 10 bombs
    int MINEFIELD_HEIGHT = 8;
    int MINEFIELD_WIDTH = 8;
    int MINES_NUMBER  = 10;

    /**
     * Get number of mines in a game
     * @return  int - value of bombs in a game
     */
    int getMinesNumber();
    int getFlagsNumber();

    int getHeight();
    int getWidth();

    void setMineField(IMineField mf);

    void initGame();

    /**
     * Метод устанавливает мины на минном поле
     * @param mf - объект типа IMineField - минное поле, на котором утанавиливаются мины
     */
    void setMines(IMineField mf);

    /**
     * метод описывающий что происходи при открытии ячейки
     * или пометки ее флажком
     *
     * @param row - ряд в таблице ячеек на минном поле
     * @param col -  колонка в таблице ячеек на минном поле
     * @param flag - устанавливается флаг или нет
     */

    void OpenCell(int row, int col, boolean flag);

    /**
     * Метод для установки ссылки на объект IUserInterface
     * для взраимодействия с ним (передача данных о проигрыше, победе)
     *
     * @param ui - Объект типа IUserInterface - интерфес пользователя
     */
    void setUserInterface(IUserInterface ui);

    /**
     * Метод проверяющий победил пользоваль или нет
     * @return да или нет
     */
    boolean checkVictory();




}
