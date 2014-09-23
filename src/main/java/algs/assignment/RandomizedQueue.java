package algs.assignment;

import algs.std.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item>
{
    private static final int RESIZE_FACTOR = 2;
    private static final int SHRINK_FACTOR = 4;

    private Item[] queue;
    private int head;
    private int tail;
    private int count;

    public RandomizedQueue()
    {
        queue = (Item[]) new Object[2];
    }

    public Item sample()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        randomExchHead();
        final Item item = queue[head];
//        printQueueInfo("sample:" + item);
        return item;
    }

    @Override
    public Iterator<Item> iterator()
    {
        return new RandomizedIterator();
    }

    public class RandomizedIterator implements Iterator<Item>
    {
        private int next;
        private int[] itemRandomIndex;

        public RandomizedIterator()
        {
            itemRandomIndex = new int[count];
            for (int i = 0; i < count; i++)
            {
                itemRandomIndex[i] = (head + i) % queue.length;
            }
            StdRandom.shuffle(itemRandomIndex);
        }

        @Override
        public boolean hasNext()
        {
            return itemRandomIndex.length > next;
        }

        @Override
        public Item next()
        {
            return queue[itemRandomIndex[next++]];
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }

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
//        printQueueInfo("enqueue:" + item);
    }

    public Item dequeue()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        randomExchHead();
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
//        printQueueInfo("dequeue:" + item);
        return item;
    }

    private void randomExchHead()
    {
        int dequeueIndex;
        if (head >= tail)
        {
            dequeueIndex = StdRandom.uniform(head, tail + queue.length) % queue.length;
        }
        else
        {
            dequeueIndex = StdRandom.uniform(head, tail);
        }
        exch(head, dequeueIndex);
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
        final RandomizedQueue<String> randomQueue = new RandomizedQueue<>();
        randomQueue.enqueue("a");
        randomQueue.sample();
        randomQueue.enqueue("b");
        randomQueue.dequeue();
        randomQueue.enqueue("c");
        randomQueue.sample();
        randomQueue.enqueue("d");
        randomQueue.sample();
        randomQueue.enqueue("e");
        randomQueue.sample();
        randomQueue.enqueue("f");
        randomQueue.enqueue("g");

        printAllItem(randomQueue);
        printAllItem(randomQueue);
        randomQueue.dequeue();
        printAllItem(randomQueue);

        randomQueue.dequeue();
        randomQueue.dequeue();
        randomQueue.dequeue();
        randomQueue.dequeue();
        randomQueue.dequeue();
    }

    private static void printAllItem(RandomizedQueue<String> randomQueue)
    {
        for (String s : randomQueue)
        {
            System.out.print(s + " ");
        }
        System.out.println();
    }

}
