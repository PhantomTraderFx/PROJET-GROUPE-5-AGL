package com.G5.dao;

import java.util.List;

import com.G5.model.Permission;


public interface IPermissionDao {
	void savePermission(Permission permission);

	 void updatePermission(Permission permission);

	 Permission getPermissionById(int id);

	 List<Permission> getAllPermission();

	 void deletePermission(int id);

}
