package com.academy.project.helper;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class SamplePageRequest implements Pageable {

    private int limit;
    private int offset;
    private final Sort sort;
    public SamplePageRequest(int offset, int limit, Sort sort) throws IllegalArgumentException {

        /*if (limit < 0){
            throw new IllegalArgumentException("Limit must not be less than one!");
        }
        if (offset < 0){
            throw new IllegalArgumentException("Offset index must not be less than zero!");
        }*/
        this.offset = offset;
        this.limit = limit;
        this.sort = sort;
    }


    @Override
    public int getPageNumber() {
        return offset / limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
}