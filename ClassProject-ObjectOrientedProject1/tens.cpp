/*
 * tens.cpp
 *
 *
 *
 *  Created on: OCT 18, 2015
 *      Author: Devin Haslam
 */

 #include "card99.h"
 #include "card.h"
 #include "tens.h"

 using namespace std;

 Tens::Tens(Suit theSuit)
: Card99(theSuit,Ten)
{}

Tens::~Tens() {
}

int Tens::value(int t)
{
    int tally = 0;
    tally = t;
    if(tally + 10 < 100)
    {
        return 0;
    }
    return -10;
}


