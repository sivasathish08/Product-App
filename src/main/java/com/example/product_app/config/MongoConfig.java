package com.example.product_app.config;

import com.example.product_app.product.ProductRepository;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = ProductRepository.class)
public class MongoConfig extends AbstractMongoClientConfiguration {

    private final String mongoUri;

    public MongoConfig(@Value("${app.mongodb.uri}") String mongoUri) {
        this.mongoUri = mongoUri;
    }

    @Override
    protected String getDatabaseName() {
        ConnectionString connectionString = new ConnectionString(mongoUri);
        return connectionString.getDatabase();
    }

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create(mongoUri);
    }
}
