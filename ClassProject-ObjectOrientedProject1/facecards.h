/*
 * faceCards.h
 *
 *
 *
 *  Created on: OCT 18, 2015
 *      Author: Devin Haslam
 */

#ifndef FACECARDS_H_
#define FACECARDS_H_

#include "card.h"
#include "card99.h"

class FaceCards: public Card99
{
    public:
        virtual ~FaceCards();
        virtual int value(int t);
        FaceCards(Rank theRank, Suit theSuit);
};

#endif
