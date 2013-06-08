package org.example.mini;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.widget.Button;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View.*;
import android.view.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;

public class InputData extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	ArmyDB objDB;
	Cursor cursor,cursor1;
	boolean val;
	String no,wpn,reg;
	Intent i;
	int butt,flag=0,detailflag=0;
	static final int DETAIL_DIALOG_ID=900;
	SharedPreferences p;
	@Override
    
    public void onCreate(Bundle savedInstanceState)
    {
  	  super.onCreate(savedInstanceState);
  	  setContentView(R.layout.main);
  	  
        Button inputButton= (Button) findViewById(R.id.input_label);
        inputButton.setOnClickListener(this);
        
        Button selectButton=(Button) findViewById(R.id.select_label);
        selectButton.setOnClickListener(this);
        
        Button weaponButton=(Button) findViewById(R.id.weapon_label);
        weaponButton.setOnClickListener(this);
        
        Button detailButton=(Button) findViewById(R.id.detailsel_label);
        detailButton.setOnClickListener(this);
        
        objDB=new ArmyDB(this);
        
    }
	
	public void onPause()
	{
	 super.onPause();
	 
	  p = this.getSharedPreferences("StorePrefs", MODE_WORLD_READABLE);

		SharedPreferences.Editor e = p.edit();

		e.putInt("WeaponFlag", flag);
		e.putInt("DetailFlag", detailflag);
		e.commit();
	}
	
	public void onResume()
	{
	 super.onResume();
	 
	 p = this.getSharedPreferences("StorePrefs", MODE_WORLD_READABLE);

	 flag = p.getInt("WeaponFlag", 0);

	}
	
	public void callFunc()
	{
		if(detailflag==1)
		{
		 i=new Intent(this,FiringDetail.class);
		 startActivity(i);
		}
		else
		{
			p = this.getSharedPreferences("StorePrefs", MODE_WORLD_READABLE);

			SharedPreferences.Editor e = p.edit();

				e.putInt("CompletedDetail", 0);
				e.commit();

			objDB.open();
			objDB.updDetailNo(null, 0);
			objDB.close();
			i=new Intent(this,DetailSelection.class);
			 startActivity(i);
		}
	}
	
	protected Dialog onCreateDialog(int id)
	{
		  AlertDialog alert;
		  AlertDialog.Builder builder;
		  switch(id)
		  {
		   case DETAIL_DIALOG_ID:
			   builder=new AlertDialog.Builder(this);
			   builder.setMessage("Detail selection has been done.Click yes if you want to view the current detail or no " +
			   		"if you want to start fresh.")
			          .setCancelable(false)
			          .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
			        	  @Override
						public void onClick(DialogInterface dialog, int which) {
			        		  detailflag=1;
			        		
							dialog.cancel();
							callFunc();
						}
					})
					  .setNegativeButton("No", new DialogInterface.OnClickListener() {
			        	  @Override
							public void onClick(DialogInterface dialog, int which) {
			        		  detailflag=0;	
			        		  
							dialog.cancel();
							callFunc();
						}
					});
			   alert=builder.create();
			   return alert;
		   	
		   
		  }
		  return null;
	}

	
        public void onClick(View v)
        {
        	
        	switch(v.getId())
        	{
        	 case R.id.input_label:i=new Intent(this,Input.class);
        	                       startActivity(i);
        	                       break;
        	                         
        	 case R.id.select_label:objDB.open();
        		                    objDB.deleteTable("Firer_Selection");
        		                    objDB.deleteTable("Weapon");
        		                    val=objDB.chkIfEmpty("Indl_Details");
        		                    objDB.close();
        		                    flag=0;
        		             	   if(val)
        		             		   Toast.makeText(getBaseContext(), "No individual's details present in the details table.", Toast.LENGTH_LONG).show();
        		             	   else
        		             	   {
        		                    i=new Intent(this,SelectFirers.class);
                                    startActivity(i);
        		             	   }
                                    break;
           
        	 case R.id.weapon_label:objDB.open();
        	                        val=objDB.chkIfEmpty("Firer_Selection");
        	                        objDB.close();
        	                        if(val)
                             		  Toast.makeText(getBaseContext(), "Firers must be selected first.", Toast.LENGTH_LONG).show();
        	                        else
        	                        {
        	                        	if(flag==0)
        	                        	{
        	                        	flag=1;
        	                        	objDB.open();
        	                        	cursor=objDB.getSelectedFirers();
        	 
        	                        	cursor.moveToFirst();
        	       
        	                         do
        	                         {
        	                        	 no=cursor.getString(0);
        	                        	cursor1= objDB.getIndlWpns(no);
        	                        	cursor1.moveToFirst();
        	                        	wpn=cursor1.getString(2);
        	                        	butt=cursor1.getInt(3);
        	                        	reg=cursor1.getString(4);
        	                        	objDB.insertWpns(no, wpn, reg, butt);
        	                        	cursor1.close();
        	                         }while(cursor.moveToNext());
        	                         objDB.close();
        	                          cursor.close();
        	                        	}
        		                       i=new Intent(this,WpnAllotment.class);
                                       startActivity(i);
        	                        }
                                    break;
             
        	 case R.id.detailsel_label:objDB.open();
        	 						   val=objDB.chkIfEmpty("Firer_Selection");
        	 						   objDB.close();
        	 						   if(val)
        	 							   Toast.makeText(getBaseContext(), "Firers must be selected first.", Toast.LENGTH_LONG).show();
        	 						   else
        	 						   {
        	 							   if(flag==0)
        	 							   {
        	 								   flag=1;
        	 								  objDB.open();
              	                        	cursor=objDB.getSelectedFirers();
              	 
              	                        	cursor.moveToFirst();
              	       
              	                         do
              	                         {
              	                        	 no=cursor.getString(0);
              	                        	cursor1= objDB.getIndlWpns(no);
              	                        	cursor1.moveToFirst();
              	                        	wpn=cursor1.getString(2);
              	                        	butt=cursor1.getInt(3);
              	                        	reg=cursor1.getString(4);
              	                        	objDB.insertWpns(no, wpn, reg, butt);
              	                        	cursor1.close();
              	                         }while(cursor.moveToNext());
              	                         objDB.close();
              	                          cursor.close();
        	 							   }
        	 							 p = this.getSharedPreferences("StorePrefs", MODE_WORLD_READABLE);

        	 							SharedPreferences.Editor e = p.edit();

        	 							e.putInt("Counter", 1);
        	 							e.commit();
        	 							objDB.open();
        	 							cursor=objDB.chkIfDetailPresent();
        	 							cursor.moveToFirst();
        	 							objDB.close();   
        	 							if(!cursor.getString(0).equals("0"))
        	 							{
        	 								showDialog(DETAIL_DIALOG_ID);
        	 							}
        	 							
        	 							else
        	 							{
        	 							 p = this.getSharedPreferences("StorePrefs", MODE_WORLD_READABLE);

            	 						 e = p.edit();

            	 							e.putInt("CompletedDetail", 0);
            	 							e.commit();

        	                             i=new Intent(this,DetailSelection.class);
        	                             startActivity(i);
        	 							}
        	 						   }
        	 						   
        	 						   break;
        	}
        }
    
}