package za.ac.smartpantry;
import android.app.*;import android.content.*;import android.graphics.Color;import android.view.*;import android.widget.*;
public class Ui {
 public static void nav(Activity a){int[] ids={R.id.navPantry,R.id.navRecipes,R.id.navSettings};Class<?>[] screens={PantryActivity.class,SuggestedActivity.class,SettingsActivity.class};for(int i=0;i<ids.length;i++){final Class<?> target=screens[i];a.findViewById(ids[i]).setOnClickListener(v->{if(!a.getClass().equals(target)){Intent intent=new Intent(a,target);intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);a.startActivity(intent);}});}}
 public static TextView row(Activity a,String title,String sub){TextView v=new TextView(a);v.setText(title+"\n"+sub);v.setTextSize(17);v.setTextColor(Color.rgb(20,55,49));v.setPadding(24,24,24,24);v.setBackgroundColor(Color.WHITE);return v;}
}