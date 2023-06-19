package com.articlesystem.entity;

/**
 * @program: ArticleSystem
 * @description: 菜单
 * @author: fuyunjia
 * @create: 2023-06-19 15:11
 **/
public class Menu {

  public Menu() {
  }

  public Menu(int menuId, String menuUrl, String menuName, int pId, int orderID, String icon,
      String title) {
    this.menuId = menuId;
    this.menuUrl = menuUrl;
    this.menuName = menuName;
    this.pId = pId;
    this.orderID = orderID;
    this.icon = icon;
    this.title = title;
  }

  public int getMenuId() {
    return menuId;
  }

  public void setMenuId(int menuId) {
    this.menuId = menuId;
  }

  public String getMenuUrl() {
    return menuUrl;
  }

  public void setMenuUrl(String menuUrl) {
    this.menuUrl = menuUrl;
  }

  public String getMenuName() {
    return menuName;
  }

  public void setMenuName(String menuName) {
    this.menuName = menuName;
  }

  public int getpId() {
    return pId;
  }

  public void setpId(int pId) {
    this.pId = pId;
  }

  public int getOrderID() {
    return orderID;
  }

  public void setOrderID(int orderID) {
    this.orderID = orderID;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  private int menuId;
  private String menuUrl;
  private String menuName;
  private int pId;
  private int orderID;
  private String icon;
  private String title;

}
