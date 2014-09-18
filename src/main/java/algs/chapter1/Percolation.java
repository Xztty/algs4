/**
 * User: ZY
 * Date: 9/12/012
 * Time: 22:17
 */

package algs.chapter1;

import algs.std.StdRandom;

public class Percolation
{
    private Block[] blocks;
    private int tvBlock;
    private int bvBlock;
    private int N;

    public Percolation(int N)                // create N-by-N grid, with all sites blocked
    {
        this.N = N;
        final int realBlock = N * N;
        blocks = new Block[realBlock + 2];

        for (int j = 0; j < blocks.length; j++)
        {
            blocks[j] = new Block();
            blocks[j].setRootBlock(j);
        }
        tvBlock = realBlock;
        bvBlock = realBlock + 1;
        blocks[tvBlock].setOpen(true);
        blocks[bvBlock].setOpen(true);
    }

    public void open(int i, int j)           // open site (row i, column j) if it is not already
    {
        final int index = getIndex(i, j);
        if (!blocks[index].isOpen())
        {
            blocks[index].setOpen(true);
            unionAround(index);
        }
    }

    public boolean isOpen(int i, int j)      // is site (row i, column j) open?
    {
        final int index = getIndex(i, j);
        return blocks[index].isOpen();
    }

    public boolean isFull(int i, int j)      // is site (row i, column j) full?
    {
        return false;
    }

    public boolean percolates()              // does the system percolate?
    {
        int topRoot = getRootBlock(tvBlock);
        int bottomRoot = getRootBlock(bvBlock);
        return topRoot == bottomRoot;
    }

    public static void main(String[] args)   // test client, optional
    {
        testRandom();
    }

    private static void testRandom()
    {
        for (int i = 0; i < 30; i++)
        {
            final int n = 5 + StdRandom.uniform(10);
            final Percolation percolation = new Percolation(n);
            for (int j = 0; j < n * n; j++)
            {
                percolation.open(StdRandom.uniform(n), StdRandom.uniform(n));
            }
            percolation.printBlocks();
        }
    }

    public void printBlocks()
    {
        showBlock(tvBlock, true);
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                final int index = getIndex(i, j);
                showBlock(index, false);
            }
            System.out.println();
        }
        showBlock(bvBlock, true);
        final boolean percolates = percolates();
        System.out.println(percolates);
    }

    private void showBlock(int index, boolean line)
    {
        String s = "*";
        System.out.print(" ");
        if (blocks[index].isOpen())
        {
//            final int rootBlock = blocks[index].getRootBlock();
            System.out.print("_");
        }
        else
        {
            System.out.print(s);
        }
        System.out.print(" ");
        if (line)
        {
            System.out.println();
        }
    }

    private void unionAround(int index)
    {
        if (topBlock(index))
        {
            union(index, tvBlock);
        }
        if (bottomBlock(index))
        {
            union(index, bvBlock);
        }
        if (hasOpenedUpBlock(index))
        {
            union(index, getUpBlock(index));
        }
        if (hasOpenedDownBlock(index))
        {
            union(index, getDownBlock(index));
        }
        if (hasOpenedLeftBlock(index))
        {
            union(index, getLeftBlock(index));
        }
        if (hasOpenedRightBlock(index))
        {
            union(index, getRightBlock(index));
        }
    }

    private int getRightBlock(int index)
    {
        return index + 1;
    }

    private boolean hasOpenedRightBlock(int index)
    {
        return !rightBlock(index) && blocks[getRightBlock(index)].isOpen();
    }

    private int getLeftBlock(int index)
    {
        return index - 1;
    }

    private boolean hasOpenedLeftBlock(int index)
    {
        return !leftBlock(index) && blocks[getLeftBlock(index)].isOpen();
    }

    private boolean hasOpenedDownBlock(int index)
    {
        return !bottomBlock(index) && blocks[getDownBlock(index)].isOpen();
    }

    private int getDownBlock(int index)
    {
        return index + N;
    }

    private int getUpBlock(int index)
    {
        return index - N;
    }

    private boolean hasOpenedUpBlock(int index)
    {
        return !topBlock(index) && blocks[getUpBlock(index)].isOpen();
    }

    private boolean bottomBlock(int index)
    {
        return index < N * N && index >= N * (N - 1);
    }

    private boolean topBlock(int index)
    {
        return index >= 0 && index < N;
    }

    private boolean leftBlock(int index)
    {
        return index % N == 0;
    }

    public boolean rightBlock(int index)
    {
        return (index + 1) % N == 0;
    }

    public void union(int p, int q)
    {
        int pRoot = getRootBlock(p);
        int qRoot = getRootBlock(q);
        if (pRoot != qRoot)
        {
            if (pRoot < qRoot)
            {
                setBlockNewRoot(pRoot, qRoot);
            }
            else
            {
                setBlockNewRoot(qRoot, pRoot);
            }
        }
    }

    public void setBlockNewRoot(int b, int r)
    {
        blocks[b].setRootBlock(r);
    }

    public int getRootBlock(int q)
    {
        int qRoot = q;
        while (qRoot != (qRoot = blocks[qRoot].getRootBlock()))
        {
            ;
        }
        return qRoot;
    }

    private int getIndex(int i, int j)
    {
        return i * N + j;
    }

    public static class Block
    {
        private int rootBlock;
        private boolean open;

        public boolean isOpen()
        {
            return open;
        }

        public void setOpen(boolean open)
        {
            this.open = open;
        }

        public int getRootBlock()
        {
            return rootBlock;
        }

        public void setRootBlock(int rootBlock)
        {
            this.rootBlock = rootBlock;
        }
    }
}
