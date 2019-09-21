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
 #include "faceCards.h"

 using namespace std;

  FaceCards::FaceCards(Rank theRank, Suit theSuit)
: Card99(theSuit,theRank)
{}
FaceCards::~FaceCards() {
}

int FaceCards::value(int t)
{
    return 10;
}

