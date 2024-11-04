package com.example.util;

import org.springframework.stereotype.Component;
@Component
public class ImageKitConfig {

//    private static final String CONFIG_FILE = "imagekit.properties";
//
//    public static Properties loadConfig() {
//        try (java.io.InputStream input = ImageKitConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
//            Properties prop = new Properties();
//            if (input != null) {
//                prop.load(input);
//            }
//            return prop;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    public static void main(String[] args) {
//        Properties imageKitConfig = loadConfig();
//
//        if (imageKitConfig != null) {
//            String urlEndpoint = imageKitConfig.getProperty("UrlEndpoint");
//            String privateKey = imageKitConfig.getProperty("PrivateKey");
//            String publicKey = imageKitConfig.getProperty("PublicKey");
//
//            System.out.println("UrlEndpoint: " + urlEndpoint);
//            System.out.println("PrivateKey: " + privateKey);
//            System.out.println("PublicKey: " + publicKey);
//        } else {
//            System.out.println("Failed to load ImageKit configuration.");
//        }
//    }
}