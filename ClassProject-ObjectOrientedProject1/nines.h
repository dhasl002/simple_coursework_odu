/*
 * nines.h
 *
 *
 *
 *  Created on: OCT 18, 2015
 *      Author: Devin Haslam
 */

#ifndef NINES_H_
#define NINES_H_

#include "card.h"
#include "card99.h"

class Nines: public Card99
{
    public:
        virtual ~Nines();
        virtual int value(int t);
        Nines(Suit theSuit);
};
#endif


