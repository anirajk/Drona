package org.example.mini;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.TableRow.LayoutParams;

public class ChangeFirers extends Activity implements OnClickListener{

Button allButton,b[],okButton,backButton,infoButton;
CheckBox cb[];
TextView tv[];
ArmyDB objDB;
String balFNo[],balFBNo[],balFRNo[];
int cfchk,dno,bId=500,i,j,n,no,f,cflag,okflag=0,k;

TableLayout tl;
TextView noTV[];
Cursor cursor;

public void onCreate(Bundle savedInstanceState)
{
 super.onCreate(savedInstanceState);
 setContentView(R.layout.change_firers);

 objDB=new ArmyDB(this);
 //objDB.open();

 //objDB.close();
 balFNo=getIntent().getStringArrayExtra("org.example.mini.BalanceFirersNo");
 balFBNo=getIntent().getStringArrayExtra("org.example.mini.BalFirersBNo");
 balFRNo=getIntent().getStringArrayExtra("org.example.mini.BalFirRNo");
 cfchk=getIntent().getIntExtra("org.example.mini.NoOfChecks",0);
 k=getIntent().getIntExtra("org.example.mini.NoOfRemFirers",0);
 cflag=getIntent().getIntExtra("org.example.mini.Flag",0);
 dno=getIntent().getIntExtra("org.example.mini.Detailno",0);
 
 noTV=new TextView[balFNo.length];
 cb=new CheckBox[balFNo.length];
 b=new Button[balFNo.length];

 allButton=(Button) findViewById(R.id.changefirersselectall_label);
 allButton.setOnClickListener(this);
 okButton=(Button) findViewById(R.id.changefirersok_label);
 okButton.setOnClickListener(this);
 backButton=(Button) findViewById(R.id.changefirersback_label);
 backButton.setOnClickListener(this);
 infoButton=(Button) findViewById(R.id.changefirersinfo_label);
 infoButton.setOnClickListener(this);

 dispNewFirers();
}

public void dispNewFirers()
{
 i=0;
 tl=(TableLayout) findViewById(R.id.changefirerstable);
 objDB.open();
 
 while(i<k)
 {
  cursor=objDB.getDetailsOfSunitFirers(balFNo[i]);
  cursor.moveToFirst();
 
  TableRow tr=new TableRow(this);
    tr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
    cb[i]=new CheckBox(this);
    cb[i].setOnClickListener(this);
    tr.addView(cb[i]);

    noTV[i]=new TextView(this);
    noTV[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    noTV[i].setText(balFNo[i]);
    tr.addView(noTV[i]); 

    TextView tv3=new TextView(this);
    tv3.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv3.setText(cursor.getString(0));
    tr.addView(tv3); 

    TextView tv4=new TextView(this);
    tv4.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv4.setText(cursor.getString(1));
    tr.addView(tv4); 
 
    TextView tv5=new TextView(this);
    tv5.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv5.setText(balFBNo[i]);
    tr.addView(tv5); 
 
    TextView tv6=new TextView(this);
    tv6.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv6.setText(balFRNo[i]);
    tr.addView(tv6); 

    b[i]=new Button(this);
    b[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    b[i].setOnClickListener(this);
    b[i].setClickable(false);
    b[i].setId(bId);
    b[i].setText("H");
    tr.addView(b[i]);
    bId++;

    TextView tv2=new TextView(this);
    tv2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv2.setText(Integer.toString(dno));
    tr.addView(tv2); 

    tl.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
  
  i++;
  cursor.close();

 }
 objDB.close();



}



public void onClick(View v)
{
 Intent in;
 f=0;
 switch(v.getId())
 {
  case R.id.changefirersselectall_label:
       f=1;
       for(j=0;j<i;j++)
       {
        cb[j].setChecked(true);
        b[j].setClickable(true);
       }
       break;

  case R.id.changefirersok_label:
       f=1;
       n=0;
       for(j=0;j<i;j++)
       {
        if(cb[j].isChecked())
         n++;
       }
       if(n!=cfchk)
        Toast.makeText(getBaseContext(), "You should select exactly "+cfchk+" firer(s).",Toast.LENGTH_LONG).show();
       else
       {
    	   okflag=1;
        objDB.open();
        for(j=0;j<i;j++)
        {
         if(cb[j].isChecked())
	 	{
          
        	 if(cflag==1)
        	 {
        		 cursor=objDB.getDetailNo(noTV[j].getText().toString());
        		 cursor.moveToFirst();
        		 no=cursor.getInt(0);
        		 cursor.close();
        		 cursor=objDB.getANo();
        		 cursor.moveToFirst();
        		 objDB.updDetailNo(cursor.getString(0),no);
        		 cursor.close();
        	 }
        	 objDB.updDetailNo(noTV[j].getText().toString(),dno);
	 	}
        }
        objDB.close();
        Toast.makeText(getBaseContext(), "Firers have been changed.",Toast.LENGTH_LONG).show();
       }	
       break;

  case R.id.changefirersback_label:
       f=1;
       SharedPreferences p = this.getSharedPreferences("StorePrefs", MODE_WORLD_READABLE);

		SharedPreferences.Editor e = p.edit();

		e.putInt("DetailFlag", 1);
		e.commit();

		if(okflag==1)
		{
			in=new Intent(this,FiringDetail.class);
			startActivity(in);
		}
		else
			Toast.makeText(getBaseContext(), "You should select firers and press ok for changing detail.",Toast.LENGTH_LONG).show();
       break;
 }

}

}

