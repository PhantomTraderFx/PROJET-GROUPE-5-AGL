package com.G5.dao;

import java.util.List;

import com.G5.model.Alerte;


public interface IAlerteDao {
	 void saveAlerte(Alerte Alerte);

	 void updateAlerte(Alerte alerte);

	 Alerte getAlerteById(int id);

	 List<Alerte> getAllAlertes();

	 void deleteAlerte(int id);
}
