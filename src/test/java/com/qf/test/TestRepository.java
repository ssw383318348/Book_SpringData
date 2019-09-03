package com.qf.test;

import com.qf.book.pojo.Book;
import com.qf.book.pojo.Kind;
import com.qf.book.repository.BookRepository;
import com.qf.book.repository.KindRepository;
import com.qf.book.repository.RecordRepository;
import com.qf.book.repository.UserRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestRepository {


}
