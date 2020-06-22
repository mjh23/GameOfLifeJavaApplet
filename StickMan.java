import java.util.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StickMan implements KeyListener
{
	private int xRight = 110;
	private int xMid = 100;
	private int xLeft = 90;
	private int yLeg = 390;
	private int yArm = 343;
	private int yHead = 320;
	private int yNeck = 330;
	private int yShoulder = 333;
	private int yTorso = 360;
	private int x = 395;
	private int y = 390;
	private double velX = 0;
	int speedMultiplier = 1;
	Environment e = new Environment();

	public void update(GameOfLifeApplet gc, PowerUp p)
	{
		gc.addKeyListener(this);
		x+=velX;
		if(x>750)
			x=725;
		if(x<50)
			x=75;
		if(y>350)
			y--;
		if(p.getHitStatus())
			speedMultiplier +=1;
	}

	public StickMan()
	{}

	public void paint(Graphics g,GameOfLifeApplet gc,Environment e)
	{
		Graphics2D g1 = (Graphics2D) g;
		if(y>350){
			g1.setColor(Color.GREEN);
			g1.fillRect(x,y,5,390-y);
		}
		if(y<=350){
			g1.setColor(Color.GREEN);
			g1.fillRect(x,350,5,40);
			g1.fillArc(x-35,335,75,50,0,45);
			g1.fillArc(x-35,345,75,50,135,45);
		}

		if(!gc.getRain()&&(e.getMin()<720))
		{
		g.setColor(Color.BLACK);
		//Legs
		g.drawLine(xMid,yTorso,xLeft,yLeg);
		g.drawLine(xMid,yTorso,xRight,yLeg);
		//Body
		g.drawLine(xMid,yTorso,xMid,yNeck);
		//Arms
		g.drawLine(xMid,yShoulder,xLeft,yArm);
		g.drawLine(xMid,yShoulder,xRight,yArm);
		//Head
		g.fillOval(xLeft+5,yHead,10,10);
		}
	}

	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT:
			{
				velX=10*(speedMultiplier*0.5);
				break;
			}
			case KeyEvent.VK_LEFT:
			{
				velX=-10*(speedMultiplier*0.5);
				break;
			}
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT:
			{
				velX=0;
				break;
			}
			case KeyEvent.VK_LEFT:
			{
				velX=0;
				break;
			}
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public double getVel()
	{
		return velX;
	}

	public int getSpeedMultiplier()
	{
		return speedMultiplier;
	}
}