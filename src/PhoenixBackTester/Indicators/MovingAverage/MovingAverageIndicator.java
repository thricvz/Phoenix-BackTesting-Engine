package PhoenixBackTester.Indicators.MovingAverage;

import PhoenixBackTester.Indicators.Indicator;
import PhoenixBackTester.data.DataFeed;
import PhoenixBackTester.data.FinancialData;
import PhoenixBackTester.data.Price;

public class MovingAverageIndicator extends Indicator {

    public MovingAverageIndicator(DataFeed dataFeed) {
        this.dataFeed = dataFeed;
        this.iterationsPassed = 0;
        this.accumulatedPrice = null;
        this.dataFeed.currentData().registerDependency(this);
    }

    @Override
    public MovingAverageData getData() {
        if (this.accumulatedPrice != null) {
            Price movingAverage = new Price(this.accumulatedPrice) ;
            movingAverage.divide(this.iterationsPassed);
            return new MovingAverageData(movingAverage);
        }
        return new MovingAverageData(new Price(1L, 9));
    }

    @Override
    public void updateData(FinancialData completeData) {
        if (this.accumulatedPrice == null)
            this.accumulatedPrice = completeData.closePrice;
        else
            this.accumulatedPrice.add(completeData.closePrice);

        this.iterationsPassed ++;
        FinancialData currentData = this.dataFeed.currentData();

        if (currentData != null) currentData.registerDependency(this);
    }

    private final DataFeed dataFeed;
    private Integer iterationsPassed;
    private Price accumulatedPrice;
}
