import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of disk requests: ");
        int n = sc.nextInt();

        int[] requests = new int[n];
        System.out.println("Enter the disk requests (cylinder numbers):");
        for (int i = 0; i < n; i++) {
            requests[i] = sc.nextInt();
        }

        System.out.print("Enter the initial head position: ");
        int initialHead = sc.nextInt();

        System.out.print("Enter the disk size: ");
        int diskSize = sc.nextInt();

        System.out.print("Enter the direction (up/down): ");
        String direction = sc.next();
        ScanDiskScheduling scanDiskScheduling = new ScanDiskScheduling();

        scanDiskScheduling.scan(requests, initialHead, diskSize, direction);
        sc.close();
    }
}