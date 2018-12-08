@relation base
@attribute age{'<30','>=60','30-59'}
@attribute sex{'female','male'}
@attribute histologic-type{'adeno','anaplastic','epidermoid'}
@attribute degree-of-diffe{'fairly','poorly','well'}
@attribute bone{'no','yes'}
@attribute bone-marrow{'no','yes'}
@attribute lung{'no','yes'}
@attribute pleura{'no','yes'}
@attribute peritoneum{'no','yes'}
@attribute liver{'no','yes'}
@attribute brain{'no','yes'}
@attribute skin{'no','yes'}
@attribute neck{'no','yes'}
@attribute supraclavicular{'no','yes'}
@attribute axillar{'no','yes'}
@attribute mediastinum{'no','yes'}
@attribute abdominal{'no','yes'}
@attribute class{'p','n'}
@inputs age, sex, histologic-type, degree-of-diffe, bone, bone-marrow, lung, pleura, peritoneum, liver, brain, skin, neck, supraclavicular, axillar, mediastinum, abdominal
@outputs class
'p' 'n'
'p' 'p'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'p'
'p' 'p'
'n' 'n'
'n' 'n'
'p' 'n'
'n' 'n'
'n' 'p'
'n' 'n'
'p' 'p'
'n' 'n'
'p' 'n'
'p' 'p'
'n' 'n'
'p' 'p'
'n' 'n'
'n' 'p'
'p' 'p'
'n' 'p'
'n' 'n'
'n' 'n'
'n' 'n'
'p' 'p'
'n' 'n'
'n' 'p'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'p' 'p'
'n' 'p'
'n' 'p'
'p' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'p'
'n' 'n'
'n' 'n'
'p' 'p'
'n' 'n'
'n' 'p'
'n' 'p'
'n' 'p'
'n' 'n'
'n' 'n'
'n' 'n'
'p' 'p'
'p' 'p'
'n' 'n'
'n' 'p'
'p' 'n'
'n' 'n'
'p' 'p'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'p' 'n'
'n' 'p'
'n' 'n'
'n' 'n'
'n' 'p'
'n' 'p'
'n' 'n'
'p' 'p'
'p' 'p'
'n' 'n'
'p' 'n'
'p' 'n'
'n' 'n'
'p' 'p'
'p' 'p'
'p' 'n'
'n' 'n'
'p' 'p'
'n' 'p'
'p' 'n'
'n' 'n'
'n' 'n'
'p' 'p'
'p' 'p'
'n' 'n'
'p' 'n'
'n' 'p'
'n' 'p'
'n' 'p'
'n' 'n'
'p' 'n'
'n' 'p'
'n' 'n'
'n' 'n'
'p' 'p'
'n' 'n'
'n' 'p'
'n' 'p'
'n' 'n'
'n' 'n'
'n' 'p'
'n' 'n'
'p' 'n'
'n' 'n'
'n' 'n'
'n' 'p'
'n' 'p'
'n' 'n'
'n' 'n'
'n' 'p'
'p' 'p'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'p' 'p'
'n' 'n'
'n' 'n'
'n' 'p'
'p' 'p'
'n' 'n'
'n' 'n'
'n' 'n'
'p' 'p'
'n' 'p'
'n' 'n'
'n' 'p'
'n' 'n'
'n' 'n'
'n' 'p'
'p' 'p'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'p' 'p'
'n' 'n'
'p' 'p'
'p' 'p'
'n' 'n'
'p' 'p'
'n' 'n'
'n' 'p'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'p'
'n' 'n'
'p' 'p'
'p' 'p'
'n' 'n'
'n' 'n'
'n' 'p'
'p' 'n'
'n' 'n'
'n' 'n'
'p' 'p'
'n' 'n'
'n' 'n'
'p' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'p'
'n' 'n'
'n' 'n'
'p' 'p'
'n' 'p'
'n' 'n'
'n' 'n'
'n' 'p'
'n' 'n'
'p' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'p'
'n' 'n'
'p' 'p'
'n' 'n'
'p' 'p'
'p' 'p'
'n' 'p'
'n' 'n'
'p' 'p'
'n' 'p'
'n' 'p'
'n' 'p'
'p' 'p'
'p' 'p'
'n' 'p'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'p'
'n' 'n'
'n' 'n'
'n' 'p'
'n' 'n'
'n' 'p'
'n' 'p'
'n' 'n'
'p' 'p'
'p' 'n'
'n' 'p'
'p' 'n'
'p' 'p'
'p' 'p'
'n' 'n'
'p' 'n'
'n' 'n'
'p' 'p'
'n' 'p'
'n' 'n'
'n' 'n'
'n' 'p'
'n' 'n'
'n' 'n'
'p' 'p'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'p'
'n' 'p'
'n' 'p'
'n' 'n'
'p' 'p'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'p' 'n'
'n' 'p'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'p'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'p' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'p' 'p'
'p' 'p'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'p' 'n'
'n' 'p'
'p' 'p'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'p' 'p'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'p' 'n'
'n' 'n'
'n' 'n'
'p' 'p'
'n' 'n'
'n' 'n'
'n' 'p'
'n' 'n'
'n' 'n'
'n' 'n'
'p' 'p'
'n' 'p'
'p' 'p'
'n' 'n'
'n' 'p'
'p' 'n'
'p' 'n'
'n' 'n'
'n' 'n'
'n' 'p'
'n' 'n'
'n' 'n'
'p' 'n'
'p' 'p'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'p'
'p' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'p'
'p' 'p'
'n' 'n'
'n' 'n'
'p' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'p'
'n' 'n'
'n' 'n'
'p' 'p'
'n' 'n'
'n' 'p'
'n' 'n'
'n' 'n'
'n' 'n'
'n' 'p'
'n' 'n'
'p' 'n'
'n' 'n'
'n' 'p'
'n' 'n'
