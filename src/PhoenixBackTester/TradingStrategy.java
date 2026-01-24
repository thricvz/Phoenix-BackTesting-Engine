package PhoenixBackTester;

import PhoenixBackTester.data.DataFeed;

import java.lang.RuntimeException;

public abstract class TradingStrategy {
    public abstract void execute() throws  RuntimeException;
    public void provideDataFeed(DataFeed dataFeed) {
       this.dataFeed = dataFeed;
    };

    protected DataFeed dataFeed;
}
