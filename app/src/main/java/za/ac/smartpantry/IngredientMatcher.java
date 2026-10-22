package za.ac.smartpantry;
import java.util.*;
import java.util.Locale;
/** Explicit small alias vocabulary; avoid fuzzy matching different ingredients. */
public final class IngredientMatcher {
 private IngredientMatcher(){}
 public static String canonical(String s){String n=s.trim().toLowerCase(Locale.ROOT).replaceAll("\\s+"," ");
  Map<String,String> aliases=new HashMap<>();aliases.put("tomatoes","tomato");aliases.put("potatoes","potato");aliases.put("eggs","egg");aliases.put("bananas","banana");aliases.put("lemons","lemon");aliases.put("slices of bread","bread");aliases.put("garlic cloves","garlic");aliases.put("clove of garlic","garlic");return aliases.containsKey(n)?aliases.get(n):n;
 }
 /** Returns NaN for incompatible units so the ingredient cannot match. */
 public static double convert(double amount,String from,String to){String a=from.toLowerCase(Locale.ROOT),b=to.toLowerCase(Locale.ROOT);if(a.equals(b))return amount;
  if(a.equals("kg")&&b.equals("g"))return amount*1000; if(a.equals("g")&&b.equals("kg"))return amount/1000;
  if(a.equals("l")&&b.equals("ml"))return amount*1000; if(a.equals("ml")&&b.equals("l"))return amount/1000;
  if(a.equals("pcs")&&b.equals("piece"))return amount;if(a.equals("piece")&&b.equals("pcs"))return amount;
  return Double.NaN;
 }
}