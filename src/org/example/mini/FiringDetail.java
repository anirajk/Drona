package org.example.mini;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.TableRow.LayoutParams;

public class FiringDetail extends Activity implements OnClickListener{

EditText wpnEditText,visEditText,posnEditText,rangeEditText,typeEditText;
Spinner tgttypeSpinner;
Button allButton,pdetButton,ndetButton,cwpnButton,cfirerButton,chktgtButton;
Button okButton,backButton,infoButton;
Button b[];
CheckBox cb[];
TextView tv1[],tv3[],tv4[],ctrTextView;
ArmyDB objDB;
DetailSelection ds;
int ctr=1,tot,nod,bId=500,i,j,k,noc=0,lnoc=0,compd=0,f,b2Id=800,cflag,tn;
ArrayAdapter<CharSequence> adapter;
TableLayout tl;
Cursor cursor,cursor1;
String vis,wpn,prac,type,posn,rank,sunit,tgttype;
int no,range,check=1,detailflag,n;
static final int BELOW_DIALOG_ID=100,EXACT_DIALOG_ID=101;
boolean cwf=false;

String balFNo[],balFBNo[],balFRNo[];

CheckBox c[];
Spinner s[];
Button b2[];
TextView t1[];


public void onCreate(Bundle savedInstanceState)
{
 super.onCreate(savedInstanceState);
 setContentView(R.layout.firing_detail);

 objDB=new ArmyDB(this);
 objDB.open();
 tot=objDB.noOfRows("Firer_Selection");
 objDB.close();

 b=new Button[tot];
 cb=new CheckBox[tot];
 tv1=new TextView[tot];
 tv3=new TextView[tot];
 tv4=new TextView[tot];
 
 
 /*ctrTextView=(TextView) findViewById(R.id.detailno_label);
 ctrTextView.setText(Integer.toString(ctr));

 
 
 wpnEditText=(EditText) findViewById(R.id.firingdetwpn_label);
 wpnEditText.setText(wpn);
 
 tgttypeSpinner=(Spinner) findViewById(R.id.firingdettgttype_label);
 adapter=ArrayAdapter.createFromResource(
            this, R.array.tgttype_array,android.R.layout.simple_spinner_item);     
 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 tgttypeSpinner.setAdapter(adapter);
 tgttypeSpinner.setSelection(adapter.getPosition(tgttype),true);

 visEditText=(EditText) findViewById(R.id.firingdetvis_label);
 visEditText.setText(vis);
 posnEditText=(EditText) findViewById(R.id.firingdetposn_label);
 posnEditText.setText(posn);
 rangeEditText=(EditText) findViewById(R.id.firingdetrange_label);
 rangeEditText.setText(range);
 typeEditText=(EditText) findViewById(R.id.firingdettype_label);
 typeEditText.setText(type);
 
 allButton=(Button) findViewById(R.id.firingdetselectall_label);
 allButton.setOnClickListener(this);
 pdetButton=(Button) findViewById(R.id.prevdetail_label);
 pdetButton.setOnClickListener(this);
 ndetButton=(Button) findViewById(R.id.nextdetail_label);
 ndetButton.setOnClickListener(this);
 cwpnButton=(Button) findViewById(R.id.changewpn_label);
 cwpnButton.setOnClickListener(this);
 cfirerButton=(Button) findViewById(R.id.changefirer_label);
 cfirerButton.setOnClickListener(this);
 chktgtButton=(Button) findViewById(R.id.checktgt_label);
 chktgtButton.setOnClickListener(this);
 okButton=(Button) findViewById(R.id.firingdetok_label);
 okButton.setOnClickListener(this);
 backButton=(Button) findViewById(R.id.firingdetback_label);
 backButton.setOnClickListener(this);
 infoButton=(Button) findViewById(R.id.firingdetinfo_label);
 infoButton.setOnClickListener(this);*/

 
  

}

public void onPause()
{
 super.onPause();
 
 if(writeInstanceState(this))
  Toast.makeText(getBaseContext(), "Stored to Shared Preferences.",Toast.LENGTH_SHORT).show();
}

public void onResume()
{
 super.onResume();
 
 readInstanceState(this);
 if(detailflag==0)
	 displayFirers();
 else
	 prevornextDetail();
}

public boolean writeInstanceState(Context c) {

 SharedPreferences p =
                c.getSharedPreferences("StorePrefs", MODE_WORLD_READABLE);

 SharedPreferences.Editor e = p.edit();

 e.putInt("Counter", ctr);
 e.putInt("Checks", noc);
 //e.putInt("NoOfDetails", nod);
 e.putInt("CompletedDetail", compd);

 return (e.commit()); 
}

public void readInstanceState(Context c) {

   SharedPreferences p = c.getSharedPreferences("StorePrefs", MODE_WORLD_READABLE);

   vis = p.getString("Vis", "");
   wpn = p.getString("Wpn", "");
   no = p.getInt("No", 5);
   prac = p.getString("Prac", "");
   type = p.getString("Type", "");
   range = p.getInt("Range", 5);
   posn = p.getString("Posn", "");
   rank = p.getString("Rank", "");
   sunit = p.getString("Subunit", "");
   tgttype = p.getString("TgtType", "");
   check = p.getInt("Check", 1);
  // nod= p.getInt("NoOfDetails", 1);
   Toast.makeText(getBaseContext(), "Retrieved from Shared Preferences.",Toast.LENGTH_SHORT).show();
   
   
 ctr = p.getInt("Counter", 1);
 noc = p.getInt("Checks", 0);
 compd = p.getInt("CompletedDetail", 0);
 detailflag = p.getInt("DetailFlag", 0);
 lnoc = noc;
 nod=tot/no;
 if((tot%no)>0)
  nod++;
}

public void displayFirers()
{
 ctrTextView=(TextView) findViewById(R.id.detailno_label);
 ctrTextView.setText(Integer.toString(ctr));
  
 wpnEditText=(EditText) findViewById(R.id.firingdetwpn_label);
 wpnEditText.setText(wpn);
 
 tgttypeSpinner=(Spinner) findViewById(R.id.firingdettgttype_label);
 adapter=ArrayAdapter.createFromResource(
            this, R.array.tgttype_array,android.R.layout.simple_spinner_item);     
 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 tgttypeSpinner.setAdapter(adapter);
 tgttypeSpinner.setSelection(adapter.getPosition(tgttype),true);

 visEditText=(EditText) findViewById(R.id.firingdetvis_label);
 visEditText.setText(vis);
 posnEditText=(EditText) findViewById(R.id.firingdetposn_label);
 posnEditText.setText(posn);
 rangeEditText=(EditText) findViewById(R.id.firingdetrange_label);
 rangeEditText.setText(Integer.toString(range)); 
 typeEditText=(EditText) findViewById(R.id.firingdettype_label);
 typeEditText.setText(type);
 
 allButton=(Button) findViewById(R.id.firingdetselectall_label);
 allButton.setOnClickListener(this);
 pdetButton=(Button) findViewById(R.id.prevdetail_label);
 pdetButton.setOnClickListener(this);
 cwpnButton=(Button) findViewById(R.id.changewpn_label);
 cwpnButton.setOnClickListener(this);
 chktgtButton=(Button) findViewById(R.id.checktgt_label);
 chktgtButton.setOnClickListener(this);
 okButton=(Button) findViewById(R.id.firingdetok_label);
 okButton.setOnClickListener(this);
 backButton=(Button) findViewById(R.id.firingdetback_label);
 backButton.setOnClickListener(this);
 infoButton=(Button) findViewById(R.id.firingdetinfo_label);
 infoButton.setOnClickListener(this);



 i=0;
 tl=(TableLayout) findViewById(R.id.firingdetailtable);
 objDB.open();
 cursor=objDB.firersForADetail(wpn);
 cursor.moveToFirst();
 if(!cursor.getString(0).equals("0"))
 {
 do
 {
  cursor1=objDB.getDetailsOfSunitFirers(cursor.getString(0));
  cursor1.moveToFirst();
 
  if(check==1||check==3&&cursor1.getString(0).equals(rank)||check==5&&cursor1.getString(2).equals(sunit)||check==7&&cursor1.getString(0).equals(rank)&&cursor1.getString(2).equals(sunit))
  
 {
  TableRow tr=new TableRow(this);
    tr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
    cb[i]=new CheckBox(this);
    cb[i].setOnClickListener(this);
    tr.addView(cb[i]);

    TextView tv2=new TextView(this);
    tv2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv2.setText("-");
    tr.addView(tv2); 

    tv1[i]=new TextView(this);
    tv1[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv1[i].setText(cursor.getString(0));
    tr.addView(tv1[i]);
    
    tv3[i]=new TextView(this);
    tv3[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv3[i].setText(cursor1.getString(0));
    tr.addView(tv3[i]); 

    tv4[i]=new TextView(this);
    tv4[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv4[i].setText(cursor1.getString(1));
    tr.addView(tv4[i]); 
 
    TextView tv5=new TextView(this);
    tv5.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv5.setText(cursor.getString(1));
    tr.addView(tv5); 
 
    TextView tv6=new TextView(this);
    tv6.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv6.setText(cursor.getString(2));
    tr.addView(tv6); 

    TextView tv7=new TextView(this);
    tv7.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv7.setText(cursor.getString(3));
    tr.addView(tv7); 

    b[i]=new Button(this);
    b[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    b[i].setOnClickListener(this);
    b[i].setClickable(false);
    b[i].setId(bId);
    b[i].setText("H");
    tr.addView(b[i]);
    bId++;

    tl.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
  
  i++;
 }
  cursor1.close();

 }while(cursor.moveToNext());
 }
 objDB.close();
 if(i==0) // No firer satisfying the condition.
  Toast.makeText(getBaseContext(), "No firer satisfying the condition.",Toast.LENGTH_LONG).show();
 else if(ctr<nod&&i<no)
  showDialog(BELOW_DIALOG_ID);

}


public void prevornextDetail()
{
 ctrTextView=(TextView) findViewById(R.id.detailno_label);
 ctrTextView.setText(Integer.toString(ctr));
  
 wpnEditText=(EditText) findViewById(R.id.firingdetwpn_label);
 wpnEditText.setText(wpn);
 
 tgttypeSpinner=(Spinner) findViewById(R.id.firingdettgttype_label);
 adapter=ArrayAdapter.createFromResource(
            this, R.array.tgttype_array,android.R.layout.simple_spinner_item);     
 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 tgttypeSpinner.setAdapter(adapter);
 tgttypeSpinner.setSelection(adapter.getPosition(tgttype),true);

 visEditText=(EditText) findViewById(R.id.firingdetvis_label);
 visEditText.setText(vis);
 posnEditText=(EditText) findViewById(R.id.firingdetposn_label);
 posnEditText.setText(posn);
 rangeEditText=(EditText) findViewById(R.id.firingdetrange_label);
 rangeEditText.setText(Integer.toString(range));
 typeEditText=(EditText) findViewById(R.id.firingdettype_label);
 typeEditText.setText(type);
 
 allButton=(Button) findViewById(R.id.firingdetselectall_label);
 allButton.setOnClickListener(this);
 pdetButton=(Button) findViewById(R.id.prevdetail_label);
 pdetButton.setOnClickListener(this);
 ndetButton=(Button) findViewById(R.id.nextdetail_label);
 ndetButton.setOnClickListener(this);
 cwpnButton=(Button) findViewById(R.id.changewpn_label);
 cwpnButton.setOnClickListener(this);
 cfirerButton=(Button) findViewById(R.id.changefirer_label);
 cfirerButton.setOnClickListener(this);
 chktgtButton=(Button) findViewById(R.id.checktgt_label);
 chktgtButton.setOnClickListener(this);
 backButton=(Button) findViewById(R.id.firingdetback_label);
 backButton.setOnClickListener(this);
 infoButton=(Button) findViewById(R.id.firingdetinfo_label);
 infoButton.setOnClickListener(this);
 

 i=0;
 tl=(TableLayout) findViewById(R.id.firingdetailtable);
 objDB.open();
 cursor=objDB.getADetail(ctr);
 cursor.moveToFirst();
 
 if(!cursor.getString(0).equals("0"))
 {
 do
 {
  cursor1=objDB.getDetailsOfSunitFirers(cursor.getString(0));
  cursor1.moveToFirst();
  TableRow tr=new TableRow(this);
    tr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
    cb[i]=new CheckBox(this);
    cb[i].setOnClickListener(this);
    tr.addView(cb[i]);

    TextView tv2=new TextView(this);
    tv2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv2.setText("-");
    tr.addView(tv2); 

    tv1[i]=new TextView(this);
    tv1[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv1[i].setText(cursor.getString(0));
    tr.addView(tv1[i]);

    
    tv3[i]=new TextView(this);
    tv3[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv3[i].setText(cursor1.getString(0));
    tr.addView(tv3[i]); 

    tv4[i]=new TextView(this);
    tv4[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv4[i].setText(cursor1.getString(1));
    tr.addView(tv4[i]); 
 
    TextView tv5=new TextView(this);
    tv5.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv5.setText(cursor.getString(1));
    tr.addView(tv5); 
 
    TextView tv6=new TextView(this);
    tv6.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv6.setText(cursor.getString(2));
    tr.addView(tv6);
    
    TextView tv7=new TextView(this);
    tv7.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    tv7.setText(cursor.getString(3));
    tr.addView(tv7); 


    b[i]=new Button(this);
    b[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    b[i].setOnClickListener(this);
    b[i].setClickable(false);
    b[i].setId(bId);
    b[i].setText("H");
    tr.addView(b[i]);
    bId++;

    tl.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
  
  i++;
 
  cursor1.close();

 }while(cursor.moveToNext());
 }
 else
	 Toast.makeText(getBaseContext(), "No firers for firing detail "+ctr,Toast.LENGTH_LONG).show();

 objDB.close();

}

public void storeDetailNo()
{
 objDB.open();
 for(j=0;j<i;j++)
 {
  if(cb[j].isChecked()) 
   objDB.updDetailNo(tv1[j].getText().toString().trim(),ctr);
 }
 objDB.close();
}

public void changeWpn()
{
 ArrayAdapter<CharSequence> adapter;
 TextView titleTV=(TextView) findViewById(R.id.text_label);
 titleTV.setText("CHANGE WPN");
 Button b1=(Button) findViewById(R.id.changeallotselectall_label);
 b1.setOnClickListener(this);
 n=0;
 for(j=0;j<i;j++)
 {
  if(cb[j].isChecked())
   n++;
 }
 c=new CheckBox[n];
 t1=new TextView[n];
 s=new Spinner[n];
 b2=new Button[n];
 
 Button b3=(Button) findViewById(R.id.changeallotok_label);
 b3.setOnClickListener(this);
 Button b4=(Button) findViewById(R.id.changeallotback_label);
 b4.setOnClickListener(this);
 Button b5=(Button) findViewById(R.id.changeallotinfo_label);
 b5.setOnClickListener(this);

 adapter=ArrayAdapter.createFromResource(this, R.array.weapon_array,android.R.layout.simple_spinner_item);
  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
 TableLayout tl1=(TableLayout) findViewById(R.id.changeallottable);
 k=0;
 for(j=0;j<i;j++)
 {
  if(cb[j].isChecked())
  {
    TableRow tr=new TableRow(this);
    tr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
    cursor.moveToPosition(j);
    
    c[k]=new CheckBox(this);
    c[k].setOnClickListener(this);
    tr.addView(c[k]);
   
    t1[k]=new TextView(this);
    t1[k].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    t1[k].setText(cursor.getString(0));
    tr.addView(t1[k]); 

    TextView t2=new TextView(this);
    t2.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    t2.setText(tv3[j].getText().toString());
    tr.addView(t2);

    TextView t3=new TextView(this);
    t3.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    t3.setText(tv4[j].getText().toString());
    tr.addView(t3);

    TextView t4=new TextView(this);
    t4.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    t4.setText(cursor.getString(1));
    tr.addView(t4);

    TextView t5=new TextView(this);
    t5.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    t5.setText(cursor.getString(2));
    tr.addView(t5);

    s[k]=new Spinner(this);
    s[k].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    s[k].setClickable(false);
    s[k].setAdapter(adapter);
    s[k].setSelection(adapter.getPosition(cursor.getString(1)),true);
    tr.addView(s[k]);

    b2[k]=new Button(this);
    b2[k].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    b2[k].setOnClickListener(this);
    b2[k].setClickable(false);
    b2[k].setId(b2Id);
    b2[k].setText(cursor.getString(2));
    tr.addView(b2[k]);
    b2Id++;

    tl1.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT)); 

    k++;
  }
 }

}


protected Dialog onCreateDialog(int id)
{
	  AlertDialog alert;
	  AlertDialog.Builder builder;
	  switch(id)
	  {
	   case BELOW_DIALOG_ID:
		   builder=new AlertDialog.Builder(this);
		   builder.setMessage("The no. of firers selected has fallen short of the required one.Go back to detail selection" +
		   		" to change conditions after checking the available ones.")
		          .setCancelable(false)
		          .setNeutralButton("OK",new DialogInterface.OnClickListener() {
		        	  @Override
					public void onClick(DialogInterface dialog, int which) {
						
						dialog.cancel();
					}
				});
		   alert=builder.create();
		   return alert;	      
		   
	   case EXACT_DIALOG_ID:
		   builder=new AlertDialog.Builder(this);
		   builder.setMessage("Exactly "+no+" firers should be selected.")
		          .setCancelable(false)
		          .setNeutralButton("OK",new DialogInterface.OnClickListener() {
		        	  @Override
					public void onClick(DialogInterface dialog, int which) {
						
						dialog.cancel();
					}
				});
		   alert=builder.create();
		   return alert;
	   
	   
	  }
	  return null;
}

public void onClick(View v)
{
 Intent in;
 int cfchk;
 f=0;
 switch(v.getId())
 {
  case R.id.firingdetselectall_label:
       f=1;
       for(j=0;j<i;j++)
       {
        cb[j].setChecked(true);
        b[j].setClickable(true);
       }
       break;
  
  case R.id.prevdetail_label:
       f=1;
       if(ctr==1)
        Toast.makeText(getBaseContext(), "No previous detail.",Toast.LENGTH_LONG).show(); 
       else
       {
        cursor.close();
        ctr--;
        setContentView(R.layout.firing_detail);
        prevornextDetail();
       }
       break;
  
  case R.id.nextdetail_label:
       f=1;
       if(ctr==nod)
        Toast.makeText(getBaseContext(), "No next detail.",Toast.LENGTH_LONG).show();
       else
       {
        ctr++;
        if(ctr<=compd)
        {
          cursor.close();
          setContentView(R.layout.firing_detail);
          prevornextDetail();
        }
        else
        {
          cursor.close();
          setContentView(R.layout.firing_detail);
          displayFirers();
        }
       }
       break;

  case R.id.changewpn_label:
      f=1;
      cfchk=0;
      cwf=true;
     
      for(j=0;j<i;j++)
      {
       if(cb[j].isChecked()) 
        cfchk++;
      }
      
      if(cfchk==0)
      {    
          Toast.makeText(getBaseContext(), "Select firers.",Toast.LENGTH_LONG).show();
      }
      else
      {
    	setContentView(R.layout.change_allotment);
      	changeWpn();
      }
      break;

 case R.id.changefirer_label:
      f=1;
      
      cfchk=0;
      int rem,cfflag=1;
    
      for(j=0;j<i;j++)
      {
       if(cb[j].isChecked()) 
        cfchk++;
      }
      
      if(cfchk==0)
      {
    	  cfflag=0;
          Toast.makeText(getBaseContext(), "Select firers.",Toast.LENGTH_LONG).show();
      }
      else
      {
      if(compd<nod)
      {
    	  objDB.open();  
    	rem=objDB.remainingFirers();  
    	if(rem<cfchk)
    	{
    		cfflag=0;
    		Toast.makeText(getBaseContext(), "You can select only "+rem+" firers at most.",Toast.LENGTH_LONG).show();
    	}
    	objDB.close();
      }
      else if(compd==nod)
      {
    	   if(nod==1||nod==2&&ctr==1&&(tot-no)<cfchk)
    	   {
    		   cfflag=0;
    		   Toast.makeText(getBaseContext(), "You can select only "+(tot-no)+" firers at most.",Toast.LENGTH_LONG).show();
    	   }
      }
      }
      
      if(cfflag==1)
      {
       objDB.open();
       k=0;
       if(compd==nod)
       {
        cursor=objDB.getOtherDetails(ctr);
        cursor.moveToFirst();
    	cflag=1;
       } 
       else
       {
        cursor=objDB.getADetail(0);
        cursor.moveToFirst();
        cflag=0;
       }
       for(j=0;j<i;j++)
        {
         if(cb[j].isChecked()) 
          objDB.updDetailNo(tv1[j].getText().toString(),0);
        }
       objDB.close();
       balFNo=new String[tot];
       balFBNo=new String[tot];
       balFRNo=new String[tot];
        do
        {
         balFNo[k]=cursor.getString(0);
         balFBNo[k]=cursor.getString(2);
         balFRNo[k]=cursor.getString(3);
         k++;
        }while(cursor.moveToNext());
        
       in=new Intent(this,ChangeFirers.class);
       in.putExtra("org.example.mini.NoOfChecks",cfchk);
       in.putExtra("org.example.mini.BalanceFirersNo",balFNo);
       in.putExtra("org.example.mini.BalFirersBNo",balFBNo);
       in.putExtra("org.example.mini.NoOfRemFirers",k);
       in.putExtra("org.example.mini.BalFirRNo",balFRNo);
       in.putExtra("org.example.mini.Detailno",ctr);
       in.putExtra("org.example.mini.Flag",cflag);
       startActivity(in);
      }
      break;
     
       
  case R.id.firingdetok_label:
       f=1;
       tn=0;
       for(j=0;j<i;j++)
       {
        if(cb[j].isChecked())
        {
         noc++;
         tn++;
        }
       }
       if((ctr!=nod)&&(i>=(no-noc))&&(noc!=no))
       {
        noc=lnoc;
        showDialog(EXACT_DIALOG_ID);
       } 
       else
       {
        if(i<no&&noc!=no)
        {
         if(ctr<nod)
         {
          storeDetailNo();
          Toast.makeText(getBaseContext(), "Going back to DetailSelection page to complete the detail.",Toast.LENGTH_LONG).show(); //Going back
          in=new Intent(this,DetailSelection.class);
          startActivity(in);
         }
         
        }
        if(ctr==nod)
         {
          
        	if(tn!=i)
            {
             noc=lnoc;
             Toast.makeText(getBaseContext(), "Select all firers.",Toast.LENGTH_LONG).show();
            // Select all
            }
            
          if((noc==no)||((tot%no>0)&&(noc==tot%no)))
          {
        	compd++;
        	noc=lnoc=0;
        	storeDetailNo();
          	Toast.makeText(getBaseContext(), "All selected firers have been alloted details.",Toast.LENGTH_LONG).show();
          }
          
          else
          {
        	  storeDetailNo();
        	  Toast.makeText(getBaseContext(), "Going back to DetailSelection page to complete the detail no "+ctr+".",Toast.LENGTH_LONG).show(); //Going back
              in=new Intent(this,DetailSelection.class);
              startActivity(in);  
          }
          
          
          
         
         }
        if(noc==no&&ctr!=nod)
        {
         storeDetailNo();
         setContentView(R.layout.firing_detail);
         noc=lnoc=0;
         ctr++;
         compd++;
         cursor.close();
         displayFirers();
        }
       }
       break;
  
  case R.id.firingdetback_label:
       f=1;
       in=new Intent(this,DetailSelection.class);
       startActivity(in);
       break;
   
  case R.id.changeallotselectall_label:
      f=1;
      for(k=0;k<n;k++)
      {
        c[k].setChecked(true);
        s[k].setClickable(true);
        b2[k].setClickable(true);
      }
      break;

 case R.id.changeallotok_label:
      f=1;
      boolean okf=false;
      String armyn,armyw,armyb;
      objDB.open();
      for(k=0;k<n;k++)
      {
       if(c[k].isChecked())
       {
        armyn=t1[k].getText().toString().trim();;
        armyw=s[k].getSelectedItem().toString();
        armyb=b2[k].getText().toString().trim();
        objDB.updateWpn(armyn,armyw,armyb,null);
        okf=true;
       }
      }
      objDB.close();
      if(okf)
    	  Toast.makeText(getBaseContext(), "Weapons of selected firers have been changed.",Toast.LENGTH_LONG).show();
      break;

 case R.id.changeallotback_label:
      f=1; 
      cwf=false;
      cursor.close();
      setContentView(R.layout.firing_detail);
      if(ctr<=compd)
       prevornextDetail();
      else
       displayFirers();
      break;

       
 }

 if(f==0&&!cwf)
 {
  for(j=0;j<i;j++)
  {
   if(cb[j].isChecked())
    b[j].setClickable(true);
   else
    b[j].setClickable(false);
  }

 }
 
 if(cwf)
 {
 for(k=0;k<n;k++)
 {
   if(c[k].isChecked())
   {
    s[k].setClickable(true);
    b2[k].setClickable(true);
   }
   else
   {
	   s[k].setClickable(false);
	   b2[k].setClickable(false);
   }
 }
 }
}

}
 

