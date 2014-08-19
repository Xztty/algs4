/**
 * User: ZY
 * Date: 8/19/019
 * Time: 22:50
 */

package algs;

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item>
{
    private static final int INIT_CAPACITY = 2;
    private static final int RESIZE_FACTOR = 2;
    private Object[] items;
    private int top;

    public Stack()
    {
        items = new Object[INIT_CAPACITY];
    }

    public void push(Item item)
    {
        if (top == items.length - 1)
        {
            resize();
        }
        items[top++] = item;
    }

    private void resize()
    {
        Object[] newItems = new Object[items.length * RESIZE_FACTOR];
        System.arraycopy(items, 0, newItems, 0, items.length);
        items = newItems;
    }

    public Item pop()
    {
        return (Item)items[--top];
    }

    public int size()
    {
        return top;
    }

    @Override
    public Iterator<Item> iterator()
    {
        return null;
    }
}
