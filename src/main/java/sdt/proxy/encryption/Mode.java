package sdt.proxy.encryption;
/*
 * 加密，解密类
 */
public abstract class Mode<D>{
  public abstract D encrypt(D d);
  public abstract D decrypt(D d);

}
