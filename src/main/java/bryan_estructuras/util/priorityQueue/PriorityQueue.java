package bryan_estructuras.util.priorityQueue;

import bryan_estructuras.util.queue.Queue;

public interface PriorityQueue<E> extends Queue<E>{

    boolean insert(int index, E element);
}
