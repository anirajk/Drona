package org.example.mini;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.TableRow.LayoutParams;


public class SelFirerManually extends Activity implements OnClickListener{
 EditText wpnEditText,tradeEditText,sunitEditText;
 TextView noTextView,noSelectedTextView;
 Button allButton,okButton,addlButton,backButton,infoButton;
 String wpn,trade,sunit,empty="-";
 int i,num=20,counter=0,flag=0,chkd,no=0;
 ArmyDB objDB;
 Cursor cursor,cursor1;
 CheckBox cb[];
 TextView tv1[],tv2[],tv3[],tv4[],tv5[],tv6[],tv7[],tv8[];
 TableLayout tl; 
 boolean ef=true,val,bflag[];

 public void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.sel_firer_manually);
  
  objDB=new ArmyDB(this);

  chkd=getIntent().getIntExtra("org.example.mini.Checked", 0);
  
  if((chkd&1)==1)
  wpn=getIntent().getStringExtra("org.example.mini.Wpn");
  if((chkd&4)==4)
  trade=getIntent().getStringExtra("org.example.mini.Trade");
  if((chkd&8)==8)
  sunit=getIntent().getStringExtra("org.example.mini.Sunit");
  if((chkd&2)==2)
  {
	   num=Integer.parseInt(getIntent().getStringExtra("org.example.mini.No"));
	   no=num;
  }

  wpnEditText=(EditText) findViewById(R.id.selmanwpntextbox_label);
  wpnEditText.setText(wpn);
  tradeEditText=(EditText) findViewById(R.id.selmantradetextbox_label);
  tradeEditText.setText(trade);
  sunitEditText=(EditText) findViewById(R.id.selmansunittextbox_label);
  sunitEditText.setText(sunit);
  noTextView=(TextView) findViewById(R.id.selmantotalfirers_label);
  if(no==num)
    noTextView.setText(Integer.toString(num));
  else
	  noTextView.setText("");
  noSelectedTextView=(TextView) findViewById(R.id.selmanfirersselected_label);
   
  allButton=(Button) findViewById(R.id.selmanselectall_label);
  allButton.setOnClickListener(this);
  okButton=(Button) findViewById(R.id.selectmanok_label);
  okButton.setOnClickListener(this);
  backButton=(Button) findViewById(R.id.selectmanback_label);
  backButton.setOnClickListener(this);
  addlButton=(Button) findViewById(R.id.selmanaddl_label);
  addlButton.setOnClickListener(this);
  infoButton=(Button) findViewById(R.id.selectmaninfo_label);
  infoButton.setOnClickListener(this);

  objDB.open();
  val=objDB.chkIfEmpty("Results");
  objDB.close();
  
  if(val)
  {
   ef=false;
   empty="-";
  }


  cb=new CheckBox[2*num];
  tv1=new TextView[2*num];
  tv2=new TextView[2*num];
  tv3=new TextView[2*num];
  tv4=new TextView[2*num];
  tv5=new TextView[2*num];
  tv6=new TextView[2*num];
  tv7=new TextView[2*num];
  tv8=new TextView[2*num];  
  bflag=new boolean[2*num];
  

  
   showFirers();
  
  
  
  
 }

 public void showFirers()
 {
    
  counter=0;
  i=0;
  tl=(TableLayout) findViewById(R.id.selectmanfirerstable);
  objDB.open();
  cursor=objDB.getFirersOfASubunit(null);
  
  cursor.moveToFirst();
  if(cursor.moveToFirst())
  {
  do
  {
    TableRow tr=new TableRow(this);
    tr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
    cb[i]=new CheckBox(this);
    cb[i].setOnClickListener(this);
    tr.addView(cb[i]);

    bflag[i]=false;
    tv1[i]=new TextView(this);
    tv1[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv1[i].setText(cursor.getString(0));
   // tv1[i].setText("-");
    tr.addView(tv1[i]);
    
    cursor1=objDB.getDetails4SelectManually(cursor.getString(0));
    cursor1.moveToFirst();
    
    tv2[i]=new TextView(this);
    tv2[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv2[i].setText(cursor1.getString(0));
   // tv2[i].setText("-");
    tr.addView(tv2[i]);

    tv3[i]=new TextView(this);
    tv3[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv3[i].setText(cursor1.getString(1));
   // tv3[i].setText("-");
    tr.addView(tv3[i]);

    tv4[i]=new TextView(this);
    tv4[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv4[i].setText(cursor1.getString(2));
   // tv4[i].setText("-");
    tr.addView(tv4[i]);

     tv5[i]=new TextView(this);
    tv5[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv5[i].setText(cursor1.getString(3));
   // tv5[i].setText("-");
    tr.addView(tv5[i]);


   // if(ef)
   // cursor1=objDB.get3Details(cursor.getString(0));    

    tv6[i]=new TextView(this);
    tv6[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    if(ef)
    {
     cursor1.moveToFirst();
    if(cursor1.getString(0).equals("0"))
     empty="-";
    else
     empty=cursor1.getString(0);
    }
    tv6[i].setText(empty);
    tr.addView(tv6[i]);

    tv7[i]=new TextView(this);
    tv7[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    if(ef)
    {
    if(cursor1.getString(0).equals("0"))
     empty="-";
    else
     empty=cursor1.getString(1);
    }
    tv7[i].setText(empty);
    tr.addView(tv7[i]);
    
    tv8[i]=new TextView(this);
    tv8[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    if(ef)
    {
    if(cursor1.getString(0).equals("0"))
     empty="-";
    else
     empty=cursor1.getString(2);
    } 
    tv8[i].setText(empty);
    tr.addView(tv8[i]);

    tl.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));

   cursor1.close();
   i++;
   if((chkd&2)==2)
   {
    if(i==num)
    break;
   }
  }while(cursor.moveToNext()); 
  }
  objDB.close();
 }

 public void addlFirers()
 {
   if(cursor.moveToPosition(i))
   {
    objDB.open();
    do
   {
    TableRow tr=new TableRow(this);
    tr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
    cb[i]=new CheckBox(this);
    cb[i].setOnClickListener(this);
    tr.addView(cb[i]);
    
    bflag[i]=false;

    tv1[i]=new TextView(this);
    tv1[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv1[i].setText(cursor.getString(0));
    tr.addView(tv1[i]);
    
    cursor1=objDB.getDetails4SelectManually(cursor.getString(0));
    cursor1.moveToFirst();

    tv2[i]=new TextView(this);
    tv2[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv2[i].setText(cursor1.getString(0));
    tr.addView(tv2[i]);

    tv3[i]=new TextView(this);
    tv3[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv3[i].setText(cursor1.getString(1));
    tr.addView(tv3[i]);

    tv4[i]=new TextView(this);
    tv4[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv4[i].setText(cursor1.getString(2));
    tr.addView(tv4[i]);

    tv5[i]=new TextView(this);
    tv5[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv5[i].setText(cursor1.getString(3));
    tr.addView(tv5[i]);

    //if(ef)
  //  cursor1=objDB.get3Details(cursor.getString(0));  

    tv6[i]=new TextView(this);
    tv6[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    if(ef)
    {
    if(cursor1.getString(0).equals("0"))
     empty="-";
    else
     empty=cursor1.getString(0);
    }
    tv6[i].setText(empty);
    tr.addView(tv6[i]);

    tv7[i]=new TextView(this);
    tv7[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    if(ef)
    {
    if(cursor1.getString(0).equals("0"))
     empty="-";
    else
     empty=cursor1.getString(1);
    }
    tv7[i].setText(empty);
    tr.addView(tv7[i]);
    
    tv8[i]=new TextView(this);
    tv8[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    if(ef)
    {
    if(cursor1.getString(0).equals("0"))
     empty="-";
    else
     empty=cursor1.getString(2);
    }
    tv8[i].setText(empty);
    tr.addView(tv8[i]);
 
    tl.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));

   cursor1.close();
   i++;
   if(i==num)
    break;
  }while(cursor.moveToNext());
  objDB.close();
 }
 else

	   Toast.makeText(getBaseContext(), "No additional firers.", Toast.LENGTH_LONG).show();
 }

 public void onClick(View v)
 {
  flag=0;
  Intent in;
  int j;
  
  
  switch(v.getId())
  {
   case R.id.selmanselectall_label:
        flag=1;
        for(j=0;j<i;j++)
        {
        	cb[j].setChecked(true);
        	bflag[j]=true;
        }
        counter=i;
        noSelectedTextView.setText(Integer.toString(counter)); 
        break;
   
   case R.id.selmanaddl_label:
        flag=1;
        addlFirers();
        break;

   case R.id.selectmanok_label:
        flag=1;
        objDB.open();
        for(j=0;j<i;j++)
        {
         if(!cb[j].isChecked())
          objDB.deleteAFirer(tv1[j].getText().toString());
        }
          objDB.close();
        cursor.close();
        tl=null;
        for(j=0;j<i;j++)
        {
         cb[j]=null;
         tv1[j]=null;
         tv2[j]=null;
         tv3[j]=null;
         tv4[j]=null;
         tv5[j]=null;
         tv6[j]=null;
        }
        
        
          in=new Intent(this,SelectManually.class);
          startActivity(in);
          break;
     case R.id.selectmanback_label:
          flag=1;
          in=new Intent(this,SelectManually.class);
          startActivity(in);
          break;
     case R.id.selectanyinfo_label:
          flag=1;
          break;
    }

   if(flag==0)
   {
   for(j=0;j<i;j++)
   {
    if(cb[j].isChecked())
    {
     if(!bflag[j])
     {
      counter++;
      bflag[j]=true;
     } 
      noSelectedTextView.setText(Integer.toString(counter));
     
    }
    else
    {
   	 if(bflag[j])
   	 {
      counter--;
      bflag[j]=false;
   	 }
     noSelectedTextView.setText(Integer.toString(counter));
    }
   }
  }
 }
}


