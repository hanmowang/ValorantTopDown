package main;

import java.awt.*;
import java.util.Random;

public class BasicEnemy extends GameObject{
    private Handler handler;
    private int HEALTH = 50;
    Random r = new Random();
    public BasicEnemy(float x, float y, ID id, Handler handler)
    {
        super(x,y,id);
        this.handler=handler;
        velX=3;
        velY=3;

    }
    public Rectangle getBounds()
    {
        return new Rectangle((int)x,(int)y,16,16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if(y<=0 || y>=Game.HEIGHT-48)velY*=-1;
        if(x<=0 || x>=Game.WIDTH-32)velX*=-1;
        collision();
        handler.addObject(new Trail((int)x,(int)y,ID.Trail,Color.red,16,16, 0.05f,handler));
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
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y,16,16);
    }
}
