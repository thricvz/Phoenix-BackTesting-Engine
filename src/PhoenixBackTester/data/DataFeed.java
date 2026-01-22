package PhoenixBackTester.data;

public interface DataFeed {
     FinancialData currentData();
     boolean reachedEnd();
     void advance();
}
