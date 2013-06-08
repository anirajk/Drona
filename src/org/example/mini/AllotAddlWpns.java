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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TableRow.LayoutParams;

public class AllotAddlWpns extends Activity implements OnClickListener,OnItemSelectedListener{
 TextView ctrTextView[];
 Button buttB[],regB[],allButton,infoButton,backButton,okButton,gen;
 ArmyDB objDB;
 String s,no,wpn,butt,reg,preWpn[],firstWpn[],curWpn[];
 Cursor cursor,cursor1;
 CheckBox cb[];
 TextView noTV[];
 Spinner wpnS[];
 int ctr[],nof,i,flag=0,sflag=0,index,bId=500,rId=800,tot;
 TableLayout tl;
 ArrayAdapter<CharSequence> adapter;
 EditText noET;
 boolean bflag[];


 public void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.allot_addl_wpns);

  ctrTextView=new TextView[6];
  ctrTextView[0]=(TextView) findViewById(R.id.allotaddlwpn1_label);
  ctrTextView[1]=(TextView) findViewById(R.id.allotaddlwpn2_label);
  ctrTextView[2]=(TextView) findViewById(R.id.allotaddlwpn3_label);
  ctrTextView[3]=(TextView) findViewById(R.id.allotaddlwpn4_label);
  ctrTextView[4]=(TextView) findViewById(R.id.allotaddlwpn5_label);
  ctrTextView[5]=(TextView) findViewById(R.id.allotaddlwpn6_label);
  ctr=new int[6];
  ctr[0]=ctr[1]=ctr[2]=ctr[3]=ctr[4]=ctr[5]=0;

  

  allButton=(Button) findViewById(R.id.allotaddlselectall_label);
  allButton.setOnClickListener(this);
  infoButton=(Button) findViewById(R.id.allotaddlinfo_label);
  infoButton.setOnClickListener(this);
  backButton=(Button) findViewById(R.id.allotaddlback_label);
  backButton.setOnClickListener(this);
  okButton=(Button) findViewById(R.id.allotaddlok_label);
  okButton.setOnClickListener(this);
  
  objDB=new ArmyDB(this);
  
  //nof=cursor.getCount();
  /*cb=new CheckBox[nof];
  noTV=new TextView[nof];
  wpnS=new Spinner[nof];
  buttB=new Button[nof];
  regB=new Button[nof]; */  

  adapter=ArrayAdapter.createFromResource(this, R.array.weapon_array,android.R.layout.simple_spinner_item);
  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

  displayWpns();
  

 }

 public void displayWpns()
 {
  i=0;
  nof=0;
  tl=(TableLayout) findViewById(R.id.allotaddlwpnstable);
  
  objDB.open();
  tot=objDB.noOfRows("Firer_Selection");
  
  cb=new CheckBox[tot];
  noTV=new TextView[tot];
  wpnS=new Spinner[tot];
  buttB=new Button[tot];
  regB=new Button[tot];  
  bflag=new boolean[tot];

  
  firstWpn=new String[tot];
  preWpn=new String[tot];
  curWpn=new String[tot];
  cursor=objDB.getWpns();
  cursor.moveToFirst();
  do
  {
    TableRow tr=new TableRow(this);
    tr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
    
    cb[i]=new CheckBox(this);
    cb[i].setOnClickListener(this);
    tr.addView(cb[i]);
   
    bflag[i]=false;
    noTV[i]=new TextView(this);
    noTV[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    noTV[i].setText(cursor.getString(0));
    tr.addView(noTV[i]); 

    cursor1=objDB.getIndlWpns(cursor.getString(0));
    cursor1.moveToFirst();
    
    TextView tv1=new TextView(this);
    tv1.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv1.setText(cursor1.getString(0));
    tr.addView(tv1);

    TextView tv2=new TextView(this);
    tv2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv2.setText(cursor1.getString(1));
    tr.addView(tv2);
   
    wpnS[i]=new Spinner(this);
    wpnS[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    wpnS[i].setClickable(false);
    wpnS[i].setAdapter(adapter);
    wpnS[i].setOnItemSelectedListener(this);
    sflag=1;
    firstWpn[i]=curWpn[i]=cursor1.getString(2);
    wpnS[i].setSelection(adapter.getPosition(cursor1.getString(2)),true);
    sflag=0;
    tr.addView(wpnS[i]);

    buttB[i]=new Button(this);
    buttB[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    buttB[i].setOnClickListener(this);
    buttB[i].setClickable(false);
    buttB[i].setId(bId);
    buttB[i].setText(cursor1.getString(3));
    tr.addView(buttB[i]);
    bId++;
 
    regB[i]=new Button(this);
    regB[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    regB[i].setOnClickListener(this);
    regB[i].setClickable(false);
    regB[i].setId(rId);
    regB[i].setText(cursor1.getString(4));
    tr.addView(regB[i]);
    rId++; 

    tl.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT)); 
  
  cursor1.close();
  i++;
  nof++;
  }while(cursor.moveToNext());
  objDB.close();
  
  
 }

 public void onItemSelected(AdapterView<?> parent, View v, int pos, long row)
 {
	 
	 if(sflag==0)
	  {
	   for(i=0;i<nof;i++)
	   {
	    if(((Spinner)parent)==wpnS[i])
	    {
	     preWpn[i]=curWpn[i];
	     curWpn[i]=parent.getItemAtPosition(pos).toString();
	  if(firstWpn[i].equals(preWpn[i]))
	  {
	   if(!(curWpn[i].equals(preWpn[i]))) 
	   {
	    index=adapter.getPosition(curWpn[i]);
	    ctr[index]++;  
	    ctrTextView[index].setText(Integer.toString(ctr[index]));  
	   }
	  }
	  else
	  {
	   if(!(curWpn[i].equals(preWpn[i]))&&!(curWpn[i].equals(firstWpn[i])))
	   {
	    index=adapter.getPosition(preWpn[i]);
	    ctr[index]--; 
	    ctrTextView[index].setText(Integer.toString(ctr[index]));  
	    index=adapter.getPosition(curWpn[i]);
	    ctr[index]++;  
	    ctrTextView[index].setText(Integer.toString(ctr[index]));
	   }
	   if(curWpn[i].equals(firstWpn[i]))
	   {
	    index=adapter.getPosition(preWpn[i]);
	    ctr[index]--;
	    ctrTextView[index].setText(Integer.toString(ctr[index]));
	   }
	  }
	  break;
	 }
	 }
	  }

 }
 
public void onNothingSelected(AdapterView<?> parent)
{
	
}

private void showAddDialog(String s)
{
 //final String TAG = "test";
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
   case R.id.allotaddlselectall_label:
        flag=1;
        for(i=0;i<nof;i++)
        {
          bflag[i]=true;
          cb[i].setChecked(true);
          wpnS[i].setClickable(true);
          regB[i].setClickable(true);
          buttB[i].setClickable(true);
        }
        break;
   case R.id.allotaddlok_label:
        flag=1;
        objDB.open();
        for(i=0;i<nof;i++)
        {
         if(cb[i].isChecked())
         {
          no=noTV[i].getText().toString().trim();;
          wpn=wpnS[i].getSelectedItem().toString();
          butt=buttB[i].getText().toString().trim();
          reg=regB[i].getText().toString().trim();
          objDB.updateWpn(no,wpn,butt,reg);
         }
        }
        objDB.close();
        in=new Intent(this,InputData.class);
        startActivity(in);
        break;
   case R.id.allotaddlback_label:
        flag=1;
        in=new Intent(this,WpnAllotment.class);
        startActivity(in);
        break;
   case R.id.allotaddlinfo_label:
        flag=1;
        break;
  }
        if(flag==0)
        {
         for(i=0;i<nof;i++)
         {
          if(cb[i].isChecked())
          {
           if(!bflag[i])
           {
        	bflag[i]=true;
            wpnS[i].setClickable(true);
            regB[i].setClickable(true);
            buttB[i].setClickable(true);
            flag=2;
            sflag=0;
           }
          }
          else
          {
           if(bflag[i])
           {
        	bflag[i]=false;
            cursor.moveToPosition(i);
            sflag=1;
            wpn=wpnS[i].getSelectedItem().toString();
            if(!wpn.equals(cursor.getString(1)))
            {
             index=adapter.getPosition(wpn);
             ctr[index]--; 
             ctrTextView[index].setText(Integer.toString(ctr[index]));
             wpnS[i].setSelection(adapter.getPosition(cursor.getString(1)),true);
            }
            sflag=0;
            wpnS[i].setClickable(false);
            regB[i].setClickable(false);
            buttB[i].setClickable(false);
            
            flag=2;
           }
           
          }
         } 
         
        }  
        if(flag==0)
        {
         //Button click
         for(i=0;i<nof;i++)
         {
          if(v.getId()==buttB[i].getId())
          {
           gen=buttB[i];
           showAddDialog("Butt");
           break;
          }
          else if(v.getId()==regB[i].getId())
          {
           gen=regB[i];
           showAddDialog("Register");
           break;
          }
         }
        }
   
  }
}


