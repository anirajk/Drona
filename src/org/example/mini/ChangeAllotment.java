package org.example.mini;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.*;
import android.widget.TableRow.LayoutParams;

public class ChangeAllotment extends Activity implements OnClickListener{

Button b[],allButton,okButton,backButton,infoButton,gen;
Spinner wpnS[];
ArmyDB objDB;
int tot,i,bId=500,flag,nof;
CheckBox cb[];
TextView noTV[];
ArrayAdapter<CharSequence> adapter;
String no,wpn,butt;
EditText noET;
Cursor cursor,cursor1;

public void onCreate(Bundle savedInstanceState)
{
 super.onCreate(savedInstanceState);
 setContentView(R.layout.change_allotment);

 objDB=new ArmyDB(this);
 objDB.open();
 tot=objDB.noOfRows("Firer_Selection");
 objDB.close();

 cb=new CheckBox[tot];
 noTV=new TextView[tot];
 b=new Button[tot];
 wpnS=new Spinner[tot];

 allButton=(Button) findViewById(R.id.changeallotselectall_label);
 allButton.setOnClickListener(this);
 okButton=(Button) findViewById(R.id.changeallotok_label);
 okButton.setOnClickListener(this);
 backButton=(Button) findViewById(R.id.changeallotback_label);
 backButton.setOnClickListener(this);
 infoButton=(Button) findViewById(R.id.changeallotinfo_label);
 infoButton.setOnClickListener(this);

 adapter=ArrayAdapter.createFromResource(this, R.array.weapon_array,android.R.layout.simple_spinner_item);
  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

 displayTable();
} 

public void displayTable()
{
  TableLayout tl=(TableLayout) findViewById(R.id.changeallottable);
  i=nof=0;
  objDB.open();
  cursor=objDB.getWpns();
  
  cursor.moveToFirst();
  
  do
  {
    TableRow tr=new TableRow(this);
    tr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
    
    cb[i]=new CheckBox(this);
    cb[i].setOnClickListener(this);
    tr.addView(cb[i]);
   
    noTV[i]=new TextView(this);
    noTV[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    noTV[i].setText(cursor.getString(0));
    tr.addView(noTV[i]); 

    cursor1=objDB.getDetailsOfSunitFirers(cursor.getString(0));
    cursor1.moveToFirst();
    
    TextView tv1=new TextView(this);
    tv1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv1.setText(cursor1.getString(0));
    tr.addView(tv1);

    TextView tv2=new TextView(this);
    tv2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv2.setText(cursor1.getString(1));
    tr.addView(tv2);
   
    TextView tv3=new TextView(this);
    tv3.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv3.setText(cursor.getString(1));
    tr.addView(tv3);

    TextView tv4=new TextView(this);
    tv4.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv4.setText(cursor.getString(2));
    tr.addView(tv4);

    wpnS[i]=new Spinner(this);
    wpnS[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    wpnS[i].setClickable(false);
    wpnS[i].setAdapter(adapter);
    wpnS[i].setSelection(adapter.getPosition(cursor.getString(1)),true);
    tr.addView(wpnS[i]);

    b[i]=new Button(this);
    b[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    b[i].setOnClickListener(this);
    b[i].setClickable(false);
    b[i].setId(bId);
    b[i].setText(cursor.getString(2));
    tr.addView(b[i]);
    bId++;

    tl.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT)); 
  
  cursor1.close();
  i++;
  nof++;
  }while(cursor.moveToNext());
  objDB.close();


}

private void showAddDialog(String s)
{
 final Dialog loginDialog = new Dialog(this);
 loginDialog.getWindow().setFlags(
 WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
 WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
 loginDialog.setTitle("Add "+s+" Number");

 LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 View dialogView = li.inflate(R.layout.dialogwithtextbox, null);
 loginDialog.setContentView(dialogView);

 loginDialog.show();

 noET=(EditText) dialogView.findViewById(R.id.dialognumber_label);
 Button addButton = (Button) dialogView.findViewById(R.id.dialogok_button);
 Button cancelButton = (Button) dialogView.findViewById(R.id.dialogcancel_button);

 addButton.setOnClickListener(new OnClickListener() {
// @Override
 public void onClick(View v) {
 if(noET.getText().toString().trim().equals(""))	 
	 Toast.makeText(getBaseContext(), "Please enter Number.",Toast.LENGTH_LONG).show();	 
 else
 {
  gen.setText(noET.getText().toString().trim());
  loginDialog.dismiss();
 }
 
 }
});

 cancelButton.setOnClickListener(new OnClickListener() {
// @Override
 public void onClick(View v) {
 loginDialog.dismiss();
 }
});

}


public void onClick(View v)
{
  Intent in;
  flag=0;
  switch(v.getId())
  {
   case R.id.changeallotselectall_label:
        flag=1;
        for(i=0;i<nof;i++)
        {
          cb[i].setChecked(true);
          wpnS[i].setClickable(true);
          b[i].setClickable(true);
        }
        break;
      
   case R.id.changeallotok_label:
        flag=1;
        objDB.open();
        for(i=0;i<nof;i++)
        {
         if(cb[i].isChecked())
         {
          no=noTV[i].getText().toString().trim();;
          wpn=wpnS[i].getSelectedItem().toString();
          butt=b[i].getText().toString().trim();
          objDB.updateWpn(no,wpn,butt,null);
         }
        }
        objDB.close();
        break;

   case R.id.changeallotback_label:
        flag=1; 
        in=new Intent(this,WpnAllotment.class);
        startActivity(in);
        break;
  }

  if(flag==0)
  {
         //Button click
         for(i=0;i<nof;i++)
         {
          if(v.getId()==b[i].getId())
          {
           gen=b[i];
           showAddDialog("Butt");
           break;
          }
         }
   }

  if(flag==0)
        {
         for(i=0;i<nof;i++)
         {
          if(cb[i].isChecked())
          {
           wpnS[i].setClickable(true);
           b[i].setClickable(true);
          }
          else
          {
           wpnS[i].setClickable(false);
           b[i].setClickable(false);
          }
         }
        }

}



}
