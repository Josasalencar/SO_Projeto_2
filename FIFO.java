package algoritmos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FIFO extends Thread {
    private int frame_Q1 ;
    private int frame_Q2 ;
    private int miss;
    private int hit;
    Queue<Integer> queue;
    private int pages[];
    private List<Integer> frames;

    public FIFO(int frame_q1, int frame_q2, int[] pages) {
        frame_Q1 = frame_q1;
        frame_Q2 = frame_q2;
        this.pages = pages;
        frames = new ArrayList<>();
        queue = new LinkedList<>();
        miss = 0;
        hit = 0;
    }

    public void ExecFIFO(int maxFrames) {
        for(int i = 0; i < pages.length; i++) {
            if(frames.contains(pages[i])) {
                hit++;
            } else {
                miss++;
                if(frames.size() == maxFrames) {
                    frames.remove(queue.remove());
                }
                frames.add(pages[i]);
                queue.add(pages[i]);
            }
        }

        System.out.println("Hits: " + hit);
        System.out.println("Miss: " + miss);
        while(!queue.isEmpty()) {
            System.out.printf("%d ", queue.remove());
        }
        System.out.println();
        for(int i = 0; i < frames.size(); i++) {
            System.out.printf("%d ", frames.get(i));
        }
    }
}
