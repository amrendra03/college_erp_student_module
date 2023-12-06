package com.server.dto;

import java.util.List;

import lombok.Data;

@Data
public class ResponseStudent {
   private List<StudentDto> content;
   private int pageNumber;
   private int pageSize;
   private long totalElement;
   private int totalPage;
   private boolean lastPage;
}
