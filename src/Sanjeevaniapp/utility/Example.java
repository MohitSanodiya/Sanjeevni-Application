package Sanjeevaniapp.utility;

public class Example {
   public static void main(String[] args) {
      boolean isValid = validateInput("Hello");
      System.out.println("The result is: " + isValid);
   }
   
   public static boolean validateInput(String input) {
      boolean isValid = true;
      // Validate the input here
      if (input.isEmpty()) {
         isValid = false;
      }
      return isValid;
   }
}