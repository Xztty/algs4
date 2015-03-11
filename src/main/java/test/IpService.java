package test;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于IP区段构建构造线段树
 */
public class IpService {
    private static final long TOTAL_IP_NUM = 1L << 32;
    /**
     * 叶子节点包含的IP段的大小
     */
    private static final int LEAF_SIZE = 1 << 7;
    private static final int NODE_NUM = (int) (TOTAL_IP_NUM / LEAF_SIZE * 2 - 1);

    private TreeNode[] treeNodes;

    public void init() {
        treeNodes = new TreeNode[NODE_NUM];
        for (int i = 0; i < treeNodes.length; i++) {
            treeNodes[i] = new TreeNode();
        }
        buildTree(0, 1, TOTAL_IP_NUM);
    }

    /**
     * 插入Ip区间对应地址
     *
     * @param startIp
     * @param endIp
     * @param address
     */
    public void insertIpAddress(String startIp, String endIp, String address) {
        final long startIpNum = convertIpToNumber(startIp);
        final long endIpNum = convertIpToNumber(endIp);
        insertSegment(0, new IpSegment(startIpNum, endIpNum, address));
    }

    /**
     * 查询指定IP所在地址
     *
     * @param ip
     * @return 返回Ip对应的地址集合
     */
    public List<String> queryIpAddress(String ip) {
        final long ipNum = convertIpToNumber(ip);
        final List<IpSegment> ipSegments = doQuery(0, ipNum);
        List<String> addresses = new ArrayList<>();
        for (IpSegment ipSegment : ipSegments) {
            addresses.add(ipSegment.address);
        }
        return addresses;
    }

    private void buildTree(int index, long left, long right) {
        treeNodes[index].start = left;
        treeNodes[index].end = right;
        if (left + LEAF_SIZE > right) {
            treeNodes[index].leaf = true;
        } else {
            long mid = (left + right) >> 1;
            buildTree((index << 1) + 1, left, mid);
            buildTree((index << 1) + 2, mid + 1, right);
        }
    }

    private List<IpSegment> doQuery(int index, long ipNum) {
        List<IpSegment> results = new ArrayList<>();
        final TreeNode treeNode = treeNodes[index];
        for (IpSegment ipSegment : treeNode.ipSegments) {
            if (match(ipSegment, ipNum)) {
                results.add(ipSegment);
            }
        }
        if (!treeNode.leaf) {
            long mid = (treeNode.start + treeNode.end) >> 1;
            if (mid >= ipNum) {
                results.addAll(doQuery((index << 1) + 1, ipNum));
            } else if (mid < ipNum) {
                results.addAll(doQuery((index << 1) + 2, ipNum));
            }
        }
        return results;
    }

    private long convertIpToNumber(String ip) {
        final String[] values = ip.split("\\.");
        if (values.length != 4) {
            throw new RuntimeException("invalid ip:" + ip);
        }
        long rst = 0;
        for (int i = 0; i < values.length; i++) {
            int num = Integer.parseInt(values[i]);
            if (num <= 255 && num >= 0) {
                rst += num << ((3 - i) * 8);
            } else {
                throw new RuntimeException("invalid ip:" + ip);
            }
        }
        return rst;
    }

    private void insertSegment(int index, IpSegment segment) {
        final TreeNode treeNode = treeNodes[index];
        if (treeNode.leaf) {
            treeNode.ipSegments.add(segment);
        } else {
            long mid = (treeNode.start + treeNode.end) >> 1;
            if (segment.e <= mid) { //左子树
                insertSegment(((index << 1) + 1), segment);
            } else if (segment.s >= mid + 1) { //右子树
                insertSegment(((index << 1) + 2), segment);
            } else {
                treeNode.ipSegments.add(segment);
            }
        }
    }

    private boolean match(IpSegment ipSegment, long ipNum) {
        return ipSegment.s <= ipNum && ipSegment.e >= ipNum;
    }

    private static final class IpSegment {
        private long s;
        private long e;
        public String address;

        public IpSegment(long s, long e, String address) {
            this.s = s;
            this.e = e;
            this.address = address;
        }

        @Override
        public String toString() {
            return "IpSegment{" +
                    "s=" + s +
                    ", e=" + e +
                    ", address='" + address + '\'' +
                    '}';
        }
    }

    private static final class TreeNode {
        private long start;
        private long end;
        private boolean leaf;
        private List<IpSegment> ipSegments = new ArrayList<>();
    }

}
