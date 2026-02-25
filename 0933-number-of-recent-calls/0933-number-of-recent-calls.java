import java.util.*;

class RecentCounter {
    private Queue<Integer> queue;

    public RecentCounter() {
        queue = new LinkedList<>();
    }
    
    public int ping(int t) {
        queue.offer(t);
        
        // Remove requests outside the 3000ms window
        while (queue.peek() < t - 3000) {
            queue.poll();
        }
        
        return queue.size();
    }
}