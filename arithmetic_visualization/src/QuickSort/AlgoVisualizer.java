package QuickSort;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//可视化
//交换最少的排序,选择排序
//M-V-C Controller控制层,只更改数据,向V层发送控制信息
public class AlgoVisualizer {
    //TODO: 创建自己的数据
    private QuicktionSortData data;     //数据
    private AlgoFrame frame; //视图
    private static int DELAY=20;
    private boolean stop=true;
    private boolean finish=false;

    public AlgoVisualizer(int screenWidth, int screenHeight, int N, QuicktionSortData.Type dataType){
        //初始化数据
        //TODO: 初始化数据
        data=new QuicktionSortData(N,screenHeight-100,dataType);
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
        this(screenWidth,screenHeight,N, QuicktionSortData.Type.Default);
    }

    //动画逻辑
    private void run() {
        //TODO: 编写自己的动画逻辑
        setData(-1,-1,-1,-1,-1);
        quickSort(0,data.N()-1);
        setData(-1,-1,-1,-1,-1);
    }

    private void quickSort(int l,int r){
        if(l>r)
            return;

        if(l==r){
            setData(l,r,l,-1,-1);
            return;
        }

        setData(l,r,-1,-1,-1);
        int p=partition(l,r);
        quickSort(l,p-1);
        quickSort(p+1,r);
    }

    private int partition(int l,int r){
            int v=data.get(l);
            setData(l,r,-1,l,-1);
            int j=l;
            for(int i=l+1;i<=r;++i){
                setData(l,r,-1,l,i);
                if(data.get(i)<v){
                    j++;
                    data.swap(j,i);
                    setData(l,r,-1,l,i);
                }
            }
            data.swap(l,j);
            setData(l,r,j,-1,-1);
            return j;
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

    private void setData(int l,int r,int fixedPivot,int curPivot,int curElement){
        data.l=l;
        data.r=r;
        if(fixedPivot!=-1)
            data.fixedPivots[fixedPivot]=true;
        data.curPrivot=curPivot;
        data.curElement=curElement;

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
