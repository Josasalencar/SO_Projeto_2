package algoritmos;

import java.util.ArrayList;

public class Otimo extends Thread {
    private int frame_Q1;
    private int frame_Q2;
    private int miss;
    private int hit;
    private ArrayList<Integer> queue;
    private ArrayList<Integer> pages;
    private ArrayList<Integer> frames;
    private ArrayList<Integer> index;


    public Otimo(ArrayList<Integer> pages) {
        this.pages = pages;
        frames = new ArrayList<>();
        queue = new ArrayList<>();
        index = new ArrayList<>();
        miss = 0;
        hit = 0;
    }

    private Integer pop() {
        return queue.remove(0);
    }

    public void ExecOtimo(int maxFrames){
        for(int i = 0; i < pages.size(); i++) {
            if(frames.contains(pages.get(i))) {
                hit++;
            } else {
                miss++;
                if (frames.size() == maxFrames) {
                    int k = 0;
                    for (int j = 0; j < queue.size(); j++) {
                        while (!(queue.get(j) == pages.get(i + k))) {
                            index.set(j, i + k);
                            k++;
                        }
                    }
                    int remo = 0;
                    for (int j = 0; j < index.size(); j++) {
                        if (index.get(j) > index.get(i + 1)) {
                            remo = index.get(j);
                        }
                    }
                    int indx = queue.indexOf(remo);
                    queue.remove(indx);
                    indx = frames.indexOf(remo);
                    frames.remove(indx);
                }
                frames.add(pages.get(i));
                queue.add(pages.get(i));
            }
        }

    }
}