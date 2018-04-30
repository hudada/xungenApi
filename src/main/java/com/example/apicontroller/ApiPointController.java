package com.example.apicontroller;

import static org.assertj.core.api.Assertions.in;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.bean.BaseBean;
import com.example.bean.WorkBean;
import com.example.bean.PointBean;
import com.example.bean.UserBean;
import com.example.dao.WorkDao;
import com.example.dao.PointDao;
import com.example.dao.UserDao;
import com.example.utils.ResultUtils;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/point")
public class ApiPointController {

	@Autowired
	private UserDao userDao;
	@Autowired
	private PointDao pointDao;
	@Autowired
	private WorkDao workDao;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public BaseBean<PointBean> add(HttpServletRequest request) {
		String pname = request.getParameter("pname");
		String uid = request.getParameter("uid");
		String wname = request.getParameter("wname");
		WorkBean bean = new WorkBean();
		bean.setUid(Long.parseLong(uid));
		bean.setName(wname);
		bean.setTime(new Date().getTime());
		long wid = workDao.save(bean).getId();

		String[] arr = pname.split(",");
		for (String string : arr) {
			PointBean pointBean = new PointBean();
			pointBean.setWid(wid);
			pointBean.setName(string);
			pointDao.save(pointBean);
		}
		return ResultUtils.resultSucceed("");
	}

	@RequestMapping(value = "/change", method = RequestMethod.POST)
	public BaseBean<PointBean> change(HttpServletRequest request) {
		String id = request.getParameter("id");
		String lng = request.getParameter("lng");
		String lat = request.getParameter("lat");
		PointBean bean = pointDao.findOne(Long.parseLong(id));
		bean.setLat(Double.parseDouble(lat));
		bean.setLng(Double.parseDouble(lng));
		return ResultUtils.resultSucceed(pointDao.save(bean));
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public BaseBean<List<PointBean>> find(HttpServletRequest request) {
		String wid = request.getParameter("wid");
		List<PointBean> list = pointDao.findByWid(Long.parseLong(wid));
		return ResultUtils.resultSucceed(list);
	}

}
