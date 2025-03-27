public class AutoBuilder 
{
  private String autoString = "";

  public void addToString(String toAdd)
  {
    autoString = autoString.isEmpty() ? autoString.concat(toAdd.toLowerCase()) : autoString.concat("," + toAdd.toLowerCase());
  }

  public String getString()
  {
    return autoString;
  }

  public void removeLastItem()
  {
    autoString = autoString.substring(0, autoString.contains(",") ? autoString.lastIndexOf(",") : 0);
  }
}
