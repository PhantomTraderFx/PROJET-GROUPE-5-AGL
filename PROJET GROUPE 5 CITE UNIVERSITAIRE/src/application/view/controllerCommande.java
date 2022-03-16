package application.view;
import java.net.URL;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.G5.dao.CommandeDao;
import com.G5.dao.EtudiantDao;
import com.G5.dao.MenuDao;
import com.G5.model.Commande;
import com.G5.model.Etudiant;
import com.G5.model.Menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
    
    MenuDao menuDao = new MenuDao();
    Menu menu = new Menu();
    EtudiantDao etudiantDao = new EtudiantDao();
    Etudiant etudiant = new Etudiant();
    CommandeDao commandeDao = new CommandeDao();

    String Pm;
    String Ps;
    int ID_Menu;
    String JourC;

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
    	
    }

    @FXML
    void SaveToTable(MouseEvent event) {
    	Commande commande = new Commande();
    	commande.setIdEtu(this.ID);
    	commande.setDatecommande(Date.valueOf(Dte_c.getValue()));
    	commande.setPlatcmdi(this.Pm);
    	commande.setPlatcsoir(this.Ps);
    	commande.setIdMenu(this.ID_Menu);
    	commandeDao.saveCommande(commande);
    	Commandelist.add(commande);
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
