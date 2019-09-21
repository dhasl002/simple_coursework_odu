#include <iostream>
#include <fstream>
#include <string>
#include <random>
#include <chrono>
#include "artists.h"

using namespace std;

int main()
{
    int artistNum = 0;
    int a = 0;
    double b = 0;
    int c = 0;
    int d = 0;
    artist * headA = NULL;
    headA = new artist;
    artist * currA = NULL;
    currA = headA;

    //if there are more than 26 artists then the id's will repeat stating again at "a"
    char alphabet[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    cout<< "How many artists are working today?" << endl;
    cin>> artistNum;


    unsigned seed = std::chrono::system_clock::now().time_since_epoch().count();
        std::default_random_engine generator(seed);
    //number of projects for each artist is determined randomly from 1-5
    if(artistNum > 0)
    {
        cout<< '\n' << "Artist" << '\t' << "Time" << '\t' << "Action" << endl;
        std::uniform_int_distribution<int> distributiona(5,20);
        std::uniform_real_distribution<double> distributionb(-.20,.20);
        std::uniform_int_distribution<int> distributionc(1,5);
        a = distributiona(generator);
        b = distributionb(generator);
        c = distributionc(generator);

        headA->setAverageTime(a);
        headA->setMult(b);
        headA->setProjectNum(c);
        headA->setID(('a'));
        headA->compute();
    }


    for(int i = 1; i < artistNum; i++)
    {
        std::uniform_int_distribution<int> distributiona(5,20);
        std::uniform_real_distribution<double> distributionb(-.2,.2);
        std::uniform_int_distribution<int> distributionc(1,5);
        a = distributiona(generator);
        b = distributionb(generator);
        c = distributionc(generator);

        currA->makeNew();
        currA=currA->getNext();
        currA->setAverageTime(a);
        currA->setMult(b);
        currA->setProjectNum(c);
        currA->setID((alphabet[i%26]));
        currA->compute();
        //cout<< currA->getID() << endl;
    }
    //the first 75% of the workers show up on time and recieve id's startine with A, the rest show up randomly during the next 3 hours
    int temp = artistNum * .75;
    currA = headA;
    for(int i = 0; i < temp; i++)
    {
        currA->setStartTime(0);
        currA = currA->getNext();
    }
    for(int i = temp; i < artistNum; i++)
    {
        std::uniform_int_distribution<int> distributiond(1,3);
        d = distributiond(generator);
        currA->setStartTime(d);
        //cout<< currA->getStartTime();
        currA = currA->getNext();
    }
    //I made the simulation last a week long in hours

    for (int i = 0; i < 168; i++)
    {
        currA = headA;
        for(int j = 0; j < artistNum; j++)
        {
            if(currA->getStartTime() == i)
            {
                currA->printStart(i);
            }
            if((currA->getStartTime() + currA->getTotalTime()) == i)
            {
                currA->printEnd(i);
            }
            currA = currA->getNext();
        }
    }

}
