package algs.problem;

import algs.Stack;

public class StackOutSeqCheck {
    public static void main(String[] args) {
        final String[] strings = {"7 6 5 9 8 4 3 2 1 0",
                "3 2 1 0 4 5 6 7 8 9", "2 3 1 5 0 6 7 4 8 9", "0 1 2 4 3 6 5 9 8 7", "0 1 2 4 5 6 7 9 3 8"};
        for (String string : strings) {
            final String[] split = string.split(" ");
            int[] outSeq = new int[split.length];
            int i = 0;
            for (String s : split) {
                outSeq[i++] = Integer.parseInt(s);
            }
            Stack<Integer> stack = new Stack<>();

            int inSeq = -1;
            boolean result = true;
            for (int os : outSeq) {
                if (os > inSeq) {
                    while (os > inSeq + 1) {
                        stack.push(++inSeq);
                    }
                } else {
                    final Integer pop = stack.pop();
                    if (pop != os)
                        result = false;
                    break;
                }
            }
            System.out.println(result);
        }
    }
}
