package MergeSort;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

//可视化
//交换最少的排序,选择排序
//M-V-C Controller控制层,只更改数据,向V层发送控制信息
public class AlgoVisualizer {
    //TODO: 创建自己的数据
    private MergeSortData data;     //数据
    private AlgoFrame frame; //视图
    private static int DELAY=20;
    private boolean stop=true;
    private boolean finish=false;

    public AlgoVisualizer(int screenWidth, int screenHeight, int N, MergeSortData.Type dataType){
        //初始化数据
        //TODO: 初始化数据
        data=new MergeSortData(N,screenHeight-100,dataType);
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
        this(screenWidth,screenHeight,N, MergeSortData.Type.Default);
    }

    //动画逻辑
    private void run() {
        //TODO: 编写自己的动画逻辑
        setData(-1,-1,-1);
        mergeSort(0,data.N()-1);
    }

    private void mergeSort(int l,int r){
        if(l>=r)
            return;

        setData(l,r,-1);

        int mid=(l+r)/2;
        mergeSort(l,mid);
        mergeSort(mid+1,r);
        merge(l,mid,r);

    }

    private void merge(int l,int mid,int r){
        int[] aux= Arrays.copyOfRange(data.numbers,l,r+1);
        int i=l,j=mid+1;
        for(int k=l;k<=r;++k){
            if(i>mid){//左半边处理完
                data.numbers[k]=aux[j-l];j++;
            }else if(j>r){//右半边处理完
                data.numbers[k]=aux[i-l];i++;
            }else if(aux[i-l]<aux[j-l]){//左<右
                data.numbers[k]=aux[i-l];++i;
            }else{//右<左
                data.numbers[k]=aux[j-l];++j;
            }
            setData(l,r,k);
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

    private void setData(int l,int r,int mergeIndex){
        data.l=l;
        data.r=r;
        data.mergeIndex=mergeIndex;

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
