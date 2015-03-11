package other;

import other.IpSegment;

import java.util.ArrayList;
import java.util.List;

public class TreeNode
{
    public int start;
    public int end;
    public boolean leaf;
    public List<IpSegment> ipSegments = new ArrayList<>();
}
