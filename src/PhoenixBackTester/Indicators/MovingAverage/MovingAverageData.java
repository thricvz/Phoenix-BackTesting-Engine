package PhoenixBackTester.Indicators.MovingAverage;

import PhoenixBackTester.Indicators.IndicatorData;
import PhoenixBackTester.data.Price;

public class MovingAverageData extends IndicatorData {
    public MovingAverageData (Price movingAveragePrice) {
        this.measurement = movingAveragePrice;
    }

    public Price measurement;
}
