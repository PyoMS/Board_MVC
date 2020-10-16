package com.ms.web.user.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ms.web.user.model.UserVO;

@Repository
public class UserDAOImpl implements UserDAO{
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public UserVO getUserInfo(String uid) throws Exception {
		return sqlSession.selectOne("com.ms.web.user.userMapper.getUserInfo", uid);
	}

	@Override
	public List<UserVO> getUserList() throws Exception {
		return sqlSession.selectList("com.ms.web.user.userMapper.getUserList");
	}

	@Override
	public int insertUser(UserVO userVO) throws Exception {
		return sqlSession.insert("com.ms.web.user.userMapper.insertUser", userVO);
	}

	@Override
	public int updateUser(UserVO userVO) throws Exception {
		return sqlSession.update("com.ms.web.user.userMapper.updateUser", userVO);
	}

	@Override
	public int deleteUser(String uid) throws Exception {
		return sqlSession.delete("com.ms.web.user.userMapper.deleteUser", uid);
	}


}
