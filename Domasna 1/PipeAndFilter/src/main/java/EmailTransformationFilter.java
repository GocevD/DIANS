import java.text.ParseException;
import java.util.List;

public class EmailTransformationFilter implements Filter<List<Winery>> {
    private Filter<List<Winery>> nextFilter;

    public void setNextFilter(Filter<List<Winery>> nextFilter) {
        this.nextFilter = nextFilter;
    }

    @Override
    public List<Winery> execute(List<Winery> inputData) throws ParseException {
        // Validate and standardize email addresses in inputData
        for (Winery winery : inputData) {
            winery.setEmail(transformEmail(winery.getEmail()));
        }

        // Pass the processed data to the next filter in the pipeline
        if (nextFilter != null) {
            return nextFilter.execute(inputData);
        } else {
            // This is the last filter in the pipeline, return the final result
            return inputData;
        }
    }

    // Example email address transformation - customize as needed
    private String transformEmail(String originalEmail) {
        // Validate and standardize email address
        // Example: Ensure the email is in lowercase
        return originalEmail.toLowerCase();
    }
}