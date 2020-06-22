import java.awt.*;
import java.util.*;
import javax.swing.JOptionPane;
import java.awt.event.*;
import javax.swing.JApplet;
import java.applet.AudioClip;


public class GameOfLifeApplet extends JApplet implements Runnable
{
	private static final long serialVersionUID = 1L;
	Thread thread = new Thread(this);
	boolean running = true;
	StickMan s;
	Environment en;
	Droplet d;
	Droplet d2;
	Droplet d3;
	Droplet d4;
	Droplet d5;
	Droplet d6;
	Droplet d7;
	Droplet d8;
	Droplet d9;
	Droplet d10;
	boolean gameOver = false;
	boolean rain = false;
	int rainTime = 0;
	int level = 1;
	int xStorm;
	boolean introMessage = true;
	int count = 0;
	PowerUp p;
	boolean powerUpExists = false;
	int bonusCount = 0;
	boolean bonusDisplay = false;
	AudioClip backgroundMusic;

	//Double Buffering
	Image dbImage;
	Graphics dbg;

	public void init()
	{
		setFocusable(true);
		s = new StickMan();
		en = new Environment(this);
		d = new Droplet(this,50);
		d2 = new Droplet(this,127);
		d3 = new Droplet(this,205);
		d4 = new Droplet(this,283);
		d5 = new Droplet(this,361);
		d6 = new Droplet(this,438);
		d7 = new Droplet(this,516);
		d8 = new Droplet(this,594);
		d9 = new Droplet(this,672);
		d10 = new Droplet(this,750);
		p = new PowerUp();
		/*backgroundMusic = getAudioClip(getDocumentBase(),"//music here");
		backgroundMusic.play();*/
	}

	public void start()
	{
		thread.start();
	}

	public void run()
	{
		while(running)
		{

			if(rain){
				if(d.getHitStatus()||d2.getHitStatus()||
					d3.getHitStatus()||d4.getHitStatus()||
					d5.getHitStatus()||d6.getHitStatus()||
					d7.getHitStatus()||d8.getHitStatus()||
					d9.getHitStatus()||d10.getHitStatus())
					gameOver = true;

				d.update(this,s);
				d2.update(this,s);
				d3.update(this,s);
				d4.update(this,s);
				d5.update(this,s);
				d6.update(this,s);
				d7.update(this,s);
				d8.update(this,s);
				d9.update(this,s);
				d10.update(this,s);

				if(powerUpExists)
				{
					p.update(this,s);
				}
				else
				{
					p.reset();
					int powerUpChance = (int)(Math.random()*10000+1);
					if(powerUpChance > 9900)
					{
						powerUpExists = true;
					}

				}

				if(p.getHitStatus())
					powerUpExists = false;

				rainTime++;
			}
			else
			{
				int rainChance = (int)(Math.random()*10000+1);
				if(rainChance>9900)
					rain = true;
				powerUpExists = false;
			}

			if(rainTime>=200)
			{
				rain = false;
				rainTime = 0;
				level++;
				d.resetDroplet();
				d2.resetDroplet();
				d3.resetDroplet();
				d4.resetDroplet();
				d5.resetDroplet();
				d6.resetDroplet();
				d7.resetDroplet();
				d8.resetDroplet();
				d9.resetDroplet();
				d10.resetDroplet();
			}
			repaint();

			en.update(this,p);
			s.update(this,p);
		try
		{
			Thread.sleep(100);
		}
		catch(InterruptedException ex)
		{
			Thread.currentThread().interrupt();
		}

		}
	}

	//Double Buffering
	public void update(Graphics g)
	{
		dbImage = createImage(getWidth(),getHeight());
		dbg = dbImage.getGraphics();
		paint(dbg);
		g.drawImage(dbImage,0,0,this);
	}

	public void paint(Graphics g)
	{

		en.paint(g,this);
		if(introMessage)
		{
			en.startMessage(g);
			count++;
			if(count>=200)
				introMessage=false;
		}

		if(powerUpExists)
			p.paint(g,this);

		s.paint(g,this,en);
		if(rain){
			d.paint(g,this);
			d2.paint(g,this);
			d3.paint(g,this);
			d4.paint(g,this);
			d5.paint(g,this);
			d6.paint(g,this);
			d7.paint(g,this);
			d8.paint(g,this);
			d9.paint(g,this);
			d10.paint(g,this);
			drawCloudsDark(g);
		}

		if(gameOver){
			Font font = new Font("Verdana", Font.BOLD, 25);
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString("Game Over!",300,200);
			g.drawString("Score: " + en.getScore(),300,250);
			g.drawString("You survived for: " + en.getTimeElapsed(),200,300);
		}

		en.paintData(g,s);

		if(p.getHitStatus())
			bonusDisplay = true;

		if(bonusDisplay)
		{
			g.drawString("+200",400,60-bonusCount);
			bonusCount++;
			if(bonusCount>=25)
			{
				bonusDisplay = false;
				bonusCount = 0;
			}
		}

		g.setColor(Color.GREEN);
		g.fillRect(0,390,800,10);
	}

	public void stop()
	{
		running = false;
	}

	public void destroy()
	{
		running = false;
	}

	public int getLevel()
	{
		return level;
	}

	public boolean getRain()
	{
		return rain;
	}

	public void drawCloudsDark(Graphics g)
	{

		g.setColor(Color.GRAY);
		for(xStorm = 0; xStorm<=800; xStorm+=100){
		g.fillRect(xStorm,0,100,50);
		g.fillOval(xStorm-25,0,50,50);
		g.fillOval(xStorm+50,-25,50,50);
		g.fillOval(xStorm,-25,50,50);
		g.fillOval(xStorm,25,50,50);
		g.fillOval(xStorm+50,25,50,50);
		g.fillOval(xStorm+75,0,50,50);
		}
	}
}
