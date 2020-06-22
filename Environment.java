import java.awt.*;
import java.util.*;

public class Environment
{
	private int minTot = 0;
	private int min = 0;
	double angle = Math.PI;
	private Image moon;
	private int score = 0;
	private	int hour = 5;
	private int x = 0;
	private int xLeft = 800;
	private int y = 50;
	private int yLeft = 100;
	private String timeElapsed;
	Random r;
	private int bonus = 0;

	public Environment()
	{}

	public Environment(GameOfLifeApplet gc)
	{
		moon = gc.getImage(gc.getDocumentBase(),"Webp.net-resizeimage.png");
		r = new Random();
	}

	public void update(GameOfLifeApplet gc, PowerUp p)
	{
		minTot++;
		min = minTot%1440;
		if(angle>=2*Math.PI)
			angle-=Math.PI;
		angle += 0.0043633231;
		if(!gc.gameOver)
		{
			score = (int)(minTot*1.5) + bonus;
			calcTimeElapsed();
		}
		if(min%60==0)
			hour++;
		xLeft-=2;

		if(xLeft<0 - 100)
		{
			xLeft = 800;
		}

		if(p.getHitStatus())
			bonus +=200;
	}

	public void paint(Graphics g, GameOfLifeApplet gc)
	{
		//Using totMin print name and goal of game for several seconds




		if(min<60 || min>660&&min<720)
		{
			Color c =  new Color(0,0,153);
			g.setColor(c);
			g.fillRect(0,0,800,400);
			g.setColor(Color.RED);
			g.fillOval((int)(250*Math.cos(angle)+400),(int)(250*Math.sin(angle)+400),60,60);
		}
		else if(min>60&&min<120 || min>600&&min<660)
		{
			Color c =  new Color(0,0,204);
			g.setColor(c);
			g.fillRect(0,0,800,400);

			g.setColor(Color.PINK);
			g.fillOval((int)(250*Math.cos(angle)+400),(int)(250*Math.sin(angle)+400),60,60);
		}
		else if(min>120&&min<600)
		{
			Color c =  new Color(31,190,214);
			g.setColor(c);
			g.fillRect(0,0,800,400);

			g.setColor(Color.YELLOW);
			g.fillOval((int)(250*Math.cos(angle)+400),(int)(250*Math.sin(angle)+400),60,60);
		}
		else
		{
			Color c =  new Color(0,0,0);
			g.setColor(c);
			g.fillRect(0,0,800,400);

			g.drawImage(moon,(int)(250*Math.cos(angle)+400),(int)(250*Math.sin(angle)+400),gc);

		}

		if(min<720)
			g.setColor(Color.WHITE);
			drawCloudsLeft(g);

		//House
		Graphics2D g2 = (Graphics2D)g;
		g2.rotate(Math.toRadians(315));
		g2.setColor(new Color(204,0,0));
		g2.fillRect(-233,190,50,50);
		g2.rotate(Math.toRadians(45));
		g2.setColor(new Color(153,102,0));
		g2.fillRect(0,300,40,90);
	}

	public void paintData(Graphics g, StickMan s)
	{
		//Data
		Font font = new Font("Verdana", Font.BOLD, 12);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Time Elasped: " + timeElapsed, 10,20);
		g.drawString("Current time: " + getCurrentTime(), 10,35);


		g.drawString("PLANT POWER", 695,20);
		g.drawString("Your plant speed: " + Math.abs(s.getVel()) + " steps per sec", 550,35);
		g.drawString("Current Speed Multipler: " + s.getSpeedMultiplier(), 610,50);

		font = new Font("Verdana", Font.BOLD, 25);
		g.setFont(font);
		g.drawString("Score: " + score, 320,30);

	}

	public String getCurrentTime()
	{
		String time = "";
		String amPm = "";
		if(min<360||min>1080)
			amPm = "AM";
		else
			amPm = "PM";

		if(min%60<10)
			time = (hour%12+1) + ":" + "0" + (min%60) + " " + (amPm);
		else
			time = (hour%12+1) + ":" + (min%60) + " " + (amPm);
		return time;
	}

	public void calcTimeElapsed()
	{
		int tempMin = minTot;
		String time = "";
		int days = (int)(minTot/1440);
		tempMin = minTot%1440;
		int hours = (int)(tempMin/60);
		tempMin=tempMin%60;
		int mins = tempMin;

		if(days<10)
			time += "0" + days + ":";
		else
			time += days + ":";
		if(hours<10)
			time += "0" + hours + ":";
		else
			time += hours + ":";
		if(mins<10)
			time += "0" + mins;
		else
			time += mins;

		/*time +=(int)(minTot/1440)+":";
		tempMin = minTot%1440;
		time +=(int)(tempMin/60)+":";
		tempMin=tempMin%60;
		time += tempMin;*/
		timeElapsed = time;
	}

	public void drawCloudsLeft(Graphics g)
	{
		g.fillRect(xLeft,yLeft,100,50);
		g.fillOval(xLeft-25,yLeft,50,50);
		g.fillOval(xLeft+50,yLeft-25,50,50);
		g.fillOval(xLeft,yLeft-25,50,50);
		g.fillOval(xLeft,yLeft+25,50,50);
		g.fillOval(xLeft+50,yLeft+25,50,50);
		g.fillOval(xLeft+75,yLeft,50,50);
	}


	public int getScore()
	{
		return score;
	}

	public int getMin()
	{
		return min;
	}

	public String getTimeElapsed()
	{
		return timeElapsed;
	}

	public void startMessage(Graphics g)
	{
		Font font = new Font("Verdana", Font.BOLD, 20);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Welcome to the Game of Life!",225,200);

		font = new Font("Verdana", Font.BOLD, 15);
		g.setFont(font);
		g.drawString("You can move left or right using the arrow keys",195,275);
		g.drawString("Try to survive as long as possible! Good luck!",205,300);
		g.drawString("by Michael Hu",425,225);
	}

	//Rain = good and growth
}