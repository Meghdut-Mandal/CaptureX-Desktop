// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message.proto

package proto;

public final class MessageOuterClass {
  private MessageOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tutorial_Message_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tutorial_Message_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_tutorial_JoinPayload_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_tutorial_JoinPayload_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rmessage.proto\022\010tutorial\032\031google/protob" +
      "uf/any.proto\"\267\001\n\007Message\022\n\n\002id\030\001 \001(\005\022\014\n\004" +
      "dest\030\002 \001(\005\022*\n\013messageType\030\003 \001(\0162\025.tutori" +
      "al.MessageType\022,\n\013joinPayload\030\004 \001(\0132\025.tu" +
      "torial.JoinPayloadH\000\022\025\n\013textPayload\030\005 \001(" +
      "\tH\000\022\026\n\014bytesPayload\030\006 \001(\014H\000B\t\n\007payload\"a" +
      "\n\013JoinPayload\022\021\n\ttimestamp\030\001 \001(\003\022\n\n\002id\030\002" +
      " \001(\005\022\022\n\ndeviceType\030\003 \001(\t\022\014\n\004name\030\004 \001(\t\022\021" +
      "\n\tclipboard\030\005 \003(\t*\327\001\n\013MessageType\022\026\n\022REQ" +
      "UEST_PASTE_TEXT\020\000\022\027\n\023REQUEST_PASTE_IMAGE" +
      "\020\001\022\023\n\017REQUEST_COPY_SS\020\002\022\027\n\023RESPONSE_PAST" +
      "E_TEXT\020\003\022\030\n\024RESPONSE_PASTE_IMAGE\020\004\022\024\n\020RE" +
      "SPONSE_COPY_SS\020\005\022\017\n\013UPDATE_JOIN\020\006\022\021\n\rUPD" +
      "ATE_CLIENT\020\007\022\025\n\021REQUEST_COPY_TEXT\020\010B\034\n\005p" +
      "rotoB\021MessageOuterClassP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.AnyProto.getDescriptor(),
        });
    internal_static_tutorial_Message_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_tutorial_Message_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tutorial_Message_descriptor,
        new java.lang.String[] { "Id", "Dest", "MessageType", "JoinPayload", "TextPayload", "BytesPayload", "Payload", });
    internal_static_tutorial_JoinPayload_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_tutorial_JoinPayload_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_tutorial_JoinPayload_descriptor,
        new java.lang.String[] { "Timestamp", "Id", "DeviceType", "Name", "Clipboard", });
    com.google.protobuf.AnyProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
