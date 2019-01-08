package demo;

import java.awt.*;
import java.awt.event.*;

//可视化
public class AlgoVisualizer {
    private Circle[] circles;
    private AlgoFrame frame;
    private boolean isAnimated=true;    //动画是否暂停

    public AlgoVisualizer(int screenWidth,int screenHeight,int N){
        circles=new Circle[N];
        int R=50;
        for(int i=0;i<N;++i){
            int x= (int)(Math.random()*(screenWidth-(R<<1)))+R;
            int y= (int)(Math.random()*(screenHeight-(R<<1)))+R;
            int vx= (int)(Math.random()*11)-5;
            int vy= (int)(Math.random()*11)-5;
            circles[i]=new Circle(x,y,R,vx,vy);
        }

        ///官方建议写法,把创建的过程放在时间分发的线程中,避免在线程增大导致的错误
        EventQueue.invokeLater(() -> {
            frame=new AlgoFrame("Welcome",screenWidth,screenHeight);
            frame.addKeyListener(new AlgoKeyListener());//添加自定义监听器
            frame.addMouseListener(new AlgoMouseListener());
            //消息队列中必须提供一个可以很快结束的程序
            //所以while必须放在一个线程中执行
            new Thread(()->{
                run();
            }).start();
        });
    }
    //mvc中具体绘制
    private void run(){
        while(true) {
            //绘制数据
            frame.render(circles);
            AlgoVisHelper.pause(20);

            if(isAnimated)
                for(Circle circle:circles)
                    circle.move(0,0,frame.getCanvasWidth(),frame.getCanvasHeight());
        }
    }
    //自定义键盘事件
    private class AlgoKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyChar()==' ')
                isAnimated=!isAnimated;
        }
    }

    //自定义鼠标事件
    private class AlgoMouseListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            //frame将menubar(菜单)的大小也算进去了,所以get不准确
            //用traslatePoint偏移下
            e.translatePoint(0,-(frame.getBounds().height-frame.getCanvasHeight()));
            for(Circle circle:circles)
                if(circle.contain(e.getPoint()))
                    circle.isFilled=!circle.isFilled;
        }
    }

    public static void main(String[] args) {
        int screenWidth=800;
        int screenHeight=800;
        int N=10;
        AlgoVisualizer visualizer=new AlgoVisualizer(screenWidth,screenHeight,N);
    }
}
