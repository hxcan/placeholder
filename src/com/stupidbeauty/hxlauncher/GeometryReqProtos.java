// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: GeometryReq.proto

package com.stupidbeauty.hxlauncher;

public final class GeometryReqProtos {
  private GeometryReqProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_stupidbeauty_hxlauncher_GeometryReq_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_stupidbeauty_hxlauncher_GeometryReq_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021GeometryReq.proto\022\033com.stupidbeauty.hx" +
      "launcher\"B\n\013GeometryReq\022\t\n\001x\030\001 \001(\005\022\t\n\001y\030" +
      "\002 \001(\005\022\r\n\005width\030\003 \001(\005\022\016\n\006height\030\004 \001(\005B2\n\033" +
      "com.stupidbeauty.hxlauncherB\021GeometryReq" +
      "ProtosP\001b\006proto3"
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
    internal_static_com_stupidbeauty_hxlauncher_GeometryReq_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_stupidbeauty_hxlauncher_GeometryReq_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_stupidbeauty_hxlauncher_GeometryReq_descriptor,
        new java.lang.String[] { "X", "Y", "Width", "Height", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
