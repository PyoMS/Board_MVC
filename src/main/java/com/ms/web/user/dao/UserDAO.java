package com.ms.web.user.dao;

import java.util.List;

import com.ms.web.user.model.UserVO;

public interface UserDAO {
	public UserVO getUserInfo(String uid) throws Exception;
	public List<UserVO> getUserList() throws Exception;
	public int insertUser(UserVO userVO) throws Exception;
	public int updateUser(UserVO userVO) throws Exception;
	public int deleteUser(String uid) throws Exception;
}
