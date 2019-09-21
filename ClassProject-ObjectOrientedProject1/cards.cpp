/*
 * card99.cpp
 *
 *
 *
 *  Created on: OCT 18, 2015
 *      Author: Devin Haslam
 */

 #include "card99.h"
 #include "card.h"

 using namespace std;

Card99::Card99(Suit theSuit, Rank theRank)
: Card(theSuit,theRank)
{}

int Card99::value(int t)
{
    int tally = 0;
    tally = t;
    return tally;
}



