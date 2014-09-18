package algs.assignment;

import algs.std.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>
{
    private int count;
    private Node<Item> head;
    private Node<Item> tail;

    @Override
    public Iterator<Item> iterator() // return an iterator over items in order from front to end
    {
        return new DequeItr();
    }

    public Deque()                           // construct an empty deque
    {

    }

    public boolean isEmpty()                 // is the deque empty?
    {
        return size() == 0;
    }

    public int size()                        // return the number of items on the deque
    {
        return count;
    }

    public void addFirst(Item item)          // insert the item at the front
    {
        checkNullItem(item);
        final Node<Item> f = head;
        final Node<Item> newNode = new Node<>(item, f, null);
        head = newNode;
        if (f == null)
        {
            tail = newNode;
        }
        else
        {
            f.prev = newNode;
        }
        count++;
        printQueueInfo("addFirst:" + item);
    }

    private void checkNullItem(Item item)
    {
        if (item == null)
        {
            throw new NullPointerException();
        }
    }

    public void addLast(Item item)           // insert the item at the end
    {
        checkNullItem(item);
        final Node<Item> t = tail;
        final Node<Item> newNode = new Node<>(item, null, tail);
        tail = newNode;
        if (t == null)
        {
            head = newNode;
        }
        else
        {
            t.next = newNode;
        }
        count++;
        printQueueInfo("addLast:" + item);
    }

    public Item removeFirst()                // delete and return the item at the front
    {
        checkNoSuchElement();
        Item item = head.item;
        Node<Item> newHead = head.next;
        head.item = null;
        head.next = null;
        head = newHead;
        if (newHead == null)
        {
            tail = null;
        }
        else
        {
            newHead.prev = null;
        }
        count--;
        printQueueInfo("removeFirst:" + item);
        return item;
    }

    public Item removeLast()                 // delete and return the item at the end
    {
        checkNoSuchElement();
        Item item = tail.item;
        Node newTail = tail.prev;
        tail.item = null;
        tail.prev = null;
        tail = newTail;
        if (newTail == null)
        {
            head = newTail;
        }
        else
        {
            newTail.next = null;
        }
        count--;
        printQueueInfo("removeLast:" + item);
        return item;
    }

    private void checkNoSuchElement()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
    }

    private void printQueueInfo(String action)
    {
        System.out.println(action);
        final Iterator<Item> iterator = iterator();
        while (iterator.hasNext())
        {
            System.out.print(iterator.next() + " -> ");
        }
        System.out.println("end");
    }

    public static void main(String[] args)   // unit testing
    {
        Deque<String> deque = new Deque<>();
        for (int i = 0; i < 20; i++)
        {
            final int uniform = StdRandom.uniform(4);
            String s = "" + i;
            if(uniform == 0)
            {
                deque.addFirst(s);
            }
            else if(uniform == 1)
            {
                deque.addLast(s);
            }
            else if(!deque.isEmpty())
            {
                if(uniform == 2)
                {
                    deque.removeFirst();
                }
                else
                {
                    deque.removeLast();
                }
            }
        }
    }

    private static class Node<Item>
    {
        Item item;
        Node<Item> next;
        Node<Item> prev;

        private Node(Item item, Node<Item> next, Node<Item> prev)
        {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }


    private class DequeItr implements Iterator<Item>
    {
        private Node<Item> next = head;

        @Override
        public boolean hasNext()
        {
            return next != null;
        }

        @Override
        public Item next()
        {
            Item item = next.item;
            next = next.next;
            return item;
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }
}
