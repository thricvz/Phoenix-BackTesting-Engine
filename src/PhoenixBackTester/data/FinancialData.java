package PhoenixBackTester.data;

import java.util.ArrayList;

enum FinancialDataState {
    COMPLETE,
    INCOMPLETE
}

class HiddenPrices {
    public HiddenPrices(Price closePrice, Price highPrice, Price lowPrice) {
       this.closePrice = closePrice;
       this.highPrice = highPrice;
       this.lowPrice = lowPrice;
    }

    public Price closePrice;
    public Price highPrice;
    public Price lowPrice;
}

public class FinancialData {
    public FinancialData(Price _openPrice, Price _closePrice,
                         Price _highPrice, Price _lowPrice) {
        this.state = FinancialDataState.INCOMPLETE;
        this.openPrice = _openPrice;
        this.unrevealedPrices = new HiddenPrices(_closePrice, _highPrice, _lowPrice);
        this.dependentComponents = new ArrayList<DependentOnCompleteData>();
    }

    public void registerDependency(DependentOnCompleteData component) {
        this.dependentComponents.add(component);
    }

    public boolean isFullyAvailable() {
       return this.state ==  FinancialDataState.COMPLETE;
    }

    public void makeFullyAvailable() {
        // can only be made available once
        if (!isFullyAvailable()) {
            this.state = FinancialDataState.COMPLETE;
            this.closePrice = this.unrevealedPrices.closePrice;
            this.lowPrice = this.unrevealedPrices.lowPrice;
            this.highPrice = this.unrevealedPrices.highPrice;

            for (DependentOnCompleteData component: this.dependentComponents) {
                component.notifyOfCompleteData(this);
            }
        }
    }

    @Override
    public String toString() {
        if (isFullyAvailable())
            return     "Open price was: "  + openPrice.toString()  + "\n"
                    +  "Close price was: " + closePrice.toString() + "\n"
                    +  "Low price was: "   + lowPrice.toString()   + "\n"
                    +  "High price was: "  + highPrice.toString()  + "\n";

       else return "Open price was: "  + openPrice.toString()  + "\n";
    }

    public Price openPrice;
    public Price closePrice = null;
    public Price lowPrice = null;
    public Price highPrice = null;

    private FinancialDataState state;
    private HiddenPrices unrevealedPrices;
    private ArrayList<DependentOnCompleteData> dependentComponents;
}

