package com.gahoccode.bookservice.query.projection;

import com.gahoccode.bookservice.command.data.Book;
import com.gahoccode.bookservice.command.data.BookRepository;
import com.gahoccode.bookservice.query.model.BookResponseModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookProjection {

    @Autowired
    private BookRepository bookRepository;

    @QueryHandler
    public List<BookResponseModel> handle(GetAllBookQuery query){
        List<Book> list = bookRepository.findAll();
        List<BookResponseModel> listBookResponse = new ArrayList<>();
        list.forEach(book -> {
            BookResponseModel model = new BookResponseModel();
            BeanUtils.copyProperties(book,model);
            listBookResponse.add(model);
        });
        return listBookResponse;
    }


}
