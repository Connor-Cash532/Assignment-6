import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        ElectionSystem es1 = new ElectionSystem();//O(n+difference)
        for(int i = 0; i < 10; i++){
            es1.executeElection();
        }
    }
}