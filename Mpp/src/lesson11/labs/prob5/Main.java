package lesson11.labs.prob5;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static <T extends Comparable<? extends T>> T secondSmallest(List<? extends T> list) {
        return list.stream().sorted().collect(Collectors.toList()).get(list.size() - 2);
    }

    public class myPredicate<T> implements Predicate<T> {

        @Override
        public boolean test(T t) {
            return false;
        }
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(5);
        t.left = new TreeNode(2);
        t.right = new TreeNode(13);
        convertBST(t);
        System.out.println(t.val);
        System.out.println(t.left.val);
        System.out.println(t.right.val);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static int sum = 0;

    static void dfs(TreeNode cur) {
        if (cur == null) {
            return;
        }
        dfs(cur.right);
        sum += cur.val;
        cur.val = sum;
        dfs(cur.left);
    }

    public static TreeNode convertBST(TreeNode root) {
        // Write your code here
        dfs(root);
        return root;
    }
}
