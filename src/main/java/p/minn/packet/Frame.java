package p.minn.packet;

/**
 * @author minn
 * @QQ:394286006
 * 
 */
public class Frame {
  
  private byte[] header;
  
  private int length;
  
  private byte[] data;

  
  public Frame(byte header, byte[] data) {
    super();
    this.header=new byte[1];
    this.header[0] = header;
    this.data = data;
    this.length=data.length;
  }
  public Frame(byte[] header, byte[] data) {
    super();
    this.header = header;
    this.data = data;
    this.length=data.length;
  }

  public byte[] getHeader() {
    return header;
  }

  public void setHeader(byte header[]) {
    this.header = header;
  }

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  
}
