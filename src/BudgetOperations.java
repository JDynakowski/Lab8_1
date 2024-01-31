import java.time.LocalDate;
import java.util.List;

public interface BudgetOperations
{
    void addTransaction(Transaction transaction);
    void displayTransactions();
    double calculateBalance();
    List<Transaction> getTransactionsByCategory(String category);
    List<Transaction> getTransactionsByDate(LocalDate date);
    double getTotalExpenses();
    double getTotalIncome();
}