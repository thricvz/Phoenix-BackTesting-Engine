package PhoenixBackTester;

import PhoenixBackTester.data.DataFeed;

public class PriceAnalyticsFactory {
    static PriceAnalyticsFactory getInstance() {
        if (m_instance == null) {
            m_instance = new PriceAnalyticsFactory();
        }
        return m_instance;
    }

    public void provideDataFeed(DataFeed data) {
       this.dataFeed = data;
    }
    private PriceAnalyticsFactory() {

    };

    private static PriceAnalyticsFactory m_instance = null;
    private DataFeed dataFeed = null;
}
