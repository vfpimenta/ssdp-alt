k=$1
opt=$2
if [[ $opt == "-mv" ]]; then
  mkdir -p "./k${k}/noa10_mcpr10"
  mkdir -p "./k${k}/noa10_mcpr15"
  mkdir -p "./k${k}/noa10_mcpr5"
  mkdir -p "./k${k}/noa20_mcpr10"
  mkdir -p "./k${k}/noa20_mcpr15"
  mkdir -p "./k${k}/noa20_mcpr5"
  mkdir -p "./k${k}/noa30_mcpr10"
  mkdir -p "./k${k}/noa30_mcpr15"
  mkdir -p "./k${k}/noa30_mcpr5"
  mv $(ls | grep "No_of_ants10-Min_cases_per_rule10") "./k${k}/noa10_mcpr10"
  mv $(ls | grep "No_of_ants10-Min_cases_per_rule15") "./k${k}/noa10_mcpr15"
  mv $(ls | grep "No_of_ants10-Min_cases_per_rule5") "./k${k}/noa10_mcpr5"
  mv $(ls | grep "No_of_ants20-Min_cases_per_rule10") "./k${k}/noa20_mcpr10"
  mv $(ls | grep "No_of_ants20-Min_cases_per_rule15") "./k${k}/noa20_mcpr15"
  mv $(ls | grep "No_of_ants20-Min_cases_per_rule5") "./k${k}/noa20_mcpr5"
  mv $(ls | grep "No_of_ants30-Min_cases_per_rule10") "./k${k}/noa30_mcpr10"
  mv $(ls | grep "No_of_ants30-Min_cases_per_rule15") "./k${k}/noa30_mcpr15"
  mv $(ls | grep "No_of_ants30-Min_cases_per_rule5") "./k${k}/noa30_mcpr5"
fi

for dir in ./k${k}/*; do 
  find $dir -type f | xargs grep "Average Qg" | cut -d ':' -f 3 | awk -v d="$dir" '{s+=$0}END{printf "%s: %.2f ", d, s/10}';
  find $dir -type f | xargs grep "Time" | cut -d ':' -f 3 | awk -v d="$dir" '{s+=$0}END{printf "%.2f\n", s/10}';
done;