package bean;

import java.util.ArrayList;
import java.util.List;

public class SQLBuilder {
    private SearchForm searchForm;
    private List<String> params = new ArrayList<>();

    public SQLBuilder(SearchForm searchForm) {
        this.searchForm = searchForm;
    }

    public List<String> getParams() {
        return params;
    }

    public StringBuilder createLine() {
        StringBuilder line = new StringBuilder("SELECT * FROM PRODUCTS WHERE (productName LIKE ?) ");
        params.add("%" + searchForm.getQuery() + "%");
        addOtherParams(line);
        return line;
    }

    private void addOtherParams(StringBuilder line) {
        if (searchForm.getTypes() != null && !searchForm.getTypes().isEmpty()) {
            addByTypes(line);
        }
        if (searchForm.getCountries() != null && !searchForm.getCountries().isEmpty()) {
            addByCountry(line);
        }
        addByPrice(line);
    }

    private void addByCountry(StringBuilder line) {
        line.append(" and (productCountry=?");
        for (int i = 0; i < searchForm.getCountries().size(); i++) {
            params.add(searchForm.getCountries().get(i));
            if (i != searchForm.getCountries().size() - 1) {
                line.append(" or productCountry=?");
            }
        }
        line.append(")");
    }

    private void addByTypes(StringBuilder line) {
        line.append(" and (productType=?");
        for (int i = 0; i < searchForm.getTypes().size(); i++) {
            params.add(searchForm.getTypes().get(i));
            if (i != searchForm.getTypes().size() - 1) {
                line.append(" or productType=?");
            }
        }
        line.append(")");
    }

    private void addByPrice(StringBuilder line) {
        if (!searchForm.getMinPrice().isEmpty()) {
            line.append(" and (productCost>=?)");
            params.add(searchForm.getMinPrice());
        }
        if (!searchForm.getMaxPrice().isEmpty()) {
            line.append(" and (productCost<=?)");
            params.add(searchForm.getMaxPrice());
        }
    }
}
