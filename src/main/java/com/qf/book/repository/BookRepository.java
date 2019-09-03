package com.qf.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qf.book.pojo.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	//通过书名查询书籍
	List<Book> findAllByBNameLike(String bName);

	//通过还书书名查询书籍
	Book findByBName(String getbName);

	//通过模糊查询查询到我们的书籍
	@Query(nativeQuery = true, value ="select * from book where kind_id=?1")
	List<Book> findAllByKind(Integer kId);


}
