package ShellSort;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//可视化
//交换最少的排序,选择排序
//M-V-C Controller控制层,只更改数据,向V层发送控制信息
public class AlgoVisualizer {
    //TODO: 创建自己的数据
    private InsertionSortData data;     //数据
    private AlgoFrame frame; //视图
    private static int DELAY=20;
    private boolean stop=true;
    private boolean finish=false;

    public AlgoVisualizer(int screenWidth, int screenHeight, int N, InsertionSortData.Type dataType){
        //初始化数据
        //TODO: 初始化数据
        data=new InsertionSortData(N,screenHeight-100,dataType);
        //初始化视图
        EventQueue.invokeLater(() -> {
            frame=new AlgoFrame("Shell's Sort Visualization.",screenWidth,screenHeight);
            //TODO: 视情况决定是否加入鼠标键盘监听器
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(()->{
                run();
            }).start();
        });
    }

    public AlgoVisualizer(int screenWidth,int screenHeight,int N){
        this(screenWidth,screenHeight,N, InsertionSortData.Type.Default);
    }

    //动画逻辑
    private void run() {
        //TODO: 编写自己的动画逻辑
        while(!finish) {
            int dk;//增量
            setData(data.N(), -1);
            if(!stop) {
                for(dk=data.N();dk>=1;dk/=2) {
                    setData(dk, -1);
                    for (int i = dk; i < data.N(); ++i) {
                        setData(dk, i);//前闭后开
                        for (int j = i; j-dk >= 0 && data.get(j) < data.get(j - dk); j-=dk) {
                            data.swap(j, j - dk);//一个一个往前找,大便交换
                            setData(dk, j - dk);
                        }
                    }
                }
                setData(dk, -1);
                finish = true;
            }
        }
    }
    //TODO: 视情况决定是否实现鼠标键盘交互事件监听器类
    //自定义键盘事件
    private class AlgoKeyListener extends KeyAdapter {}
    //自定义鼠标事件
    private class AlgoMouseListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            stop=!stop;
        }
    }

    private void setData(int d,int currentIndex){
        data.d=d;
        data.currentIndex=currentIndex;
        if(d!=0)
            data.firstIndex=currentIndex%d;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        int screenWidth=800;
        int screenHeight=800;
        int N=100;
        AlgoVisualizer visualizer=new AlgoVisualizer(screenWidth,screenHeight,N);
    }
}
