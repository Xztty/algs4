package test;

public class Segment
{
    public int s;
    public int e;

    public String desc;

    public Segment()
    {
    }

    public Segment(int s, int e)
    {
        this.s = s;
        this.e = e;
    }

    @Override
    public String toString()
    {
        return "{" +
            "s=" + s +
            ", e=" + e +
            '}';
    }
}
