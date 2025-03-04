package application;
	
import java.util.List;

import Models.ConstructorResult;
import Models.DriverResult;
import Models.Season;
import Repositories.ConstructorRepository;
import Repositories.DriverResultRepository;
import Repositories.SeasonRepository;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	@Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Resultados: Conductores Y Constructores ");

        Button driverButton = new Button("Ver Resultados de Conductores");
        driverButton.setOnAction(e -> {
            DriverResultsWindow driverResultsWindow = new DriverResultsWindow();
            driverResultsWindow.show();
        });

        Button constructorButton = new Button("Ver Resultados de Constructores");
        constructorButton.setOnAction(e -> {
            ConstructorResultsWindow constructorResultsWindow = new ConstructorResultsWindow();
            constructorResultsWindow.show();
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(driverButton, constructorButton);

        Scene scene = new Scene(vbox, 500, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
	
        }
	
	
	public class DriverResultsWindow extends Stage {

	    private TableView<DriverResult> table;
	    private ComboBox<Season> yearComboBox;
	    private DriverResultRepository driverResultRepository;
	    private SeasonRepository seasonRepository;

	    public DriverResultsWindow() {
	        setTitle("Resultados Conductores");

	        driverResultRepository = new DriverResultRepository();
	        seasonRepository = new SeasonRepository();

	        // Crear columnas
	        TableColumn<DriverResult, String> driverNameColumn = new TableColumn<>("DriverName");
	        driverNameColumn.setMinWidth(200);
	        driverNameColumn.setCellValueFactory(new PropertyValueFactory<>("driverName"));

	        TableColumn<DriverResult, Integer> winsColumn = new TableColumn<>("Wins");
	        winsColumn.setMinWidth(100);
	        winsColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));

	        TableColumn<DriverResult, Integer> totalPointsColumn = new TableColumn<>("TotalPoints");
	        totalPointsColumn.setMinWidth(100);
	        totalPointsColumn.setCellValueFactory(new PropertyValueFactory<>("totalPoints"));

	        TableColumn<DriverResult, Integer> rankColumn = new TableColumn<>("Rank");
	        rankColumn.setMinWidth(100);
	        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));

	        // Crear TableView
	        table = new TableView<>();
	        table.getColumns().addAll(driverNameColumn, winsColumn, totalPointsColumn, rankColumn);

	        // Crear ComboBox
	        yearComboBox = new ComboBox<>();
	        List<Season> seasons = seasonRepository.getSeasons();
	        ObservableList<Season> observableSeasons = FXCollections.observableArrayList(seasons);
	        yearComboBox.setItems(observableSeasons);
	        yearComboBox.setConverter(new StringConverter<Season>() {
	            @Override
	            public String toString(Season season) {
	                return season != null ? String.valueOf(season.getYear()) : "";
	            }

	            @Override
	            public Season fromString(String string) {
	                return null;
	            }
	        });
	        yearComboBox.setOnAction(e -> {
	            if (yearComboBox.getValue() != null) {
	                updateTable(yearComboBox.getValue().getYear());
	            }
	        });

	        VBox vbox = new VBox();
	        vbox.getChildren().addAll(yearComboBox, table);

	        Scene scene = new Scene(vbox);
	        setScene(scene);
	    }

	    private void updateTable(int year) {
	        List<DriverResult> results = driverResultRepository.getResultByYear(year);
	        ObservableList<DriverResult> observableResults = FXCollections.observableArrayList(results);
	        table.setItems(observableResults);
	    }
	}
	
    
	public class ConstructorResultsWindow extends Stage {

	    private TableView<ConstructorResult> table;
	    private ComboBox<Season> yearComboBox;
	    private ConstructorRepository constructorRepository;
	    private SeasonRepository seasonRepository;

	    public ConstructorResultsWindow() {
	        setTitle("Resultados Constructores");

	        constructorRepository = new ConstructorRepository();
	        seasonRepository = new SeasonRepository();

	        // Crear columnas
	        TableColumn<ConstructorResult, String> constructorNameColumn = new TableColumn<>("ConstructorName");
	        constructorNameColumn.setMinWidth(200);
	        constructorNameColumn.setCellValueFactory(new PropertyValueFactory<>("constructorName"));

	        TableColumn<ConstructorResult, Integer> winsColumn = new TableColumn<>("Wins");
	        winsColumn.setMinWidth(100);
	        winsColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));

	        TableColumn<ConstructorResult, Integer> totalPointsColumn = new TableColumn<>("TotalPoints");
	        totalPointsColumn.setMinWidth(100);
	        totalPointsColumn.setCellValueFactory(new PropertyValueFactory<>("totalPoints"));

	        TableColumn<ConstructorResult, Integer> rankColumn = new TableColumn<>("Rank");
	        rankColumn.setMinWidth(100);
	        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));

	        // Crear TableView
	        table = new TableView<>();
	        table.getColumns().addAll(constructorNameColumn, winsColumn, totalPointsColumn, rankColumn);

	        // Crear ComboBox
	        yearComboBox = new ComboBox<>();
	        List<Season> seasons = seasonRepository.getSeasons();
	        ObservableList<Season> observableSeasons = FXCollections.observableArrayList(seasons);
	        yearComboBox.setItems(observableSeasons);
	        yearComboBox.setConverter(new StringConverter<Season>() {
	            @Override
	            public String toString(Season season) {
	                return season != null ? String.valueOf(season.getYear()) : "";
	            }

	            @Override
	            public Season fromString(String string) {
	                return null;
	            }
	        });
	        yearComboBox.setOnAction(e -> {
	            if (yearComboBox.getValue() != null) {
	                updateTable(yearComboBox.getValue().getYear());
	            }
	        });

	        VBox vbox = new VBox();
	        vbox.getChildren().addAll(yearComboBox, table);

	        Scene scene = new Scene(vbox);
	        setScene(scene);
	    }

	    private void updateTable(int year) {
	        List<ConstructorResult> results = constructorRepository.getResultByYear(year);
	        ObservableList<ConstructorResult> observableResults = FXCollections.observableArrayList(results);
	        table.setItems(observableResults);
	    }
	}
   
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
