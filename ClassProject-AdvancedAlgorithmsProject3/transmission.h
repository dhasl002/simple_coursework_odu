#ifndef TRANSMISSION_H_INCLUDED
#define TRANSMISSION_H_INCLUDED
#include <utility>
#include <string>
#include <iostream>
#include <fstream>
#include <list>
using namespace std;

class transmission
{
public:
    transmission()
    {
        transNum = 0;
        listLength = 0;
    }
    void normalize(string transmission)
    {
        for(int i = 0; i < transNum; i++)
        {

            //cout<< transmission.substr(i,1) << " " << transmission.substr(i+1,1);
            listT.push_back(make_pair(transmission.substr(0,1),transmission.substr(1,1)));
            transmission.erase(0,2);
        }

            listT.push_back(make_pair(transmission.substr(transmission.length()-2,1),transmission.substr(transmission.length()-1,1)));
            transmission.erase(transmission.length()-2,2);

            message = transmission;
    }
    void setTransNum(int i)
    {
        transNum = i;
    }
    void printToScreen()
    {
        destination = listT.back();
        listT.pop_back();

        list<pair <string, string>>::const_iterator iterator;
        for( iterator = listT.begin(); iterator != listT.end(); ++iterator)
        {
            cout<< (*iterator).first << (*iterator).second << endl;
        }
            cout<< message << endl;
            cout<< destination.first << destination.second << '\n' <<'\n';

    }


private:
    //not including starting position
    pair<string, string> destination;
    pair<string, string> temp;
    int transNum;
    int listLength;
    string message;
    list<pair <string, string>> listT;
};

#endif //TRANSMISSION_H_INCLUDED
