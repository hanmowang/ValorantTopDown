package main;

import java.awt.*;

public class VandalBullet extends GameObject{
    private Handler handler;
    private GameObject player;
    public VandalBullet(float x, float y, ID id, Handler handler,float velX, float velY)
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
        this.velX=velX;
        this.velY=velY;
    }


    public Rectangle getBounds()
    {
        return new Rectangle((int)x,(int)y,4,4);
    }
    public void tick() {
        x += velX*5;
        y += velY*5;

        if(y<=0 || y>=Game.HEIGHT-48 || x<=0 || x>=Game.WIDTH-32) {
            handler.removeObject(this);
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect((int)x,(int)y,4,4);
    }
}
