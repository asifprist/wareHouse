//package com.example.leadsTargetClent;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.mongodb.MongoDatabaseFactory;
//import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
//import org.springframework.stereotype.Component;
//
//import com.mongodb.ConnectionString;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//@Component
//@org.springframework.context.annotation.Configuration
//public class MongoConfigLeads extends AbstractMongoClientConfiguration {
//
//	@Value("${spring.data.mongodb.uri}")
//	private String mongoUri;
//
//	@Bean
//	public MongoDatabaseFactory mongoDbFactory(MongoClient mongoClient) {
//	    return new SimpleMongoClientDatabaseFactory(mongoClient, "test");
//	}
//
//	    @Bean
//	    public MongoClient mongoClient() {
//	        return MongoClients.create(mongoUri);
//	    }
//	    @Bean
//	    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDbFactory) {
//	        return new MongoTemplate(mongoDbFactory);
//	    }
//	    
//		@Override
//		protected String getDatabaseName() {
//			ConnectionString connectionString = new ConnectionString(mongoUri);
//			return connectionString.getDatabase();
//		}
//}
////	    @Bean
////	    public MongoDatabaseFactory mongoDbFactory(MongoClient mongoClient) {
////	        ConnectionString connectionString = new ConnectionString(mongoUri);
////	        String databaseName = connectionString.getDatabase();
////	        
////	        if (databaseName == null || databaseName.isEmpty()) {
////	            throw new IllegalArgumentException("Database name must not be empty");
////	        }
////
////	        return new SimpleMongoClientDatabaseFactory(mongoClient, databaseName);
////	    }
//
//	 
//	
////	  @Value("${spring.data.mongodb.uri}")
////	    private String mongoUri;
////
////	    @Override
////	    protected String getDatabaseName() {
////	        ConnectionString connectionString = new ConnectionString(mongoUri);
////	        return connectionString.getDatabase();
////	    }
////
////	    @Override
////	    public MongoClient mongoClient() {
////	        return MongoClients.create(mongoUri);
////	    }
////	
//
//	
//
////	
//	
////	 @Value("${spring.data.mongodb.uri}")
////	    private String mongoUri;
////
////	    @Override
////	    protected String getDatabaseName() {
////	        return MongoClients.create(mongoUri).getDatabase("targetClients").getName();
////	    }
////
////	    @Override
////	    public MongoClient mongoClient() {
////	        return MongoClients.create(mongoUri);
////	    }
//
//
