package PhoenixBackTester.data;

public class FinancialData {
    public FinancialData(Price _openPrice, Price _closePrice,
                         Price _highPrice, Price _lowPrice) {
        this.openPrice = _openPrice;
        this.closePrice = _closePrice;
        this.highPrice = _highPrice;
        this.lowPrice = _lowPrice;
    }
    public Price openPrice;
    public Price closePrice;
    public Price highPrice;
    public Price lowPrice;

    @Override
    public String toString() {
        return "Open price was: "  + openPrice.toString()  + "\n"
            +  "Close price was: " + closePrice.toString() + "\n"
            +  "High price was: "  + highPrice.toString()  + "\n"
            +  "Low price was: "   + lowPrice.toString()   + "\n";
    }
}
