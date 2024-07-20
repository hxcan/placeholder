// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: SetVoiceRecognizeEngineReq.proto

package com.stupidbeauty.hxlauncher;

/**
 * Protobuf type {@code com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq}
 */
public  final class SetVoiceRecognizeEngineReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq)
    SetVoiceRecognizeEngineReqOrBuilder {
private static final long serialVersionUID = 0L;
  // Use SetVoiceRecognizeEngineReq.newBuilder() to construct.
  private SetVoiceRecognizeEngineReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SetVoiceRecognizeEngineReq() {
    languageId_ = "";
    voiceRecognizeEngineName_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SetVoiceRecognizeEngineReq(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            languageId_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            voiceRecognizeEngineName_ = s;
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReqProtos.internal_static_com_stupidbeauty_hxlauncher_SetVoiceRecognizeEngineReq_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReqProtos.internal_static_com_stupidbeauty_hxlauncher_SetVoiceRecognizeEngineReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq.class, com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq.Builder.class);
  }

  public static final int LANGUAGEID_FIELD_NUMBER = 1;
  private volatile java.lang.Object languageId_;
  /**
   * <pre>
   *语言编号。
   * </pre>
   *
   * <code>string languageId = 1;</code>
   */
  public java.lang.String getLanguageId() {
    java.lang.Object ref = languageId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      languageId_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *语言编号。
   * </pre>
   *
   * <code>string languageId = 1;</code>
   */
  public com.google.protobuf.ByteString
      getLanguageIdBytes() {
    java.lang.Object ref = languageId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      languageId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int VOICERECOGNIZEENGINENAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object voiceRecognizeEngineName_;
  /**
   * <pre>
   *语音识别引擎名字。
   * </pre>
   *
   * <code>string voiceRecognizeEngineName = 2;</code>
   */
  public java.lang.String getVoiceRecognizeEngineName() {
    java.lang.Object ref = voiceRecognizeEngineName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      voiceRecognizeEngineName_ = s;
      return s;
    }
  }
  /**
   * <pre>
   *语音识别引擎名字。
   * </pre>
   *
   * <code>string voiceRecognizeEngineName = 2;</code>
   */
  public com.google.protobuf.ByteString
      getVoiceRecognizeEngineNameBytes() {
    java.lang.Object ref = voiceRecognizeEngineName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      voiceRecognizeEngineName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getLanguageIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, languageId_);
    }
    if (!getVoiceRecognizeEngineNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, voiceRecognizeEngineName_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getLanguageIdBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, languageId_);
    }
    if (!getVoiceRecognizeEngineNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, voiceRecognizeEngineName_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq)) {
      return super.equals(obj);
    }
    com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq other = (com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq) obj;

    boolean result = true;
    result = result && getLanguageId()
        .equals(other.getLanguageId());
    result = result && getVoiceRecognizeEngineName()
        .equals(other.getVoiceRecognizeEngineName());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + LANGUAGEID_FIELD_NUMBER;
    hash = (53 * hash) + getLanguageId().hashCode();
    hash = (37 * hash) + VOICERECOGNIZEENGINENAME_FIELD_NUMBER;
    hash = (53 * hash) + getVoiceRecognizeEngineName().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq)
      com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReqProtos.internal_static_com_stupidbeauty_hxlauncher_SetVoiceRecognizeEngineReq_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReqProtos.internal_static_com_stupidbeauty_hxlauncher_SetVoiceRecognizeEngineReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq.class, com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq.Builder.class);
    }

    // Construct using com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      languageId_ = "";

      voiceRecognizeEngineName_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReqProtos.internal_static_com_stupidbeauty_hxlauncher_SetVoiceRecognizeEngineReq_descriptor;
    }

    @java.lang.Override
    public com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq getDefaultInstanceForType() {
      return com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq.getDefaultInstance();
    }

    @java.lang.Override
    public com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq build() {
      com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq buildPartial() {
      com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq result = new com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq(this);
      result.languageId_ = languageId_;
      result.voiceRecognizeEngineName_ = voiceRecognizeEngineName_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq) {
        return mergeFrom((com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq other) {
      if (other == com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq.getDefaultInstance()) return this;
      if (!other.getLanguageId().isEmpty()) {
        languageId_ = other.languageId_;
        onChanged();
      }
      if (!other.getVoiceRecognizeEngineName().isEmpty()) {
        voiceRecognizeEngineName_ = other.voiceRecognizeEngineName_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object languageId_ = "";
    /**
     * <pre>
     *语言编号。
     * </pre>
     *
     * <code>string languageId = 1;</code>
     */
    public java.lang.String getLanguageId() {
      java.lang.Object ref = languageId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        languageId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *语言编号。
     * </pre>
     *
     * <code>string languageId = 1;</code>
     */
    public com.google.protobuf.ByteString
        getLanguageIdBytes() {
      java.lang.Object ref = languageId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        languageId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *语言编号。
     * </pre>
     *
     * <code>string languageId = 1;</code>
     */
    public Builder setLanguageId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      languageId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *语言编号。
     * </pre>
     *
     * <code>string languageId = 1;</code>
     */
    public Builder clearLanguageId() {
      
      languageId_ = getDefaultInstance().getLanguageId();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *语言编号。
     * </pre>
     *
     * <code>string languageId = 1;</code>
     */
    public Builder setLanguageIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      languageId_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object voiceRecognizeEngineName_ = "";
    /**
     * <pre>
     *语音识别引擎名字。
     * </pre>
     *
     * <code>string voiceRecognizeEngineName = 2;</code>
     */
    public java.lang.String getVoiceRecognizeEngineName() {
      java.lang.Object ref = voiceRecognizeEngineName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        voiceRecognizeEngineName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     *语音识别引擎名字。
     * </pre>
     *
     * <code>string voiceRecognizeEngineName = 2;</code>
     */
    public com.google.protobuf.ByteString
        getVoiceRecognizeEngineNameBytes() {
      java.lang.Object ref = voiceRecognizeEngineName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        voiceRecognizeEngineName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     *语音识别引擎名字。
     * </pre>
     *
     * <code>string voiceRecognizeEngineName = 2;</code>
     */
    public Builder setVoiceRecognizeEngineName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      voiceRecognizeEngineName_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *语音识别引擎名字。
     * </pre>
     *
     * <code>string voiceRecognizeEngineName = 2;</code>
     */
    public Builder clearVoiceRecognizeEngineName() {
      
      voiceRecognizeEngineName_ = getDefaultInstance().getVoiceRecognizeEngineName();
      onChanged();
      return this;
    }
    /**
     * <pre>
     *语音识别引擎名字。
     * </pre>
     *
     * <code>string voiceRecognizeEngineName = 2;</code>
     */
    public Builder setVoiceRecognizeEngineNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      voiceRecognizeEngineName_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq)
  }

  // @@protoc_insertion_point(class_scope:com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq)
  private static final com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq();
  }

  public static com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SetVoiceRecognizeEngineReq>
      PARSER = new com.google.protobuf.AbstractParser<SetVoiceRecognizeEngineReq>() {
    @java.lang.Override
    public SetVoiceRecognizeEngineReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new SetVoiceRecognizeEngineReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SetVoiceRecognizeEngineReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SetVoiceRecognizeEngineReq> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.stupidbeauty.hxlauncher.SetVoiceRecognizeEngineReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

