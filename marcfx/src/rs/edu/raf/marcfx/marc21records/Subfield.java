package rs.edu.raf.marcfx.marc21records;

public class Subfield implements java.io.Serializable {

 /** the name of this subfield */
  private char name;
  /** subfield content; an empty string if the subfield is empty */
  private String content;
  
  
  
  
  /**
   * Default constructor.
   */
  public Subfield() {
    name = ' ';
    content = "";  
  }

  /**
   * Constructs a subfield with the given name.
   * @param name The name of the subfield
   */
  public Subfield(char name) {
    this.name = name;
    content = "";   
  }
  
  /**
   * Returns a printable string representation of this subfield.
   */
  public String toString() {
    StringBuffer retVal = new StringBuffer();
    retVal.append("$");
    retVal.append(name);    
    retVal.append(content);   
    return retVal.toString();
  }

  /**
   * @return Returns the content.
   */
  public String getContent() {
    return content;
  }
  /**
   * @param content The content to set.
   */
  public void setContent(String content) {
    this.content = content;
  }
  /**
   * @return Returns the name.
   */
  public char getName() {
    return name;
  }
  /**
   * @param name The name to set.
   */
  public void setName(char name) {
    this.name = name;
  } 
 
 
  
}