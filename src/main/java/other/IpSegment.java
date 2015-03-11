package other;

public class IpSegment {
    public int s;
    public int e;

    public String desc;

    public IpSegment() {
    }

    public IpSegment(int s, int e) {
        this.s = s;
        this.e = e;
    }

    public IpSegment(String s, String e) {

    }

    @Override
    public String toString() {
        return "{" +
                "s=" + s +
                ", e=" + e +
                '}';
    }
}
