package org.example.mini;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableRow.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ViewDetails extends Activity implements OnClickListener{
	
   ArmyDB objDB;
   Cursor cursor;
   String no,rank,name,trade,subunit,dob,doe,wpn,regno,buttno;
   String months[]={"Jan","Feb","Mar","Apr","June","July","Aug","Sep","Oct","Nov","Dec"};
   boolean val;
   
	public void onCreate(Bundle savedInstanceState)
	{
	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.view_details);
	   TableLayout tl=(TableLayout) findViewById(R.id.alldettable_label);
	   
	   Button backButton=(Button) findViewById(R.id.detback_label);
	   backButton.setOnClickListener(this);
	   
	   objDB=new ArmyDB(this);
	   objDB.open();
	   val=objDB.chkIfEmpty("Indl_Details");
	   if(val)
		   Toast.makeText(getBaseContext(), "No individual's details present in the details table.", Toast.LENGTH_LONG).show();
	   else
	   {
	   cursor=objDB.getAllSoldierDetails();
	   
	   cursor.moveToFirst();
	   if(cursor.moveToFirst())
	   {
	   do
	   {
		   TableRow tr=new TableRow(this);
		   TextView tv1=new TextView(this);
		   TextView tv2=new TextView(this);
		   TextView tv3=new TextView(this);
		   TextView tv4=new TextView(this);
		   TextView tv5=new TextView(this);
		   TextView tv6=new TextView(this);
		   TextView tv7=new TextView(this);
		   TextView tv8=new TextView(this);
		   TextView tv9=new TextView(this);
		   TextView tv10=new TextView(this);
		   
		   no=cursor.getString(cursor.getColumnIndex("Armyno"));
		   rank=cursor.getString(cursor.getColumnIndex("Rank"));
		   name=cursor.getString(cursor.getColumnIndex("Name"));
		   trade=cursor.getString(cursor.getColumnIndex("Trade"));
		   subunit=cursor.getString(cursor.getColumnIndex("Subunit"));
		   dob=cursor.getString(cursor.getColumnIndex("DOB"));
		   String idob[]=dob.split("-");
		   String m=months[Integer.parseInt(idob[1])-1];
		   dob=idob[2]+"-"+m+"-"+idob[0];
		   
		   doe=cursor.getString(cursor.getColumnIndex("DOE"));
		   String idoe[]=doe.split("-");
		   m=months[Integer.parseInt(idoe[1])-1];
		   doe=idoe[2]+"-"+m+"-"+idoe[0];
		   
		   wpn=cursor.getString(cursor.getColumnIndex("WPN"));
		   regno=cursor.getString(cursor.getColumnIndex("Regno"));
		   buttno=cursor.getString(cursor.getColumnIndex("Buttno"));
		   tr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
		   tv1.setText(no);
		   tv1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		   tr.addView(tv1);
		   tv2.setText(rank);
		   tv2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		   tr.addView(tv2);
		   tv3.setText(name);
		   tv3.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		   tr.addView(tv3);
		   tv4.setText(trade);
		   tv4.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		   tr.addView(tv4);
		   tv5.setText(subunit);
		   tv5.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		   tr.addView(tv5);
		   tv6.setText(dob);
		   tv6.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		   tr.addView(tv6);
		   tv7.setText(doe);
		   tv7.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		   tr.addView(tv7);
		   tv8.setText(wpn);
		   tv8.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		   tr.addView(tv8);
		   tv9.setText(regno);
		   tv9.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		   tr.addView(tv9);
		   tv10.setText(buttno);
		   tv10.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		   tr.addView(tv10);  
		   tl.addView(tr,new TableLayout.LayoutParams(
                   LayoutParams.FILL_PARENT,
                   LayoutParams.WRAP_CONTENT));
	   }while(cursor.moveToNext());
	  }
	   }
	   
	   objDB.close();
	}
	
	public void onClick(View v)
	{
		Intent i;
		switch(v.getId())
		{
		 case R.id.detback_label:
			 Toast.makeText(getBaseContext(), "Going back to Input Manually Page.", Toast.LENGTH_SHORT).show();
			  i=new Intent(this,InputManually.class);
              startActivity(i);
              break;
		}
	}

}
