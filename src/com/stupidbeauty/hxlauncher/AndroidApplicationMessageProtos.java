// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AndroidApplicationMessage.proto

package com.stupidbeauty.hxlauncher;

public final class AndroidApplicationMessageProtos {
  private AndroidApplicationMessageProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_stupidbeauty_hxlauncher_AndroidApplicationMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_stupidbeauty_hxlauncher_AndroidApplicationMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\037AndroidApplicationMessage.proto\022\033com.s" +
      "tupidbeauty.hxlauncher\032\024SetLanguageReq.p" +
      "roto\032\037ReportAudioSoundSourceReq.proto\032*S" +
      "et3DEngineCameraPositionRotationReq.prot" +
      "o\"\372\003\n\031AndroidApplicationMessage\022\023\n\013packa" +
      "geName\030\001 \001(\t\022\021\n\tclassName\030\002 \001(\t\022[\n\rcompo" +
      "nentType\030\003 \001(\0162D.com.stupidbeauty.hxlaun" +
      "cher.AndroidApplicationMessage.Component" +
      "Type\022G\n\022setLanguageMessage\030\004 \001(\0132+.com.s" +
      "tupidbeauty.hxlauncher.SetLanguageReq\022`\n" +
      " reportAudioSoundSourceReqMessage\030\006 \001(\0132" +
      "6.com.stupidbeauty.hxlauncher.ReportAudi" +
      "oSoundSourceReq\022s\n(set3DEngineCameraPosi" +
      "tionRotationMessage\030\010 \001(\0132A.com.stupidbe" +
      "auty.hxlauncher.Set3DEngineCameraPositio" +
      "nRotationReq\"8\n\rComponentType\022\014\n\010ACTIVIT" +
      "Y\020\000\022\013\n\007SERVICE\020\001\022\014\n\010NoLaunch\020\002B@\n\033com.st" +
      "upidbeauty.hxlauncherB\037AndroidApplicatio" +
      "nMessageProtosP\001b\006proto3"
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
          com.stupidbeauty.hxlauncher.SetLanguageReqProtos.getDescriptor(),
          com.stupidbeauty.hxlauncher.ReportAudioSoundSourceReqProtos.getDescriptor(),
          com.stupidbeauty.hxlauncher.Set3DEngineCameraPositionRotationReqProtos.getDescriptor(),
        }, assigner);
    internal_static_com_stupidbeauty_hxlauncher_AndroidApplicationMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_stupidbeauty_hxlauncher_AndroidApplicationMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_stupidbeauty_hxlauncher_AndroidApplicationMessage_descriptor,
        new java.lang.String[] { "PackageName", "ClassName", "ComponentType", "SetLanguageMessage", "ReportAudioSoundSourceReqMessage", "Set3DEngineCameraPositionRotationMessage", });
    com.stupidbeauty.hxlauncher.SetLanguageReqProtos.getDescriptor();
    com.stupidbeauty.hxlauncher.ReportAudioSoundSourceReqProtos.getDescriptor();
    com.stupidbeauty.hxlauncher.Set3DEngineCameraPositionRotationReqProtos.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
