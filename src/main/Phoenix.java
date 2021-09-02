package main;


import java.awt.*;
import java.util.Random;

public class Phoenix extends GameObject{
    private Handler handler;
    private GameObject player;
    private int HEALTH = 150;
    Random r = new Random();
    public Phoenix(float x, float y, ID id, Handler handler)
    {
        super(x,y,id);
        this.handler=handler;
        for(int i=0;i<handler.object.size();i++)
        {
            if(handler.object.get(i).getId()==ID.Player)
            {
                player = handler.object.get(i);
            }
        }


    }
    private void collision()
    {
        for(int i=0;i<handler.object.size();i++)
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId()==ID.JettKnife)
            {
                if(tempObject.getBounds().intersects(getBounds()))
                {
                    int spawn = r.nextInt(50);
                    if(spawn==0)
                    {
                        HEALTH-=150;
                        HUD.damageTaken=150;
                        HUD.x=x;
                        HUD.y=y;
                    }
                    else
                    {
                        HEALTH-=50;
                        HUD.damageTaken=50;
                        HUD.x=x;
                        HUD.y=y;
                    }
                    if(HEALTH<=0)
                    {
                        handler.removeObject(this);
                        handler.addObject(new HealthPack(x,y,ID.HealthPack,handler));
                    }
                    handler.removeObject(tempObject);
                }

            }
        }
    }
    public Rectangle getBounds()
    {
        return new Rectangle((int)x,(int)y,32,32);
    }
    public void tick() {
        x += velX;
        y += velY;

        float diffX = x - player.getX() -8;
        float diffY = y - player.getY() -8;
        float distance = (float)Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()));

        velX = (float) ((-1.0/distance)*diffX);
        velY = (float) ((-1.0/distance)*diffY);
        if(y<=0 || y>=Game.HEIGHT-48)velY*=-1;
        if(x<=0 || x>=Game.WIDTH-32)velX*=-1;
        int spawn = r.nextInt(100);;
        if(spawn==0)
        {
            handler.addObject(new Molotov(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT),ID.Molotov,handler));
        }
        spawn=r.nextInt(100);
        if(spawn==0)
        {
            handler.addObject(new VandalBullet(x,y,ID.VandalBullet,handler,velX,velY));
        }
        collision();
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y,32,16);
        g.setColor(Color.orange);
        g.fillRect((int)x,(int)y+16,32,16);
    }
}

