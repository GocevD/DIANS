import java.text.ParseException;
import java.util.List;

public class DataValidationFilter implements Filter<List<Winery>> {
    private Filter<List<Winery>> nextFilter;

    public void setNextFilter(Filter<List<Winery>> nextFilter) {
        this.nextFilter = nextFilter;
    }

    @Override
    public List<Winery> execute(List<Winery> inputData) throws ParseException {
        // Validate data in inputData
        validateData(inputData);

        // Pass the processed data to the next filter in the pipeline
        if (nextFilter != null) {
            return nextFilter.execute(inputData);
        } else {
            // This is the last filter in the pipeline, return the final result
            return inputData;
        }
    }

    // Example method to validate data - customize as needed
    private void validateData(List<Winery> wineries) {
        for (Winery winery : wineries) {
            if (!isValidWinery(winery)) {
                // Handle invalid winery data (e.g., log, throw exception, skip, etc.)
                System.out.println("Invalid Winery: " + winery);
            }
        }
    }

    // Example method to check if a Winery is valid - customize as needed
    private boolean isValidWinery(Winery winery) {
        // Add your validation logic here
        // For example, check if essential fields are not null or empty
        return winery.getName() != null && !winery.getName().isEmpty()
                && winery.getAddress() != null && !winery.getAddress().isEmpty()
                && winery.getCity() != null && !winery.getCity().isEmpty();
    }
}