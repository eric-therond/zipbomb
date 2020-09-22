package com.zipbomb;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
    
public class FileResourcesUtils {

  public static File getFileFromResource(String fileName) throws URISyntaxException {
  
    InputStream in = FileResourcesUtils.class.getClassLoader().getResourceAsStream(fileName);
    //URL resource = classLoader.getResource(fileName);
    URL resource = FileResourcesUtils.class.getResource(fileName);
    //System.out.println("fileName = "+fileName+" -- resource.toURI() = "+resource.toURI());
    if (resource == null) {
      throw new IllegalArgumentException("file "+fileName+" doesn't exist");
    } else {
      System.out.println("fileName = "+fileName+" -- resource.toURI() = "+resource.toURI());
      return new File(resource.toURI());
    }
  }
} 
