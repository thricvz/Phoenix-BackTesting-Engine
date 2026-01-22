package PhoenixBackTester.data;

public class FinancialData {
    public FinancialData(String _openPrice, String _closePrice,
                         String _highPrice, String _lowPrice) {
        this.openPrice = _openPrice;
        this.closePrice = _closePrice;
        this.highPrice = _highPrice;
        this.lowPrice = _lowPrice;
    }
    public String openPrice;
    public String closePrice;
    public String highPrice;
    public String lowPrice;

    @Override
    public String toString() {
        return "Open price was: "  + openPrice  + "\n"
            +  "Close price was: " + closePrice + "\n"
            +  "High price was: "  + highPrice  + "\n"
            +  "Low price was: "   + lowPrice   + "\n";
    }
}
/*public class FinancialData {
    public FinancialData(long _openPrice, long _closePrice,
                         long _highPrice, long _lowPrice) {
        this.openPrice = _openPrice;
        this.closePrice = _closePrice;
        this.highPrice = _highPrice;
        this.lowPrice = _lowPrice;
    }
    public long openPrice;
    public long closePrice;
    public long highPrice;
    public long lowPrice;
}*/
