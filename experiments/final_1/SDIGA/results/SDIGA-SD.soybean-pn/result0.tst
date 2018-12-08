@relation base
@attribute date{'april','august','july','june','may','october','september'}
@attribute plant-stand{'lt-normal','normal'}
@attribute precip{'gt-norm','lt-norm','norm'}
@attribute temp{'gt-norm','lt-norm','norm'}
@attribute hail{'no','yes'}
@attribute crop-hist{'diff-lst-year','same-lst-sev-yrs','same-lst-two-yrs','same-lst-yr'}
@attribute area-damaged{'low-areas','scattered','upper-areas','whole-field'}
@attribute severity{'minor','pot-severe','severe'}
@attribute seed-tmt{'fungicide','none','other'}
@attribute germination{'80-89','90-100','lt-80'}
@attribute plant-growth{'abnorm','norm'}
@attribute leaves{'abnorm','norm'}
@attribute leafspots-halo{'absent','no-yellow-halos','yellow-halos'}
@attribute leafspots-marg{'dna','no-w-s-marg','w-s-marg'}
@attribute leafspot-size{'dna','gt-1/8','lt-1/8'}
@attribute leaf-shread{'absent','present'}
@attribute leaf-malf{'absent','present'}
@attribute leaf-mild{'absent','lower-surf','upper-surf'}
@attribute stem{'abnorm','norm'}
@attribute lodging{'no','yes'}
@attribute stem-cankers{'above-sec-nde','above-soil','absent','below-soil'}
@attribute canker-lesion{'brown','dk-brown-blk','dna','tan'}
@attribute fruiting-bodies{'absent','present'}
@attribute external-decay{'absent','firm-and-dry','watery'}
@attribute mycelium{'absent','present'}
@attribute int-discolor{'black','brown','none'}
@attribute sclerotia{'absent','present'}
@attribute fruit-pods{'diseased','dna','few-present','norm'}
@attribute fruit-spots{'absent','brown-w/blk-specks','colored','dna'}
@attribute seed{'abnorm','norm'}
@attribute mold-growth{'absent','present'}
@attribute seed-discolor{'absent','present'}
@attribute seed-size{'lt-norm','norm'}
@attribute shriveling{'absent','present'}
@attribute roots{'galls-cysts','norm','rotted'}
@attribute class{'p','n'}
@inputs date, plant-stand, precip, temp, hail, crop-hist, area-damaged, severity, seed-tmt, germination, plant-growth, leaves, leafspots-halo, leafspots-marg, leafspot-size, leaf-shread, leaf-malf, leaf-mild, stem, lodging, stem-cankers, canker-lesion, fruiting-bodies, external-decay, mycelium, int-discolor, sclerotia, fruit-pods, fruit-spots, seed, mold-growth, seed-discolor, seed-size, shriveling, roots
@outputs class
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'p' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
