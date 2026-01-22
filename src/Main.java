import PhoenixBackTester.*;
import PhoenixBackTester.data.CSVDataFeed;
import PhoenixBackTester.data.CSVFormat;
import PhoenixBackTester.data.FinancialData;


public class Main {
    public static void main(String[] argv) {
        // BackTester engine = new BackTester();
        CSVFormat format = new CSVFormat(
                "Open",
                "Close",
                "High",
                "Lows"
        );

        CSVDataFeed dataFeed = new CSVDataFeed(
                "/home/eric/DataSets/AAPL.csv",
                format
        );

        while (!dataFeed.reachedEnd()) {
            FinancialData  data = dataFeed.currentData();
            System.out.println(data.toString());
            System.out.println();

            dataFeed.advance();
        }
    }
}