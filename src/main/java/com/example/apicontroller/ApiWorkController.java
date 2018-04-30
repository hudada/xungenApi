package com.example.apicontroller;

import static org.mockito.Matchers.booleanThat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = "/api/work")
public class ApiWorkController {

	@Autowired
	private UserDao userDao;
	@Autowired
	private PointDao pointDao;
	@Autowired
	private WorkDao workDao;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public BaseBean<List<WorkBean>> list(HttpServletRequest request) {
		String uid = request.getParameter("uid");
		List<WorkBean> list = workDao.findByUid(Long.parseLong(uid));
		return ResultUtils.resultSucceed(list);
	}

}
