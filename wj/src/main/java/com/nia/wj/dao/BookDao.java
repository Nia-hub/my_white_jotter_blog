package com.nia.wj.dao;

import com.nia.wj.pojo.Book;
import com.nia.wj.pojo.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName BookDao
 * @Description TODO
 * @Author ganwei
 * @Date 2021/4/4 23:40
 * @VERSION 1.0
 **/
public interface BookDao extends JpaRepository<Book,Integer> {

    List<Book> findAllByCategoryId(int categoryId);

    List<Book> findAllByTitleLikeOrAuthorLike(String keyword1, String keyword2);
}

