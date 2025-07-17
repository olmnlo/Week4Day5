package org.example.todo.Controller;

import org.example.todo.Model.Todo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/todo")
public class Controller {

    ArrayList<Todo> todos = new ArrayList<>();


    @PostMapping("/create-task")
    public String createNewTask(@RequestBody Todo todo) {
        todos.add(todo);
        return "add successfully";
    }

    @GetMapping("/get-tasks")
    public ArrayList<Todo> getTodos() {
        return todos;
    }

    @PutMapping("/update-task/{index}")
    public String updateTodo(@PathVariable int index, @RequestBody Todo todo){
        todos.set(index, todo);
        return "update successfully";
    }


    @DeleteMapping("/delete/{index}")
    public String deleteTodo(@PathVariable int index){
        todos.remove(index);
        return "delete successfully";
    }

    @PutMapping
    public String changeStatus( boolean status){}






}
