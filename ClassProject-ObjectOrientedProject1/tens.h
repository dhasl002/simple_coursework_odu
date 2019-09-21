/*
 * tens.h
 *
 *
 *
 *  Created on: OCT 18, 2015
 *      Author: Devin Haslam
 */

#ifndef TENS_H_
#define TENS_H_

#include "card.h"
#include "card99.h"

class Tens: public Card99
{
    public:
        virtual ~Tens();
        virtual int value(int t);
        Tens(Suit theSuit);
};
#endif



