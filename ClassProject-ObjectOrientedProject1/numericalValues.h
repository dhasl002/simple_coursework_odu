/*
 * numericalValues.h
 *
 *
 *
 *  Created on: OCT 18, 2015
 *      Author: Devin Haslam
 */

#ifndef NUMERICALVALUES_H_
#define NUMERICALVALUES_H_

#include "card.h"
#include "card99.h"

class NumericalValues: public Card99
{
    public:
        virtual ~NumericalValues();
        virtual int value(int t);
        NumericalValues(Rank theRank, Suit theSuit);

};
#endif

