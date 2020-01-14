package com.surc;

import java.text.ParseException;

public interface BaseCRUD {
    void insert() throws Exception;
    void delete();
    void update() throws ParseException;
    void select();
    void selectAll();
}
