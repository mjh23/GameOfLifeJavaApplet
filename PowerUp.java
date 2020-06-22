import java.awt.*;
import java.util.*;

public class PowerUp
{
	Random r = new Random();
	private int xPU;
	private int yPU = 360;
	private boolean hit = false;

	public PowerUp()
	{}

	public void update(GameOfLifeApplet gc, StickMan s)
	{
		checkCollision(s);
	}

	public void paint(Graphics g, GameOfLifeApplet gc)
	{
		g.setColor(Color.BLUE);
		g.fillRect(xPU,yPU,30,30);
	}


	public void checkCollision(StickMan s)
	{
		int x = s.getX()-20;
		int y = s.getY();

		if( x-30 <= xPU && x+50 >= xPU)
			hit = true;
	}

	public boolean getHitStatus()
	{
		return hit;
	}

	public void reset()
	{
		xPU = r.nextInt(700)+50;
		hit = false;
	}

	//y-30 <= yPU &&  y+30 >= yPU &&

}

