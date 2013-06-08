package org.example.mini;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.database.*;
import android.widget.TableRow.LayoutParams;



public class DeleteIndl extends Activity implements OnClickListener{
	
    ArmyDB objDB;
    Button okButton,infoButton,backButton,srchbutton;
    private EditText num;
    private Cursor c;
    private TableLayout tl;
    private TableRow tr;
    
    static final int DELETE_ID=210;
  //  private boolean flag;  
    String armyNo = null,disp;
    
  //  private String attributes[] ={"ARMY_NO","RANK","NAME","TRADE","SUBUNIT","DOB","DOE","REG_NO","BUTT_NO"};
    
	 public void onCreate(Bundle savedInstanceState)
	 {
	  super.onCreate(savedInstanceState);	  setContentView(R.layout.delete_indl);
		  
		  
	  tr = new TableRow(this);
		
		  
	//  flag=false;
	  tl = (TableLayout) findViewById(R.id.deleteindl_tablelayout);
	  okButton = (Button)findViewById(R.id.deleteindl_okbutton);
	  infoButton = (Button)findViewById(R.id.deleteindl_infobutton);	  backButton = (Button)findViewById(R.id.deleteindl_backbutton);
	  num=(EditText)findViewById(R.id.deleteindl_numtextbox);
	  srchbutton=(Button)findViewById(R.id.deleteindl_gobutton);	  
		  
	  srchbutton.setOnClickListener(this);
          okButton.setOnClickListener( this);
	  backButton.setOnClickListener( this);
	  infoButton.setOnClickListener( this);
		  
		 objDB=new ArmyDB(this);
	  }
	 
	 
 public Dialog onCreateDialog(int id)	
 {
	 switch(id)
	 {
	 case DELETE_ID:  AlertDialog.Builder builder = new AlertDialog.Builder(this);
  builder.setMessage(disp)
         .setCancelable(false)
	 .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
	         public void onClick(DialogInterface dialog, int id) {
	                              objDB.open();                                                                         
	                               objDB.deleteIndlDetail(armyNo);
	                               objDB.close();
	                          refresh();                      	  			              dialog.cancel();
	                     }})
	 .setNegativeButton("No", new DialogInterface.OnClickListener() {
	  public void onClick(DialogInterface dialog, int id) {
                  dialog.cancel();
                 } } );
	                AlertDialog alert = builder.create();
	                return alert;
	 }
	 return null;
	 }
	 
	 
	 
	void refresh()
	{
		num.setText("");
		tl.setVisibility(View.INVISIBLE);
		
	}
	 
  public void onClick(View v)throws SQLException  {  	 
   switch(v.getId())
   {
    case R.id.deleteindl_gobutton: armyNo=num.getText().toString().trim();         
      if(armyNo.matches("((IC|SS|RC|SL|SC)( )?(\\d\\d\\d\\d\\d)( )?[A-Z])|(JC( )?(\\d\\d\\d\\d\\d)( )?[A-Z])|" +
		  		"((\\d\\d\\d\\d\\d\\d\\d\\d)( )?[A-Z])"))
      {
       objDB.open();
       c=objDB.getOneSoldierDetail(armyNo);
       objDB.close();
       if(c.getString(0).equals("0"))
    	   Toast.makeText(getBaseContext(), "Entered no doesn't exist.", Toast.LENGTH_LONG).show();
       else
       {
     //  flag=true;
       tl.setVisibility(View.VISIBLE);
       //parameters[0]=armyNo;
			  						 //c=DB.askquery("INDL_DET",attributes,"ARMY_NO= ?",parameters,null,null,null);
			  				
			  				            
tr.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));   

			  	
TextView armynum = new TextView(this);
armynum.setText(armyNo);armynum.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
tr.addView(armynum);
			  				            TextView rank = new TextView(this);rank.setText(c.getString(0));rank.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
tr.addView(rank);
			  				  
TextView name = new TextView(this);
name.setText(c.getString(1));
name.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));tr.addView(name);
			  					 
TextView trade = new TextView(this);trade.setText(c.getString(2));trade.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));tr.addView(trade);

TextView subunit = new TextView(this);
subunit.setText(c.getString(3));subunit.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
tr.addView(subunit);
			  					
TextView dob = new TextView(this);dob.setText(c.getString(4));
dob.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
tr.addView(dob);
			  				            
TextView doe = new TextView(this);
doe.setText(c.getString(5));
doe.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
tr.addView(doe);
		
TextView wpn = new TextView(this);wpn.setText(c.getString(6));wpn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));tr.addView(wpn);
	  				            
TextView regno = new TextView(this);regno.setText(c.getString(7));regno.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));tr.addView(regno);
			  				
TextView buttno = new TextView(this);buttno.setText(c.getString(8));buttno.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
tr.addView(buttno);
			  				            
tl.addView(tr, new TableLayout.LayoutParams(
LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
       }
}
 else{
	 Toast.makeText(getBaseContext(), "Error in No.", Toast.LENGTH_LONG).show();
 }
			  					 
 break; 
		
case R.id.deleteindl_okbutton:
	armyNo=num.getText().toString().trim();
     if(!armyNo.matches("((IC|SS|RC|SL|SC)( )?(\\d\\d\\d\\d\\d)( )?[A-Z])|(JC( )?(\\d\\d\\d\\d\\d)( )?[A-Z])|" +
		  		"((\\d\\d\\d\\d\\d\\d\\d\\d)( )?[A-Z])"))
     {
Toast.makeText(getBaseContext(), "Error in No.", Toast.LENGTH_LONG).show();
     }
     else
     {
   /*  if(!flag)
     {
       disp="Are you sure you want to delete the individual without viewing his details?";
       showDialog(DELETE_ID);
     }
    else*/
         disp="Selected data will be erased. Do you want to continue ?";
     showDialog(DELETE_ID);
    
    
    }
    break;
			 
   case R.id.deleteindl_backbutton:
        Intent back=new Intent(this,Delete.class);
        startActivity(back);
        break;
			 
   case R.id.deleteindl_infobutton:        break;
			
  } }
}
