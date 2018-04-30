package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bean.WorkBean;
import com.example.bean.PointBean;
import com.example.bean.UserBean;

public interface WorkDao extends JpaRepository<WorkBean, Long> {

	@Query("from WorkBean b where b.uid=:uid order by b.time desc")
	List<WorkBean> findByUid(@Param("uid") Long uid);
	
	
}
