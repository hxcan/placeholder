// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AccurateVoiceCommandReq.proto

package com.stupidbeauty.hxlauncher;

public interface AccurateVoiceCommandReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.stupidbeauty.hxlauncher.AccurateVoiceCommandReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *识别出来的文字。
   * </pre>
   *
   * <code>string text = 1;</code>
   */
  java.lang.String getText();
  /**
   * <pre>
   *识别出来的文字。
   * </pre>
   *
   * <code>string text = 1;</code>
   */
  com.google.protobuf.ByteString
      getTextBytes();

  /**
   * <pre>
   *精确语音指令代码。
   * </pre>
   *
   * <code>int32 voiceCommand = 2;</code>
   */
  int getVoiceCommand();
}
