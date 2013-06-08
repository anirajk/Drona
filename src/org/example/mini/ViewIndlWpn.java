package org.example.mini;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.TableRow.LayoutParams;

public class ViewIndlWpn extends Activity implements OnClickListener{
 Button backButton;
 ArmyDB objDB;
 boolean val;
 
 public void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.view_indl_wpn);

  backButton=(Button) findViewById(R.id.indlwpnback_label);
  backButton.setOnClickListener(this);

  objDB=new ArmyDB(this);
 
  viewWpn();
 }

 public void viewWpn()
 {
  Cursor cursor,cursor1;
  TableLayout tl=(TableLayout) findViewById(R.id.indlwpntable);
  objDB.open();
  val=objDB.chkIfEmpty("Firer_Selection");
  if(val)
	  Toast.makeText(getBaseContext(), "Firers must be selected first.", Toast.LENGTH_LONG).show();
  else
  {
  cursor=objDB.getSelectedFirers();
 
  cursor.moveToFirst();
  do
  {
   TableRow tr=new TableRow(this);
   tr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));

   TextView tv1=new TextView(this);
   tv1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
   tv1.setText(cursor.getString(0));
   tr.addView(tv1);

   cursor1=objDB.getIndlWpns(cursor.getString(0));
   cursor1.moveToFirst();
   
   TextView tv2=new TextView(this);
   tv2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
   tv2.setText(cursor1.getString(0));
   tr.addView(tv2);

   TextView tv3=new TextView(this);
   tv3.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
   tv3.setText(cursor1.getString(1));
   tr.addView(tv3);

   TextView tv4=new TextView(this);
   tv4.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
   tv4.setText(cursor1.getString(2));
   tr.addView(tv4);

   TextView tv5=new TextView(this);
   tv5.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
   tv5.setText(cursor1.getString(3));
   tr.addView(tv5);

   TextView tv6=new TextView(this);
   tv6.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
   tv6.setText(cursor1.getString(4));
   tr.addView(tv6);

   tl.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
   
   cursor1.close();

  }while(cursor.moveToNext());
  objDB.close();
  cursor.close();
  }
 }

 public void onClick(View v)
 {
  switch(v.getId())
  {
   case R.id.indlwpnback_label:
        Intent i=new Intent(this,WpnAllotment.class);
        startActivity(i);
        break;
        
 }
 }
}


