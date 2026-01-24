import PhoenixBackTester.BackTester;
import PhoenixBackTester.TradingStrategy;
import PhoenixBackTester.data.CSVDataFeed.CSVDataFeed;
import PhoenixBackTester.data.CSVDataFeed.CSVFormat;
import PhoenixBackTester.data.FinancialData;
import PhoenixBackTester.Indicators.MovingAverage.*;
import PhoenixBackTester.Indicators.Indicator;

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

        BackTester.launch(
               new TradingStrategy() {
                   @Override
                   public void execute() {
                      FinancialData openData = this.dataFeed.currentData();
                      System.out.println(openData.toString());
                   }
               },
               dataFeed
        );
    }
}