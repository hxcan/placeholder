// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MonitorSubSystemMessage.proto

package com.stupidbeauty.hxlauncher;

public final class MonitorSubSystemMessageProtos {
  private MonitorSubSystemMessageProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_stupidbeauty_hxlauncher_MonitorSubSystemMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_stupidbeauty_hxlauncher_MonitorSubSystemMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\035MonitorSubSystemMessage.proto\022\033com.stu" +
      "pidbeauty.hxlauncher\"Z\n\027MonitorSubSystem" +
      "Message\022\031\n\021monitorServiceUrl\030\001 \001(\t\022\017\n\007ro" +
      "botId\030\002 \001(\t\022\023\n\013machineCode\030\003 \001(\tB>\n\033com." +
      "stupidbeauty.hxlauncherB\035MonitorSubSyste" +
      "mMessageProtosP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_com_stupidbeauty_hxlauncher_MonitorSubSystemMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_stupidbeauty_hxlauncher_MonitorSubSystemMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_stupidbeauty_hxlauncher_MonitorSubSystemMessage_descriptor,
        new java.lang.String[] { "MonitorServiceUrl", "RobotId", "MachineCode", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
