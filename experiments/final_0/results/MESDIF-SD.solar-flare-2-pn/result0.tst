@relation solar-flare-2-pn
@attribute largest_spot_size{A,R,S,X,K,H}
@attribute spot_distribution{X,O,I,C}
@attribute Activity integer[1,2]
@attribute Evolution integer[1,3]
@attribute Previous_24_hour_flare_activity_code integer[1,3]
@attribute Historically-complex integer[1,2]
@attribute Did_region_become_historically_complex integer[1,2]
@attribute Area integer[1,2]
@attribute Area_of_the_largest_spot integer[1,1]
@attribute C-class_flares_production_by_this_region integer[0,8]
@attribute M-class_flares_production_by_this_region integer[0,5]
@attribute X-class_flares_production_by_this_region integer[0,2]
@attribute y{p,n}
@inputs largest_spot_size, spot_distribution, Activity, Evolution, Previous_24_hour_flare_activity_code, Historically-complex, Did_region_become_historically_complex, Area, Area_of_the_largest_spot, C-class_flares_production_by_this_region, M-class_flares_production_by_this_region, X-class_flares_production_by_this_region
@outputs y
