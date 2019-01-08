package demo;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/*
*工具函数
 */
public class AlgoVisHelper {
    private AlgoVisHelper(){}
    ///线条宽度
    public static void setStrokeWidth(Graphics2D g2d,int w){
        int strokeWidth = w;
        /*
        * 第二个参数是端点的形状,设置为圆形更平滑
        * 第三个参数是折点的形状,ROUND同样更圆滑
        * 见P2-5
         */
        g2d.setStroke(new BasicStroke(strokeWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
    }

    ///绘制空心圆
    ///position: (x,y),半径: r
    public static void strokeCircle(Graphics2D g2d,int x,int y,int r){
        Ellipse2D circle=new Ellipse2D.Double(x-r,y-r,2*r,2*r);
        g2d.draw(circle);
    }

    ///绘制实心圆
    ///position: (x,y),半径: r
    public static void fillCircle(Graphics2D g2d,int x,int y,int r){
        Ellipse2D circle=new Ellipse2D.Double(x-r,y-r,2*r,2*r);
        g2d.fill(circle);
    }

    public static void setColor(Graphics2D g2d,Color color){
        g2d.setColor(color);
    }

    ///睡眠时间
    public static void pause(int t){
        try{
            Thread.sleep(t);
        }catch(InterruptedException e){
            System.out.println("Error in Sleepping.");
        }
    }
}
