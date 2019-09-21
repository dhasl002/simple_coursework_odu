#include "colors.inc"
#include "textures.inc"
#include "shapes.inc"
#include "metals.inc"
#include "glass.inc"
#include "woods.inc"               
#include "textures.inc"                     
#include "firepillar.inc"                   
#declare Fire_Enable = 1;

// tree textures:  --------------------------------
#declare Stem_Texture =
texture{ pigment{ color rgb< 0.75, 0.5, 0.30>*0.25}
        normal{bumps 0.75 scale<0.025,0.075,0.025>}
         finish { phong 0.2 }
       } // end of texture
#declare Leaves_Texture_1 =
texture{ pigment{ color rgbf<0.2,0.5,0, 0.1>*0.75}
         normal { bumps 0.15 scale 0.05 }
         finish { phong 1 }
       } // end of texture
#declare Leaves_Texture_2 =
texture{ pigment{ color rgbf<0.2,0.5,0, 0.2>*0.25}
         normal { bumps 0.15 scale 0.05 }
         finish { phong 0.2 }
       } // end of texture
//-------------------------------------------------
#include "sassafras_m.inc"
//-------------------------------------------------
// tree with leaves
   union{ 
          object{ sassafras_13_stems
                  texture{ Stem_Texture }
                } //------------------------
          object{ sassafras_13_leaves  
                  double_illuminate
                  texture{ Leaves_Texture_1 }   
                  interior_texture{ Leaves_Texture_2 }   
                } //------------------------
      rotate <0,90,0>
      translate<33,110,110>
    } // end of union 
//--------------------------------------------------------------------------   
//-------------------------------------------------
// tree with leaves
   union{ 
          object{ sassafras_13_stems
                  texture{ Stem_Texture }
                } //------------------------
          object{ sassafras_13_leaves  
                  double_illuminate
                  texture{ Leaves_Texture_1 }   
                  interior_texture{ Leaves_Texture_2 }   
                } //------------------------
      rotate <0,90,0>
      translate<15,105,140>
    } // end of union 
//--------------------------------------------------------------------------  
//-------------------------------------------------
// tree with leaves
   union{ 
          object{ sassafras_13_stems
                  texture{ Stem_Texture }
                } //------------------------
          object{ sassafras_13_leaves  
                  double_illuminate
                  texture{ Leaves_Texture_1 }   
                  interior_texture{ Leaves_Texture_2 }   
                } //------------------------
      rotate <0,90,0>
      translate<59,110,127>
    } // end of union 
//--------------------------------------------------------------------------     
//-------------------------------------------------
// tree with leaves
   union{ 
          object{ sassafras_13_stems
                  texture{ Stem_Texture }
                } //------------------------
          object{ sassafras_13_leaves  
                  double_illuminate
                  texture{ Leaves_Texture_1 }   
                  interior_texture{ Leaves_Texture_2 }   
                } //------------------------
      rotate <0,90,0>
      translate<100, 117,164>
    } // end of union 
//--------------------------------------------------------------------------
  
      
global_settings {max_trace_level 5}

camera {
   location <-40, 125, 100>
   direction <0, 0,  2>
   look_at <100,90,150>
}

// Uncomment the area lights only if you've got lots of time.
#declare Dist=80.0;        
light_source{<13800, 500, 7460> color White*0.7
        looks_like{
           sphere{ <0,0,0>,250
                   texture{
                    pigment{color BrightGold}
                    finish {ambient 0.9
                            diffuse 0.1
                            phong 1}
                   } // end texture
                } // end of sphere
         } //end of looks_like
 } //end of light_source     
 
 #declare HF_Res_X = 1024; // number of points in x
#declare HF_Res_Z = 1024; // number of points in z
#declare HF_Function  =
 function{
   pigment{
     crackle turbulence 0.45
     color_map{
       [0.00, color 0.00]
       [0.10, color 0.25]
       [0.12, color 0.30]
       [0.30, color 0.30]
       [0.70, color 0.27]
       [1.00, color 0.25]
     }// end color_map
    scale <0.25,0.005,0.25>*0.7
   } // end pigment
 } // end function
#declare HF_Amplitude = 0.5;

height_field{
  function  HF_Res_X, HF_Res_Z
  { HF_Function(x,0,y).gray * HF_Amplitude }
  //smooth
  //water_level 0
  texture { pigment{ Brown_Agate}
            finish { phong 1 phong_size 400
                     reflection{0.25 metallic}}
          } // end of texture
  translate< 0,-.1,-.1>
  scale <2000,400,2000>
}//------------------------------------------------

// sky -----------------------------------
sky_sphere{
 pigment{ gradient y
   color_map{
   [0.0 color rgb<1,1,1> ]
   [0.3 color rgb<0.18,0.28,0.75>*0.8]
   [1.0 color rgb<0.15,0.28,0.75>*0.5]}
   scale 1.05
   translate<0,-0.05,0>
 } // end pigment
} // end sky_sphere

// spherical cloud layer ----------------
#declare R_planet = 6000000;
#declare R_sky    = R_planet + 2000;
sphere{ <0, -R_planet, 0>, R_sky hollow
  texture{
     pigment{ bozo turbulence 0.75
              octaves 6  omega 0.7
              lambda 2  phase 0.15
         color_map {
         [0.00 color rgb <1,1,1>*0.95]
         [0.05 color rgb <1,1,1>*1.25]
         [0.15 color rgb <1,1,1>*0.85]
         [0.55 color rgbt<1,1,1,1>]
         [1.00 color rgbt<1,1,1,1>]
       } // end color_map
       translate< 3, 0,-1>
       scale<0.3, 0.4, 0.2>*3
     } // end pigment
     #if(version = 3.7)
           finish{emission 1 diffuse 0}
     #else finish{ ambient 1 diffuse 0}
     #end
     scale 3000
  } // end texture
   // no_shadow // optional!!
 } // end of sphere ---------------------       
        
// sea ------------------------------
plane{<0,1,0>, 0
      texture{pigment{rgb <.2,.2,.2>}
              finish {ambient 0.15
                      diffuse 0.55
                      brilliance 6.0
                      phong 0.8
                      phong_size 120
                      reflection 0.6} 
              normal{ bumps 0.03
                        scale <100,100,100>*1
                        turbulence 0.6
                    }
              }// end of texture
     }// end of plane
//-----------------------------------           
object { 
	FirePillar(Fire_Enable, T_Stone17)
	translate <470,25,340>  
}    
object { 
	FirePillar(Fire_Enable, T_Stone17)
	translate <469,24,341>  
} 
object { 
	FirePillar(Fire_Enable, T_Stone17)
	translate <471,24,339>  
} 


union {
    rotate -y*35
}

