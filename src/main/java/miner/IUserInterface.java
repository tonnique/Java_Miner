/**
 * Интерфейс описывает поведение самого приложения с игрой
 * - пользовательского интерфеса
  */

package miner;

/**
 * Created by Antony on 25.05.2016.
 */
public interface IUserInterface {

    /**
     * Method to start the game
     */
    void StartGame();

    /**
     * Win the game, takes appropriate action and congratulates the user
     */
    void win();

    /**
     * Lose the game, takes appropriate action
     */
    void lose();

    void showErrorMessage(String s);

}
