import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final PersistableStore store;
    private final BillCalculator calculator = new BillCalculator();
    private final InvoiceFormatter formatter = new InvoiceFormatter();
    private int invoiceSeq = 1000;

    public CafeteriaSystem(PersistableStore store) {
        this.store = store;
    }

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        double subtotal = calculator.calculateSubtotal(lines, menu);
        double taxPct = TaxRules.taxPercent(customerType);
        double tax = calculator.calculateTax(subtotal, customerType);
        double discount = calculator.calculateDiscount(customerType, subtotal, lines.size());
        double total = subtotal + tax - discount;

        String printable = formatter.formatInvoice(invId, lines, menu, subtotal, tax, taxPct, discount, total);
        System.out.print(printable);

        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
