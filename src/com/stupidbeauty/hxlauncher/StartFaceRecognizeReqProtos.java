// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: StartFaceRecognizeReq.proto

package com.stupidbeauty.hxlauncher;

public final class StartFaceRecognizeReqProtos {
  private StartFaceRecognizeReqProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_stupidbeauty_hxlauncher_StartFaceRecognizeReq_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_stupidbeauty_hxlauncher_StartFaceRecognizeReq_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\033StartFaceRecognizeReq.proto\022\033com.stupi" +
      "dbeauty.hxlauncher\"K\n\025StartFaceRecognize" +
      "Req\022\025\n\rmaxFaceAmount\030\001 \001(\005\022\033\n\023detectMaxF" +
      "aceAmount\030\002 \001(\005B<\n\033com.stupidbeauty.hxla" +
      "uncherB\033StartFaceRecognizeReqProtosP\001b\006p" +
      "roto3"
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
    internal_static_com_stupidbeauty_hxlauncher_StartFaceRecognizeReq_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_stupidbeauty_hxlauncher_StartFaceRecognizeReq_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_stupidbeauty_hxlauncher_StartFaceRecognizeReq_descriptor,
        new java.lang.String[] { "MaxFaceAmount", "DetectMaxFaceAmount", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
