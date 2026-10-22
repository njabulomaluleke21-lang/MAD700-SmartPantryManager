package za.ac.smartpantry;
import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import java.util.*;

/** Local database: recipes are seeded once; pantry rows remain editable and persistent. */
public class PantryDatabase extends SQLiteOpenHelper {
 public PantryDatabase(Context context){ super(context,"pantry.db",null,1); }
 @Override public void onCreate(SQLiteDatabase db){
  db.execSQL("CREATE TABLE pantry(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL,quantity REAL NOT NULL,unit TEXT NOT NULL,expiry TEXT)");
  db.execSQL("CREATE TABLE recipes(id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT NOT NULL,method TEXT NOT NULL)");
  db.execSQL("CREATE TABLE recipe_ingredients(id INTEGER PRIMARY KEY AUTOINCREMENT,recipe_id INTEGER NOT NULL,name TEXT NOT NULL,quantity REAL NOT NULL,unit TEXT NOT NULL,FOREIGN KEY(recipe_id) REFERENCES recipes(id))");
  seed(db);
 }
 @Override public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion) { /* Future migrations go here. */ }
 private static long addRecipe(SQLiteDatabase db,String title,String method){ContentValues v=new ContentValues();v.put("title",title);v.put("method",method);return db.insertOrThrow("recipes",null,v);}
 private static void addNeed(SQLiteDatabase db,long recipeId,String name,double qty,String unit){ContentValues v=new ContentValues();v.put("recipe_id",recipeId);v.put("name",name);v.put("quantity",qty);v.put("unit",unit);db.insertOrThrow("recipe_ingredients",null,v);}
 private static void seed(SQLiteDatabase db){long id;
  id=addRecipe(db,"Tomato Salad","Slice tomatoes; toss with olive oil and salt; serve fresh."); addNeed(db,id,"tomato",2,"pcs");addNeed(db,id,"olive oil",10,"ml");addNeed(db,id,"salt",1,"g");
 id=addRecipe(db,"Scrambled Eggs","Whisk eggs and milk; heat oil and stir until softly set."); addNeed(db,id,"egg",2,"pcs");addNeed(db,id,"milk",30,"ml");addNeed(db,id,"oil",5,"ml");
 id=addRecipe(db,"Boiled Eggs","Boil eggs in water for 9 minutes; cool before peeling."); addNeed(db,id,"egg",2,"pcs");addNeed(db,id,"water",500,"ml");
 id=addRecipe(db,"Butter Toast","Toast the bread; spread butter and serve."); addNeed(db,id,"bread",2,"slice");addNeed(db,id,"butter",10,"g");
 id=addRecipe(db,"Cheese Toast","Place cheese on bread and toast in a pan."); addNeed(db,id,"bread",2,"slice");addNeed(db,id,"cheese",40,"g");
 id=addRecipe(db,"Tomato Sandwich","Slice tomatoes; fill bread; season lightly."); addNeed(db,id,"bread",2,"slice");addNeed(db,id,"tomato",1,"pcs");addNeed(db,id,"salt",1,"g");
 id=addRecipe(db,"Cheese Sandwich","Arrange cheese between two slices of bread."); addNeed(db,id,"bread",2,"slice");addNeed(db,id,"cheese",30,"g");
 id=addRecipe(db,"Banana Oats","Simmer oats in milk; top with banana slices."); addNeed(db,id,"oats",50,"g");addNeed(db,id,"milk",200,"ml");addNeed(db,id,"banana",1,"pcs");
 id=addRecipe(db,"Honey Oats","Cook oats in water; stir in honey."); addNeed(db,id,"oats",50,"g");addNeed(db,id,"water",200,"ml");addNeed(db,id,"honey",15,"g");
 id=addRecipe(db,"Rice with Butter","Cook rice in water until tender; stir in butter."); addNeed(db,id,"rice",100,"g");addNeed(db,id,"water",200,"ml");addNeed(db,id,"butter",10,"g");
 id=addRecipe(db,"Garlic Rice","Cook rice; lightly fry chopped garlic in oil; stir together."); addNeed(db,id,"rice",100,"g");addNeed(db,id,"water",200,"ml");addNeed(db,id,"garlic",2,"pcs");addNeed(db,id,"oil",10,"ml");
 id=addRecipe(db,"Simple Pasta","Boil pasta in salted water; drain and drizzle with oil."); addNeed(db,id,"pasta",100,"g");addNeed(db,id,"water",500,"ml");addNeed(db,id,"oil",10,"ml");addNeed(db,id,"salt",2,"g");
 id=addRecipe(db,"Pasta with Tomato","Cook pasta; simmer chopped tomato in oil; combine."); addNeed(db,id,"pasta",100,"g");addNeed(db,id,"tomato",2,"pcs");addNeed(db,id,"oil",10,"ml");addNeed(db,id,"water",500,"ml");
 id=addRecipe(db,"Egg Fried Rice","Fry cooked rice and beaten egg in oil until cooked."); addNeed(db,id,"rice",100,"g");addNeed(db,id,"egg",1,"pcs");addNeed(db,id,"oil",10,"ml");addNeed(db,id,"water",200,"ml");
 id=addRecipe(db,"Mashed Potatoes","Boil potatoes; mash with butter and milk."); addNeed(db,id,"potato",3,"pcs");addNeed(db,id,"butter",15,"g");addNeed(db,id,"milk",50,"ml");addNeed(db,id,"water",500,"ml");
 id=addRecipe(db,"Roasted Potatoes","Dice potatoes, coat with oil and salt, roast until tender."); addNeed(db,id,"potato",3,"pcs");addNeed(db,id,"oil",20,"ml");addNeed(db,id,"salt",2,"g");
 id=addRecipe(db,"Banana Smoothie","Blend banana and milk until smooth."); addNeed(db,id,"banana",1,"pcs");addNeed(db,id,"milk",250,"ml");
 id=addRecipe(db,"Lemon Water","Stir lemon juice into water and serve chilled."); addNeed(db,id,"lemon",1,"pcs");addNeed(db,id,"water",250,"ml");
 id=addRecipe(db,"Peanut Butter Toast","Toast bread and spread peanut butter."); addNeed(db,id,"bread",2,"slice");addNeed(db,id,"peanut butter",25,"g");
 id=addRecipe(db,"Simple Pancakes","Mix flour, egg, milk; cook small pancakes with oil."); addNeed(db,id,"flour",100,"g");addNeed(db,id,"egg",1,"pcs");addNeed(db,id,"milk",150,"ml");addNeed(db,id,"oil",10,"ml");
 }
 public List<Ingredient> getPantry(){List<Ingredient> out=new ArrayList<>();try(Cursor c=getReadableDatabase().rawQuery("SELECT id,name,quantity,unit,expiry FROM pantry ORDER BY name",null)){while(c.moveToNext())out.add(new Ingredient(c.getLong(0),c.getString(1),c.getDouble(2),c.getString(3),c.getString(4)));}return out;}
 public Ingredient getIngredient(long id){try(Cursor c=getReadableDatabase().rawQuery("SELECT id,name,quantity,unit,expiry FROM pantry WHERE id=?",new String[]{Long.toString(id)})){if(c.moveToFirst())return new Ingredient(c.getLong(0),c.getString(1),c.getDouble(2),c.getString(3),c.getString(4));}return null;}
 public void saveIngredient(long id,String name,double quantity,String unit,String expiry){ContentValues v=new ContentValues();v.put("name",name.trim());v.put("quantity",quantity);v.put("unit",unit);v.put("expiry",expiry.isEmpty()?null:expiry);if(id<0)getWritableDatabase().insertOrThrow("pantry",null,v);else getWritableDatabase().update("pantry",v,"id=?",new String[]{Long.toString(id)});}
 public void deleteIngredient(long id){getWritableDatabase().delete("pantry","id=?",new String[]{Long.toString(id)});}
 public List<Recipe> allRecipes(){List<Recipe> out=new ArrayList<>();try(Cursor c=getReadableDatabase().rawQuery("SELECT id,title,method FROM recipes ORDER BY title",null)){while(c.moveToNext())out.add(new Recipe(c.getLong(0),c.getString(1),c.getString(2)));}return out;}
 public Recipe getRecipe(long id){try(Cursor c=getReadableDatabase().rawQuery("SELECT id,title,method FROM recipes WHERE id=?",new String[]{Long.toString(id)})){if(c.moveToFirst())return new Recipe(c.getLong(0),c.getString(1),c.getString(2));}return null;}
 public List<Ingredient> getNeeds(long recipeId){List<Ingredient> out=new ArrayList<>();try(Cursor c=getReadableDatabase().rawQuery("SELECT id,name,quantity,unit,NULL FROM recipe_ingredients WHERE recipe_id=?",new String[]{Long.toString(recipeId)})){while(c.moveToNext())out.add(new Ingredient(c.getLong(0),c.getString(1),c.getDouble(2),c.getString(3),null));}return out;}
 public List<Recipe> suggestions(){List<Ingredient> pantry=getPantry();List<Recipe> out=new ArrayList<>();for(Recipe r:allRecipes()){boolean all=true;for(Ingredient need:getNeeds(r.id)){double total=0;for(Ingredient have:pantry){if(IngredientMatcher.canonical(have.name).equals(IngredientMatcher.canonical(need.name))){double converted=IngredientMatcher.convert(have.quantity,have.unit,need.unit);if(!Double.isNaN(converted))total+=converted;}}if(total+0.000001<need.quantity){all=false;break;}}if(all)out.add(r);}return out;}
}