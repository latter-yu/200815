import java.util.Stack;

public class Test {
    public int JumpFloor(int target) {
        // 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级。
        // 求该青蛙跳上一个 n 级的台阶总共有多少种跳法（先后次序不同算不同的结果）。

        // 假设现在 6 个台阶，我们可以从第 5 跳一步到 6 ，这样的话有多少种方案跳到 5 就有多少种方案跳到 6
        // 另外我们也可以从 4 跳两步跳到 6，跳到 4 有多少种方案的话，就有多少种方案跳到 6
        // 其他的不能从 3 跳到 6 什么的啦，所以最后就是 f(6) = f(5) + f(4)

        if (target == 0) {
            return 0;
        }
        if (target == 1) {
            return 1;
        }
        if (target == 2) {
            return 2;
        }
        return JumpFloor(target - 1) + JumpFloor(target - 2);
    }

    public int RectCover(int target) {
        // 我们可以用 2 * 1 的小矩形横着或者竖着去覆盖更大的矩形。
        // 请问用 n 个 2 * 1 的小矩形无重叠地覆盖一个 2 * n 的大矩形，总共有多少种方法？
        // 比如 n = 3 时，2 * 3 的矩形块有 3 种覆盖方法

        if (target == 0) {
            return 0;
        }
        if (target == 1 || target == 2) {
            return target;
        }
        return RectCover(target - 1) + RectCover(target - 2);
    }

    public int NumberOf1(int n) {
        // 输入一个整数，输出该数 32 位二进制表示中 1 的个数。其中负数用补码表示。
        // 计算机中所有数据都是用补码保存的

        // 解析：
        // 如果一个整数不为 0，那么这个整数至少有一位是 1。
        // 如果我们把这个整数减 1，那么原来处在整数最右边的1就会变为 0
        // 原来在 1 后面的所有的 0 都会变成 1(如果最右边的 1 后面还有 0 的话)。
        // 其余所有位将不会受到影响。
        // 举个例子：一个二进制数 1100，从右边数起第三位是处于最右边的一个 1。
        // 减去 1 后，第三位变成 0，它后面的两位 0 变成了 1，而前面的 1 保持不变，因此得到的结果是 1011.
        // 我们发现减 1 的结果是把最右边的一个 1 开始的所有位都取反了。
        // 这个时候如果我们再把原来的整数和减去 1 之后的结果做与运算，从原来整数最右边一个 1 那一位开始所有位都会变成 0。
        // 如 1100 & 1011 = 1000.
        // 也就是说，把一个整数减去 1，再和原整数做与运算，会把该整数最右边一个 1 变成 0.
        // 那么一个整数的二进制有多少个 1，就可以进行多少次这样的操作。

        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    public double Power(double base, int exponent) {
        // 给定一个 double 类型的浮点数 base 和 int 类型的整数 exponent。
        // 求 base 的 exponent 次方。
        // 保证 base 和 exponent 不同时为 0

        if (base == 0 || base == 1 || exponent == 1) {
            return base;
        }
        if (exponent == 0 && base != 0) {
            return 1;
        }
        // 返回 double 类型
        double mul = 1.0;
        if (exponent > 0) {
            for (int i = 1; i <= exponent; i++) {
                mul *= base;
            }
        } else {
            // exponent 可能为负
            for (int i = 1; i <= -exponent; i++) {
                mul *= (1 / base);
            }
        }
        return mul;
    }

    public void reOrderArray(int [] array) {
        // 输入一个整数数组，实现一个函数来调整该数组中数字的顺序
        // 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分
        // 并保证奇数和奇数，偶数和偶数之间的相对位置不变

        // 使用冒泡排序
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] % 2 == 0 && array[j + 1] % 2 != 0) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }
}

class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        int ret = 0;
        if (stack2.isEmpty()) {
            // 需要先判断 栈2 是否为空
            while (!stack1.isEmpty()) {
                ret = stack1.pop();
                stack2.push(ret);
            }
        }
        return stack2.pop();
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
class Main {
    public ListNode ReverseList(ListNode head) {
        // 输入一个链表，反转链表后，输出新链表的表头

        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode prev = null;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public ListNode Merge(ListNode list1,ListNode list2) {
        // 输入两个单调递增的链表，输出两个链表合成后的链表
        // 当然我们需要合成后的链表满足单调递增规则

        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                cur.next = list2;
                list2 = list2.next;
            } else {
                cur.next = list1;
                list1 = list1.next;
            }
            cur = cur.next;
        }
        cur.next = (list1 == null) ? list2 : list1;
        return head.next;
    }
}