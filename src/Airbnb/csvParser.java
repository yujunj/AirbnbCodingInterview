package Airbnb;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class csvParser {
	public static String parseCSV(String s) {
	    List<String> result = new ArrayList<>();
	    if (s == null || s.length() == 0) {
	      return "";
	    }
	    boolean inQuote = false;
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < s.length(); i++) {
	      if (inQuote) {
	        if (s.charAt(i) == '"') {//quote inside quote,remove the outermost
	          if (i == s.length() - 1) {//ending
	            result.add(sb.toString()); // change the 2 lines to break
	            return printStr(result); //
	          } 
	          else if (s.charAt(i + 1) == '"') {//we can keep it if it is inside the outer quote
	            sb.append('"');
	            i++;//skip the next quote
	          } 
	          else {
	            result.add(sb.toString());
	            sb.setLength(0);
	            inQuote = false;
	            i++;
	          }
	        } 
	        else {
	          sb.append(s.charAt(i));
	        }
	      } 
	      else {
	        if (s.charAt(i) == '"') {
	          inQuote = true;
	        } 
	        else if (s.charAt(i) == ',') {//comma is separator, should be replaced by | later
	          result.add(sb.toString());
	          sb.setLength(0);
	        }
	        else {
	          sb.append(s.charAt(i));//continue concatenate the string builder
	        }
	      }
	    }
	    if (sb.length() > 0) {
	      result.add(sb.toString());//handle the last string
	    }
	    return printStr(result);
	  }
	
	  private static String printStr(List<String> input) {
	    if (input == null || input.size() == 0) {
	      return "";
	    }
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < input.size(); i++) {
	      sb.append(input.get(i));
	      if (i == input.size() - 1) {
	        break;
	      }
	      sb.append("|");
	    } 
	    return sb.toString();
	 }
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        System.out.print("Enter String");
	        String s = br.readLine();
		System.out.println(parseCSV(s));
	}

}
