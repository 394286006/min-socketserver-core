package p.minn.job;

/**
 * @author minn
 * @QQ:394286006
 * 
 */
public class GroupId {
   
  private int group;
  
  private int clientId;

  public GroupId(int group, int clientId) {
    super();
    this.group = group;
    this.clientId = clientId;
  }

  public int getGroup() {
    return group;
  }

  public void setGroup(int group) {
    this.group = group;
  }

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }
  
  
}
