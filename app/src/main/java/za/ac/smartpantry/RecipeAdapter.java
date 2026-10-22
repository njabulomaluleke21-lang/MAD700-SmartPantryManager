package za.ac.smartpantry;
import android.content.*;import android.view.*;import android.widget.*;import androidx.recyclerview.widget.RecyclerView;import java.util.*;
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.Holder>{private final SuggestedActivity activity;private final List<Recipe> items;public RecipeAdapter(SuggestedActivity a,List<Recipe> recipes){activity=a;items=recipes;}public static class Holder extends RecyclerView.ViewHolder{TextView text;Holder(TextView v){super(v);text=v;}}
 @Override public Holder onCreateViewHolder(ViewGroup p,int t){return new Holder(Ui.row(activity,"",""));}
 @Override public void onBindViewHolder(Holder h,int pos){Recipe r=items.get(pos);h.text.setText("✓ "+r.title+"\nAll required ingredients available • Tap for recipe");h.text.setOnClickListener(v->{Intent i=new Intent(activity,RecipeDetailActivity.class);i.putExtra("id",r.id);activity.startActivity(i);});}
 @Override public int getItemCount(){return items.size();}}