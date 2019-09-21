/*
 * nines.cpp
 *
 *
 *
 *  Created on: OCT 18, 2015
 *      Author: Devin Haslam
 */

 #include "card99.h"
 #include "card.h"
 #include "nines.h"

 using namespace std;

  Nines::Nines(Suit theSuit)
  :Card99(theSuit,Nine)
{}
Nines::~Nines() {
}

int Nines::value(int t)
{
    return 0;
}

