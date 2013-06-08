package org.example.mini;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class WpnAllotment extends Activity implements OnClickListener{

 Button indlButton,addlButton,selectButton,changeButton,homeButton;

 public void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.wpn_allotment);
  
  indlButton=(Button) findViewById(R.id.viewindlwpn_label);
  indlButton.setOnClickListener(this);
  addlButton=(Button) findViewById(R.id.allotaddlwpn_label);
  addlButton.setOnClickListener(this);
  selectButton=(Button) findViewById(R.id.selectwpn_label);
  selectButton.setOnClickListener(this);
  changeButton=(Button) findViewById(R.id.changeallot_label);
  changeButton.setOnClickListener(this);
  homeButton=(Button) findViewById(R.id.wpnhome_label);
  homeButton.setOnClickListener(this);
 }

 public void onClick(View v)
 {
  Intent i;
  switch(v.getId())
  {
   case R.id.viewindlwpn_label:
        i=new Intent(this,ViewIndlWpn.class);
        startActivity(i);
        break;
   case R.id.allotaddlwpn_label:
        i=new Intent(this,AllotAddlWpns.class);
        startActivity(i);
        break;
  /* case R.id.selectwpn_label:
        i=new Intent(this,);
        startActivity(i);
        break;*/
   case R.id.changeallot_label:
        i=new Intent(this,ChangeAllotment.class);
        startActivity(i);
        break;
   case R.id.wpnhome_label:
        i=new Intent(this,InputData.class);
        startActivity(i);
        break;
  }
 }
}

