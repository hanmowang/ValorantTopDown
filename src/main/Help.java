package main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import static main.ID.Player;

public class Help extends MouseAdapter {
    private Game game;
    private Handler handler;
    public Help(Game game)
    {
        this.game=game;
    }

    public void mousePressed(MouseEvent e)
    {
        int mx = e.getX();
        int my = e.getY();
        if(game.gameState== Game.STATE.Help)
        {
            if(mouseOver(mx,my,15,25,255,90))
            {
                game.gameState= Game.STATE.Menu;
            }
            else if(mouseOver(mx,my,275,600,460,150))
            {
                game.gameState=Game.STATE.Enemies;
            }
        }
    }
    public void mouseReleased(MouseEvent e)
    {

    }
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
    {
        if(mx>x && mx<x+width)
        {
            if(my>y && my<y+height)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }
    public void tick()
    {

    }
    public void render(Graphics g) throws IOException, FontFormatException {
        Font fnt = new Font("ariel",1,50);
        g.setColor(Color.WHITE);
        g.setFont(fnt);
        g.drawString("W A S D to move around",225,200);

        g.drawString("Left click to shoot knives",225,300);
        g.drawString("Right click to shoot all your", 175, 400);
        g.drawString("knives at once",330,450);

        g.drawString("your knives can sometimes do",150,525);
        g.drawString("critical damage!",325,575);

        fnt=Font.createFont(Font.TRUETYPE_FONT, new File("Valorant Font.ttf")).deriveFont(80f);
        g.setFont(fnt);
        g.drawString("next page",275,700);
        g.drawString("back",30,100);
        g.drawRect(15,25,255,90);
    }
}
