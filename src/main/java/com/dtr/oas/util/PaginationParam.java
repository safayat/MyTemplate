package com.dtr.oas.util;

import org.springframework.util.StringUtils;

/**
 * Created by safayat on 12/12/15.
 */
public class PaginationParam {
    public static final int QUERY_LIMIT = 100;
    private int limit;
    private int offset;
    private String order;
    private String sort;

    public PaginationParam(int limit, int offset, String order, String sort) {
        limit(limit);
        offset(offset);
        this.order = order;
        sort(sort);
    }
    public PaginationParam(String order) {
        this.limit = QUERY_LIMIT;
        this.offset = 0;
        this.order = order;
        this.sort = "desc";
    }
    public PaginationParam(String order, String sort) {
        this.limit = QUERY_LIMIT;
        this.offset = 0;
        this.order = order;
        this.sort = sort;
    }
    public PaginationParam() {
        this.limit = QUERY_LIMIT;
        this.offset = 0;
        this.sort = "desc";
    }

    public int getLimit() {
        return limit;
    }

    public PaginationParam limit(int limit) {
        this.limit = limit;
        if(limit > 100 || limit <=0)
        {
            this.limit = QUERY_LIMIT;
        }
        return this;
    }

    public int getOffset() {
        return offset;
    }

    public PaginationParam offset(int offset) {
        this.offset = offset;
        if(offset < 0){
            this.offset = 0;
        }
        return this;
    }

    public String getOrder() {
        return order;
    }

    public PaginationParam order(String order) {
        this.order = order;
        return this;
    }

    public String getSort() {
        return sort;
    }

    public PaginationParam sort(String sort) {
        this.sort = sort;
        if("asc".equalsIgnoreCase(sort) == false){
            this.sort = "desc";
        }
        return this;
    }

    public String toQuery(){
        String query = "";
        if(StringUtils.hasLength(order)){
            query = query + " order by " + order;
            if(StringUtils.hasLength(sort)){
                query = query + " " + sort;
            }
        }
        return query;
    }
}
