package com.spring.cloud.bff.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.MongoId;



@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Book {
    @MongoId
    private String isn;
    private String title;
    private String publisher;
    private String[] authors;
    private String publishedDate;

}
