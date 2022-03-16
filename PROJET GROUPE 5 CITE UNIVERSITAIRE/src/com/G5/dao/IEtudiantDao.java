package com.G5.dao;

import java.util.List;

import com.G5.model.Etudiant;

public interface IEtudiantDao {
	void saveEtudiant(Etudiant etudiant);

	 void updateEtudiant(Etudiant etudiant);

	 Etudiant getEtudiantById(int id);

	 List<Etudiant> getAllEtudiants();

	 void deleteEtudiant(int id);
	 
	 Etudiant getEtudiant(String nom, String prenom);

}
