// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message.proto

/**
 * Protobuf enum {@code tutorial.MessageType}
 */
public enum MessageType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>REQUEST_PASTE_TEXT = 0;</code>
   */
  REQUEST_PASTE_TEXT(0),
  /**
   * <code>REQUEST_PASTE_IMAGE = 1;</code>
   */
  REQUEST_PASTE_IMAGE(1),
  /**
   * <code>REQUEST_COPY_SS = 2;</code>
   */
  REQUEST_COPY_SS(2),
  /**
   * <code>RESPONSE_PASTE_TEXT = 3;</code>
   */
  RESPONSE_PASTE_TEXT(3),
  /**
   * <code>RESPONSE_PASTE_IMAGE = 4;</code>
   */
  RESPONSE_PASTE_IMAGE(4),
  /**
   * <code>RESPONSE_COPY_SS = 5;</code>
   */
  RESPONSE_COPY_SS(5),
  /**
   * <code>UPDATE_JOIN = 6;</code>
   */
  UPDATE_JOIN(6),
  /**
   * <code>UPDATE_CLIENT = 7;</code>
   */
  UPDATE_CLIENT(7),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>REQUEST_PASTE_TEXT = 0;</code>
   */
  public static final int REQUEST_PASTE_TEXT_VALUE = 0;
  /**
   * <code>REQUEST_PASTE_IMAGE = 1;</code>
   */
  public static final int REQUEST_PASTE_IMAGE_VALUE = 1;
  /**
   * <code>REQUEST_COPY_SS = 2;</code>
   */
  public static final int REQUEST_COPY_SS_VALUE = 2;
  /**
   * <code>RESPONSE_PASTE_TEXT = 3;</code>
   */
  public static final int RESPONSE_PASTE_TEXT_VALUE = 3;
  /**
   * <code>RESPONSE_PASTE_IMAGE = 4;</code>
   */
  public static final int RESPONSE_PASTE_IMAGE_VALUE = 4;
  /**
   * <code>RESPONSE_COPY_SS = 5;</code>
   */
  public static final int RESPONSE_COPY_SS_VALUE = 5;
  /**
   * <code>UPDATE_JOIN = 6;</code>
   */
  public static final int UPDATE_JOIN_VALUE = 6;
  /**
   * <code>UPDATE_CLIENT = 7;</code>
   */
  public static final int UPDATE_CLIENT_VALUE = 7;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static MessageType valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static MessageType forNumber(int value) {
    switch (value) {
      case 0: return REQUEST_PASTE_TEXT;
      case 1: return REQUEST_PASTE_IMAGE;
      case 2: return REQUEST_COPY_SS;
      case 3: return RESPONSE_PASTE_TEXT;
      case 4: return RESPONSE_PASTE_IMAGE;
      case 5: return RESPONSE_COPY_SS;
      case 6: return UPDATE_JOIN;
      case 7: return UPDATE_CLIENT;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<MessageType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      MessageType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<MessageType>() {
          public MessageType findValueByNumber(int number) {
            return MessageType.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalStateException(
          "Can't get the descriptor of an unrecognized enum value.");
    }
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return MessageOuterClass.getDescriptor().getEnumTypes().get(0);
  }

  private static final MessageType[] VALUES = values();

  public static MessageType valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private MessageType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:tutorial.MessageType)
}

