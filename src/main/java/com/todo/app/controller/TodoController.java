package com.todo.app.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.todo.app.entity.Todo;
import com.todo.app.mapper.TodoMapper;

@Controller
public class TodoController {

  private final TodoMapper todoMapper;

  public TodoController(TodoMapper todoMapper) {
    this.todoMapper = todoMapper;
  }

  @GetMapping(value = "/")
  public String index(Model model) {
    List<Todo> list = todoMapper.selectIncomplete();
    List<Todo> doneList = todoMapper.selectComplete();
    model.addAttribute("todos", list);
    model.addAttribute("doneTodos", doneList);
    return "index";
  }

  @PostMapping(value = "/add")
  public String add(@Validated @ModelAttribute Todo todo, BindingResult result, Model model) {
    if (result.hasErrors()) {
      List<String> errorList = new ArrayList<String>();
      for (ObjectError error : result.getAllErrors()) {
        errorList.add(error.getDefaultMessage());
      }
      model.addAttribute("error", errorList);
      return index(model);
    }
    todoMapper.add(todo);
    return "redirect:/";
  }

  @PostMapping(value = "/update")
  public String update(Todo todo) {
    todoMapper.update(todo);
    return "redirect:/";
  }

  @PostMapping(value = "/delete")
  public String delete() {
    todoMapper.delete();
    return "redirect:/";
  }
}
