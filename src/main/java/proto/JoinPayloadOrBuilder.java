// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: message.proto

package proto;

public interface JoinPayloadOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tutorial.JoinPayload)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 timestamp = 1;</code>
   * @return The timestamp.
   */
  long getTimestamp();

  /**
   * <code>int32 id = 2;</code>
   * @return The id.
   */
  int getId();

  /**
   * <code>string deviceType = 3;</code>
   * @return The deviceType.
   */
  java.lang.String getDeviceType();
  /**
   * <code>string deviceType = 3;</code>
   * @return The bytes for deviceType.
   */
  com.google.protobuf.ByteString
      getDeviceTypeBytes();

  /**
   * <code>string name = 4;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 4;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>repeated string clipboard = 5;</code>
   * @return A list containing the clipboard.
   */
  java.util.List<java.lang.String>
      getClipboardList();
  /**
   * <code>repeated string clipboard = 5;</code>
   * @return The count of clipboard.
   */
  int getClipboardCount();
  /**
   * <code>repeated string clipboard = 5;</code>
   * @param index The index of the element to return.
   * @return The clipboard at the given index.
   */
  java.lang.String getClipboard(int index);
  /**
   * <code>repeated string clipboard = 5;</code>
   * @param index The index of the value to return.
   * @return The bytes of the clipboard at the given index.
   */
  com.google.protobuf.ByteString
      getClipboardBytes(int index);
}