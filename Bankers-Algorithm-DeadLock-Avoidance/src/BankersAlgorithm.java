import java.util.Scanner;

public class BankersAlgorithm {

    private int numProcesses;       // no of processes
    private int numResources;       // no of resource types
    private int[][] max;            // max matrix
    private int[][] allocation;     // allocation matrix
    private int[][] need;           // need matrix
    private int[] available;      // available matrix
    // matrix - for each process how many resources

    public BankersAlgorithm(int numProcesses,int numResources){
        this.numProcesses = numProcesses;
        this.numResources = numResources;

        max = new int[numProcesses][numResources];
        allocation = new int[numProcesses][numResources];
        need = new int[numProcesses][numResources];
        available = new int[numResources];
    }


    public void inputMatrices(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the max Marix:");
        for(int i=0;i<numProcesses;i++){
            for(int j=0;j<numResources;j++){
                max[i][j] = sc.nextInt();
            }

        }
        System.out.println("Enter the Allocation matrix:");
        for (int i = 0; i < numProcesses; i++) {
            for (int j = 0; j < numResources; j++) {
                allocation[i][j] = sc.nextInt();
                need[i][j] = max[i][j] - allocation[i][j]; // Calculate Need matrix
            }
        }

        System.out.println("Enter the Available resources:");
        for (int i = 0; i < numResources; i++) {
            available[i] = sc.nextInt();
        }
    }
    public boolean isSafe(){
        boolean[] finish = new boolean[numProcesses];
        int[] work = available.clone();
        int[] safeSequence = new int[numProcesses];
        int count=0;


        while(count < numProcesses){
            boolean found = false;
            for(int i=0;i<numProcesses;i++){
                if(!finish[i]){
                    boolean canAllocate = true;
                    for(int j =0;j<numResources;j++){
                        if(need[i][j]>work[j]){
                            canAllocate = false;
                            break;
                        }
                    }
                    if(canAllocate){
                        for(int j=0;j<numResources;j++){
                            work[j]+=allocation[i][j];
                        }
                        safeSequence[count++] =i;
                        finish[i] = true;
                        found=true;
                    }
                }
            }
            if(!found){
                System.out.println("System is in unsafe state");
                return false;
            }
        }
        System.out.println("The system is in a safe state.");
        System.out.print("Safe Sequence: ");
        for (int i = 0; i < numProcesses; i++) {
            System.out.print("P" + safeSequence[i] + " ");
        }
        System.out.println();
        return true;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int numProcesses = sc.nextInt();

        System.out.print("Enter the number of resources: ");
        int numResources = sc.nextInt();

        BankersAlgorithm bankers = new BankersAlgorithm(numProcesses, numResources);
        bankers.inputMatrices();
        bankers.isSafe();
    }

}
