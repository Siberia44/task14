package bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SearchForm {
    private String query;
    private List<String> types;
    private List<String> countries;
    private String minPrice;
    private String maxPrice;

    public SearchForm(String query, String[] types, String[] countries, String minPrice, String maxPrice) {
        this.query = query;
        this.types = convert(types);
        this.countries = convert(countries);
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    private List<String> convert(String[] arrayOfParameters) {
        if (arrayOfParameters == null) {
            return new ArrayList<>();
        } else {
            return Arrays.asList(arrayOfParameters);
        }
    }

    public List<String> getTypes() {
        return types;
    }

    public String getQuery() {
        return query;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public List<String> getCountries() {
        return countries;
    }
}
