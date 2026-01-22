package PhoenixBackTester.data;

import java.util.List;


enum CSVDataTypes{
    Integer ,
    Float   ,
    String  ,
    Boolean ,
}

public class CSVFormat {
    public CSVFormat(String _openPriceColumn, String _closePriceColumn,
                     String _highPriceColumn, String _lowPriceColumn) {

       this.openPriceColumn = _openPriceColumn;
       this.closePriceColumn = _closePriceColumn;
       this.highPriceColumn = _highPriceColumn;
       this.lowPriceColumn = _lowPriceColumn;
    }


    public boolean hasHeader(String searchHeader) {
        for (String header : List.of(openPriceColumn,
                closePriceColumn,
                highPriceColumn,
                lowPriceColumn)) {

            if (header.equals(searchHeader))
                return true;
        }
        return false;
    };

    public CSVDataTypes getType() {
        return CSVDataTypes.Integer;
    }

    public String openPriceColumn;
    public String closePriceColumn;
    public String highPriceColumn;
    public String lowPriceColumn;

    public String delimiter = ",";
}
