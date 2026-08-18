import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Warehouse warehouse = new Warehouse();

        System.out.print("จำนวน Stack: ");
        int stackCount = sc.nextInt();

        System.out.print("ความจุของแต่ละ Stack: ");
        int capacity = sc.nextInt();
        sc.nextLine();

        if (stackCount <= 0 || capacity <= 0) {
            System.out.println("จำนวน Stack และ Capacity ต้องมากกว่า 0");
            return;
        }

        for (int i = 0; i < stackCount; i++) {
            warehouse.addStack(new WarehouseStack(capacity));
        }

        for (int i = 0; i < stackCount; i++) {
            WarehouseStack stack = warehouse.getStacks().get(i);

            System.out.println("\nStack " + (i + 1));
            System.out.print("จำนวนกล่อง: ");
            int boxCount = sc.nextInt();
            sc.nextLine();

            if (boxCount < 0 || boxCount > capacity) {
                System.out.println("จำนวนกล่องไม่ถูกต้อง");
                return;
            }

            for (int j = 0; j < boxCount; j++) {
                System.out.println("กล่องที่ " + (j + 1));

                System.out.print("Box ID: ");
                String id = sc.nextLine();

                System.out.print("Category: ");
                String category = sc.nextLine();

                System.out.print("Arrival Time: ");
                long arrivalTime = sc.nextLong();

                System.out.print("Priority: ");
                int priority = sc.nextInt();
                sc.nextLine();

                Box box = new Box(
                        id, category, arrivalTime, priority);

                stack.push(box);
            }
        }

        System.out.println("\nเลือก Algorithm");
        System.out.println("1. Algorithm A - First Available");
        System.out.println("2. Algorithm B - Best Fit");
        System.out.print("เลือก: ");

        int choice = sc.nextInt();
        sc.nextLine();

        System.out.print("Box ID ที่ต้องการนำออก: ");
        String target = sc.nextLine();

        try {
            long startTime = System.nanoTime();

            boolean result;

            if (choice == 1) {
                RetrievalAlgorithmA algorithm = new RetrievalAlgorithmA();

                result = algorithm.retrieve(warehouse, target);

            } else if (choice == 2) {
                RetrievalAlgorithmB algorithm = new RetrievalAlgorithmB();

                result = algorithm.retrieve(warehouse, target);

            } else {
                System.out.println("เลือก Algorithm ไม่ถูกต้อง");
                return;
            }

            long endTime = System.nanoTime();

            if (result) {
                System.out.println(
                        "นำกล่อง " + target + " ออกสำเร็จ");
            } else {
                System.out.println("ไม่สามารถนำกล่องออกได้");
            }

            System.out.println(
                    "Execution Time = "
                            + (endTime - startTime) + " ns");

        } catch (BoxNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (StackFullException e) {
            System.out.println(e.getMessage());

        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());

        } catch (Exception e) {
            System.out.println(
                    "เกิดข้อผิดพลาด: " + e.getMessage());
        }

        sc.close();
    }
}