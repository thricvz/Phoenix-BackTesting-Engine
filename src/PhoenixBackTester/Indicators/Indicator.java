package PhoenixBackTester.Indicators;

import PhoenixBackTester.data.DependentOnCompleteData;
import PhoenixBackTester.data.FinancialData;

public abstract class Indicator implements DependentOnCompleteData {
    public abstract boolean isDataRelevant();
    public  abstract IndicatorData  getData();

    protected abstract void updateData(FinancialData completeData);

    public void notifyOfCompleteData(FinancialData completeData) {
        this.updateData(completeData);
    }

}
