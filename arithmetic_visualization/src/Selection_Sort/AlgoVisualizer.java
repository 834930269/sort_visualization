package Selection_Sort;

import jdk.internal.org.objectweb.asm.tree.MethodInsnNode;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

//可视化
//交换最少的排序,选择排序
//M-V-C Controller控制层,只更改数据,向V层发送控制信息
public class AlgoVisualizer {
    //TODO: 创建自己的数据
    private SelectionSortData data;     //数据
    private AlgoFrame frame; //视图
    private static int DELAY=30;

    public AlgoVisualizer(int screenWidth,int screenHeight,int N){
        //初始化数据
        //TODO: 初始化数据
        data=new SelectionSortData(N,screenHeight);
        //初始化视图
        EventQueue.invokeLater(() -> {
            frame=new AlgoFrame("Selection Sort Visualization.",screenWidth,screenHeight);
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
        setData(0,-1,-1);

        for(int i=0;i<data.N();++i){
            int minIndex=i;
            setData(i,-1,minIndex);
            for(int j=i+1;j<data.N();++j){
                setData(i,j,minIndex);
                if(data.get(j)<data.get(minIndex)) {
                    minIndex = j;
                    setData(i,j,minIndex);
                }
            }
            data.swap(i,minIndex);
            setData(i+1,-1,-1);
        }
        setData(data.N(),-1,-1);
    }
    //TODO: 视情况决定是否实现鼠标键盘交互事件监听器类
    //自定义键盘事件
    private class AlgoKeyListener extends KeyAdapter {}
    //自定义鼠标事件
    private class AlgoMouseListener extends MouseAdapter{}

    private void setData(int orderedIndex,int currentCompareIndex,int currentMinIndex){
        data.orderedIndex=orderedIndex;
        data.currentCompareIndex=currentCompareIndex;
        data.currentMinIndex=currentMinIndex;

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
