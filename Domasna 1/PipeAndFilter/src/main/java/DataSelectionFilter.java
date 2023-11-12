import java.text.ParseException;
import java.util.List;

public class DataSelectionFilter implements Filter<List<Winery>> {
    private Filter<List<Winery>> nextFilter;

    public void setNextFilter(Filter<List<Winery>> nextFilter) {
        this.nextFilter = nextFilter;
    }

    @Override
    public List<Winery> execute(List<Winery> inputData) throws ParseException {
        // Select relevant data points in inputData
        // For example, let's say we want to exclude wineries without a phone number
        inputData.removeIf(winery -> winery.getPhone() == null || winery.getPhone().isEmpty());

        // Pass the processed data to the next filter in the pipeline
        if (nextFilter != null) {
            return nextFilter.execute(inputData);
        } else {
            // This is the last filter in the pipeline, return the final result
            return inputData;
        }
    }
}