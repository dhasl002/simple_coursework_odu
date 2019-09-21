#include "colors.inc"      
#include "column.inc"
#include "stones.inc"
   
#declare Fire = 

intersection {    
    sphere { 
        <0,0,0>, 1
        scale y*100
        hollow
        interior {
            media {
                intervals 10
                samples 10
                emission 2
                density {
                        spherical
                        scale <20,70,20>                                
                        warp {
                                turbulence <80,3.5,5>
                        }
                        density_map {
                                [0 rgb 0]
                                [.45 rgb 0]
                                [.6 rgb OrangeRed]
                                [.7 color Orange]
                                [.8 color Orange]
                                [.9 color White]
                                [1 color White ]
                        }
                }
                scale <0.28,0.25,0.28>
            }	        
    	}
	
    }
    
    cylinder {
        <0,1,0>
        <0,-10,0>
        10000
    }
    
    texture {
        pigment {
                Clear
        }
    }
    no_shadow
}


#macro FirePillar(FireOn, PillarMaterial)
    union { 
        #if (FireOn)
            union {
                light_source {
                		<0,1.5,0>
                		Orange
                		area_light <0,0,0>,<1,1,1>,5,5
                		//looks_like {Fire}
                		fade_distance 1
                		fade_power 1
                }
                object { 
                    Fire
                    translate y*1.5
                }
            }
        #end
    }
#end