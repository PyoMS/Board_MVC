package com.ms.web.menu.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ms.web.menu.model.MenuVO;

@Repository
public class MenuDAOImpl implements MenuDAO{
	@Inject
	private SqlSession SqlSession;
	
	@Override
	public List<MenuVO> getMenuList() throws Exception {
		return SqlSession.selectList("com.ms.web.menu.menuMapper.getMenuList");
	}

	@Override
	public int saveMenu(MenuVO menuVO) throws Exception {
		return SqlSession.insert("com.ms.web.menu.menuMapper.insertMenu", menuVO);
	}

	@Override
	public int updateMenu(MenuVO menuVO) throws Exception {
		return SqlSession.update("com.ms.web.menu.menuMapper.updateMenu", menuVO);
	}

	@Override
	public int deleteMenu(String code) throws Exception {
		return SqlSession.delete("com.ms.web.menu.menuMapper.deleteMenu", code);
	}

}
