package com.tauros.kaleido.aron.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;

/**
 * Created by zhy on 2018/10/27.
 */
@Controller
@Slf4j
public class IndexController {

    @Resource
    private RedisTemplate redisTemplate;

    @RequestMapping(value = {"/", "/index", "/home"})
    @ResponseBody
    public String index() {
        return "kaleido-aron" + redisTemplate;
    }

    static class ListNode {
        int      val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static class Heap<T> {
        private Object[] array = new Object[0];
        private int      size  = 0;
        private Comparator<T> comparator;

        public Heap(Comparator<T> comparator) {
            this.comparator = comparator;
        }

        private void ensure(int newSize) {
            while (newSize + 1 > array.length * 0.9) {
                int nowSize = array.length + (array.length >> 1) + 2;
                Object[] newArray = new Object[nowSize];
                if (size > 0) {
                    System.arraycopy(array, 1, newArray, 1, size);
                }
                array = newArray;
            }
        }

        @SuppressWarnings("unchecked")
        private void swap(int indexA, int indexB) {
            T tmp = (T) array[indexA];
            array[indexA] = array[indexB];
            array[indexB] = tmp;
        }

        @SuppressWarnings("unchecked")
        private int moveDown(int u) {
            int target = u;
            int l = u << 1;
            int r = l | 1;
            if (l <= size && comparator.compare((T) array[target], (T) array[l]) > 0)
                target = l;
            if (r <= size && comparator.compare((T) array[target], (T) array[r]) > 0)
                target = r;
            if (target != u) {
                swap(u, target);
                return moveDown(target);
            }
            return target;
        }

        @SuppressWarnings("unchecked")
        public int add(T o) {
            ensure(size + 1);
            array[++size] = o;
            return moveUp(size);
        }

        @SuppressWarnings("unchecked")
        public T pop() {
            if (size == 1) {
                size--;
                return (T) array[1];
            }
            T top = (T) array[1];
            swap(1, size--);
            moveDown(1);
            return top;
        }

        @SuppressWarnings("unchecked")
        public T top() {
            return (T) array[1];
        }

        public int size() {
            return size;
        }

        public void build(T[] array) {
            ensure(array.length);
            System.arraycopy(array, 0, this.array, 1, array.length);
            this.size = array.length;
            for (int i = size >> 1; i >= 1; i--) {
                moveDown(i);
            }
        }

        @SuppressWarnings("unchecked")
        public int modifyByIndex(int index, T val) {
            if (index > size) {
                return -1;
            }
            array[index] = val;
            if (index == 1) {
                return moveDown(index);
            } else if (index > size >> 1) {
                return moveUp(index);
            } else {
                int parent = index >> 1;
                boolean moveUp = comparator.compare((T) array[parent], (T) array[index]) > 0;
                if (moveUp) {
                    return moveUp(index);
                }
                return moveDown(index);
            }
        }

        @SuppressWarnings("unchecked")
        private int moveUp(int index) {
            int now = index;
            int parent = now >> 1;
            while (parent > 0 && comparator.compare((T) array[parent], (T) array[now]) > 0) {
                swap(now, parent);
                parent = (now = parent) >> 1;
            }
            return now;
        }

        public void removeVal(T val) {
            int index = -1;
            for (int i = 1; i <= size; i++) {
                if (array[i] == val) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                return;
            }
            swap(index, size--);
            moveDown(index);
        }

        @SuppressWarnings("unchecked")
        public int findIndex(Function<T, Boolean> function) {
            for (int i = 1; i <= size; i++) {
                if (function.apply((T) array[i])) {
                    return i;
                }
            }
            return -1;
        }

        @SuppressWarnings("unchecked")
        public T get(int index) {
            if (index < 1 || index > size) {
                return null;
            }
            return (T) array[index];
        }
    }

    static class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class NumArray {

        private int[] nums;
        private Node root;

        class Node {
            int l;
            int r;
            int sum;
            Node left;
            Node right;
        }

        private Node build(int l, int r) {
            if (l > r) {
                return null;
            }
            if (l == r) {
                Node node = new Node();
                node.l = l;
                node.r = r;
                node.sum = nums[l];
                return node;
            }

            int mid = (l + r) >> 1;
            Node left = build(l, mid);
            Node right = build(mid + 1, r);
            Node node = new Node();
            node.l = l;
            node.r = r;
            node.left = left;
            node.right = right;
            node.sum = (left == null ? 0 : left.sum) + (right == null ? 0 : right.sum);
            return node;
        }

        public NumArray(int[] nums) {
            this.nums = nums;
            root = build(0, nums.length - 1);
        }

        private void update(Node node, int i) {
            if (node.l == node.r && node.l == i) {
                node.sum = nums[i];
                return;
            }
            Node left = node.left;
            Node right = node.right;
            if (left != null && i >= left.l && i <= left.r) {
                update(left, i);
            }
            if (right != null && i >= right.l && i <= right.r) {
                update(right, i);
            }
            node.sum = (left == null ? 0 : left.sum) + (right == null ? 0 : right.sum);
        }

        public void update(int i, int val) {
            nums[i] = val;
            update(root, i);
        }

        private int sumRange(Node node, int l, int r) {
            if (node.l == l && node.r == r) {
                return node.sum;
            }
            int mid = (node.l + node.r) >> 1;
            if (r <= mid) {
                return sumRange(node.left, l, r);
            }
            if (l > mid) {
                return sumRange(node.right, l, r);
            }
            return sumRange(node.left, l, mid) + sumRange(node.right, mid + 1, r);
        }

        public int sumRange(int i, int j) {
            return sumRange(root, i, j);
        }
    }

    public static void main(String[] args) {
//        Heap<Integer> heap = new Heap<>(Integer::compareTo);
//        Integer[] nums = new Integer[]{1, 100, 199, 12, 13, 14, 15, 14, 24};
//        heap.build(nums);
//        while (heap.size() > 0) {
//            System.out.println(heap.pop());
//        }
        for (int i =0 ; i < 100; i++) {
            System.out.println(i & 15);
        }
    }
}
