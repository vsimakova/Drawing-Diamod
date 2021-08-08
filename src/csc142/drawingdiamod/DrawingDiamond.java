package csc142.drawingdiamod;

import java.awt.*;

public class DrawingDiamond {

	//constants
    public static final int WIDTH_CANVAS = 500;
    public static final int HEIGHT_CANVAS = 500;
    public static final int STEP = 4;
    
	public static void main(String[] args) {
		part1();
		part2();
	}
	
	/**
     * Method draws figure which consist of parabolic curves made up with 
     * straight lines and without using actual curves.
     */
    public static void part1(){
        DrawingPanel dp = new DrawingPanel(WIDTH_CANVAS,HEIGHT_CANVAS);
        dp.setBackground(Color.lightGray);
        Graphics g = dp.getGraphics();
        //set two points for first line
        int x1=WIDTH_CANVAS/2;
        int y1=HEIGHT_CANVAS/2-STEP;
        int x2=WIDTH_CANVAS;
        int y2=HEIGHT_CANVAS/2;
        int i=0;//variable to help move x2-coordinate each time 
        int howMany = HEIGHT_CANVAS/2/STEP;//how many steps we need
        Color color = color();
        g.setColor(color);
        for(int e=1;e<=howMany;e++){
            //takes percent of step which were made to set new color
            if(e==(int)(howMany*0.08)){
                color = color();
                g.setColor(color);
            }
            if(e==(int)(howMany*0.63)){
                color = color();
                g.setColor(color);
            }
            if(e==(int)(howMany*0.81)){
                color = color();
                g.setColor(color);
            }
            g.drawLine(x1,y1,x2,y2);//draws line in 1st quadrant
            y1+=STEP*2*e;//moves first point down to create 2nd line in the
            //4th quadrant which is symmetric to the line in 1st quadrant
            g.drawLine(x1,y1,x2,y2);//draws line in 4th quadrant
            x2-=WIDTH_CANVAS-STEP*i++;//moves second point from right to the
            //left to create symmetric line to the line in 4th quadrant
            g.drawLine(x1,y1,x2,y2);//draws line in 3rd quadrant
            y1-=STEP*2*e;//moves first point to initial place completing simple
            //diamond
            g.drawLine(x1,y1,x2,y2);//draws line in 2nd quadrant
            //moves line(both points) by step to create next simple diamond
            y1-=STEP;
            x2+=WIDTH_CANVAS-STEP*i++;
        }
    }

    /**
     * Method creates random color.
     * @return returns new random color.
     */
    public static Color color(){
        int red = (int)(Math.random()*256);
        int green = (int)(Math.random()*256);
        int blue = (int)(Math.random()*256);
        Color color = new Color(red, green, blue);
        return color;
    }
    
    /**
     * Method draws rotated figure which consist of parabolic curves made up 
     * with straight lines and without using actual curves.
     */
    public static void part2(){
        DrawingPanel dp = new DrawingPanel(WIDTH_CANVAS,HEIGHT_CANVAS);
        dp.setBackground(Color.lightGray);
        Graphics g = dp.getGraphics();
        //take triangle ABC where AB is x-axis, BC is y-axis, and usind 
        //Pythagorean Theorem find new axis(AC - hypotenuse)for rotated figure
        int newAxis = pythagorean();
        //find how many steps we need to make on new axis to keep the same step
        //which we used on regular axis
        int howMany = newAxis/STEP;
        //determine new step on regular axis which will correspond to normal
        //step on new axis
        int newStep = (int)Math.round(WIDTH_CANVAS/2.0/howMany);
        //set first line(two points)
        int x1=WIDTH_CANVAS/2;
        int y1=HEIGHT_CANVAS/2;
        int x2=WIDTH_CANVAS;
        int y2=HEIGHT_CANVAS;
        int i = 0;//variable to help move coordinate each time
        int k = 0;//variable to help move x2-coordinate each time
        //creating gradient
        Color start = Color.yellow;
        Color end = Color.magenta;
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradient = new GradientPaint(x1,y1,start,x2,y2,end);
        g2d.setPaint(gradient);
        for(int e=0;e<=WIDTH_CANVAS/2;e+=newStep){
            if(e>=WIDTH_CANVAS/2*0.5){//takes percent of step which were made to
                //create and to set one more gradient
                start = Color.magenta;
                end = Color.green;
                gradient = new GradientPaint(x1,y1,start,x2,y2,end);
                g2d.setPaint(gradient);
            }
            //draws figure in the same manner as in part1 just in different direction
            g.drawLine(x1,y1,x2,y2);
            x1-=newStep*2*i;
            y1+=newStep*2*i;
            g.drawLine(x1,y1,x2,y2);
            x2-=WIDTH_CANVAS-newStep*k;
            y2-=HEIGHT_CANVAS-newStep*k++;
            g.drawLine(x1,y1,x2,y2);
            x1+=newStep*2*i;
            y1-=newStep*2*i++;
            g.drawLine(x1,y1,x2,y2);
            x1+=newStep;
            y1-=newStep;
            x2+=WIDTH_CANVAS-newStep*k;
            y2+=HEIGHT_CANVAS-newStep*k++;
        }
    }

    /**
     * Method calculates new axis using Pythagorean Theorem.
     */
    public static int pythagorean(){
        int AB = WIDTH_CANVAS/2;//AB is x-axis
        int BC = HEIGHT_CANVAS/2;//BC is y-axis
        int sum = AB*AB+BC*BC;
        int AC = (int)Math.sqrt(sum);
        return AC;//new axis(AC - hypotenuse)
    }
}
