package de.oose.environmentservice.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import de.oose.environmentservice.fx.spring.SpringFxmlLoader;

public class ShowEnvironment extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private Stage primaryStage;
	private BorderPane rootLayout;
	private SpringFxmlLoader loader = new SpringFxmlLoader(
			"classpath:/applicationContext.xml");

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("ShowEnvironment");

		initRootLayout();
		showEnvironment();
	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {

		rootLayout = (BorderPane) loader.load("/RootLayout.fxml");
		

		// Show the scene containing the root layout.
		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Shows the person overview inside the root layout.
	 */
	public void showEnvironment() {

		AnchorPane environmentView = (AnchorPane) loader
				.load("/EnvironmentView.fxml");
		
		// Set person overview into the center of root layout.
		rootLayout.setCenter(environmentView);

	}
}
