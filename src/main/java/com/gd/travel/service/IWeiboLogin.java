package com.gd.travel.service;

public interface IWeiboLogin {

  /**
   * 微博登录
   * @param username
   * @param password
   * @return
   */
  boolean login(String username, String password);
  
}
