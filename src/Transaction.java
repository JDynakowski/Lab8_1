import jdk.jfr.Description;
import java.time.LocalDate;

public abstract class Transaction
{
    protected double amount;
    protected String category;
    protected LocalDate date;
    protected String description;

    public Transaction(double amount, String category, LocalDate date, String description)
    {
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description;
    }

    public double getAmount()
    {
        return amount;
    }
    public String getCategory()
    {
        return category;
    }
    public LocalDate getDate()
    {
        return date;
    }
    public String getDescription()
    {
        return description;
    }

}
