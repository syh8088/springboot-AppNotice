package com.example.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Setter;

public class Paginator {


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pageNo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pageSize;

    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer limit;

    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer offset;

    @Setter
    @JsonIgnore
    private Integer totalCount;

    @Setter
    @JsonIgnore
    private boolean countable;

    public void setPageNo(Integer pageNo) {

        if (pageNo == null) {
            this.pageNo = null;
            return;
        }
        if (pageNo == 0) {
            this.pageNo = null;
            return;
        }
        this.pageNo = pageNo;
        if (this.pageSize != null) {
            this.offset = (this.pageSize * pageNo) - this.pageSize;
        }
    }

    public void setPageSize(Integer pageSize) {

        if (pageSize == null) {
            this.pageSize = null;
            return;
        }
        if (pageSize == 0) {
            this.pageSize = null;
            return;
        }
        this.pageSize = pageSize;
        this.limit = pageSize;
        if (this.pageNo != null) {
            this.offset = (pageSize * pageNo) - pageSize;
        }
    }
}
