// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: OnlineTtsReq.proto

package com.stupidbeauty.hxlauncher;

public interface OnlineTtsReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.stupidbeauty.hxlauncher.OnlineTtsReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *要显示的文字。
   * </pre>
   *
   * <code>string text = 1;</code>
   */
  java.lang.String getText();
  /**
   * <pre>
   *要显示的文字。
   * </pre>
   *
   * <code>string text = 1;</code>
   */
  com.google.protobuf.ByteString
      getTextBytes();

  /**
   * <pre>
   *语音事务编号。
   * </pre>
   *
   * <code>string voiceTransactionId = 4;</code>
   */
  java.lang.String getVoiceTransactionId();
  /**
   * <pre>
   *语音事务编号。
   * </pre>
   *
   * <code>string voiceTransactionId = 4;</code>
   */
  com.google.protobuf.ByteString
      getVoiceTransactionIdBytes();

  /**
   * <pre>
   *发起此请求的客户端的IP。
   * </pre>
   *
   * <code>string clientIp = 5;</code>
   */
  java.lang.String getClientIp();
  /**
   * <pre>
   *发起此请求的客户端的IP。
   * </pre>
   *
   * <code>string clientIp = 5;</code>
   */
  com.google.protobuf.ByteString
      getClientIpBytes();
}
