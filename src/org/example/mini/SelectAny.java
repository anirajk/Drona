package org.example.mini;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.TableRow.LayoutParams;

public class SelectAny extends Activity implements OnClickListener{
 EditText wpnEditText;
 TextView noTextView,noSelectedTextView;
 Button allButton,okButton,addlButton,backButton,infoButton;
 String wpn,no,empty="-";
 int sunit,i,num,counter=0,flag=0,cf=0;
 ArmyDB objDB;
 Cursor cursor,cursor1;
 CheckBox cb[];
 TextView tv1[],tv2[],tv3[],tv4[],tv5[],tv6[];
 TableLayout tl; 
 boolean ef=true,val,bflag[];

 public void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.select_any);
  
  objDB=new ArmyDB(this);

  wpn=getIntent().getStringExtra("org.example.mini.Wpn");
  no=getIntent().getStringExtra("org.example.mini.NoOfFirers");
  sunit=getIntent().getIntExtra("org.example.mini.Sunits",15);
  num=Integer.parseInt(no);

  wpnEditText=(EditText) findViewById(R.id.firerwpntextbox_label);
  wpnEditText.setText(wpn);
  noTextView=(TextView) findViewById(R.id.totalfirers_label);
  noTextView.setText(no);
  noSelectedTextView=(TextView) findViewById(R.id.firersselected_label);
   
  allButton=(Button) findViewById(R.id.arbselectall_label);
  allButton.setOnClickListener(this);
  okButton=(Button) findViewById(R.id.selectanyok_label);
  okButton.setOnClickListener(this);
  backButton=(Button) findViewById(R.id.selectanyback_label);
  backButton.setOnClickListener(this);
  addlButton=(Button) findViewById(R.id.addl_label);
  addlButton.setOnClickListener(this);
  infoButton=(Button) findViewById(R.id.selectanyinfo_label);
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
  bflag=new boolean[2*num];

  if((sunit&1)==1)
  {
   sunit=sunit&14;
   showFirers("HQ");
  }
  else if((sunit&2)==2)
  {
   sunit=sunit&13;
   showFirers("P");
  }
  else if((sunit&4)==4)
  {
   sunit=sunit&11;
   showFirers("Q");
  }
  else if((sunit&8)==8)
  {
   sunit=sunit&7;
   showFirers("R");
  }
  
  
  
 }

 public void showFirers(String s)
 {
	// wpn=getIntent().getStringExtra("org.example.mini.Wpn");
	//	no=getIntent().getStringExtra("org.example.mini.NoOfFirers");
		
	 
	 wpnEditText=(EditText) findViewById(R.id.firerwpntextbox_label);
	  wpnEditText.setText(wpn);
	  noTextView=(TextView) findViewById(R.id.totalfirers_label);
	  noTextView.setText(no);
	  noSelectedTextView=(TextView) findViewById(R.id.firersselected_label);
	  
	 
   
	allButton=(Button) findViewById(R.id.arbselectall_label);
	  allButton.setOnClickListener(this);
	  okButton=(Button) findViewById(R.id.selectanyok_label);
	  okButton.setOnClickListener(this);
	  backButton=(Button) findViewById(R.id.selectanyback_label);
	  backButton.setOnClickListener(this);
	  addlButton=(Button) findViewById(R.id.addl_label);
	  addlButton.setOnClickListener(this);
	  infoButton=(Button) findViewById(R.id.selectanyinfo_label);
	  infoButton.setOnClickListener(this);

	 
  wpnEditText.setText(wpn);
  noTextView.setText(no);

  
  counter=0;
  i=0;
  tl=(TableLayout) findViewById(R.id.arbselectfirerstable);
  objDB.open();
  cursor=null;
  cursor=objDB.getFirersOfASubunit(s);
  
  cursor.moveToFirst();
  do
  {
	  cursor1=objDB.getDetailsOfSunitFirers(cursor.getString(0));
	    cursor1.moveToFirst();  
  
    if(cursor1.getString(3).equals(wpn))
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

    if(ef)
    {
     cursor1.close();
     cursor1=objDB.getDetails(cursor.getString(0));
    }

    tv5[i]=new TextView(this);
    tv5[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    if(ef)
    {
     cursor1.moveToFirst();
    if(cursor1.getString(0).equals("0"))
     empty="-";
    else
     empty=cursor1.getString(0);
    }
    tv5[i].setText(empty);
    tr.addView(tv5[i]);

    tv6[i]=new TextView(this);
    tv6[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    if(ef)
    {
    if(cursor1.getString(0).equals("0"))
     empty="-";
    else
     empty=cursor1.getString(1);
    }
    tv6[i].setText(empty);
    tr.addView(tv6[i]);  
    
    tl.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));

    i++;
    }
   cursor1.close();
   
   if(i==num)
    break;
  }while(cursor.moveToNext());  
  objDB.close();  
 }

 public void addlFirers()
 {
   if(cursor.moveToPosition(i))
   {
    objDB.open();
    do
   {
    	cursor1=objDB.getDetailsOfSunitFirers(cursor.getString(0));
        cursor1.moveToFirst();
        	
        if(cursor1.getString(3).equals(wpn))
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

    if(ef)
    cursor1=objDB.getDetails(cursor.getString(0));  

    tv5[i]=new TextView(this);
    tv5[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    if(ef)
    {
    if(cursor1.getString(0).equals("0"))
     empty="-";
    else
     empty=cursor1.getString(0);
    }
    tv5[i].setText(empty);
    tr.addView(tv5[i]);

    tv6[i]=new TextView(this);
    tv6[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    if(ef)
    {
    if(cursor1.getString(0).equals("0"))
     empty="-";
    else
     empty=cursor1.getString(1);
    }
    tv6[i].setText(empty);
    tr.addView(tv6[i]);
    
    tl.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
   
    i++;
        }
    cursor1.close();
   
   if(i==num)
    break;
  }while(cursor.moveToNext());
  objDB.close();
 }
 else
   Toast.makeText(getBaseContext(), "No additional firers.", Toast.LENGTH_LONG);
 }

 public void onClick(View v)
 {
  flag=0;
  cf=0;
  Intent in;
  int j;
 // String s;
  
  switch(v.getId())
  {
   case R.id.arbselectall_label:
        flag=1;
        for(j=0;j<i;j++)
        {
         cb[j].setChecked(true);
         bflag[j]=true;
        } 
        counter=i;
        noSelectedTextView.setText(Integer.toString(counter)); 
        break;
   
   case R.id.addl_label:
        flag=1;
        addlFirers();
        break;

   case R.id.selectanyok_label:
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
         bflag[j]=false;
        }
        
        if((sunit&2)==2)
        {
          sunit=sunit&13;
          setContentView(R.layout.select_any);
          showFirers("P");
        }
        else if((sunit&4)==4)
        {
          sunit=sunit&11;
          setContentView(R.layout.select_any);
          showFirers("Q");
        }
        else if((sunit&8)==8)
        {
          sunit=sunit&7;
          setContentView(R.layout.select_any);
          showFirers("R");
        }
        else
        {
          in=new Intent(this,Arbitrary.class);
          startActivity(in);
        }
        break;
     case R.id.selectanyback_label:
          flag=1;
          in=new Intent(this,Arbitrary.class);
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
     cf=1;
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


