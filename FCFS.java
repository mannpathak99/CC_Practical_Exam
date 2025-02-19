import java.util.Arrays;
import java.util.Scanner;

class Process {
    String name;
    int BT;  // Burst Time
    int AT;  // Arrival Time
    int CT;  // Completion Time
    int TAT; // Turnaround Time
    int WT;  // Waiting Time

    public Process(String name, int BT, int AT) {
        this.name = name;
        this.BT = BT;
        this.AT = AT;
    }

    public void display() {
        System.out.println(name + "\t" + BT + "\t" + AT + "\t" + CT + "\t" + TAT + "\t" + WT);
    }
}

public class FCFS {

    private Scanner sc;

    public void execute() {
        sc = new Scanner(System.in);

        // --------FCFS
        System.out.println("Enter Number of Processes:");
        int numProcess = sc.nextInt();
        Process[] process = new Process[numProcess];

        // Accept Input
        for (int i = 0; i < numProcess; i++) {
            System.out.println("P(" + (i + 1) + "): Enter Arrival time & Burst time");
            int at = sc.nextInt();
            int bt = sc.nextInt();

            process[i] = new Process("P" + (i + 1), bt, at);
        }

        // Sorting processes according to Arrival Time (No need if AT=0 or in ascending order)
        Arrays.sort(process, (a, b) -> a.AT - b.AT);

        int sum = 0;
        double avgWT = 0, avgTAT = 0;
        System.out.println("\n\nPRNo\tBT\tAT\tCT\tTAT\tWT");
        System.out.println("====================================");
        for (int i = 0; i < numProcess; i++) {
            sum = process[i].CT = sum + process[i].BT;
            process[i].TAT = process[i].CT - process[i].AT;
            process[i].WT = process[i].TAT - process[i].BT;

            avgWT = avgWT + process[i].WT;
            avgTAT = avgTAT + process[i].TAT;

            process[i].display();
        }
        avgTAT = (double) avgTAT / numProcess;
        avgWT = (double) avgWT / numProcess;
        System.out.println("Average Waiting Time: " + avgWT);
        System.out.println("Average TAT: " + avgTAT);
    }

    public static void main(String[] args) {
        FCFS scheduler = new FCFS();
        scheduler.execute();
    }
}