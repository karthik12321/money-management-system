import java.util.*;
public class mm
{
    public static void main(String[] args) {


    Scanner sc = new Scanner(System.in);
    //HOw many things are there
    System.out.println("How many things did you spend money on this month? ");
    int n = sc.nextInt();
    sc.nextLine();
    String[] expenseName = new String[n];
    double[] expenseAmount = new double[n];
    for(int i=0;i<n;i++){
        System.out.println("\nExpense "+(i+1));


        System.out.println("Enter expense name: ");
        expenseName[i] = sc.nextLine();

        System.out.println("Enter expense amount of "+expenseName[i] + ": ");
        expenseAmount[i] = sc.nextInt();
        sc.nextLine();
    }
        double total = 0;
        System.out.println("\n-----Monthly Expenditure Summary-------");
        for (int i = 0; i < n; i++) {
            System.out.println(expenseName[i] + ": "+expenseAmount[i]);
            total += expenseAmount[i];
        }
        System.out.println("----------------------------------");
        System.out.println("Total Monthly Expenditure "+total);

        //monthly income
        System.out.println("Enter your monthly income:");
        int income = sc.nextInt();
        income -= total;
        System.out.println("You have "+income+" to invest or save :");
        System.out.println("What would you like to with this money : 1 FD : 2 Stocks 3 : Health insurance 4 : Nothing ");
       int x = sc.nextInt();
       switch(x){
           case 1:
       }



    }
}
