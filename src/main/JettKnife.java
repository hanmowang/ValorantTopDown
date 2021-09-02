package main;

import java.awt.*;

public class JettKnife extends GameObject{
    private Handler handler;
    private GameObject player;
    private float velX;
    private float velY;
    public JettKnife(float x, float y, ID id, Handler handler,float mx, float my)
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
        float distance = (float) Math.sqrt(Math.pow((mx - x), 2) + Math.pow((my - y), 2));
        float speed=50;
        //find Y
        velY = (float)((my - y) * speed / distance);
        //find X
        velX = (float)((mx - x) * speed / distance);

    }


    public Rectangle getBounds()
    {
        return new Rectangle((int)x,(int)y,8,8);
    }
    public void tick() {
        x += velX;
        y += velY;

        if(y<=0 || y>=Game.HEIGHT-48 || x<=0 || x>=Game.WIDTH-32) {
            handler.removeObject(this);
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int)x,(int)y,8,8);
    }
}
