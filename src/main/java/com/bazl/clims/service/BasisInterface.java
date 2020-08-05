package com.bazl.clims.service;

import java.util.List;

/**
 * 基础接口  CRUD
 */
public interface BasisInterface<T,V> {
    /**
     * 查询list
     * @return
     */
    List<T> queryList();
    /**
     * 条件查询
     * @return t
     */
    T queryById(T t);

    /**
     * 条件删除
     * @param v
     */
    void deleteById(V v);
    /**
     * 新增
     * @param t
     */
    void addOne(T t);
    /**
     * 更新
     * @param t
     */
    void updateOne(T t);
}
