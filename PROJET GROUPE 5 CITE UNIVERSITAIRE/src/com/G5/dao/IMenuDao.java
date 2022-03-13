package com.G5.dao;

import java.util.List;

import com.G5.model.Menu;


public interface IMenuDao {
	 void saveMenu(Menu menu);

	 void updateMenu(Menu menu);

	 Menu getMenuById(int id);

	 List<Menu> getAllMenus();

	 void deleteMenu(int id);
}
