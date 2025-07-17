package org.example.todo.Controller;

import org.example.todo.Api.ApiResponse;
import org.example.todo.Model.Todo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/todo")
public class Controller {

    ArrayList<Todo> todos = new ArrayList<>();


    @PostMapping("/create-task")
    public ApiResponse createNewTask(@RequestBody Todo todo) {
        todo.setId(todos.size());
        todos.add(todo);
        return new ApiResponse("todo created successfully");
    }

    @GetMapping("/get-tasks")
    public ArrayList<Todo> getTodos() {
        return todos;
    }

    @PutMapping("/update-task/{index}")
    public ApiResponse updateTodo(@PathVariable int index, @RequestBody Todo todo){
        todo.setId(todos.get(index).getId());
        todos.set(index, todo);

        return new  ApiResponse("todo updated successfully");
    }


    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTodo(@PathVariable int index){
        todos.remove(index);
        for (int i = index; i < todos.size() ; i++) {
            todos.get(i).setId(todos.get(i).getId()-1);
        }
        return new ApiResponse("todo deleted successfully");
    }

    @PutMapping("change-status/{index}")
    public ApiResponse changeStatus(@PathVariable int index){
        todos.get(index).setStatus(!todos.get(index).isStatus());
        return new ApiResponse("status changed successfully");
    }


    @GetMapping("title/{title}")
    public ApiResponse findTodoByTitle(@PathVariable String title){
        for (Todo t : todos) {
            if(t.getTitle().equals(title)){
                return new ApiResponse("todo found: "+t.toString());
            }
        }
        return new ApiResponse("not found");
    }






}
