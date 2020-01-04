package algorithmUtils.sortUtils.byFork_Join;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * *********************************************************************
 * <br/>
 * @Title MergeSortTask
 * @date 2019/1/23 13:46
 */
public class MergeSortTask extends RecursiveTask<Void> {
    //不需要返回值的task继承RecursiveAction更好
    private int[] array;
    private int left;
    private int right;

    public static void main(String[] args) {
        int[] a = {4,2,1,4,7,5,3,8,2,7,1,78,89,6,5,4,8,5};
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MergeSortTask task = new MergeSortTask(a, 0, a.length-1);
        Future<Void> result = forkJoinPool.submit(task);
        try {
            result.get();
            for (int n : a) {
                System.out.print(n + " ");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected Void compute() {
        boolean isNeedSplit = right - left >= 1;
        /*是否需要拆分*/
        if (isNeedSplit) {
            int mid = (left + right)/2;
            MergeSortTask mergeSortTask1 = new MergeSortTask(array, left, mid);
            MergeSortTask mergeSortTask2 = new MergeSortTask(array, mid+1, right);
            mergeSortTask1.fork();
            mergeSortTask2.fork();
            mergeSortTask1.join();
            mergeSortTask2.join();
            /*合并结果*/
            merge(array, left, mid, right);
        }else {
            int mid = (left+right)/2;
            merge(array, left, mid, right);
        }
        return null;
    }
    /**
     * *********************************************************************
     * <br/>    两部分合并为一部分
     * /**
     * 两两归并排好序的数组（2路归并）
     *
     * @param nums   带排序数组对象
     * @param left   左边数组的第一个索引
     * @param center 左数组的最后一个索引，center + 1右数组的第一个索引
     * @param right  右数组的最后一个索引
     */

    public static void merge(int a[], int left, int mid, int right) {
        int len = right - left + 1;
        int temp[] = new int[len];
        int i = left;
        int j = mid + 1;
        int index = 0;
        while(i<=mid && j <= right) {
            temp[index++] = a[i] <= a[j] ? a[i++] : a[j++];
        }
        while (i <= mid) {
            temp[index++] = a[i++];
        }
        while (j<=right) {
            temp[index++] = a[j++];
        }
        for (int k = 0; k<temp.length; k++) {
            a[left++] = temp[k];
        }
    }


    public MergeSortTask(int[] array, int left, int right) {
        this.array = array;
        this.left = left;
        this.right = right;
    }
}