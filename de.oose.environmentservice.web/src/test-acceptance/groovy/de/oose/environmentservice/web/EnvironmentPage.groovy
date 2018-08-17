package de.oose.environmentservice.web

import org.springframework.beans.factory.annotation.Value;

import geb.Page



class EnvironmentPage extends Page{

	
	// we do not use this to keep the port dynamic
	//static url = "localhost:8090/view"
		
	// assert that the driver is on the correct page
	static at = { title == "Environment"}
	
	static content = {
		textfeld {identifier -> $("p", id: identifier)}
		position {$("p#position")}
		country  {$("p#country")}
	}
}
