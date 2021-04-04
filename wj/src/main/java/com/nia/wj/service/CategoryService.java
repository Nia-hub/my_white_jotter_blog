package com.nia.wj.service;

import com.nia.wj.dao.CategoryDao;
import com.nia.wj.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CategoryService
 * @Description TODO
 * @Author ganwei
 * @Date 2021/4/4 23:46
 * @VERSION 1.0
 **/
@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    /**
     * 查询全部，并按照主键降序排序
     * @return
     */
    public List<Category> getList(){
        //按照id降序排序
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return categoryDao.findAll(sort);
    }

    /**
     * 根据种类主键查询
     * @param id
     * @return
     */
    public Category getById(int id){
        //按照id查询，为空返回null
        return categoryDao.findById(id).orElse(null);
    }
}
