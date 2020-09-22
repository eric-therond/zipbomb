package com.zipbomb;

import java.lang.Exception;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.CRC32;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.net.URISyntaxException;
    
public class ZipBombSafe {

  public static void test(String filename) {
    try {
      ZipFile zipFile = new ZipFile(filename);
      Enumeration<? extends ZipEntry> entries = zipFile.entries();
        
      int totalSizeArchive = 0;
      int totalEntryArchive = 0;
        
      while(entries.hasMoreElements()) { 
        ZipEntry ze = entries.nextElement();
        System.out.println("entry = "+ze.getName());
          
        InputStream in = new BufferedInputStream(zipFile.getInputStream(ze));
        OutputStream out = new BufferedOutputStream(new FileOutputStream("./tmp/"+ze.getName()));
          
        int nBytes = -1;
        byte[] buffer = new byte[2048];
        int totalSizeEntry = 0;
          
        while((nBytes = in.read(buffer)) > 0) {
          out.write(buffer, 0, nBytes);
          totalSizeEntry += nBytes;
            
          double ratio = totalSizeEntry / ze.getCompressedSize();
          if(ratio > 100) {
            System.out.println("zip bomb attack detected = "+totalSizeEntry+" -- ratio = "+ratio);
            break;
          }
        }
      }
    }
    catch(Exception e) {
      System.out.println("exception = "+e.getMessage());
    }
  }
} 
