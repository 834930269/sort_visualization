package Selection_Sort;

public class SelectionSortData {
    private int[] numbers;

    public int orderedIndex=-1; //{0..orderIndex有序}
    public int currentMinIndex=-1; //当前找到的最小元素的索引
    public int currentCompareIndex=-1; //当前正在比较元素的索引

    public SelectionSortData(int N,int randomBound){
        numbers=new int[N];
        for(int i=0;i<N;++i){
            numbers[i]=(int)(Math.random()*randomBound)+1;
        }
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
