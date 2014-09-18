/**
 * User: ZY
 * Date: 9/1/001
 * Time: 21:18
 */

package algs.util;

import java.util.Random;

public class RandomUtil
{
    public static int random(int min, int max)
    {
        final Random random = new Random();
        int abs = max - min;
        final int r =  random.nextInt();
        final int result = r % abs + min;
        return result;
    }

    public static void main(String[] args)
    {
        for (int i = 0; i < 30; i++)
        {
            final int random = random(0, 100);
            System.out.println(random);
        }
    }
}
