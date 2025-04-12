import java.util.LinkedList;
import java.util.Random;

public class ElectionSystem {



    protected LinkedList<String> candidates;
    protected int p;


    public void executeElection(){
        Random rnd = new Random();
        candidates = initRandomElection(); //O(250)
        p = 3 * (rnd.nextInt(candidates.size())+1);

        Election e1 = new Election(); //O(1)
        initRandomElection(); //O(
        e1.initializeCandidates(candidates); //O(n^2)
        System.out.println(p + " electoral votes");
        for(int i = 0; i < (p); i++){ //O(p)
            e1.castRandomVote();
        }
        e1.auditElection(); //O(n^2)
        //System.out.println(e1.getVoteTotals());
        System.out.println();
        System.out.println("Top three candidates");
        System.out.println(e1.getTopKCandidates(3)); //O(n^2)

        String candidate = candidates.get(rnd.nextInt(candidates.size()));
        e1.rigElection(candidate); //O(n^2)
        System.out.println();


        System.out.println("Top three candidates after rigging election for " + candidate);
        System.out.println(e1.getTopKCandidates(3));//O(n^2)
        System.out.println();
        e1.auditElection();//O(n^2)
        System.out.println();
    }

    public static LinkedList<String> initRandomElection(){
        String[] names = {
                "James Smith", "Mary Johnson", "John Brown", "Patricia Taylor", "Robert Anderson",
                "Jennifer Thomas", "Michael Jackson", "Elizabeth White", "William Harris", "Linda Martin",
                "David Thompson", "Barbara Garcia", "Richard Martinez", "Susan Robinson", "Joseph Clark",
                "Jessica Lewis", "Charles Young", "Sarah Allen", "Thomas Scott", "Karen King",
                "Christopher Wright", "Nancy Hill", "Daniel Adams", "Betty Nelson", "Matthew Carter",
                "Helen Mitchell", "Anthony Perez", "Sandra Roberts", "Mark Turner", "Dorothy Phillips",
                "Paul Campbell", "Ruth Parker", "Steven Evans", "Cynthia Edwards", "Andrew Collins",
                "Margaret Stewart", "Joshua Sanchez", "Ashley Morris", "Ethan Rogers", "Megan Reed",
                "Brian Cook", "Emily Bailey", "Kevin Bell", "Laura Gonzalez", "Jason Murphy",
                "Deborah Rivera", "Gary Cooper", "Maria Richardson", "Timothy Cox", "Rachel Howard",
                "Adam Ward", "Debra Torres", "Christopher Peterson", "Kimberly Gray", "Joshua James",
                "Linda Green", "Ryan Adams", "Sharon Nelson", "Joseph Walker", "Theresa Allen",
                "Dennis Young", "Betty King", "Kenneth Scott", "Amy Mitchell", "Frank Hernandez",
                "Jackie Lewis", "Brian Lee", "Laura Young", "Sandra Hall", "Mark Harris",
                "Steven King", "Rebecca Davis", "Paul Clark", "Donna Lewis", "Brian Walker",
                "Linda Young", "Jason Campbell", "Debbie Robinson", "Mark Allen", "Susan Nelson",
                "Thomas Wright", "Cynthia Peterson", "Anthony Davis", "Sarah Martin", "George Carter",
                "Jessica Harris", "David Lee", "Rebecca Allen", "Michael Turner", "Angela Scott",
                "Jason Martin", "Stephanie Perez", "Gary Evans", "Nancy Clark", "Robert Johnson",
                "Ruth Murphy", "Charles Davis", "James Garcia", "Debbie Young", "Andrew Mitchell",
                "Kathleen White", "Joshua Turner", "Emma Walker", "William Green", "Joseph Young",
                "Christopher White", "Debra Mitchell", "Michael Scott", "Susan Roberts", "Jessica Harris",
                "Brian Harris", "Timothy Evans", "Rachel White", "Jack Brown", "Paul Harris",
                "Sharon Martinez", "Ryan Thompson", "Angela Miller", "George Roberts", "Stephanie Walker",
                "Elizabeth Lee", "David Moore", "Mary Walker", "Daniel Lewis", "Megan Walker",
                "James Miller", "Kathleen Hall", "Jacob Harris", "Jessica Lewis", "Mary Scott",
                "Paul Mitchell", "Olivia Lewis", "Daniel Adams", "Emily Turner", "David Nelson",
                "Nancy Martin", "Charles Robinson", "Jessica White", "Robert King", "Debbie Brown",
                "Kevin Turner", "Sarah White", "Jacob Miller", "Cynthia Davis", "Joshua White",
                "Stephanie Thomas", "James Clark", "Daniel Hall", "Angela Scott", "John Lee",
                "Michael Turner", "William White", "Linda Perez", "Debra Thomas", "William Brown",
                "Brian Scott", "Theresa Lee", "Joseph Young", "Linda Thomas", "Michael Harris",
                "Debbie Clark", "Angela Turner", "John Robinson", "Christopher Young", "Karen Adams",
                "Richard Evans", "Barbara Moore", "Kenneth White", "Paul Johnson", "Laura Young",
                "Mark Walker", "Jessica Turner", "Timothy Brown", "Jackie Davis", "Andrew White",
                "Paul Scott", "Sarah Hall", "Joshua Green", "Jessica Turner", "David Scott",
                "Karen Clark", "Stephanie Harris", "Michael Walker", "Jessica Green", "Joseph Mitchell",
                "Sarah White", "Michael King", "William Thomas", "Theresa White", "James Martin",
                "Elizabeth Turner", "Mark Hall", "Daniel Green", "George Turner", "Kimberly Lee",
                "Joshua Miller", "Sarah Perez", "Joseph Moore", "Debbie Harris", "Angela White",
                "David Green", "Jessica Walker", "Ryan White", "Charles Brown", "Ethan Thomas",
                "William King", "Nancy Walker", "Debbie White", "Rachel Scott", "Thomas Turner",
                "Joseph King", "Sarah Walker", "Rebecca Green", "Charles Harris", "Brian White",
                "Helen Adams", "David Harris", "George Young", "Joshua Brown", "Stephanie King",
                "Jessica Brown", "Mary White", "Michael Green", "Angela Moore", "David Miller",
                "Karen Harris", "George White", "Elizabeth Miller", "Ethan Walker", "James Green",
                "Ryan Young", "Mark White", "Paul Turner", "Sharon Walker", "Jessica White",
                "Kevin Scott", "Paul White", "George Harris", "Jessica Green", "John Young",
                "Rachel Turner", "William Lee", "Jessica White", "Sarah Brown", "Elizabeth King",
                "Michael Moore", "Jessica Walker", "Thomas White", "George Young", "Jessica Moore"
        };
        LinkedList<String> temp = new LinkedList<>();
        Random rnd = new Random();
        int count = rnd.nextInt(names.length);
        int n = 0;
        for(int i = 0; i < count; i++){ //O(250)
            n = rnd.nextInt(names.length);
            if(!temp.contains(names[n])) //O(250)
                temp.add(names[n]); //O(1)
        }
        return temp;
    }

}
