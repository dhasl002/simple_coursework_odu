/*
 * aces.h
 *
 *
 *
 *  Created on: OCT 18, 2015
 *      Author: Devin Haslam
 */

#ifndef ACES_H_
#define ACES_H_

#include "card.h"
#include "card99.h"

class Aces: public Card99
{
    public:
        virtual ~Aces();
        virtual int value(int t);
        Aces(Suit theSuit);
};

#endif

