import java.text.ParseException;
import java.util.List;

public class WebsiteTransformationFilter implements Filter<List<Winery>> {
    private Filter<List<Winery>> nextFilter;

    public void setNextFilter(Filter<List<Winery>> nextFilter) {
        this.nextFilter = nextFilter;
    }

    @Override
    public List<Winery> execute(List<Winery> inputData) throws ParseException {
        // Standardize website URLs in inputData
        for (Winery winery : inputData) {
            winery.setWebsite(transformWebsite(winery.getWebsite()));
        }

        // Pass the processed data to the next filter in the pipeline
        if (nextFilter != null) {
            return nextFilter.execute(inputData);
        } else {
            // This is the last filter in the pipeline, return the final result
            return inputData;
        }
    }

    // Example website URL transformation - customize as needed
    private String transformWebsite(String originalWebsite) {
        // Standardize website URL
        // Example: Ensure the URL starts with "http://" or "https://"
        if (originalWebsite != null && !originalWebsite.isEmpty()) {
            if (!originalWebsite.startsWith("http://") && !originalWebsite.startsWith("https://")) {
                return "http://" + originalWebsite;
            }
        }
        return originalWebsite;
    }
}