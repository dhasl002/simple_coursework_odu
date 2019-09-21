include "functions.inc"
#declare Water = material {
	texture {
		pigment {
			color rgbt <1, 1, 1, 1>
		}
		finish {
			ambient 0.0
			diffuse 0.0
			
			reflection {
				0.0, 1.0
				fresnel on
			}
			
			specular 0.4
			roughness 0.003
		}
		normal {
			bozo 0.4
			scale 0.13
		}		
	}
	interior {
		ior 1.3
	}
}