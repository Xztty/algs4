/**
 * User: ZY
 * Date: 8/19/019
 * Time: 23:01
 */

package algs.test;

import algs.Stack;

public class TestStack
{
    public static void main(String[] args)
    {
        Stack<String> stack = new Stack<>();
        stack.push("h");
        stack.push("e");
        stack.push("l");
        stack.push("l");

        for (int i = 0; i < 4; i++)
        {
            System.out.println(stack.pop());
        }
    }
}
