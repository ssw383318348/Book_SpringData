package com.qf.book.repository;

import com.qf.book.pojo.Kind;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KindRepository extends JpaRepository<Kind, Integer> {

	//通过类型查询到所对应书籍
	Kind findByType(String type);


}
