package main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import static main.ID.Player;

public class End extends MouseAdapter {
    private Game game;
    private Handler handler;
    private HUD hud;
    public static int currScore;
    public End(Game game, Handler handler, HUD hud)
    {
        this.handler=handler;
        this.game=game;
        this.hud = hud;

    }

    public void mousePressed(MouseEvent e)
    {
        int mx = e.getX();
        int my = e.getY();
        if(game.gameState== Game.STATE.End)
        {
            if(mouseOver(mx,my,185,385,600,80))
            {
                handler.addObject(new Player(100,100, Player,handler));
                hud.setScore(0);
                hud.setLevel(0);
                game.gameState= Game.STATE.Game;

            }
            else if(mouseOver(mx,my,185,535,600,80))
            {
                game.gameState=Game.STATE.Menu;
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
    public void tick(){

    }
    public void render(Graphics g) throws IOException, FontFormatException {
        Font fnt = Font.createFont(Font.TRUETYPE_FONT, new File("Valorant Font.ttf")).deriveFont(100f);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Valorant Font.ttf")));

        g.setFont(fnt);
        g.setColor(Color.white);
        g.drawString("Game Over",Game.WIDTH/2-350,Game.HEIGHT/8+50);
        g.setColor(Color.WHITE);

        fnt=fnt.deriveFont(30f);
        g.setFont(fnt);
        g.drawString("score: "+currScore,Game.WIDTH/2-100,250);
        g.drawString("highest score:"+HUD.highestScore,Game.WIDTH/2-150,300);
        fnt=fnt.deriveFont(70f);
        g.setFont(fnt);
        g.drawString("Play Again",Game.WIDTH/2-235,450);
        g.drawRect(185,385,600,80);

        g.drawString("Back to Menu",Game.WIDTH/2-305,600);
        g.drawRect(185,535,600,80);
    }
}

