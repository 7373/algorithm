package algorithmUtils.sortUtils.byFork_Join;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;

/**
 * *********************************************************************
 * <br/>
 * @author  Yien <br/>
 * @Title QuickSortTask
 */

public class QuickSortTask extends RecursiveAction {

    private int[] array;
    private int left;
    private int right;

    @Override
    protected void compute() {
        int pivot = partition(array, left, right);
        QuickSortTask task1 = null;
        QuickSortTask task2 = null;
        /*左边再partition*/
        if (pivot - left > 1) {
            task1 = new QuickSortTask(array, left, pivot-1);
            task1.fork();
        }
        /*右边partition*/
        if (right - pivot > 1) {
            task2 = new QuickSortTask(array, pivot+1, right);
            task2.fork();
        }
        if (task1 != null && !task1.isDone()) {
            task1.join();
        }
        if (task2 != null && !task2.isDone()) {
            task2.join();
        }
    }

    /*拆分成：左边小于flag 右边大于flag*/
    public static int partition(int[] a, int left, int right) {
        /*标杆寻找：这里可以优化提高效率*/
        int pivot = a[left];
        while (left < right) {
            /*从最右边开始找比标杆小的*/
            while (left < right && a[right] >= pivot) {
                right--;
            }
            /*标杆右边小于标杆，则交换*/
            swap(a, left, right);
             /*从最左边开始找比标杆大的*/
            while (left < right && a[left] <= pivot) {
                left++;
            }
            /*标杆左边大于标杆，则交换*/
            swap(a, left, right);
        }
        /*left=right 返回其中一个位置就可以*/
        return left;
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {4,2,1,4,7,5,3,8,2,7,1,78,89,6,5,4,8,5};
        /*使用ForkJoin线程池*/
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        /*创建一个线程任务*/
        QuickSortTask task = new QuickSortTask(a, 0, a.length-1);
        /*提交一个快排任务*/
        /*注意两种方式区别*/
//        Future<Void> result = forkJoinPool.execute(task);
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

    public QuickSortTask(int[] array, int left, int right) {
        this.array = array;
        this.left = left;
        this.right = right;
    }
}


