import com.sun.tools.javac.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Challange2 {
    private static final int ALL_CAT_CNT = 5;

    //hitta snabbaste sättet att se alla cat
    public static void main(String[] args) {
        Challange2 challange = new Challange2();

        challange.go();
    }


    private void go() {

        //some test data
        Video[] videos = {
                new Video("v1", 11, new int[]{1, 4}),
                new Video("v2", 21, new int[]{1, 2}),
                new Video("v3", 14, new int[]{2}),
                new Video("v4", 3, new int[]{2, 5}),
                new Video("v5", 1, new int[]{2, 3, 4}),
                new Video("v6", 7, new int[]{1, 2, 3}),
                new Video("v7", 8, new int[]{1, 5})};


        //find videos with shortest time per cat
        Video[] toWatch = buildResult(new ArrayList<Video>(), new ArrayList<Video>(Arrays.asList(videos)), new ArrayList<Integer>());
        for (Video watch : toWatch) {
            System.out.println("Video to watch " +watch.name);
        }
    }


    Video[] buildResult(ArrayList<Video> aggrResult, ArrayList<Video> allVideos, ArrayList<Integer> catBlacklist) {
        System.out.println("aggr " + aggrResult.size() + "  all " + allVideos.size() + "  blacklist " + catBlacklist);
        if (catBlacklist.size() >= ALL_CAT_CNT) {
            return aggrResult.toArray(new Video[0]);
        }
        MyTuple<Video, ArrayList<Integer>> bm = getBestMatch(allVideos, catBlacklist);
        aggrResult.add(bm.a);
        allVideos.remove(bm.a);
        catBlacklist.addAll(bm.b);
        return buildResult(aggrResult, allVideos, catBlacklist);
        //with scala/haskell I could do something like 'buildResult(aggrResult.add(bm.a), allVideos.remove(bm.a), catBlacklist.addAll())'
        //since array is immutable an all operations return a new array...
    }


    private MyTuple<Video, ArrayList<Integer>> getBestMatch(ArrayList<Video> allVideos, ArrayList<Integer> catBlacklist) {
        //FIXME Should have to have all these 3 variables
        int shortestLengthPerCat = Integer.MAX_VALUE;
        Video bestMatch = null;
        ArrayList<Integer> bestMatchBlacklist = new ArrayList<>();
        for (Video video : allVideos) {
            MyTuple<Integer, ArrayList<Integer>> timePerCatAndBlacklist = video.getTimePerCat(catBlacklist);
            if (timePerCatAndBlacklist.a < shortestLengthPerCat) {
                shortestLengthPerCat = timePerCatAndBlacklist.a;
                bestMatch = video;
                bestMatchBlacklist = timePerCatAndBlacklist.b;
            }
        }
        return new MyTuple<>(bestMatch, bestMatchBlacklist);
    }


    public class Video {
        public Video(String name, int length, int[] categories) {
            this.name = name;
            this.length = length;
            this.categories = categories;
        }
        String name = "";
        int length = 0;
        int[] categories = new int[0];

        MyTuple<Integer, ArrayList<Integer>> getTimePerCat(ArrayList<Integer> catBlacklist) {
            int[] catsA = excludeBlacklist(this.categories, catBlacklist);
            ArrayList<Integer> cats = (ArrayList<Integer>)Arrays.stream(catsA).boxed().collect(Collectors.toList());

            if (cats.size() == 0) {
                return new MyTuple<>(Integer.MAX_VALUE, new ArrayList<>());
            }
            return new MyTuple<>(length / cats.size(), cats);
        }

        private int[] excludeBlacklist(int[] categories, ArrayList<Integer> catBlacklist) {
            return Arrays.stream(categories).filter(a-> catBlacklist.stream().noneMatch(i -> i == a) ).toArray();
        }
    }

    /**
     * A tuple using generics
     * @param <A>
     * @param <B>
     */
    public class MyTuple<A, B> {
        public final A a;
        public final B b;
        public MyTuple(A a, B b) {
            this.a = a;
            this.b = b;
        }
    }
}
