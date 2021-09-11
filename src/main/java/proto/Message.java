// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message.proto

package proto;

/**
 * Protobuf type {@code tutorial.Message}
 */
public  final class Message extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:tutorial.Message)
    MessageOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Message.newBuilder() to construct.
  private Message(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Message() {
    messageType_ = 0;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Message();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Message(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
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
          case 8: {

            id_ = input.readInt32();
            break;
          }
          case 16: {

            dest_ = input.readInt32();
            break;
          }
          case 24: {
            int rawValue = input.readEnum();

            messageType_ = rawValue;
            break;
          }
          case 34: {
            proto.JoinPayload.Builder subBuilder = null;
            if (payloadCase_ == 4) {
              subBuilder = ((proto.JoinPayload) payload_).toBuilder();
            }
            payload_ =
                input.readMessage(proto.JoinPayload.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom((proto.JoinPayload) payload_);
              payload_ = subBuilder.buildPartial();
            }
            payloadCase_ = 4;
            break;
          }
          case 42: {
            java.lang.String s = input.readStringRequireUtf8();
            payloadCase_ = 5;
            payload_ = s;
            break;
          }
          case 50: {
            payloadCase_ = 6;
            payload_ = input.readBytes();
            break;
          }
          default: {
            if (!parseUnknownField(
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
    return proto.MessageOuterClass.internal_static_tutorial_Message_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return proto.MessageOuterClass.internal_static_tutorial_Message_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            proto.Message.class, proto.Message.Builder.class);
  }

  private int payloadCase_ = 0;
  private java.lang.Object payload_;
  public enum PayloadCase
      implements com.google.protobuf.Internal.EnumLite {
    JOINPAYLOAD(4),
    TEXTPAYLOAD(5),
    BYTESPAYLOAD(6),
    PAYLOAD_NOT_SET(0);
    private final int value;
    private PayloadCase(int value) {
      this.value = value;
    }
    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static PayloadCase valueOf(int value) {
      return forNumber(value);
    }

    public static PayloadCase forNumber(int value) {
      switch (value) {
        case 4: return JOINPAYLOAD;
        case 5: return TEXTPAYLOAD;
        case 6: return BYTESPAYLOAD;
        case 0: return PAYLOAD_NOT_SET;
        default: return null;
      }
    }
    public int getNumber() {
      return this.value;
    }
  };

  public PayloadCase
  getPayloadCase() {
    return PayloadCase.forNumber(
        payloadCase_);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private int id_;
  /**
   * <code>int32 id = 1;</code>
   */
  public int getId() {
    return id_;
  }

  public static final int DEST_FIELD_NUMBER = 2;
  private int dest_;
  /**
   * <code>int32 dest = 2;</code>
   */
  public int getDest() {
    return dest_;
  }

  public static final int MESSAGETYPE_FIELD_NUMBER = 3;
  private int messageType_;
  /**
   * <code>.tutorial.MessageType messageType = 3;</code>
   */
  public int getMessageTypeValue() {
    return messageType_;
  }
  /**
   * <code>.tutorial.MessageType messageType = 3;</code>
   */
  public proto.MessageType getMessageType() {
    @SuppressWarnings("deprecation")
    proto.MessageType result = proto.MessageType.valueOf(messageType_);
    return result == null ? proto.MessageType.UNRECOGNIZED : result;
  }

  public static final int JOINPAYLOAD_FIELD_NUMBER = 4;
  /**
   * <code>.tutorial.JoinPayload joinPayload = 4;</code>
   */
  public boolean hasJoinPayload() {
    return payloadCase_ == 4;
  }
  /**
   * <code>.tutorial.JoinPayload joinPayload = 4;</code>
   */
  public proto.JoinPayload getJoinPayload() {
    if (payloadCase_ == 4) {
       return (proto.JoinPayload) payload_;
    }
    return proto.JoinPayload.getDefaultInstance();
  }
  /**
   * <code>.tutorial.JoinPayload joinPayload = 4;</code>
   */
  public proto.JoinPayloadOrBuilder getJoinPayloadOrBuilder() {
    if (payloadCase_ == 4) {
       return (proto.JoinPayload) payload_;
    }
    return proto.JoinPayload.getDefaultInstance();
  }

  public static final int TEXTPAYLOAD_FIELD_NUMBER = 5;
  /**
   * <code>string textPayload = 5;</code>
   */
  public java.lang.String getTextPayload() {
    java.lang.Object ref = "";
    if (payloadCase_ == 5) {
      ref = payload_;
    }
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (payloadCase_ == 5) {
        payload_ = s;
      }
      return s;
    }
  }
  /**
   * <code>string textPayload = 5;</code>
   */
  public com.google.protobuf.ByteString
      getTextPayloadBytes() {
    java.lang.Object ref = "";
    if (payloadCase_ == 5) {
      ref = payload_;
    }
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      if (payloadCase_ == 5) {
        payload_ = b;
      }
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int BYTESPAYLOAD_FIELD_NUMBER = 6;
  /**
   * <code>bytes bytesPayload = 6;</code>
   */
  public com.google.protobuf.ByteString getBytesPayload() {
    if (payloadCase_ == 6) {
      return (com.google.protobuf.ByteString) payload_;
    }
    return com.google.protobuf.ByteString.EMPTY;
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
    if (id_ != 0) {
      output.writeInt32(1, id_);
    }
    if (dest_ != 0) {
      output.writeInt32(2, dest_);
    }
    if (messageType_ != proto.MessageType.REQUEST_PASTE_TEXT.getNumber()) {
      output.writeEnum(3, messageType_);
    }
    if (payloadCase_ == 4) {
      output.writeMessage(4, (proto.JoinPayload) payload_);
    }
    if (payloadCase_ == 5) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, payload_);
    }
    if (payloadCase_ == 6) {
      output.writeBytes(
          6, (com.google.protobuf.ByteString) payload_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (id_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, id_);
    }
    if (dest_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, dest_);
    }
    if (messageType_ != proto.MessageType.REQUEST_PASTE_TEXT.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(3, messageType_);
    }
    if (payloadCase_ == 4) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, (proto.JoinPayload) payload_);
    }
    if (payloadCase_ == 5) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, payload_);
    }
    if (payloadCase_ == 6) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(
            6, (com.google.protobuf.ByteString) payload_);
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
    if (!(obj instanceof proto.Message)) {
      return super.equals(obj);
    }
    proto.Message other = (proto.Message) obj;

    if (getId()
        != other.getId()) return false;
    if (getDest()
        != other.getDest()) return false;
    if (messageType_ != other.messageType_) return false;
    if (!getPayloadCase().equals(other.getPayloadCase())) return false;
    switch (payloadCase_) {
      case 4:
        if (!getJoinPayload()
            .equals(other.getJoinPayload())) return false;
        break;
      case 5:
        if (!getTextPayload()
            .equals(other.getTextPayload())) return false;
        break;
      case 6:
        if (!getBytesPayload()
            .equals(other.getBytesPayload())) return false;
        break;
      case 0:
      default:
    }
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + getId();
    hash = (37 * hash) + DEST_FIELD_NUMBER;
    hash = (53 * hash) + getDest();
    hash = (37 * hash) + MESSAGETYPE_FIELD_NUMBER;
    hash = (53 * hash) + messageType_;
    switch (payloadCase_) {
      case 4:
        hash = (37 * hash) + JOINPAYLOAD_FIELD_NUMBER;
        hash = (53 * hash) + getJoinPayload().hashCode();
        break;
      case 5:
        hash = (37 * hash) + TEXTPAYLOAD_FIELD_NUMBER;
        hash = (53 * hash) + getTextPayload().hashCode();
        break;
      case 6:
        hash = (37 * hash) + BYTESPAYLOAD_FIELD_NUMBER;
        hash = (53 * hash) + getBytesPayload().hashCode();
        break;
      case 0:
      default:
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static proto.Message parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static proto.Message parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static proto.Message parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static proto.Message parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static proto.Message parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static proto.Message parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static proto.Message parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static proto.Message parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static proto.Message parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static proto.Message parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static proto.Message parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static proto.Message parseFrom(
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
  public static Builder newBuilder(proto.Message prototype) {
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
   * Protobuf type {@code tutorial.Message}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:tutorial.Message)
      proto.MessageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return proto.MessageOuterClass.internal_static_tutorial_Message_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return proto.MessageOuterClass.internal_static_tutorial_Message_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              proto.Message.class, proto.Message.Builder.class);
    }

    // Construct using proto.Message.newBuilder()
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
      id_ = 0;

      dest_ = 0;

      messageType_ = 0;

      payloadCase_ = 0;
      payload_ = null;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return proto.MessageOuterClass.internal_static_tutorial_Message_descriptor;
    }

    @java.lang.Override
    public proto.Message getDefaultInstanceForType() {
      return proto.Message.getDefaultInstance();
    }

    @java.lang.Override
    public proto.Message build() {
      proto.Message result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public proto.Message buildPartial() {
      proto.Message result = new proto.Message(this);
      result.id_ = id_;
      result.dest_ = dest_;
      result.messageType_ = messageType_;
      if (payloadCase_ == 4) {
        if (joinPayloadBuilder_ == null) {
          result.payload_ = payload_;
        } else {
          result.payload_ = joinPayloadBuilder_.build();
        }
      }
      if (payloadCase_ == 5) {
        result.payload_ = payload_;
      }
      if (payloadCase_ == 6) {
        result.payload_ = payload_;
      }
      result.payloadCase_ = payloadCase_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof proto.Message) {
        return mergeFrom((proto.Message)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(proto.Message other) {
      if (other == proto.Message.getDefaultInstance()) return this;
      if (other.getId() != 0) {
        setId(other.getId());
      }
      if (other.getDest() != 0) {
        setDest(other.getDest());
      }
      if (other.messageType_ != 0) {
        setMessageTypeValue(other.getMessageTypeValue());
      }
      switch (other.getPayloadCase()) {
        case JOINPAYLOAD: {
          mergeJoinPayload(other.getJoinPayload());
          break;
        }
        case TEXTPAYLOAD: {
          payloadCase_ = 5;
          payload_ = other.payload_;
          onChanged();
          break;
        }
        case BYTESPAYLOAD: {
          setBytesPayload(other.getBytesPayload());
          break;
        }
        case PAYLOAD_NOT_SET: {
          break;
        }
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
      proto.Message parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (proto.Message) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int payloadCase_ = 0;
    private java.lang.Object payload_;
    public PayloadCase
        getPayloadCase() {
      return PayloadCase.forNumber(
          payloadCase_);
    }

    public Builder clearPayload() {
      payloadCase_ = 0;
      payload_ = null;
      onChanged();
      return this;
    }


    private int id_ ;
    /**
     * <code>int32 id = 1;</code>
     */
    public int getId() {
      return id_;
    }
    /**
     * <code>int32 id = 1;</code>
     */
    public Builder setId(int value) {
      
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 id = 1;</code>
     */
    public Builder clearId() {
      
      id_ = 0;
      onChanged();
      return this;
    }

    private int dest_ ;
    /**
     * <code>int32 dest = 2;</code>
     */
    public int getDest() {
      return dest_;
    }
    /**
     * <code>int32 dest = 2;</code>
     */
    public Builder setDest(int value) {
      
      dest_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 dest = 2;</code>
     */
    public Builder clearDest() {
      
      dest_ = 0;
      onChanged();
      return this;
    }

    private int messageType_ = 0;
    /**
     * <code>.tutorial.MessageType messageType = 3;</code>
     */
    public int getMessageTypeValue() {
      return messageType_;
    }
    /**
     * <code>.tutorial.MessageType messageType = 3;</code>
     */
    public Builder setMessageTypeValue(int value) {
      messageType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.tutorial.MessageType messageType = 3;</code>
     */
    public proto.MessageType getMessageType() {
      @SuppressWarnings("deprecation")
      proto.MessageType result = proto.MessageType.valueOf(messageType_);
      return result == null ? proto.MessageType.UNRECOGNIZED : result;
    }
    /**
     * <code>.tutorial.MessageType messageType = 3;</code>
     */
    public Builder setMessageType(proto.MessageType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      messageType_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.tutorial.MessageType messageType = 3;</code>
     */
    public Builder clearMessageType() {
      
      messageType_ = 0;
      onChanged();
      return this;
    }

    private com.google.protobuf.SingleFieldBuilderV3<
        proto.JoinPayload, proto.JoinPayload.Builder, proto.JoinPayloadOrBuilder> joinPayloadBuilder_;
    /**
     * <code>.tutorial.JoinPayload joinPayload = 4;</code>
     */
    public boolean hasJoinPayload() {
      return payloadCase_ == 4;
    }
    /**
     * <code>.tutorial.JoinPayload joinPayload = 4;</code>
     */
    public proto.JoinPayload getJoinPayload() {
      if (joinPayloadBuilder_ == null) {
        if (payloadCase_ == 4) {
          return (proto.JoinPayload) payload_;
        }
        return proto.JoinPayload.getDefaultInstance();
      } else {
        if (payloadCase_ == 4) {
          return joinPayloadBuilder_.getMessage();
        }
        return proto.JoinPayload.getDefaultInstance();
      }
    }
    /**
     * <code>.tutorial.JoinPayload joinPayload = 4;</code>
     */
    public Builder setJoinPayload(proto.JoinPayload value) {
      if (joinPayloadBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        payload_ = value;
        onChanged();
      } else {
        joinPayloadBuilder_.setMessage(value);
      }
      payloadCase_ = 4;
      return this;
    }
    /**
     * <code>.tutorial.JoinPayload joinPayload = 4;</code>
     */
    public Builder setJoinPayload(
        proto.JoinPayload.Builder builderForValue) {
      if (joinPayloadBuilder_ == null) {
        payload_ = builderForValue.build();
        onChanged();
      } else {
        joinPayloadBuilder_.setMessage(builderForValue.build());
      }
      payloadCase_ = 4;
      return this;
    }
    /**
     * <code>.tutorial.JoinPayload joinPayload = 4;</code>
     */
    public Builder mergeJoinPayload(proto.JoinPayload value) {
      if (joinPayloadBuilder_ == null) {
        if (payloadCase_ == 4 &&
            payload_ != proto.JoinPayload.getDefaultInstance()) {
          payload_ = proto.JoinPayload.newBuilder((proto.JoinPayload) payload_)
              .mergeFrom(value).buildPartial();
        } else {
          payload_ = value;
        }
        onChanged();
      } else {
        if (payloadCase_ == 4) {
          joinPayloadBuilder_.mergeFrom(value);
        }
        joinPayloadBuilder_.setMessage(value);
      }
      payloadCase_ = 4;
      return this;
    }
    /**
     * <code>.tutorial.JoinPayload joinPayload = 4;</code>
     */
    public Builder clearJoinPayload() {
      if (joinPayloadBuilder_ == null) {
        if (payloadCase_ == 4) {
          payloadCase_ = 0;
          payload_ = null;
          onChanged();
        }
      } else {
        if (payloadCase_ == 4) {
          payloadCase_ = 0;
          payload_ = null;
        }
        joinPayloadBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>.tutorial.JoinPayload joinPayload = 4;</code>
     */
    public proto.JoinPayload.Builder getJoinPayloadBuilder() {
      return getJoinPayloadFieldBuilder().getBuilder();
    }
    /**
     * <code>.tutorial.JoinPayload joinPayload = 4;</code>
     */
    public proto.JoinPayloadOrBuilder getJoinPayloadOrBuilder() {
      if ((payloadCase_ == 4) && (joinPayloadBuilder_ != null)) {
        return joinPayloadBuilder_.getMessageOrBuilder();
      } else {
        if (payloadCase_ == 4) {
          return (proto.JoinPayload) payload_;
        }
        return proto.JoinPayload.getDefaultInstance();
      }
    }
    /**
     * <code>.tutorial.JoinPayload joinPayload = 4;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        proto.JoinPayload, proto.JoinPayload.Builder, proto.JoinPayloadOrBuilder> 
        getJoinPayloadFieldBuilder() {
      if (joinPayloadBuilder_ == null) {
        if (!(payloadCase_ == 4)) {
          payload_ = proto.JoinPayload.getDefaultInstance();
        }
        joinPayloadBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            proto.JoinPayload, proto.JoinPayload.Builder, proto.JoinPayloadOrBuilder>(
                (proto.JoinPayload) payload_,
                getParentForChildren(),
                isClean());
        payload_ = null;
      }
      payloadCase_ = 4;
      onChanged();;
      return joinPayloadBuilder_;
    }

    /**
     * <code>string textPayload = 5;</code>
     */
    public java.lang.String getTextPayload() {
      java.lang.Object ref = "";
      if (payloadCase_ == 5) {
        ref = payload_;
      }
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (payloadCase_ == 5) {
          payload_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string textPayload = 5;</code>
     */
    public com.google.protobuf.ByteString
        getTextPayloadBytes() {
      java.lang.Object ref = "";
      if (payloadCase_ == 5) {
        ref = payload_;
      }
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        if (payloadCase_ == 5) {
          payload_ = b;
        }
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string textPayload = 5;</code>
     */
    public Builder setTextPayload(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  payloadCase_ = 5;
      payload_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string textPayload = 5;</code>
     */
    public Builder clearTextPayload() {
      if (payloadCase_ == 5) {
        payloadCase_ = 0;
        payload_ = null;
        onChanged();
      }
      return this;
    }
    /**
     * <code>string textPayload = 5;</code>
     */
    public Builder setTextPayloadBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      payloadCase_ = 5;
      payload_ = value;
      onChanged();
      return this;
    }

    /**
     * <code>bytes bytesPayload = 6;</code>
     */
    public com.google.protobuf.ByteString getBytesPayload() {
      if (payloadCase_ == 6) {
        return (com.google.protobuf.ByteString) payload_;
      }
      return com.google.protobuf.ByteString.EMPTY;
    }
    /**
     * <code>bytes bytesPayload = 6;</code>
     */
    public Builder setBytesPayload(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  payloadCase_ = 6;
      payload_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes bytesPayload = 6;</code>
     */
    public Builder clearBytesPayload() {
      if (payloadCase_ == 6) {
        payloadCase_ = 0;
        payload_ = null;
        onChanged();
      }
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:tutorial.Message)
  }

  // @@protoc_insertion_point(class_scope:tutorial.Message)
  private static final proto.Message DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new proto.Message();
  }

  public static proto.Message getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Message>
      PARSER = new com.google.protobuf.AbstractParser<Message>() {
    @java.lang.Override
    public Message parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Message(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Message> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Message> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public proto.Message getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

