package org.example.mini;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class ArmyDB {

	 private static final String DATABASE_NAME="Army";
	 private static final String DATABASE_TABLE_DETAILS="Indl_Details";
	 private static final String DATABASE_TABLE_FIRERS="Firer_Selection";
	 private static final String DATABASE_TABLE_WEAPONS="Weapon";
	 private static final String DATABASE_TABLE_RESULTS="Results";
	 
	 private static final int DATABASE_VERSION=1;
	 public static final String NO="Armyno";
	 public static final String RANK="Rank";
	 public static final String NAME="Name";
	 public static final String TRADE="Trade";
	 public static final String SUBUNIT="Subunit";
	 public static final String DOB="DOB";
	 public static final String DOE="DOE";
	 public static final String WPN="WPN";
	 public static final String REGNO="Regno";
	 public static final String BUTTNO="Buttno";
	 
	 
	 private SQLiteDatabase db;
	 private final Context context;
	 Cursor cursor,cursor1;
	 
	  private static class InputDBOpenHelper extends SQLiteOpenHelper{
		 InputDBOpenHelper(Context c,String n,CursorFactory cf,int v)
		 {
			 super(c,n,cf,v);
		 }
		 private static final String DATABASE_CREATE="CREATE TABLE "+DATABASE_TABLE_DETAILS+"(" +
		 		"_id INTEGER PRIMARY KEY AUTOINCREMENT,Armyno TEXT UNIQUE NOT NULL,Rank TEXT NOT NULL,Name TEXT NOT NULL," +
		 		"Trade TEXT DEFAULT NULL,Subunit TEXT NOT NULL,DOB DATE NOT NULL,DOE DATE NOT NULL,WPN TEXT NOT NULL," +
		 		"Regno TEXT UNIQUE NOT NULL,Buttno INTEGER NOT NULL);";
		 
		 private static final String FIRERS_TABLE="CREATE TABLE "+DATABASE_TABLE_FIRERS+"(" +
	 		"_id INTEGER PRIMARY KEY AUTOINCREMENT,No TEXT UNIQUE NOT NULL,Sunit TEXT NOT NULL);";
	 
		 private static final String WEAPON_TABLE="CREATE TABLE "+DATABASE_TABLE_WEAPONS+"(" +
	 		"_id INTEGER PRIMARY KEY AUTOINCREMENT,No TEXT UNIQUE NOT NULL,Detailno INTEGER NOT NULL,WPN TEXT NOT NULL," +
	 		"Reg TEXT UNIQUE NOT NULL,Butt INTEGER NOT NULL);";
	 
		 private static final String RESULTS_TABLE="CREATE TABLE "+DATABASE_TABLE_RESULTS+"(" +
	 		"_id INTEGER PRIMARY KEY AUTOINCREMENT,NoId TEXT NOT NULL,DOF DATE NOT NULL,Visibility TEXT NOT NULL," +
	 		"WpnType TEXT NOT NULL,Position TEXT NOT NULL,TargetType TEXT NOT NULL,Range INTEGER NOT NULL," +
	 		"WPN TEXT NOT NULL,Reg TEXT UNIQUE NOT NULL,Butt INTEGER NOT NULL,Rounds INTEGER NOT NULL," +
	 		"Points INTEGER NOT NULL,STD TEXT NOT NULL);";
	 
		 
		 public void onCreate(SQLiteDatabase sq)
		 {
			 sq.execSQL(DATABASE_CREATE);
			 sq.execSQL(FIRERS_TABLE);
			 sq.execSQL(WEAPON_TABLE);
			 sq.execSQL(RESULTS_TABLE);
			 
		 }
		 public void onUpgrade(SQLiteDatabase sq,int oldv,int newv)
		 {
			 Log.w("ArmyDB","Upgrading from version " 
			 + oldv + " to " + newv + ", which will destroy all old data");
			 sq.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE_DETAILS);
			 onCreate(sq);
		 }
	 }
	 
	  private InputDBOpenHelper dbHelper;
		 public ArmyDB(Context c)
		 {
			 this.context=c;
			 dbHelper=new InputDBOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION);
		 }
		 
	  
	 public void open() throws SQLiteException
	 {
		 try
		 {
			 db=dbHelper.getWritableDatabase();
		 }
		 catch(SQLiteException ex)
		 {
			 db=dbHelper.getReadableDatabase();
		 }
	 }
	 public void close()
	 {
		 db.close();
	 }
	 public long insertDetails(String no,String rank,String name,String trade,String subunit,
			                   String dob,String doe,String wpn,int regno,int buttno) throws SQLiteException
	 {
	   /*String s1="SELECT "+NO+" FROM "+DATABASE_TABLE_DETAILS+" WHERE "+NO+"="+no,
	          s2="SELECT "+NO+" FROM "+DATABASE_TABLE_DETAILS+" WHERE "+REGNO+"="+regno;
	   Cursor cursor= db.rawQuery(s1,null);
	  if(cursor.getCount()!=0)
	   return 0;
	  cursor= db.rawQuery(s2,null);
	  if(cursor.getCount()!=0)
		   return -1;*/
	  
		  ContentValues cv=new ContentValues();
		  cv.put(NO, no);
		  cv.put(RANK, rank);
		  cv.put(NAME, name);
		  cv.put(TRADE, trade);
		  cv.put(SUBUNIT, subunit);
		  cv.put(DOB, dob);
		  cv.put(DOE, doe);
		  cv.put(WPN, wpn);
		  cv.put(REGNO, regno);
		  cv.put(BUTTNO, buttno);
		  return db.insert(DATABASE_TABLE_DETAILS, null, cv);
		  
		  
	 
		  
	 }
	 public Cursor getAllSoldierDetails() throws SQLException
	 {
		 cursor=db.query(DATABASE_TABLE_DETAILS, null, null, null, null, null, null);
		 if((cursor.getCount()==0)||!cursor.moveToFirst())
			 throw new SQLException("No records in the database.");
		 return cursor;
	 }
	 public Cursor getOneSoldierDetail(String s) throws SQLException
	 {
		 cursor=db.rawQuery("SELECT COUNT(*) FROM "+DATABASE_TABLE_DETAILS+" WHERE Armyno=?", new String[]{s});
		 cursor.moveToFirst();
		 if(!(cursor.getString(0).equals("0")))
	    {
	      cursor.close();
		  cursor=db.query(DATABASE_TABLE_DETAILS, new String[]{RANK,NAME,TRADE,SUBUNIT,DOB,DOE,WPN,REGNO,BUTTNO},
				  NO + "=?", new String[]{s}, null, null, null); 
		
		 }		   
		 
		  if((cursor.getCount()==0)||!cursor.moveToFirst())
			 throw new SQLException("No record found with the given id in the database.");
		 return cursor;
	 }
	 public boolean update(String oldNO,String no,String rank,String name,String trade,String subunit,
             String dob,String doe,String wpn,int regno,int buttno) throws SQLiteException
	 {
		 ContentValues cv=new ContentValues();
		 cv.put(NO, no);
		  cv.put(RANK, rank);
		  cv.put(NAME, name);
		  cv.put(TRADE, trade);
		  cv.put(SUBUNIT, subunit);
		  cv.put(DOB, dob);
		  cv.put(DOE, doe);
		  cv.put(WPN, wpn);
		  cv.put(REGNO, regno);
		  cv.put(BUTTNO, buttno);
		  return db.update(DATABASE_TABLE_DETAILS, cv, NO + "=?", new String[]{oldNO})>0;
	 }
	 
	 
	 // Delete from Main Table
	 
	 public void deleteAllDetails()
	 {
	  db.delete(DATABASE_TABLE_DETAILS,null,null);
	 }

	 public void deleteSubunitFromDetails(String s)
	 {
	  db.delete(DATABASE_TABLE_DETAILS,"Subunit=?", new String[] {s});
	 }

	 public void deleteIndlDetail(String s)
	 {
	  db.delete(DATABASE_TABLE_DETAILS, "Armyno=?", new String[] {s});
	 }

	 
	 
	 public void execStatement(String s)
	 {
	  db.execSQL(s);
	 }

	 public int noOfRows(String s)
	 {
		 cursor=db.rawQuery("SELECT count(*) FROM "+s,null);
		 cursor.moveToFirst();
		 return cursor.getInt(0);
	 }
	 
	 public void deleteSunitFromFirers(String s)
	 {
	  db.delete("Firer_Selection","Sunit=?",new String[]{s});
	 }

	 public Cursor getFirersOfASubunit(String s)
	 {
	  if(s!=null)
	  cursor=db.query(DATABASE_TABLE_FIRERS, new String[]{"No"}, "Sunit=?",new String[]{s}, null, null, null);
	  else
	  cursor=db.query(DATABASE_TABLE_FIRERS, new String[]{"No"}, null, null, null, null, null);
	  	  
	  return cursor;
	 }

	 public Cursor getDetailsOfSunitFirers(String s)
	 {
	  cursor=db.query(DATABASE_TABLE_DETAILS, new String[]{RANK,NAME,SUBUNIT,WPN}, "Armyno=?",new String[]{s}, null, null, null);
	  return cursor;
	 }
	 
	 public Cursor getDetails4SelectManually(String s)
	 {
	  cursor=db.query(DATABASE_TABLE_DETAILS, new String[]{RANK,NAME,BUTTNO,REGNO}, "Armyno=?",new String[]{s}, null, null, null);
		  return cursor;	 
	 }
	 
	 public boolean chkIfEmpty(String s)
	 {
	  cursor= db.rawQuery("SELECT count(*) FROM "+s,null);
	  cursor.moveToFirst();
	  if(cursor.getString(0).equals("0"))
	  {
	   cursor.close();
	   return true;
	  }
	  else
	  {
	   cursor.close();
	   return false;
	  }
	 }

	 public Cursor getDetails(String s)
	 {
	  cursor=db.rawQuery("SELECT count(*) FROM Results WHERE NoId=?",new String[] {s});

	  cursor.moveToFirst();
	  if(!cursor.getString(0).equals("0"))
	  {
	   cursor.close();
	   cursor=db.rawQuery("SELECT max(DOF),STD FROM Results WHERE NoId=?",new String[] {s});
	  }
	  return cursor;
	 }

	 public void deleteAFirer(String s)
	 {
	  db.delete("Firer_Selection","No=?",new String[]{s});
	 }
	 
	 public void deleteFirersNotSelected(String type,String value)
	 {
		 cursor=db.query(DATABASE_TABLE_DETAILS, new String[]{NO}, type+"!=?", new String[]{value}, null, null, null);
		 cursor.moveToFirst();
		 do
		 {
			 db.delete(DATABASE_TABLE_FIRERS, "No=?", new String[]{cursor.getString(0)});
		 }while(cursor.moveToNext());
		 cursor.close();
	 }
	 
