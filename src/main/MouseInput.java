package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
    private Handler handler;
    private int timer=100;
    public MouseInput(Handler handler)
    {
        this.handler=handler;
    }
    public void mousePressed(MouseEvent e) {
        int mx= e.getX();
        int my= e.getY();

        for(int i=0;i<handler.object.size();i++)
        {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId()==ID.Player)
            {
                if(e.getButton()==MouseEvent.BUTTON1)
                {
                    if(HUD.count!=5)
                    {
                        handler.addObject(new JettKnife(tempObject.getX(), tempObject.getY(), ID.JettKnife,handler,mx,my));
                        HUD.count++;
                    }
                }
                else if(e.getButton()==MouseEvent.BUTTON3)
                {
                    int knivesLeft = 5-HUD.count;
                    for(int j=0;j<knivesLeft;j++)
                    {
                        float xN = (float)Math.random()*50;
                        float yN = (float)Math.random()*50;
                        handler.addObject(new JettKnife(tempObject.getX(),tempObject.getY(),ID.JettKnife,handler,mx+xN,my+yN));
                    }
                    HUD.count=5;
                    HUD.rightClicked=true;
                }

            }
        }
    }
}
