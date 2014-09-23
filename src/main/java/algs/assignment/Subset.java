package algs.assignment;

import algs.std.StdRandom;

import java.util.*;

public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        List<String> list = readAllStrings();
        IStatistic directRandom = new DirectRandomizedQueueStatistic();
        IStatistic optimizeRandom = new OptimizeRandomizedQueueStatistic();
        IStatistic directDeque = new DirectDequeStatistic();
        IStatistic optimizeDeque = new OptimizeDequeStatistic();


        print(directRandom.randomize(k, list));
        print(optimizeRandom.randomize(k, list));
        print(directDeque.randomize(k, list));
        print(optimizeDeque.randomize(k, list));

        statisticDirect(k, list, directRandom);
        statisticDirect(k, list, optimizeRandom);
        statisticDirect(k, list, directDeque);
        statisticDirect(k, list, optimizeDeque);
    }

    private static void statisticDirect(int k, List<String> list, IStatistic iStatistic) {
        Map<String, Integer> map = initMapCount(list);
        for (int i = 0; i < 1000000; i++) {
            final Set<String> strings = iStatistic.randomize(k, list);
            for (String string : strings) {
                map.put(string, map.get(string) + 1);
            }
        }
        printStatistic(map);
    }

    private static Map<String, Integer> initMapCount(List<String> list) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : list) {
            map.put(s, 0);
        }
        return map;
    }

    private static void printStatistic(Map<String, Integer> map) {

        List<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        for (String key : list) {
            System.out.println(key + " " + map.get(key));
        }
        System.out.println("--------------------");
    }

    private static List<String> readAllStrings() {
        List<String> list = new ArrayList<>();
//        while (!StdIn.isEmpty()) {
//            list.add(StdIn.readString());
//        }
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            list.add(c + "");
        }
        return list;
    }

    private static void print(Set<String> strings) {
        for (String string : strings) {
            System.out.print(string + " ");
        }
        System.out.println();
    }

    interface IStatistic {
        Set<String> randomize(int k, Collection<String> collection);
    }

    public static class DirectRandomizedQueueStatistic implements IStatistic {
        @Override
        public Set<String> randomize(int k, Collection<String> collection) {
            RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
            for (String item : collection) {
                randomizedQueue.enqueue(item);
            }
            Set<String> result = new HashSet<>();
            while (k-- > 0) {
                result.add(randomizedQueue.dequeue());
            }
            return result;
        }
    }

    public static class OptimizeRandomizedQueueStatistic implements IStatistic {
        @Override
        public Set<String> randomize(int k, Collection<String> collection) {
            RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
            int n = 0;
            for (String item : collection) {
                n++;
                if (n <= k) {
                    randomizedQueue.enqueue(item);
                } else {
                    double p = 1.0 * k / n;
                    if (StdRandom.bernoulli(p)) {
                        randomizedQueue.dequeue();
                        randomizedQueue.enqueue(item);
                    }
                }
            }
            Set<String> result = new HashSet<>();
            while (!randomizedQueue.isEmpty()) {
                result.add(randomizedQueue.dequeue());
            }
            return result;
        }
    }

    public static class DirectDequeStatistic implements IStatistic {
        @Override
        public Set<String> randomize(int k, Collection<String> collection) {
            Deque<String> deque = new Deque<>();
            for (String s : collection) {
                deque.addLast(s);
            }
            int n = deque.size();
            Set<String> result = new HashSet<>();
            while (!deque.isEmpty()) {
                final String s = deque.removeFirst();
                final int pos = StdRandom.uniform(n--) + 1;
                if (pos <= k - result.size()) {
                    result.add(s);
                }
                if (result.size() == k) {
                    break;
                }
            }
            return result;
        }
    }

    public static class OptimizeDequeStatistic implements IStatistic {
        @Override
        public Set<String> randomize(int k, Collection<String> collection) {
            Deque<String> deque = new Deque<>();
            for (String s : collection) {
                deque.addLast(s);
            }
            int n = deque.size();
            Set<String> result = new HashSet<>();
            while (!deque.isEmpty()) {
                final String s = deque.removeFirst();
                final int pos = StdRandom.uniform(n--) + 1;
                if (pos <= k - result.size()) {
                    result.add(s);
                }
                if (result.size() == k) {
                    break;
                }
            }
            return result;
        }
    }
}
