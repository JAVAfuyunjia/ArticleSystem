package com.articlesystem.entity;

/**
 * @author 云佳
 * @create 2023-04-21 18:50
 * @往之不谏，来者可追。
 */
public class Attachment {

  private  Integer attachmentId;

  private String attachmentName;

  private String getAttachmentPath;

  public Integer getAttachmentId() {
    return attachmentId;
  }

  public void setAttachmentId(Integer attachmentId) {
    this.attachmentId = attachmentId;
  }

  public String getAttachmentName() {
    return attachmentName;
  }

  public void setAttachmentName(String attachmentName) {
    this.attachmentName = attachmentName;
  }

  public String getGetAttachmentPath() {
    return getAttachmentPath;
  }

  public void setGetAttachmentPath(String getAttachmentPath) {
    this.getAttachmentPath = getAttachmentPath;
  }

  public Attachment(Integer attachmentId, String attachmentName, String getAttachmentPath) {
    this.attachmentId = attachmentId;
    this.attachmentName = attachmentName;
    this.getAttachmentPath = getAttachmentPath;

  }

  public Attachment() {
  }
}
