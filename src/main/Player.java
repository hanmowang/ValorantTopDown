package main;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject{
    public int knifeRemaining =5;
    public boolean isPoisoned=false;
    Random r = new Random();
    Handler handler;
    int poisonTime=50;
    public Player(float x, float y, ID id, Handler handler)
    {
        super(x,y,id);
        this.handler=handler;

    }
    public Rectangle getBounds()
    {
        return new Rectangle((int)x,(int)y,32,32);
    }
    public void tick() {
        x += velX;
        y += velY;
        x=Game.clamp((int)x,0,Game.WIDTH-48);
        y=Game.clamp((int)y,0,Game.HEIGHT-72);

        if(isPoisoned)
        {
            poisonTime--;
            handler.addObject(new Trail((int)x,(int)y,ID.Trail,new Color(0,128,0),16,16, 0.05f,handler));
        }
        else
        {
            handler.addObject(new Trail((int)x,(int)y,ID.Trail,Color.white,16,16, 0.05f,handler));
        }
        collision();

    }
    private void collision()
    {
        boolean gotHit=false;
        for(int i=0;i<handler.object.size();i++)
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId()== ID.Viper || tempObject.getId()==ID.Phoenix)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    gotHit=true;
                    int temp = 1;
                    if(isPoisoned)
                    {
                        temp*=2;
                    }
                    HUD.HEALTH-=temp;
                    if(gotHit)
                    {
                        HUD.damageTaken+=temp;
                    }
                    else {
                        HUD.damageTaken = temp;
                    }
                    HUD.x=x;
                    HUD.y=y;
                }
            }
            if(tempObject.getId() == ID.VandalBullet)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    gotHit=true;
                    int temp1 = 160;
                    int temp2 = 40;
                    if(isPoisoned)
                    {
                        temp1*=2;
                        temp2*=2;
                    }
                    int headshot = r.nextInt(100);
                    if(headshot==1)
                    {
                        HUD.HEALTH-=temp1;
                        if(gotHit)
                        {
                            HUD.damageTaken+=temp1;
                        }
                        else {
                            HUD.damageTaken = temp1;
                        }
                        HUD.x=x;
                        HUD.y=y;
                    }
                    else
                    {
                        HUD.HEALTH-=temp2;
                        if(gotHit)
                        {
                            HUD.damageTaken+=temp2;
                        }
                        else {
                            HUD.damageTaken = temp2;
                        }
                        HUD.x=x;
                        HUD.y=y;
                    }
                    handler.removeObject(tempObject);
                }

            }
            if(tempObject.getId()==ID.Molotov)
            {
                gotHit=true;
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    int temp = 1;
                    if(isPoisoned)
                    {
                        temp*=2;
                    }
                    HUD.HEALTH-=temp;
                    if(gotHit)
                    {
                        HUD.damageTaken+=temp;
                    }
                    else {
                        HUD.damageTaken = temp;
                    }
                    HUD.x=x;
                    HUD.y=y;
                }
            }
            if(tempObject.getId()==ID.ViperMolotov)
            {
                gotHit=true;
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    HUD.HEALTH--;
                    if(gotHit)
                    {
                        HUD.damageTaken += 1;
                    }
                    else {
                        HUD.damageTaken = 1;
                    }
                    HUD.x=x;
                    HUD.y=y;
                    isPoisoned=true;
                    poisonTime=50;
                }
                else
                {
                    if(poisonTime<=0)
                    {
                        isPoisoned=false;
                    }
                }
            }
            if(tempObject.getId()==ID.HealthPack)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    HUD.HEALTH+=50;
                    HUD.amtHealed+=50;
                    HUD.x=x;
                    HUD.y=y;
                    handler.removeObject(tempObject);
                }
            }
        }
    }
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        g.setColor(Color.white);
        g.fillRect((int)x,(int)y,32,32);
    }

}
