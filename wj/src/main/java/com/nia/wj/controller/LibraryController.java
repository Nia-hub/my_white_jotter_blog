package com.nia.wj.controller;

import com.nia.wj.pojo.Book;
import com.nia.wj.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName LibraryController
 * @Description TODO
 * @Author ganwei
 * @Date 2021/4/5 0:02
 * @VERSION 1.0
 **/
@RestController
public class LibraryController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/api/hello")
    @ResponseBody
    public String hello(){
        return "LibraryController";
    }

    @GetMapping("/api/books")
    public List<Book> list() throws Exception {
        return bookService.getList();
    }

    @PostMapping("/api/books")
    public Book addOrUpdate(@RequestBody Book book) throws Exception {
        bookService.addOrUpdate(book);
        return book;
    }

    @PostMapping("/api/delete")
    public void delete(@RequestBody Book book) throws Exception {
        bookService.deleteById(book.getId());
    }


    @GetMapping("/api/categories/{categoryId}/books")
    public List<Book> listByCategory(@PathVariable("categoryId") int categoryId) throws Exception {
        if (0 != categoryId) {
            return bookService.listByCategoryId(categoryId);
        } else {
            return list();
        }
    }
}
