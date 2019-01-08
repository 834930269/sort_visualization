package InsertSort;

import javax.swing.*;
import java.awt.*;

/// M-V-C View层
public class AlgoFrame extends JFrame {
    private int canvasWidth;
    private int canvasHeight;

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    public AlgoFrame(String title, int canvasWidth, int canvasHeight){
        super(title);//调用继承的JFrame的构造函数JFrame(TITLE)

        this.canvasHeight=canvasHeight;
        this.canvasWidth=canvasWidth;

        AlgoCanvas canvas=new AlgoCanvas();
        /*
        *所谓Dimension,就是一个包裹了width和height的类
        * 在Panel中重写getpreferredsize,可以在创建初期直接返回自定义大小(程序猿定义)
         */
        //canvas.setPreferredSize(new Dimension(canvasWidth,canvasHeight));
        setContentPane(canvas);
        pack();//根据加载进窗口的大小调整窗口大小
        setResizable(false);//禁止改变大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//点击X后进程结束
        setVisible(true);//使窗口显示
    }

    public AlgoFrame(String title){
        this(title,1024,768);
    }

    private InsertionSortData data;
    public void render(InsertionSortData data){
        this.data=data;
        repaint();//刷新
    }

    //JPanel,画布,自支持双缓存
    private class AlgoCanvas extends JPanel{

        public AlgoCanvas(){
            super(true);
        }

        ///override的作用之一是减少bug,因为自己可能写的函数名并不是要重写父类的那个函数
        ///但如果override没报错,那就是对的
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);

            Graphics2D g2d=(Graphics2D)g;

            //抗锯齿
            RenderingHints hints=new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            //具体绘制
            //TODO: 绘制自己的数据data

            AlgoVisHelper.drawText(g2d,"插入排序.O(N^2),蓝色:待插入元素",canvasWidth/2,(this.getBounds().height-canvasHeight),12);
            AlgoVisHelper.drawText(g2d,"每次新加入一个元素,将该元素插入到已排好序的合适的位置",canvasWidth/2,(this.getBounds().height-canvasHeight)+16,12);
            AlgoVisHelper.setColor(g2d,AlgoVisHelper.DeepOrange);
            AlgoVisHelper.drawText(g2d,"若数列基本有序,则插排进化为O(N),在N比较小时常用插排",canvasWidth/2,(this.getBounds().height-canvasHeight)+40,24);

            int w = canvasWidth/data.N();
            AlgoVisHelper.setColor(g2d, AlgoVisHelper.LightBlue);
            for(int i = 0 ; i < data.N() ; i ++ ) {
                if(i<data.orderedIndex)
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.Red);
                else
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.Grey);

                if(i==data.currentIndex)
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.LightBlue);
                AlgoVisHelper.fillRectangle(g2d, i * w, canvasHeight - data.get(i), w - 1, data.get(i));

                AlgoVisHelper.setColor(g2d, AlgoVisHelper.Red);
                AlgoVisHelper.drawText(g2d,"已排序: ",50,(this.getBounds().height-canvasHeight)+10,24);
                AlgoVisHelper.drawText(g2d,String.valueOf(data.orderedIndex),150,(this.getBounds().height-canvasHeight)+10,24);
            }
        }

        @Override
        public Dimension getPreferredSize(){
            return new Dimension(canvasWidth,canvasHeight);
        }

    }

}
