import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileHandler
{

    public static void saveTransactions(List<Transaction> transactions, String filename)
    {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename, true)))
        {
            for (Transaction t : transactions)
            {
                out.println(t.getClass().getSimpleName() + "," + t.getAmount() + "," + t.getCategory() + "," + t.getDate() + "," + t.getDescription());
            }
        }
        catch (IOException e)
        {
            System.err.println("Wystąpił błąd podczas zapisu do pliku: " + e.getMessage());
        }
    }

    public static List<Transaction> loadTransactions(String filename) {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 5) { // Upewnij się, że wszystkie wymagane dane są obecne
                    System.err.println("Nieprawidłowy format linii: " + line);
                    continue; // Pomiń tę linię i kontynuuj z następną
                }
                String type = parts[0];
                double amount = Double.parseDouble(parts[1]);
                String category = parts[2];
                LocalDate date = LocalDate.parse(parts[3]);
                String description = parts.length > 4 ? parts[4] : "";

                Transaction transaction = type.equals("Income")
                        ? new Income(amount, category, date, description)
                        : new Expense(amount, category, date, description);
                transactions.add(transaction);
            }
        } catch (IOException e) {
            System.err.println("Wystąpił błąd podczas odczytu z pliku: " + e.getMessage());
        }
        return transactions;
    }

}
