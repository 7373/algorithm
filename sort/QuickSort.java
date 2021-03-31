package acm.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort implements IArraySort {

    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        return quickSort(arr, 0, arr.length - 1);
    }

    private int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }

    private int partition(int[] arr, int left, int right) {
        // 设定基准值（pivot）
        /**
         * 标杆
         */
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            /**
             * 从标杆右边找比他小的  交换
             */
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        /**
         * 为什么是index-1 是因为之前index++
         */
        swap(arr, pivot, index - 1);
        /**
         * 返回最后标杆留下的位置
         */
        return index - 1;
    }

    /**
     * 交换 i j
     */
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
