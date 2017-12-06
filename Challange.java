import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.BiConsumer;

public class Challange {
    public static void main(String[] args) {
        Challange challange = new Challange();
        challange.go();
    }

    private void go() {
        String[] ss = {"a", "b"};
        int[] categories = {1, 2};

        //some test data
        Video[] videos = {
                new Video("v1", 11, new int[]{1, 4}),
                new Video("v2", 21, new int[]{1, 2}),
                new Video("v3", 14, new int[]{2}),
                new Video("v4", 3, new int[]{2, 5}),
                new Video("v5", 1, new int[]{2, 3, 4}),
                new Video("v6", 7, new int[]{1, 2, 3}),
                new Video("v7", 8, new int[]{1, 5})};

        //build map
        HashMap<Integer, ArrayList<Video>> hm = new HashMap<Integer, ArrayList<Video>>();
        for (Video video : videos) {
            for (int cat : video.categories) {
                ArrayList<Video> catVideos = hm.get(cat);
                if (catVideos == null) {
                    catVideos = new ArrayList<Video>();
                    hm.put(cat, catVideos);
                }
                catVideos.add(video);
            }
        }


        /*
        hm.forEach(new BiConsumer<Integer, ArrayList<Video>>() {
            @Override
            public void accept(Integer integer, ArrayList<Video> videos) {
                System.out.println("Cat: " + integer);
                for (Video video : videos) {
                    System.out.println("  Video: " + video.name);
                }
            }
        });
        */
        //show contents,
        //using lambda instead of class...
        hm.forEach( (x,y) -> {
            System.out.println("Category " + x);
            for (Video video : y) {
                System.out.println("Video: " + video.name);
            }
        } );

        //find all the shortest
        ArrayList<Video> shortest = new ArrayList<>();
        hm.forEach( (x,videos2) -> {
            shortest.add(getShortest(videos2));
            }
        );
        shortest.forEach(v -> System.out.println("Name: " + v.name + " Lenght: " + v.length));
    }

    Video getShortest(ArrayList<Video> videos) {
        /*
        Video shortest = videos.get(0);
        for (Video video : videos) {
            if (shortest.length > video.length) {
                shortest = video;
            }
        }
        return shortest;
        */
        //use streams instead
        return videos.stream().sorted( (Video a, Video b) -> new Integer(a.length).compareTo(b.length) ).findFirst().orElse(null);
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
    }
}
