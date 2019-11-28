package algoritmos;

import java.util.ArrayList;

public class MRU extends Thread {
    private int frame_Q1;
    private int frame_Q2;
    private int miss;
    private int hit;
    private ArrayList<Integer> queue;
    private ArrayList<Integer> pages;
    private ArrayList<Integer> frames;


    public MRU(ArrayList<Integer> pages) {
        this.pages = pages;
        frames = new ArrayList<>();
        queue = new ArrayList<>();
        miss = 0;
        hit = 0;
    }

    private Integer pop() {
        return queue.remove(0);
    }

    public void ExecMRU(int maxFrames) {
        for(int i = 0; i < pages.size(); i++) {
            if(frames.contains(pages.get(i))) {
                hit++;
                int index = queue.indexOf(pages.get(i));
                int num = queue.remove(index);
                queue.add(num);
            } else {
                miss++;
                if(frames.size() == maxFrames) {
                    int index = frames.indexOf(pop());
                    frames.remove(index);
                }
                frames.add(pages.get(i));
                queue.add(pages.get(i));
            }
        }

        System.out.println("Hits: " + hit);
        System.out.println("Miss: " + miss);
        System.out.printf("FILA: ");
        while(!queue.isEmpty()) {
            System.out.printf("%d ", pop());
        }
        System.out.println();
        System.out.printf("FRAMES: ");
        for(int i = 0; i < frames.size(); i++) {
            System.out.printf("%d ", frames.get(i));
        }
    }

}

