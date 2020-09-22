package com.zipbomb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.Enumeration;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.Path;
import java.net.URISyntaxException;
    
public class ZipBombGetSize {

  public static void test(String filename) {
    try {
      final int MAX_SIZE_DIFF = 10;
      
      ZipFile zipFile = new ZipFile(filename);
      Enumeration<? extends ZipEntry> entries = zipFile.entries();
        
      while(entries.hasMoreElements()) {
        ZipEntry entry = entries.nextElement();
          
        long compressedSize = entry.getCompressedSize();
        long uncompressedSize = entry.getSize();
          
        if (compressedSize < 0 || uncompressedSize < 0) {
          System.out.println("Zip bomb attack detected, invalid sizes: compressed "+compressedSize+", uncompressed "+uncompressedSize+", name "+entry.getName());
          break;
        }
          
        if (compressedSize * MAX_SIZE_DIFF < uncompressedSize) {
          System.out.println("Zip bomb attack detected, invalid sizes: compressed "+compressedSize+", uncompressed "+uncompressedSize+", name "+entry.getName());
          break;
        }
          
        System.out.println("entry = "+entry.getName());
        InputStream initialStream = zipFile.getInputStream(entry);
          
        File f1 = new File("./tmp/"+entry.getName());
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
