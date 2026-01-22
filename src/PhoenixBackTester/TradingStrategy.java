package PhoenixBackTester;

import PhoenixBackTester.data.DataFeed;

import java.lang.RuntimeException;

public interface TradingStrategy {
    void execute() throws  RuntimeException;
    void provideDataFeed(DataFeed data);

}
