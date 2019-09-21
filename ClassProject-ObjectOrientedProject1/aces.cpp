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
 #include "aces.h"
 using namespace std;

 Aces::Aces(Suit theSuit)
 :Card99(theSuit,Ace)
{}
Aces::~Aces() {
}

int Aces::value(int t)
{
    int tally = 0;
    tally = t;
    if(tally + 11 < 100)
    {
        return 11;
    }
    return 1;
}

