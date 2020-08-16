import java.util.List;

public class ShortestWordInCarPlate {
  List<String> vocabulary;
  
  ShortestWordInCarPlate(List<String> vocabulary) {
    this.vocabulary = vocabulary;
  }
  
  public String findShortestWord(String car_plate) {
    System.out.println(car_plate);
    StringBuilder text = new StringBuilder();
    int counter = 0;
    
    for (int i = 0; i < car_plate.length(); i++) {
      if (Character.isLetter(car_plate.charAt(i))) {
        text.append(car_plate.charAt(i));
      }
    }
    String newText = text.toString();
    String low = newText.toLowerCase();
    String up = newText.toUpperCase();
    
    int vocabIterator = 0;
    String minString = "";
    
    while (vocabIterator < this.vocabulary.size()) {
      boolean check = true;
      for (int i = 0; i < newText.length(); i++) {
        check &=
                ((this.vocabulary.get(vocabIterator).indexOf(low.charAt(i)) != -1)
                        || (this.vocabulary.get(vocabIterator).indexOf(up.charAt(i)) != -1));
      }
      if (check
              && (minString.equals("")
              || this.vocabulary.get(vocabIterator).length() < minString.length())) {
        minString = this.vocabulary.get(vocabIterator);
        if (minString.length() == newText.length()) {
          System.out.println(minString);
          return minString;
        }
      }
      vocabIterator++;
    }
    System.out.println(minString);
    return minString;
  }
}
