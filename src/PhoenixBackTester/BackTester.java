package PhoenixBackTester;

import PhoenixBackTester.Indicators.Indicator;
import PhoenixBackTester.Indicators.IndicatorFactory;
import PhoenixBackTester.data.DataFeed;

public class BackTester {
    public static void launch(TradingStrategy strategy , DataFeed dataFeed){

        strategy.provideDataFeed(dataFeed);

        System.out.println("\u001B[42m Launching BackTest... \u001B[0m");
        while (!dataFeed.reachedEnd()) {
            try {
                strategy.execute();
                dataFeed.advance();
            } catch (RuntimeException error) {
               System.err.print("Back Test canceled due to : ");
               System.err.println(error.getMessage());
               break;
            }
        }

        System.out.println("\u001B[42m End Of BackTest \u001B[0m");
    }
}

