package com.saucesubfresh.admin.common.crypto;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Base64 能够将任何数据转换为易移植的字符串，避免了传输过程中失真问题。
 * 需要注意的是，Base 64不是一种加密方式，只是一种编码方式。很多时候，我们都将Base64编码作为数据加密后的传输 / 存储格式
 * @author: 李俊平
 * @Date: 2021-06-06 11:03
 */
public class Base64Util {

  private static Base64.Encoder encoder = Base64.getEncoder();
  private static Base64.Decoder decoder = Base64.getDecoder();

  /**
   * Base64编码，byte[] 转 String
   * @param bytes byte[]
   * @return 字符串
   */
  public static String encodeBytesToString(byte[] bytes){
    return encoder.encodeToString(bytes);
  }

  /**
   * Base64解码，String 转 byte[]
   * @param text 字符串
   * @return byte[]
   */
  public static byte[] decodeStringToBytes(String text){
    return decoder.decode(text);
  }

  /**
   * Base64编码，String 转 String
   * @param text 字符串
   * @return Base64格式字符串
   */
  public static String encode(String text){
    return encoder.encodeToString(text.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * Base64解码，String 转 String
   * @param base64Text Base64格式字符串
   * @return 字符串
   */
  public static String decode(String base64Text){
    return new String(decoder.decode(base64Text), StandardCharsets.UTF_8);
  }
}
