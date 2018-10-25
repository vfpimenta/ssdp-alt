#!/bin/bash

for arg in Time WRAcc Qg Chi_Quad p_value Lift DiffSupp "Average size" cov conf "supp:" suppP suppN Coverage TP FP; do
  echo "$arg ========================"
  for file in *.out; do 
    base=$(echo $file | cut -d '.' -f 1)
    metric=$(cat $file | grep "$arg" | cut -d ':' -f 2)
    if [ "$arg" == "Coverage" ]; then
      echo "$(echo $metric | cut -d ':' -f 2 | tr -d '%' | awk '{print $1/100}')"
    else 
      echo "$metric"
    fi
  done 
done 