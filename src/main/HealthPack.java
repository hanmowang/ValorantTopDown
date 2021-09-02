package main;

import java.awt.*;

public class HealthPack extends GameObject{
    private int lastTime=200;
    Handler handler = new Handler();
    public HealthPack(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler=handler;
    }

    @Override
    public void tick() {
        lastTime--;
        if(lastTime<0)
        {
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int)x-3,(int)y,8,2);
        g.fillRect((int)x,(int)y-3,2,8);

    }

    @Override
    public Rectangle getBounds()
    {
        return new Rectangle((int)x,(int)y,8,8);
    }
}
