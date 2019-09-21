/*
 * numericalValues.cpp
 *
 *
 *
 *  Created on: OCT 18, 2015
 *      Author: Devin Haslam
 */

 #include "card99.h"
 #include "card.h"
 #include "numericalValues.h"

 using namespace std;

  NumericalValues::NumericalValues(Rank theRank, Suit theSuit)
: Card99(theSuit,theRank)
{}
NumericalValues::~NumericalValues() {
}

int NumericalValues::value(int t)
{
    int num = getRank();
    return num;
}
