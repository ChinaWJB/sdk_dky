package org.caeit.cs.model.dky.service;
public interface Accessible {
    /**
     * 设置名为name的字段的值
     *
     * @param name
     * @param value
     */
    void set(String name, String value);

    /**
     * 获取名为name的字段的值
     *
     * @param name
     * @return
     */
    String get(String name);

    /**
     * 克隆当前对象
     *
     * @return
     */
    Accessible copy();
}