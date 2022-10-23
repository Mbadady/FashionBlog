package com.FashionblogMbadady.FashionBlog.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDtoCustom {
    private List<CreatePostRequest> content;
    private boolean last;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
}
