package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bean.PointBean;
import com.example.bean.WorkBean;

public interface PointDao extends JpaRepository<PointBean, Long> {
	@Query("from PointBean b where b.wid=:wid")
	List<PointBean> findByWid(@Param("wid") Long wid);
}
