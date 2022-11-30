package com.academy.project.helper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class CustomPage<T> {
    List<T> content;

    CustomPageable pageable;
    public CustomPage(Page<T>page){
        this.content = page.getContent();
        this.pageable = new CustomPageable(page.getTotalElements());

    }
}

@Data
class CustomPageable{
    long totalRecords;
    public CustomPageable(long totalRecords){
        this.totalRecords = totalRecords;
    }
}
