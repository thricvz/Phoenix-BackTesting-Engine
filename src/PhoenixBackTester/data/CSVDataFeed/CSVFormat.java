package PhoenixBackTester.data.CSVDataFeed;

import java.util.List;

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


    public String openPriceColumn;
    public String closePriceColumn;
    public String highPriceColumn;
    public String lowPriceColumn;

    public String regexDelimiter = "\\,";
    public String regexfloatingPoint = "\\.";
}
