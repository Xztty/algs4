package algs.assignment;

import algs.std.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayQueue<Item> implements Iterable<Item>
{
    private static final int RESIZE_FACTOR = 2;
    private static final int SHRINK_FACTOR = 4;

    private Item[] queue;
    private int head;
    private int tail;
    private int count;

    public ResizingArrayQueue()
    {
        queue = (Item[]) new Object[2];
    }

    public int size()
    {
        return count;
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }

    public void resize(int max)
    {
        if (max <= count)
        {
            throw new UnsupportedOperationException();
        }
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < count; i++)
        {
            temp[i] = queue[(i + head) % queue.length];
        }
        queue = temp;
        head = 0;
        tail = count;
    }

    public void enqueue(Item item)
    {
        if (item == null)
        {
            throw new NullPointerException();
        }
        if (size() == queue.length)
        {
            resize(RESIZE_FACTOR * queue.length);
        }
        queue[tail++] = item;
        count++;
        if (tail == queue.length)
        {
            tail = 0;
        }
        printQueueInfo("enqueue:" + item);
    }

    public Item dequeue()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        Item item = queue[head];
        queue[head] = null;
        head++;
        count--;
        if (head == queue.length)
        {
            head = 0;
        }
        if (count < queue.length / SHRINK_FACTOR)
        {
            resize(queue.length / SHRINK_FACTOR);
        }
        printQueueInfo("dequeue:" + item);
        return item;
    }

    private void exch(int head, int dequeueIndex)
    {
        Item item = queue[head];
        queue[head] = queue[dequeueIndex];
        queue[dequeueIndex] = item;
    }

    private void printQueueInfo(String action)
    {
        System.out.printf("action:%s, head:%d, tail:%d, count:%d, queue.length:%d\n", action, head, tail, count,
            queue.length);
    }

    public static void main(String[] args)
    {
        final ResizingArrayQueue<String> stringResizingArrayQueue = new ResizingArrayQueue<>();
        stringResizingArrayQueue.enqueue("a");
        stringResizingArrayQueue.enqueue("b");
        stringResizingArrayQueue.dequeue();
        stringResizingArrayQueue.enqueue("c");
        stringResizingArrayQueue.enqueue("d");
        stringResizingArrayQueue.enqueue("e");
        stringResizingArrayQueue.enqueue("f");
        stringResizingArrayQueue.enqueue("g");
        stringResizingArrayQueue.dequeue();
        stringResizingArrayQueue.dequeue();
        stringResizingArrayQueue.dequeue();
        stringResizingArrayQueue.dequeue();
        stringResizingArrayQueue.dequeue();
        stringResizingArrayQueue.dequeue();
    }

    @Override
    public Iterator<Item> iterator()
    {
        return null;
    }
}
