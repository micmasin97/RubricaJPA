package it.advancia.michele.pubblisher;

import javax.xml.ws.Endpoint;

import it.advancia.michele.service.ContactService;

public class Pubblisher
{
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:1244/RubricaService", new ContactService());
	}
}
