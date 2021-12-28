package com.stark.pojo;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class Book {

    private String name;
    private String isbn;
    private Object year;
    private String author;
    private Object book_category_id;
    private String description;

}
