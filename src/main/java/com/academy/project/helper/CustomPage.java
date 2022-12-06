package com.academy.project.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomPage<T> {
    List<T> content;

    Long totalRecords;

    public CustomPage(Slice<T> slice, HttpStatus status, Long count) {
        this.content = slice.getContent();
        this.totalRecords = count;
    }
}
