import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class BudgetManager implements BudgetOperations
{
    private List<Transaction> transactions;

    public BudgetManager()
    {
        transactions = new ArrayList<>();
    }

    @Override
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public void displayTransactions()
    {
        for (Transaction transaction : transactions)
        {
            System.out.println(transaction.getClass().getSimpleName() + ": " + transaction.getAmount() + " " + transaction.getCategory());
        }
    }
    @Override
    public double calculateBalance()
    {
        double balance = 0.0;
        for (Transaction transaction : transactions)
        {
            if (transaction instanceof Income)
            {
                balance += transaction.getAmount();
            } else if (transaction instanceof Expense)
            {
                balance -= transaction.getAmount();
            }
        }
        return balance;
    }

    @Override
    public List<Transaction> getTransactionsByCategory(String category)
    {
        return transactions.stream()
                .filter(t -> t.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getTransactionsByDate(LocalDate date)
    {
        return transactions.stream()
                .filter(t -> t.getDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public double getTotalExpenses()
    {
        return transactions.stream()
                .filter(t -> t instanceof Expense)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    @Override
    public double getTotalIncome()
    {
        return transactions.stream()
                .filter(t -> t instanceof Income)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
}
