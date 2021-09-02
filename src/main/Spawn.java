package main;

import java.awt.*;
import java.util.Random;

public class Spawn {
    private Handler handler;
    private HUD hud;
    private Random r = new Random();
    private int scoreKeep = 0;
    private Player player;
    public Spawn(Handler handler, HUD hud)
    {
        this.handler=handler;
        this.hud=hud;
    }
    public void tick()
    {
        scoreKeep++;
        for(int i=0;i<handler.object.size();i++)
        {
            if(handler.object.get(i).getId()==ID.Player)
            {
                player = (Player) handler.object.get(i);
                break;
            }
        }
        if(scoreKeep>=100)
        {
            scoreKeep=0;
            hud.setLevel(hud.getLevel()+1);
            if(hud.getLevel()==2)
            {
                for(int i=0;i<3;i++)
                {
                    int x=r.nextInt(Game.WIDTH-16);
                    int y=r.nextInt(Game.HEIGHT-16);
                    Rectangle temp = new Rectangle((int)x,(int)y,32,32);
                    while(player.getBounds().intersects(temp))
                    {
                        x=r.nextInt(Game.WIDTH-16);
                        y=r.nextInt(Game.HEIGHT-16);
                    }
                    handler.addObject(new BasicEnemy(x,y,ID.BasicEnemy,handler));
                }

            }
            else if(hud.getLevel()==4)
            {
                for(int i=0;i<3;i++)
                {
                    int x=r.nextInt(Game.WIDTH-32);
                    int y=r.nextInt(Game.HEIGHT-32);
                    Rectangle temp = new Rectangle((int)x,(int)y,64,64);
                    while(player.getBounds().intersects(temp))
                    {
                        x=r.nextInt(Game.WIDTH-32);
                        y=r.nextInt(Game.HEIGHT-32);
                    }
                    handler.addObject(new SmartEnemy(x,y,ID.SmartEnemy,handler));
                }
            }
            else if(hud.getLevel()==6)
            {
                for(int i=0;i<2;i++)
                {
                    int x=r.nextInt(Game.WIDTH-32);
                    int y=r.nextInt(Game.HEIGHT-32);
                    Rectangle temp = new Rectangle((int)x,(int)y,64,64);
                    while(player.getBounds().intersects(temp))
                    {
                        x=r.nextInt(Game.WIDTH-32);
                        y=r.nextInt(Game.HEIGHT-32);
                    }
                    handler.addObject(new Phoenix(x,y,ID.Phoenix,handler));
                }
            }
            else if(hud.getLevel()==8)
            {
                for(int i=0;i<2;i++)
                {
                    int x=r.nextInt(Game.WIDTH-32);
                    int y=r.nextInt(Game.HEIGHT-32);
                    Rectangle temp = new Rectangle((int)x,(int)y,64,64);
                    while(player.getBounds().intersects(temp))
                    {
                        x=r.nextInt(Game.WIDTH-32);
                        y=r.nextInt(Game.HEIGHT-32);
                    }
                    handler.addObject(new Viper(x,y,ID.Viper,handler));
                }
            }
            else if(hud.getLevel()==10)
            {
                for(int i=0;i<2;i++)
                {
                    int x=r.nextInt(Game.WIDTH-32);
                    int y=r.nextInt(Game.HEIGHT-32);
                    Rectangle temp = new Rectangle((int)x,(int)y,64,64);
                    while(player.getBounds().intersects(temp))
                    {
                        x=r.nextInt(Game.WIDTH-32);
                        y=r.nextInt(Game.HEIGHT-32);
                    }
                    handler.addObject(new Viper(x,y,ID.Viper,handler));
                }
                for(int i=0;i<2;i++)
                {
                    int x=r.nextInt(Game.WIDTH-32);
                    int y=r.nextInt(Game.HEIGHT-32);
                    Rectangle temp = new Rectangle((int)x,(int)y,64,64);
                    while(player.getBounds().intersects(temp))
                    {
                        x=r.nextInt(Game.WIDTH-32);
                        y=r.nextInt(Game.HEIGHT-32);
                    }
                    handler.addObject(new Phoenix(x,y,ID.Phoenix,handler));
                }
            }
            else if(hud.getLevel()%2==0)
            {
                int randomEnemy = r.nextInt(4);
                if(randomEnemy==0)
                {
                    for(int i=0;i<4;i++)
                    {
                        int x=r.nextInt(Game.WIDTH-32);
                        int y=r.nextInt(Game.HEIGHT-32);
                        Rectangle temp = new Rectangle((int)x,(int)y,64,64);
                        while(player.getBounds().intersects(temp))
                        {
                            x=r.nextInt(Game.WIDTH-32);
                            y=r.nextInt(Game.HEIGHT-32);
                        }
                        handler.addObject(new Phoenix(x,y,ID.Phoenix,handler));
                    }
                }
                else if(randomEnemy==1)
                {
                    for(int i=0;i<4;i++)
                    {
                        int x=r.nextInt(Game.WIDTH-32);
                        int y=r.nextInt(Game.HEIGHT-32);
                        Rectangle temp = new Rectangle((int)x,(int)y,64,64);
                        while(player.getBounds().intersects(temp))
                        {
                            x=r.nextInt(Game.WIDTH-32);
                            y=r.nextInt(Game.HEIGHT-32);
                        }
                        handler.addObject(new Viper(x,y,ID.Viper,handler));
                    }
                }
                else if(randomEnemy==2)
                {
                    for(int i=0;i<4;i++)
                    {
                        int x=r.nextInt(Game.WIDTH-32);
                        int y=r.nextInt(Game.HEIGHT-32);
                        Rectangle temp = new Rectangle((int)x,(int)y,64,64);
                        while(player.getBounds().intersects(temp))
                        {
                            x=r.nextInt(Game.WIDTH-32);
                            y=r.nextInt(Game.HEIGHT-32);
                        }
                        handler.addObject(new BasicEnemy(x,y,ID.BasicEnemy,handler));
                    }
                }
                else
                {
                    for(int i=0;i<4;i++)
                    {
                        int x=r.nextInt(Game.WIDTH-32);
                        int y=r.nextInt(Game.HEIGHT-32);
                        Rectangle temp = new Rectangle((int)x,(int)y,64,64);
                        while(player.getBounds().intersects(temp))
                        {
                            x=r.nextInt(Game.WIDTH-32);
                            y=r.nextInt(Game.HEIGHT-32);
                        }
                        handler.addObject(new SmartEnemy(x,y,ID.SmartEnemy,handler));
                    }
                }
            }
        }
    }
}
