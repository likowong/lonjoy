package com.spring.cloud.bff.graphql.service.datafetcher;


import com.spring.cloud.bff.graphql.mongoservice.base.BaseDocService;
import com.spring.cloud.bff.graphql.model.Book;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookDataFetcher implements DataFetcher<Book> {

    @Autowired
    private BaseDocService<Book> baseDocService;

    @Override
    public Book get(DataFetchingEnvironment dataFetchingEnvironment) {
        String isn = dataFetchingEnvironment.getArgument("id");
        return baseDocService.findById(isn, Book.class, "test");
    }
}
