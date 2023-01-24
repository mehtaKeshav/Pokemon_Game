
import java.util.Scanner;
import java.io.*;
import java.awt.*;

public class Map {
  private char [][] map;
  private boolean [][] revealed;
  private static Map instance = null;

  /** Constructor
  * Description: contructs the map as an array with width and height of 5.
  */
  public Map() {
    map = new char[5][5];
    revealed = new boolean [5][5];
    for(int i = 0; i < revealed.length; i++) {
      for (int j = 0; j < revealed[0].length; j++) {
        revealed[i][j] = false;
      }
    }
  }

  public static Map getInstance(){
    if(instance == null){
      instance = new Map();
    }
    return instance;
  }
  
  /**
  * Description: loads the data of a map based off a text file.
  * @param mapNum is the number of the map/area being loaded
  */
  public void loadMap(int mapNum){
    try {
      String map_name = "Area" + mapNum + ".txt";
      Scanner read = new Scanner(new File(map_name));
      int row_count = 0; //Counts how many rows the map has gone through already
      while (read.hasNext()) {
          
          String s1 = read.nextLine();
          char [] row = new char [5];
          int row_length = 0;
          for (int i=0; i < s1.length(); ++i) {
            char character = s1.charAt(i);
            if (character != ' ') {
              row[row_length] = character;
              row_length += 1;
            }
          }
          map[row_count] = row;
          row_count += 1;
      }
      //hide the map when it transitions to another map
      for(int i = 0; i < revealed.length; i++) {
      for (int j = 0; j < revealed[0].length; j++) {
        revealed[i][j] = false;
      }
    }
    }catch(FileNotFoundException e){
      System.out.println("File Not Found");
    }
  }
  /**
  * Description: finds the character on a map of a specific point on the map
  * @param p is the point/spot of the map that the character is at.
  * @return return the character that is on the specified point of the map.
  */
  public char getCharAtLoc(Point p) {
    char location = map[p.x][p.y];
    return location;
  }
  /**
  * Description: prints out the map as a String
  * @param p where p is the location of the player/trainer represented with an asterisk.
  * @return return the entire map as a string.
  */
  public String mapToString(Point p) {
    String map_string = "";
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        if (i == p.x && j == p.y){
          map_string += "* ";
        }
        else {
          if (revealed[i][j] == false) {
            map_string += "x ";
          }
          else {
            map_string += map[i][j] + " ";
          }
        }
      }
      map_string += "\n"; 
    }
    return map_string;
  }
  
  /**
  * Description: finds the starting point of the map represented by s.
  * @return return the point where the start of the map is.
  */
  public Point findStart() {
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        if (map[i][j] == 's') {
          Point start = new Point(i, j);
          return start;
        }
      }
    }
    Point default_point = new Point(0, 0);
    return default_point;
  }
  /**
  * Description: changes the array of reveal to true which makes the character visible at the point
  * @param p is the point that gets revealed
  */
  public void reveal(Point p) {
      revealed[p.x][p.y] = true;
  }
  /**
  * Description: removes a character at the location on the map replacing it with n which means nothing is at that point.
  * @param p is the point/location that character is being removed from.
  */
  public void removeCharAtLoc(Point p) {
    map[p.x][p.y] = 'n';
  }
  

}