#!/usr/bin/env bash

files=( "10star.pas" "easter.pas" "gcd.pas" "mini.pas" "opers.pas" "primes.pas" )
pc="pascal2016.jar"

for i in "${files[@]}" 
do
    java -jar $pc -testparser test/$i;
done