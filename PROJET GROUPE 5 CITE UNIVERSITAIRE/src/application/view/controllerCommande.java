package application.view;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.G5.dao.CommandeDao;
import com.G5.dao.EtudiantDao;
import com.G5.dao.MenuDao;
import com.G5.model.Commande;
import com.G5.model.Etudiant;
import com.G5.model.Menu;

import application.CommandeTest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class controllerCommande implements Initializable{

	@FXML
	private DatePicker Dte_c;
	 
    @FXML
    private ComboBox<String> CommandeNomEtudiant;
    
    @FXML
    private TextField searchBox;


    @FXML
    private TableView<Commande> IdTable;

    @FXML
    private ComboBox<String> JourCommande;

    @FXML
    private ComboBox<String> PlatMidi;

    @FXML
    private ComboBox<String> PlatSoir;

    @FXML
    private Button enregistrerCommande;

    @FXML
    private TableColumn<Commande, Date> idDateTab;

    @FXML
    private TableColumn<Commande, String> idNomTab;

    @FXML
    private TableColumn<Commande, String> idPlatMTab;

    @FXML
    private TableColumn<Commande, String> idPlatSTab;

    //@FXML
   // private TableColumn<?, ?> idPreTab;
    
    @FXML
    private TableColumn<Commande, String> IdAction;
    
   
    List<Commande> Commandelist = FXCollections.observableArrayList();
    ObservableList<Commande> l = FXCollections.observableArrayList();
    
    MenuDao menuDao = new MenuDao();
    Menu menu = new Menu();
    EtudiantDao etudiantDao = new EtudiantDao();
    Etudiant etudiant = new Etudiant();
    CommandeDao commandeDao = new CommandeDao();
    Commande commandeEdit = new Commande();
    Commande commande = new Commande();

    String Pm;
    String Ps;
    int ID_Menu;
    String JourC;
    boolean edit = false;

	int ID;
    
    @FXML
    void recupererPlatMidi(ActionEvent event) {
    	String e = PlatMidi.getValue();
    	this.Pm = e;
    }

    @FXML
    void recupererPlatSoir(ActionEvent event) {
    	String a = PlatSoir.getValue();
    	this.Ps = a; 
    }

    @FXML
    void MethodeTableView(ActionEvent event) {
    	
    }
    
    void ValeurID(int e) {
    	this.ID = e;
    }
    
    void ValeurJourC(String j) {
    	this.JourC = j;
    }
    
    @FXML
    void test1(ActionEvent event) {
    	String nomPrenom = CommandeNomEtudiant.getValue();
    	//String t = nomPrenom.toString();
    	List<String> listNP = Arrays.asList(nomPrenom.split(" "));
    	//System.out.println(listNP);
    	String NomE = listNP.get(0);
    	String PrenomE = "";
    	for(int i=1;i<listNP.size();i++) {
    		if (PrenomE == "") {
    			PrenomE = listNP.get(i);
    		}else {
    		PrenomE = PrenomE + " " + listNP.get(i);
    		}
    	}
    	etudiant = etudiantDao.getEtudiant(NomE, PrenomE);
    	
    	int e = etudiant.getIdEtu();
    	ValeurID(e);
    	
    }
    
    @FXML
    void SearchBoxMethod(ActionEvent event) {
		FilteredList<Commande> filteredData = new FilteredList<>(l, p -> true);
				
				searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
					filteredData.setPredicate(person -> {
						// If filter text is empty, display all persons.
						if (newValue == null || newValue.isEmpty()) {
							return true;
						}
						
						// Compare first name and last name of every person with filter text.
						String lowerCaseFilter = newValue.toLowerCase();
						
						if (String.valueOf(commande.getIdEtu()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
							return true; // Filter matches first name.
						}
						return false; // Does not match.
					});
				});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Commande> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(IdTable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		IdTable.getItems().setAll(sortedData);
    }

    @FXML
    void SaveToTable(MouseEvent event) {
        commande.setIdEtu(this.ID);
        commande.setDatecommande(Date.valueOf(Dte_c.getValue()));
        commande.setPlatcmdi(this.Pm);
        commande.setPlatcsoir(this.Ps);
        commande.setIdMenu(this.ID_Menu);
        commandeDao.saveCommande(commande);
        Commandelist.add(commande);
        l.add(commande);
        chargementbtn();
        IdTable.getItems().setAll(commandeDao.getAllCommandes());
    	
    }
    
    void MettreDansCombo(String jour) {
    	PlatMidi.getItems().clear();
		PlatSoir.getItems().clear();
		menu = menuDao.recupererPlat(jour);
		this.ID_Menu = menu.getIdMenu();
		PlatMidi.getItems().add(menu.getPlat1mdi());
		PlatMidi.getItems().add(menu.getPlat2midi());
		PlatSoir.getItems().add(menu.getPlat1soir());
		PlatSoir.getItems().add(menu.getPlat2soir());
    }
    
    @FXML
    void test(ActionEvent event) {
    	String V = JourCommande.getValue();
		ValeurJourC(V);
    	if(V.equals("Lundi")) {
    		MettreDansCombo(V);
    	}
    	else if (V.equals("Mardi")) {
    		MettreDansCombo(V);
    	}
    	else if (V.equals("Mercredi")) {
    		MettreDansCombo(V);
    	}
    	else if (V.equals("Jeudi")) {
    		MettreDansCombo(V);
    	}
    	else if (V.equals("Vendredi")) {
    		MettreDansCombo(V);
    	}
    	else if (V.equals("Samedi")) {
    		MettreDansCombo(V);
    	}
    	else {
    		MettreDansCombo(V);
    	}
    	
    }
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//l = (ObservableList<Commande>) commandeDao.getAllCommandes();
		System.out.println(l);
		Commandelist = commandeDao.getAllCommandes();
		EtudiantDao etudiantDao = new EtudiantDao();
		for(Etudiant e: etudiantDao.getAllEtudiants()) {
			CommandeNomEtudiant.getItems().addAll(e.getNom()+" "+e.getPrenoms());
		}
		
		MenuDao menuDao = new MenuDao();
		for(Menu m: menuDao.getAllMenus()) {
			JourCommande.getItems().addAll(m.getJour());
		}
		idNomTab.setCellValueFactory(new PropertyValueFactory<>("idEtu"));
		idPlatMTab.setCellValueFactory(new PropertyValueFactory<>("platcmdi"));
		idPlatSTab.setCellValueFactory(new PropertyValueFactory<>("platcsoir"));
		idDateTab.setCellValueFactory(new PropertyValueFactory<>("datecommande"));
		IdTable.getItems().setAll(commandeDao.getAllCommandes());
		chargementbtn();
		
		
	}
		
	
	public void chargementbtn() {
		Callback<TableColumn<Commande, String>, TableCell<Commande, String>> cellFoctory = (TableColumn<Commande, String> param) -> {
            // make cell containing buttons
            final TableCell<Commande, String> cell = new TableCell<Commande, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        final Text deleteIcon = new Text("supprimer");
                        Text editIcon = new Text("modifier");

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            Commande commande = IdTable.getSelectionModel().getSelectedItem();
                            commandeDao.deleteCommande(commande.getIdCommande());
                            IdTable.getItems().setAll(commandeDao.getAllCommandes());
                            
                            
                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            commandeEdit = IdTable.getSelectionModel().getSelectedItem();
							/*
							 * commande.setIdEtu(13); commandeDao.updateCommande(commande);
							 * IdTable.getItems().setAll(commandeDao.getAllCommandes());
							 */
							
							 commandeEdit.setIdEtu(ID); 
							 commandeEdit.setIdMenu(ID_Menu);
							 commandeEdit.setPlatcmdi(Pm);
							 commandeEdit.setPlatcsoir(Ps);
							 commandeEdit.setDatecommande(Date.valueOf(Dte_c.getValue()));
							 commandeDao.updateCommande(commandeEdit);
							 IdTable.getItems().setAll(commandeDao.getAllCommandes());
							 
							 //System.out.println(ID);
                            
                        	
                           

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        IdAction.setCellFactory(cellFoctory);
        IdTable.getItems().setAll(Commandelist);

	}

}
