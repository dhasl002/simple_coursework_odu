/*
 * card99.h
 *
 *
 *
 *  Created on: OCT 18, 2015
 *      Author: Devin Haslam
 */

#ifndef CARD99_H_
#define CARD99_H_

#include "card.h"

class Card99: public Card
{
    public:
        virtual int value(int t);
    protected:
        Card99(Suit theSuit, Rank theRank);

};

#endif
