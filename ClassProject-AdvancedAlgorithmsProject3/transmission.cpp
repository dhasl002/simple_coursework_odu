#include <iostream>
#include <fstream>
#include <string>
#include <list>
#include "transmission.h"
#include <utility>

using namespace std;

int main()
{
    string trans;
    string temp;
    int transNum;
    ifstream fin("range.txt");
    if(fin)
    {
        while(! fin.eof() )
        {
            getline (fin,trans);
            fin >> transNum;
            getline (fin,temp);
            transmission  * t = NULL;
            t = new transmission;
            t->setTransNum(transNum);
            t->normalize(trans);
            t->printToScreen();
        }

    }
}
/*
For this situation I decided to use a list of pairs. At one point I wanted to have the pairs consist of an int and char,
but I decided to use strings because I am using getline and substring. I didn't really understand exactly how you wanted
this driver to work so I just listed a bunch of test cases in an input file. NOTICE: the way I set up my code, the 2nd line is
the amount of locations the message will pass through.


*/






