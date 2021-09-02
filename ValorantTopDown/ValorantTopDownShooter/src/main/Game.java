package main;


import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.Random;

import static main.ID.Player;

public class Game extends Canvas implements Runnable{
    public static final int WIDTH = 1024, HEIGHT=WIDTH/12*9;

    private Thread thread;
    private boolean running = false;

    private Random r;
    private Handler handler = new Handler();
    private HUD hud  = new HUD();
    private Spawn spawner = new Spawn(handler,hud);
    private Menu menu = new Menu(this,handler,hud);
    private Help help = new Help(this);
    private Enemies enemies = new Enemies(this);
    private End end = new End(this,handler,hud);
    public enum STATE {
        Menu,
        Enemies,
        Help,
        Game,
        End
    }
    public static STATE gameState = STATE.Menu ;
    public Game()
    {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        menu=new Menu(this,handler,hud);

        new Window(WIDTH,HEIGHT,"ValorantTopDown",this);

        hud = new HUD();
        spawner = new Spawn(handler,hud);
        r=new Random();
        help = new Help(this);
        this.addMouseListener(new MouseInput(handler));
        this.addMouseListener(menu);
        this.addMouseListener(help);
        this.addMouseListener(end);
        this.addMouseListener(enemies);

    }
    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public synchronized void stop()
    {
        try{
            thread.join();
            running=false;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void run()
    {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta+=(now-lastTime)/ns;
            lastTime = now;
            while(delta>=1)
            {
                tick();
                delta--;
            }
            if(running) {
                try {
                    render();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (FontFormatException e) {
                    e.printStackTrace();
                }
            }
            frames++;

            if(System.currentTimeMillis()-timer>1000)
            {
                timer+=1000;
                //System.out.println("FPS: " + frames);
                frames=0;
            }
        }
        stop();
    }
    private void tick()
    {
        handler.tick();
        if(gameState==STATE.Game)
        {
            hud.tick();
            spawner.tick();
        }
        else if(gameState==STATE.Menu)
        {
            menu.tick();
        }
        else if(gameState==STATE.Help)
        {
            help.tick();
        }
        else if(gameState==STATE.End)
        {
            end.tick();
        }
        else if(gameState==STATE.Enemies)
        {
            enemies.tick();
        }

    }
    private void render() throws IOException, FontFormatException {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);

        handler.render(g);
        if(gameState==STATE.Game)
        {
            hud.render(g);
            if(HUD.HEALTH<=0)
            {
                handler.clear();
                hud.HEALTH=150;
                HUD.count=0;
                if(HUD.highestScore<hud.getScore())
                {
                    HUD.highestScore=hud.getScore();
                }
                gameState=STATE.End;
                End.currScore=hud.getScore();
                hud.setScore(0);
                hud.setLevel(0);

            }
        }
        else if(gameState==STATE.Menu)
        {
            menu.render(g);
        }
        else if(gameState==STATE.Help)
        {
            help.render(g);
        }
        else if(gameState==STATE.End)
        {
            end.render(g);
        }
        else if(gameState==STATE.Enemies)
        {
            enemies.render(g);
        }
        g.dispose();
        bs.show();

    }
    public static float clamp(float var, float min, float max)
    {
        if(var>=max)
            return var=max;
        else if(var<=min)
            return var=min;
        else
            return var;
    }
    public static void main(String args[])
    {
        new Game();
    }
}
