package PhoenixBackTester.Indicators;

import PhoenixBackTester.data.DependentOnCompleteData;
import PhoenixBackTester.data.FinancialData;

public abstract class Indicator implements DependentOnCompleteData {
    public  abstract IndicatorData  getData() ;

    public void notifyOfCompleteData(FinancialData completeData) {
        this.updateData(completeData);
    }
    protected abstract void updateData(FinancialData completeData);

}
