package com.zipbomb;
  
public class ZipBombTests {

  public static void main(String[] args) { 
  
    if(args.length < 1) {
      System.out.println("command usage:");
      System.out.println("java -jar ./zipbombtests.jar zipbomb.zip");
    }
    else {
      ZipBombVuln.test(args[0]);
      ZipBombGetSize.test(args[0]);
      ZipBombSafe.test(args[0]);
    }
  }
}
