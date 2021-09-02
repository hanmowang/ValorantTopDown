package main;

import java.awt.*;

public class Molotov extends GameObject{
    private Handler handler;
    private GameObject player;
    private int timer=100;
    public Molotov(float x, float y, ID id, Handler handler)
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

    @Override
    public void tick() {
        timer--;
        if(timer==0)
        {
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect((int)x,(int)y,64,64);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,64,64);
    }
}
