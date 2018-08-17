package de.oose.environmentservice.fx.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import org.springframework.beans.factory.annotation.Autowired;

import de.oose.environmentservice.Environment;
import de.oose.environmentservice.fx.model.EnvironmentServiceClient;
import de.oose.environmentservice.fx.model.LocationServiceClient;
import de.oose.locationservice.Location;

public class EnvironmentViewController {

    @FXML
    private Label positionLabel;
    @FXML
    private Label headingLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label countryLabel;
    @FXML
    private Label languageLabel;
    @FXML
    private Label weatherLabel;

    @Autowired
    private LocationServiceClient locationClient;

    @Autowired
    private EnvironmentServiceClient environmentClient;

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public EnvironmentViewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    public void refresh() {

        Location location = locationClient.getLocationFromService();

        Environment environment =
                location == null ? null : environmentClient
                        .geEnvironmentFromService(location.getPosition());

        if (location != null) {
            positionLabel.setText(location.getPosition().toString());
            headingLabel.setText(location.getHeading() + "");
            timeLabel.setText(location.getTime().toString());

            if (environment != null) {

                countryLabel.setText(environment.getCountry());
                languageLabel.setText(environment.getLanguage());
                weatherLabel.setText(environment.getWeather());

            }
        }
    }
}