/*	 public Cursor getAllFirers()
	 {
	  cursor=db.rawQuery("SELECT Armyno,Rank,Name,Buttno,Regno FROM Indl_Details WHERE Armyno IN (SELECT No FROM Firer_Selection)",null);
	  return cursor;
	 }*/
	  
	/* public Cursor get3Details(String s)
	 {
	  
	 }*/

	 public Cursor getSelectedFirers()
	 {
		 
		 cursor=db.query(DATABASE_TABLE_FIRERS, new String[]{"No"}, null, null, null, null, null);
		 return cursor;
	 }
	 
	 public Cursor getIndlWpns(String s)
	 {
	  cursor=db.query(DATABASE_TABLE_DETAILS, new String[]{RANK,NAME,WPN,BUTTNO,REGNO}, "Armyno=?", new String[]{s}, null, null,null);
	  return cursor;
	 }

	 public void updateWpn(String no,String wpn,String butt,String reg)
	 {
	  ContentValues cv=new ContentValues();
	  cv.put("WPN",wpn);
	  cv.put("Butt",Integer.parseInt(butt));
	  if(reg!=null)
	  cv.put("Reg",reg);
	  db.update("Weapon",cv,"No=?",new String[]{no});
	 }  
	 
	 public void insertFirers(String wpn)
	 {
		 if(wpn!=null)
		 cursor=db.query(DATABASE_TABLE_DETAILS, new String[] {NO,SUBUNIT}, "WPN=?", new String[]{wpn}, null, null, null);
		 else
		 cursor=db.query(DATABASE_TABLE_DETAILS, new String[] {NO,SUBUNIT}, null, null, null, null, null);
		 	 
		 cursor.moveToFirst();
		 do
		 {
			 ContentValues cv=new ContentValues();
			 cv.put("No", cursor.getString(0));
			 cv.put("Sunit", cursor.getString(1));
			 db.insert(DATABASE_TABLE_FIRERS, null, cv);
		 }while(cursor.moveToNext());
	 }

	 public void deleteTable(String s)
	 {
		 db.delete(s, null, null);
	 }
	 
	 public void insertWpns(String no,String wpn,String reg,int butt)
	 {
		 ContentValues cv=new ContentValues();
		  cv.put("No", no);
		  cv.put("Detailno", 0);
		  cv.put("WPN",wpn);
		  cv.put("Butt",butt);
		  cv.put("Reg",reg);
		  db.insert(DATABASE_TABLE_WEAPONS, null, cv);
		  
	 }
	 
	 public Cursor getWpns()
	 {
		 cursor=db.query(DATABASE_TABLE_WEAPONS, new String[] {"No","WPN","Butt"}, null, null, null, null, null);
		 return cursor;
	 }
	 
	 public Cursor firersForADetail(String s)
	 {
	  cursor=db.rawQuery("SELECT count(*) FROM Weapon WHERE WPN=? AND Detailno=?",new String[]{s,"0"});

	  cursor.moveToFirst();
	  if(!cursor.getString(0).equals("0"))
	  {
	   cursor.close();
	   cursor=db.query(DATABASE_TABLE_WEAPONS, new String[]{"No","WPN","Butt","Reg"}, "WPN=? AND Detailno=?", new String[]{s,"0"}, null, null, null);
	  }
	  return cursor;
	 }

	 public void updDetailNo(String s,int ctr)
	 {
	  ContentValues cv=new ContentValues();
	  cv.put("Detailno",ctr);
	  if(s!=null)
		  db.update(DATABASE_TABLE_WEAPONS, cv, "No=?", new String[]{s});
	  else
		  db.update(DATABASE_TABLE_WEAPONS, cv, null, null);
	 }

	 public Cursor getADetail(int ctr)
	 {
		 cursor=db.rawQuery("SELECT count(*) FROM Weapon WHERE Detailno=?",new String[]{Integer.toString(ctr)});

		  cursor.moveToFirst();
         if(!cursor.getString(0).equals("0"))
         {
          cursor.close();	 
		  cursor=db.query(DATABASE_TABLE_WEAPONS, new String[] {"No","WPN","Butt","Reg"}, "Detailno=?", new String[]{Integer.toString(ctr)}, null, null, null);
         }
		 return cursor;
	 }
	 
	 public Cursor chkIfDetailPresent()
	 {
		 cursor=db.rawQuery("SELECT count(*) FROM Weapon WHERE Detailno!=?", new String[]{"0"});
		 return cursor;
	 }
	 
	 public Cursor getDetailNo(String s)
	 {
		 cursor=db.query(DATABASE_TABLE_WEAPONS, new String[]{"Detailno"}, "No=?", new String[]{s}, null, null, null);
		 return cursor;
	 }
	 
	 public Cursor getANo()
	 {
		 cursor=db.query(DATABASE_TABLE_WEAPONS, new String[]{"No"}, "Detailno=?", new String[]{"0"}, null, null, null, "1");
		 return cursor;
	 }
	 	 
	 public Cursor getOtherDetails(int n)
	 {
		 cursor=db.query(DATABASE_TABLE_WEAPONS, new String[] {"No","WPN","Butt","Reg"}, "Detailno!=?", new String[]{Integer.toString(n)}, null, null, null);
		 return cursor;
	 }
	 
	 public int remainingFirers()
	 {
		 cursor=db.rawQuery("SELECT count(*) FROM Weapon WHERE Detailno=?", new String[]{"0"});
		 return cursor.getInt(0); 
	 }
}

