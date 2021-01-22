package com.springcloud.graphql.service.datafetcher;


import com.springcloud.graphql.model.Book;
import com.springcloud.graphql.mongoservice.base.BaseDocService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllBooksDataFetcher implements DataFetcher<List<Book>> {

    @Autowired
    private BaseDocService<Book> baseDocService;

    @Override
    public List<Book> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return baseDocService.findAll(Book.class, "test");
    }
}
