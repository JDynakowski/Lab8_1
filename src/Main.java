import java.util.Scanner;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String FILENAME = "C://Users//jakub//OneDrive//Bureaublad//Informatyka//Studia//Rok 2//semestr I//Java//Zadania - Java//Zadanie 8//Transactions.txt";

    public static void main(String[] args) {
        BudgetManager budgetManager = new BudgetManager();
        Scanner scanner = new Scanner(System.in);

        // Wczytanie transakcji z pliku
        List<Transaction> loadedTransactions = FileHandler.loadTransactions(FILENAME);
        for (Transaction t : loadedTransactions) {
            budgetManager.addTransaction(t);
        }

        while (true) {
            System.out.println("\nMenu zarządzania budżetem:");
            System.out.println("1. Dodaj transakcję");
            System.out.println("2. Wyświetl wszystkie transakcje");
            System.out.println("3. Pokaż saldo");
            System.out.println("4. Wyjście");
            System.out.print("Wybierz opcję: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addTransaction(budgetManager, scanner);
                    break;
                case 2:
                    budgetManager.displayTransactions();
                    break;
                case 3:
                    System.out.println("Aktualne saldo: " + budgetManager.calculateBalance());
                    break;
                case 4:
                    System.out.println("Wyjście z programu.");
                    return;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
                    break;
            }
        }
    }

    private static void addTransaction(BudgetManager budgetManager, Scanner scanner) {
        System.out.print("Wpisz 'W' dla wydatku lub 'P' dla przychodu: ");
        String type = scanner.next();
        System.out.print("Wpisz kwotę: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Czyści bufor po wprowadzeniu liczby
        System.out.print("Wpisz kategorię: ");
        String category = scanner.nextLine();

        Transaction transaction;
        if (type.equalsIgnoreCase("W")) {
            transaction = new Expense(amount, category, LocalDate.now(), ""); // Zakładając, że konstruktory przyjmują datę i opis
        } else if (type.equalsIgnoreCase("P")) {
            transaction = new Income(amount, category, LocalDate.now(), ""); // Podobnie jak wyżej
        } else {
            System.out.println("Nieprawidłowy typ transakcji. Spróbuj ponownie.");
            return;
        }

        budgetManager.addTransaction(transaction);
        System.out.println("Transakcja dodana pomyślnie.");

        // Zapisz transakcję do pliku
        List<Transaction> transactionsToSave = Collections.singletonList(transaction);
        FileHandler.saveTransactions(transactionsToSave, FILENAME);
    }
}
