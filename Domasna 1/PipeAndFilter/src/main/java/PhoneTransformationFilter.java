import java.text.ParseException;
import java.util.List;

public class PhoneTransformationFilter implements Filter<List<Winery>> {
    private Filter<List<Winery>> nextFilter;

    public void setNextFilter(Filter<List<Winery>> nextFilter) {
        this.nextFilter = nextFilter;
    }

    @Override
    public List<Winery> execute(List<Winery> inputData) throws ParseException {
        // Standardize the phone number format in inputData
        for (Winery winery : inputData) {
            winery.setPhone(transformPhone(winery.getPhone()));
        }

        // Pass the processed data to the next filter in the pipeline
        if (nextFilter != null) {
            return nextFilter.execute(inputData);
        } else {
            // This is the last filter in the pipeline, return the final result
            return inputData;
        }
    }

    // Example phone number transformation - customize as needed
    private String transformPhone(String originalPhone) {
        // Perform phone number transformation (e.g., remove non-numeric characters)
        // Example: Remove non-numeric characters
        return originalPhone.replaceAll("[^\\d]", "");
    }
}