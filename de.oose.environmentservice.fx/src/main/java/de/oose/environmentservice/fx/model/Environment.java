package de.oose.environmentservice.fx.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Environment {

	private StringProperty position = new SimpleStringProperty();
	private StringProperty heading = new SimpleStringProperty();
	private StringProperty time = new SimpleStringProperty();

	private StringProperty country = new SimpleStringProperty();
	private StringProperty language = new SimpleStringProperty();
	private StringProperty weather = new SimpleStringProperty();

	public String getPosition() {
		return position.get();
	}

	public void setPosition(String position) {
		this.position.set(position);
	}

	public String getHeading() {
		return heading.get();
	}

	public void setHeading(String heading) {
		this.heading.set(heading);
	}

	public String getTime() {
		return time.get();
	}

	public void setTime(String time) {
		this.time.set(time);
	}

	public String getCountry() {
		return country.get();
	}

	public void setCountry(String country) {
		this.country.set(country);
	}

	public String getLanguage() {
		return language.get();
	}

	public void setLanguage(String language) {
		this.language.set(language);
	}

	public String getWeather() {
		return weather.get();
	}

	public void setWeather(String weather) {
		this.weather.set(weather);
	}

}
