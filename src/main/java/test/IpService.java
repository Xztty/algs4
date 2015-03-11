package test;

import java.util.ArrayList;
import java.util.List;

public class IpService
{
    public static void main(String[] args)
    {
        int n = 1 << 4;
        TreeNode[] treeNodes = new TreeNode[n / 2 - 1];
        for (int i = 0; i < treeNodes.length; i++)
        {
            treeNodes[i] = new TreeNode();
        }
        build(treeNodes, 0, 1, n);

        printTreeNodes(treeNodes);

        insertSegment(treeNodes, 0, 2, 6);
        insertSegment(treeNodes, 0, 1, 2);
        insertSegment(treeNodes, 0, 2, 4);
        insertSegment(treeNodes, 0, 8, 10);
        insertSegment(treeNodes, 0, 3, 5);
        insertSegment(treeNodes, 0, 13, 15);

        printTreeNodes(treeNodes);

        query(treeNodes, 0, 3);

    }

    private static List<Segment> query(TreeNode[] treeNodes, int index, int point)
    {
        List<Segment> results = new ArrayList<>();
        final TreeNode treeNode = treeNodes[index];
        int mid = (treeNode.start + treeNode.end) >> 1;
        if (mid >= point)
        {

        }
        return null;
    }

    public static boolean involve(Segment segment, int point)
    {
        return segment.s <= point && segment.e >= point;
    }

    public static boolean involve(TreeNode treeNode, int point)
    {
        return treeNode.start <= point && treeNode.end >= point;
    }

    private static void insertSegment(TreeNode[] treeNodes, int index, int left, int right)
    {
        final TreeNode treeNode = treeNodes[index];
        if (treeNode.leaf)
        {
            treeNode.segments.add(new Segment(left, right));
        }
        else
        {
            int mid = (treeNode.start + treeNode.end) >> 1;
            if (right <= mid)  //左子树
            {
                insertSegment(treeNodes, ((index << 1) + 1), left, right);
            }
            else if (left >= mid + 1) //右子树
            {
                insertSegment(treeNodes, ((index << 1) + 2), left, right);
            }
            else
            {
                treeNode.segments.add(new Segment(left, right));
            }
        }
    }

    private static void printTreeNodes(TreeNode[] treeNodes)
    {
        for (int i = 0; i < treeNodes.length; i++)
        {
            System.out.print(i + 1);
            System.out.println(" ==> " + treeNodes[i].start + "->" + treeNodes[i].end + " leaf:" + treeNodes[i].leaf);
            final List<Segment> segments = treeNodes[i].segments;
            for (int j = 0; j < segments.size(); j++)
            {
                final Segment segment = segments.get(j);
                System.out.println("    " + segment);
            }
        }
    }

    public static void build(TreeNode[] treeNodes, int index, int left, int right)
    {
        treeNodes[index].start = left;
        treeNodes[index].end = right;
        int leafSeg = 1 << 2;
        if (left + leafSeg > right)
        {
            treeNodes[index].leaf = true;
            return;
        }
        else
        {
            int mid = (left + right) >> 1;
            build(treeNodes, (index << 1) + 1, left, mid);
            build(treeNodes, (index << 1) + 2, mid + 1, right);
        }
    }
}
