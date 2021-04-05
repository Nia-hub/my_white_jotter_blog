package com.nia.wj.controller;

import com.nia.wj.pojo.Book;
import com.nia.wj.service.BookService;
import com.nia.wj.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    /**
     * 根据关键词搜索书籍
     * @param keywords
     * @return
     */
    @GetMapping("/api/search")
    public List<Book> searchResult(@RequestParam("keywords") String keywords) {
        // 关键词为空时查询出所有书籍
        if ("".equals(keywords)) {
            return bookService.getList();
        } else {
            return bookService.Search(keywords);
        }
    }

    /**
     * 上传封面
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("api/covers")
    public String coversUpload(MultipartFile file) throws Exception {

        String folder = "D:/upload/img";

        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            return "http://localhost:8888/api/file/" + f.getName();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
