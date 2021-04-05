package com.nia.wj.service;

import com.nia.wj.dao.BookDao;
import com.nia.wj.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName BookService
 * @Description TODO
 * @Author ganwei
 * @Date 2021/4/4 23:46
 * @VERSION 1.0
 **/
@Service
public class BookService {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private CategoryService categoryService;

    /**
     * 按照书籍主键查询，并按照主键降序排序
     * @return
     */
    public List<Book> getList(){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return bookDao.findAll(sort);
    }

    /**
     * 当book主键存在时更新，否则插入
     * @param book
     */
    public void addOrUpdate(Book book){
        bookDao.save(book);
    }

    /**
     * 根据书籍主键删除
     * @param id
     */
    public void deleteById(int id){
        bookDao.deleteById(id);
    }

    /**
     * 根据种类主键查询所有数据
     * @param categoryId
     * @return
     */
    public List<Book> listByCategoryId(int categoryId) {
        return bookDao.findAllByCategoryId(categoryId);
    }

    /**
     * 根据关键词搜索书籍
     * @param keywords
     * @return
     */
    public List<Book> Search(String keywords) {
        return bookDao.findAllByTitleLikeOrAuthorLike('%' + keywords + '%', '%' + keywords + '%');
    }
}
