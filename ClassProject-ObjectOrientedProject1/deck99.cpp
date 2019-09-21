/*
 * deck99.cpp
 *
 *
 *
 *  Created on: OCT 18, 2015
 *      Author: Devin Haslam
 */

 #include "card99.h"
 #include "card.h"
 #include "deck99.h"
 #include "tens.h"
 #include "aces.h"
 #include "numericalValues.h"
 #include "faceCards.h"
 #include "nines.h"

 using namespace std;

void Deck99::fill()
{
    Tens* a = new Tens(Card::Suit::Clubs);
    deck.add(a);
    Tens* b = new Tens(Card::Suit::Diamonds);
    deck.add(b);
    Tens* c = new Tens(Card::Suit::Hearts);
    deck.add(c);
    Tens* d = new Tens(Card::Suit::Spades);
    deck.add(d);

    Aces* e = new Aces(Card::Suit::Clubs);
    deck.add(e);
    Aces* f = new Aces(Card::Suit::Diamonds);
    deck.add(f);
    Aces* g = new Aces(Card::Suit::Hearts);
    deck.add(g);
    Aces* h = new Aces(Card::Suit::Spades);
    deck.add(h);

    Nines* i = new Nines(Card::Suit::Clubs);
    deck.add(i);
    Nines* j = new Nines(Card::Suit::Diamonds);
    deck.add(j);
    Nines* k = new Nines(Card::Suit::Hearts);
    deck.add(k);
    Nines* l = new Nines(Card::Suit::Spades);
    deck.add(l);

    FaceCards* m = new FaceCards(Card::Rank::Jack,Card::Suit::Clubs);
    deck.add(m);
    FaceCards* n = new FaceCards(Card::Rank::Jack,Card::Suit::Diamonds);
    deck.add(n);
    FaceCards* o = new FaceCards(Card::Rank::Jack,Card::Suit::Hearts);
    deck.add(o);
    FaceCards* p = new FaceCards(Card::Rank::Jack,Card::Suit::Spades);
    deck.add(p);
    FaceCards* q = new FaceCards(Card::Rank::Queen,Card::Suit::Clubs);
    deck.add(q);
    FaceCards* r = new FaceCards(Card::Rank::Queen,Card::Suit::Diamonds);
    deck.add(r);
    FaceCards* s = new FaceCards(Card::Rank::Queen,Card::Suit::Hearts);
    deck.add(s);
    FaceCards* t = new FaceCards(Card::Rank::Queen,Card::Suit::Spades);
    deck.add(t);
    FaceCards* u = new FaceCards(Card::Rank::King,Card::Suit::Clubs);
    deck.add(u);
    FaceCards* v = new FaceCards(Card::Rank::King,Card::Suit::Diamonds);
    deck.add(v);
    FaceCards* w = new FaceCards(Card::Rank::King,Card::Suit::Hearts);
    deck.add(w);
    FaceCards* x = new FaceCards(Card::Rank::King,Card::Suit::Spades);
    deck.add(x);

    NumericalValues* y = new NumericalValues(Card::Rank::Two,Card::Suit::Clubs);
    deck.add(y);
    NumericalValues* z = new NumericalValues(Card::Rank::Two,Card::Suit::Diamonds);
    deck.add(z);
    NumericalValues* aa = new NumericalValues(Card::Rank::Two,Card::Suit::Hearts);
    deck.add(aa);
    NumericalValues* ab = new NumericalValues(Card::Rank::Two,Card::Suit::Spades);
    deck.add(ab);
    NumericalValues* ac = new NumericalValues(Card::Rank::Three,Card::Suit::Clubs);
    deck.add(ac);
    NumericalValues* ad = new NumericalValues(Card::Rank::Three,Card::Suit::Diamonds);
    deck.add(ad);
    NumericalValues* ae = new NumericalValues(Card::Rank::Three,Card::Suit::Hearts);
    deck.add(ae);
    NumericalValues* af = new NumericalValues(Card::Rank::Three,Card::Suit::Spades);
    deck.add(af);
    NumericalValues* ag = new NumericalValues(Card::Rank::Four,Card::Suit::Clubs);
    deck.add(ag);
    NumericalValues* ah = new NumericalValues(Card::Rank::Four,Card::Suit::Diamonds);
    deck.add(ah);
    NumericalValues* ai = new NumericalValues(Card::Rank::Four,Card::Suit::Hearts);
    deck.add(ai);
    NumericalValues* aj = new NumericalValues(Card::Rank::Four,Card::Suit::Spades);
    deck.add(aj);
    NumericalValues* ak = new NumericalValues(Card::Rank::Five,Card::Suit::Clubs);
    deck.add(ak);
    NumericalValues* al = new NumericalValues(Card::Rank::Five,Card::Suit::Diamonds);
    deck.add(al);
    NumericalValues* am = new NumericalValues(Card::Rank::Five,Card::Suit::Hearts);
    deck.add(am);
    NumericalValues* an = new NumericalValues(Card::Rank::Five,Card::Suit::Spades);
    deck.add(an);
    NumericalValues* ao = new NumericalValues(Card::Rank::Siz,Card::Suit::Clubs);
    deck.add(ao);
    NumericalValues* ap = new NumericalValues(Card::Rank::Siz,Card::Suit::Diamonds);
    deck.add(ap);
    NumericalValues* aq = new NumericalValues(Card::Rank::Siz,Card::Suit::Hearts);
    deck.add(aq);
    NumericalValues* ar = new NumericalValues(Card::Rank::Siz,Card::Suit::Spades);
    deck.add(ar);
    NumericalValues* as = new NumericalValues(Card::Rank::Seven,Card::Suit::Clubs);
    deck.add(as);
    NumericalValues* au = new NumericalValues(Card::Rank::Seven,Card::Suit::Diamonds);
    deck.add(au);
    NumericalValues* av = new NumericalValues(Card::Rank::Seven,Card::Suit::Hearts);
    deck.add(av);
    NumericalValues* aw = new NumericalValues(Card::Rank::Seven,Card::Suit::Spades);
    deck.add(aw);
    NumericalValues* ax = new NumericalValues(Card::Rank::Eight,Card::Suit::Clubs);
    deck.add(ax);
    NumericalValues* ay = new NumericalValues(Card::Rank::Eight,Card::Suit::Diamonds);
    deck.add(ay);
    NumericalValues* az = new NumericalValues(Card::Rank::Eight,Card::Suit::Hearts);
    deck.add(az);
    NumericalValues* ba = new NumericalValues(Card::Rank::Eight,Card::Suit::Spades);
    deck.add(ba);


}

