package fr.cmm.helper;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;


public class Pagination {
    // 1 based page index
    private int pageIndex;

    public static final int PAGINATION_SIZE = 10;

    private int pageSize;

    private long count;

    public int getPreviousPageIndex() {
        return isFirstPage() ? pageIndex : pageIndex - 1;
    }

    public int getNextPageIndex() {
        return isLastPage() ? pageIndex : pageIndex + 1;
    }

    public boolean isFirstPage() {
        return pageIndex == 1;
    }

    public boolean isLastPage() {
        return pageIndex * pageSize >= count;
    }

    public int getPageCount() {
        if (count % pageSize == 0) {
            return (int) count / pageSize;
        } else {
            return (int) count / pageSize + 1;
        }
    }

    public List<Integer> getPages() {
        List pageList = new ArrayList<>();
        if (pageIndex < PAGINATION_SIZE/2){
            for (int i = 1; i<=Math.min(this.getPageCount(), PAGINATION_SIZE); i++)
            {
                pageList.add(i);
            }
        }
        else if (pageIndex >= PAGINATION_SIZE/2){
            for (int i = pageIndex-((PAGINATION_SIZE/2)-1); i<=Math.min(this.getPageCount(), pageIndex+PAGINATION_SIZE/2); i++)
            {
                pageList.add(i);
            }
        }

        return pageList;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
