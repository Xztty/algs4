/**
 * User: ZY
 * Date: 3/11/011
 * Time: 21:12
 */

package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IpAddressService {

    private List<IpSegment> sortedIpSegments = new ArrayList<>();

    public void insertIpAddress(String startIp, String endIp, String address) {
        final long startIpNum = convertIpToNumber(startIp);
        final long endIpNum = convertIpToNumber(endIp);
        sortedIpSegments.add(new IpSegment(startIpNum, endIpNum, address));
    }

    public String queryAddressOfIp(String ip) {
        final long ipNum = convertIpToNumber(ip);
        String address = null;
        int low = 0, high = sortedIpSegments.size();
        while (low <= high) {
            int mid = (low + high) / 2;
            final IpSegment ipSegment = sortedIpSegments.get(mid);
            if (ipSegment.s > ipNum) {
                high = mid - 1;
            } else {
                if (match(ipSegment, ipNum)) {
                    address = ipSegment.address;
                    break;
                } else {
                    low = mid + 1;
                }
            }
        }
        return address;
    }

    private boolean match(IpSegment ipSegment, long ipNum) {
        return ipSegment.s <= ipNum && ipSegment.e >= ipNum;
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

    private static final class IpSegment implements Comparable<IpSegment> {
        private long s;
        private long e;
        public String address;

        public IpSegment(long s, long e, String address) {
            this.s = s;
            this.e = e;
            this.address = address;
        }

        @Override
        public int compareTo(IpSegment o) {
            return this.s < o.s ? -1 : 1;
        }
    }

    private static final class TreeNode {
        private long start;
        private long end;
        private boolean leaf;
        private List<IpSegment> ipSegments = new ArrayList<>();
    }

}
