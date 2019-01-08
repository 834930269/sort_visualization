package QuickSort;

import java.util.Arrays;

public class QuicktionSortData {

    public enum Type{
        Default,
        NearlyOrdered
    }

    private int[] numbers;

    public int l,r;
    public int curPrivot;
    public int curElement;
    public boolean[] fixedPivots;//已经交换到正确位置的Pivot

    public QuicktionSortData(int N, int randomBound, Type dataType){
        numbers=new int[N];
        fixedPivots=new boolean[N];

        for(int i=0;i<N;++i){
            numbers[i]=(int)(Math.random()*randomBound)+1;
            fixedPivots[i]=false;
        }

        if(dataType== Type.NearlyOrdered){
            Arrays.sort(numbers);
            int swapTime=(int)(0.02*N);
            for(int i=0;i<swapTime;++i){
                int a=(int)(Math.random()*N);
                int b=(int)(Math.random()*N);
                swap(a,b);
            }
        }
    }

    public QuicktionSortData(int N, int randomBound){
        this(N,randomBound, Type.Default);
    }

    public int N(){return numbers.length;}

    public int get(int index){
        if(index<0 || index>=numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data");

        return numbers[index];
    }

    public void swap(int i,int j){
        if( i < 0 || i >= numbers.length || j < 0 || j >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");

        //这里不能用^交换是因为有可能出现两个数据一样的情况
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }

}
