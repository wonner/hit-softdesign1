package stronglyconnected;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;


public class DrawTest
{
   public static void main(String[] args) throws IOException
   {
	   AdjGraph g=new AdjGraph();
		g.createAdjGraph();
		g.findComponent();
      EventQueue.invokeLater(new Runnable()
    	{
            public void run()
            {
               JFrame frame = new DrawFrame(g);
               frame.setTitle("DrawTest");
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setVisible(true);
            }
         });
   }
}

/**
 * A frame that contains a panel with drawings
 */
class DrawFrame extends JFrame
{
   public DrawFrame(AdjGraph g)
   {      
      add(new DrawComponent(g));
      pack();
   }
}

/**
 * A component that displays rectangles and ellipses.
 */
class DrawComponent extends JComponent
{
   private static final int DEFAULT_WIDTH = 500;
   private static final int DEFAULT_HEIGHT = 500;
   private AdjGraph graph;
   
   public DrawComponent(AdjGraph g)
   {
	   this.graph=g;
   }

   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;

      double[] x=new double[this.graph.getN()];
      double[] y=new double[this.graph.getN()];
      
      for(int i=0;i<this.graph.getN();i++)
      {
    	  Random random=new Random();

          double centerX = random.nextDouble()*500;
          double centerY = random.nextDouble()*500;
          double radius = 15;
          x[i]=centerX;
          y[i]=centerY;
          
          Ellipse2D circle = new Ellipse2D.Double();
          circle.setFrameFromCenter(centerX, centerY, centerX + radius, centerY + radius);
          g2.setPaint(Color.RED);
          g2.draw(circle);
          if(this.graph.getVertexNode(i).getIsArtis())
        	  g2.fill(circle);
          g2.setPaint(Color.BLACK);
          g2.drawString(this.graph.getVertexNode(i).getVertex(),(int)centerX,(int)centerY);
        	  
      }
      g2.setPaint(Color.BLACK);
      for(int i=0;i<this.graph.getN();i++)
      {
    	  EdgeNode temp=this.graph.getVertexNode(i).getFirstedge();
    	  while(temp!=null)
    	  {
    		  g2.draw(new Line2D.Double(x[i],y[i],x[temp.getIndex()],y[temp.getIndex()]));
    		  temp=temp.getNext();
    	  }
      }
     
   }
   
   public Dimension getPreferredSize() { return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT); }
}