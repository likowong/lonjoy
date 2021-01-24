package com.spring.cloud.bff.graphql.service.datafetcher;


import com.spring.cloud.bff.graphql.model.Book;
import com.spring.cloud.bff.graphql.mongoservice.base.BaseDocService;
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
