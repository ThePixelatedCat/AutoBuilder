public class AutoBuilder 
{
  private String autoString = "";

  public void addToString(String toAdd)
  {
    autoString = autoString.concat(toAdd.toLowerCase() + ",");
  }

  public String getString()
  {
    return autoString.substring(0, Math.max(0, autoString.length() - 1));
  }
}
