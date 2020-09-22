package com.zipbomb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.CRC32;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.Path;
import java.net.URISyntaxException;
    
public class ZipBombVuln {

  public static void test(String filename) {
  
    try {
      ZipFile zipFile = new ZipFile(filename);
      Enumeration<? extends ZipEntry> entries = zipFile.entries();
        
      while(entries.hasMoreElements()) {
        ZipEntry ze = entries.nextElement();
        System.out.println("entry = "+ze.getName());
        InputStream initialStream = zipFile.getInputStream(ze);
          
        File f1 = new File("./tmp/"+ze.getName());
        Path p1 = f1.toPath();
          
        if(!f1.isDirectory()) {
          Files.copy(initialStream, p1, StandardCopyOption.REPLACE_EXISTING);
        }
      }
    }
    catch(IOException e) {
      System.out.println("exception = "+e.getMessage());
    }
  }
} 
