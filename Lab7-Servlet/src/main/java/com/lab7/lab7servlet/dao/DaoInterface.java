package com.lab7.lab7servlet.dao;

import java.util.List;

public interface DaoInterface<T> {
    public int insert(T t);
    public int update(T t);
    public int delete(Long id);
    public List<T> selectAll();
    public T selectById(Long id);
}
