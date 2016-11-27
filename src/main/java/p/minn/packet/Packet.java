package p.minn.packet;

/**
 * @author minn
 * @QQ:394286006
 * 
 */
public  class Packet {

  protected int clientId=-1;
  protected int group=1;
  public byte [ ] body;
  public int bodyType = 0;
  public int bodySize = 0;
  
  public Packet() {
  }
  public Packet(byte[] body) {
    super();
    this.bodySize=body.length;
    this.body = body;
  }
  public Packet(int clientId) {
    super();
    this.clientId = clientId;
  }
  public byte[] getBody() {
    return body;
  }
  public void setBody(byte[] body) {
    this.body = body;
  }
  public int getClientId() {
    return clientId;
  }
  public void setClientId(int clientId) {
    this.clientId = clientId;
  }
  public int getGroup() {
    return group;
  }
  public void setGroup(int group) {
    this.group = group;
  }
  
}
