package com.G5.dao;

import java.util.List;

import com.G5.model.Commande;

public interface ICommandeDao {
	void saveCommande(Commande commande);

	 void updateCommande(Commande commande);

	 Commande getCommandeById(int id);

	 List<Commande> getAllCommandes();

	 void deleteCommande(int id);
	 void updateCommand(Commande commande);

}
