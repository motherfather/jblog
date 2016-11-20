package com.bit2016.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bit2016.jblog.repository.BlogDao;
import com.bit2016.jblog.repository.CategoryDao;
import com.bit2016.jblog.repository.UserDao;
import com.bit2016.jblog.vo.BlogVo;
import com.bit2016.jblog.vo.UserVo;

@Service
public class BlogService {
	private static final String SAVE_PATH = "/upload";
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	public List<Map<String, Object>> blogMain(String userId) {
		return blogDao.blogMain(userId);
	}
	
	public List<Map<String, Object>> blogMain1(String userId, Object cNo) {
		return blogDao.blogMain1(userId, cNo);
	}
	
	public List<Map<String, Object>> blogMain2(String userId) {
		return blogDao.blogMain2(userId);
	}
	
	public List<Map<String, Object>> mainPost(String userId) {
		return blogDao.mainPost(userId);
	}
	
	public List<Map<String, Object>> CPNo(String userId, Long cNo, Long pNo) {
		return blogDao.CPNo(userId, cNo, pNo);
	}
	
	public List<Map<String, Object>> PNo(String userId, Long pNo) {
		return blogDao.PNo(userId, pNo);
	}
	
	public Long PPerC(String userId, Long cNo) {
		return blogDao.PPerC(userId, cNo);
	}
	
	public void modify(UserVo authUser, BlogVo vo, MultipartFile file) {
		try {
			String orgName = file.getOriginalFilename();
			String extName = orgName.substring(orgName.lastIndexOf('.')+1, orgName.length());
			String saveName = createName(extName);
			
//			Long no = userDao.getNo(userId);
			
			writeFile(file, saveName);
			
			vo.setLogo(saveName);
			vo.setUsersNo(authUser.getNo());
			
			System.out.println(vo);
			blogDao.modify(vo);
		} catch (IOException ex) {
			throw new RuntimeException();
		}
	}
	
	public void writeFile(MultipartFile file, String saveName) throws IOException {
		byte[] fileData = file.getBytes();
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveName);
		fos.write(fileData);
		fos.close();
	}
	
	public String createName(String extName) {
		String fileName = "";
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		fileName += format.format(calendar.getTime());
		fileName += ('.' + extName);
		return fileName;
	}
}
