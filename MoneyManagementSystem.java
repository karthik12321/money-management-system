import java.util.*;

public class MoneyManagementSystem
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        TransactionManager tm = new TransactionManager();
        ReportGenerator rg = new ReportGenerator(tm); // new class for reports

        while(true)
        {
            System.out.println("================================");
            System.out.println("    Money Management System     ");
            System.out.println("================================");
            System.out.println("  1. Add Income");
            System.out.println("  2. Add Expense");
            System.out.println("  3. View balance");
            System.out.println("  4. View Detailed Report");
            System.out.println("  5. Exit\n");
            System.out.print("Your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice)
            {
                case 1:
                {
                    int amt = readValidAmount(sc);
                    String type = chooseIncomeType(sc);
                    tm.addincome(amt, type);
                    System.out.println("Income added successfully!\n");
                    break;
                }

                case 2:
                {
                    int amt = readValidAmount(sc);
                    String type = chooseExpenseType(sc);
                    tm.addexpense(amt, type);
                    System.out.println("Expense added successfully!\n");
                    break;
                }

                case 3:
                {
                    tm.checkbalance();
                    break;
                }

                case 4:
                {
                    rg.generateDetailedReport(); // call detailed report
                    break;
                }

                case 5:
                {
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                }
                default:
                {
                    System.out.println("Invalid Choice\n");
                }
            }
        }
    }
    static int readValidAmount(Scanner sc)
{
    while (true)
    {
        System.out.print("Enter amount: ");
        String input = sc.nextLine();

        if (input.length() == 0)
        {
            System.out.println("Amount cannot be empty.");
            continue;
        }

        boolean valid = true;

        // check every character is digit
        for (int i = 0; i < input.length(); i++)
        {
            char c = input.charAt(i);

            if (c < '0' || c > '9')
            {
                valid = false;
                break;
            }
        }

        if (!valid)
        {
            System.out.println("Invalid input. Only digits allowed.");
            continue;
        }

        int value = Integer.parseInt(input);

        if (value <= 0)
        {
            System.out.println("Amount must be greater than 0.");
            continue;
        }

        return value;
    }
}
static String chooseIncomeType(Scanner sc)
{
    while (true)
    {
        System.out.println("\nSelect income type:");
        System.out.println("1. Salary");
        System.out.println("2. Business/Freelance");
        System.out.println("3. Investment");
        System.out.println("4. Other");
        System.out.print("Choice: ");

        String input = sc.nextLine();

        switch (input)
        {
            case "1": return "Salary";
            case "2": return "Business/Freelance";
            case "3": return "Investment";
            case "4": return "Other";
            default: System.out.println("Invalid choice. Try again.");
        }
    }
}
static String chooseExpenseType(Scanner sc)
{
    while (true)
    {
        System.out.println("\nSelect expense type:");
        System.out.println("1. Food");
        System.out.println("2. Housing");
        System.out.println("3. Transport");
        System.out.println("4. Personal");
        System.out.println("5. Other");
        System.out.print("Choice: ");

        String input = sc.nextLine();

        switch (input)
        {
            case "1": return "Food";
            case "2": return "Housing";
            case "3": return "Transport";
            case "4": return "Personal";
            case "5": return "Other";
            default: System.out.println("Invalid choice. Try again.");
        }
    }
}

}

class Transaction
{
    String type;
    int amount;
    Transaction(int amt,String type)
    {
        this.amount = amt;
        this.type = type;
    }
}

class TransactionManager
{
    Transaction[] income = new Transaction[2];
    Transaction[] expense = new Transaction[2];
    int incomecount = 0;
    int expensecount = 0;

    void addincome(int amt,String type)
    {
        ensurecapacity();
        income[incomecount++] = new Transaction(amt, type);
    }

    void addexpense(int amt,String type)
    {
        ensurecapacity();
        expense[expensecount++] = new Transaction(amt, type);
    }

    void ensurecapacity()
    {
        if(incomecount == income.length)
        {
            Transaction[] newincarr = new Transaction[income.length * 2];
            for(int i = 0 ; i <income.length ; i++)
            {
                newincarr[i] = income[i];
            }
            income = newincarr;
        }
        if(expensecount == expense.length)
        {
            Transaction[] newexparr = new Transaction[expense.length * 2];
            for(int i = 0 ; i <expense.length ; i++)
            {
                newexparr[i] = expense[i];
            }
            expense = newexparr;
        }
    }

    static int balance = 0;

    int getTotalIncome()
    {
        int totincome = 0;
        for(int i = 0 ; i<incomecount ; i++)
        {
            totincome += income[i].amount;
        }
        return totincome;
    }

    int getTotalExpense()
    {
        int totexpense = 0;
        for(int i = 0 ; i<expensecount ; i++)
        {
            totexpense += expense[i].amount;
        }
        return totexpense;
    }

    Transaction[] getIncomeTransactions()
    {
        return Arrays.copyOf(income, incomecount);
    }

    Transaction[] getExpenseTransactions()
    {
        return Arrays.copyOf(expense, expensecount);
    }

    void checkbalance()
    {
        balance = getTotalIncome() - getTotalExpense();
        System.out.println("Your balance is: " + balance + "\n");
    }
}

// New class for generating detailed reports
class ReportGenerator
{
    TransactionManager tm;

    ReportGenerator(TransactionManager tm)
    {
        this.tm = tm;
    }

    void generateDetailedReport()
{
    int totalIncome = tm.getTotalIncome();
    int totalExpense = tm.getTotalExpense();
    int balance = totalIncome - totalExpense;

    System.out.println();
    System.out.println("===============================================");
    System.out.println("        DETAILED FINANCIAL REPORT");
    System.out.println("===============================================");

    // Income section
    System.out.println("\nINCOME TRANSACTIONS");
    System.out.println("-----------------------------------------------");

    Transaction[] incomes = tm.getIncomeTransactions();
    if (incomes.length == 0) {
        System.out.println("No income recorded.");
    } else {
        for (int i = 0; i < incomes.length; i++) {
            System.out.println(
                (i + 1) + ". Type: " + incomes[i].type +
                " | Amount: " + incomes[i].amount
            );
        }
    }

    // Expense section
    System.out.println("\nEXPENSE TRANSACTIONS");
    System.out.println("-----------------------------------------------");

    Transaction[] expenses = tm.getExpenseTransactions();
    if (expenses.length == 0) {
        System.out.println("No expenses recorded.");
    } else {
        for (int i = 0; i < expenses.length; i++) {
            System.out.println(
                (i + 1) + ". Type: " + expenses[i].type +
                " | Amount: " + expenses[i].amount
            );
        }
    }

    // Summary section
    System.out.println("\nSUMMARY");
    System.out.println("-----------------------------------------------");
    System.out.println("Total Income   : " + totalIncome);
    System.out.println("Total Expense  : " + totalExpense);
    System.out.println("-----------------------------------------------");
    System.out.println("Final Balance  : " + balance);
    System.out.println("===============================================\n");
}
}