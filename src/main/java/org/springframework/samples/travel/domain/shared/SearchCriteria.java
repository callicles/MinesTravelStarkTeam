package org.springframework.samples.travel.domain.shared;

import java.io.Serializable;

/**
 * A backing bean for generic string search. Encapsulates the criteria needed to
 * perform search.
 */
public final class SearchCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The user-provided search criteria.
     */
    private String searchString;

    /**
     * The maximum page size of the result list
     */
    private int pageSize;

    /**
     * The current page of the result list.
     */
    private int page;

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
