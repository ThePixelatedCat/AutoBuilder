import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class AutoBuilder 
{
  private String autoString;

  public AutoBuilder()
  {
    autoString = fetchLastSave();
  }

  private String fetchLastSave()
  {
    List<String> lines = Collections.emptyList();
    
    try 
    {
      lines = Files.readAllLines
      (
        getSavePath(),
        StandardCharsets.UTF_8
      );
    } 
    catch(IOException e) 
    {
      e.printStackTrace();
    }

    if (lines.size() == 0) 
    {
      lines.add("");  
    }
  
    String lastLine = lines.get(lines.size() - 1);
    return lastLine;
  }

  public void writeToSave()
  {
    try 
    {
      Files.writeString(getSavePath(), autoString);
    } 
    catch(IOException e) 
    {
      e.printStackTrace();
    }
  }

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
  
  public static Path getSavePath()
  {
    URI jarPath = URI.create(BuilderFrame.class.getProtectionDomain().getCodeSource().getLocation().toString());
    Path filePath = Path.of(jarPath).getParent().resolve("AutoBuilderSaves.txt");
    if (!Files.exists(filePath))
    {
      try 
      {
        Files.createFile(filePath);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    return filePath;
  }
}
