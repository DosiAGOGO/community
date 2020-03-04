package cc.smcoco.community.dto;

import java.util.ArrayList;
import java.util.List;

public class PaginationDTO {
    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isEndPage() {
        return endPage;
    }

    public void setEndPage(boolean endPage) {
        this.endPage = endPage;
    }

    public boolean isPrePage() {
        return prePage;
    }

    public void setPrePage(boolean prePage) {
        this.prePage = prePage;
    }

    public boolean isNextPage() {
        return nextPage;
    }

    public void setNextPage(boolean nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    private List<QuestionDTO> questions;
    private boolean firstPage;
    private boolean endPage;
    private boolean prePage;
    private boolean nextPage;
    private Integer totalPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    ;

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
    public void setPagination(Integer totalCount, Integer page, Integer size) {
        //如果第一页则无前端显示
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page > totalCount) {
            page = totalPage;
        }
        if (page < 1) {
            page = 1;
        }
        this.page = page;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
            if (page - i > 0) {
                pages.add(0, page - i);
            }
        }

        if (page == 1) {
            prePage = false;
        } else {
            prePage = true;
        }
        if (page == totalPage) {
            nextPage = false;
        } else {
            nextPage = true;
        }
        if (page > 3) {
            firstPage = true;
        } else {
            firstPage = false;
        }
        if (page < totalPage - 3) {
            endPage = true;
        } else {
            endPage = false;
        }
    }
}
