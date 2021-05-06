package com.kubrick.jsbt.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author k
 * @version 1.0.0 @ClassName NioFile
 * @description: nio file
 * @date 2021/4/13 下午11:00
 */
public class NioFile {

  public static void main(String[] args) throws Exception {
    String url =
        "/Users/kubrick/Documents/workspace/java/study/jsbt/jsbt-nio/src/main/resources/data/nio-data.txt";
    RandomAccessFile aFile = new RandomAccessFile(url, "rw");
    FileChannel inChannel = aFile.getChannel();

    ByteBuffer buf = ByteBuffer.allocate(48);

    int bytesRead = inChannel.read(buf);
    while (bytesRead != -1) {

      System.out.println("Read " + bytesRead);
      buf.flip();

      while (buf.hasRemaining()) {
        System.out.print((char) buf.get());
      }

      buf.clear();
      bytesRead = inChannel.read(buf);
    }
    aFile.close();
  }
}
