import PhoenixBackTester.data.CSVDataFeed.CSVDataFeed;
import PhoenixBackTester.data.CSVDataFeed.CSVFormat;
import PhoenixBackTester.data.FinancialData;


public class Main {
    public static void main(String[] argv) {
        // BackTester engine = new BackTester();
        CSVFormat format = new CSVFormat(
                "Open",
                "Close",
                "High",
                "Low"
        );

        CSVDataFeed dataFeed = new CSVDataFeed(
                "/home/eric/DataSets/NVidia_stock_history.csv",
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