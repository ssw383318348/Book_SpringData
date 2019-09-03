package com.qf.book.repository;

import com.qf.book.pojo.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Integer>{

	//通过用户名查询借书记录
	List<Record> findAllByUNameLike(String uName);

}
