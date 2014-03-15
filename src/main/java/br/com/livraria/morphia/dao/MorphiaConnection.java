package br.com.livraria.morphia.dao;

import java.net.UnknownHostException;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class MorphiaConnection {

	private static final String DB_HOST = "127.0.0.1";
	private static final String DB_NAME = "livraria";

	private static MorphiaConnection connection;

	private Mongo mongo;

	private Datastore datastore;

	private MorphiaConnection() {
		super();
	}

	public static MorphiaConnection getInstance() {
		if (connection == null) {
			connection = new MorphiaConnection();
		}
		return connection;
	}

	private Mongo mongoDB() {
		if (mongo == null) {
			try {
				mongo = new MongoClient(DB_HOST);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		return mongo;
	}

	public Datastore getDatastore() {
		if (datastore == null) {
			datastore = new Morphia().createDatastore(mongoDB(), DB_NAME);
		}
		return datastore;
	}

}
