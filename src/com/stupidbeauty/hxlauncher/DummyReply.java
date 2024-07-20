// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: DummyReply.proto

package com.stupidbeauty.hxlauncher;

/**
 * Protobuf type {@code com.stupidbeauty.hxlauncher.DummyReply}
 */
public  final class DummyReply extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.stupidbeauty.hxlauncher.DummyReply)
    DummyReplyOrBuilder {
private static final long serialVersionUID = 0L;
  // Use DummyReply.newBuilder() to construct.
  private DummyReply(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private DummyReply() {
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private DummyReply(
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
            com.stupidbeauty.hxlauncher.ReplyHeader.Builder subBuilder = null;
            if (header_ != null) {
              subBuilder = header_.toBuilder();
            }
            header_ = input.readMessage(com.stupidbeauty.hxlauncher.ReplyHeader.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(header_);
              header_ = subBuilder.buildPartial();
            }

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
    return com.stupidbeauty.hxlauncher.DummyReplyProtos.internal_static_com_stupidbeauty_hxlauncher_DummyReply_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.stupidbeauty.hxlauncher.DummyReplyProtos.internal_static_com_stupidbeauty_hxlauncher_DummyReply_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.stupidbeauty.hxlauncher.DummyReply.class, com.stupidbeauty.hxlauncher.DummyReply.Builder.class);
  }

  public static final int HEADER_FIELD_NUMBER = 1;
  private com.stupidbeauty.hxlauncher.ReplyHeader header_;
  /**
   * <pre>
   * 返回结果头部
   * </pre>
   *
   * <code>.com.stupidbeauty.hxlauncher.ReplyHeader header = 1;</code>
   */
  public boolean hasHeader() {
    return header_ != null;
  }
  /**
   * <pre>
   * 返回结果头部
   * </pre>
   *
   * <code>.com.stupidbeauty.hxlauncher.ReplyHeader header = 1;</code>
   */
  public com.stupidbeauty.hxlauncher.ReplyHeader getHeader() {
    return header_ == null ? com.stupidbeauty.hxlauncher.ReplyHeader.getDefaultInstance() : header_;
  }
  /**
   * <pre>
   * 返回结果头部
   * </pre>
   *
   * <code>.com.stupidbeauty.hxlauncher.ReplyHeader header = 1;</code>
   */
  public com.stupidbeauty.hxlauncher.ReplyHeaderOrBuilder getHeaderOrBuilder() {
    return getHeader();
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
    if (header_ != null) {
      output.writeMessage(1, getHeader());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (header_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getHeader());
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
    if (!(obj instanceof com.stupidbeauty.hxlauncher.DummyReply)) {
      return super.equals(obj);
    }
    com.stupidbeauty.hxlauncher.DummyReply other = (com.stupidbeauty.hxlauncher.DummyReply) obj;

    boolean result = true;
    result = result && (hasHeader() == other.hasHeader());
    if (hasHeader()) {
      result = result && getHeader()
          .equals(other.getHeader());
    }
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
    if (hasHeader()) {
      hash = (37 * hash) + HEADER_FIELD_NUMBER;
      hash = (53 * hash) + getHeader().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.stupidbeauty.hxlauncher.DummyReply parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.stupidbeauty.hxlauncher.DummyReply parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.stupidbeauty.hxlauncher.DummyReply parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.stupidbeauty.hxlauncher.DummyReply parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.stupidbeauty.hxlauncher.DummyReply parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.stupidbeauty.hxlauncher.DummyReply parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.stupidbeauty.hxlauncher.DummyReply parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.stupidbeauty.hxlauncher.DummyReply parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.stupidbeauty.hxlauncher.DummyReply parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.stupidbeauty.hxlauncher.DummyReply parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.stupidbeauty.hxlauncher.DummyReply parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.stupidbeauty.hxlauncher.DummyReply parseFrom(
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
  public static Builder newBuilder(com.stupidbeauty.hxlauncher.DummyReply prototype) {
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
   * Protobuf type {@code com.stupidbeauty.hxlauncher.DummyReply}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.stupidbeauty.hxlauncher.DummyReply)
      com.stupidbeauty.hxlauncher.DummyReplyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.stupidbeauty.hxlauncher.DummyReplyProtos.internal_static_com_stupidbeauty_hxlauncher_DummyReply_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.stupidbeauty.hxlauncher.DummyReplyProtos.internal_static_com_stupidbeauty_hxlauncher_DummyReply_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.stupidbeauty.hxlauncher.DummyReply.class, com.stupidbeauty.hxlauncher.DummyReply.Builder.class);
    }

    // Construct using com.stupidbeauty.hxlauncher.DummyReply.newBuilder()
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
      if (headerBuilder_ == null) {
        header_ = null;
      } else {
        header_ = null;
        headerBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.stupidbeauty.hxlauncher.DummyReplyProtos.internal_static_com_stupidbeauty_hxlauncher_DummyReply_descriptor;
    }

    @java.lang.Override
    public com.stupidbeauty.hxlauncher.DummyReply getDefaultInstanceForType() {
      return com.stupidbeauty.hxlauncher.DummyReply.getDefaultInstance();
    }

    @java.lang.Override
    public com.stupidbeauty.hxlauncher.DummyReply build() {
      com.stupidbeauty.hxlauncher.DummyReply result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.stupidbeauty.hxlauncher.DummyReply buildPartial() {
      com.stupidbeauty.hxlauncher.DummyReply result = new com.stupidbeauty.hxlauncher.DummyReply(this);
      if (headerBuilder_ == null) {
        result.header_ = header_;
      } else {
        result.header_ = headerBuilder_.build();
      }
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
      if (other instanceof com.stupidbeauty.hxlauncher.DummyReply) {
        return mergeFrom((com.stupidbeauty.hxlauncher.DummyReply)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.stupidbeauty.hxlauncher.DummyReply other) {
      if (other == com.stupidbeauty.hxlauncher.DummyReply.getDefaultInstance()) return this;
      if (other.hasHeader()) {
        mergeHeader(other.getHeader());
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
      com.stupidbeauty.hxlauncher.DummyReply parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.stupidbeauty.hxlauncher.DummyReply) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.stupidbeauty.hxlauncher.ReplyHeader header_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.stupidbeauty.hxlauncher.ReplyHeader, com.stupidbeauty.hxlauncher.ReplyHeader.Builder, com.stupidbeauty.hxlauncher.ReplyHeaderOrBuilder> headerBuilder_;
    /**
     * <pre>
     * 返回结果头部
     * </pre>
     *
     * <code>.com.stupidbeauty.hxlauncher.ReplyHeader header = 1;</code>
     */
    public boolean hasHeader() {
      return headerBuilder_ != null || header_ != null;
    }
    /**
     * <pre>
     * 返回结果头部
     * </pre>
     *
     * <code>.com.stupidbeauty.hxlauncher.ReplyHeader header = 1;</code>
     */
    public com.stupidbeauty.hxlauncher.ReplyHeader getHeader() {
      if (headerBuilder_ == null) {
        return header_ == null ? com.stupidbeauty.hxlauncher.ReplyHeader.getDefaultInstance() : header_;
      } else {
        return headerBuilder_.getMessage();
      }
    }
    /**
     * <pre>
     * 返回结果头部
     * </pre>
     *
     * <code>.com.stupidbeauty.hxlauncher.ReplyHeader header = 1;</code>
     */
    public Builder setHeader(com.stupidbeauty.hxlauncher.ReplyHeader value) {
      if (headerBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        header_ = value;
        onChanged();
      } else {
        headerBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <pre>
     * 返回结果头部
     * </pre>
     *
     * <code>.com.stupidbeauty.hxlauncher.ReplyHeader header = 1;</code>
     */
    public Builder setHeader(
        com.stupidbeauty.hxlauncher.ReplyHeader.Builder builderForValue) {
      if (headerBuilder_ == null) {
        header_ = builderForValue.build();
        onChanged();
      } else {
        headerBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <pre>
     * 返回结果头部
     * </pre>
     *
     * <code>.com.stupidbeauty.hxlauncher.ReplyHeader header = 1;</code>
     */
    public Builder mergeHeader(com.stupidbeauty.hxlauncher.ReplyHeader value) {
      if (headerBuilder_ == null) {
        if (header_ != null) {
          header_ =
            com.stupidbeauty.hxlauncher.ReplyHeader.newBuilder(header_).mergeFrom(value).buildPartial();
        } else {
          header_ = value;
        }
        onChanged();
      } else {
        headerBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <pre>
     * 返回结果头部
     * </pre>
     *
     * <code>.com.stupidbeauty.hxlauncher.ReplyHeader header = 1;</code>
     */
    public Builder clearHeader() {
      if (headerBuilder_ == null) {
        header_ = null;
        onChanged();
      } else {
        header_ = null;
        headerBuilder_ = null;
      }

      return this;
    }
    /**
     * <pre>
     * 返回结果头部
     * </pre>
     *
     * <code>.com.stupidbeauty.hxlauncher.ReplyHeader header = 1;</code>
     */
    public com.stupidbeauty.hxlauncher.ReplyHeader.Builder getHeaderBuilder() {
      
      onChanged();
      return getHeaderFieldBuilder().getBuilder();
    }
    /**
     * <pre>
     * 返回结果头部
     * </pre>
     *
     * <code>.com.stupidbeauty.hxlauncher.ReplyHeader header = 1;</code>
     */
    public com.stupidbeauty.hxlauncher.ReplyHeaderOrBuilder getHeaderOrBuilder() {
      if (headerBuilder_ != null) {
        return headerBuilder_.getMessageOrBuilder();
      } else {
        return header_ == null ?
            com.stupidbeauty.hxlauncher.ReplyHeader.getDefaultInstance() : header_;
      }
    }
    /**
     * <pre>
     * 返回结果头部
     * </pre>
     *
     * <code>.com.stupidbeauty.hxlauncher.ReplyHeader header = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.stupidbeauty.hxlauncher.ReplyHeader, com.stupidbeauty.hxlauncher.ReplyHeader.Builder, com.stupidbeauty.hxlauncher.ReplyHeaderOrBuilder> 
        getHeaderFieldBuilder() {
      if (headerBuilder_ == null) {
        headerBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.stupidbeauty.hxlauncher.ReplyHeader, com.stupidbeauty.hxlauncher.ReplyHeader.Builder, com.stupidbeauty.hxlauncher.ReplyHeaderOrBuilder>(
                getHeader(),
                getParentForChildren(),
                isClean());
        header_ = null;
      }
      return headerBuilder_;
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


    // @@protoc_insertion_point(builder_scope:com.stupidbeauty.hxlauncher.DummyReply)
  }

  // @@protoc_insertion_point(class_scope:com.stupidbeauty.hxlauncher.DummyReply)
  private static final com.stupidbeauty.hxlauncher.DummyReply DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.stupidbeauty.hxlauncher.DummyReply();
  }

  public static com.stupidbeauty.hxlauncher.DummyReply getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<DummyReply>
      PARSER = new com.google.protobuf.AbstractParser<DummyReply>() {
    @java.lang.Override
    public DummyReply parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new DummyReply(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<DummyReply> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<DummyReply> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.stupidbeauty.hxlauncher.DummyReply getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

