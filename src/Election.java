import java.util.*;

public class Election {
    protected int voteCount;
    protected LinkedList<String> candidates;
    protected HashMap<String, Integer> voteTotals = new HashMap<>();
    protected HashMap<Integer, LinkedList<String>> voteTotals2 = new HashMap<>();

    protected PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    /*
    n = candidates.size();
     */
    public void initializeCandidates(LinkedList<String> candidates){
        this.candidates = candidates;
        for(int i = 0; i < candidates.size(); i++){ //O(n)
            voteTotals.put(candidates.get(i), 0);
            maxHeap.add(0);
        }
    }
    public void castVote(String candidate){
        if(voteTotals.containsKey(candidate)) {
            maxHeap.remove(voteTotals.get(candidate)); //O(n)
            voteTotals.replace(candidate, voteTotals.get(candidate) + 1); //O(1)
            maxHeap.add(voteTotals.get(candidate)); //O(logn)
            updateVoteTotals2(); //O(n)
            voteCount++; //O(1)
        }
    }

    public void removeVote(String candidate){
        if(voteTotals.containsKey(candidate) && voteTotals.get(candidate) > 0) {
            maxHeap.remove(voteTotals.get(candidate)); //O(n)
            voteTotals.replace(candidate, voteTotals.get(candidate) - 1); //O(1)
            maxHeap.add(voteTotals.get(candidate)); //O(logn)
            updateVoteTotals2(); //O(n^2)
        }
    }

    public void castRandomVote(){
        Random rnd = new Random();
        int candidateNum = rnd.nextInt(candidates.size());  //O(1)
        String candidate = candidates.get(candidateNum); //O(n)
        castVote(candidate); //O(n^2)
        updateVoteTotals2();
    }

    public void rigElection(String candidate){
        int maxVotes = (voteCount/2) + 1; //O(1)
        int difference = maxVotes;
        if(voteTotals.containsKey(candidate))
            difference = maxVotes - voteTotals.get(candidate); //O(1)

        if(voteTotals.containsKey(candidate)) {
            maxHeap.remove(voteTotals.get(candidate)); //O(n)
            voteTotals.replace(candidate, maxVotes); //O(1)
            maxHeap.add(voteTotals.get(candidate)); //O(logn)
        }


        for(int i = 0; difference != 0; i++){ //O(difference)
            if(!candidates.get(i).equals(candidate)){
                while(difference != 0 && voteTotals.get(candidates.get(i)) != 0){ //O(maxVotes - voteTotals.get(candidate))
                    removeVote(candidates.get(i)); //O(n)
                    difference--; //O(1)
                }
            }
        }

    }

    public void updateVoteTotals2(){
        voteTotals2.clear(); //O(n)
        for(int i = 0; i < candidates.size(); i++){ //O(n)
            voteTotals2.putIfAbsent(voteTotals.get(candidates.get(i)), new LinkedList<>()); //O(1)
            voteTotals2.get(voteTotals.get(candidates.get(i))).add(candidates.get(i)); //O(n)
        }
    }


    public LinkedList<String> getTopKCandidates(int k){
        updateVoteTotals2(); //O(n^2)
        LinkedList<String> kCandidates = new LinkedList<>();
        int maxVotes = 0;
        if(!maxHeap.isEmpty())
            maxVotes = maxHeap.peek();
        int count = 0;
        int j;
        LinkedList<String> temp;
        for(int i = maxVotes; i >= 0 && kCandidates.size() != k; i--){ //O(n)
            if(voteTotals2.containsKey(i)){
                temp = voteTotals2.get(i); //O(1)
                for(j = 0; j < temp.size() && kCandidates.size() != k; j++){
                    kCandidates.add(temp.get(j));
                }
            }
        }
        return kCandidates;
    }

    public void auditElection(){
        LinkedList<String> tempCandidates = getTopKCandidates(candidates.size()); //O(n^2,)
        for(int i = 0; i < tempCandidates.size(); i++){ //O(n)
            System.out.println(tempCandidates.get(i) + " - " + voteTotals.get(tempCandidates.get(i)));
        }
    }

}
