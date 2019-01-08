package InsertSort;

import com.sun.org.apache.bcel.internal.generic.DADD;

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

    public AlgoVisualizer(int screenWidth,int screenHeight,int N,InsertionSortData.Type dataType){
        //初始化数据
        //TODO: 初始化数据
        data=new InsertionSortData(N,screenHeight-100,dataType);
        //初始化视图
        EventQueue.invokeLater(() -> {
            frame=new AlgoFrame("Insertion Sort Visualization.",screenWidth,screenHeight);
            //TODO: 视情况决定是否加入鼠标键盘监听器
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(()->{
                run();
            }).start();
        });
    }

    public AlgoVisualizer(int screenWidth,int screenHeight,int N){
        this(screenWidth,screenHeight,N,InsertionSortData.Type.Default);
    }

    //动画逻辑
    private void run() {
        //TODO: 编写自己的动画逻辑
        while(!finish) {
            setData(0, -1);
            if(!stop) {
                for (int i = 0; i < data.N(); ++i) {
                    setData(i, i);//前闭后开
                    for (int j = i; j > 0 && data.get(j) < data.get(j - 1); j--) {
                        data.swap(j, j - 1);//一个一个往前找,大便交换
                        setData(i + 1, j - 1);
                    }
                }
                setData(data.N(), -1);
                finish=true;
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

    private void setData(int orderedIndex,int currentIndex){
        data.orderedIndex=orderedIndex;
        data.currentIndex=currentIndex;

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
