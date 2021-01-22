package com.springcloud.graphql.service.datafetcher;


import com.springcloud.graphql.model.Book;
import com.springcloud.graphql.mongoservice.base.BaseDocService;
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
