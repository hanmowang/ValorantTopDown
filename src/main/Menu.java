package main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import static main.ID.Player;

public class Menu extends MouseAdapter {
    private Game game;
    private Handler handler;
    private HUD hud;
    public Menu(Game game, Handler handler, HUD hud)
    {
        this.handler=handler;
        this.game=game;
        this.hud=hud;
    }

    public void mousePressed(MouseEvent e)
    {
        int mx = e.getX();
        int my = e.getY();
        if(game.gameState== Game.STATE.Menu)
        {
            if(mouseOver(mx,my,185,200,600,80))
            {
                handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, Player, handler));
                game.gameState= Game.STATE.Game;
            }
            else if(mouseOver(mx,my,185,350,600,80))
            {
                game.gameState=Game.STATE.Help;
            }
            else if(mouseOver(mx,my,185,500,600,80))
            {
                System.exit(1);
            }
        }
        else if(game.gameState== Game.STATE.End)
        {
            if (mouseOver(mx, my, 185, 385, 600, 80)) {
                handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, Player, handler));
                game.gameState = Game.STATE.Game;

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
        g.drawString("Menu",Game.WIDTH/2-200,Game.HEIGHT/8);
        g.setColor(Color.WHITE);

        fnt=fnt.deriveFont(70f);
        g.setFont(fnt);
        g.drawString("Play",Game.WIDTH/2-115,265);
        g.drawRect(185,200,600,80);

        g.drawString("Help",Game.WIDTH/2-115,415);
        g.drawRect(185,350,600,80);

        g.drawString("Quit",Game.WIDTH/2-115,565);
        g.drawRect(185,500,600,80);
    }
}
