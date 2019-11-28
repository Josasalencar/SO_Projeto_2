package algoritmos;

import java.util.ArrayList;
import java.util.List;

public class NUR {
    private List<Integer> frames;
    private  List<PairNUR<Integer, Boolean>> pages;
    private List<RMF> queue;
    private int hit;
    private int miss;

    public NUR(List<PairNUR<Integer, Boolean>> pages) {
        this.pages = pages;
        frames = new ArrayList<>();
        queue = new ArrayList<>();
        hit = 0;
        miss = 0;
    }

    private int indexOf(int num) {
        int index = -1;
        for(int i = 0; i < queue.size(); i++) {
            if(queue.get(i).getPage() == num) {
                index = i;
            }
        }
        return index;
    }

    private Integer pop() {
        return queue.remove(0).getPage();
    }

    private Integer peek() {
        return queue.get(0).getPage();
    }

    private int findIndexToRemove() {
        int i = 0;

        while(i < queue.size()) {
            if(queue.get(i).isZeroZero()) {
                return i;
            }
            i++;
        }

        i = 0;
        while(i < queue.size()) {
            if(queue.get(i).isZeroUm()) {
                return i;
            }
            i++;
        }

        i = 0;
        while(i < queue.size()) {
            if(queue.get(i).isUmZero()) {
                return i;
            }
            i++;
        }

        i = 0;
        while(i < queue.size()) {
            if(queue.get(i).isUmUM()) {
                return i;
            }
            i++;
        }

        return -1;
    }

    public void execNUR(int maxFrames, int delta) {
        int count = 0;
        for(int i = 0; i < pages.size(); i++) {

            if(count == delta) {
                for(int j = 0; j < queue.size(); j++) {
                    queue.get(j).setBitR(false);
                }
                count = 0;
            }

            if(frames.contains(pages.get(i).getPage())) {
                hit++;
                int index = indexOf(pages.get(i).getPage());
                queue.get(index).setBitR(true);
                queue.get(index).setM(pages.get(i).getWrite());
            } else {
                miss++;
                if(frames.size() == maxFrames) {
                    int index = findIndexToRemove();
                    frames.remove(index);
                    queue.remove(index);
                }
                frames.add(pages.get(i).getPage());
                queue.add(new RMF(true, pages.get(i).getWrite(), pages.get(i).getPage()));
            }
            count++;
        }
        System.out.println("HITS: " + hit);
        System.out.println("MISS: " + miss);
        System.out.printf("FILA: ");
        while(!queue.isEmpty()) {
            System.out.printf("%d ", queue.remove(0).getPage());
        }
        System.out.println();
        System.out.printf("FRAMES: ");
        for(int i = 0; i < frames.size(); i++) {
            System.out.printf("%d ", frames.get(i));
        }

    }

}

class RMF {
    private boolean bitR;
    private boolean M;
    private int page;

    public RMF(boolean bitR, boolean m, int page) {
        this.bitR = bitR;
        M = m;
        this.page = page;
    }

    public boolean isUmUM() {
        return (bitR && M);
    }

    public boolean isUmZero() {
        return (bitR && !M);
    }

    public boolean isZeroUm() {
        return (!bitR && M);
    }

    public boolean isZeroZero() {
        return (!bitR && !M);
    }

    public boolean isBitR() {
        return bitR;
    }

    public void setBitR(boolean bitR) {
        this.bitR = bitR;
    }

    public boolean isM() {
        return M;
    }

    public void setM(boolean m) {
        M = m;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
