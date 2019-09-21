// Roman column with bowl

                            
//Cutout cylinders for fluting
#declare FluteCutout = cylinder { <0,-0.01,0.25>, <0,1.01,0.25>, 0.03525 }
 
//Rind around top and bottom of column
#declare ColumnRing = union {
	torus { 0.25, 0.03525 }
	cylinder { <0,-0.03525,0>, <0,0.03525,0>, 0.25 }
}
  
#declare Column = union { 
	//Main column body
	difference {
		cylinder { <0,0,0>, <0,1,0>, 0.25 }
		object { FluteCutout }
		object { FluteCutout rotate y*30 }
		object { FluteCutout rotate y*60 }
		object { FluteCutout rotate y*90 }
		object { FluteCutout rotate y*120 }
		object { FluteCutout rotate y*150 }
		object { FluteCutout rotate y*180 }
		object { FluteCutout rotate y*210 }
		object { FluteCutout rotate y*240 }
		object { FluteCutout rotate y*270 }
		object { FluteCutout rotate y*300 }
		object { FluteCutout rotate y*330 }
	}  
	
	object { ColumnRing }
	object { ColumnRing translate y*1 }
	
	//Bowl
	difference {
		sphere { <0,1.45,0>, 0.5 }
		sphere { <0,1.7,0>, 0.66 }
		box { <-0.61,2,-0.61>, <0.61,1.12,0.61> }     
	}
	
	//Base
	box {
		<-0.35,-0.03525,-0.35>,
		<0.35,-0.13525,0.35>
	}
	
	translate y*0.13525
}