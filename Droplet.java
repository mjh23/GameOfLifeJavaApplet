import java.awt.*;
import java.util.*;

public class Droplet
{
	Random r = new Random();
	private int xDrop = 100;
	private int yDrop = 0 - r.nextInt(500);
	private int speed = 5;
	private boolean hit = false;

	public Droplet(GameOfLifeApplet gc, int x)
	{
		xDrop = x;
	}

	public void update(GameOfLifeApplet gc, StickMan s)
	{
		if(yDrop<400){
			yDrop+=(speed*(int)(gc.level*0.75));
		}
		else{
			Random r = new Random();
			yDrop = 0 - r.nextInt(500);
		}

		checkCollision(s);
	}

	public void paint(Graphics g, GameOfLifeApplet gc)
	{
		g.setColor(Color.RED);
		g.fillRect(xDrop,yDrop,30,30);
	}


	public void checkCollision(StickMan s)
	{
		int x = s.getX()-20;
		int y = s.getY();


		if(y-30 <= yDrop &&  y+30 >= yDrop && x-30 <= xDrop && x+50 >= xDrop)
			hit = true;
	}

	public boolean getHitStatus()
	{
		return hit;
	}

	public void resetDroplet()
	{
		yDrop = 0 - r.nextInt(500);
	}

}

