package PhoenixBackTester;

import PhoenixBackTester.data.DataFeed;

public class BackTester {
    public static void launch(TradingStrategy strategy , DataFeed dataFeed,
                PriceAnalyticsFactory analyticsFactory) {

        strategy.provideDataFeed(dataFeed);
        analyticsFactory.provideDataFeed(dataFeed);

        System.out.println("Launching BackTest...");
        while (!dataFeed.reachedEnd()) {
            try {
                strategy.execute();
                dataFeed.advance();

            } catch (RuntimeException error) {
               System.err.print("BackTestCanceled due to Error: ");
               System.err.println(error.getMessage());
               break;
            }
        }

        System.out.println("End of BackTest...");
    }
}

