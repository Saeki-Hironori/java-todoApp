package com.todo.app.entity;

import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

// DataはGetter,Setterを自動生成する
@Data
public class Todo {

  private long id;
  
  @NotBlank(message="タイトルを入力してね")
  @Size(max=20)
  private String title;
  
  private int done_flg;
  
  @DateTimeFormat(pattern ="yyyy-MM-dd")
  private String time_limit;

}
