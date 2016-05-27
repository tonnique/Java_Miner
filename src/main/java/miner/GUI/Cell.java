package miner.GUI;

import miner.ICell;

import javax.swing.*;
import java.awt.*;



/**
 * Created by Antony on 25.05.2016.
 */
public class Cell extends JLabel implements ICell {
//public class Cell implements ICell {
    //private CellStatus cellStatus;

    public static int CELLSIZE = 30;
    public static Color CELLBORDERCOLOR = Color.BLACK;
    public static Color UNCKECKEDCOLOR = new Color(181, 230, 29);
    public static Color UNCOVEREDCOLOR = new Color(231, 235, 65);

    private boolean uncovered;
    private boolean flag;
    private  boolean bomb;
    private int neibourMinesQty = 0;



    public Cell()
    {
        //this.cellStatus = CellStatus.UNCHECKED;
        this.uncovered = false;
        this.flag = false;
        this.bomb = false;

        setPreferredSize(new Dimension(CELLSIZE, CELLSIZE));
        drawCell();
    }

    @Override
    public boolean isBomb() {
        return bomb;
    }

    @Override
    public boolean isFlag() {
        return flag;
    }

    @Override
    public boolean isUncovered() {
        return uncovered;
    }

    @Override
    public void setIsBomb(boolean b) {
        bomb = b;
    }

    @Override
    public void setIsFlag(boolean b) {
        flag = b;
    }

    @Override
    public void setIsUncovered(boolean b) {
        uncovered = b;
    }

    @Override
    public void setNeibourMinesQty(int i) {
        neibourMinesQty = i;
    }

    @Override
    public int getNeibourMinesQty() {
        return  neibourMinesQty ;
    }

    //public void DrawCell(int x , int y)
    public void drawCell() {
        setBorder(BorderFactory.createLineBorder(CELLBORDERCOLOR));

        if (!uncovered) {
            setText("");

            setOpaque(true);
            setBackground(UNCKECKEDCOLOR);

            if (flag)
            {
                drawFlag();
            }

        } else if (uncovered) {
            if (!isBomb()) {
                setOpaque(true);
                setBackground(UNCOVEREDCOLOR);
                if (neibourMinesQty != 0) {
                    setNeighbourNumbers();
                }

            }
        }

        if (uncovered && isBomb()) {
            drawBomb();
        }
    }

    /**
     * here we can draw a bomb in any way we like
     */
    public void drawBomb()
    {
        setOpaque(true);
        setBackground(Color.RED);
    }

    public void setNeighbourNumbers()
    {
        setHorizontalAlignment(CENTER);
        setText("" + neibourMinesQty);
    }

    public void drawFlag()
    {
        setHorizontalAlignment(CENTER);
        setText("F");
    }
}
