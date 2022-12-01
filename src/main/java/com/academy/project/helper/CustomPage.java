package com.academy.project.helper;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class CustomPage<T> {
    List<T> content;

    String totalRecords;

    public CustomPage(Slice<T> slice, HttpStatus status) {
        this.content = slice.getContent();
        this.totalRecords = ("" + slice.getNumberOfElements());
    }
}
