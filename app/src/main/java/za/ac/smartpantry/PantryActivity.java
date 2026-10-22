package za.ac.smartpantry;
import android.app.*;import android.content.*;import android.os.*;import android.view.*;import android.widget.*;import androidx.recyclerview.widget.*;import java.util.*;
public class PantryActivity extends Activity{
 @Override public void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_pantry);Ui.nav(this);findViewById(R.id.addButton).setOnClickListener(v->startActivity(new Intent(this,EditIngredientActivity.class)));RecyclerView list=findViewById(R.id.itemsList);list.setLayoutManager(new LinearLayoutManager(this));load();}
 @Override protected void onResume(){super.onResume();load();}
 public void load(){RecyclerView list=findViewById(R.id.itemsList);List<Ingredient> data=new PantryDatabase(this).getPantry();list.setAdapter(new PantryAdapter(this,data));findViewById(R.id.emptyLabel).setVisibility(data.isEmpty()?View.VISIBLE:View.GONE);}
}