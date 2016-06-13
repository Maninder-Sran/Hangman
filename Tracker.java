/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;

public class Tracker extends JFrame{

Shape [] shapes = new Shape[10];    
static int score;
static int lives;
    
public Tracker(){ 
    add("Center", new drawScreen());
    shapes[0]  = new Line2D.Float(25, 10, 25, 325);
    shapes[1]  = new Line2D.Float(25, 10, 100, 10);
    shapes[2]  = new Line2D.Float(100, 10, 100, 40);
    shapes[3]  = new Line2D.Float(5, 325, 100, 325);
    shapes[4]  = new Ellipse2D.Float(60, 40, 80, 90);
    shapes[5]  = new Line2D.Float(100, 130, 100, 235);
    shapes[6]  = new Line2D.Float(100, 235, 65, 315);
    shapes[7]  = new Line2D.Float(100, 235, 135, 315);
    shapes[8]  = new Line2D.Float(50, 135, 100, 165);
    shapes[9]  = new Line2D.Float(150, 135,100, 165);    
    setSize(400, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
}

public static void setScore(int num)
{
    score += num;
}
public static void setLives(int num,char sign)
{
    switch(sign){
        case '+':
            lives += num;
            break;
        case '-':
            lives-=num;
            break;
        case 'n':
            lives = num;
            break;
        default:
            lives = 0;
            break;
    }
}
public static int getScore(){return score;}
public static int getLives(){return lives;}

public class drawScreen extends Canvas
{
    @Override
    public void paint(Graphics graphics)
    {
        Hangman user;
        Graphics2D g = (Graphics2D) graphics;
         
        for (int i = 0; i < lives; ++i) 
        {
           if(lives<=10&&lives>=0)
           {
              g.draw(shapes[i]);
              g.drawString("Score: "+getScore(),15,350);
           } 
        }
    }
}
}
