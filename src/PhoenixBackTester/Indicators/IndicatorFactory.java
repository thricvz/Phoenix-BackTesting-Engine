package PhoenixBackTester.Indicators;

import PhoenixBackTester.data.DataFeed;
import PhoenixBackTester.Indicators.MovingAverage.*;

public class IndicatorFactory {
    static IndicatorFactory getInstance(DataFeed dataFeed) {
        if (m_instance == null) {
            m_instance = new IndicatorFactory(dataFeed);
        }
        return m_instance;
    }


    public Indicator produceIndicator(String indicatorName) {
        switch (indicatorName) {
            case "MA" : return new MovingAverageIndicator(this.dataFeed);
        }
        return null;
    }

    private IndicatorFactory(DataFeed dataFeed) {
        this.dataFeed = dataFeed;
    };

    private static IndicatorFactory m_instance = null;
    private DataFeed dataFeed = null;
}
