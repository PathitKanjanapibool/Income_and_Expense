import java.util.List;

public class calculate {

    public double totalIncome(List<Transaction> transactions) {
        double total = 0;
        for (Transaction t : transactions) {
            // ใช้ == เปรียบเทียบกับ Enum ได้เลย
            if (t.getType() == TransactionType.INCOME) {
                total += t.getAmount();
            }
        }
        return total;
    }

    public double totalOutcome(List<Transaction> transactions) {
        double total = 0;
        for (Transaction t : transactions) {
            if (t.getType() == TransactionType.OUTCOME) {
                total += t.getAmount();
            }
        }
        return total;
    }

    public double totalMoney(List<Transaction> transactions) {
        return totalIncome(transactions) - totalOutcome(transactions);
    }
}