package algoritmos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SecondChance {
    private List<Integer> frames;
    private Queue<Pair<Integer, Boolean>> queue;
    private  List<Integer> pages;
    private int hit;
    private int miss;

    public SecondChance(List<Integer> pages) {
        frames = new ArrayList<>();
        queue = new LinkedList<>();
        hit = 0;
        miss = 0;
        this.pages = pages;
    }

    public void run(int maxFrames, int delta) {
        int count = 0;
        for(int i = 0; i < pages.size(); i++) {
            // Verifica o contador para zerar os BitR's
            if(count == delta) {
                System.out.println("ZERA TUDO");
                // Zera bitR
                Queue<Pair<Integer, Boolean>> aux = new LinkedList<>();
                int len = queue.size();
                for(int j = 0; j < len; j++) {
                    queue.peek().setBitR(false);
                    aux.add(queue.remove());
                }
                queue = aux;
                count = 0;
            }

            // Verifica se a pagina já está na memória Ram
            if(frames.contains(pages.get(i))) {
                // Incrementa os acertos
                hit++;
                //System.out.println("NEW PAGE: " + String.valueOf(pages.get(i)));
                // Seta o BitR para 1
                Queue<Pair<Integer, Boolean>> aux = new LinkedList<>();
                int len = queue.size();
                for(int j = 0; j < len; j++) {
                    if(queue.peek().getPage().equals(pages.get(i))) {
                        Pair<Integer, Boolean> pair = new Pair<>(pages.get(j), true);
                        aux.add(pair);
                        queue.remove();
                    } else {
                        aux.add(queue.remove());
                    }
                }
                queue = aux;
            // Senão estiver na memoria ram
            } else {
                // Incrementa os erros
                miss++;
                // Verifica se a memoria RAM está lotada
                if(frames.size() == maxFrames) {
                    System.out.println("TAMANHO FRAMES"+ frames.size());
                    System.out.printf("PAGES[i]: %d\n", pages.get(i));
                    // Procura uma pagina com BitR igual a 0
                    while(queue.peek().getBitR()) {
                        System.out.println("WHILE");
                        queue.peek().setBitR(false);
                        queue.add(queue.remove());
                    }
                    Integer num = queue.peek().getPage();
                    System.out.println("NUM: " + num);
                    queue.remove();
                    
                    System.out.println(queue.peek().getPage());
                    frames.remove(num);
                    frames.add(pages.get(i));
                    queue.add(new Pair<Integer, Boolean>(pages.get(i), true));
                    num = queue.peek().getPage();
                    System.out.println(" NUM: " + num);
                } else {
                    // Adiciona na memoria ram
                    System.out.println("NEW PAGE 2: " + String.valueOf(pages.get(i)));
                    frames.add(pages.get(i));
                    // Adiciona na fila com BitR igual a 1
                    queue.add(new Pair<Integer, Boolean>(pages.get(i), true));
                }
            }
            count++;
        }
        System.out.println("HITS: " + hit);
        System.out.println("MISS: " + miss);
        System.out.println(queue.size());
        while(!queue.isEmpty()) {
            System.out.printf("%d ", queue.remove().getPage());
        }
        System.out.println();
        for(int i = 0; i < frames.size(); i++) {
            System.out.printf("%d ", frames.get(i));
        }

    }

}

class Pair<A, B> {
    private A page;
    private B bitR;

    public Pair(A page, B bitR) {
        this.page = page;
        this.bitR = bitR;
    }

    public A getPage() {
        return page;
    }

    public void setPage(A page) {
        this.page = page;
    }

    public B getBitR() {
        return bitR;
    }

    public void setBitR(B bitR) {
        this.bitR = bitR;
    }
}
