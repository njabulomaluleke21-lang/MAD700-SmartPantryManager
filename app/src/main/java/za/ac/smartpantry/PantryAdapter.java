package za.ac.smartpantry;
import android.app.*;import android.content.*;import android.view.*;import android.widget.*;import androidx.recyclerview.widget.RecyclerView;import java.util.*;import java.time.*;import java.time.temporal.ChronoUnit;
public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.Holder>{
 private final PantryActivity activity;private final List<Ingredient> items;
 public PantryAdapter(PantryActivity a,List<Ingredient> items){activity=a;this.items=items;}
 public static class Holder extends RecyclerView.ViewHolder{TextView text;Holder(TextView v){super(v);text=v;}}
 @Override public Holder onCreateViewHolder(ViewGroup parent,int type){return new Holder(Ui.row(activity,"",""));}
 @Override public void onBindViewHolder(Holder h,int position){Ingredient item=items.get(position);String expiry=item.expiry==null?"":" • Expires "+item.expiry;String flag="";if(item.expiry!=null&&activity.getSharedPreferences("pantry_settings",0).getBoolean("expiry_alert",true)){try{long d=ChronoUnit.DAYS.between(LocalDate.now(),LocalDate.parse(item.expiry));if(d<=3)flag="  ⚠ Expiring soon";}catch(Exception ignored){}}h.text.setText(item.name+"\n"+item.quantity+" "+item.unit+expiry+flag+"\nTap to edit • long-press to delete");h.text.setOnClickListener(v->{Intent intent=new Intent(activity,EditIngredientActivity.class);intent.putExtra("id",item.id);activity.startActivity(intent);});h.text.setOnLongClickListener(v->{new AlertDialog.Builder(activity).setTitle("Delete "+item.name+"?").setMessage("This cannot be undone.").setPositiveButton("Delete",(d,w)->{new PantryDatabase(activity).deleteIngredient(item.id);activity.load();}).setNegativeButton("Cancel",null).show();return true;});}
 @Override public int getItemCount(){return items.size();}
}