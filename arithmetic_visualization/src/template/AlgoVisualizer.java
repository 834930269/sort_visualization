package template;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//可视化
//M-V-C Controller控制层
public class AlgoVisualizer {
    //TODO: 创建自己的数据
    private Object data;     //数据
    private AlgoFrame frame; //视图

    public AlgoVisualizer(int screenWidth,int screenHeight){
        //初始化数据
        //TODO: 初始化数据

        //初始化视图
        EventQueue.invokeLater(() -> {
            frame=new AlgoFrame("Welcome",screenWidth,screenHeight);
            //TODO: 视情况决定是否加入鼠标键盘监听器
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(()->{
                run();
            }).start();
        });
    }
    //动画逻辑
    private void run(){
        //TODO: 编写自己的动画逻辑
    }
    //TODO: 视情况决定是否实现鼠标键盘交互事件监听器类
    //自定义键盘事件
    private class AlgoKeyListener extends KeyAdapter {}
    //自定义鼠标事件
    private class AlgoMouseListener extends MouseAdapter{}

    public static void main(String[] args) {
        int screenWidth=800;
        int screenHeight=800;
        AlgoVisualizer visualizer=new AlgoVisualizer(screenWidth,screenHeight);
    }
}
