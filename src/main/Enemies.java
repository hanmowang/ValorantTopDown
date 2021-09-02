package main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import static main.ID.Player;

public class Enemies extends MouseAdapter {
    private Game game;
    private Handler handler;
    public Enemies(Game game)
    {
        this.game=game;
    }

    public void mousePressed(MouseEvent e)
    {
        int mx = e.getX();
        int my = e.getY();
        if(game.gameState== Game.STATE.Enemies)
        {
            if(mouseOver(mx,my,15,25,255,90))
            {
                game.gameState= Game.STATE.Help;
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
        g.drawString("There are 4 types of enemies",200,175);
        g.setColor(Color.RED);
        g.fillRect(125,250,50,50);
        g.setColor(Color.WHITE);
        fnt=fnt.deriveFont(20f);
        g.setFont(fnt);
        g.drawString("aimlessly move around",200,280);
        g.setColor(Color.GRAY);
        g.fillRect(125,500,50,50);
        g.setColor(Color.WHITE);
        g.drawString("move towards the player",200,525);
        g.drawString("and shoot bullet at it",200,550);
        g.setColor(Color.RED);
        g.fillRect(550, 250, 50, 25);
        g.setColor(Color.YELLOW);
        g.fillRect(550, 275, 50, 25);
        g.setColor(Color.WHITE);
        g.drawString("throw molotov that constantly do", 620,270);
        g.drawString("damage to the player", 620,295);
        g.setColor(Color.GRAY);
        g.fillRect(550, 500, 50, 25);
        g.setColor(Color.GREEN);
        g.fillRect(550, 525, 50, 25);
        g.setColor(Color.WHITE);
        g.drawString("throw molotov that makes the", 620,520);
        g.drawString("player fragil for 2 seconds", 620,545);
        g.drawString("*fragil: The player will take two", 600,600);
        g.drawString(" times the damage it normally takes", 600,625);
        
        fnt=Font.createFont(Font.TRUETYPE_FONT, new File("Valorant Font.ttf")).deriveFont(80f);
        g.setFont(fnt);
        g.drawString("back",30,100);
        g.drawRect(15,25,255,90);
    }
}

