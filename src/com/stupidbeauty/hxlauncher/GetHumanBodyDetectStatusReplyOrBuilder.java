// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GetHumanBodyDetectStatusReply.proto

package com.stupidbeauty.hxlauncher;

public interface GetHumanBodyDetectStatusReplyOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.stupidbeauty.hxlauncher.GetHumanBodyDetectStatusReply)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * 返回结果头部
   * </pre>
   *
   * <code>.com.stupidbeauty.hxlauncher.ReplyHeader header = 1;</code>
   */
  boolean hasHeader();
  /**
   * <pre>
   * 返回结果头部
   * </pre>
   *
   * <code>.com.stupidbeauty.hxlauncher.ReplyHeader header = 1;</code>
   */
  com.stupidbeauty.hxlauncher.ReplyHeader getHeader();
  /**
   * <pre>
   * 返回结果头部
   * </pre>
   *
   * <code>.com.stupidbeauty.hxlauncher.ReplyHeader header = 1;</code>
   */
  com.stupidbeauty.hxlauncher.ReplyHeaderOrBuilder getHeaderOrBuilder();

  /**
   * <pre>
   *返回结果的内容。原样转发给网页，由网页自行解释。
   * </pre>
   *
   * <code>string statusContent = 2;</code>
   */
  java.lang.String getStatusContent();
  /**
   * <pre>
   *返回结果的内容。原样转发给网页，由网页自行解释。
   * </pre>
   *
   * <code>string statusContent = 2;</code>
   */
  com.google.protobuf.ByteString
      getStatusContentBytes();
}
