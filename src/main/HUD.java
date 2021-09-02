package main;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HUD{

    public static float HEALTH = 150;
    private float greenValue = 255;
    public static float damageTaken=0;
    public static float amtHealed=0;
    public static float x;
    public static float y;
    public static int highestScore=0;
    public static boolean rightClicked = false;
    private int score=0;
    private int level=1;
    private int dmgTimer=50;
    private int healTimer=50;
    private boolean hasReset=false;
    public int timerBullet = 25;
    public static int count=0;

    public void tick()
    {
        HEALTH = Game.clamp(HEALTH,0,150);
        greenValue = HEALTH*2;
        greenValue = Game.clamp(greenValue,0,255);
        score++;
        dmgTimer--;
        healTimer--;
        timerBullet--;
    }
    public void render(Graphics g) throws IOException, FontFormatException {
        Font fnt = new Font("arial",1,14);
        g.setFont(fnt);
        g.setColor(Color.gray);
        g.fillRect(15,15,300,32);
        g.setColor(new Color(75,(int)greenValue,0));
        g.fillRect(15,15, (int) (HEALTH*2),32);
        g.setColor(Color.WHITE);
        g.drawRect(15,15,300,32);

        g.drawString("Score "+score, 16 , 80);
        g.drawString("Level "+level,16,100);
        if(damageTaken!=0 && dmgTimer>0)
        {
            g.setColor(Color.RED);
            g.drawString("-"+ (int)damageTaken,(int)x,(int)y);
        }
        else
        {
            dmgTimer=50;
            damageTaken=0;
        }
        if(amtHealed>0 && healTimer>0)
        {
            g.setColor(Color.GREEN);

            g.drawString("+" + (int)amtHealed,(int)x,(int)y);
        }
        else
        {
            healTimer=50;
            amtHealed=0;
        }
        g.setColor(Color.WHITE);
        if(count==5)
        {
            g.setColor(Color.red);
            if(!hasReset)
            {
                if(rightClicked==false)
                {
                    timerBullet=20;
                }
                else
                {
                    timerBullet=50;
                    rightClicked=false;
                }
                hasReset=true;
            }
            if(timerBullet<=0)
            {
                count=0;
                g.setColor(Color.white);
                hasReset=false;
            }
        }
        g.drawString("Knife Count ",16,120);
        for(int i=5;i>count;i--)
        {
            int x = (5-i)*10+17;
            g.setColor(Color.WHITE);
            g.drawRect(x,130,4,8);
            g.fillRect(x,130,4,8);
        }
    }
    public void setScore(int score)
    {
        this.score=score;
    }
    public int getScore()
    {
        return score;
    }
    public void setLevel(int level)
    {
        this.level=level;
    }
    public int getLevel()
    {
        return level;
    }
}
